package edu.umg.programacion2.agenda.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import edu.umg.programacion2.agenda.dao.CitaDAO;
import edu.umg.programacion2.agenda.modelo.Cita;

public class VentanaPrincipal extends JFrame {

    private JTextField txtCliente;
    private JTextField txtFechaHora;
    private JTextField txtServicio;
    private JTextField txtDuracion;

    private JComboBox<String> cmbEstado;

    private JTable tabla;
    private DefaultTableModel modeloTabla;

    private JButton btnCrear;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnLimpiar;

    private final CitaDAO citaDAO;

    private int idSeleccionado = -1;

    private final DateTimeFormatter formato =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public VentanaPrincipal() {

        citaDAO = new CitaDAO();

        setTitle("Agenda de Citas");
        setSize(1000, 650);
        setMinimumSize(new Dimension(850, 550));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        crearInterfaz();
        cargarCitas();
    }

    private void crearInterfaz() {

        Color azul = new Color(35, 78, 122);
        Color fondo = new Color(245, 247, 250);
        Color verde = new Color(40, 167, 69);
        Color rojo = new Color(220, 53, 69);
        Color gris = new Color(108, 117, 125);

        JPanel panelPrincipal =
                new JPanel(new BorderLayout(15, 15));

        panelPrincipal.setBackground(fondo);

        panelPrincipal.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 25, 20, 25
                )
        );

        setContentPane(panelPrincipal);

        // ENCABEZADO

        JPanel panelTitulo =
                new JPanel(new BorderLayout());

        panelTitulo.setBackground(azul);

        panelTitulo.setBorder(
                BorderFactory.createEmptyBorder(
                        18, 20, 18, 20
                )
        );

        JLabel lblTitulo =
                new JLabel(
                        "AGENDA DE CITAS",
                        SwingConstants.CENTER
                );

        lblTitulo.setForeground(Color.WHITE);

        lblTitulo.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        26
                )
        );

        JLabel lblSubtitulo =
                new JLabel(
                        "Administración de citas del negocio",
                        SwingConstants.CENTER
                );

        lblSubtitulo.setForeground(
                new Color(220, 230, 240)
        );

        lblSubtitulo.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        14
                )
        );

        panelTitulo.add(
                lblTitulo,
                BorderLayout.CENTER
        );

        panelTitulo.add(
                lblSubtitulo,
                BorderLayout.SOUTH
        );

        panelPrincipal.add(
                panelTitulo,
                BorderLayout.NORTH
        );

        // PANEL CENTRAL

        JPanel panelCentro =
                new JPanel(
                        new BorderLayout(15, 15)
                );

        panelCentro.setBackground(fondo);

        panelPrincipal.add(
                panelCentro,
                BorderLayout.CENTER
        );

        // FORMULARIO

        JPanel panelFormulario =
                new JPanel(
                        new GridBagLayout()
                );

        panelFormulario.setBackground(Color.WHITE);

        panelFormulario.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder(
                                " Datos de la Cita "
                        ),
                        BorderFactory.createEmptyBorder(
                                10, 20, 15, 20
                        )
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(8, 8, 8, 8);

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        // CLIENTE

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;

        panelFormulario.add(
                new JLabel("Cliente:"),
                gbc
        );

        txtCliente =
                new JTextField(20);

        gbc.gridx = 1;
        gbc.weightx = 1;

        panelFormulario.add(
                txtCliente,
                gbc
        );

        // FECHA Y HORA

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;

        panelFormulario.add(
                new JLabel("Fecha y hora:"),
                gbc
        );

        txtFechaHora =
                new JTextField();

        txtFechaHora.setToolTipText(
                "Ejemplo: 2026-09-25 14:30"
        );

        gbc.gridx = 1;
        gbc.weightx = 1;

        panelFormulario.add(
                txtFechaHora,
                gbc
        );

        // SERVICIO

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;

        panelFormulario.add(
                new JLabel("Servicio:"),
                gbc
        );

        txtServicio =
                new JTextField();

        gbc.gridx = 1;
        gbc.weightx = 1;

        panelFormulario.add(
                txtServicio,
                gbc
        );

        // DURACIÓN

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 0;

        panelFormulario.add(
                new JLabel(
                        "Duración (minutos):"
                ),
                gbc
        );

        txtDuracion =
                new JTextField();

        gbc.gridx = 3;
        gbc.weightx = 1;

        panelFormulario.add(
                txtDuracion,
                gbc
        );

        // ESTADO

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.weightx = 0;

        panelFormulario.add(
                new JLabel("Estado:"),
                gbc
        );

        cmbEstado =
                new JComboBox<>(
                        new String[] {
                                "pendiente",
                                "confirmada",
                                "cancelada"
                        }
                );

        gbc.gridx = 3;
        gbc.weightx = 1;

        panelFormulario.add(
                cmbEstado,
                gbc
        );

        // FORMATO DE FECHA

        JLabel lblFormato =
                new JLabel(
                        "Formato de fecha: yyyy-MM-dd HH:mm"
                );

        lblFormato.setForeground(gris);

        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 2;

        panelFormulario.add(
                lblFormato,
                gbc
        );

        panelCentro.add(
                panelFormulario,
                BorderLayout.NORTH
        );

        // TABLA

        modeloTabla =
                new DefaultTableModel(
                        new Object[] {
                                "ID",
                                "Cliente",
                                "Fecha y hora",
                                "Servicio",
                                "Duración",
                                "Estado"
                        },
                        0
                ) {

                    private static final long serialVersionUID = 1L;

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column
                    ) {
                        return false;
                    }
                };

        tabla =
                new JTable(modeloTabla);

        tabla.setRowHeight(28);

        tabla.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );

        tabla.getTableHeader().setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );

        tabla.getTableHeader()
                .setBackground(azul);

        tabla.getTableHeader()
                .setForeground(Color.WHITE);

        tabla.setSelectionBackground(
                new Color(210, 225, 240)
        );

        tabla.setSelectionForeground(
                Color.BLACK
        );

        tabla.setShowVerticalLines(false);

        tabla.setFillsViewportHeight(true);

        // CENTRAR COLUMNAS

        DefaultTableCellRenderer centro =
                new DefaultTableCellRenderer();

        centro.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        tabla.getColumnModel()
                .getColumn(0)
                .setCellRenderer(centro);

        tabla.getColumnModel()
                .getColumn(2)
                .setCellRenderer(centro);

        tabla.getColumnModel()
                .getColumn(4)
                .setCellRenderer(centro);

        tabla.getColumnModel()
                .getColumn(5)
                .setCellRenderer(centro);

        JScrollPane scroll =
                new JScrollPane(tabla);

        scroll.setBorder(
                BorderFactory.createTitledBorder(
                        " Listado de Citas "
                )
        );

        panelCentro.add(
                scroll,
                BorderLayout.CENTER
        );

        // BOTONES

        JPanel panelBotones =
                new JPanel();

        panelBotones.setBackground(fondo);

        btnCrear =
                crearBoton(
                        "Crear",
                        verde
                );

        btnActualizar =
                crearBoton(
                        "Actualizar",
                        azul
                );

        btnEliminar =
                crearBoton(
                        "Eliminar",
                        rojo
                );

        btnLimpiar =
                crearBoton(
                        "Limpiar",
                        gris
                );

        panelBotones.add(btnCrear);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);

        panelCentro.add(
                panelBotones,
                BorderLayout.SOUTH
        );

        // EVENTOS

        btnCrear.addActionListener(
                e -> crearCita()
        );

        btnActualizar.addActionListener(
                e -> actualizarCita()
        );

        btnEliminar.addActionListener(
                e -> eliminarCita()
        );

        btnLimpiar.addActionListener(
                e -> limpiarFormulario()
        );

        tabla.getSelectionModel()
                .addListSelectionListener(e -> {

                    if (!e.getValueIsAdjusting()) {
                        seleccionarCita();
                    }
                });
    }

    private JButton crearBoton(
            String texto,
            Color color
    ) {

        JButton boton =
                new JButton(texto);

        boton.setPreferredSize(
                new Dimension(130, 38)
        );

        boton.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );

        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setOpaque(true);
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);

        return boton;
    }

    // LISTAR

    private void cargarCitas() {

        modeloTabla.setRowCount(0);

        try {

            List<Cita> citas =
                    citaDAO.listarTodos();

            for (Cita cita : citas) {

                modeloTabla.addRow(
                        new Object[] {
                                cita.getId(),
                                cita.getCliente(),
                                cita.getFechaHora()
                                        .format(formato),
                                cita.getServicio(),
                                cita.getDuracionMinutos(),
                                cita.getEstado()
                        }
                );
            }

        } catch (SQLException e) {

            mostrarErrorBD(
                    "No se pudieron cargar las citas."
            );
        }
    }

    // VALIDACIONES

    private boolean validarDatos(
            String cliente,
            String servicio,
            int duracion,
            LocalDateTime fechaHora,
            boolean validarFechaPasada
    ) {

        if (cliente.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "El nombre del cliente es obligatorio.",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE
            );

            return false;
        }

        if (servicio.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "La descripción del servicio es obligatoria.",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE
            );

            return false;
        }

        if (duracion <= 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "La duración debe ser mayor que 0 minutos.",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE
            );

            return false;
        }

        if (validarFechaPasada
                && fechaHora.isBefore(
                        LocalDateTime.now()
                )) {

            JOptionPane.showMessageDialog(
                    this,
                    "La fecha y hora de una cita nueva "
                    + "no pueden estar en el pasado.",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE
            );

            return false;
        }

        return true;
    }

    // CREAR

    private void crearCita() {

        String cliente =
                txtCliente.getText().trim();

        String servicio =
                txtServicio.getText().trim();

        String textoDuracion =
                txtDuracion.getText().trim();

        String textoFecha =
                txtFechaHora.getText().trim();

        if (cliente.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "El nombre del cliente es obligatorio.",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (servicio.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "La descripción del servicio es obligatoria.",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (textoFecha.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar la fecha y hora.",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (textoDuracion.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar la duración.",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        try {

            int duracion =
                    Integer.parseInt(
                            textoDuracion
                    );

            LocalDateTime fechaHora =
                    LocalDateTime.parse(
                            textoFecha,
                            formato
                    );

            if (!validarDatos(
                    cliente,
                    servicio,
                    duracion,
                    fechaHora,
                    true
            )) {
                return;
            }

            /*
             * Una cita nueva siempre se crea
             * en estado pendiente.
             */
            Cita cita =
                    new Cita(
                            0,
                            cliente,
                            fechaHora,
                            servicio,
                            duracion,
                            "pendiente"
                    );

            citaDAO.crear(cita);

            JOptionPane.showMessageDialog(
                    this,
                    "Cita creada correctamente.",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE
            );

            limpiarFormulario();
            cargarCitas();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "La duración debe ser un número entero.",
                    "Dato incorrecto",
                    JOptionPane.WARNING_MESSAGE
            );

        } catch (DateTimeParseException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "La fecha y hora no tienen "
                    + "el formato correcto.\n"
                    + "Use: yyyy-MM-dd HH:mm",
                    "Dato incorrecto",
                    JOptionPane.WARNING_MESSAGE
            );

        } catch (SQLException e) {

            mostrarErrorBD(
                    "No se pudo crear la cita."
            );
        }
    }

    // SELECCIONAR FILA

    private void seleccionarCita() {

        int fila =
                tabla.getSelectedRow();

        if (fila == -1) {
            return;
        }

        idSeleccionado =
                Integer.parseInt(
                        modeloTabla
                                .getValueAt(
                                        fila,
                                        0
                                )
                                .toString()
                );

        txtCliente.setText(
                modeloTabla
                        .getValueAt(
                                fila,
                                1
                        )
                        .toString()
        );

        txtFechaHora.setText(
                modeloTabla
                        .getValueAt(
                                fila,
                                2
                        )
                        .toString()
        );

        txtServicio.setText(
                modeloTabla
                        .getValueAt(
                                fila,
                                3
                        )
                        .toString()
        );

        txtDuracion.setText(
                modeloTabla
                        .getValueAt(
                                fila,
                                4
                        )
                        .toString()
        );

        cmbEstado.setSelectedItem(
                modeloTabla
                        .getValueAt(
                                fila,
                                5
                        )
                        .toString()
        );
    }

    // ACTUALIZAR

    private void actualizarCita() {

        if (idSeleccionado == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione una cita de la tabla.",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String cliente =
                txtCliente.getText().trim();

        String servicio =
                txtServicio.getText().trim();

        String textoDuracion =
                txtDuracion.getText().trim();

        String textoFecha =
                txtFechaHora.getText().trim();

        if (cliente.isEmpty()
                || servicio.isEmpty()
                || textoDuracion.isEmpty()
                || textoFecha.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Todos los campos son obligatorios.",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        try {

            int duracion =
                    Integer.parseInt(
                            textoDuracion
                    );

            LocalDateTime fechaHora =
                    LocalDateTime.parse(
                            textoFecha,
                            formato
                    );

            /*
             * La regla del enunciado dice que
             * la fecha no puede haber pasado
             * al momento de CREAR la cita.
             *
             * Por eso aquí no rechazamos una
             * cita existente solamente porque
             * su fecha ya pasó.
             */
            if (!validarDatos(
                    cliente,
                    servicio,
                    duracion,
                    fechaHora,
                    false
            )) {
                return;
            }

            String estado =
                    (String) cmbEstado
                            .getSelectedItem();

            Cita cita =
                    new Cita(
                            idSeleccionado,
                            cliente,
                            fechaHora,
                            servicio,
                            duracion,
                            estado
                    );

            boolean actualizado =
                    citaDAO.actualizar(cita);

            if (actualizado) {

                JOptionPane.showMessageDialog(
                        this,
                        "Cita actualizada correctamente.",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE
                );

                limpiarFormulario();
                cargarCitas();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "La cita ya no existe.",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "La duración debe ser un número entero.",
                    "Dato incorrecto",
                    JOptionPane.WARNING_MESSAGE
            );

        } catch (DateTimeParseException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "La fecha y hora no tienen "
                    + "el formato correcto.\n"
                    + "Use: yyyy-MM-dd HH:mm",
                    "Dato incorrecto",
                    JOptionPane.WARNING_MESSAGE
            );

        } catch (SQLException e) {

            mostrarErrorBD(
                    "No se pudo actualizar la cita."
            );
        }
    }

    // ELIMINAR

    private void eliminarCita() {

        if (idSeleccionado == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione una cita de la tabla.",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int respuesta =
                JOptionPane.showConfirmDialog(
                        this,
                        "¿Está seguro de eliminar "
                        + "permanentemente esta cita?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

        if (respuesta
                != JOptionPane.YES_OPTION) {
            return;
        }

        try {

            boolean eliminado =
                    citaDAO.eliminar(
                            idSeleccionado
                    );

            if (eliminado) {

                JOptionPane.showMessageDialog(
                        this,
                        "Cita eliminada correctamente.",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE
                );

                limpiarFormulario();
                cargarCitas();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "La cita ya no existe.",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE
                );
            }

        } catch (SQLException e) {

            mostrarErrorBD(
                    "No se pudo eliminar la cita."
            );
        }
    }

    // ERROR DE BASE DE DATOS

    private void mostrarErrorBD(
            String mensaje
    ) {

        JOptionPane.showMessageDialog(
                this,
                mensaje
                + "\nVerifique la conexión "
                + "con la base de datos.",
                "Error de base de datos",
                JOptionPane.ERROR_MESSAGE
        );
    }

    // LIMPIAR FORMULARIO

    private void limpiarFormulario() {

        txtCliente.setText("");
        txtFechaHora.setText("");
        txtServicio.setText("");
        txtDuracion.setText("");

        cmbEstado.setSelectedItem(
                "pendiente"
        );

        tabla.clearSelection();

        idSeleccionado = -1;

        txtCliente.requestFocus();
    }
}
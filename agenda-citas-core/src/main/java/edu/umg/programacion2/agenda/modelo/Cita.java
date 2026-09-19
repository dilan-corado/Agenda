package edu.umg.programacion2.agenda.modelo;

import java.time.LocalDateTime;

public class Cita {

    private int id;
    private String cliente;
    private LocalDateTime fechaHora;
    private String servicio;
    private int duracionMinutos;
    private String estado;
    private boolean requiereConfirmacionLlamada;

    public Cita() {
    }

    public Cita(int id, String cliente, LocalDateTime fechaHora,
                String servicio, int duracionMinutos, String estado,
                boolean requiereConfirmacionLlamada) {

        this.id = id;
        this.cliente = cliente;
        this.fechaHora = fechaHora;
        this.servicio = servicio;
        this.duracionMinutos = duracionMinutos;
        this.estado = estado;
        this.requiereConfirmacionLlamada = requiereConfirmacionLlamada;
    }

    public Cita(String cliente, LocalDateTime fechaHora,
                String servicio, int duracionMinutos, String estado,
                boolean requiereConfirmacionLlamada) {

        this.cliente = cliente;
        this.fechaHora = fechaHora;
        this.servicio = servicio;
        this.duracionMinutos = duracionMinutos;
        this.estado = estado;
        this.requiereConfirmacionLlamada = requiereConfirmacionLlamada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(int duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isRequiereConfirmacionLlamada() {
        return requiereConfirmacionLlamada;
    }

    public void setRequiereConfirmacionLlamada(boolean requiereConfirmacionLlamada) {
        this.requiereConfirmacionLlamada = requiereConfirmacionLlamada;
    }

    @Override
    public String toString() {
        return "[" + id + "] "
                + cliente
                + " | " + fechaHora
                + " | " + servicio
                + " | " + duracionMinutos + " min"
                + " | " + estado
                + " | Confirmación llamada: "
                + (requiereConfirmacionLlamada ? "Sí" : "No");
    }
}
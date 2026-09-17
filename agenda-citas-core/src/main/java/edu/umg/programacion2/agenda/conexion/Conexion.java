package edu.umg.programacion2.agenda.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL =
            "jdbc:mariadb://localhost:3306/agenda_citas_db";

    private static final String USUARIO = "root";
    private static final String PASSWORD = "";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
    }
    
    public static void main(String[] args) {

        try {
            Connection conexion = conectar();

            System.out.println("Conexion exitosa");

            conexion.close();

        } catch (SQLException e) {
            System.out.println("Error de conexion");
            System.out.println(e.getMessage());
        }
    }
}
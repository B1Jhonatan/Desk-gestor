package com.jaimes.gestorclaves.data_base;

import com.jaimes.gestorclaves.models.EncodeModel;
import com.jaimes.gestorclaves.models.UsuarioModel;

import java.sql.*;

public class Conexion {
    private static final String urlUsers = "jdbc:sqlite:data_base/users.db";
    private static final String urlClaves = "jdbc:sqlite:data_base/claves.db";
    private static final String url = "jdbc:sqlite:data_base/xxxxx.db";


    public static void conexion(){
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String sql = """
                    CREATE TABLE IF NOT EXISTS usuarios (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        username TEXT NOT NULL,
                        password TEXT NOT NULL
                    );
                    """;
                stmt.execute(sql);
                System.out.println("Tabla creada correctamente.");
                stmt.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void savePassword(EncodeModel encodeModel){
        String sql = "INSERT INTO usuarios (nombre, clave) VALUES (?,?)";
        try(Connection conn = DriverManager.getConnection(urlClaves)){
            if (conn != null){
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, encodeModel.getName());
                pstmt.setString(2, encodeModel.getEncode());
                pstmt.executeUpdate();
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void saveUsers(UsuarioModel usuarioModel){
        String sql = "INSERT INTO usuarios (username, password) VALUES (?,?)";
        try(Connection conn = DriverManager.getConnection(urlUsers)){
            if (conn != null){
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, usuarioModel.getUsername());
                pstmt.setString(2, usuarioModel.getPassword());
                pstmt.executeUpdate();
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

}

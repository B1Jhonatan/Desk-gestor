package com.jaimes.gestorclaves.repository;

import com.jaimes.gestorclaves.models.EncodeModel;
import com.jaimes.gestorclaves.models.UsuarioModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
                pstmt.close();
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
                pstmt.close();
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static List<UsuarioModel> getUsuariosModel(){
        List<UsuarioModel> usuarioModels = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try(Connection conn = DriverManager.getConnection(urlUsers)){
            if(conn != null){
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()){
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    UsuarioModel usuarioModel = new UsuarioModel(username, password);
                    usuarioModels.add(usuarioModel);
                }

            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return usuarioModels;
    }

    public static List<EncodeModel> getEncodeModel(){
        List<EncodeModel> encodeModels = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try(Connection conn = DriverManager.getConnection(urlClaves)){
            if (conn != null){
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    String nombre = rs.getString("nombre");
                    String clave = rs.getString("clave");
                    EncodeModel encodeModel = new EncodeModel(nombre, clave);
                    encodeModels.add(encodeModel);
                }
                stmt.close();
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return encodeModels;
    }


}

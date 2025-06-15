package com.jaimes.gestorclaves.repository;

import com.jaimes.gestorclaves.models.EncodeModel;
import com.jaimes.gestorclaves.models.UsuarioModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Conexion {

    private static final String url = "jdbc:sqlite:data_base/data-app.db";

    public static void conexion(){
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String sqlUsuarios = """
                        CREATE TABLE IF NOT EXISTS usuarios (
                            id INTEGER PRIMARY KEY AUTOINCREMENT,
                            username TEXT NOT NULL,
                            password TEXT NOT NULL
                        );
                        """;
                stmt.execute(sqlUsuarios);
                String sqlClaves = """
                        CREATE TABLE IF NOT EXISTS claves (
                            id INTEGER PRIMARY KEY AUTOINCREMENT,
                            site_web TEXT NOT NULL,
                            password TEXT NOT NULL,
                            usuario_id INTEGER,
                            FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
                        );
                        """;
                stmt.execute(sqlClaves);
                System.out.println("Tabla creada correctamente.");
                stmt.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void savePassword(EncodeModel encodeModel){
        String sql = "INSERT INTO claves (site_web, password) VALUES (?,?)";
        try(Connection conn = DriverManager.getConnection(url)){
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
        try(Connection conn = DriverManager.getConnection(url)){
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
        try(Connection conn = DriverManager.getConnection(url)){
            if(conn != null){
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()){
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    UsuarioModel usuarioModel = new UsuarioModel(null, username, password);
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
        String sql = "SELECT * FROM claves";
        try(Connection conn = DriverManager.getConnection(url)){
            if (conn != null){
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    String nombre = rs.getString("site_web");
                    String clave = rs.getString("password");
                    Integer id = rs.getInt("id");
                    EncodeModel encodeModel = new EncodeModel(id, nombre, clave);
                    encodeModels.add(encodeModel);
                }
                stmt.close();
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return encodeModels;
    }

    public static void updatePassword(EncodeModel encodeModel){
        String sql = "UPDATE claves SET site_web = ?, password = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url)){
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(3, encodeModel.getId());
            pstmt.setString(1, encodeModel.getName());
            pstmt.setString(2, encodeModel.getEncode());
            pstmt.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void deletePassword(int id){
        String sql= "DELETE FROM claves WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url)) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }


}

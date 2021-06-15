/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class UsuarioModel {

    private final String SQL_INSERT
            = "INSERT INTO usuarios(usuario, contrasena, id_rol) VALUES (?)";
    private final String SQL_UPDATE
            = "UPDATE usuarios SET usuario = ?, id_rol = ? WHERE id = ?";
    private final String SQL_DELETE
            = "DELETE FROM usuarios WHERE id = ?";
    private final String SQL_SELECT
            = "SELECT usuarios.id as Codigo, usuario as Usuario, roles.nombre as Rol FROM usuarios INNER JOIN roles ON usuarios.id_rol = roles.id";
    private final String SQL_LOGIN
            = "SELECT id, usuario, id_rol FROM usuarios WHERE usuario = ? AND contrasena = ?";

    public int insert(String descripcion) {
        int rows = 0;
        try {
            Conexion conexion = new Conexion();
            Connection conn = conexion.getConexion();
            PreparedStatement stmt = null;
            stmt = conn.prepareStatement(this.SQL_INSERT);
            int index = 1;

            stmt.setString(index++, descripcion);
            rows = stmt.executeUpdate();
            System.out.println("Numero de registros afectados: " + rows);
            conexion.cerrarConexion();
        } catch (SQLException e) {
            System.out.println("Error al insertar: " + e.getMessage());
        }
        return rows;
    }

    public int update(String descripcion, int id) {
        int rows = 0;
        try {
            Conexion conexion = new Conexion();
            Connection conn = conexion.getConexion();
            PreparedStatement stmt = null;
            stmt = conn.prepareStatement(this.SQL_UPDATE);
            int index = 1;

            stmt.setString(index++, descripcion);
            stmt.setInt(index++, id);
            rows = stmt.executeUpdate();
            System.out.println("Numero de registros afectados: " + rows);
            conexion.cerrarConexion();
        } catch (SQLException e) {
            System.out.println("Error al actualizar: " + e.getMessage());
        }
        return rows;
    }

    public int delete(int id) {
        int rows = 0;
        try {
            Conexion conexion = new Conexion();
            Connection conn = conexion.getConexion();
            PreparedStatement stmt = null;
            stmt = conn.prepareStatement(this.SQL_DELETE);
            int index = 1;

            stmt.setInt(index++, id);
            rows = stmt.executeUpdate();
            System.out.println("Numero de registros afectados: " + rows);
            conexion.cerrarConexion();
        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }
        return rows;
    }

    public DefaultTableModel select() {
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        try {
            Conexion conexion = new Conexion();
            Connection conn = conexion.getConexion();
            PreparedStatement stmt = null;
            stmt = conn.prepareStatement(this.SQL_SELECT);
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();

            int numberOfColumns = meta.getColumnCount();

            // Formato encabezado
            for (int i = 1; i <= numberOfColumns; i++) {
                dtm.addColumn(meta.getColumnLabel(i));
            }

            // Creando filas de la tabla
            while (rs.next()) {
                Object[] fila = new Object[numberOfColumns];

                for (int j = 0; j < numberOfColumns; j++) {
                    fila[j] = rs.getObject(j + 1);
                }
                dtm.addRow(fila);
            }
            conexion.cerrarConexion();
        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }
        return dtm;
    }

    public Boolean login(String usuario, String contrasena) {
        Boolean autenticado = false;
        try {
            Conexion conexion = new Conexion();
            Connection conn = conexion.getConexion();
            PreparedStatement stmt = null;
            stmt = conn.prepareStatement(this.SQL_LOGIN);
            int index = 1;
            stmt.setString(index++, usuario);
            stmt.setString(index++, contrasena);
            ResultSet rs = stmt.executeQuery();

            // Creando filas de la tabla
            while (rs.next()) {
                autenticado = rs.getInt("id_rol") == 1;
            }
            conexion.cerrarConexion();
        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }
        return autenticado;
    }
}

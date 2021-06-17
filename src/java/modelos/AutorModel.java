package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javabeans.AutorBean;
import javax.swing.table.DefaultTableModel;

public class AutorModel {

    private final String SQL_INSERT
            = "INSERT INTO autores(nombres, apellidos, fecha_nacimiento) VALUES (?, ?, ?)";
    private final String SQL_UPDATE
            = "UPDATE autores SET nombres = ?, apellidos = ?, fecha_nacimiento = ? WHERE id = ?";
    private final String SQL_DELETE
            = "DELETE FROM autores WHERE id = ?";
    private final String SQL_SELECT
            = "SELECT id, nombres, apellidos, fecha_nacimiento FROM autores";

    public int insert(String nombres, String apellidos, String fecha_nacimiento) {
        int rows = 0;
        try {
            Conexion conexion = new Conexion();
            Connection conn = conexion.getConexion();
            PreparedStatement stmt = null;
            stmt = conn.prepareStatement(this.SQL_INSERT);
            int index = 1;

            stmt.setString(index++, nombres);
            stmt.setString(index++, apellidos);
            stmt.setString(index++, fecha_nacimiento);

            rows = stmt.executeUpdate();
            System.out.println("Numero de registros afectados: " + rows);
            conexion.cerrarConexion();
        } catch (SQLException e) {
            System.out.println("Error al insertar: " + e.getMessage());
        }
        return rows;
    }

    public int update(String nombres, String apellidos, String fecha_nacimiento, int id) {
        int rows = 0;
        try {
            Conexion conexion = new Conexion();
            Connection conn = conexion.getConexion();
            PreparedStatement stmt = null;
            stmt = conn.prepareStatement(this.SQL_UPDATE);
            int index = 1;

            stmt.setString(index++, nombres);
            stmt.setString(index++, apellidos);
            stmt.setString(index++, fecha_nacimiento);
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

    public List<AutorBean> list() {
        List<AutorBean> listaAutor = new ArrayList<>();

        try {
            Conexion conexion = new Conexion();
            Connection conn = conexion.getConexion();
            PreparedStatement stmt = null;
            stmt = conn.prepareStatement(this.SQL_SELECT);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                AutorBean autor = new AutorBean(rs.getInt("id"), rs.getString("nombres"), rs.getString("apellidos"), rs.getString("fecha_nacimiento"));

                listaAutor.add(autor);
            }
            conexion.cerrarConexion();
        } catch (SQLException e) {
            System.out.println("Error al consultar: " + e.getMessage());
        }

        return listaAutor;
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
}

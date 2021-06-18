package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javabeans.AutorBean;
import javabeans.CategoriaBean;
import javabeans.EjemplarBean;

public class EjemplarModel {

    private final String SQL_INSERT
            = "INSERT INTO ejemplares(nombre, descripcion, publicacion, ISBN, editorial, id_categoria, id_autor) VALUES (?, ? ,?, ?, ?, ?, ?)";
    private final String SQL_UPDATE
            = "UPDATE ejemplares SET nombre = ?, descripcion = ?, publicacion = ?, ISBN = ?, editorial = ?, id_categoria = ?, id_autor = ? WHERE id = ?";
    private final String SQL_DELETE
            = "DELETE FROM ejemplares WHERE id = ?";
    private final String SQL_SELECT
            = "SELECT ejemplares.id, nombre, ejemplares.descripcion, publicacion,\n"
            + "ISBN, editorial, categorias.id categoriaId, categorias.descripcion categoriaDescripcion,"
            + "autores.id autorId, autores.nombres autorNombres, autores.apellidos autorApellidos, autores.fecha_nacimiento\n"
            + "FROM ejemplares\n"
            + "INNER JOIN categorias ON ejemplares.id_categoria = categorias.id\n"
            + "INNER JOIN autores ON ejemplares.id_autor = autores.id ";

    private final String SQL_FIND = "SELECT * FROM ejemplares WHERE id = ?";

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

    public int find(int id) {
        int rows = 0;
        try {
            Conexion conexion = new Conexion();
            Connection conn = conexion.getConexion();
            PreparedStatement stmt = null;
            stmt = conn.prepareStatement(SQL_FIND);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                rows = 1;
            }
            conexion.cerrarConexion();
        } catch (SQLException e) {
            System.out.println("Error al consultar: " + e.getMessage());
        }
        return rows;
    }

    public List<EjemplarBean> list(EjemplarBean filtro) {
        List<EjemplarBean> listaEjemplares = new ArrayList<>();

        try {
            Boolean contieneFiltro = false;

            String CONSULTA_SELECT = this.SQL_SELECT;

            if (!filtro.getNombre().trim().isEmpty()) {
                CONSULTA_SELECT += "WHERE ejemplares.nombre LIKE '%" + filtro.getNombre().trim() + "%' ";
                contieneFiltro = true;
            }

            if (!filtro.getDescripcion().trim().isEmpty()) {
                CONSULTA_SELECT += (!contieneFiltro ? "WHERE " : "") + "ejemplares.descripcion LIKE '%" + filtro.getDescripcion().trim() + "%' ";
                contieneFiltro = true;
            }

            if (!filtro.getEditorial().trim().isEmpty()) {
                CONSULTA_SELECT += (!contieneFiltro ? "WHERE " : "") + "ejemplares.editorial LIKE '%" + filtro.getEditorial().trim() + "%' ";
                contieneFiltro = true;
            }

            if (!filtro.getIsbn().trim().isEmpty()) {
                CONSULTA_SELECT += (!contieneFiltro ? "WHERE " : "") + "ejemplares.ISBN LIKE '%" + filtro.getIsbn().trim() + "%' ";
                contieneFiltro = true;
            }

            if (filtro.getIdCategoria() > 0) {
                CONSULTA_SELECT += (!contieneFiltro ? "WHERE " : "") + "ejemplares.id_categoria = " + filtro.getIdCategoria();
                contieneFiltro = true;
            }

            if (filtro.getIdAutor() > 0) {
                CONSULTA_SELECT += (!contieneFiltro ? "WHERE " : "") + "ejemplares.id_autor = " + filtro.getIdAutor();
                contieneFiltro = true;
            }

            Conexion conexion = new Conexion();
            Connection conn = conexion.getConexion();
            PreparedStatement stmt = null;
            stmt = conn.prepareStatement(CONSULTA_SELECT);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Integer id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                String publicacion = rs.getString("publicacion");
                String isbn = rs.getString("isbn");
                String editorial = rs.getString("editorial");
                Integer categoriaId = rs.getInt("categoriaId");
                String categoriaDescripcion = rs.getString("categoriaDescripcion");
                Integer autorId = rs.getInt("autorId");
                String autorNombres = rs.getString("autorNombres");
                String autorApellidos = rs.getString("autorApellidos");
                String autorFechaNacimiento = rs.getString("fecha_nacimiento");

                CategoriaBean categoria = new CategoriaBean(categoriaId, categoriaDescripcion);
                AutorBean autor = new AutorBean(autorId, autorNombres, autorApellidos, autorFechaNacimiento);

                EjemplarBean ejemplar = new EjemplarBean(
                        id,
                        nombre,
                        descripcion,
                        publicacion,
                        isbn,
                        editorial,
                        categoriaId,
                        categoria,
                        autorId,
                        autor
                );

                listaEjemplares.add(ejemplar);
            }
            conexion.cerrarConexion();
        } catch (SQLException e) {
            System.out.println("Error al consultar: " + e.getMessage());
        }

        return listaEjemplares;
    }

}

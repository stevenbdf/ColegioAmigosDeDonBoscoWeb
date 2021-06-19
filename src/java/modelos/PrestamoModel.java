package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javabeans.PrestamoBean;

public class PrestamoModel {

    private final String SQL_INSERT
            = "INSERT INTO prestamos(fecha_prestamo, fecha_regreso, estado, ejemplar_id, usuario_id) VALUES (?, ? ,?, ?, ?)";

    private final String SQL_PRESTAMO_PENDIENTE
            = "SELECT * FROM prestamos WHERE estado = 'PRESTADO' AND fecha_regreso < DATE(NOW()) AND usuario_id = ?";

    private final String SQL_CANTIDAD_PRESTAMOS_PENDIENTES
            = "SELECT * FROM prestamos WHERE estado = 'PRESTADO' AND usuario_id = ?";

    private final String SQL_SELECT_CONFIGURACION
            = "SELECT * FROM configuracion";

    private final String SQL_SELECT_PRESTAMOS
            = "SELECT prestamos.id, fecha_prestamo, fecha_regreso, estado, ejemplares.id as idEjemplar, ejemplares.nombre\n"
            + "FROM prestamos\n"
            + "INNER JOIN ejemplares ON ejemplares.id = prestamos.ejemplar_id WHERE usuario_id = ?";

    private final String SQL_REGRESAR_PRESTAMO
            = "UPDATE prestamos SET estado = 'REGRESADO' WHERE id = ?";

    public Boolean usuarioConLimite(int idUsuario, int idRol) {
        int rows = 0, limiteAlumnos = 0, limiteProfesores = 0;
        try {
            Conexion conexion = new Conexion();
            Connection conn = conexion.getConexion();

            PreparedStatement stmt = null;
            stmt = conn.prepareStatement(SQL_SELECT_CONFIGURACION);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                limiteAlumnos = rs.getInt("ejemplares_prestar_alumnos");
                limiteProfesores = rs.getInt("ejemplares_prestar_profesor");
            }

            PreparedStatement stmt2 = null;
            stmt2 = conn.prepareStatement(SQL_CANTIDAD_PRESTAMOS_PENDIENTES);
            stmt2.setInt(1, idUsuario);
            ResultSet rs2 = stmt2.executeQuery();

            while (rs2.next()) {
                rows++;
            }
            conexion.cerrarConexion();
        } catch (SQLException e) {
            System.out.println("Error al consultar: " + e.getMessage());
        }

        return idRol == 2 ? rows >= limiteProfesores : rows >= limiteAlumnos;
    }

    public Boolean usuarioConMora(int idUsuario) {
        int rows = 0;
        try {
            Conexion conexion = new Conexion();
            Connection conn = conexion.getConexion();
            PreparedStatement stmt = null;
            stmt = conn.prepareStatement(SQL_PRESTAMO_PENDIENTE);
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                rows = 1;
            }
            conexion.cerrarConexion();
        } catch (SQLException e) {
            System.out.println("Error al consultar: " + e.getMessage());
        }

        return rows == 1;
    }

    public HashMap<Boolean, String> insert(PrestamoBean prestamo) {
        HashMap<Boolean, String> result = new HashMap();

        int rows = 0;

        if (prestamo.getFechaPrestamo() == null || prestamo.getFechaRegreso() == null) {
            result.put(false, "Fechas invalidas");
            return result;
        }

        if (!prestamo.getEstado().equals("PRESTADO")) {
            result.put(false, "Estado invalido");
            return result;
        }

        if (prestamo.getIdUsuario() < 1) {
            result.put(false, "Usuario invalido");
            return result;
        }

        if (prestamo.getIdEjemplar() < 1) {
            result.put(false, "Ejemplar invalido");
            return result;
        }

        try {
            Date fechaPrestamo = new SimpleDateFormat("yyyy-MM-dd").parse(prestamo.getFechaPrestamo());
            Date fechaRegreso = new SimpleDateFormat("yyyy-MM-dd").parse(prestamo.getFechaRegreso());

            if (fechaRegreso.compareTo(fechaPrestamo) < 0) {
                result.put(false, "Fecha de regreso no puede ser menor a la fecha de prestamo");
                return result;
            }
        } catch (ParseException ex) {
            Logger.getLogger(PrestamoModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            Conexion conexion = new Conexion();
            Connection conn = conexion.getConexion();
            PreparedStatement stmt = null;
            stmt = conn.prepareStatement(this.SQL_INSERT);
            int index = 1;

            stmt.setString(index++, prestamo.getFechaPrestamo());
            stmt.setString(index++, prestamo.getFechaRegreso());
            stmt.setString(index++, prestamo.getEstado());
            stmt.setInt(index++, prestamo.getIdEjemplar());
            stmt.setInt(index++, prestamo.getIdUsuario());

            rows = stmt.executeUpdate();
            if (rows > 0) {
                result.put(true, "Prestamo guardado existosamente");
            } else {
                result.put(false, "Error al guardar ejemplar");
            }
            conexion.cerrarConexion();
        } catch (SQLException e) {
            System.out.println("Error al insertar: " + e.getMessage());
        }

        return result;
    }

    public List<PrestamoBean> usuarioPrestamos(int idUsuario) {
        List<PrestamoBean> listaEjemplares = new ArrayList<>();

        try {
            Conexion conexion = new Conexion();
            Connection conn = conexion.getConexion();

            PreparedStatement stmt = null;
            stmt = conn.prepareStatement(SQL_SELECT_PRESTAMOS);
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Integer id = rs.getInt("id");
                String fechaPrestamo = rs.getString("fecha_prestamo");
                String fechaRegreso = rs.getString("fecha_regreso");
                String estado = rs.getString("estado");
                Integer idEjemplar = rs.getInt("idEjemplar");
                String nombreEjemplar = rs.getString("nombre");

                PrestamoBean prestamo = new PrestamoBean(
                        id,
                        fechaPrestamo,
                        fechaRegreso,
                        estado,
                        idEjemplar,
                        nombreEjemplar,
                        idUsuario
                );

                listaEjemplares.add(prestamo);
            }

            conexion.cerrarConexion();
        } catch (SQLException e) {
            System.out.println("Error al consultar: " + e.getMessage());
        }

        return listaEjemplares;
    }

    public Boolean regresarPrestamo(int idPrestamo) {
        int rows = 0;
        try {
            Conexion conexion = new Conexion();
            Connection conn = conexion.getConexion();
            PreparedStatement stmt = null;
            stmt = conn.prepareStatement(SQL_REGRESAR_PRESTAMO);
            stmt.setInt(1, idPrestamo);
            rows = stmt.executeUpdate();
            conexion.cerrarConexion();
        } catch (SQLException e) {
            System.out.println("Error al actualizar: " + e.getMessage());
        }
        return rows > 0;
    }
}

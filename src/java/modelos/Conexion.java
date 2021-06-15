package modelos;

import java.sql.*;

public class Conexion {

    private Connection conexion = null;

    private final String SERVIDOR_BD = "localhost";
    private final String PUERTO_BD = "3306";
    private final String NOMBRE_BD = "cadb";
    private final String USUARIO_BD = "root";
    private final String CONTRASENA_BD = "";

    //Contructor
    public Conexion() throws SQLException {
        try {
            //obtenemos el driver de para mysql
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Se obtiene una conexión con la base de datos.
            this.conexion = DriverManager.getConnection("jdbc:mysql://" + this.SERVIDOR_BD + ":" + this.PUERTO_BD + "/" + this.NOMBRE_BD,
                    this.USUARIO_BD, this.CONTRASENA_BD);
        } catch (ClassNotFoundException e1) {
            //Error si no puedo leer el driver de MySQL
            System.out.println("ERROR:No encuentro el driver de la BD:" + e1.getMessage());
        }
    }
    
    //Metodo que retorna la conexion
    public Connection getConexion() {
        return this.conexion;
    } 

    //Metodo que cierra la conexion
    public void cerrarConexion() throws SQLException {
        this.conexion.close();
    }
}

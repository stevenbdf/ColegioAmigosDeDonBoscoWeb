package javabeans;

public class LoginBean {

    private String usuario;
    private String contrasena;

    public LoginBean() {

    }

    public LoginBean(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the contrasena
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * @param contrasena the contrasena to set
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}

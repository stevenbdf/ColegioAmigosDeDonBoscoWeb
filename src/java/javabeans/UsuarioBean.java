/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabeans;

public class UsuarioBean {

    private Integer id;
    private String usuario;
    private Integer idRol;

    public UsuarioBean() {
        this.id = 0;
        this.usuario = "";
        this.idRol = 0;
    }

    public UsuarioBean(Integer id, String usuario, Integer idRol) {
        this.id = id;
        this.usuario = usuario;
        this.idRol = idRol;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
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
     * @return the idRol
     */
    public Integer getIdRol() {
        return idRol;
    }

    /**
     * @param idRol the idRol to set
     */
    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }
}

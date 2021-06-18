package javabeans;

import java.time.LocalDate;

public class PrestamoBean {

    private Integer id;
    private String fechaPrestamo;
    private String fechaRegreso;
    private String estado;
    private Integer idEjemplar;
    private Integer idUsuario;

    public PrestamoBean() {
        this.id = 0;
        this.fechaPrestamo = LocalDate.now().toString();
        this.estado = "PRESTADO";
    }

    public PrestamoBean(Integer id, String fechaPrestamo, String fechaRegreso, String estado, Integer idEjemplar, Integer idUsuario) {
        this.id = id;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaRegreso = fechaRegreso;
        this.estado = estado;
        this.idEjemplar = idEjemplar;
        this.idUsuario = idUsuario;
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
     * @return the fechaPrestamo
     */
    public String getFechaPrestamo() {
        return fechaPrestamo;
    }

    /**
     * @param fechaPrestamo the fechaPrestamo to set
     */
    public void setFechaPrestamo(String fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    /**
     * @return the fechaRegreso
     */
    public String getFechaRegreso() {
        return fechaRegreso;
    }

    /**
     * @param fechaRegreso the fechaRegreso to set
     */
    public void setFechaRegreso(String fechaRegreso) {
        this.fechaRegreso = fechaRegreso;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the idEjemplar
     */
    public Integer getIdEjemplar() {
        return idEjemplar;
    }

    /**
     * @param idEjemplar the idEjemplar to set
     */
    public void setIdEjemplar(Integer idEjemplar) {
        this.idEjemplar = idEjemplar;
    }

    /**
     * @return the idUsuario
     */
    public Integer getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
}

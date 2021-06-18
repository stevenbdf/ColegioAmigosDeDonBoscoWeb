package javabeans;

public class EjemplarBean {

    private Integer id;
    private String nombre;
    private String descripcion;
    private String publicacion;
    private String isbn;
    private String editorial;
    private Integer idCategoria;
    private CategoriaBean categoria;
    private Integer idAutor;
    private AutorBean autor;

    public EjemplarBean() {
        this.id = 0;
        this.nombre = "";
        this.descripcion = "";
        this.publicacion = "";
        this.isbn = "";
        this.editorial = "";
        this.idCategoria = 0;
        this.categoria = new CategoriaBean();
        this.idAutor = 0;
        this.autor = new AutorBean();
    }

    public EjemplarBean(Integer id, String nombre, String descripcion, String publicacion, String isbn, String editorial,
            Integer idCategoria, CategoriaBean categoria, Integer idAutor, AutorBean autor) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.publicacion = publicacion;
        this.isbn = isbn;
        this.editorial = editorial;
        this.idCategoria = idCategoria;
        this.categoria = categoria;
        this.idAutor = idAutor;
        this.autor = autor;
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
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the publicacion
     */
    public String getPublicacion() {
        return publicacion;
    }

    /**
     * @param publicacion the publicacion to set
     */
    public void setPublicacion(String publicacion) {
        this.publicacion = publicacion;
    }

    /**
     * @return the isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * @param isbn the isbn to set
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * @return the editorial
     */
    public String getEditorial() {
        return editorial;
    }

    /**
     * @param editorial the editorial to set
     */
    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    /**
     * @return the idCategoria
     */
    public Integer getIdCategoria() {
        return idCategoria;
    }

    /**
     * @param idCategoria the idCategoria to set
     */
    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    /**
     * @return the categoria
     */
    public CategoriaBean getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(CategoriaBean categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the idAutor
     */
    public Integer getIdAutor() {
        return idAutor;
    }

    /**
     * @param idAutor the idAutor to set
     */
    public void setIdAutor(Integer idAutor) {
        this.idAutor = idAutor;
    }

    /**
     * @return the autor
     */
    public AutorBean getAutor() {
        return autor;
    }

    /**
     * @param autor the autor to set
     */
    public void setAutor(AutorBean autor) {
        this.autor = autor;
    }

}

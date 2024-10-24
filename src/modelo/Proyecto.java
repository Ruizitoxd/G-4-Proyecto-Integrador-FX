package modelo;

public class Proyecto {
    private String id;
    private String nombre;
    private int cantidadTorres;
    private RolUsuario idAdmin;

    // Constructor vacío
    public Proyecto() {
        // Constructor sin implementación especial
    }

    // Constructor completo
    public Proyecto(String id, String nombre, int cantidadTorres, RolUsuario idAdmin) {
        this.id = id;
        this.nombre = nombre;
        this.cantidadTorres = cantidadTorres;
        this.idAdmin = idAdmin;
    }

    // Getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidadTorres() {
        return cantidadTorres;
    }

    public void setCantidadTorres(int cantidadTorres) {
        this.cantidadTorres = cantidadTorres;
    }

    public RolUsuario getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(RolUsuario idAdmin) {
        this.idAdmin = idAdmin;
    }
}

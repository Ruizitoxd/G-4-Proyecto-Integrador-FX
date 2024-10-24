package modelo;

public class RolUsuario {
    private int id;
    private String identificacion;
    private String correo;
    private String nombre;
    private String direccion; 

    // Constructor vacío
    public RolUsuario() {
        // Constructor vacío sin excepción
    }

    // Constructor con parámetros
    public RolUsuario(int id, String identificacion, String correo, String nombre, String direccion) {
        this.id = id;
        this.identificacion = identificacion;
        this.correo = correo;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
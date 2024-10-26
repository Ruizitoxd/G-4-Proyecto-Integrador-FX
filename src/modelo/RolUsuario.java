package modelo;

public class RolUsuario {
    private String id;
    private String identificacion;
    private String correo;
    private String nombre;
    private String direccion; 
    private String rol;

    // Constructor vacío
    public RolUsuario() {
        this.id = "";
        this.identificacion = "";
        this.correo = "";
        this.nombre = "";
        this.direccion = "";
    }

    // Constructor con parámetros
    public RolUsuario(String id, String identificacion, String correo, String nombre, String direccion, String rol) {
        this.id = id;
        this.identificacion = identificacion;
        this.correo = correo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.rol = rol;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }    
}

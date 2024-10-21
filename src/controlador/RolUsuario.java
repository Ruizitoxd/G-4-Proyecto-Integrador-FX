package controlador;
import datos.ValidarAdmin;
import datos.ValidarAsesor;

public class RolUsuario {
    private int id;
    private String identificacion, correo, nombre,direcion,telefono;

    public RolUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

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

    public String getDirecion() {
        return direcion;
    }

    public void setDirecion(String direcion) {
        this.direcion = direcion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public RolUsuario(int id, String identificacion, String correo, String nombre, String direcion, String telefono) {
        this.id = id;
        this.identificacion = identificacion;
        this.correo = correo;
        this.nombre = nombre;
        this.direcion = direcion;
        this.telefono = telefono;
    }
    
    public String validarRol(String correo, String identificacion){
        ValidarAdmin valAd = new ValidarAdmin();
        ValidarAsesor valAs = new ValidarAsesor();
        if(valAd.validarAdmin(correo, identificacion)){
            return "admin";
        }else if(valAs.validarAsesor(correo, identificacion)){

            return "asesor";
        }else{
            return "error";

        }
    }
}

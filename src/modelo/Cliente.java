
package modelo;


public class Cliente {
    private String id;
    private String identificacion;
    private String correo;
    private String nombre;
    private String apellido;
    private String direccion;
    private String SISBEN;
    private String subsidio;
    private int numeroTelef;
    
    public Cliente(){
        this.id = "";
        this.identificacion = "";
        this.correo = "";
        this.nombre = "";
        this.apellido="";
        this.direccion = "";
        this.SISBEN = "";
        this.subsidio = "";
        this.numeroTelef = 0;
    }

    public int getNumeroTelef() {
        return numeroTelef;
    }

    public void setNumeroTelef(int numeroTelef) {
        this.numeroTelef = numeroTelef;
    }



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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getSISBEN() {
        return SISBEN;
    }

    public void setSISBEN(String SISBEN) {
        this.SISBEN = SISBEN;
    }

    public String getSubsidio() {
        return subsidio;
    }

    public void setSubsidio(String subsidio) {
        this.subsidio = subsidio;
    }

    public Cliente(String id, String identificacion, String correo, String nombre, String apellido, String direccion, String SISBEN, String subsidio) {
        this.id = id;
        this.identificacion = identificacion;
        this.correo = correo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.SISBEN = SISBEN;
        this.subsidio = subsidio;
    }


}

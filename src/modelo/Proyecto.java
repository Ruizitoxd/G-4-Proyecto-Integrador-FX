package modelo;

public class Proyecto {
    private int id; // Mantener como int o cambiar a String
    private String nombre;
    private int cantidadTorres;

    // Constructor que acepta todos los parámetros
    public Proyecto(int id, String nombre, int cantidadTorres) {
        this.id = id;
        this.nombre = nombre;
        this.cantidadTorres = cantidadTorres;
    }

    // Constructor por defecto
    public Proyecto() {
    }

    // Métodos getter y setter
    public int getId() { // Cambiado a int
        return id;
    }

    public void setId(int id) { // Cambiado a int
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
}

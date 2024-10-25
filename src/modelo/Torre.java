
package modelo;

public class Torre {
    private int id;
    private String nombre;
    private String id_proy;
    private int cantidadAparta;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId_proy() {
        return id_proy;
    }

    public void setId_proy(String id_proy) {
        this.id_proy = id_proy;
    }

    public int getCantidadAparta() {
        return cantidadAparta;
    }

    public void setCantidadAparta(int cantidadAparta) {
        this.cantidadAparta = cantidadAparta;
    }
    
    public Torre(int id, String nombre, String id_proy, int cantidadAparta) {
        this.id = id;
        this.nombre = nombre;
        this.id_proy = id_proy;
        this.cantidadAparta = cantidadAparta;
    }

    public Torre() {
    }
    
}

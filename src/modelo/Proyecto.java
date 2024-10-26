package modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Proyecto {
    private IntegerProperty id, cantidadTorres;
    private StringProperty nombre;

    public Proyecto(int id, int cantidadTorres, String nombre) {
        this.id = new SimpleIntegerProperty(id);
        this.cantidadTorres = new SimpleIntegerProperty(cantidadTorres);
        this.nombre = new SimpleStringProperty(nombre);
    }
    
    public Proyecto() {        
        this.id = new SimpleIntegerProperty(0);
        this.cantidadTorres = new SimpleIntegerProperty(0);
        this.nombre = new SimpleStringProperty("");
    }
    
    //Getters y Setters
    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public int getCantidadTorres() {
        return cantidadTorres.get();
    }

    public void setCantidadTorres(int cantidadTorres) {
        this.cantidadTorres.set(cantidadTorres);
    }

    @Override
    public String toString() {
        return "Proyecto{" + "id=" + id + ", cantidadTorres=" + cantidadTorres + ", nombre=" + nombre + '}';
    }
}
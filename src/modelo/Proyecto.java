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
    
    //Getters para los String y Integer Property
        public IntegerProperty idProperty() {
        return id;
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public IntegerProperty cantidadTorresProperty() {
        return cantidadTorres;
    }

    // Métodos convencionales para obtener y establecer valores de los tipos int y String directamente
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
}
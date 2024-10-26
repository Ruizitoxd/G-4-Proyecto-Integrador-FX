
package modelo;

import java.util.ArrayList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Torre {
    private IntegerProperty id; 
    private StringProperty nombre;
    private StringProperty id_proy;
    private IntegerProperty cantidadApartamentos;
    private ArrayList<Apartamento> apartamentos;
    
    public Torre(int id, String nombre, String id_proy, int cantidadApartamentos) {
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.id_proy = new SimpleStringProperty(id_proy);
        this.cantidadApartamentos = new SimpleIntegerProperty(cantidadApartamentos);
    }

    public Torre() {
        this.id = new SimpleIntegerProperty(0);
        this.nombre = new SimpleStringProperty("");
        this.id_proy = new SimpleStringProperty("");
        this.cantidadApartamentos = new SimpleIntegerProperty(0);
    }
    
    //Getters para los String y Integer Property
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

    public String getId_proy() {
        return id_proy.get();
    }

    public void setId_proy(String id_proy) {
        this.id_proy.set(id_proy);
    }

    public int getCantidadApartamentos() {
        return cantidadApartamentos.get();
    }

    public void setCantidadApartamentos(int cantidadApartamentos) {
        this.cantidadApartamentos.set(cantidadApartamentos);
    }
}

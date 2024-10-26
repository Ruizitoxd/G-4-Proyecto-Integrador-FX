package modelo;

import java.util.ArrayList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Proyecto {
    private IntegerProperty id, cantidadTorres;
    private StringProperty nombre;
    private ArrayList<Torre> torres;
    
    public Proyecto(int id, int cantidadTorres, String nombre, ArrayList<Torre> torres) {
        this.id = new SimpleIntegerProperty(id);
        this.cantidadTorres = new SimpleIntegerProperty(cantidadTorres);
        this.nombre = new SimpleStringProperty(nombre);
        this.torres = torres;
    }

    public Proyecto(int id, int cantidadTorres, String nombre) {
        this.id = new SimpleIntegerProperty(id);
        this.cantidadTorres = new SimpleIntegerProperty(cantidadTorres);
        this.nombre = new SimpleStringProperty(nombre);
        this.torres = new ArrayList<>();
    }
    
    public Proyecto() {        
        this.id = new SimpleIntegerProperty(0);
        this.cantidadTorres = new SimpleIntegerProperty(0);
        this.nombre = new SimpleStringProperty("");
        this.torres = new ArrayList<>();
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
    
    public void modificarTorres(ArrayList<Torre> listaTorres){
        this.torres = listaTorres;
    }
    
    public void añadirTorre(Torre torre){
        this.torres.add(torre);
        this.cantidadTorres.set(this.cantidadTorres.get() + 1);
    }
    
    public ArrayList<Torre> obtenerTorres(){
        return this.torres;
    }

    @Override
    public String toString() {
        return "Proyecto{" + "id=" + id + ", cantidadTorres=" + cantidadTorres + ", nombre=" + nombre + '}';
    }
}
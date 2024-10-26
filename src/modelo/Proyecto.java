package modelo;

<<<<<<< HEAD
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
=======
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
    
    //Getters para los String y Integer Property
    public IntegerProperty idProperty() {
        return id;
    }

    public StringProperty nombreProperty() {
>>>>>>> 7a334b8097fd190c412dbccea12952a9f52f7f5d
        return nombre;
    }

    public IntegerProperty cantidadTorresProperty() {
        return cantidadTorres;
    }

<<<<<<< HEAD
    public int getCantidadTorres() {
        return cantidadTorres;
=======
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

    @Override
    public String toString() {
        return "Proyecto{" + "id=" + id + ", cantidadTorres=" + cantidadTorres + ", nombre=" + nombre + '}';
>>>>>>> 7a334b8097fd190c412dbccea12952a9f52f7f5d
    }

    public void setCantidadTorres(int cantidadTorres) {
        this.cantidadTorres = cantidadTorres;
    }
}

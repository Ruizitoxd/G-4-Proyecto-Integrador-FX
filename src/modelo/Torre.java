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

    public Torre(int id, String nombre, String id_proy, int cantidadApartamentos, ArrayList<Apartamento> listaApartamentos) {
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.id_proy = new SimpleStringProperty(id_proy);
        this.cantidadApartamentos = new SimpleIntegerProperty(cantidadApartamentos);
        this.apartamentos = listaApartamentos;
    }

    public Torre() {
        this.id = new SimpleIntegerProperty(0);
        this.nombre = new SimpleStringProperty("");
        this.id_proy = new SimpleStringProperty("");
        this.cantidadApartamentos = new SimpleIntegerProperty(0);
        this.apartamentos = new ArrayList<>();
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

    public void ModificarTorres(ArrayList<Apartamento> listaApartamentos) {
        this.apartamentos = listaApartamentos;
    }

    public void AñadirApartamento(Apartamento apartamento) {
        this.apartamentos.add(apartamento);
        this.cantidadApartamentos.set(this.cantidadApartamentos.get() + 1);
    }

    public ArrayList<Apartamento> obtenerApartamentos() {
        return this.apartamentos;
    }

    @Override
    public String toString() {
        return "Torre{" + "id=" + id + ", nombre=" + nombre + ", id_proy=" + id_proy + ", cantidadApartamentos=" + cantidadApartamentos + ", apartamentos=" + apartamentos + '}';
    }
}

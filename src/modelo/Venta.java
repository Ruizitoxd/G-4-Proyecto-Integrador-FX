package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Venta {
    private IntegerProperty id;
    private DoubleProperty valor;
    private DoubleProperty interes;
    private ArrayList<Pago> pagos;
    private IntegerProperty cantidadVentas;
    private IntegerProperty numCuotas;

    // Constructor corregido
    public Venta(int id, double valor, double interes, ArrayList<Pago> pagos, int cantidadVentas, int numCuotas) {
        this.id = new SimpleIntegerProperty(id);
        this.valor = new SimpleDoubleProperty(valor);
        this.interes = new SimpleDoubleProperty(interes);
        this.pagos = pagos;
        this.cantidadVentas = new SimpleIntegerProperty(cantidadVentas);
        this.numCuotas = new SimpleIntegerProperty(numCuotas);
    }

    // Constructor vacío
    public Venta() {
        this.pagos = new ArrayList<>();
    }

    // Getters y Setters para los valores
    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public double getValor() {
        return valor.get();
    }

    public void setValor(double valor) {
        this.valor.set(valor);
    }

    public double getInteres() {
        return interes.get();
    }

    public void setInteres(double interes) {
        this.interes.set(interes);
    }


    public ArrayList<Pago> getPagos() {
        return pagos;
    }

    public void setPagos(ArrayList<Pago> pagos) {
        this.pagos = pagos;
    }

    public int getCantidadVentas() {
        return cantidadVentas.get();
    }

    public void setCantidadVentas(int cantidadVentas) {
        this.cantidadVentas.set(cantidadVentas);
    }

    public int getNumCuotas() {
        return numCuotas.get();  // Devuelve el valor de numCuotas
    }

    public void setNumCuotas(int numCuotas) {
        this.numCuotas.set(numCuotas);
    }

}

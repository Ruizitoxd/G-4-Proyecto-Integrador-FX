package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Venta {

    private IntegerProperty id;
    private DoubleProperty valor;
    private DoubleProperty interes;
    private ArrayList<Pago> pagos;
    private IntegerProperty numCuotas;
    private StringProperty idApartamento;

    // Constructor corregido
    public Venta(int id, double valor, double interes, ArrayList<Pago> pagos, int numCuotas, String idApartamento) {
        this.id = new SimpleIntegerProperty(id);
        this.valor = new SimpleDoubleProperty(valor);
        this.interes = new SimpleDoubleProperty(interes);
        this.pagos = pagos;
        this.numCuotas = new SimpleIntegerProperty(numCuotas);
        this.idApartamento = new SimpleStringProperty(idApartamento);
    }

    public Venta(int id, double valor, double interes, int numCuotas, String idApartamento) {
        this.id = new SimpleIntegerProperty(id);
        this.valor = new SimpleDoubleProperty(valor);
        this.interes = new SimpleDoubleProperty(interes);
        this.pagos = new ArrayList<>();
        this.numCuotas = new SimpleIntegerProperty(numCuotas);
        this.idApartamento = new SimpleStringProperty(idApartamento);
    }

    // Constructor vacío
    public Venta() {
        this.id = new SimpleIntegerProperty(0);
        this.valor = new SimpleDoubleProperty(0);
        this.interes = new SimpleDoubleProperty(0);
        this.pagos = new ArrayList<>();
        this.numCuotas = new SimpleIntegerProperty(0);
        this.idApartamento = new SimpleStringProperty("");
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

    public int getNumCuotas() {
        return numCuotas.get();  // Devuelve el valor de numCuotas
    }

    public void setNumCuotas(int numCuotas) {
        this.numCuotas.set(numCuotas);
    }

    public String getIdApartamento() {
        return idApartamento.get();
    }

    public void setIdApartamento(String idApartamento) {
        this.idApartamento.set(idApartamento);
    }
}
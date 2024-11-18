package modelo;

import java.time.LocalDate;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Pago {

    private IntegerProperty id;
    private DoubleProperty valor;
    private LocalDate fechaApagar;
    private LocalDate fechaPago;
    private LocalDate fechaVencimiento;
    private StringProperty idVenta;
    private StringProperty idCliente;
    private StringProperty estado;

    public Pago(int id, double valor, LocalDate fechaApagar, LocalDate fechaPago, LocalDate fechaVencimiento, String idVenta, String idCliente, String estado) {
        this.id = new SimpleIntegerProperty(id);
        this.valor = new SimpleDoubleProperty(valor);
        this.fechaApagar = fechaApagar;
        this.fechaPago = fechaPago;
        this.fechaVencimiento = fechaVencimiento;
        this.idVenta = new SimpleStringProperty(idVenta);
        this.idCliente = new SimpleStringProperty(idCliente);
        this.estado = new SimpleStringProperty(estado);
    }

    public Pago() {
        this.id = new SimpleIntegerProperty(0);
        this.valor = new SimpleDoubleProperty(0.0);
        this.fechaApagar = fechaApagar;
        this.fechaPago = fechaPago;
        this.fechaVencimiento = fechaVencimiento;
        this.idVenta = new SimpleStringProperty("");
        this.idCliente = new SimpleStringProperty("");
        this.estado = new SimpleStringProperty("");
    }

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

    public LocalDate getFechaApagar() {
        return fechaApagar;
    }

    public void setFechaApagar(LocalDate fechaApagar) {
        this.fechaApagar = fechaApagar;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getIdVenta() {
        return idVenta.get();
    }

    public void setIdVenta(String idVenta) {
        this.idVenta.set(idVenta);
    }

    public String getIdCliente() {
        return idCliente.get();
    }

    public void setIdCliente(String idCliente) {
        this.idCliente.set(idCliente);
    }

    public String getEstado() {
        return estado.get();
    }

    public void setEstado(String estado) {
        this.estado.set(estado);
    }
}

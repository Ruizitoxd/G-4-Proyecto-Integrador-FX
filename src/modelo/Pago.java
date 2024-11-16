package modelo;

import java.sql.Date;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Pago {

    private IntegerProperty id;
    private DoubleProperty valor;
    private java.sql.Date fechaApagar;
    private java.sql.Date fechaPago;
    private java.sql.Date fechaVencimiento;
    private StringProperty idVenta;
    private StringProperty idCliente;
    private StringProperty estado;

    public StringProperty getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado.set(estado);
    }

    public Pago(int id, double valor, java.sql.Date fechaApagar, java.sql.Date fechaPago, java.sql.Date fechaVencimiento, String idVenta, String idCliente, String estado) {
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

    public IntegerProperty getId() {
        return id;
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

    public java.sql.Date getFechaApagar() {
        return fechaApagar;
    }

    public void setFechaApagar(java.sql.Date fechaApagar) {
        this.fechaApagar = fechaApagar;
    }

    public java.sql.Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(java.sql.Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public java.sql.Date getFechaVenciomiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(java.sql.Date fechaVencimiento) {
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
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.LocalDate;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;

/**
 *
 * @author josem
 */
public class Pago {
    private IntegerProperty id;
    private DoubleProperty valor;
    private ObjectProperty<LocalDate> fecha;

    public Pago(IntegerProperty id, DoubleProperty valor, ObjectProperty<LocalDate> fecha) {
        this.id = id;
        this.valor = valor;
        this.fecha = fecha;
    }

    public Pago() {
    }

    public IntegerProperty getId() {
        return id;
    }

    public void setId(IntegerProperty id) {
        this.id = id;
    }

    public DoubleProperty getValor() {
        return valor;
    }

    public void setValor(DoubleProperty valor) {
        this.valor = valor;
    }

    public ObjectProperty<LocalDate> getFecha() {
        return fecha;
    }

    public void setFecha(ObjectProperty<LocalDate> fecha) {
        this.fecha = fecha;
    }
 
}

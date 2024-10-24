/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author santi
 */
public class Apartamento {
    
    //Atributoos
    private int id;
    private String nombre, matricula, area, fechaEscritura;
    private double valor;

    //metodos
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getArea() {
        return area;
    }

    public String getFechaEscritura() {
        return fechaEscritura;
    }

    
    public double getValor() {
        return valor;
    }

    //constructor
    public Apartamento(int id, String nombre, String matricula, String area, String fechaEscritura, double valor) {
        this.id = id;
        this.nombre = nombre;
        this.matricula = matricula;
        this.area = area;
        this.fechaEscritura = fechaEscritura;
        this.valor = valor;
    }
}

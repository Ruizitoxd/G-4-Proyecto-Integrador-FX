/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author santi
 */
public class Torre {
    
    //Atributos
    private int id;
    private String numero;

    //metodos
    public int getId() {
        return id;
    }

    
    public String getNumero() {
        return numero;
    }

    //constructor
    public Torre(int id, String numero) {
        this.id = id;
        this.numero = numero;
    }
    
    
}

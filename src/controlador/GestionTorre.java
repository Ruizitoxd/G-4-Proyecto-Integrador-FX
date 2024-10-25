/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import datos.TorreDAO;
import java.util.List;
import modelo.Torre;

public class GestionTorre {
    TorreDAO torreDAO = new TorreDAO();
    
    public List<Torre> obtenerTorre(int id){
        return torreDAO.mostrarTorre(id);
    }
    
    public int obtenerTotalProyecto(){
        return torreDAO.CantidadTorre();
    }
    
    public boolean guardarTorre(Torre tr,int idProy){
        return torreDAO.crearTorre(tr, idProy);
    }
    
    public boolean actualizarTorre(int idTorre, String nombre){
        return torreDAO.editarProyecto(idTorre,nombre);
    }
    
    public boolean borrarTorre(int id){
        return torreDAO.EliminarTorre(id);
    }
}

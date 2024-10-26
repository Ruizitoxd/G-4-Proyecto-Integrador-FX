
package controlador;

import datos.ApartamentoDAO;
import java.util.ArrayList;
import modelo.Apartamento;

public class GestionApartamento {
    ApartamentoDAO apartamentoDAO= new ApartamentoDAO();
    
<<<<<<< HEAD
    public List<Apartamento> obtenerApartamento(int id){
=======
    public ArrayList<Apartamento> obtenerApartamento(int id){
>>>>>>> 9567d7a377f780cd888e5fb4cceaf41e0a682991
        return apartamentoDAO.MostrarApartamento(id);
    }
    
    public int obtenerApartamentos(){
        return apartamentoDAO.CantidadApartamentos();
    }
    
    public boolean guardarApartamento(Apartamento a ,int idTorre, int idTipoUnidad){
        return apartamentoDAO.CrearApartamento(a, idTorre, idTipoUnidad);
    }
    
    public boolean ActualizarApartamento(int id , String numero, double valor, String area){
        return apartamentoDAO.EditarApartamento(id, numero, valor, area);
    }
    
    public boolean borrarApartamento(int id){
        return apartamentoDAO.EliminarApartamento(id);
    }
    
    public ArrayList<String> obtenerTipoUnidades(){
        return apartamentoDAO.ObtenerTipoUnidad();
    }
}

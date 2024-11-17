
package controlador;
import datos.ClienteDAO;
import modelo.Cliente;


public class GestionCliente {
    ClienteDAO Cdao = new ClienteDAO();
    
    
    public boolean GuardarCliente(Cliente cl){
        return Cdao.CrearCliente(cl);
    }
    
    public int IdentificarCliente(String cedula){
        return Cdao.BuscarCliente(cedula);
    }
    
    public boolean GuardarNuemroCliente(int id_cliente, String numero){
        return Cdao.numeroDeTelefonoDelCliente(id_cliente, numero);
    }
}

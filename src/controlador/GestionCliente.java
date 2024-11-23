package controlador;

import datos.ClienteDAO;
import modelo.Cliente;

public class GestionCliente {

    ClienteDAO clienteDAO = new ClienteDAO();

    public boolean GuardarCliente(String nombre, String apellido, String direccion, String identificacion, Double subsidio, String correoElectronico, String sisben) {
        return clienteDAO.CrearCliente(nombre, apellido, direccion, identificacion, subsidio, correoElectronico, sisben);
    }

    public int IdentificarCliente(String cedula) {
        return clienteDAO.BuscarCliente(cedula);
    }

    public boolean GuardarNumeroCliente(int id_cliente, String numero) {
        return clienteDAO.numeroDeTelefonoDelCliente(id_cliente, numero);
    }

    public boolean ObtenerSISBEN(int id_cliente) {
        return clienteDAO.ConsultarSISBEN(id_cliente);
    }

    public String obtenerNombre(int id_cliente) {
        return clienteDAO.consultarNombre(id_cliente);
    }

    public Double obtenerSubsidio(int id_cliente) {
        return clienteDAO.consultarSubsidio(id_cliente);
    }

    public String obtenerNumero(int id_cliente) {
        return clienteDAO.consultarNumero(id_cliente);
    }
    
    public boolean GuardarNuemroCliente(int id_cliente, String numero){
        return clienteDAO.numeroDeTelefonoDelCliente(id_cliente, numero);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Proyecto;
import modelo.RolUsuario;



public class ProyectoDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    ConexionBD conexion = new ConexionBD();
    
    public List<Proyecto> mostrarProyectos(String idAdmin){
    
        List<Proyecto> proyectos = new ArrayList<>();
        String sql ="SELECT p.id, p.nombre, COUNT(t.id) AS numTorres " +
                 "FROM proyecto p " +
                 "LEFT JOIN torre t ON p.id = t.id_proyecto " +
                 "WHERE p.id_admin = ? " +
                 "GROUP BY p.id, p.nombre";
        
        try{
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, idAdmin); 
            rs = ps.executeQuery();
            while(rs.next()){
                Proyecto proyecto = new Proyecto();
                proyecto.setId(rs.getString("id"));
                proyecto.setNombre(rs.getString("nombre"));
                proyecto.setCantidadTorres(rs.getInt("COUNT(t.id)"));
                proyectos.add(proyecto);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        finally {
            conexion.closeConnection();
        }
        return proyectos;
    }
    
    
    public int cantidadProyectos(){
        String sql="select count(*) as proyectos from proyectos";
        int TotalProyectos =0;
        try{
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            if(rs.next()){
                TotalProyectos= rs.getInt("proyectos");}
            }catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            }
        finally {
            conexion.closeConnection();
        }
    return TotalProyectos;    
    }
    
    
    public boolean crearProyecto(Proyecto pr, String idAdmin) {
        String sql = "INSERT INTO proyecto(id, nombre, id_admin) VALUES (?, ?, ?)";

        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, pr.getId());
            ps.setString(2, pr.getNombre());
            ps.setString(3, idAdmin);

            int resultado = ps.executeUpdate();

            if (resultado > 0) {
                return true;
            }
        } catch (SQLException sqlex) {
            System.out.println("ERROR: " + sqlex.getMessage());
        } finally {
            conexion.closeConnection();
        }
        return false;
    }
    
    public boolean editarProyecto(String id, String nombre) {
        String sql = "UPDATE proyecto " +  
                     "SET nombre = ? " +
                     "WHERE id = ?";

        try {
            con = conexion.getConnection();           
            ps = con.prepareStatement(sql);           
            ps.setString(1, nombre);                  
            ps.setString(2, id);                      
            ps.executeUpdate();                      
            return true;                              
        } catch (SQLException ex) {
            System.out.println("Error al actualizar proyecto: " + ex.getMessage());
            return false;                            
        } finally {
           conexion.closeConnection(); 
        }
    }


    
    
    
}
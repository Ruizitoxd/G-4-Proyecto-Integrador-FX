
package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Torre;

public class TorreDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    ConexionBD conexion = new ConexionBD();
    
    public List<Torre> mostrarTorre(int id){
        List<Torre> torres = new ArrayList();
        String sql = "select t.id as idTorre, t.numero as numTorre, count(a.id) as cantidadApa"+
                    "from torre t"+
                    "left join apartamento a"+
                    "on t.id = id_torre"+
                    "where id_proy= ?"+
                    "group by t.id, t.numero";
        
        try{
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                Torre torre = new Torre();
                torre.setId(rs.getInt("idTorre"));
                torre.setNombre(rs.getString("numTorre"));
                torre.setCantidadAparta(rs.getInt("cantidadApa"));
                torres.add(torre);
            }
        }catch(SQLException ex){
            System.out.println("Error: " + ex.getMessage());
        }finally {
            conexion.closeConnection();
        }
        return torres;
    }
    
    
    public int CantidadTorre(){
        String sql = "select count(*) as torres"+
                    "from torre";
        int TotalTorres = 0;
        try{
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            if(rs.next()){
                TotalTorres = rs.getInt("torres");}
        }catch(SQLException ex){
                System.out.println("Error: " + ex.getMessage());
        }finally {
            conexion.closeConnection();
        }
        return TotalTorres;
    }  
    
    
    public boolean crearTorre(Torre tr, int idProy){
        String sql= "insert into torre(id, numero ,idProy)"+
                    "values (?,?,?)";
        try{
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, tr.getId());
            ps.setString(2, tr.getNombre());
            ps.setInt(3, idProy);
            
            int resultado = ps.executeUpdate();
            if(resultado > 0){
                return true;
            }
        }catch(SQLException ex){
            System.out.println("Error :"+ ex.getMessage());
        }finally {
            conexion.closeConnection();
        }
        return false;
    }
    
    
    public boolean editarProyecto(int idTorre, String nombre){
        String sql = "update torre"+
                    "set numero = ?"+
                    "where id = ?";
        try{
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setInt(2, idTorre);
            ps.executeUpdate();
            return true;          
        }catch(SQLException ex){
            System.out.println("Error al actualizar Torre :"+ ex.getMessage());
            return false;
        }finally {
            conexion.closeConnection();
        }
    }
    
    
    public boolean EliminarTorre(int id){
        String sql = " delete from torre"+
                    "where id = ?";
        try{
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true ;
        }catch(SQLException ex){
            System.out.println("Error al borrar torre"+ ex.getMessage());
            return false;
        }finally{
            conexion.closeConnection();
        }
    }
    
    
}
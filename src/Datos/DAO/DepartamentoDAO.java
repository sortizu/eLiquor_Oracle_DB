package Datos.DAO;

import Datos.Entidades.Departamento;
import java.util.List;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;


/**
 *
 * @author richard
 */
public class DepartamentoDAO implements CRUD{
    Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    @Override
    public int add(Object[] o) {
        int r = 0;
        int id=setLastId()+1;
        String sql = 
            "insert into departamento(fechaRegistro, nombre, cantidad, idDepartamento) values(?,?,?,?)";
        try{
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            
            ps.setObject(1, o[0]);
            ps.setObject(2, o[1]);
            ps.setObject(3, o[2]);
            ps.setObject(4, id);
            r=ps.executeUpdate();
        }catch(SQLException e){
             System.out.println(e.toString());
         }
        return r;
    }
    
    
    @Override
    public List listar() {
        List<Departamento> lista = new ArrayList<>();
        String sql = "select * from departamento";
        try{
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Departamento d = new Departamento();
                d.setIdDepartamento(rs.getInt(1));
                d.setFechaRegistro(rs.getDate(2).toLocalDate());
                d.setNombre(rs.getString(3));
                d.setCantidad(rs.getInt(4));
        
                lista.add(d);
            }
        }catch(SQLException e){
             System.out.println(e.toString());
         }
        return lista;
    }

    @Override
    public void eliminar(int id) {
        String sql = "delete from departamento where idDepartamento=?";
        try{
           con = cn.Conectar();
           ps = con.prepareStatement(sql);
           ps.setInt(1,id);
           ps.executeUpdate();
       }catch(SQLException e){
            System.out.println(e.toString());
        }
    }
    
    @Override
    public int actualizar(Object[] o) {
        int r = 0;
        String sql = "update departamento set fechaRegistro=?, nombre=?, cantidad=?"
                + " where idDepartamento=?";
        try{
           con = cn.Conectar();
           ps = con.prepareStatement(sql);
           ps.setObject(1, o[0]);
           ps.setObject(2, o[1]);
           ps.setObject(3, o[2]);
           ps.setObject(4, o[3]);
           
           r = ps.executeUpdate();
       }catch(SQLException e){
            System.out.println(e.toString());
        }
       return r;
    }
    
    
    public int setLastId(){
        int id=1;
       String sql = "SELECT MAX(idDepartamento) from departamento;";
       try{
           con = cn.Conectar();
           ps = con.prepareStatement(sql);
           rs = ps.executeQuery();
           
           rs.beforeFirst();
           rs.next();
           
           id = rs.getInt(1);
           
       }catch(SQLException e){
            System.out.println(e.toString());
        }
       return id;
    }
       
    
}

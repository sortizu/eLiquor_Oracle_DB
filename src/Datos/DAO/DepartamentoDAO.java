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
        String sql = 
                "BEGIN "
                + "ROOT.SP_AGREGAR_DEPARTAMENTO(?);"
                + "END;";
        try{
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            
            ps.setObject(1, o[0]);
            r=ps.executeUpdate();
        }catch(SQLException e){
             System.out.println(e.toString());
         }
        return r;
    }
    
    
    @Override
    public List listar() {
        List<Departamento> lista = new ArrayList<>();
        String sql = "select * from ROOT.VW_TOTAL_DEPARTAMENTOS";
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
    
    public void eliminacionLogica(int departamendoID){
        String sql = 
            "BEGIN "
            +"ROOT.SP_ELIMINAR_DEPARTAMENTO(?);"
            +"END;";
        try{
           con = cn.Conectar();
           ps = con.prepareStatement(sql);
           ps.setInt(1, departamendoID);
           ps.executeUpdate();
       }catch(SQLException e){
            System.out.println(e.toString());
        }
    }
    
    @Override
    public int actualizar(Object[] o) {
        int r = 0;
        String sql = "BEGIN "
                    + "ROOT.SP_MODIFICAR_DEPARTAMENTO(?,?);"
                    + "END;";
        try{
           con = cn.Conectar();
           ps = con.prepareStatement(sql);
           ps.setObject(1, o[0]);
           ps.setObject(2, o[1]);
           
           r = ps.executeUpdate();
       }catch(SQLException e){
            System.out.println(e.toString());
        }
       return r;
    }
}

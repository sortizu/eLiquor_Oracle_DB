/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos.DAO;

import Datos.Entidades.DepartamentoProducto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author richard
 */
public class DepartamentoProductoDAO implements CRUD{
    
    Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    
    @Override
    public int add(Object[] o){
        
        int r = 0;
        int id=setLastId()+1;
        String sql = 
            "insert into departamentoproducto(idDepartamento, idProducto, idDepartamentoProducto) values(?,?,?)";
        try{
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setObject(1, o[0]);
            ps.setObject(2, o[1]);
            ps.setObject(3, id);
            r=ps.executeUpdate();
            
        }catch(SQLException e){
             System.out.println(e.toString());
         }
        return r;
    }
    
    @Override
    public List listar() {
        List<DepartamentoProducto> lista = new ArrayList<DepartamentoProducto>();
        String sql = "select * from departamentoproducto";
        try{
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                DepartamentoProducto dp  = new DepartamentoProducto();
                dp.setIdDepartamentoProducto(rs.getInt(1));
                dp.setIdDepartamento(rs.getInt(2));
                dp.setIdProducto(rs.getInt(3));
                
                lista.add(dp);
            }
        }catch(SQLException e){
             System.out.println(e.toString());
         }
        return lista;
    }
    
    // listar de acuerdo al id
    public List obtenerIdDeProducto(int idDepartamento) {
        
        List<Integer> lista = new ArrayList<Integer>();
        String sql = "select idProducto from departamentoproducto where idDepartamento=?";
        try{
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setObject(1, idDepartamento);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                lista.add(rs.getInt(1));
            }
            
        }catch(SQLException e){
             System.out.println(e.toString());
         }
        return lista;
    }
    
    @Override
    public void eliminar(int id) {
        String sql = "delete from departamentoproducto where idDepartamentoProducto=?";
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
        String sql = "update departamentoproducto set idDepartamento=?, idProducto=? "
                +"where idDepartamentoProducto=?";
        try{
           con = cn.Conectar();
           ps = con.prepareStatement(sql);
           ps.setObject(1, o[0]);
           ps.setObject(2, o[1]);
           ps.setObject(3, o[2]);
           
           r = ps.executeUpdate();
       }catch(SQLException e){
            System.out.println(e.toString());
        }
       return r;
    }
    
    public int setLastId(){
        int id=1;
       String sql = "SELECT MAX(idDepartamentoProducto) from departamentoproducto;";
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
       
  


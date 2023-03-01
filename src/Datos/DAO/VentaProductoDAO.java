/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos.DAO;

import Datos.Entidades.VentaProducto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class VentaProductoDAO implements CRUD{
    
    Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public int add(Object[] o) {
        int r = 0;
        int id=setLastId()+1;
        String sql = 
            "insert into ventaproducto(idVenta, idProducto, cantidadProducto, idVentaProducto ) values(?,?,?,?)";
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
    public int actualizar(Object[] o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List listar() {
        List<VentaProducto> lista = new ArrayList<>();
        String sql = "select * from ventaproducto";
        try{
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                VentaProducto p = new VentaProducto();
                p.setIdVentaProducto(rs.getInt(1));
                p.setIdVenta(rs.getInt(2));
                p.setIdProducto(rs.getInt(3));
                p.setCantidadProducto(rs.getInt(4));
                
                lista.add(p);
            }
        }catch(SQLException e){
             System.out.println(e.toString());
         }
        return lista;
    }
    
    public int setLastId(){
        int id=1;
       String sql = "SELECT MAX(idVentaProducto) from ventaproducto;";
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

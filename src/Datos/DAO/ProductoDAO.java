
package Datos.DAO;

import Datos.Entidades.Producto;
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
public class ProductoDAO implements CRUD{
    Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    @Override
    public int add(Object[] o) {
        int r = 0;
        int id=setLastId()+1;
        String sql = 
            "insert into producto(nombre, precio, costo, stock, precioVariable, activarDescuentos, mostrarEnCaja, fechaRegistro, IGV, ISC,estadoEliminacion,idProducto) values(?,?,?,?,?,?,?,?,?,?,?,?)";
        try{
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setObject(1, o[0]);
            ps.setObject(2, o[1]);
            ps.setObject(3, o[2]);
            ps.setObject(4, o[3]);
            ps.setObject(5, o[4]);
            ps.setObject(6, o[5]);
            ps.setObject(7, o[6]);
            ps.setObject(8, o[7]);
            ps.setObject(9, o[8]);
            ps.setObject(10, o[9]);
            ps.setObject(11, 0);
            ps.setObject(12, id);
            r=ps.executeUpdate();
            
        }catch(SQLException e){
             System.out.println(e.toString());
         }
        return r;
    }
    
    @Override
    public List listar() {
        List<Producto> lista = new ArrayList<Producto>();
        String sql = "select * from producto where estadoEliminacion=0";
        try{
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Producto p = new Producto();
                p.setIdProducto(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setPrecio(rs.getDouble(3));
                p.setCosto(rs.getDouble(4));
                p.setStock(rs.getInt(5));
                p.setPrecioVariable(rs.getBoolean(6));
                p.setActivarDescuentos(rs.getBoolean(7));
                p.setMostrarEnCaja(rs.getBoolean(8));
                p.setFechaRegistro(rs.getDate(9).toLocalDate());
                p.setIGV(rs.getBoolean(10));
                p.setISC(rs.getBoolean(11));

                lista.add(p);
            }
        }catch(SQLException e){
             System.out.println(e.toString());
         }
        return lista;
    }
    
    public Producto obtenerProductoPorSuID(int idProducto) {
        Producto p  = new Producto();
        String sql = "select nombre, precio, costo, stock, precioVariable, "+
                "activarDescuentos, mostrarEnCaja, fechaRegistro, "
                +"IGV, ISC from producto where idProducto=?";
        try{
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setObject(1, idProducto);
            
            rs = ps.executeQuery();
            while(rs.next()){
                p.setIdProducto(idProducto);
                p.setNombre(rs.getString(1));
                p.setPrecio(rs.getDouble(2));
                p.setCosto(rs.getDouble(3));
                p.setStock(rs.getInt(4));
                p.setPrecioVariable(rs.getBoolean(5));
                p.setActivarDescuentos(rs.getBoolean(6));
                p.setMostrarEnCaja(rs.getBoolean(7));
                p.setFechaRegistro(rs.getDate(8).toLocalDate());
                p.setIGV(rs.getBoolean(9));
                p.setISC(rs.getBoolean(10));
            }
                
                
                
                
        }catch(SQLException e){
             System.out.println(e.toString());
         }
        return p;
    }
    
    
    

    @Override
    public void eliminar(int id) {
        String sql = "delete from producto where idProducto=?";
        try{
           con = cn.Conectar();
           ps = con.prepareStatement(sql);
           ps.setInt(1,id);
           ps.executeUpdate();
       }catch(SQLException e){
            System.out.println(e.toString());
        }
    }
    
    public void eliminacionLogica(int id){
        String sql = "update producto set estadoEliminacion=? where IdProducto=?";
        try{
           con = cn.Conectar();
           ps = con.prepareStatement(sql);
           ps.setInt(1, 1);
           ps.setInt(2, id);
           ps.executeUpdate();
       }catch(SQLException e){
            System.out.println(e.toString());
        }
    }
    
    @Override
    public int actualizar(Object[] o) {
        int r = 0;
        String sql = "update producto set nombre=?, precio=?, costo=?,stock=?, precioVariable=?, activarDescuentos=?, "
                +"mostrarEnCaja=?,  fechaRegistro=?, IGV=?, ISC=? where idProducto=?";
        try{
           con = cn.Conectar();
           ps = con.prepareStatement(sql);
           ps.setObject(1, o[0]);
           ps.setObject(2, o[1]);
           ps.setObject(3, o[2]);
           ps.setObject(4, o[3]);
           ps.setObject(5, o[4]);
           ps.setObject(6, o[5]);
           ps.setObject(7, o[6]);
           ps.setObject(8, o[7]);
           ps.setObject(9, o[8]);
           ps.setObject(10, o[9]);
           ps.setObject(11, o[10]);
           
           
           r = ps.executeUpdate();
       }catch(SQLException e){
            System.out.println(e.toString());
        }
       return r;
    }
    
    
    
    
    
    public int setLastId(){
        int id=1;
       String sql = "SELECT MAX(idProducto) from producto;";
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


package Datos.DAO;

import Datos.Entidades.Entrega;
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
        String sql = "BEGIN "
                    + "ROOT.SP_AGREGAR_PRODUCTO(?,?,?,?,?,?,?,?);"
                    + "END;";
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
            r=ps.executeUpdate();
        }catch(SQLException e){
             System.out.println(e.toString());
         }
        return r;
    }
    
    @Override
    public List listar() {
        return null;
    }
    
    public List listarProductosDeDepartamento(int departamentoID){
        List<Producto> lista = new ArrayList<Producto>();
        String sql = "SELECT * FROM ROOT.VW_PRODUCTOS_EN_DEPARTAMENTO";
        try{
            con = cn.Conectar();
            ps = con.prepareStatement(
            "BEGIN ROOT.ROOT_INVENTARIO.V_DEPARTAMENTO_ID_BUSQUEDA:=?; END;"
            );
            ps.setObject(1, departamentoID<0?null:departamentoID);
            ps.executeUpdate();
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

                lista.add(p);
            }
        }catch(SQLException e){
             System.out.println(e.toString());
         }
        return lista;
    }
    
    public List listarProductosDeDepartamentoEnCaja(int departamentoID){
        List<Producto> lista = new ArrayList<Producto>();
        String sql = "SELECT * FROM ROOT.VW_PRODUCTOS_EN_DEP_CAJA";
        try{
            con = cn.Conectar();
            ps = con.prepareStatement(
            "BEGIN ROOT.ROOT_INVENTARIO.V_DEPARTAMENTO_ID_BUSQUEDA:=?; END;"
            );
            ps.setObject(1, departamentoID<0?null:departamentoID);
            ps.executeUpdate();
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
    
    public void registrarEntregaProducto(Object[] e){
        String sql = "BEGIN "
                    + "ROOT.SP_INGRESO_PRODUCTO(?,?,?);"
                    + "END;";
        try{
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setObject(1, e[0]);
            ps.setObject(2, e[1]);
            ps.setObject(3, e[2]);
            rs = ps.executeQuery();
                
        }catch(SQLException er){
             System.out.println(er.toString());
         }
    }
    

    @Override
    public void eliminar(int id) {
    }
    
    public void eliminacionLogica(int id){
        String sql = "BEGIN "
                    + "ROOT.SP_ELIMINAR_PRODUCTO(?);"
                    + "END;";
        try{
           con = cn.Conectar();
           ps = con.prepareStatement(sql);
           ps.setInt(1, id);
           ps.executeUpdate();
       }catch(SQLException e){
            System.out.println(e.toString());
        }
    }
    
    @Override
    public int actualizar(Object[] o) {
        int r = 0;
        String sql = "BEGIN "
                    + "ROOT.SP_MODIFICAR_PRODUCTO(?,?,?,?,?,?,?,?);"
                    + "END;";
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

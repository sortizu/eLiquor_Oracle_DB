package Datos.DAO;

import Datos.Entidades.Proveedor;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author 
 */
public class ProveedorDAO implements CRUD{
    Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    @Override
    public int add(Object[] o) {
        int r = 0;
        int id=setLastId()+1;
        String sql = 
            "insert into proveedor(razonSocial, correo, telefono, fechaRegistro,estadoEliminacion,idProveedor)values(?,?,?,?,?,?)";
        try{
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setObject(1, o[0]);
            ps.setObject(2, o[1]);
            ps.setObject(3, o[2]);
            ps.setObject(4, o[3]);
            ps.setObject(5, 0);
            ps.setObject(6, id);
            r = ps.executeUpdate();
        }catch(SQLException e){
             System.out.println(e.toString());
         }
        return r;
    }
    

    @Override
    public List listar() {
        List<Proveedor> lista = new ArrayList<>();
        String sql = "select * from proveedor where estadoEliminacion=0";
        try{
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Proveedor p = new Proveedor();
                p.setIdProveedor(rs.getInt(1));
                p.setRazonSocial(rs.getString(2));
                p.setTelefono(rs.getInt(3));
                p.setCorreo(rs.getString(4));
                p.setFechaRegistro(rs.getDate(5).toLocalDate());
                lista.add(p);
            }
        }catch(SQLException e){
             System.out.println(e.toString());
         }
        return lista;
    }

    @Override
    public void eliminar(int id) {
        String sql = "delete from proveedor where idProveedor=?";
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
        String sql = "update proveedor set estadoEliminacion=? where IdProveedor=?";
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
        String sql = "update proveedor set razonSocial=?,correo=?,telefono=?,fechaRegistro=? where IdProveedor=?";
        try{
           con = cn.Conectar();
           ps = con.prepareStatement(sql);
           ps.setObject(1, o[0]);
           ps.setObject(2, o[1]);
           ps.setObject(3, o[2]);
           ps.setObject(4, o[3]);
           ps.setObject(5, o[4]);
           r = ps.executeUpdate();
       }catch(SQLException e){
            System.out.println(e.toString());
        }
       return r;
    }
    
    public int setLastId(){
        int id=1;
       String sql = "SELECT MAX(idProveedor) from proveedor;";
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

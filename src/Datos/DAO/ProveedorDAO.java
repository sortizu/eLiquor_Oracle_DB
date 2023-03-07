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
        String sql = 
            "BEGIN "
            +"SP_AGREGAR_PROVEEDOR(?,?,?);"
            +"END;";
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
    

    @Override
    public List listar() {
        List<Proveedor> lista = new ArrayList<>();
        String sql = "select * from VW_TOTAL_PROVEEDORES";
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
        String sql = 
            "BEGIN "
            +"SP_ELIMINAR_PROVEEDOR(?);"
            +"END;";
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
        String sql = 
            "BEGIN "
            +"SP_MODIFICAR_PROVEEDOR(?,?,?,?);"
            +"END;";
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
}

package Datos.DAO;

import Datos.Entidades.Cliente;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author 
 */
public class ClienteDAO implements CRUD{
    Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    @Override
    public int add(Object[] o) {
        int r = 0;
        String sql = 
            "BEGIN "
            +"ROOT.SP_AGREGAR_CLIENTE(?,?,?);"
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
        List<Cliente> lista = new ArrayList<>();
        String sql = "select * from ROOT.VW_TOTAL_CLIENTES";
        try{
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Cliente c = new Cliente();
                c.setIdCliente(rs.getInt(1));
                c.setNombre(rs.getString(2));
                c.setTelefono(rs.getInt(3));
                c.setCorreo(rs.getString(4));
                c.setFechaRegistro(rs.getDate(5).toLocalDate());
                lista.add(c);
            }
        }catch(SQLException e){
             System.out.println(e.toString());
         }
        return lista;
    }

    @Override
    public void eliminar(int id) {
        String sql = "delete from cliente where idCliente=?";
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
            +"ROOT.SP_ELIMINAR_CLIENTE(?);"
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
            +"ROOT.SP_MODIFICAR_CLIENTE(?,?,?,?);"
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

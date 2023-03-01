package Datos.DAO;

import Datos.Entidades.Usuario;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author sortizu
 */
public class UsuarioDAO implements CRUD{
    Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    @Override
    public int add(Object[] o) {
        int r = 0;
        int id=setLastId()+1;
        String sql = 
            "insert into usuarios(nombre, PIN, gestionarVentas, gestionarUsuarios, gestionarProveedores, gestionarClientes, gestionarInventario, generarReportes,estado,ultimoIngreso,fechaRegistro,estadoEliminacion,idUsuario)values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
            ps.setObject(12, 0);
            ps.setObject(13, id);
            r = ps.executeUpdate();
        }catch(SQLException e){
             System.out.println(e.toString());
         }
        return r;
    }
    

    @Override
    public List listar() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "select * from usuarios where estadoEliminacion=0";
        try{
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt(1));
                u.setNombre(rs.getString(2));
                u.setPIN(rs.getInt(3));
                u.setGestionarVentas(rs.getBoolean(4));
                u.setGestionarUsuarios(rs.getBoolean(5));
                u.setGestionarProveedores(rs.getBoolean(6));
                u.setGestionarClientes(rs.getBoolean(7));
                u.setGestionarInventario(rs.getBoolean(8));
                u.setGenerarReportes(rs.getBoolean(9));
                u.setEstado(rs.getBoolean(10));
                Date fechaCargada = rs.getDate(11);
                if(fechaCargada!=null){
                    u.setUltimoIngreso(fechaCargada.toLocalDate());
                }
                u.setFechaRegistro(rs.getDate(12).toLocalDate());
                lista.add(u);
            }
        }catch(SQLException e){
             System.out.println(e.toString());
         }
        return lista;
    }

    public List listarUsuarioActivos(){
        List<Usuario> lista = new ArrayList<>();
        String sql = "select * from usuarios where estadoEliminacion=0 and estado=1";
        try{
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt(1));
                u.setNombre(rs.getString(2));
                u.setPIN(rs.getInt(3));
                u.setGestionarVentas(rs.getBoolean(4));
                u.setGestionarUsuarios(rs.getBoolean(5));
                u.setGestionarProveedores(rs.getBoolean(6));
                u.setGestionarClientes(rs.getBoolean(7));
                u.setGestionarInventario(rs.getBoolean(8));
                u.setGenerarReportes(rs.getBoolean(9));
                u.setEstado(rs.getBoolean(10));
                Date fechaCargada = rs.getDate(11);
                if(fechaCargada!=null){
                    u.setUltimoIngreso(fechaCargada.toLocalDate());
                }
                u.setFechaRegistro(rs.getDate(12).toLocalDate());
                lista.add(u);
            }
        }catch(SQLException e){
             System.out.println(e.toString());
         }
        return lista;
    }    
    
    @Override
    public void eliminar(int id) {
        String sql = "delete from usuarios where idUsuario=?";
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
        String sql = "update usuarios set estadoEliminacion=? where IdUsuario=?";
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
        String sql = "update usuarios set nombre=?,PIN=?,gestionarVentas=?,gestionarUsuarios=?,"
                + " gestionarProveedores=?, gestionarClientes=?,gestionarInventario=?,generarReportes=?, estado=?, ultimoIngreso=?  where IdUsuario=?";
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
       String sql = "SELECT MAX(idUsuario) from usuarios;";
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

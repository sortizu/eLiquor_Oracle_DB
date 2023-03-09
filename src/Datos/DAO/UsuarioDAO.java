package Datos.DAO;

import Datos.Entidades.Usuario;
import Presentacion.Interfaces.FramePrincipal;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author sortizu
 */
public class UsuarioDAO implements CRUD{
    Conexion cn=FramePrincipal.conexion;
    Connection con=cn.con;
    PreparedStatement ps;
    ResultSet rs;
    @Override
    public int add(Object[] o) {
        int r = 0;
        String sql = 
            "BEGIN "
            + "ROOT.SP_AGREGAR_USUARIO(?,?,?);"
            + "END;";
        try{
            
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
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM ROOT.VW_TOTAL_USUARIOS";
        try{
            
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt(1));
                u.setNombre(rs.getString(2));                   u.setFechaRegistro(rs.getDate(3).toLocalDate());
                u.setEstado(rs.getBoolean(4));
                u.setRol(rs.getInt(5)==101?Usuario.ROL.ADMINISTRADOR:Usuario.ROL.EMPLEADO);
                lista.add(u);
            }
        }catch(SQLException e){
             System.out.println(e.toString());
         }
        return lista;
    }

    public List listarUsuarioActivos(){
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM ROOT.VW_USUARIOS_ACTIVOS";
        try{
            
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt(1));
                u.setNombre(rs.getString(2));
                u.setEstado(rs.getBoolean(3));
                u.setFechaRegistro(rs.getDate(4).toLocalDate());
                lista.add(u);
            }
        }catch(SQLException e){
             System.out.println(e.toString());
         }
        return lista;
    }    
    
    public String obtenerNombreUsuario(int usuarioID){
        String result = "";
        String sql = "SELECT ROOT.F_OBTENER_USUARIO("+usuarioID+") FROM DUAL";
        try{
            
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            rs.next();
            result=rs.getString(1);
        }catch(SQLException e){
             System.out.println(e.toString());
         }
        return result;
    }
    
    public boolean [] cargarPermisosDeUsuario(int usuarioID){
        boolean [] permisos = new boolean[7];
        String sql = "SELECT \n" +
        "  P.ACCESO_VENTAS,\n" +
        "  P.ACCESO_USUARIOS,\n" +
        "  P.ACCESO_PROVEEDORES,\n" +
        "  P.ACCESO_CLIENTES,\n" +
        "  P.ACCESO_INVENTARIO,\n" +
        "  P.ACCESO_REPORTES,\n" +
        "  P.ACCESO_CONFIGURACION\n" +
        "  FROM ROOT.USUARIO U\n" +
        "  LEFT JOIN ROOT.PERMISOS P\n" +
        "  ON U.PERMISOS_ID = P.PERMISOS_ID\n" +
        "  WHERE U.USUARIO_ID="+usuarioID;
        try{
            cn.setStaticRootConfiguration();
            
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            rs.next();
            permisos[0]=rs.getBoolean(1);
            permisos[1]=rs.getBoolean(2);
            permisos[2]=rs.getBoolean(3);
            permisos[3]=rs.getBoolean(4);
            permisos[4]=rs.getBoolean(5);
            permisos[5]=rs.getBoolean(6);
            permisos[6]=rs.getBoolean(7);
        }catch(SQLException e){
             System.out.println(e.toString());
         }
        return permisos;
    }
    
    @Override
    public void eliminar(int id) {
        String sql = "delete from usuarios where idUsuario=?";
        try{
           
           ps = con.prepareStatement(sql);
           ps.setInt(1,id);
           ps.executeUpdate();
       }catch(SQLException e){
            System.out.println(e.toString());
        }
    }
    
    public void eliminacionLogica(int id){
        String sql = "BEGIN "
                    + "ROOT.SP_ELIMINAR_USUARIO(?);"
                    + "END;";
        try{
           
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
                    + "ROOT.SP_MODIFICAR_USUARIO(?,?,?,?,?);"
                    + "END;";
        try{
           
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
    
}

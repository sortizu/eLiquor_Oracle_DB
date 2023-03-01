package Datos.DAO;

import Datos.Entidades.Configuracion;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author 
 */
public class ConfiguracionDAO implements CRUD{
    Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    @Override
    public int add(Object[] o) {
        int r = 0;
        int id=setLastId()+1;
        String sql = 
            "insert into sistema(razonSocial, numeroTerminal, RUC, telefono, codigoTienda, ciudad, provincia, distrito, direccion, codigoPostal, idSistema)values(?,?,?,?,?,?,?,?,?,?,?)";
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
    

    @Override
    public List listar() {
        List<Configuracion> lista = new ArrayList<>();
        String sql = "select * from sistema";
        try{
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Configuracion c = new Configuracion();
                c.setIdSistema(rs.getInt(1));
                c.setRazonSocial(rs.getString(2));
                c.setNumeroTerminal(rs.getInt(3));
                c.setRUC(rs.getString(4));
                c.setTelefono(rs.getInt(5));
                c.setCodigoTienda(rs.getString(6));
                c.setCiudad(rs.getString(7));
                c.setProvincia(rs.getString(8));
                c.setDistrito(rs.getString(9));
                c.setDireccion(rs.getString(10));
                c.setCodigoPostal(rs.getInt(11));
                lista.add(c);
            }
        }catch(SQLException e){
             System.out.println(e.toString());
         }
        return lista;
    }

    @Override
    public void eliminar(int id) {
        String sql = "delete from sistema where idSistema=?";
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
        String sql = "update sistema set razonSocial=?,numeroTerminal=?,RUC=?,telefono=?,codigoTienda=?,ciudad=?,provincia=?,distrito=?,direccion=?,codigoPostal=? where IdSistema=?";
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
           ps.setObject(10,o[9]);
           ps.setObject(11, o[10]);
           r = ps.executeUpdate();
       }catch(SQLException e){
            System.out.println(e.toString());
        }
       return r;
    }
    
    public int setLastId(){
        int id=1;
       String sql = "SELECT MAX(idSistema) from sistema;";
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

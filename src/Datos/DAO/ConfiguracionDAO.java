package Datos.DAO;

import Datos.Entidades.Configuracion;
import Presentacion.Interfaces.FramePrincipal;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author 
 */
public class ConfiguracionDAO implements CRUD{
    Conexion cn=FramePrincipal.conexion;
    Connection con=cn.con;
    PreparedStatement ps;
    ResultSet rs;
    @Override
    public int add(Object[] o) {
        return 0;
    }
    

    @Override
    public List listar() {
        return null;
    }

    public Configuracion cargarConfiguracionSistema(){
        Configuracion c = null;
        String sql = "select * from VW_CONFIGURACION_SISTEMA";
        try{
            
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            rs.next();
            c = new Configuracion(0,
            rs.getString(3),
            rs.getString(1),
            rs.getInt(2),
            rs.getString(5),
            rs.getInt(4),
            rs.getString(7),
            rs.getString(8),
            rs.getString(6),
            rs.getString(9),
            rs.getInt(10));
        }catch(SQLException e){
             System.out.println(e.toString());
         }
        return c;
    }
    
    @Override
    public void eliminar(int id) {}

    @Override
    public int actualizar(Object[] o) {
        int r = 0;
        String sql = "BEGIN "
                    + "ROOT.SP_GUARDAR_CONFIGURACION(?,?,?,?,?,?,?,?,?,?);"
                    + "END;";
        try{
           
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
           r = ps.executeUpdate();
       }catch(SQLException e){
            System.out.println(e.toString());
        }
       return r;
    }
    
}

package Datos.DAO;

import Datos.Entidades.Entrega;
import Presentacion.Interfaces.FramePrincipal;
import java.util.List;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
/**
 *
 * @author sortizu
 */
public class EntregaDAO implements CRUD{
    Conexion cn=FramePrincipal.conexion;
    Connection con=cn.con;
    PreparedStatement ps;
    ResultSet rs;
    @Override
    public int add(Object[] o) {
        int r = 0;
        int id=setLastId()+1;
        String sql = 
            "insert into ProveedorProducto(costo,cantidad,fechaEntrega,idProducto,idProveedor,idProveedorProducto)values(?,?,?,?,?,?)";
        try{
            
            ps = con.prepareStatement(sql);
            ps.setObject(1, o[0]);
            ps.setObject(2, o[1]);
            ps.setObject(3, o[2]);
            ps.setObject(4, o[3]);
            ps.setObject(5, o[4]);
            ps.setObject(6, id);
            r = ps.executeUpdate();
        }catch(SQLException e){
             System.out.println(e.toString());
         }
        return r;
    }
    

    @Override
    public List listar() {
        List<Entrega> lista = new ArrayList<>();
        String sql = "select * from ProveedorProducto";
        try{
            
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Entrega u = new Entrega();
                u.setIdEntrega(rs.getInt(1));
                u.setCosto(rs.getDouble(2));
                u.setCantidad(rs.getInt(3));
                u.setFechaEntrega(rs.getDate(4).toLocalDate());
                lista.add(u);
            }
        }catch(SQLException e){
             System.out.println(e.toString());
         }
        return lista;
    }
    
    public Entrega ObtenerUltimaEntregaDeProveedor(int idProveedor) {
        Entrega u = null;
        String sql =  "select * from ROOT.VW_ULTIMA_ENTREGA";
        try{
            
            ps = con.prepareStatement(
                 "BEGIN "
                + "ROOT.ROOT_PROVEEDOR.V_PROVEEDOR_ID_ULT_ENT:="+idProveedor+";"
                + "END;"
            );
            ps.executeUpdate();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            rs.next();
            u = new Entrega(0, 
                    null,
                    rs.getInt(3),
                    0,
                    null, 
                    rs.getDate(4).toLocalDate());
        }catch(SQLException e){
             System.out.println(e.toString());
         }
        return u;
    }

    @Override
    public void eliminar(int id) {
        String sql = "delete from ProveedorProducto where idProveedorProducto=?";
        try{
           
           ps = con.prepareStatement(sql);
           ps.setInt(1,id);
           ps.executeUpdate();
       }catch(SQLException e){
            System.out.println(e.toString());
        }
    }
    
    
    @Override
    public int actualizar(Object[] o) {
       return 0;
    }
    
    public int setLastId(){
        int id=1;
       String sql = "SELECT MAX(idProveedorProducto) from ProveedorProducto;";
       try{
           
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

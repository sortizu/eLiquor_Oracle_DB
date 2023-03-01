
package Datos.DAO;

import Datos.Entidades.Venta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yos
 */
public class VentaDAO implements CRUD {
    Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    @Override
    public int add(Object[] o) {
        int r = 0;
        int id=setLastId()+1;
        String sql = 
            "insert into venta(fechaRegistro, ventaBruta, totalImpuestos, totalDescuentos, totalCosto,pagoCliente,cambio,idCliente,idUsuario,idVenta) values(?,?,?,?,?,?,?,?,?,?)";
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
            ps.setObject(10, id);
            r=ps.executeUpdate();
        }catch(SQLException e){
             System.out.println(e.toString());
         }
        return r; 
    }

    @Override
    public int actualizar(Object[] o) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List listar() {
        List<Venta> lista = new ArrayList<>();
        String sql = "select * from venta";
        try{
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Venta p = new Venta();
                p.setIdVenta(rs.getInt(1));
                p.setFechaRegistro(rs.getDate(2).toLocalDate());
                p.setVentaBruta(rs.getDouble(3));
                p.setTotalImpuestos(rs.getDouble(4));
                p.setTotalDescuento(rs.getDouble(5));
                p.setTotalCosto(rs.getDouble(6));
                p.setPagoCliente(rs.getDouble(7));
                p.setCambio(rs.getDouble(8));
                p.setIdCliente(rs.getInt(9));
                p.setIdUsuario(rs.getInt(10));
                
                lista.add(p);
            }
        }catch(SQLException e){
             System.out.println(e.toString());
         }
        return lista;
    }
    
    public int setLastId(){
        int id=1;
       String sql = "SELECT MAX(idVenta) from venta;";
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

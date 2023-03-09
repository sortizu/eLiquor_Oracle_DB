
package Datos.DAO;

import Datos.Entidades.DetalleVenta;
import Datos.Entidades.Venta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.driver.OracleCallableStatement;
import oracle.jdbc.driver.OraclePreparedStatement;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;

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
        return 0;
    }
    
    public void registrarVenta(Venta venta){
        int r = 0;
        String sql = 
            "BEGIN "
            +"ROOT.SP_REGISTRO_VENTA(?,?,?,?,?,?);"
            +"END;";
        try{
            con = cn.Conectar();
            StructDescriptor itemDescriptor =
                StructDescriptor.createDescriptor("ROOT.TYPE_VENTA_PRODUCTO",con);
            //Array de objetos que describe los atributos del tipo TYPE_VENTA_PRODUCTO
            STRUCT [] VentaProductoArray = new STRUCT[venta.getDetallesVenta().size()];
            for(int i = 0;i<venta.getDetallesVenta().size();i++){
                Object[] itemAtributes = new Object[]{
                new Integer(venta.getDetallesVenta().get(i).getProducto().getIdProducto()),
                new Integer(venta.getDetallesVenta().get(i).getCantidad())
            };
                STRUCT itemObject = new STRUCT(itemDescriptor, con, itemAtributes);
                VentaProductoArray[i]=itemObject;
            }
            //Preparando estructura para enviar array a procedimniento
            ArrayDescriptor descriptor =
                    ArrayDescriptor.createDescriptor("ROOT.TYPE_TABLE_VENTA_PRODUCTO",con);
            ARRAY arrayDeVentaProducto = 
                    new ARRAY(descriptor, con, VentaProductoArray);
            OraclePreparedStatement ps = 
                    (OraclePreparedStatement)con.prepareStatement(sql);
            
            ps.setDouble(1, venta.getTotalImpuestos());
            ps.setDouble(2, venta.getTotalDescuento());
            ps.setDouble(3, venta.getPagoCliente());
            ps.setObject(4, venta.getIdCliente()<0?null:venta.getIdCliente());
            ps.setDouble(5, venta.getIdUsuario());
            ps.setARRAY(6, arrayDeVentaProducto);
            ps.execute();

        }catch(SQLException e){
             System.out.println(e.toString());
         }
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
    
}

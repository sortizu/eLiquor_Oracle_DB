
package Datos.DAO;

import Datos.Entidades.DetalleVenta;
import Datos.Entidades.Producto;
import Datos.Entidades.Venta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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
    public List cargarVentasDeReporte(LocalDate fechaInicio, LocalDate fechaFin){
        
        List<Venta> lista = new ArrayList<>();
        String sql = "select * from ROOT.VW_VENTAS_PARA_REPORTE";
        try{
            con = cn.Conectar();
            ps = con.prepareStatement(
                    "BEGIN "
                    + "ROOT.ROOT_REPORTE.V_REPORTE_FECHA_INICIO:=?;"
                    + "ROOT.ROOT_REPORTE.V_REPORTE_FECHA_FIN:=?;"
                    + "END;"
            );
            ps.setDate(1, java.sql.Date.valueOf(fechaInicio));
            ps.setDate(2, java.sql.Date.valueOf(fechaFin));
            ps.executeUpdate();
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
            
            for(Venta v:lista){
                ps = con.prepareStatement(
                    "BEGIN "
                    + "ROOT.ROOT_REPORTE.V_VENTA_ID_REPORTE:=?;"
                    + "END;"
                );
                ps.setObject(1, v.getIdVenta());
                ps.executeUpdate();
                ps = con.prepareStatement(""
                        + "SELECT * FROM ROOT.VW_BUSQUEDA_VENTA_PRODUCTO");
                rs = ps.executeQuery();
                while(rs.next()){
                DetalleVenta dv = new DetalleVenta();
                Producto p = new Producto();
                p.setIdProducto(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setPrecio(rs.getDouble(3));
                p.setCosto(rs.getDouble(4));
                p.setStock(rs.getInt(5));
                p.setPrecioVariable(rs.getBoolean(6));
                p.setActivarDescuentos(rs.getBoolean(7));
                p.setMostrarEnCaja(rs.getBoolean(8));
                p.setFechaRegistro(rs.getDate(9).toLocalDate());
                dv.setCantidad(rs.getInt(10));
                dv.setProducto(p);
                dv.setPrecio(p.getPrecio());
                v.getDetallesVenta().add(dv);
                }
            }
        }catch(SQLException e){
             System.out.println(e.toString());
         }
        return lista;
    }
    
    public int obtenerNumeroDeVentas(LocalDate fechaInicio, LocalDate fechaFin){
        int numeroVentas=0;
        String sql = "SELECT ROOT.ROOT_REPORTE.F_NUMERO_DE_VENTAS(?,?) FROM DUAL";
        try{
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setDate(1, java.sql.Date.valueOf(fechaInicio));
            ps.setDate(2, java.sql.Date.valueOf(fechaFin));
            rs = ps.executeQuery();
            rs.next();
            numeroVentas = rs.getInt(1);
        }catch(SQLException e){
             System.out.println(e.toString());
         }
        return numeroVentas;
    }
    
    public double obtenerValorMercanciaEnTienda(){
        double valorMercancia=0;
        String sql = "SELECT ROOT.ROOT_REPORTE.F_VALOR_MERCANCIA FROM DUAL";
        try{
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            rs.next();
            valorMercancia = rs.getDouble(1);
        }catch(SQLException e){
             System.out.println(e.toString());
         }
        return valorMercancia;
    }
    
    public int obtenerPromedioDeVentasPorDia(LocalDate fechaInicio, LocalDate fechaFin){
        int promedioDeVentas=0;
        String sql = "SELECT ROOT.ROOT_REPORTE.F_PROMEDIO_VENTAS_X_DIA(?,?) FROM DUAL";
        try{
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setDate(1, java.sql.Date.valueOf(fechaInicio));
            ps.setDate(2, java.sql.Date.valueOf(fechaFin));
            rs = ps.executeQuery();
            rs.next();
            promedioDeVentas = rs.getInt(1);
        }catch(SQLException e){
             System.out.println(e.toString());
         }
        return promedioDeVentas;
    }
    
    public double obtenerValorDeOrdenPromedio(LocalDate fechaInicio, LocalDate fechaFin){
        double valorDeOrdenPromedio=0;
        String sql = "SELECT ROOT.ROOT_REPORTE.F_VALOR_ORDEN_PROMEDIO(?,?) FROM DUAL";
        try{
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setDate(1, java.sql.Date.valueOf(fechaInicio));
            ps.setDate(2, java.sql.Date.valueOf(fechaFin));
            rs = ps.executeQuery();
            rs.next();
            valorDeOrdenPromedio = rs.getDouble(1);
        }catch(SQLException e){
             System.out.println(e.toString());
         }
        return valorDeOrdenPromedio;
    }
    
    public int obtenerNumeroDeProductosVendidos(LocalDate fechaInicio, LocalDate fechaFin){
        int numeroDeProductosVendidos=0;
        String sql = "SELECT ROOT.ROOT_REPORTE.F_PRODUCTOS_VENDIDOS(?,?) FROM DUAL";
        try{
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setDate(1, java.sql.Date.valueOf(fechaInicio));
            ps.setDate(2, java.sql.Date.valueOf(fechaFin));
            rs = ps.executeQuery();
            rs.next();
            numeroDeProductosVendidos = rs.getInt(1);
        }catch(SQLException e){
             System.out.println(e.toString());
         }
        return numeroDeProductosVendidos;
    }
    
    public int obtenerTotalClientesRegistrados(LocalDate fechaInicio, LocalDate fechaFin){
        int numeroDeClientesRegistrados=0;
        String sql = "SELECT ROOT.ROOT_REPORTE.F_CLIENTES_REGISTRADOS(?,?) FROM DUAL";
        try{
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setDate(1, java.sql.Date.valueOf(fechaInicio));
            ps.setDate(2, java.sql.Date.valueOf(fechaFin));
            rs = ps.executeQuery();
            rs.next();
            numeroDeClientesRegistrados = rs.getInt(1);
        }catch(SQLException e){
             System.out.println(e.toString());
         }
        return numeroDeClientesRegistrados;
    }
}

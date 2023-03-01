package Negocio;

import Datos.DAO.ProductoDAO;
import Datos.DAO.VentaDAO;
import Datos.DAO.VentaProductoDAO;
import Datos.Entidades.DetalleVenta;
import Datos.Entidades.Producto;
import Datos.Entidades.Venta;
import java.util.ArrayList;

/**
 *
 * @author sortizu
 */
public class ControlVentas {
    public static void registrarVenta(Venta venta){
        
        VentaDAO ventaE = new VentaDAO ();
        int idVenta = ventaE.setLastId()+1;
        //fechaRegistro, ventaBruta, totalImpuestos, totalDescuentos, 
        //totalCosto,pagoCliente,cambio,idCliente,idUsuario,idVenta
        Object[]campos;
        int idCliente=venta.getIdCliente();
        if(idCliente<0){
            campos = new Object[]{venta.getFechaRegistro(),venta.getVentaBruta()
        ,venta.getTotalImpuestos(),venta.getTotalDescuento(), venta.getTotalCosto()
        ,venta.getPagoCliente(),venta.getCambio(), null
        ,venta.getIdUsuario(), venta.getIdVenta()};
        ventaE.add(campos);
        }
        else{
            campos = new Object[]{venta.getFechaRegistro(),venta.getVentaBruta()
        ,venta.getTotalImpuestos(),venta.getTotalDescuento(), venta.getTotalCosto()
        ,venta.getPagoCliente(),venta.getCambio(), venta.getIdCliente()
        ,venta.getIdUsuario(), venta.getIdVenta()};
        ventaE.add(campos);
        }
        
        ProductoDAO pDao = new ProductoDAO ();
        
        
        ArrayList<DetalleVenta> productos= venta.getDetallesVenta();
        VentaProductoDAO ventaR = new VentaProductoDAO();
        for(DetalleVenta dv:productos){
            Object[]relacionar= new Object[]{idVenta,dv.getProducto().getIdProducto(),dv.getCantidad(),0};
            ventaR.add(relacionar);
            Producto productoModificado =dv.getProducto();
            productoModificado.setStock(productoModificado.getStock()-dv.getCantidad());
            
            Object[] datosProducto={productoModificado.getNombre(),productoModificado.getPrecio(),
            productoModificado.getCosto(), productoModificado.getStock(),productoModificado.isPrecioVariable(),
            productoModificado.isActivarDescuentos(), productoModificado.isMostrarEnCaja(),
            productoModificado.getFechaRegistro(), productoModificado.isIGV(), productoModificado.isISC(),
            productoModificado.getIdProducto()};
            
            pDao.actualizar(datosProducto);
        }
        
        
        
        
        
        /*
        Este metodo debe usar el parametro "venta" para 
        registrar una nueva venta en la BD. Para ello
        es necesario crear un DAO venta y que la tabla 
        "Venta" tambien exista en la BD (revisar documento
        de especificacion de la BD, punto 5: modelo 
        relacional) .Ademas, el DAO deben encargarse de 
        asignar un id diferente a cada venta (El dao 
        de usuario funciona de la misma manera, tomarlo 
        de ejemplo si es necesario.)
        Por otro lado, ademas de la venta tambien tiene que
        registrarse la relacion entre la venta y los 
        productos que contiene. Los productos se pueden 
        obtener recorriendo el ArrayList de "DetalleVenta"
        que tiene la venta, y por cada detalleVenta obtener
        los productos. La relacion entre una venta y un 
        producto se registra en la tabla Venta-Producto
        (ver modelo relacional), que obviamente tambien
        debe ser creado. Si es necesario separar ese registro
        como otra funcion dentro de esta clase, y crearle su
        propia clase a Venta-Producto y su DAO tambien si es
        necesario.
        OJO: Antes de intentar registrar las relaciones,
        verificar si "cliente" o "usuario" son null, si lo 
        son, no registrar esos datos junto a la venta en la
        BD.
        */
    }
}

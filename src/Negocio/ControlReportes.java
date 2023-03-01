/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import Datos.DAO.ClienteDAO;
import Datos.DAO.ProductoDAO;
import Datos.DAO.UsuarioDAO;
import Datos.DAO.VentaDAO;
import Datos.DAO.VentaProductoDAO;
import Datos.Entidades.Cliente;
import Datos.Entidades.DetalleVenta;
import Datos.Entidades.Producto;
import Datos.Entidades.Usuario;
import Datos.Entidades.Venta;
import Datos.Entidades.VentaProducto;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author sortizu
 */
public class ControlReportes {
    public static ArrayList<Venta> cargarVentas(LocalDate fechaInicio, LocalDate fechaFin){
        VentaDAO vdao = new VentaDAO();
        ArrayList<Venta> ventasCargadas = (ArrayList<Venta>)vdao.listar();
        ArrayList<Venta> ventasCargadasEnFechas = new ArrayList<Venta>();
        for (Venta v:ventasCargadas) {
            if(v.getFechaRegistro().compareTo(fechaInicio)>=0
                    &&v.getFechaRegistro().compareTo(fechaFin)<=0){
                v.setDetallesVenta(obtenerDetalleVenta(v.getIdVenta()));
                ventasCargadasEnFechas.add(v);
            }
        }
        return ventasCargadasEnFechas;
    }
    
    public static double obtenerValorMercanciaEnTienda(){
        double valor=0;
        ProductoDAO pdao=new ProductoDAO();
        ArrayList<Producto> productos = (ArrayList<Producto>)pdao.listar();
        for(Producto p:productos){
            valor+=p.getCosto()*p.getStock();
        }
        return valor;
    }
    
    public static int obtenerTotalClientesRegistrados(){
        ClienteDAO cdao = new ClienteDAO();
        ArrayList<Cliente> clientesRegistrados = (ArrayList<Cliente>)cdao.listar();
        return clientesRegistrados.size();
    }
    
    public static Cliente cargarCliente(int idCliente){
        ClienteDAO cdao = new ClienteDAO();
        ArrayList<Cliente> clientesRegistrados = (ArrayList<Cliente>)cdao.listar();
        for(Cliente c:clientesRegistrados){
            if(c.getIdCliente()==idCliente){
                return c;
            }
        }
        return null;
    }
    
    public static Usuario cargarUsuario(int idUsuario){
        UsuarioDAO udao = new UsuarioDAO();
        ArrayList<Usuario> usuarioRegistrados = (ArrayList<Usuario>)udao.listar();
        for(Usuario u:usuarioRegistrados){
            if(u.getIdUsuario()==idUsuario){
                return u;
            }
        }
        return null;
    }
    
    /*
    Este metodo carga detalles basicos del supuesto detalle Venta 
    de la venta registrada. Sin embargo, no es capaz de recuperar
    todos los datos que el usuario pudo haber editado sobre un 
    producto de la lista de items.
    */
    public static ArrayList<DetalleVenta> obtenerDetalleVenta(int idVenta){
        VentaProductoDAO vpdao=new VentaProductoDAO();
        ProductoDAO pdao = new ProductoDAO();
        ArrayList<VentaProducto> relVentaProd = (ArrayList<VentaProducto>)vpdao.listar();
        ArrayList<DetalleVenta> detalleVenta = new ArrayList<DetalleVenta>();
        
        for(VentaProducto vp:relVentaProd){
            if(vp.getIdVenta()==idVenta){
                Producto p = pdao.obtenerProductoPorSuID(vp.getIdProducto());
                DetalleVenta ndv = new DetalleVenta();
                ndv.setPrecio(p.getPrecio());
                ndv.setCantidad(vp.getCantidadProducto());
                ndv.setProducto(p);
                detalleVenta.add(ndv);
            }
        }
        
        return detalleVenta;
    }
}

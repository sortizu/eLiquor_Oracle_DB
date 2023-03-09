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
        ArrayList<Venta> ventasCargadas = (ArrayList<Venta>)vdao.cargarVentasDeReporte(fechaInicio,fechaFin);
        return ventasCargadas;
    }
    
    public static int obtenerNumeroDeVentas(LocalDate fechaInicio, LocalDate fechaFin){
        VentaDAO vdao = new VentaDAO();
        return vdao.obtenerNumeroDeVentas(fechaInicio, fechaFin);
    }
    
    public static double obtenerValorMercanciaEnTienda(){
        VentaDAO vdao = new VentaDAO();
        return vdao.obtenerValorMercanciaEnTienda();
    }
    
    public static int obtenerPromedioDeVentasPorDia(LocalDate fechaInicio, LocalDate fechaFin){
        VentaDAO vdao = new VentaDAO();
        return vdao.obtenerPromedioDeVentasPorDia(fechaInicio, fechaFin);
    }
    
    public static double obtenerValorDeOrdenPromedio(LocalDate fechaInicio, LocalDate fechaFin){
        VentaDAO vdao = new VentaDAO();
        return vdao.obtenerValorDeOrdenPromedio(fechaInicio, fechaFin);
    }
    
    public static int obtenerNumeroDeProductosVendidos(LocalDate fechaInicio, LocalDate fechaFin){
        VentaDAO vdao = new VentaDAO();
        return vdao.obtenerNumeroDeProductosVendidos(fechaInicio, fechaFin);
    }
    
    public static int obtenerTotalClientesRegistrados(LocalDate fechaInicio, LocalDate fechaFin){
        VentaDAO vdao = new VentaDAO();
        return vdao.obtenerTotalClientesRegistrados(fechaInicio, fechaFin);
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
}

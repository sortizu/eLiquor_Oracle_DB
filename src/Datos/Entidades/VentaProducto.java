/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos.Entidades;

/**
 *
 * @author User
 */
public class VentaProducto {
    private int idVentaProducto;
    private int idVenta;
    private int idProducto;
    private int cantidadProducto;

    public VentaProducto() {
    }

    public VentaProducto(int idVentaProducto, int idVenta, int idProducto, int cantidadProducto) {
        this.idVentaProducto = idVentaProducto;
        this.idVenta = idVenta;
        this.idProducto = idProducto;
        this.cantidadProducto = cantidadProducto;
    }

    

    public int getIdVentaProducto() {
        return idVentaProducto;
    }

    public void setIdVentaProducto(int idVentaProducto) {
        this.idVentaProducto = idVentaProducto;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }
    
    
    
    
}

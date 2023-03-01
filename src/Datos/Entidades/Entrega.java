
package Datos.Entidades;

import java.time.LocalDate;

/**
 *
 * @author sortizu
 */
public class Entrega {
    private int idEntrega;
    private Producto item;
    private int cantidad;
    private double costo;
    private Proveedor proveedor;
    private LocalDate fechaEntrega;

    public Entrega() {
    }

    public Entrega(int idEntrega, Producto item, int cantidad, double costo, Proveedor proveedor, LocalDate fechaEntrega) {
        this.idEntrega = idEntrega;
        this.item = item;
        this.cantidad = cantidad;
        this.costo = costo;
        this.proveedor = proveedor;
        this.fechaEntrega = fechaEntrega;
    }
    
    public int getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(int idEntrega) {
        this.idEntrega = idEntrega;
    }

    public Producto getItem() {
        return item;
    }

    public void setItem(Producto item) {
        this.item = item;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }
    
    
}

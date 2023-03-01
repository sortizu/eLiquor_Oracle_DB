package Datos.Entidades;

import java.time.LocalDate;

/**
 *
 * @author sortizu
 */
public class Producto {
    private int idProducto;
    private String nombre;
    private double precio;
    private double costo;
    private int stock;
    private boolean precioVariable;
    private boolean mostrarEnCaja;
    private boolean activarDescuentos;
    private LocalDate fechaRegistro;
    private boolean IGV;
    private boolean ISC;

    public Producto() {
    }

    public Producto(int idProducto, String nombre, double precio, double costo, int stock, boolean precioVariable, boolean mostrarEnCaja, boolean activarDescuentos, LocalDate fechaRegistra) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.costo = costo;
        this.stock = stock;
        this.precioVariable = precioVariable;
        this.mostrarEnCaja = mostrarEnCaja;
        this.activarDescuentos = activarDescuentos;
        this.fechaRegistro = fechaRegistra;
    }
    
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isPrecioVariable() {
        return precioVariable;
    }

    public void setPrecioVariable(boolean precioVariable) {
        this.precioVariable = precioVariable;
    }

    public boolean isMostrarEnCaja() {
        return mostrarEnCaja;
    }

    public void setMostrarEnCaja(boolean mostrarEnCaja) {
        this.mostrarEnCaja = mostrarEnCaja;
    }

    public boolean isActivarDescuentos() {
        return activarDescuentos;
    }

    public void setActivarDescuentos(boolean activarDescuentos) {
        this.activarDescuentos = activarDescuentos;
    }

    public boolean isIGV() {
        return IGV;
    }

    public void setIGV(boolean IGV) {
        this.IGV = IGV;
    }

    public boolean isISC() {
        return ISC;
    }

    public void setISC(boolean ISC) {
        this.ISC = ISC;
    }
    
    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}

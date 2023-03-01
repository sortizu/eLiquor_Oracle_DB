
package Datos.Entidades;

import java.time.LocalDate;

/**
 *
 * @author sortizu
 */
public class Proveedor {
    private int idProveedor;
    private String razonSocial;
    private String correo;
    private int telefono;
    private LocalDate fechaRegistro;

    public Proveedor() {
    }

    public Proveedor(int idProveedor, String razonSocial, String correo, int telefono, LocalDate fechaRegistro) {
        this.idProveedor = idProveedor;
        this.razonSocial = razonSocial;
        this.correo = correo;
        this.telefono = telefono;
        this.fechaRegistro = fechaRegistro;
    }
    
    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    
}

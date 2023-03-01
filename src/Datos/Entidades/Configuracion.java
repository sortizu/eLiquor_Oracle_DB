/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos.Entidades;

/**
 *
 * @author sortizu
 */
public class Configuracion {
    private int idSistema;
    private String razonSocial;
    private String RUC;
    private int numeroTerminal;
    private String codigoTienda;
    private int telefono;
    private String provincia;
    private String distrito;
    private String ciudad;
    private String direccion;
    private int codigoPostal;
    
    public Configuracion() {
    }

    public Configuracion(int idSistema, String razonSocial, String RUC, int numeroTerminal, String codigoTienda, int telefono, String provincia, String distrito, String ciudad, String direccion, int codigoPostal) {
        this.idSistema = idSistema;
        this.razonSocial = razonSocial;
        this.RUC = RUC;
        this.numeroTerminal = numeroTerminal;
        this.codigoTienda = codigoTienda;
        this.telefono = telefono;
        this.provincia = provincia;
        this.distrito = distrito;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRUC() {
        return RUC;
    }

    public void setRUC(String RUC) {
        this.RUC = RUC;
    }

    public int getNumeroTerminal() {
        return numeroTerminal;
    }

    public void setNumeroTerminal(int numeroTerminal) {
        this.numeroTerminal = numeroTerminal;
    }

    public String getCodigoTienda() {
        return codigoTienda;
    }

    public void setCodigoTienda(String codigoTienda) {
        this.codigoTienda = codigoTienda;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getIdSistema() {
        return idSistema;
    }

    public void setIdSistema(int idSistema) {
        this.idSistema = idSistema;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
}

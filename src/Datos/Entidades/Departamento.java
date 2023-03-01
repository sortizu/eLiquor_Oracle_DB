/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos.Entidades;

import java.time.LocalDate;

/**
 *
 * @author sortizu
 */
public class Departamento {
    private int idDepartamento;
    private String nombre;
    private int cantidad;
    private LocalDate fechaRegistro;

    public Departamento() {
    }

    public Departamento(int idDepartamento, String nombre /* , int cantidad*/ , LocalDate fechaRegistro) {
        this.idDepartamento = idDepartamento;
        this.nombre = nombre;
       // this.cantidad = cantidad;
        this.fechaRegistro = fechaRegistro;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
  
    
}

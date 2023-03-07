/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import Datos.DAO.ConfiguracionDAO;
import Datos.Entidades.Configuracion;
import java.util.ArrayList;

/**
 *
 * @author sortizu
 */
public class ControlConfiguracion {
    public static void aplicarCambios(Configuracion nuevaConfiguracion){
        ConfiguracionDAO cfdao = new ConfiguracionDAO();
        Object[] datos = {
            nuevaConfiguracion.getRUC(),
            nuevaConfiguracion.getNumeroTerminal(),
            nuevaConfiguracion.getRazonSocial(),
            nuevaConfiguracion.getTelefono(),
            nuevaConfiguracion.getCodigoTienda(),
            nuevaConfiguracion.getCiudad(),
            nuevaConfiguracion.getProvincia(),
            nuevaConfiguracion.getDistrito(),
            nuevaConfiguracion.getDireccion(),
            nuevaConfiguracion.getCodigoPostal()
        };
        cfdao.actualizar(datos);
    }
    
    public static Configuracion cargarConfiguracion(){
        ConfiguracionDAO cfdao = new ConfiguracionDAO();
        return cfdao.cargarConfiguracionSistema();
    }
    
}

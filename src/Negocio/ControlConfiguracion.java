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
            nuevaConfiguracion.getRazonSocial(),
            nuevaConfiguracion.getNumeroTerminal(),
            nuevaConfiguracion.getRUC(),
            nuevaConfiguracion.getTelefono(),
            nuevaConfiguracion.getCodigoTienda(),
            nuevaConfiguracion.getCiudad(),
            nuevaConfiguracion.getProvincia(),
            nuevaConfiguracion.getDistrito(),
            nuevaConfiguracion.getDireccion(),
            nuevaConfiguracion.getCodigoPostal(),1
        };
        cfdao.actualizar(datos);
    }
    
    public static Configuracion cargarConfiguracion(){
        int idACargar=1;
        ConfiguracionDAO cfdao = new ConfiguracionDAO();
        for(Configuracion c : (ArrayList<Configuracion>)cfdao.listar()){
            if(c.getIdSistema()==idACargar){
                return c;
            }
        }
        return null;
    }
    
    public static void agregarConfiguracion(Configuracion nuevaConfiguracion){
        ConfiguracionDAO cfdao = new ConfiguracionDAO();
        Object [] datos ={
            nuevaConfiguracion.getRazonSocial(),
            nuevaConfiguracion.getNumeroTerminal(),
            nuevaConfiguracion.getRUC(),
            nuevaConfiguracion.getTelefono(),
            nuevaConfiguracion.getCodigoTienda(),
            nuevaConfiguracion.getCiudad(),
            nuevaConfiguracion.getProvincia(),
            nuevaConfiguracion.getDistrito(),
            nuevaConfiguracion.getDireccion(),
            nuevaConfiguracion.getCodigoPostal(),
            nuevaConfiguracion.getIdSistema()
        };
        cfdao.add(datos);
    }
}

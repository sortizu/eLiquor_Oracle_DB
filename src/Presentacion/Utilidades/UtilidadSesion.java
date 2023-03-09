package Presentacion.Utilidades;

import Datos.Entidades.Configuracion;
import Negocio.ControlConfiguracion;

/**
 *
 * @author sortizu
 */
public class UtilidadSesion {
    public static int idUsuarioActual;
    public static String nombreUsuarioActual;
    public static Configuracion configuracionActual;
    
    static {
        Configuracion tempConf=ControlConfiguracion.cargarConfiguracion();
        if(tempConf==null){
            configuracionActual=new Configuracion();
            configuracionActual.setRazonSocial(" ");
            configuracionActual.setRUC("");
            configuracionActual.setNumeroTerminal(1);
            configuracionActual.setCodigoTienda("00000");
            configuracionActual.setTelefono(0);
            configuracionActual.setProvincia("");
            configuracionActual.setDistrito("");
            configuracionActual.setCiudad("");
            configuracionActual.setDireccion("");
            ControlConfiguracion.aplicarCambios(configuracionActual);
        }else{
            configuracionActual=tempConf;
        }
    }
    
}

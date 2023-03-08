/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import Datos.DAO.UsuarioDAO;
import Datos.Entidades.Usuario;
import java.util.ArrayList;

/**
 *
 * @author sortizu
 */
public class ControlUsuarios {
    public static ArrayList<Usuario> cargarListaDeUsuarios(){
        UsuarioDAO udao=new UsuarioDAO();
        return (ArrayList<Usuario>)udao.listar();
    }
    
    public static void agregarUsuario(Usuario usuario){
        UsuarioDAO udao=new UsuarioDAO();
        udao.add(new Object[]{
            usuario.getNombre(),
            usuario.getPIN(),
            usuario.isGestionarVentas(),
            usuario.isGestionarUsuarios(),
            usuario.isGestionarProveedores(),
            usuario.isGestionarClientes(),
            usuario.isGestionarInventario(),
            usuario.isGenerarReportes(),
            usuario.isEstado(),
            usuario.getFechaRegistro()
        });
    }
    
    public static void eliminarUsuarios(ArrayList<Usuario> usuarios){
        UsuarioDAO udao=new UsuarioDAO();
        for (Usuario usuario: usuarios) {
            udao.eliminacionLogica(usuario.getIdUsuario());
        }
    }
    
    public static void modificarUsuario(int indiceLista, Usuario usuario, String pinNuevo){
        UsuarioDAO udao=new UsuarioDAO();
        Object[] datos=
        {usuario.getNombre(),
            pinNuevo,
            usuario.isGestionarVentas(),
            usuario.isGestionarUsuarios(),
            usuario.isGestionarProveedores(),
            usuario.isGestionarClientes(),
            usuario.isGestionarInventario(),
            usuario.isGenerarReportes(),
            usuario.isEstado(),
            usuario.getIdUsuario()    
        };
        if(pinNuevo.contains("*")){
            String pinViejo = Integer.toString(((Usuario)udao.listar().get(indiceLista)).getPIN());
            String pin="";
            for(int i=0;i<pinViejo.length();i++){
                if(pinNuevo.charAt(i)=='*'){
                    pin+=pinViejo.charAt(i);
                }else{
                    pin+=pinNuevo.charAt(i);
                }
            }
            datos[1]=Integer.parseInt(pin);
        }
        udao.actualizar(datos);
    }
    
    public static int obtenerUltimoID(){
        UsuarioDAO udao=new UsuarioDAO();
        return udao.setLastId();
    }
}

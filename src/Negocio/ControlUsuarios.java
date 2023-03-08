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
            usuario.getRol()==Usuario.ROL.ADMINISTRADOR?1:0
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
        {
            usuario.getIdUsuario(),
            usuario.getNombre(),
            pinNuevo,
            usuario.isEstado()?1:0,
            usuario.getRol()==Usuario.ROL.ADMINISTRADOR?1:0
        };
        udao.actualizar(datos);
    }
    
}

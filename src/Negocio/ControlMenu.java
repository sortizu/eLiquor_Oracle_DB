/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import Datos.DAO.UsuarioDAO;
import Datos.Entidades.Usuario;
import java.util.List;

/**
 *
 * @author sortizu
 */
public class ControlMenu {
    public static boolean [] cargarPermisosDeUsuario(int indice){
        UsuarioDAO udao = new UsuarioDAO();
        List<Usuario> usuarios = udao.listarUsuarioActivos();
        boolean [] permisos = {usuarios.get(indice).isGestionarVentas(),
            usuarios.get(indice).isGestionarUsuarios(), usuarios.get(indice).isGestionarProveedores(),
            usuarios.get(indice).isGestionarClientes(), usuarios.get(indice).isGestionarInventario(),
            usuarios.get(indice).isGenerarReportes()};
        return permisos;
    }
}

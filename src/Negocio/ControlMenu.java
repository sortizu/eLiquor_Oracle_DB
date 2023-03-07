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
    public static boolean [] cargarPermisosDeUsuario(int usuarioID){
        UsuarioDAO udao = new UsuarioDAO();
        return udao.cargarPermisosDeUsuario(usuarioID);
    }
}

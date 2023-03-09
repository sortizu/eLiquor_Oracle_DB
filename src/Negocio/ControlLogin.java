package Negocio;

import Datos.DAO.Conexion;
import Datos.DAO.UsuarioDAO;
import Datos.Entidades.Usuario;
import Presentacion.Interfaces.FramePrincipal;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author sortizu
 */
public class ControlLogin {
    public static Object[][] mostrarListaDeUsuarios(){
        UsuarioDAO udao = new UsuarioDAO();
        List<Usuario> usuarios = udao.listarUsuarioActivos();
        Object[][] datosUsuario= new Object[usuarios.size()][2];
        for (int i = 0; i < usuarios.size(); i++) {
            datosUsuario[i][0]=((Usuario)(usuarios.get(i))).getIdUsuario();
            datosUsuario[i][1]=((Usuario)(usuarios.get(i))).getNombre();
        }
        return datosUsuario;
    }
    /*
    Oportunidad: Se podria crear metodos en las entidades DAO para consultar
    solo un usuario a la vez, sin tener que recorrer a todos los usuarios tal
    como se hacer ahora.
    */
    public static boolean verificarPassword(int usuarioID, String ps){
        UsuarioDAO udao = new UsuarioDAO();
        String nombreUsuario = udao.obtenerNombreUsuario(usuarioID);
        Conexion cn=FramePrincipal.conexion;
        try {
            cn.setStaticUserConfiguration(nombreUsuario, ps);
            cn.Conectar();
        } catch (Exception e) {
            cn.setStaticRootConfiguration();
            return false;
        }
        return true;
    }
}

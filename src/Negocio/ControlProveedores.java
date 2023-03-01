package Negocio;

import Datos.DAO.EntregaDAO;
import Datos.DAO.ProveedorDAO;
import Datos.Entidades.Entrega;
import Datos.Entidades.Proveedor;
import Datos.Entidades.Usuario;
import java.util.ArrayList;

/**
 *
 * @author 
 */
public class ControlProveedores {
    public static ArrayList<Proveedor> cargarListaDeProveedores(){
        ProveedorDAO pdao=new ProveedorDAO();
        ArrayList<Proveedor> proveedores=new ArrayList<Proveedor>();
        for(Proveedor p: (ArrayList<Proveedor>)pdao.listar()){
            Proveedor nuevoProveedor=new Proveedor(p.getIdProveedor(),p.getRazonSocial(),p.getCorreo(),p.getTelefono(),p.getFechaRegistro());
            proveedores.add(nuevoProveedor);
        }
        return proveedores;
    }
    
    public static Entrega cargarUltimaEntregaProveedor(int idProveedor){
        EntregaDAO edao = new EntregaDAO();
        ArrayList<Entrega> entregas = (ArrayList<Entrega>)edao.listarXProveedor(idProveedor);
        Entrega ultimaEntrega = null;
        if(entregas.size()>0){
            ultimaEntrega=entregas.get(0);
            for(int i = 1; i<entregas.size();i++){
                if(ultimaEntrega.getFechaEntrega().compareTo(entregas.get(i).getFechaEntrega())<0){
                    ultimaEntrega=entregas.get(i);
                }
            }
        }
        return ultimaEntrega;
    }
    
    public static void agregarProveedor(Proveedor proveedor){
        ProveedorDAO pdao=new ProveedorDAO();
        pdao.add(new Object[]{proveedor.getRazonSocial(),proveedor.getCorreo(),proveedor.getTelefono(),proveedor.getFechaRegistro(),proveedor.getIdProveedor()});
    }
    
    public static void eliminarProveedores(ArrayList<Proveedor> proveedores){
        ProveedorDAO pdao=new ProveedorDAO();
        for (Proveedor proveedor: proveedores) {
            pdao.eliminacionLogica(proveedor.getIdProveedor());
        }
    }
    
    public static void modificarProveedor(Proveedor proveedor){
        ProveedorDAO pdao=new ProveedorDAO();
        Object[] datos={proveedor.getRazonSocial(),proveedor.getCorreo(),proveedor.getTelefono(),proveedor.getFechaRegistro(),proveedor.getIdProveedor()};
        pdao.actualizar(datos);
    }
    
    public static int obtenerUltimoID(){
        ProveedorDAO pdao=new ProveedorDAO();
        return pdao.setLastId();
    }
}

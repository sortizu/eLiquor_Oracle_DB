package Negocio;

import Datos.DAO.DepartamentoDAO;
import Datos.DAO.DepartamentoProductoDAO;
import Datos.DAO.EntregaDAO;
import Datos.DAO.ProductoDAO;
import Datos.Entidades.Departamento;
import Datos.Entidades.DepartamentoProducto;
import Datos.Entidades.Entrega;
import Datos.Entidades.Producto;
import java.util.ArrayList;

/**
 *
 * @author sortizu
 * @author Richard
 */
public class ControlInventario {
    
    public static ArrayList<Departamento> cargarDepartamentos(){
        
        DepartamentoDAO ddao=new DepartamentoDAO();
        ArrayList<Departamento> departamento=new ArrayList<Departamento>();
        
        for(Object d: ddao.listar()){
            Departamento nuevoDepartamento;
            nuevoDepartamento = new Departamento(((Departamento)d).getIdDepartamento(),((Departamento)d).getNombre(), ((Departamento)d).getFechaRegistro());
            
            
                    
            nuevoDepartamento.setCantidad(((Departamento)d).getCantidad());
            departamento.add(nuevoDepartamento);
        }

        return departamento;

    }
    
    
    //------------------------------LISTO-----------
    public static ArrayList<Producto> cargarProductos(int idDepartamento){

        ProductoDAO pdao = new ProductoDAO();
        ArrayList<Producto> productosCargados=(ArrayList<Producto>)pdao.listarProductosDeDepartamento(idDepartamento);
        return productosCargados;

    }
    
    public static ArrayList<Producto> cargarProductosCaja(int idDepartamento){

        
        DepartamentoProductoDAO dpdao = new DepartamentoProductoDAO();
        ProductoDAO pdao = new ProductoDAO();
        ArrayList<Producto> productosCargados=new ArrayList<Producto>();
        ArrayList<Producto> productosProcesados=new ArrayList<Producto>();
        
        if(idDepartamento == -1){
         
            productosCargados=(ArrayList)pdao.listar();    
        }
        if(idDepartamento >= 0){
            
            
            for(Object dp: dpdao.obtenerIdDeProducto(idDepartamento)){
                Integer id = (Integer)dp;
                
                Producto p = pdao.obtenerProductoPorSuID(id.intValue()); 
                productosCargados.add(p);
            }
        }
        
        for(Producto p: productosCargados){
            if(p.isMostrarEnCaja()){
                    productosProcesados.add(p);
                }
        }
        
        return productosProcesados;

    }
    
    
    
    //---------------------------LISTO---------------------------
    public static void agregarDepartamento(Departamento nuevoDepartamento){
        DepartamentoDAO ddao=new DepartamentoDAO();
        ddao.add(new Object[]{nuevoDepartamento.getNombre()}    
        );
    }
    
    
    //---------------------------------LISTO----------------------
    public static void agregarProducto(Producto nuevoProducto){

        
        ProductoDAO pdao=new ProductoDAO();
        pdao.add(new Object[]{
            nuevoProducto.getNombre(),nuevoProducto.getPrecio(),nuevoProducto.getCosto(),
            nuevoProducto.getStock(),nuevoProducto.isPrecioVariable(),
            nuevoProducto.isActivarDescuentos(),nuevoProducto.isMostrarEnCaja(),nuevoProducto.getDepartamentoID()<0?null:nuevoProducto.getDepartamentoID()});

    }
    

    public static void agregarProductoEnDepartamento(int idProducto, int idDepartamento){

        
        DepartamentoProducto nuevoDepPro = new DepartamentoProducto(idDepartamento, idProducto);
        DepartamentoProductoDAO dpdao = new DepartamentoProductoDAO();
        dpdao.add(new Object[]{
            nuevoDepPro.getIdDepartamento(), nuevoDepPro.getIdProducto()});
        
        for(Departamento d: cargarDepartamentos()){
            
            if(d.getIdDepartamento() == idDepartamento){
               
                d.setCantidad(d.getCantidad() + 1);
                modificarDepartamento(d);
                
                break;  
            }

        }
    }
    
    
    public static void eliminarProductoDeDepartamento(int idProducto){

        
        DepartamentoProductoDAO dpdao = new DepartamentoProductoDAO();
        DepartamentoDAO ddao = new DepartamentoDAO();
        
        for (Object x: dpdao.listar()) {
            DepartamentoProducto dp = (DepartamentoProducto)x;
            
            if(dp.getIdProducto() == idProducto){
                dpdao.eliminar(dp.getIdDepartamentoProducto());
                
                for (Object n: ddao.listar()){
                    
                    Departamento d = (Departamento)n;
                    if(d.getIdDepartamento() == dp.getIdDepartamento()){
                    d.setCantidad(d.getCantidad()-1);  
                 
                    modificarDepartamento(d);
                    }
                }
            }
        }
    }
    
    
    //-----------------------------------LISTO--------------------------------
    public static void modificarDepartamento(Departamento departamentoModificado){
        
        DepartamentoDAO ddao=new DepartamentoDAO();
        Object[] datos={departamentoModificado.getIdDepartamento(),departamentoModificado.getNombre(),
        };
        ddao.actualizar(datos);
    }
    

    public static void modificarProducto(Producto productoModificado){

        
        ProductoDAO pdao=new ProductoDAO();
        Object[] datos={
            productoModificado.getIdProducto(),
            productoModificado.getNombre(),
            productoModificado.getPrecio(),
            productoModificado.getCosto(), 
            productoModificado.getStock(),
            productoModificado.isPrecioVariable(),
            productoModificado.isActivarDescuentos(),
            productoModificado.isMostrarEnCaja()};
        
            pdao.actualizar(datos);
    }
    
    //---------------------------------LISTO-----------
    public static void eliminarDepartamentos(ArrayList<Departamento> departamentosABorrar){

        
        DepartamentoDAO ddao=new DepartamentoDAO();
        for (Departamento departamento: departamentosABorrar) {
            ddao.eliminacionLogica(departamento.getIdDepartamento());
        }
    }
    

    public static void eliminarProductos(ArrayList<Producto> productosABorrar){
        ProductoDAO pdao=new ProductoDAO();
        for (Producto producto: productosABorrar) {
            pdao.eliminacionLogica(producto.getIdProducto());
        }
    }
    

    public static int obtenerUltimoIDProducto(){

        
        ProductoDAO pdao=new ProductoDAO();
        return pdao.setLastId();
    }
    
    public static void registrarEntrega(Entrega nuevaEntrega){
        ProductoDAO pdao = new ProductoDAO();
        Object[] datos={
            nuevaEntrega.getItem().getIdProducto(),
            nuevaEntrega.getProveedor().getIdProveedor(),
            nuevaEntrega.getCantidad()
        };
        pdao.registrarEntregaProducto(datos);
    }
}

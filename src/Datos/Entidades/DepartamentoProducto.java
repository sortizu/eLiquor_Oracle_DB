
package Datos.Entidades;

/**
 *
 * @author richard
 */
public class DepartamentoProducto {
    private int idDepartamentoProducto;
    private int idDepartamento;
    private int idProducto;
    
    
    public DepartamentoProducto(){
        
    }
    public DepartamentoProducto(int idDepartamento, int idProducto){
        
        this.idDepartamento = idDepartamento;
        this.idProducto = idProducto;
    }
    
    public DepartamentoProducto(int idDepartamentoProducto, int idDepartamento, int idProducto){
        
        this.idDepartamentoProducto = idDepartamentoProducto;
        this.idDepartamento = idDepartamento;
        this.idProducto = idProducto;
    }

    public int getIdDepartamentoProducto() {
        return idDepartamentoProducto;
    }

    public void setIdDepartamentoProducto(int idDepartamentoProducto) {
        this.idDepartamentoProducto = idDepartamentoProducto;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    
    
    
}

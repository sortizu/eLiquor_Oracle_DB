/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion.Interfaces.Inventario;

import Datos.Entidades.Departamento;
import Datos.Entidades.Entrega;
import Datos.Entidades.Producto;
import Negocio.ControlInventario;
import Presentacion.Interfaces.BotonRedondeadoMultiple;
import Presentacion.Interfaces.Buscador;
import Presentacion.Interfaces.FramePrincipal;
import Presentacion.Interfaces.Item;
import Presentacion.Interfaces.PanelModulo;
import Presentacion.Interfaces.Selector;
import Presentacion.Interfaces.TablaItem;
import Presentacion.Utilidades.UtilidadesFuentes;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author sortizu
 */
public class PanelDeInventario extends JPanel implements PropertyChangeListener{
    private PanelModulo panelModuloInventario;
    public ArrayList<Producto> productos;
    public ArrayList<Departamento> departamentos;
    public Departamento departamentoActual;
    private PanelDeInventario panelPrincipalInventario;
    private Container parent;
    private JPanel cuerpo;
    
    private Selector selectorVista;
    private Selector selectorMultiple;
    private Buscador buscadorDepartamento;
    
    private TablaItem tablaInventario;

    private BotonRedondeadoMultiple botonesAccionDepartamento;
    private BotonRedondeadoMultiple botonesAccionProducto;
    
    public PanelDeInventario(Container parent) {
        this.parent=parent;
        panelPrincipalInventario=this;
        iniciarComponentes();
        tablaInventario.revalidate();
        tablaInventario.repaint();
    }
    private void iniciarComponentes(){
        setOpaque(false);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        panelModuloInventario=new PanelModulo(parent,"/Presentacion/Imagenes/Paneles/Boton Ayuda/TutorialInventario.png");
        panelModuloInventario.setTituloPanelModulo("I N V E N T A R I O", Color.decode("#C6C94D"));
        gbc.insets = new Insets((int)(8.0/panelModuloInventario.basePanelHeight*panelModuloInventario.getPreferredSize().getHeight()), 0, 0, 0);
        gbc.gridx=0;
        gbc.gridy=0;
        add(panelModuloInventario,gbc);
        iniciarComponentesCuerpo();
        MouseAdapter limpiarSeleccion = new MouseAdapter(){
            @Override
            public void mouseReleased(MouseEvent e) {
                tablaInventario.getTabla().clearSelection();
                botonesAccionDepartamento.desactivarBoton(0);
                botonesAccionDepartamento.desactivarBoton(1);
            }
        };
        addMouseListener(limpiarSeleccion);
        tablaInventario.getScrollPaneTabla().addMouseListener(limpiarSeleccion);
        cargarListaDeDepartamentos();
        tablaInventario.revalidate();
        tablaInventario.repaint();
    }

    private void iniciarComponentesCuerpo(){
        cuerpo = panelModuloInventario.getPanelContenedorComponentes().getCuerpo();
        int width = (int)panelModuloInventario.getPreferredSize().getWidth();
        int height = (int)panelModuloInventario.getPreferredSize().getHeight();
        
        iniciarComponentesCuerpoSuperior(width, height);
        iniciarComponentesCuerpoMedio(width, height);
        iniciarComponentesCuerpoInferior(width, height);
    }
    
    private void iniciarComponentesCuerpoSuperior(int width, int height){
        GridBagConstraints gbc = new GridBagConstraints();
        
        JLabel selVista = new JLabel("VISTA:");
        selVista.setFont(UtilidadesFuentes.InterLight.deriveFont(20.0f));
        selVista.setForeground(Color.decode("#8C8C8C"));
        gbc.insets=new Insets(25,60,0,10);
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        gbc.weighty=0;
        cuerpo.add(selVista,gbc);
        
        selectorVista = new Selector(new String[]{"DEPARTAMENTO","PRODUCTO"},150,37);
        selectorVista.setFuenteDeOpcion(UtilidadesFuentes.InterRegular.deriveFont(15.0f));
        selectorVista.setColorDeFuente(Color.decode("#8C8C8C"));
        selectorVista.addPropertyChangeListener(this);
        selectorVista.solicitarSeleccion(0);
        selectorVista.setNombreDeSelector("SVista");
        gbc.insets=new Insets(25,0,0,0);
        gbc.gridx=1;
        gbc.gridy=0;
        gbc.anchor=GridBagConstraints.LINE_START;
        gbc.weightx=1;
        gbc.weighty=0;
        cuerpo.add(selectorVista,gbc);
        
        JLabel selMul = new JLabel("SELECCIÓN MÚLTIPLE:");
        selMul.setFont(UtilidadesFuentes.InterLight.deriveFont(20.0f));
        selMul.setForeground(Color.decode("#8C8C8C"));
        gbc.insets=new Insets(25,0,0,10);
        gbc.gridx=2;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        gbc.weighty=0;
        cuerpo.add(selMul,gbc);
        
        selectorMultiple=new Selector(new String[]{"SI","NO"},50,37);
        selectorMultiple.setFuenteDeOpcion(UtilidadesFuentes.InterRegular.deriveFont(15.0f));
        selectorMultiple.setColorDeFuente(Color.decode("#8C8C8C"));
        selectorMultiple.addPropertyChangeListener(this);
        selectorMultiple.solicitarSeleccion(0);
        selectorMultiple.addColorDeOpcion(Color.decode("#72AD57"));
        selectorMultiple.addColorDeOpcion(Color.decode("#AD5757"));
        selectorMultiple.setNombreDeSelector("SSeleccion");
        gbc.insets=new Insets(25,0,0,0);
        gbc.gridx=3;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        gbc.weighty=0;
        cuerpo.add(selectorMultiple,gbc);
        
        buscadorDepartamento=new Buscador();
        buscadorDepartamento.setPreferredSize(new Dimension(375,37));
        buscadorDepartamento.getTxtABuscar().setFont(UtilidadesFuentes.InterRegular.deriveFont(20.0f));
        buscadorDepartamento.getTxtABuscar().setForeground(Color.decode("#8C8C8C"));
        buscadorDepartamento.setPreferredSize(new Dimension(375,37));
        buscadorDepartamento.getTxtABuscar().addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                BuscarDeFiltro(buscadorDepartamento.getTxtABuscar().getText());
            }
        });
        gbc.insets=new Insets(25,50,0,60);
        gbc.gridx=4;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        gbc.weighty=0;
        cuerpo.add(buscadorDepartamento,gbc);
    }
    
    private void iniciarComponentesCuerpoMedio(int width, int height){
        GridBagConstraints gbc = new GridBagConstraints();
        
        tablaInventario=new TablaItem(9,25,25,panelModuloInventario);
        ListSelectionListener listenerTabla = new ListSelectionListener(){
        public void valueChanged(ListSelectionEvent event) {
                boolean activarFunciones=false;
                int[] columnas = tablaInventario.getTabla().getSelectedColumns();
                int[] filas  = tablaInventario.getTabla().getSelectedRows();
                for(int i=0; i< tablaInventario.getTabla().getColumnCount();i++){
                    for(int j=0; j< tablaInventario.getTabla().getRowCount();j++){
                        Item item = (Item)tablaInventario.getTabla().getValueAt(j, i);
                        if(item!=null){
                            item.DeseleccionarItem();
                            tablaInventario.getTabla().setValueAt(item, j, i);
                        }
                    }
                }
                for(int columna: columnas){
                    for(int fila: filas){
                        Item item = (Item)tablaInventario.getTabla().getValueAt(fila, columna);
                        if(item!=null){
                            activarFunciones=true;
                            if(!item.getSeleccionado()){
                                item.seleccionarItem();
                                tablaInventario.getTabla().setValueAt(item, fila, columna);
                            }
                        }
                    }
                }
                if(activarFunciones){
                    if(selectorVista.getOpcionSeleccionada()==0){
                        activarBotonesFuncionDepartamento();
                    }else{
                        activarBotonesFuncionProducto();
                    }
                }else{
                    if(selectorVista.getOpcionSeleccionada()==0){
                        desactivarBotonesFuncionDepartamento();
                    }else{
                        desactivarBotonesFuncionProducto();
                    }
                }
        }
        };
        tablaInventario.getTabla().getSelectionModel().addListSelectionListener(listenerTabla);
        tablaInventario.getTabla().getColumnModel().getSelectionModel().addListSelectionListener(listenerTabla);
        tablaInventario.mostrarCabecera("DEPARTAMENTOS");
        gbc.insets=new Insets(20,60,0,60);
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=1;
        gbc.weighty=1;
        gbc.gridwidth=5;
        cuerpo.add(tablaInventario,gbc);
        
        JSeparator separadorTabla=new JSeparator(JSeparator.HORIZONTAL);
        separadorTabla.setForeground(Color.decode("#D0D0D0"));
        separadorTabla.setOpaque(false);
        separadorTabla.setBorder(new LineBorder(Color.decode("#D0D0D0"),1));
        gbc.insets=new Insets(5, 60, 0,60);
        gbc.gridx=0;
        gbc.gridy=2;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=0;
        gbc.weighty=0;
        cuerpo.add(separadorTabla,gbc);
        
        tablaInventario.getTabla().addMouseListener(new MouseAdapter(){
            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.getClickCount()==2){
                    int fila=tablaInventario.getTabla().getSelectedRow();
                    int columna=tablaInventario.getTabla().getSelectedColumn();
                    if(selectorVista.getOpcionSeleccionada()==0){
                        try {
                            obtenerSeleccionDepartamento();
                        } catch (Exception er) {}
                    }
                }
            }
        });
    }
    
    private void iniciarComponentesCuerpoInferior(int width, int height){
        GridBagConstraints gbc = new GridBagConstraints();
        botonesAccionDepartamento=new BotonRedondeadoMultiple(new String[]{"ELIMINAR","MODIFICAR","VER PROD.","AGREGAR"},new Dimension(130,55)){
            @Override
            public void botonOpcionPresionado(int opcionPresionada) {
                switch (opcionPresionada) {
                    case 0:
                        int [] indicesSeleccionFila=tablaInventario.getTabla().getSelectedRows();
                        int [] indicesSeleccionColumna=tablaInventario.getTabla().getSelectedColumns();
                        for(int i = 0;i<indicesSeleccionFila.length;i++){
                            indicesSeleccionFila[i]=tablaInventario.getTabla().convertRowIndexToModel(indicesSeleccionFila[i]);
                        }
                        for(int i = 0;i<indicesSeleccionColumna.length;i++){
                            indicesSeleccionColumna[i]=tablaInventario.getTabla().convertColumnIndexToModel(indicesSeleccionColumna[i]);
                        }
                        EliminarDepartamento eliminarDepartamento = new EliminarDepartamento(panelPrincipalInventario,indicesSeleccionFila,indicesSeleccionColumna);
                    ((FramePrincipal) SwingUtilities.getWindowAncestor(panelPrincipalInventario)).mostrarPanelEmergente(eliminarDepartamento);
                    eliminarDepartamento.requestFocusInWindow();
                        break;
                    case 1:
                        ModificarDepartamento modificarDepartamento = new ModificarDepartamento(panelPrincipalInventario, new int [] {tablaInventario.getTabla().convertRowIndexToModel(tablaInventario.getTabla().getSelectedRow()), tablaInventario.getTabla().convertColumnIndexToModel(tablaInventario.getTabla().getSelectedColumn())});
                    ((FramePrincipal) SwingUtilities.getWindowAncestor(panelPrincipalInventario)).mostrarPanelEmergente(modificarDepartamento);
                    modificarDepartamento.requestFocusInWindow();
                        break;
                    case 2:
                        obtenerSeleccionDepartamento();
                        break;
                    case 3:
                    default:
                       AgregarDepartamento agregarDepartamento = new AgregarDepartamento(panelPrincipalInventario);
                    ((FramePrincipal) SwingUtilities.getWindowAncestor(panelPrincipalInventario)).mostrarPanelEmergente(agregarDepartamento);
                    agregarDepartamento.requestFocusInWindow();
                        break;
                }
            }        
        };
        botonesAccionProducto=new BotonRedondeadoMultiple(new String[]{"ELIMINAR","MODIFICAR","INGRESO","AGREGAR"},new Dimension(130,55)){
            @Override
            public void botonOpcionPresionado(int opcionPresionada) {
                switch (opcionPresionada) {
                    case 0:
                        int [] indicesSeleccionFila=tablaInventario.getTabla().getSelectedRows();
                        int [] indicesSeleccionColumna=tablaInventario.getTabla().getSelectedColumns();
                        for(int i = 0;i<indicesSeleccionFila.length;i++){
                            indicesSeleccionFila[i]=tablaInventario.getTabla().convertRowIndexToModel(indicesSeleccionFila[i]);
                        }
                        for(int i = 0;i<indicesSeleccionColumna.length;i++){
                            indicesSeleccionColumna[i]=tablaInventario.getTabla().convertColumnIndexToModel(indicesSeleccionColumna[i]);
                        }
                        EliminarProducto eliminarProducto = new EliminarProducto(panelPrincipalInventario,indicesSeleccionFila,indicesSeleccionColumna);
                    ((FramePrincipal) SwingUtilities.getWindowAncestor(panelPrincipalInventario)).mostrarPanelEmergente(eliminarProducto);
                    eliminarProducto.requestFocusInWindow();
                        break;
                    case 1:
                        int fila=tablaInventario.getTabla().convertRowIndexToModel(tablaInventario.getTabla().getSelectedRow());
                        int columna=tablaInventario.getTabla().convertColumnIndexToModel(tablaInventario.getTabla().getSelectedColumn());
                       ModificarProducto modificarProducto = new ModificarProducto(panelPrincipalInventario,new int [] {fila,columna},productos.get(fila*tablaInventario.getColumnas()+columna));
                    ((FramePrincipal) SwingUtilities.getWindowAncestor(panelPrincipalInventario)).mostrarPanelEmergente(modificarProducto);
                    modificarProducto.requestFocusInWindow();
                        break;
                    case 2:
                        Producto p=productos.get(tablaInventario.getTabla().convertRowIndexToModel(tablaInventario.getTabla().getSelectedRow())*tablaInventario.getColumnas()+tablaInventario.getTabla().convertColumnIndexToModel(tablaInventario.getTabla().getSelectedColumn()));
                        Entrega nuevaEntrega=new Entrega(0,p,0,p.getCosto(),null,null);
                       IngresoProducto1 ingresoProducto1 = new IngresoProducto1(panelPrincipalInventario,nuevaEntrega);
                    ((FramePrincipal) SwingUtilities.getWindowAncestor(panelPrincipalInventario)).mostrarPanelEmergente(ingresoProducto1);
                    ingresoProducto1.requestFocusInWindow();
                        break;    
                    case 3:
                    default:
                       AgregarProducto agregarProducto = new AgregarProducto(panelPrincipalInventario);
                    ((FramePrincipal) SwingUtilities.getWindowAncestor(panelPrincipalInventario)).mostrarPanelEmergente(agregarProducto);
                    agregarProducto.requestFocusInWindow();
                        break;
                }
            }        
        };
        botonesAccionDepartamento.setColorOpcion(0, Color.decode("#CD5F5F"));
        botonesAccionDepartamento.setColorOpcion(1, Color.decode("#5F7ECD"));
        botonesAccionDepartamento.setColorOpcion(2, Color.decode("#D9AA4F"));
        botonesAccionDepartamento.setColorOpcion(3, Color.decode("#6ECD5F"));
        botonesAccionProducto.setColorOpcion(0, Color.decode("#CD5F5F"));
        botonesAccionProducto.setColorOpcion(1, Color.decode("#5F7ECD"));
        botonesAccionProducto.setColorOpcion(2, Color.decode("#D9AA4F"));
        botonesAccionProducto.setColorOpcion(3, Color.decode("#6ECD5F"));
        desactivarBotonesFuncionDepartamento();
        gbc.insets=new Insets(10, 0, 20,0);
        gbc.gridx=0;
        gbc.gridy=3;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=1;
        gbc.weighty=0;
        gbc.gridwidth=5;
        cuerpo.add(botonesAccionDepartamento,gbc);
        cuerpo.add(botonesAccionProducto,gbc);
        botonesAccionProducto.setVisible(false);
    }
    
    private void obtenerSeleccionDepartamento(){
        departamentoActual = departamentos.get(tablaInventario.getTabla().convertRowIndexToModel(tablaInventario.getTabla().getSelectedRow())*tablaInventario.getColumnas()+tablaInventario.getTabla().convertColumnIndexToModel(tablaInventario.getTabla().getSelectedColumn()));
        tablaInventario.mostrarCabecera("PRODUCTOS DE \""+departamentoActual.getNombre()+"\"");
        ((Selector)selectorVista).solicitarSeleccion(1);
    }
    
    public void desactivarBotonesFuncionDepartamento(){
        botonesAccionDepartamento.desactivarBoton(0);
        botonesAccionDepartamento.desactivarBoton(1);
        botonesAccionDepartamento.desactivarBoton(2);
    }
    
    public void activarBotonesFuncionDepartamento(){
        botonesAccionDepartamento.activarBoton(0);
        botonesAccionDepartamento.activarBoton(1);
        botonesAccionDepartamento.activarBoton(2);
    }
    
    public void desactivarBotonesFuncionProducto(){
        botonesAccionProducto.desactivarBoton(0);
        botonesAccionProducto.desactivarBoton(1);
        botonesAccionProducto.desactivarBoton(2);
    }
    
    public void activarBotonesFuncionProducto(){
        botonesAccionProducto.activarBoton(0);
        botonesAccionProducto.activarBoton(1);
        botonesAccionProducto.activarBoton(2);
    }
    
    public void cargarListaDeDepartamentos(){
        departamentos=ControlInventario.cargarDepartamentos();
        mostrarListaDepartamentosCargadaEnTabla();
    }
    
    public void cargarListaDeProductos(int idDepartamento){
        productos=ControlInventario.cargarProductos(idDepartamento);
        mostrarListaProductosCargadaEnTabla();
    }
    
    public void mostrarListaDepartamentosCargadaEnTabla(){
        tablaInventario.limpiarTabla();
        tablaInventario.getCursorTabla()[0]=0;
        tablaInventario.getCursorTabla()[1]=0;
        for(Departamento departamento: departamentos){
            agregarDepartamentoATabla(departamento);
        }
    }
    
    public void mostrarListaProductosCargadaEnTabla(){
        tablaInventario.limpiarTabla();
        tablaInventario.getCursorTabla()[0]=0;
        tablaInventario.getCursorTabla()[1]=0;
        for(Producto producto: productos){
            agregarProductoATabla(producto);
        }
    }
    
    public void agregarDepartamentoATabla(Departamento departamento){
       Item item = new Item(departamento.getNombre(), (departamento.getCantidad()+" ITEMS"));
       tablaInventario.agregarItem(item);
    }
    
    public void modificarDepartamentoDeTabla(int[] indice, Departamento departamento){
        Item item = (Item)tablaInventario.getTabla().getValueAt(indice[0], indice[1]);
        item.getNombre().setText(departamento.getNombre());
        item.getSubtitulo().setText(departamento.getCantidad()+" ITEMS");
        tablaInventario.modificarItem(indice,item);
    }
    
    public void agregarProductoATabla(Producto producto){
        Item item = new Item(producto.getNombre(),"STOCK: "+producto.getStock());
        tablaInventario.agregarItem(item);
    }
    
    public void modificarProductoEnTabla(int[] indice, Producto producto){
        indice[0]=tablaInventario.getTabla().convertRowIndexToView(indice[0]);
        indice[1]=tablaInventario.getTabla().convertColumnIndexToView(indice[1]);
        Item item = (Item)tablaInventario.getTabla().getValueAt(indice[0], indice[1]);
        item.getNombre().setText(producto.getNombre());
        item.getSubtitulo().setText("STOCK: "+producto.getStock());
        tablaInventario.modificarItem(indice,item);
    }
    
    private void BuscarDeFiltro(String textoBusqueda){
        RowFilter<Object,Object> filtro = new RowFilter<Object, Object>(){
            private Matcher matcher=Pattern.compile("(?i)"+textoBusqueda).matcher("");
            @Override
            public boolean include(RowFilter.Entry<? extends Object, ? extends Object> entry) {
                for(int i=entry.getValueCount()-1;i>=0;i--){
                    Object obj = entry.getValue(i);
                    if(obj instanceof Item){
                        Item item = (Item)obj;
                        String nombre=item.getNombre().getText();
                        matcher.reset(nombre);
                        if(matcher.find()){
                            return true;
                        }
                    }
                }
                return false;
            }
            
        };
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(tablaInventario.getModeloTabla());
        tablaInventario.getTabla().setRowSorter(sorter);
        sorter.setRowFilter(filtro);
    }

    public TablaItem getTablaInventario() {
        return tablaInventario;
    }

    public void setTablaInventario(TablaItem tablaInventario) {
        this.tablaInventario = tablaInventario;
    }
    
    public void reiniciarBusqueda(){
        buscadorDepartamento.getTxtABuscar().setText("");
        tablaInventario.getTabla().setRowSorter(null);
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Selector selectorModificado=((Selector)evt.getSource());
        String tipoSelector=selectorModificado.getNombreDeSelector();
        switch (tipoSelector) {
            case "SSeleccion":
                    try {
                        if((int)evt.getNewValue()==0){
                            tablaInventario.getTabla().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                        }else{
                            tablaInventario.getTabla().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                        }
                    } catch (Exception e) {
                        System.err.println(e);
                    }
            break;
            case "SVista":
                if((int)evt.getNewValue()==1){
                    int idDepartamento=-1;
                    if(departamentoActual==null){
                        tablaInventario.mostrarCabecera("TODOS LOS PRODUCTOS");
                    }else{
                        idDepartamento=departamentoActual.getIdDepartamento();
                    }
                    cargarListaDeProductos(idDepartamento);
                    botonesAccionProducto.setVisible(true);
                    botonesAccionDepartamento.setVisible(false);
                    activarBotonesFuncionProducto();
                    desactivarBotonesFuncionProducto();
                }else{
                    tablaInventario.mostrarCabecera("DEPARTAMENTOS");
                    departamentoActual=null;
                    productos=null;
                    cargarListaDeDepartamentos();
                    mostrarListaDepartamentosCargadaEnTabla();
                    botonesAccionProducto.setVisible(false);
                    botonesAccionDepartamento.setVisible(true);
                    activarBotonesFuncionDepartamento();
                    desactivarBotonesFuncionDepartamento();
                }
                reiniciarBusqueda();
            break;
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion.Interfaces.Proveedores;

import Datos.Entidades.Entrega;
import Datos.Entidades.Proveedor;
import Datos.Entidades.Usuario;
import Negocio.ControlProveedores;
import Presentacion.Interfaces.BotonRedondeadoMultiple;
import Presentacion.Interfaces.Buscador;
import Presentacion.Interfaces.FramePrincipal;
import Presentacion.Interfaces.PanelModulo;
import Presentacion.Interfaces.Selector;
import Presentacion.Interfaces.TablaDefault;
import Presentacion.Interfaces.Usuarios.AgregarUsuarios;
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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
public class PanelDeProveedores extends JPanel implements PropertyChangeListener{
    private PanelModulo panelModuloProveedores;
    public ArrayList<Proveedor> proveedores;
    private PanelDeProveedores panelPrincipalProveedores;
    private Container parent;
    private JPanel cuerpo;
    
    private Selector selectorMultiple;
    private Buscador buscadorProveedor;
    
    private TablaDefault tablaProveedores;

    private BotonRedondeadoMultiple botonesAccionProveedores;
    
    public PanelDeProveedores(Container parent) {
        this.parent=parent;
        panelPrincipalProveedores=this;
        proveedores=new ArrayList<Proveedor>();
        iniciarComponentes();
        cargarListaDeProveedores();
        tablaProveedores.revalidate();
        tablaProveedores.repaint();
    }
    private void iniciarComponentes(){
        setOpaque(false);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        panelModuloProveedores=new PanelModulo(parent,"/Presentacion/Imagenes/Paneles/Boton Ayuda/TutorialProveedores.png");
        panelModuloProveedores.setTituloPanelModulo("P R O V E E D O R E S", Color.decode("#8D2E7E"));
        gbc.insets = new Insets((int)(8.0/panelModuloProveedores.basePanelHeight*panelModuloProveedores.getPreferredSize().getHeight()), 0, 0, 0);
        gbc.gridx=0;
        gbc.gridy=0;
        add(panelModuloProveedores,gbc);
        iniciarComponentesCuerpo();
        MouseAdapter limpiarSeleccion = new MouseAdapter(){
            @Override
            public void mouseReleased(MouseEvent e) {
                tablaProveedores.getTabla().clearSelection();
                botonesAccionProveedores.desactivarBoton(0);
                botonesAccionProveedores.desactivarBoton(1);
            }
        };
        addMouseListener(limpiarSeleccion);
        tablaProveedores.getScrollPaneTabla().addMouseListener(limpiarSeleccion);
    }

    private void iniciarComponentesCuerpo(){
        cuerpo = panelModuloProveedores.getPanelContenedorComponentes().getCuerpo();
        int width = (int)panelModuloProveedores.getPreferredSize().getWidth();
        int height = (int)panelModuloProveedores.getPreferredSize().getHeight();
        
        iniciarComponentesCuerpoSuperior(width, height);
        iniciarComponentesCuerpoMedio(width, height);
        iniciarComponentesCuerpoInferior(width, height);
    }
    
    private void iniciarComponentesCuerpoSuperior(int width, int height){
        GridBagConstraints gbc = new GridBagConstraints();
        
        JLabel selMul = new JLabel("SELECCIÓN MÚLTIPLE:");
        selMul.setFont(UtilidadesFuentes.InterLight.deriveFont(20.0f));
        selMul.setForeground(Color.decode("#8C8C8C"));
        gbc.insets=new Insets(25,0,0,5);
        gbc.anchor=GridBagConstraints.LINE_END;
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=1;
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
        gbc.anchor=GridBagConstraints.CENTER;
        gbc.gridx=1;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        gbc.weighty=0;
        cuerpo.add(selectorMultiple,gbc);
        
        buscadorProveedor=new Buscador();
        buscadorProveedor.setPreferredSize(new Dimension(375,37));
        buscadorProveedor.getTxtABuscar().setFont(UtilidadesFuentes.InterRegular.deriveFont(20.0f));
        buscadorProveedor.getTxtABuscar().setForeground(Color.decode("#8C8C8C"));
        buscadorProveedor.setPreferredSize(new Dimension(375,37));
        buscadorProveedor.getTxtABuscar().addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                BuscarDeFiltro(buscadorProveedor.getTxtABuscar().getText());
            }
        });
        gbc.insets=new Insets(25,50,0,60);
        gbc.gridx=2;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        gbc.weighty=0;
        cuerpo.add(buscadorProveedor,gbc);
    }
    
    private void iniciarComponentesCuerpoMedio(int width, int height){
        GridBagConstraints gbc = new GridBagConstraints();
        
        tablaProveedores=new TablaDefault(new String[]{"Razón Social","Correo","Teléfono","Últ. Entrega","Items","Fec. registro"}, new int[]{200,300,50,50,50,50}, panelModuloProveedores);
        tablaProveedores.getTabla().getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(tablaProveedores.getTabla().getSelectedRows().length>0){
                    botonesAccionProveedores.activarBoton(0);
                    botonesAccionProveedores.activarBoton(1);
                }else{
                    botonesAccionProveedores.desactivarBoton(0);
                    botonesAccionProveedores.desactivarBoton(1);
                }
            }
        });
        tablaProveedores.setAltoFilaBase(75);
        gbc.insets=new Insets(20,60,0,60);
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=1;
        gbc.weighty=1;
        gbc.gridwidth=3;
        cuerpo.add(tablaProveedores,gbc);
        
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
    }
    
    private void iniciarComponentesCuerpoInferior(int width, int height){
        GridBagConstraints gbc = new GridBagConstraints();
        botonesAccionProveedores=new BotonRedondeadoMultiple(new String[]{"ELIMINAR","MODIFICAR","AGREGAR"},new Dimension(130,55)){
            @Override
            public void botonOpcionPresionado(int opcionPresionada) {
                switch (opcionPresionada) {
                    case 0:
                        int [] indicesSeleccion=tablaProveedores.getTabla().getSelectedRows();
                        for(int i = 0;i<indicesSeleccion.length;i++){
                            indicesSeleccion[i]=tablaProveedores.getTabla().convertRowIndexToModel(indicesSeleccion[i]);
                        }
                        EliminarProveedores eliminarProveedores = new EliminarProveedores(panelPrincipalProveedores,indicesSeleccion);
                        ((FramePrincipal) SwingUtilities.getWindowAncestor(panelPrincipalProveedores)).mostrarPanelEmergente(eliminarProveedores);
                        eliminarProveedores.requestFocusInWindow();
                        break;
                    case 1:
                        
                        ModificarProveedores modificarProveedores = new ModificarProveedores(panelPrincipalProveedores,tablaProveedores.getTabla().convertRowIndexToModel(tablaProveedores.getTabla().getSelectedRow()));
                        ((FramePrincipal) SwingUtilities.getWindowAncestor(panelPrincipalProveedores)).mostrarPanelEmergente(modificarProveedores);
                        modificarProveedores.requestFocusInWindow();
                        break;
                    case 2:
                    default:
                        AgregarProveedores agregarProveedores = new AgregarProveedores(panelPrincipalProveedores);
                        ((FramePrincipal) SwingUtilities.getWindowAncestor(panelPrincipalProveedores)).mostrarPanelEmergente(agregarProveedores);
                        agregarProveedores.requestFocusInWindow();
                        break;
                }
            }        
        };
        botonesAccionProveedores.setColorOpcion(0, Color.decode("#CD5F5F"));
        botonesAccionProveedores.setColorOpcion(1, Color.decode("#5F7ECD"));
        botonesAccionProveedores.setColorOpcion(2, Color.decode("#6ECD5F"));
        botonesAccionProveedores.desactivarBoton(0);
        botonesAccionProveedores.desactivarBoton(1);
        gbc.insets=new Insets(10, 0, 20,0);
        gbc.gridx=0;
        gbc.gridy=3;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=1;
        gbc.weighty=0;
        gbc.gridwidth=3;
        cuerpo.add(botonesAccionProveedores,gbc);
    }

    public void cargarListaDeProveedores(){
        tablaProveedores.getModeloTabla().setRowCount(0);
        proveedores=ControlProveedores.cargarListaDeProveedores();
        for(Proveedor p: proveedores){
            agregarProveedorATabla(p);
        }
    }
    
    public void agregarProveedorATabla(Proveedor p){
        Entrega ultimaEntrega = ControlProveedores.cargarUltimaEntregaProveedor(p.getIdProveedor());
        Object[] datos=
        {
            p.getRazonSocial(),
            p.getCorreo(),
            p.getTelefono(),
            ultimaEntrega!=null?ultimaEntrega.getFechaEntrega().format(DateTimeFormatter.ofPattern("dd/MM/YYYY")):"",
            ultimaEntrega!=null?ultimaEntrega.getCantidad():0,
            p.getFechaRegistro().format(DateTimeFormatter.ofPattern("dd/MM/YYYY"))
        };
        tablaProveedores.getModeloTabla().addRow(datos);
    }
    
    public void modificarProveedorDeTabla(int fila, Proveedor p){
        tablaProveedores.getModeloTabla().setValueAt(p.getRazonSocial(),fila ,0);
        tablaProveedores.getModeloTabla().setValueAt(p.getCorreo(),fila ,1);
        tablaProveedores.getModeloTabla().setValueAt(p.getTelefono(),fila ,2);
    }
    
    public void eliminarProveedorDeLaTabla(int indice){
        tablaProveedores.getModeloTabla().removeRow(indice);
    }
    
    private void BuscarDeFiltro(String textoBusqueda){
        TableRowSorter<DefaultTableModel> sorter =sorter = new TableRowSorter<DefaultTableModel>(tablaProveedores.getModeloTabla());
        tablaProveedores.getTabla().setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter("(?i)"+textoBusqueda));
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Selector selectorModificado=((Selector)evt.getSource());
        String tipoSelector=selectorModificado.getNombreDeSelector();
        switch (tipoSelector) {
            case "SSeleccion":
                    try {
                        if((int)evt.getNewValue()==0){
                            tablaProveedores.getTabla().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                        }else{
                            tablaProveedores.getTabla().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                        }
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                break;
        }
    }
}

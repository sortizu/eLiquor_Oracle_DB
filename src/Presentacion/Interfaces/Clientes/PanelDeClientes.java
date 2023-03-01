/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion.Interfaces.Clientes;

import Presentacion.Interfaces.Clientes.*;
import Datos.Entidades.Cliente;
import Negocio.ControlClientes;
import Presentacion.Interfaces.BotonRedondeadoMultiple;
import Presentacion.Interfaces.Buscador;
import Presentacion.Interfaces.FramePrincipal;
import Presentacion.Interfaces.PanelModulo;
import Presentacion.Interfaces.Selector;
import Presentacion.Interfaces.TablaDefault;
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
public class PanelDeClientes extends JPanel implements PropertyChangeListener{
    private PanelModulo panelModuloClientes;
    public ArrayList<Cliente> clientes;
    private PanelDeClientes panelPrincipalClientes;
    private Container parent;
    private JPanel cuerpo;
    
    private Selector selectorMultiple;
    private Buscador buscadorCliente;
    
    private TablaDefault tablaClientes;

    private BotonRedondeadoMultiple botonesAccionClientes;
    
    public PanelDeClientes(Container parent) {
        this.parent=parent;
        panelPrincipalClientes=this;
        clientes=new ArrayList<Cliente>();
        iniciarComponentes();
        cargarListaDeClientes();
        tablaClientes.revalidate();
        tablaClientes.repaint();
    }
    private void iniciarComponentes(){
        setOpaque(false);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        panelModuloClientes=new PanelModulo(parent,"/Presentacion/Imagenes/Paneles/Boton Ayuda/TutorialClientes.png");
        panelModuloClientes.setTituloPanelModulo("C L I E N T E S", Color.decode("#B88A30"));
        gbc.insets = new Insets((int)(8.0/panelModuloClientes.basePanelHeight*panelModuloClientes.getPreferredSize().getHeight()), 0, 0, 0);
        gbc.gridx=0;
        gbc.gridy=0;
        add(panelModuloClientes,gbc);
        iniciarComponentesCuerpo();
        MouseAdapter limpiarSeleccion = new MouseAdapter(){
            @Override
            public void mouseReleased(MouseEvent e) {
                tablaClientes.getTabla().clearSelection();
                botonesAccionClientes.desactivarBoton(0);
                botonesAccionClientes.desactivarBoton(1);
            }
        };
        addMouseListener(limpiarSeleccion);
        tablaClientes.getScrollPaneTabla().addMouseListener(limpiarSeleccion);
        
    }

    private void iniciarComponentesCuerpo(){
        cuerpo = panelModuloClientes.getPanelContenedorComponentes().getCuerpo();
        int width = (int)panelModuloClientes.getPreferredSize().getWidth();
        int height = (int)panelModuloClientes.getPreferredSize().getHeight();
        
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
        
        buscadorCliente=new Buscador();
        buscadorCliente.setPreferredSize(new Dimension(375,37));
        buscadorCliente.getTxtABuscar().setFont(UtilidadesFuentes.InterRegular.deriveFont(20.0f));
        buscadorCliente.getTxtABuscar().setForeground(Color.decode("#8C8C8C"));
        buscadorCliente.setPreferredSize(new Dimension(375,37));
        buscadorCliente.getTxtABuscar().addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                BuscarDeFiltro(buscadorCliente.getTxtABuscar().getText());
            }
        });
        gbc.insets=new Insets(25,50,0,60);
        gbc.gridx=2;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        gbc.weighty=0;
        cuerpo.add(buscadorCliente,gbc);
    }
    
    private void iniciarComponentesCuerpoMedio(int width, int height){
        GridBagConstraints gbc = new GridBagConstraints();
        
        tablaClientes=new TablaDefault(new String[]{"Nombre","Correo","Teléfono","Fecha de registro"}, new int[]{200,300,200,200}, panelModuloClientes);
        tablaClientes.getTabla().getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(tablaClientes.getTabla().getSelectedRows().length>0){
                    botonesAccionClientes.activarBoton(0);
                    botonesAccionClientes.activarBoton(1);
                }else{
                    botonesAccionClientes.desactivarBoton(0);
                    botonesAccionClientes.desactivarBoton(1);
                }
            }
        });
        tablaClientes.setAltoFilaBase(75);
        gbc.insets=new Insets(20,60,0,60);
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=1;
        gbc.weighty=1;
        gbc.gridwidth=3;
        cuerpo.add(tablaClientes,gbc);
        
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
        botonesAccionClientes=new BotonRedondeadoMultiple(new String[]{"ELIMINAR","MODIFICAR","AGREGAR"},new Dimension(130,55)){
            @Override
            public void botonOpcionPresionado(int opcionPresionada) {
                switch (opcionPresionada) {
                    case 0:
                        int [] indicesSeleccion=tablaClientes.getTabla().getSelectedRows();
                        for(int i = 0;i<indicesSeleccion.length;i++){
                            indicesSeleccion[i]=tablaClientes.getTabla().convertRowIndexToModel(indicesSeleccion[i]);
                        }
                        EliminarClientes eliminarClientes = new EliminarClientes(panelPrincipalClientes,indicesSeleccion);
                        ((FramePrincipal) SwingUtilities.getWindowAncestor(panelPrincipalClientes)).mostrarPanelEmergente(eliminarClientes);
                        eliminarClientes.requestFocusInWindow();
                        break;
                    case 1:
                        
                        ModificarClientes modificarClientes = new ModificarClientes(panelPrincipalClientes,tablaClientes.getTabla().convertRowIndexToModel(tablaClientes.getTabla().getSelectedRow()));
                        ((FramePrincipal) SwingUtilities.getWindowAncestor(panelPrincipalClientes)).mostrarPanelEmergente(modificarClientes);
                        modificarClientes.requestFocusInWindow();
                        break;
                    case 2:
                    default:
                        AgregarClientes agregarClientes = new AgregarClientes(panelPrincipalClientes);
                        ((FramePrincipal) SwingUtilities.getWindowAncestor(panelPrincipalClientes)).mostrarPanelEmergente(agregarClientes);
                        agregarClientes.requestFocusInWindow();
                        break;
                }
            }        
        };
        botonesAccionClientes.setColorOpcion(0, Color.decode("#CD5F5F"));
        botonesAccionClientes.setColorOpcion(1, Color.decode("#5F7ECD"));
        botonesAccionClientes.setColorOpcion(2, Color.decode("#6ECD5F"));
        botonesAccionClientes.desactivarBoton(0);
        botonesAccionClientes.desactivarBoton(1);
        gbc.insets=new Insets(10, 0, 20,0);
        gbc.gridx=0;
        gbc.gridy=3;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=1;
        gbc.weighty=0;
        gbc.gridwidth=3;
        cuerpo.add(botonesAccionClientes,gbc);
    }

    public void cargarListaDeClientes(){
        clientes=ControlClientes.cargarListaDeClientes();
        for(Cliente p: clientes){
            agregarClienteATabla(p);
        }
    }
    
    public void agregarClienteATabla(Cliente p){
        Object[] datos=
        {
            p.getNombre(),
            p.getCorreo(),
            p.getTelefono(),
            p.getFechaRegistro().format(DateTimeFormatter.ofPattern("dd/MM/YYYY"))
        };
        tablaClientes.getModeloTabla().addRow(datos);
    }
    
    public void modificarClienteDeTabla(int fila, Cliente p){
        tablaClientes.getModeloTabla().setValueAt(p.getNombre(),fila ,0);
        tablaClientes.getModeloTabla().setValueAt(p.getCorreo(),fila ,1);
        tablaClientes.getModeloTabla().setValueAt(p.getTelefono(),fila ,2);
    }
    
    public void eliminarClienteDeLaTabla(int indice){
        tablaClientes.getModeloTabla().removeRow(indice);
    }
    
    private void BuscarDeFiltro(String textoBusqueda){
        TableRowSorter<DefaultTableModel> sorter =sorter = new TableRowSorter<DefaultTableModel>(tablaClientes.getModeloTabla());
        tablaClientes.getTabla().setRowSorter(sorter);
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
                            tablaClientes.getTabla().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                        }else{
                            tablaClientes.getTabla().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                        }
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                break;
        }
    }
}

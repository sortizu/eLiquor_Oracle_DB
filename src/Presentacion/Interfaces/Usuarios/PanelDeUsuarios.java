/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion.Interfaces.Usuarios;

import Presentacion.Interfaces.Usuarios.*;
import Presentacion.Interfaces.Usuarios.*;
import Datos.Entidades.Usuario;
import Negocio.ControlUsuarios;
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
public class PanelDeUsuarios extends JPanel implements PropertyChangeListener{
    private PanelModulo panelModuloUsuarios;
    public ArrayList<Usuario> usuarios;
    private PanelDeUsuarios panelPrincipalUsuarios;
    private Container parent;
    private JPanel cuerpo;
    
    private Selector selectorMultiple;
    private Buscador buscadorUsuario;
    
    private TablaDefault tablaUsuarios;

    private BotonRedondeadoMultiple botonesAccionUsuarios;
    
    public PanelDeUsuarios(Container parent) {
        this.parent=parent;
        panelPrincipalUsuarios=this;
        usuarios=new ArrayList<Usuario>();
        iniciarComponentes();
        cargarListaDeUsuarios();
        tablaUsuarios.revalidate();
        tablaUsuarios.repaint();
    }
    private void iniciarComponentes(){
        setOpaque(false);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        panelModuloUsuarios=new PanelModulo(parent,"/Presentacion/Imagenes/Paneles/Boton Ayuda/TutorialUsuarios.png");
        panelModuloUsuarios.setTituloPanelModulo("U S U A R I O S", Color.decode("#9D4040"));
        gbc.insets = new Insets((int)(8.0/panelModuloUsuarios.basePanelHeight*panelModuloUsuarios.getPreferredSize().getHeight()), 0, 0, 0);
        gbc.gridx=0;
        gbc.gridy=0;
        add(panelModuloUsuarios,gbc);
        iniciarComponentesCuerpo();
        MouseAdapter limpiarSeleccion = new MouseAdapter(){
            @Override
            public void mouseReleased(MouseEvent e) {
                tablaUsuarios.getTabla().clearSelection();
                botonesAccionUsuarios.desactivarBoton(0);
                botonesAccionUsuarios.desactivarBoton(1);
            }
        };
        addMouseListener(limpiarSeleccion);
        tablaUsuarios.getScrollPaneTabla().addMouseListener(limpiarSeleccion);
        
    }

    private void iniciarComponentesCuerpo(){
        cuerpo = panelModuloUsuarios.getPanelContenedorComponentes().getCuerpo();
        int width = (int)panelModuloUsuarios.getPreferredSize().getWidth();
        int height = (int)panelModuloUsuarios.getPreferredSize().getHeight();
        
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
        
        buscadorUsuario=new Buscador();
        buscadorUsuario.setPreferredSize(new Dimension(375,37));
        buscadorUsuario.getTxtABuscar().setFont(UtilidadesFuentes.InterRegular.deriveFont(20.0f));
        buscadorUsuario.getTxtABuscar().setForeground(Color.decode("#8C8C8C"));
        buscadorUsuario.setPreferredSize(new Dimension(375,37));
        buscadorUsuario.getTxtABuscar().addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                BuscarDeFiltro(buscadorUsuario.getTxtABuscar().getText());
            }
        });
        gbc.insets=new Insets(25,50,0,60);
        gbc.gridx=2;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        gbc.weighty=0;
        cuerpo.add(buscadorUsuario,gbc);
    }
    
    private void iniciarComponentesCuerpoMedio(int width, int height){
        GridBagConstraints gbc = new GridBagConstraints();
        
        tablaUsuarios=new TablaDefault(new String[]{"Nombre","Estado","Último Ingreso","Fecha de registro"}, new int[]{200,100,100,100}, panelModuloUsuarios);
        tablaUsuarios.getTabla().getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(tablaUsuarios.getTabla().getSelectedRows().length>0){
                    botonesAccionUsuarios.activarBoton(0);
                    botonesAccionUsuarios.activarBoton(1);
                }else{
                    botonesAccionUsuarios.desactivarBoton(0);
                    botonesAccionUsuarios.desactivarBoton(1);
                }
            }
        });
        tablaUsuarios.setAltoFilaBase(75);
        gbc.insets=new Insets(20,60,0,60);
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=1;
        gbc.weighty=1;
        gbc.gridwidth=3;
        cuerpo.add(tablaUsuarios,gbc);
        
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
        botonesAccionUsuarios=new BotonRedondeadoMultiple(new String[]{"ELIMINAR","MODIFICAR","AGREGAR"},new Dimension(130,55)){
            @Override
            public void botonOpcionPresionado(int opcionPresionada) {
                switch (opcionPresionada) {
                    case 0:
                        int [] indicesSeleccion=tablaUsuarios.getTabla().getSelectedRows();
                        for(int i = 0;i<indicesSeleccion.length;i++){
                            indicesSeleccion[i]=tablaUsuarios.getTabla().convertRowIndexToModel(indicesSeleccion[i]);
                        }
                        EliminarUsuarios eliminarUsuarios = new EliminarUsuarios(panelPrincipalUsuarios,indicesSeleccion);
                        ((FramePrincipal) SwingUtilities.getWindowAncestor(panelPrincipalUsuarios)).mostrarPanelEmergente(eliminarUsuarios);
                        eliminarUsuarios.requestFocusInWindow();
                        break;
                    case 1:
                        ModificarUsuarios modificarUsuarios = new ModificarUsuarios(panelPrincipalUsuarios,tablaUsuarios.getTabla().convertRowIndexToModel(tablaUsuarios.getTabla().getSelectedRow()));
                        ((FramePrincipal) SwingUtilities.getWindowAncestor(panelPrincipalUsuarios)).mostrarPanelEmergente(modificarUsuarios);
                        modificarUsuarios.requestFocusInWindow();
                        break;
                    case 2:
                    default:
                        AgregarUsuarios agregarUsuarios = new AgregarUsuarios(panelPrincipalUsuarios);
                        ((FramePrincipal) SwingUtilities.getWindowAncestor(panelPrincipalUsuarios)).mostrarPanelEmergente(agregarUsuarios);
                        agregarUsuarios.requestFocusInWindow();
                        break;
                }
            }        
        };
        botonesAccionUsuarios.setColorOpcion(0, Color.decode("#CD5F5F"));
        botonesAccionUsuarios.setColorOpcion(1, Color.decode("#5F7ECD"));
        botonesAccionUsuarios.setColorOpcion(2, Color.decode("#6ECD5F"));
        botonesAccionUsuarios.desactivarBoton(0);
        botonesAccionUsuarios.desactivarBoton(1);
        gbc.insets=new Insets(10, 0, 20,0);
        gbc.gridx=0;
        gbc.gridy=3;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=1;
        gbc.weighty=0;
        gbc.gridwidth=3;
        cuerpo.add(botonesAccionUsuarios,gbc);
    }

    public void cargarListaDeUsuarios(){
        usuarios=ControlUsuarios.cargarListaDeUsuarios();
        for(Usuario p: usuarios){
            agregarUsuarioATabla(p);
        }
    }
    
    public void agregarUsuarioATabla(Usuario p){
        Object[] datos=
        {
            p.getNombre(),
            p.isEstado()?"Activo":"Inactivo",
            "",
            p.getFechaRegistro().format(DateTimeFormatter.ofPattern("dd/MM/YYYY"))
        };
        tablaUsuarios.getModeloTabla().addRow(datos);
    }
    
    public void modificarUsuarioDeTabla(int fila, Usuario p){
        tablaUsuarios.getModeloTabla().setValueAt(p.getNombre(),fila ,0);
        tablaUsuarios.getModeloTabla().setValueAt(p.isEstado()?"Activo":"Inactivo",fila ,1);
    }
    
    public void eliminarUsuarioDeLaTabla(int indice){
        tablaUsuarios.getModeloTabla().removeRow(indice);
    }
    
    private void BuscarDeFiltro(String textoBusqueda){
        TableRowSorter<DefaultTableModel> sorter =sorter = new TableRowSorter<DefaultTableModel>(tablaUsuarios.getModeloTabla());
        tablaUsuarios.getTabla().setRowSorter(sorter);
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
                            tablaUsuarios.getTabla().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                        }else{
                            tablaUsuarios.getTabla().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                        }
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                break;
        }
    }
}

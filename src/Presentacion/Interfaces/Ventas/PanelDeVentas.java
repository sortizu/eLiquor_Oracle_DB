/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion.Interfaces.Ventas;

import Datos.Entidades.Departamento;
import Datos.Entidades.DetalleVenta;
import Datos.Entidades.Producto;
import Datos.Entidades.Venta;
import Negocio.ControlInventario;
import Presentacion.Interfaces.BotonRedondeado;
import Presentacion.Interfaces.Buscador;
import Presentacion.Interfaces.FramePrincipal;
import Presentacion.Interfaces.Item;
import Presentacion.Interfaces.PanelModulo;
import Presentacion.Interfaces.PanelRedondeado;
import Presentacion.Interfaces.Selector;
import Presentacion.Interfaces.TablaDefault;
import Presentacion.Interfaces.TablaItem;
import Presentacion.Interfaces.TextFieldRedondeado;
import Presentacion.Utilidades.UtilidadesFuentes;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
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
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

/**
 *
 * @author sortizu
 */
public class PanelDeVentas extends JPanel implements PropertyChangeListener{
    private PanelModulo panelModuloVentas;
    public Venta ventaActual=new Venta();
    public ArrayList<Departamento> departamentos;
    public ArrayList<Producto> productos;
    public Departamento departamentoSeleccionado;
    private PanelDeVentas panelPrincipalVentas;
    private Container parent;
    private JPanel cuerpo;
    

    private Buscador buscadorUsuario;
    
    private TablaDefault TablaListaDeVenta;
    private TablaItem TablaListaDeItems;
    //Botones de Lista de Venta
    JLabel btnSumaVenta;
    JLabel btnRestaVenta;
    JLabel btnEliminacionVenta;
    JLabel btnEdicionVenta;
    //Datos de lista de venta
    JLabel lblItem;
    JLabel lblSubtotal;
    JLabel lblDescuentos;
    JLabel lblImpuestos;
    JLabel lblTotal;
    
    //Selectores de Tabla
    private Selector selectorMostrar;
    private Selector selectorSMultiple;
    //Items recientes
    private JPanel panelItemsRecientes;
    private ArrayList<Item> itemReciente;
    private ArrayList<Producto> productosRecientes;
    //Barra de busqueda de tabla
    private Buscador buscadorItem;
    //Botones de funcion de Venta
    BotonRedondeado btnPago;
    BotonRedondeado btnClientes;
    BotonRedondeado btnCancelar;
    //Elementos del KeyPad
    Selector QtPc;
    TextFieldRedondeado montoTeclado;
    JLabel btnTeclado1;
    JLabel btnTeclado2;
    JLabel btnTeclado3;
    JLabel btnTeclado4;
    JLabel btnTeclado5;
    JLabel btnTeclado6;
    JLabel btnTeclado7;
    JLabel btnTeclado8;
    JLabel btnTeclado9;
    JLabel btnTeclado0;
    JLabel btnTeclado00;
    JLabel btnTecladoPunto;
    JLabel btnTecladoBorrar;
    JLabel btnTecladoEquis;
    JLabel btnTecladoAceptar;
    
    
    public PanelDeVentas(Container parent) {
        this.parent=parent;
        panelPrincipalVentas=this;
        iniciarComponentes();
        TablaListaDeVenta.getTabla().revalidate();
        TablaListaDeVenta.getTabla().repaint();
        //TablaListaDeItems.getTabla().revalidate();
        //TablaListaDeItems.getTabla().repaint();
    }
    private void iniciarComponentes(){
        setOpaque(false);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        panelModuloVentas=new PanelModulo(parent,"/Presentacion/Imagenes/Paneles/Boton Ayuda/TutorialVentas.png");
        panelModuloVentas.setTituloPanelModulo("V E N T A S", Color.decode("#72AD57"));
        gbc.insets = new Insets((int)(8.0/panelModuloVentas.basePanelHeight*panelModuloVentas.getPreferredSize().getHeight()), 0, 0, 0);
        gbc.gridx=0;
        gbc.gridy=0;
        add(panelModuloVentas,gbc);
        iniciarComponentesCuerpo();
        MouseAdapter limpiarSeleccion = new MouseAdapter(){
            @Override
            public void mouseReleased(MouseEvent e) {
                limpiarSeleccionTablas();
            }
        };
        addMouseListener(limpiarSeleccion);
        TablaListaDeItems.getScrollPaneTabla().addMouseListener(limpiarSeleccion);
        TablaListaDeVenta.getScrollPaneTabla().addMouseListener(limpiarSeleccion);
        cargarListaDeDepartamentos();
    }

    private void iniciarComponentesCuerpo(){
        cuerpo = panelModuloVentas.getPanelContenedorComponentes().getCuerpo();
        int width = (int)panelModuloVentas.getPreferredSize().getWidth();
        int height = (int)panelModuloVentas.getPreferredSize().getHeight();
        
        iniciarComponentesCuerpoIzquierdo(width, height);
        iniciarComponentesCuerpoDerecho(width, height);
    }
    
    private void iniciarComponentesCuerpoIzquierdo(int width, int height){
        GridBagConstraints gbc = new GridBagConstraints();
        //Agregando panel de Lista de Venta
        JPanel PanelListaDeVenta = new JPanel();
        PanelListaDeVenta.setOpaque(false);
        PanelListaDeVenta.setLayout(new GridBagLayout());
        PanelListaDeVenta.setPreferredSize(new Dimension(0, 0));
        //Creando tabla de lista de venta
        TablaListaDeVenta=new TablaDefault(new String[]{"Item","Qt","P/U","Total"}, new int[]{600,175,175,175}, panelModuloVentas);
        TablaListaDeVenta.setAltoFilaBase(75);
        TablaListaDeVenta.setPreferredSize(new Dimension(0,370));
        //Añadiendo botones de edicion para lista de venta
         JSeparator hsep1 = new JSeparator(javax.swing.SwingConstants.HORIZONTAL);
        hsep1.setPreferredSize(new Dimension(0,2));
        hsep1.setBorder(new LineBorder(Color.decode("#8C8C8C"),2));
        
        btnSumaVenta=new JLabel("+");
        btnSumaVenta.setFont(UtilidadesFuentes.InterLight.deriveFont(40.0f));
        btnSumaVenta.setForeground(Color.decode("#6EA6BE"));
        btnSumaVenta.setHorizontalAlignment(JLabel.CENTER);
        btnSumaVenta.setBorder(BorderFactory.createEmptyBorder( -5, 0, 0, 0 ));
        btnSumaVenta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //btnSuma.setPreferredSize(new Dimension(45,45));
        btnSumaVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ArrayList<DetalleVenta> detallesVenta=ventaActual.getDetallesVenta();
                int[] indicesFilaDetalleVenta=TablaListaDeVenta.getTabla().getSelectedRows();
                for(int indice=indicesFilaDetalleVenta.length-1;indice>=0;indice--){
                    DetalleVenta nuevoDetalleVenta = detallesVenta.get(indicesFilaDetalleVenta[indice]);
                    nuevoDetalleVenta.setCantidad(nuevoDetalleVenta.getCantidad()+1);
                    modificarDetalleVentaATabla(indicesFilaDetalleVenta[indice], nuevoDetalleVenta);
                }
            }
        });
        
        JSeparator vsep1 = new JSeparator(javax.swing.SwingConstants.VERTICAL);
        vsep1.setPreferredSize(new Dimension(2,0));
        vsep1.setBorder(new LineBorder(Color.decode("#8C8C8C"),2));
        
        btnRestaVenta=new JLabel("-");
        btnRestaVenta.setFont(UtilidadesFuentes.InterLight.deriveFont(50.0f));
        btnRestaVenta.setForeground(Color.decode("#D0A47C"));
        btnRestaVenta.setHorizontalAlignment(JLabel.CENTER);
        btnRestaVenta.setBorder(BorderFactory.createEmptyBorder( -3, 0, 0, 0 ));
        btnRestaVenta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //btnSuma.setPreferredSize(new Dimension(45,45));
        btnRestaVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                 ArrayList<DetalleVenta> detallesVenta=ventaActual.getDetallesVenta();
                int[] indicesFilaDetalleVenta=TablaListaDeVenta.getTabla().getSelectedRows();
                for(int indice=indicesFilaDetalleVenta.length-1;indice>=0;indice--){
                    DetalleVenta nuevoDetalleVenta = detallesVenta.get(indicesFilaDetalleVenta[indice]);
                    if(nuevoDetalleVenta.getCantidad()>0){
                        nuevoDetalleVenta.setCantidad(nuevoDetalleVenta.getCantidad()-1);
                        modificarDetalleVentaATabla(indicesFilaDetalleVenta[indice], nuevoDetalleVenta);
                    }
                }
            }
        });
        
        JSeparator vsep2 = new JSeparator(javax.swing.SwingConstants.VERTICAL);
        vsep2.setPreferredSize(new Dimension(2,0));
        vsep2.setBorder(new LineBorder(Color.decode("#8C8C8C"),2));
        
        btnEliminacionVenta=new JLabel("×");
        btnEliminacionVenta.setFont(UtilidadesFuentes.InterLight.deriveFont(40.0f));
        btnEliminacionVenta.setForeground(Color.decode("#BC7474"));
        btnEliminacionVenta.setHorizontalAlignment(JLabel.CENTER);
        btnEliminacionVenta.setBorder(BorderFactory.createEmptyBorder( -5, 0, 0, 0 ));
        btnEliminacionVenta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //btnSuma.setPreferredSize(new Dimension(45,45));
        btnEliminacionVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ArrayList<DetalleVenta> detallesVenta=ventaActual.getDetallesVenta();
                int[] indicesFilaDetalleVenta=TablaListaDeVenta.getTabla().getSelectedRows();
                for(int indice=indicesFilaDetalleVenta.length-1;indice>=0;indice--){
                    detallesVenta.remove(indicesFilaDetalleVenta[indice]);
                    eliminarDetalleVentaEnTabla(indicesFilaDetalleVenta[indice]);
                }
            }
        });
        
        JSeparator vsep3 = new JSeparator(javax.swing.SwingConstants.VERTICAL);
        vsep3.setPreferredSize(new Dimension(2,0));
        vsep3.setBorder(new LineBorder(Color.decode("#8C8C8C"),2));
        
        JSeparator vsep4 = new JSeparator(javax.swing.SwingConstants.VERTICAL);
        vsep4.setPreferredSize(new Dimension(2,0));
        vsep4.setBorder(new LineBorder(Color.decode("#8C8C8C"),2));
        
        btnEdicionVenta=new JLabel("...");
        btnEdicionVenta.setFont(UtilidadesFuentes.InterLight.deriveFont(40.0f));
        btnEdicionVenta.setForeground(Color.decode("#999999"));
        btnEdicionVenta.setHorizontalAlignment(JLabel.CENTER);
        btnEdicionVenta.setBorder(BorderFactory.createEmptyBorder( -23, 0, 0, 0 ));
        btnEdicionVenta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEdicionVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                int filaSeleccionada = TablaListaDeVenta.getTabla().getSelectedRow();
                if(filaSeleccionada>=0){
                    EditarItem editarItem = new EditarItem(filaSeleccionada,panelPrincipalVentas);
                    ((FramePrincipal) SwingUtilities.getWindowAncestor(panelPrincipalVentas)).mostrarPanelEmergente(editarItem);
                    editarItem.requestFocus();
                }
            }
        });
        
        JSeparator hsep2 = new JSeparator(javax.swing.SwingConstants.HORIZONTAL);
        hsep2.setPreferredSize(new Dimension(0,2));
        hsep2.setBorder(new LineBorder(Color.decode("#8C8C8C"),2));
        
        lblItem=new JLabel(String.format("<html><body style='text-align: center;'># ITEMS<br>%d</body></html>",0));
        lblItem.setFont(UtilidadesFuentes.InterLight.deriveFont(25.0f));
        lblItem.setForeground(Color.decode("#8C8C8C"));
        lblItem.setHorizontalAlignment(JLabel.CENTER);
        lblItem.setPreferredSize(new Dimension(0,0));
        
        lblSubtotal=new JLabel(String.format("<html><body style='text-align: center;'>SUBTOTAL<br>S/. %.2f</body></html>", Double.valueOf(0)));
        lblSubtotal.setFont(UtilidadesFuentes.InterLight.deriveFont(25.0f));
        lblSubtotal.setForeground(Color.decode("#8C8C8C"));
        lblSubtotal.setHorizontalAlignment(JLabel.CENTER);
        lblSubtotal.setPreferredSize(new Dimension(0,0));
        
        lblDescuentos=new JLabel(String.format("<html><body style='text-align: center;'>DESCUENTOS<br>S/. %.2f</body></html>",Double.valueOf(0)));
        lblDescuentos.setFont(UtilidadesFuentes.InterLight.deriveFont(25.0f));
        lblDescuentos.setForeground(Color.decode("#8C8C8C"));
        lblDescuentos.setHorizontalAlignment(JLabel.CENTER);
        lblDescuentos.setPreferredSize(new Dimension(0,0));
        
        lblImpuestos=new JLabel(String.format("<html><body style='text-align: center;'>IMPUESTOS<br>S/. %.2f</body></html>",Double.valueOf(0)));
        lblImpuestos.setFont(UtilidadesFuentes.InterLight.deriveFont(25.0f));
        lblImpuestos.setForeground(Color.decode("#8C8C8C"));
        lblImpuestos.setHorizontalAlignment(JLabel.CENTER);
        lblImpuestos.setPreferredSize(new Dimension(0,0));
        
        lblTotal=new JLabel(String.format("<html><body style='text-align: center;'>TOTAL<br>S/. %.2f</body></html>",Double.valueOf(0)));
        lblTotal.setFont(UtilidadesFuentes.InterLight.deriveFont(35.0f));
        lblTotal.setForeground(Color.decode("#8C8C8C"));
        lblTotal.setHorizontalAlignment(JLabel.CENTER);
        lblTotal.setPreferredSize(new Dimension(0,0));
        
        //Agregando componentes al panel
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1.0;
        gbc.weighty=0;
        PanelListaDeVenta.add(TablaListaDeVenta,gbc);
        
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1.0;
        gbc.weighty=0;
        PanelListaDeVenta.add(hsep1,gbc);
        //Panel de botones de Lista de venta
        JPanel panelBtnListaVenta = new JPanel();
        panelBtnListaVenta.setOpaque(false);
        panelBtnListaVenta.setPreferredSize(new Dimension(0,40));
        panelBtnListaVenta.setLayout(new GridBagLayout());
        
        
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=0.15;
        gbc.weighty=1.0;
        panelBtnListaVenta.add(btnSumaVenta,gbc);
        
        gbc.gridx=1;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.VERTICAL;
        gbc.weightx=0;
        gbc.weighty=1.0;
        panelBtnListaVenta.add(vsep1,gbc);
        
        
        gbc.gridx=2;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=0.15;
        gbc.weighty=1.0;
        panelBtnListaVenta.add(btnRestaVenta,gbc);
        
        gbc.gridx=3;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.VERTICAL;
        gbc.weightx=0;
        gbc.weighty=1.0;
        panelBtnListaVenta.add(vsep2,gbc);
        
        gbc.gridx=4;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=0.15;
        gbc.weighty=1.0;
        panelBtnListaVenta.add(btnEliminacionVenta,gbc);
        
        
        gbc.gridx=5;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.VERTICAL;
        gbc.weightx=0;
        gbc.weighty=1.0;
        panelBtnListaVenta.add(vsep3,gbc);
        
        gbc.gridx=6;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=0.4;
        gbc.weighty=1.0;
        panelBtnListaVenta.add(new JPanel(){
            @Override
            public void setOpaque(boolean isOpaque) {
                super.setOpaque(false); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
            }
        },gbc);
        
        gbc.gridx=7;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.VERTICAL;
        gbc.weightx=0;
        gbc.weighty=1.0;
        panelBtnListaVenta.add(vsep4,gbc);
        
        gbc.gridx=8;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=0.15;
        gbc.weighty=1.0;
        panelBtnListaVenta.add(btnEdicionVenta,gbc);
        
        
        gbc.gridx=0;
        gbc.gridy=2;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1.0;
        gbc.weighty=0;
        PanelListaDeVenta.add(panelBtnListaVenta,gbc);
        
        
        gbc.gridx=0;
        gbc.gridy=3;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1.0;
        gbc.weighty=0;
        PanelListaDeVenta.add(hsep2,gbc);
        
        //Agregando panel de Datos de lista de venta
        JPanel panelDatos = new JPanel();
        panelDatos.setPreferredSize(new Dimension(0,0));
        panelDatos.setOpaque(false);
        panelDatos.setLayout(new GridBagLayout());
        
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=1.0;
        gbc.weighty=1.0;
        panelDatos.add(lblItem,gbc);
        
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=1.0;
        gbc.weighty=1.0;
        panelDatos.add(lblSubtotal,gbc);
        
        gbc.gridx=1;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=1.0;
        gbc.weighty=1.0;
        panelDatos.add(lblDescuentos,gbc);
        
        gbc.gridx=1;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=1.0;
        gbc.weighty=1.0;
        panelDatos.add(lblImpuestos,gbc);
        
        gbc.gridx=2;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=1.0;
        gbc.weighty=1.0;
        gbc.gridheight=2;
        panelDatos.add(lblTotal,gbc);
        
        gbc.gridx=0;
        gbc.gridy=4;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=1.0;
        gbc.weighty=1;
        gbc.gridheight=1;
        PanelListaDeVenta.add(panelDatos,gbc);

        //Agregando paneles de Lista de Venta y Busqueda de venta
        gbc.insets=new Insets(25, 37, 25, 10);
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=0.43;
        gbc.weighty=1.0;
        cuerpo.add(PanelListaDeVenta,gbc);
        
    }
    
    private void iniciarComponentesCuerpoDerecho(int width, int height){
        GridBagConstraints gbc = new GridBagConstraints();
        
        //Agregando panel de busqueda de venta
        JPanel PanelBusquedaVenta = new JPanel();
        PanelBusquedaVenta.setOpaque(false);
        PanelBusquedaVenta.setPreferredSize(new Dimension(0, 0));
        PanelBusquedaVenta.setLayout(new GridBagLayout());
        //Agregando el mostrador de items al panel de Busqueda Venta
        JPanel mostradorDeItems=new JPanel();
        //mostradorDeItems.setOpaque(false);
        mostradorDeItems.setOpaque(false);
        mostradorDeItems.setPreferredSize(new Dimension(0, 0));
        mostradorDeItems.setLayout(new GridBagLayout());
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=1;
        gbc.weighty=1;
        PanelBusquedaVenta.add(mostradorDeItems,gbc);
        //Componentes del mostrador de Items
        //Agregando el panel de Tabla de Items
        PanelRedondeado panelTablaItems=new PanelRedondeado(20,3,new Color(0,0,0,0),Color.decode("#8C8C8C"));
        panelTablaItems.setOpaque(false);
        panelTablaItems.setPreferredSize(new Dimension(560, 0));
        panelTablaItems.setMinimumSize(new Dimension(560, 0));
        panelTablaItems.setLayout(new GridBagLayout());
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.VERTICAL;
        gbc.weightx=0;
        gbc.weighty=1;
        gbc.gridheight=2;
        mostradorDeItems.add(panelTablaItems,gbc);
        //Componentes del panel de tabla de items
        //Agregando buscador de items para tabla
        buscadorItem=new Buscador();
        buscadorItem.getTxtABuscar().setColumns(0);
        buscadorItem.getTxtABuscar().setFont(UtilidadesFuentes.InterRegular.deriveFont(20.0f));
        buscadorItem.setMinimumSize(new Dimension(410,37));
        buscadorItem.getTxtABuscar().setForeground(Color.decode("#8C8C8C"));
        
        buscadorItem.getTxtABuscar().addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                BuscarDeFiltro(buscadorItem.getTxtABuscar().getText());
            }
            
        });
        gbc.insets=new Insets(10, 10, 7, 10);
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        gbc.weighty=0;
        gbc.gridheight=1;
        panelTablaItems.add(buscadorItem,gbc);
        //agregando Tabla de Lista de Items
        TablaListaDeItems=new TablaItem(4, 5, 5, panelModuloVentas);
        TablaListaDeItems.setMinimumSize(new Dimension(410,0));
         ListSelectionListener listenerTabla = new ListSelectionListener(){
        public void valueChanged(ListSelectionEvent event) {
                int[] columnas = TablaListaDeItems.getTabla().getSelectedColumns();
                int[] filas  = TablaListaDeItems.getTabla().getSelectedRows();
                for(int i=0; i< TablaListaDeItems.getTabla().getColumnCount();i++){
                    for(int j=0; j< TablaListaDeItems.getTabla().getRowCount();j++){
                        Item item = (Item)TablaListaDeItems.getTabla().getValueAt(j, i);
                        if(item!=null){
                            item.DeseleccionarItem();
                            TablaListaDeItems.getTabla().setValueAt(item, j, i);
                        }
                    }
                }
                for(int columna: columnas){
                    for(int fila: filas){
                        Item item = (Item)TablaListaDeItems.getTabla().getValueAt(fila, columna);
                        if(item!=null){
                            if(!item.getSeleccionado()){
                                item.seleccionarItem();
                                TablaListaDeItems.getTabla().setValueAt(item, fila, columna);
                            }
                        }
                    }
                }
        }
        };
        TablaListaDeItems.getTabla().getSelectionModel().addListSelectionListener(listenerTabla);
        TablaListaDeItems.getTabla().getColumnModel().getSelectionModel().addListSelectionListener(listenerTabla);
        TablaListaDeItems.getTabla().addMouseListener(new MouseAdapter(){
            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.getClickCount()==2){
                    if(selectorMostrar.getOpcionSeleccionada()==0){
                        int fila=TablaListaDeItems.getTabla().getSelectedRow();
                        int columna=TablaListaDeItems.getTabla().getSelectedColumn();
                        if(selectorMostrar.getOpcionSeleccionada()==0){
                            try {
                                obtenerSeleccionDepartamento();
                            } catch (Exception er) {}
                        }
                    }else{
                        agregarProductoDeTabla();
                    }
                }
            }
        });
        TablaListaDeItems.mostrarCabecera("DEPARTAMENTOS");
        TablaListaDeItems.getLblTitulo().setFont(TablaListaDeItems.getLblTitulo().getFont().deriveFont(20.0f));
        gbc.insets=new Insets(0, 10, 10, 10);
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.VERTICAL;
        gbc.weightx=0;
        gbc.weighty=0;
        panelTablaItems.add(TablaListaDeItems,gbc);
        //Agregando selectores al panel mostrador de items
        JSeparator separador = new JSeparator(javax.swing.SwingConstants.VERTICAL);
        separador.setPreferredSize(new Dimension(panelTablaItems.getGrosorDeBorde(),0));
        separador.setBorder(new LineBorder(panelTablaItems.getColorBorde(),panelTablaItems.getGrosorDeBorde()));
        gbc.insets=new Insets(0, 0, 0, 0);
        gbc.gridx=1;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.VERTICAL;
        gbc.gridheight=2;
        gbc.weightx=0;
        gbc.weighty=1;
        panelTablaItems.add(separador,gbc);
        
        JPanel panelSelectoresTabla=new JPanel();
        panelSelectoresTabla.setPreferredSize(new Dimension(0,0));
        panelSelectoresTabla.setOpaque(false);
        panelSelectoresTabla.setLayout(new GridBagLayout());
        
        gbc.gridx=2;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridheight=3;
        gbc.weightx=1;
        gbc.weighty=1;
        panelTablaItems.add(panelSelectoresTabla,gbc);
        
        JLabel lblSelectorMostrar=new JLabel("Mostrar:");
        lblSelectorMostrar.setFont(UtilidadesFuentes.InterLight.deriveFont(15.0f));
        lblSelectorMostrar.setForeground(Color.decode("#8C8C8C"));
        lblSelectorMostrar.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.NONE;
        gbc.gridheight=1;
        gbc.weightx=1;
        gbc.weighty=0;
        panelSelectoresTabla.add(lblSelectorMostrar,gbc);
        
        selectorMostrar=new Selector(new String[]{"Dpto","Prod"},50,37);
        selectorMostrar.setMinimumSize(new Dimension(100,37));
        selectorMostrar.setFuenteDeOpcion(UtilidadesFuentes.InterRegular.deriveFont(13.0f));
        selectorMostrar.setColorDeFuente(Color.decode("#8C8C8C"));
        selectorMostrar.solicitarSeleccion(0);
        selectorMostrar.addColorDeOpcion(Color.decode("#f94144"));
        selectorMostrar.addColorDeOpcion(Color.decode("#f8961e"));
        selectorMostrar.setNombreDeSelector("SMostrar");
        selectorMostrar.addPropertyChangeListener(this);
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.gridheight=1;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=1;
        gbc.weighty=0;
        panelSelectoresTabla.add(selectorMostrar,gbc);
        
        JLabel lblSelectorSMultiple=new JLabel("<html><body style='text-align: center;'>Selección<br>Múltiple:</body></html>");
        lblSelectorSMultiple.setFont(UtilidadesFuentes.InterLight.deriveFont(15.0f));
        lblSelectorSMultiple.setForeground(Color.decode("#8C8C8C"));
        lblSelectorSMultiple.setHorizontalAlignment(JLabel.CENTER);
        gbc.insets=new Insets(10, 0, 0, 0);
        gbc.gridx=0;
        gbc.gridy=2;
        gbc.fill=GridBagConstraints.NONE;
        gbc.gridheight=1;
        gbc.weightx=1;
        gbc.weighty=0;
        panelSelectoresTabla.add(lblSelectorSMultiple,gbc);
        
        selectorSMultiple=new Selector(new String[]{"SI","NO"},50,37);
        selectorSMultiple.setMinimumSize(new Dimension(100,37));
        selectorSMultiple.setFuenteDeOpcion(UtilidadesFuentes.InterRegular.deriveFont(13.0f));
        selectorSMultiple.setColorDeFuente(Color.decode("#8C8C8C"));
        selectorSMultiple.solicitarSeleccion(0);
        selectorSMultiple.addColorDeOpcion(Color.decode("#72AD57"));
        selectorSMultiple.addColorDeOpcion(Color.decode("#AD5757"));
        selectorSMultiple.setNombreDeSelector("SMultiple");
        selectorSMultiple.addPropertyChangeListener(this);
        gbc.insets=new Insets(0, 0, 0, 0);
        gbc.gridx=0;
        gbc.gridy=3;
        gbc.gridheight=1;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=1;
        gbc.weighty=0;
        panelSelectoresTabla.add(selectorSMultiple,gbc);
        
        
        //Agregando el panel de Items recientes
        panelItemsRecientes=new JPanel();
        panelItemsRecientes.setOpaque(false);
        panelItemsRecientes.setLayout(new GridBagLayout());
        gbc.insets=new Insets(0, 0, 0, 0);
        gbc.gridx=2;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=1;
        gbc.weighty=1;
        mostradorDeItems.add(panelItemsRecientes,gbc);
        //Componentes de panelItemsRecientes
        JLabel lblRecientes=new JLabel("RECIENTES");
        lblRecientes.setFont(UtilidadesFuentes.InterLight.deriveFont(20.0f));
        lblRecientes.setForeground(Color.decode("#8C8C8C"));
        lblRecientes.setHorizontalAlignment(JLabel.CENTER);
        gbc.anchor=GridBagConstraints.CENTER;
        gbc.gridx=2;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.NONE;
        gbc.gridheight=1;
        gbc.weightx=1;
        gbc.weighty=0;
        mostradorDeItems.add(lblRecientes,gbc);
        
        itemReciente=new ArrayList<Item>();
        productosRecientes=new ArrayList<Producto>();
        
        ////Agregando el panel de Funciones Venta al panel de Busqueda Venta
        JPanel funcionesVenta = new JPanel();
        funcionesVenta.setPreferredSize(new Dimension(0, 0));
        funcionesVenta.setOpaque(false);
        funcionesVenta.setLayout(new GridBagLayout());
        gbc.insets=new Insets(10, 0, 0, 0);
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=1;
        gbc.weighty=1;
        PanelBusquedaVenta.add(funcionesVenta,gbc);
        //Agregando el panel de los botones de venta
        JPanel PanelBotonesVenta = new JPanel();
        PanelBotonesVenta.setPreferredSize(new Dimension(0, 0));
        PanelBotonesVenta.setOpaque(false);
        PanelBotonesVenta.setLayout(new GridBagLayout());
        gbc.insets=new Insets(0, 0, 0, 10);
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=0.45;
        gbc.weighty=1;
        funcionesVenta.add(PanelBotonesVenta,gbc);
        //Componentes del panel de botones de venta
        //Boton Cliente
        btnClientes=new BotonRedondeado(10, 3, Color.decode("#D9AA4F"),"CLIENTES",UtilidadesFuentes.InterRegular.deriveFont(17.0f)){
            @Override
            public void botonPresionado() {
                DefinirCliente definirCliente = new DefinirCliente(panelPrincipalVentas);
                ((FramePrincipal) SwingUtilities.getWindowAncestor(panelPrincipalVentas)).mostrarPanelEmergente(definirCliente);
                definirCliente.requestFocus();
            }
        };
        gbc.insets=new Insets(0, 0, 10, 10);
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=0.5;
        gbc.weighty=1;
        PanelBotonesVenta.add(btnClientes,gbc);
        //Boton Cancelar
        btnCancelar=new BotonRedondeado(10, 3, Color.decode("#EB7979"),"CANCELAR",UtilidadesFuentes.InterRegular.deriveFont(17.0f)){
            @Override
            public void botonPresionado() {
                TablaListaDeVenta.getModeloTabla().setRowCount(0);
                ventaActual.getDetallesVenta().clear();
                actualizarDatosVenta();
            }
        };
        gbc.insets=new Insets(0, 0, 0, 10);
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=0.5;
        gbc.weighty=1;
        PanelBotonesVenta.add(btnCancelar,gbc);
        btnPago=new BotonRedondeado(10, 3, Color.decode("#8CC560"),"PAGO",UtilidadesFuentes.InterRegular.deriveFont(17.0f)){
            @Override
            public void botonPresionado() {
                VentaPago1 ventaPago1 = new VentaPago1(panelPrincipalVentas,ventaActual);
                ((FramePrincipal) SwingUtilities.getWindowAncestor(panelPrincipalVentas)).mostrarPanelEmergente(ventaPago1);
                ventaPago1.requestFocus();
            }
        };
        gbc.gridx=1;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.gridheight=2;
        gbc.weightx=1;
        gbc.weighty=1;
        PanelBotonesVenta.add(btnPago,gbc);
        //Agregando el panel de teclado
        JPanel PanelTeclado = new JPanel();
        PanelTeclado.setPreferredSize(new Dimension(0, 0));
        PanelTeclado.setOpaque(false);
        PanelTeclado.setLayout(new GridBagLayout());
        gbc.gridx=1;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.gridheight=1;
        gbc.weightx=0.55;
        gbc.weighty=1;
        funcionesVenta.add(PanelTeclado,gbc);
        //Componentes de Teclado
        JPanel PanelMontoTeclado = new JPanel();
        PanelMontoTeclado.setPreferredSize(new Dimension (0,0));
        PanelMontoTeclado.setOpaque(false);
        PanelMontoTeclado.setLayout(new GridBagLayout());
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.gridheight=1;
        gbc.weightx=1;
        gbc.weighty=0.15;
        PanelTeclado.add(PanelMontoTeclado,gbc);
        QtPc=new Selector(new String[]{"QT","PC"},50,44);
        QtPc.setFuenteDeOpcion(UtilidadesFuentes.InterRegular.deriveFont(18.0f));
        QtPc.setColorDeFuente(Color.decode("#8C8C8C"));
        QtPc.solicitarSeleccion(0);
        QtPc.addColorDeOpcion(Color.decode("#577590"));
        QtPc.addColorDeOpcion(Color.decode("#43aa8b"));
        QtPc.setMinimumSize(new Dimension(100,44));
        gbc.insets=new Insets(0, 0, 0, 5);
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.NONE;
        gbc.gridheight=1;
        gbc.weightx=0;
        gbc.weighty=0;
        PanelMontoTeclado.add(QtPc,gbc);
        
        montoTeclado = new TextFieldRedondeado(0);
        montoTeclado.setGrosorBorde(3);
        montoTeclado.setRadioDeBorde(40);
        montoTeclado.setColorBorde(Color.decode("#8C8C8C"));
        montoTeclado.setFont(UtilidadesFuentes.InterBlack.deriveFont(30.0f));
        montoTeclado.setForeground(Color.decode("#8C8C8C"));
        montoTeclado.setHorizontalAlignment(JLabel.RIGHT);
        montoTeclado.setBorder( BorderFactory.createEmptyBorder(2, 20, 0, 20) );
        montoTeclado.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                JComponent component = evt.getComponent();
		component.requestFocusInWindow();
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        montoTeclado.setText("");
        montoTeclado.setCaretPosition(montoTeclado.getText().length());
        gbc.insets=new Insets(0, 0, 0, 0);
        gbc.gridx=1;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=1;
        gbc.weighty=1;
        PanelMontoTeclado.add(montoTeclado,gbc);
        //Formato de Teclado
        PlainDocument documentMontoTeclado = (PlainDocument) montoTeclado.getDocument();
        documentMontoTeclado.setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if(text.matches("[0-9[.]]+")||text.isEmpty()){
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
        
        //Panel de Botones
        JPanel PanelBotonesTeclado = new JPanel();
        PanelBotonesTeclado.setPreferredSize(new Dimension (0,0));
        PanelBotonesTeclado.setOpaque(false);
        PanelBotonesTeclado.setLayout(new GridBagLayout());
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.gridheight=1;
        gbc.weightx=1;
        gbc.weighty=0.85;
        PanelTeclado.add(PanelBotonesTeclado,gbc);
        
        PanelRedondeado panelDigitos=new PanelRedondeado
        (20,0,Color.decode("#EFEFEF"),Color.decode("#EFEFEF"));
        panelDigitos.setOpaque(false);
        panelDigitos.setPreferredSize(new Dimension (0,0));
        panelDigitos.setLayout(new GridBagLayout());
        gbc.insets=new Insets(10, 0, 0, 0);
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.gridheight=1;
        gbc.weightx=0.8;
        gbc.weighty=1;
        gbc.gridheight=3;
        PanelBotonesTeclado.add(panelDigitos,gbc);
        //Botones de digitos
        //Creando listener de los botones
        MouseAdapter listenerBotonNumericoTeclado = new MouseAdapter(){
            @Override
            public void mouseReleased(MouseEvent e) {
                String valorTeclado = ((JLabel)e.getSource()).getText();
                String valorMonto = montoTeclado.getText();
                String nuevoMonto="";
                int posicionInicioSeleccion=montoTeclado.getSelectionStart();
                int posicionFinSeleccion=montoTeclado.getSelectionEnd();
                
                String primeraParte = valorMonto.substring(0,posicionInicioSeleccion);
                String ultimaParte= valorMonto.substring(posicionFinSeleccion,valorMonto.length());
                
                if(valorTeclado.equals(".")){
                    primeraParte=primeraParte.replace(".", "");
                    ultimaParte=ultimaParte.replace(".", "");
                }
                nuevoMonto += primeraParte;
                nuevoMonto += valorTeclado;
                nuevoMonto += ultimaParte;
                montoTeclado.setText(nuevoMonto);
                montoTeclado.setCaretPosition((primeraParte.length()+valorTeclado.length()));
            }
        };
        
        MouseAdapter listenerBotonAccionTeclado = new MouseAdapter(){
            @Override
            public void mouseReleased(MouseEvent e) {
                String valorTeclado = ((JLabel)e.getSource()).getText();
                int posicionInicioSeleccion=montoTeclado.getSelectionStart();
                int posicionFinSeleccion=montoTeclado.getSelectionEnd();
                switch(valorTeclado){
                    case "⟵": //Borrar
                        String valorMonto = montoTeclado.getText();
                        String nuevoMonto="";
                        if(posicionFinSeleccion-posicionInicioSeleccion==0){
                            if(posicionInicioSeleccion>0){
                                nuevoMonto += valorMonto.substring(0,posicionInicioSeleccion-1);
                                nuevoMonto += valorMonto.substring(posicionFinSeleccion,valorMonto.length());
                                montoTeclado.setText(nuevoMonto);
                                montoTeclado.setCaretPosition(posicionInicioSeleccion-1);
                            }
                        }else{
                            nuevoMonto += valorMonto.substring(0,posicionInicioSeleccion);
                            nuevoMonto += valorMonto.substring(posicionFinSeleccion,valorMonto.length());
                            montoTeclado.setText(nuevoMonto);
                            montoTeclado.setCaretPosition(posicionInicioSeleccion);
                        }
                        
                        break;
                    case "×":
                        montoTeclado.setText("");
                        break;
                    case "✓": //Aceptar
                        //Revisando los productos elegidos
                        agregarProductoDeTabla();
                        break;
                }
            }
        };
        //Agregando Boton 1
        btnTeclado1=new JLabel("1",JLabel.CENTER);
        btnTeclado1.setFont(UtilidadesFuentes.InterBlack.deriveFont(30.0f));
        btnTeclado1.setForeground(Color.decode("#AEAEAE"));
        btnTeclado1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnTeclado1.addMouseListener(listenerBotonNumericoTeclado);
        gbc.insets=new Insets(0, 0, 0, 0);
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.gridheight=1;
        gbc.weightx=1;
        gbc.weighty=1;
        panelDigitos.add(btnTeclado1,gbc);
        //Agregando Boton 2
        btnTeclado2=new JLabel("2",JLabel.CENTER);
        btnTeclado2.setFont(UtilidadesFuentes.InterBlack.deriveFont(30.0f));
        btnTeclado2.setForeground(Color.decode("#AEAEAE"));
        btnTeclado2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnTeclado2.addMouseListener(listenerBotonNumericoTeclado);
        gbc.gridx=1;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.gridheight=1;
        gbc.weightx=1;
        gbc.weighty=1;
        panelDigitos.add(btnTeclado2,gbc);
        //Agregando Boton 3
        btnTeclado3=new JLabel("3",JLabel.CENTER);
        btnTeclado3.setFont(UtilidadesFuentes.InterBlack.deriveFont(30.0f));
        btnTeclado3.setForeground(Color.decode("#AEAEAE"));
        btnTeclado3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnTeclado3.addMouseListener(listenerBotonNumericoTeclado);
        gbc.gridx=2;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.gridheight=1;
        gbc.weightx=1;
        gbc.weighty=1;
        panelDigitos.add(btnTeclado3,gbc);
        //Agregando Boton 0
        btnTeclado0=new JLabel("0",JLabel.CENTER);
        btnTeclado0.setFont(UtilidadesFuentes.InterBlack.deriveFont(30.0f));
        btnTeclado0.setForeground(Color.decode("#AEAEAE"));
        btnTeclado0.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnTeclado0.addMouseListener(listenerBotonNumericoTeclado);
        gbc.gridx=3;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.gridheight=1;
        gbc.weightx=1;
        gbc.weighty=1;
        panelDigitos.add(btnTeclado0,gbc);
        //Agregando Boton 4
        btnTeclado4=new JLabel("4",JLabel.CENTER);
        btnTeclado4.setFont(UtilidadesFuentes.InterBlack.deriveFont(30.0f));
        btnTeclado4.setForeground(Color.decode("#AEAEAE"));
        btnTeclado4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnTeclado4.addMouseListener(listenerBotonNumericoTeclado);
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.gridheight=1;
        gbc.weightx=1;
        gbc.weighty=1;
        panelDigitos.add(btnTeclado4,gbc);
        //Agregando Boton 5
        btnTeclado5=new JLabel("5",JLabel.CENTER);
        btnTeclado5.setFont(UtilidadesFuentes.InterBlack.deriveFont(30.0f));
        btnTeclado5.setForeground(Color.decode("#AEAEAE"));
        btnTeclado5.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnTeclado5.addMouseListener(listenerBotonNumericoTeclado);
        gbc.gridx=1;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.gridheight=1;
        gbc.weightx=1;
        gbc.weighty=1;
        panelDigitos.add(btnTeclado5,gbc);
        //Agregando Boton 6
        btnTeclado6=new JLabel("6",JLabel.CENTER);
        btnTeclado6.setFont(UtilidadesFuentes.InterBlack.deriveFont(30.0f));
        btnTeclado6.setForeground(Color.decode("#AEAEAE"));
        btnTeclado6.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnTeclado6.addMouseListener(listenerBotonNumericoTeclado);
        gbc.gridx=2;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.gridheight=1;
        gbc.weightx=1;
        gbc.weighty=1;
        panelDigitos.add(btnTeclado6,gbc);
        //Agregando Boton 00
        btnTeclado00=new JLabel("00",JLabel.CENTER);
        btnTeclado00.setFont(UtilidadesFuentes.InterBlack.deriveFont(30.0f));
        btnTeclado00.setForeground(Color.decode("#AEAEAE"));
        btnTeclado00.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnTeclado00.addMouseListener(listenerBotonNumericoTeclado);
        gbc.gridx=3;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.gridheight=1;
        gbc.weightx=1;
        gbc.weighty=1;
        panelDigitos.add(btnTeclado00,gbc);
        //Agregando Boton 7
        btnTeclado7=new JLabel("7",JLabel.CENTER);
        btnTeclado7.setFont(UtilidadesFuentes.InterBlack.deriveFont(30.0f));
        btnTeclado7.setForeground(Color.decode("#AEAEAE"));
        btnTeclado7.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnTeclado7.addMouseListener(listenerBotonNumericoTeclado);
        gbc.gridx=0;
        gbc.gridy=2;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.gridheight=1;
        gbc.weightx=1;
        gbc.weighty=1;
        panelDigitos.add(btnTeclado7,gbc);
        //Agregando Boton 8
        btnTeclado8=new JLabel("8",JLabel.CENTER);
        btnTeclado8.setFont(UtilidadesFuentes.InterBlack.deriveFont(30.0f));
        btnTeclado8.setForeground(Color.decode("#AEAEAE"));
        btnTeclado8.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnTeclado8.addMouseListener(listenerBotonNumericoTeclado);
        gbc.gridx=1;
        gbc.gridy=2;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.gridheight=1;
        gbc.weightx=1;
        gbc.weighty=1;
        panelDigitos.add(btnTeclado8,gbc);
        //Agregando Boton 9
        btnTeclado9=new JLabel("9",JLabel.CENTER);
        btnTeclado9.setFont(UtilidadesFuentes.InterBlack.deriveFont(30.0f));
        btnTeclado9.setForeground(Color.decode("#AEAEAE"));
        btnTeclado9.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnTeclado9.addMouseListener(listenerBotonNumericoTeclado);
        gbc.gridx=2;
        gbc.gridy=2;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.gridheight=1;
        gbc.weightx=1;
        gbc.weighty=1;
        panelDigitos.add(btnTeclado9,gbc);
        //Agregando Boton .
        btnTecladoPunto=new JLabel(".",JLabel.CENTER);
        btnTecladoPunto.setFont(UtilidadesFuentes.InterBlack.deriveFont(30.0f));
        btnTecladoPunto.setForeground(Color.decode("#AEAEAE"));
        btnTecladoPunto.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnTecladoPunto.addMouseListener(listenerBotonNumericoTeclado);
        gbc.gridx=3;
        gbc.gridy=2;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.gridheight=1;
        gbc.weightx=1;
        gbc.weighty=1;
        panelDigitos.add(btnTecladoPunto,gbc);
        
        //Botones de Control Teclado
        //Boton Borrar
        btnTecladoBorrar=new JLabel("⟵");
        btnTecladoBorrar.setFont(UtilidadesFuentes.InterBlack.deriveFont(30.0f));
        btnTecladoBorrar.setForeground(Color.decode("#AEAEAE"));
        btnTecladoBorrar.setHorizontalAlignment(JLabel.CENTER);
        btnTecladoBorrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnTecladoBorrar.addMouseListener(listenerBotonAccionTeclado);
        gbc.insets=new Insets(10, 0, 0, 0);
        gbc.gridx=1;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.gridheight=1;
        gbc.weightx=0.2;
        gbc.weighty=1;
        PanelBotonesTeclado.add(btnTecladoBorrar,gbc);
        //Boton X
        btnTecladoEquis=new JLabel("×");
        btnTecladoEquis.setFont(UtilidadesFuentes.InterBlack.deriveFont(30.0f));
        btnTecladoEquis.setForeground(Color.decode("#AEAEAE"));
        btnTecladoEquis.setHorizontalAlignment(JLabel.CENTER);
        btnTecladoEquis.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnTecladoEquis.addMouseListener(listenerBotonAccionTeclado);
        gbc.insets=new Insets(0, 0, 0, 0);
        gbc.gridx=1;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.gridheight=1;
        gbc.weightx=0.2;
        gbc.weighty=1;
        PanelBotonesTeclado.add(btnTecladoEquis,gbc);
        //Boton Aceptar
        btnTecladoAceptar=new JLabel("✓");
        btnTecladoAceptar.setFont(UtilidadesFuentes.InterBlack.deriveFont(30.0f));
        btnTecladoAceptar.setForeground(Color.decode("#6CC564"));
        btnTecladoAceptar.setHorizontalAlignment(JLabel.CENTER);
        btnTecladoAceptar.setBorder(BorderFactory.createEmptyBorder( 0, 0, 0, 0 ));
        btnTecladoAceptar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnTecladoAceptar.addMouseListener(listenerBotonAccionTeclado);
        gbc.insets=new Insets(0, 0, 0, 0);
        gbc.gridx=1;
        gbc.gridy=2;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.gridheight=1;
        gbc.weightx=0.2;
        gbc.weighty=1;
        PanelBotonesTeclado.add(btnTecladoAceptar,gbc);
        //Agregando panel de Busqueda de venta
        gbc.insets=new Insets(25, 10, 25, 37);
        gbc.gridx=1;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=0.57;
        gbc.weighty=1.0;
        cuerpo.add(PanelBusquedaVenta,gbc);
    }
    
    
    public void cargarListaDeDepartamentos(){
        departamentos=ControlInventario.cargarDepartamentos();
        mostrarListaDepartamentosCargadaEnTabla();
    }
    
    public void cargarListaDeProductos(int idDepartamento){
        productos=ControlInventario.cargarProductosCaja(idDepartamento);
        mostrarListaProductosCargadaEnTabla();
    }
    
    public void mostrarListaDepartamentosCargadaEnTabla(){
        TablaListaDeItems.limpiarTabla();
        TablaListaDeItems.getCursorTabla()[0]=0;
        TablaListaDeItems.getCursorTabla()[1]=0;
        for(Departamento departamento: departamentos){
            agregarDepartamentoATabla(departamento);
        }
    }
    
    public void mostrarListaProductosCargadaEnTabla(){
        TablaListaDeItems.limpiarTabla();
        TablaListaDeItems.getCursorTabla()[0]=0;
        TablaListaDeItems.getCursorTabla()[1]=0;
        for(Producto producto: productos){
            agregarProductoATabla(producto);
        }
    }
    
    public void agregarDepartamentoATabla(Departamento departamento){
        Item item = new Item(departamento.getNombre(), (departamento.getCantidad()+" ITEMS"));
        item.getNombre().setFont(item.getNombre().getFont().deriveFont(14f));
        item.getSubtitulo().setFont(item.getSubtitulo().getFont().deriveFont(14f));
       TablaListaDeItems.agregarItem(item);
    }
    
    public void agregarProductoATabla(Producto producto){
        Item item = new Item(producto.getNombre(),"STOCK: "+producto.getStock());
        item.getNombre().setFont(item.getNombre().getFont().deriveFont(14f));
        item.getSubtitulo().setFont(item.getSubtitulo().getFont().deriveFont(14f));
        TablaListaDeItems.agregarItem(item);
    }
    
    
    public void modificarProductoEnTabla(int[] indice, Producto producto){
        indice[0]=TablaListaDeItems.getTabla().convertRowIndexToView(indice[0]);
        indice[1]=TablaListaDeItems.getTabla().convertColumnIndexToView(indice[1]);
        Item item = (Item)TablaListaDeItems.getTabla().getValueAt(indice[0], indice[1]);
        item.getNombre().setText(producto.getNombre());
        item.getSubtitulo().setText("STOCK: "+producto.getStock());
        TablaListaDeItems.modificarItem(indice,item);
    }
    
    public void añadirItemReciente(Producto producto){
        Item nuevoItemReciente=new Item(producto.getNombre(),"STOCK: "+producto.getStock());
        nuevoItemReciente.getNombre().setFont(UtilidadesFuentes.InterLight.deriveFont(14.0f));
        nuevoItemReciente.getSubtitulo().setFont(UtilidadesFuentes.InterLight.deriveFont(14.0f));
        nuevoItemReciente.setPreferredSize(new Dimension(88,88));
        nuevoItemReciente.setMinimumSize(new Dimension(88,88));
        nuevoItemReciente.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseReleased(MouseEvent e) {
                limpiarSeleccionTablas();
                deseleccionarItemRecientes();
                if(e.getClickCount()==1){
                    ((Item)e.getSource()).seleccionarItem();
                }else if(e.getClickCount()==2){
                    limpiarSeleccionTablas();
                    Producto producto = productosRecientes.get(itemReciente.indexOf(((Item)e.getSource())));
                    if(!estaProductoEnLista(producto)){
                        DetalleVenta nuevoDetalleVenta = new DetalleVenta(producto,0,producto.getPrecio(),0);
                        ventaActual.getDetallesVenta().add(nuevoDetalleVenta);
                        añadirDetalleVentaATabla(nuevoDetalleVenta);
                    }
                }
                actualizarPanelItemRecientes();
            }

        });
        itemReciente.add(0,nuevoItemReciente);
        actualizarPanelItemRecientes();
    }
    
    public void actualizarPanelItemRecientes(){
        panelItemsRecientes.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor=GridBagConstraints.CENTER;
        gbc.gridx=0;
        gbc.gridy=0;
        for(int i =0;i<itemReciente.size();i++){
            Item item=itemReciente.get(i);
            gbc.gridy=i;
            gbc.fill=GridBagConstraints.NONE;
            gbc.weightx=1;
            gbc.weighty=0;
            gbc.gridheight=1;
            gbc.gridwidth=1;
            if(i==itemReciente.size()-1){
                gbc.anchor=GridBagConstraints.PAGE_START;
                gbc.weighty=1;
            }
            panelItemsRecientes.add(item,gbc);
        }
        panelItemsRecientes.revalidate();
        panelItemsRecientes.repaint();
    }
    
    private void deseleccionarItemRecientes(){
        for(Item item:itemReciente){
            item.DeseleccionarItem();
        }
        actualizarPanelItemRecientes();
    }
    
    public void añadirDetalleVentaATabla(DetalleVenta nuevoDetalleVenta){
        TablaListaDeVenta.getModeloTabla().addRow(new Object[]{nuevoDetalleVenta.getProducto().getNombre(),nuevoDetalleVenta.getCantidad(),nuevoDetalleVenta.getPrecio(),nuevoDetalleVenta.getSubTotal()});
        actualizarDatosVenta();
    }
    
    public void modificarDetalleVentaATabla(int fila, DetalleVenta nuevoDetalleVenta){
        TablaListaDeVenta.getModeloTabla().setValueAt(nuevoDetalleVenta.getProducto().getNombre(), fila, 0);
        TablaListaDeVenta.getModeloTabla().setValueAt(nuevoDetalleVenta.getCantidad(), fila, 1);
        TablaListaDeVenta.getModeloTabla().setValueAt(nuevoDetalleVenta.getPrecio(), fila, 2);
        TablaListaDeVenta.getModeloTabla().setValueAt(nuevoDetalleVenta.getSubTotal(), fila, 3);
        actualizarDatosVenta();
    }
    
    public void eliminarDetalleVentaEnTabla(int fila){
        TablaListaDeVenta.getModeloTabla().removeRow(fila);
        actualizarDatosVenta();
    }
   
    public void actualizarDatosVenta(){
        ArrayList<DetalleVenta> detalleVentas = ventaActual.getDetallesVenta();
        double totalSubtotal=0;
        double totalDescuento=0;
        double totalImpuestos=0;
        double Total=0;
        for(DetalleVenta dv:detalleVentas){
            totalSubtotal+=dv.getSubTotal();
            totalDescuento+=dv.getDescuento();
            totalImpuestos+=dv.getImpuestos();
            Total+=dv.getTotal();
        }
        lblItem.setText(String.format("<html><body style='text-align: center;'># ITEMS<br>%d</body></html>",detalleVentas.size()));
        lblSubtotal.setText(String.format("<html><body style='text-align: center;'>SUBTOTAL<br>S/. %.2f</body></html>", totalSubtotal));
        lblDescuentos.setText(String.format("<html><body style='text-align: center;'>DESCUENTOS<br>S/. %.2f</body></html>",totalDescuento));
        lblImpuestos.setText(String.format("<html><body style='text-align: center;'>IMPUESTOS<br>S/. %.2f</body></html>",totalImpuestos));
        lblTotal.setText(String.format("<html><body style='text-align: center;'>TOTAL<br>S/. %.2f</body></html>",Total));
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
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(TablaListaDeItems.getModeloTabla());
        TablaListaDeItems.getTabla().setRowSorter(sorter);
        sorter.setRowFilter(filtro);
    }
    
    private void limpiarSeleccionTablas(){
        TablaListaDeItems.getTabla().clearSelection();
        TablaListaDeVenta.getTabla().clearSelection();
    }
    
    public boolean estaProductoEnLista(Producto producto){
        for(DetalleVenta detalleVenta:ventaActual.getDetallesVenta()){
            if(detalleVenta.getProducto().getIdProducto()==producto.getIdProducto()){
                return true;
            }
        }
        return false;
    }

    public void reiniciarBusqueda(){
        buscadorItem.getTxtABuscar().setText("");
        TablaListaDeItems.getTabla().setRowSorter(null);
    }
    
    private void obtenerSeleccionDepartamento(){
        departamentoSeleccionado = departamentos.get(TablaListaDeItems.getTabla().convertRowIndexToModel(TablaListaDeItems.getTabla().getSelectedRow())*TablaListaDeItems.getColumnas()+TablaListaDeItems.getTabla().convertColumnIndexToModel(TablaListaDeItems.getTabla().getSelectedColumn()));
        TablaListaDeItems.mostrarCabecera("PRODUCTOS DE \""+departamentoSeleccionado.getNombre()+"\"");
        selectorMostrar.solicitarSeleccion(1);
    }
    
    private void agregarProductoDeTabla(){
        int [] indicesSeleccionFila=TablaListaDeItems.getTabla().getSelectedRows();
                        int [] indicesSeleccionColumna=TablaListaDeItems.getTabla().getSelectedColumns();
                        for(int i = 0;i<indicesSeleccionFila.length;i++){
                            indicesSeleccionFila[i]=TablaListaDeItems.getTabla().convertRowIndexToModel(indicesSeleccionFila[i]);
                        }
                        for(int i = 0;i<indicesSeleccionColumna.length;i++){
                            indicesSeleccionColumna[i]=TablaListaDeItems.getTabla().convertColumnIndexToModel(indicesSeleccionColumna[i]);
                        }
                        ArrayList<Producto> productosAAgregar=new ArrayList<Producto>();
                        //Revisando por productos de la tabla
                        if(selectorMostrar.getOpcionSeleccionada()==1){
                            for(int columna: indicesSeleccionColumna){
                            for(int fila: indicesSeleccionFila){
                                try {
                                    Producto productoAAgregar = productos.get(fila*TablaListaDeItems.getColumnas()+columna);
                                    productosAAgregar.add(productoAAgregar);
                                } catch (Exception er) {}}
                            }
                        }
                        //Revisando por items recientes
                        for(int i = 0; i<itemReciente.size();i++){
                            if(itemReciente.get(i).getSeleccionado()){
                                Producto productoAAgregar = productosRecientes.get(i);
                                productosAAgregar.add(productoAAgregar);
                            }
                        }
                        //Agregando items elegidos a la lista
                        for(Producto producto: productosAAgregar){
                            if(!estaProductoEnLista(producto)){
                                int cantidad=0;
                                double precio = producto.getPrecio();
                                if(QtPc.getOpcionSeleccionada()==0){
                                    try {
                                        cantidad = (int)Double.parseDouble(montoTeclado.getText());
                                    } catch (Exception er) {
                                        cantidad=0;
                                    }
                                }else{
                                    try {
                                        precio = Double.parseDouble(montoTeclado.getText());
                                    } catch (Exception er) {
                                        precio=0;
                                    }
                                }
                                DetalleVenta nuevoDetalleVenta = new DetalleVenta(producto,cantidad,precio,0);
                                ventaActual.getDetallesVenta().add(nuevoDetalleVenta);
                                añadirDetalleVentaATabla(nuevoDetalleVenta);
                                if(productosRecientes.size()==3){
                                    productosRecientes.remove(2);
                                    itemReciente.remove(2);
                                }
                                productosRecientes.add(0,producto);
                                añadirItemReciente(producto);
                            }
                        }
                        limpiarSeleccionTablas();
                        deseleccionarItemRecientes();
    }

    public TablaDefault getTablaListaDeVenta() {
        return TablaListaDeVenta;
    }

    public void setTablaListaDeVenta(TablaDefault TablaListaDeVenta) {
        this.TablaListaDeVenta = TablaListaDeVenta;
    }

    public TablaItem getTablaListaDeItems() {
        return TablaListaDeItems;
    }

    public void setTablaListaDeItems(TablaItem TablaListaDeItems) {
        this.TablaListaDeItems = TablaListaDeItems;
    }

    public Selector getSelectorMostrar() {
        return selectorMostrar;
    }

    public void setSelectorMostrar(Selector selectorMostrar) {
        this.selectorMostrar = selectorMostrar;
    }

    public Selector getSelectorSMultiple() {
        return selectorSMultiple;
    }

    public void setSelectorSMultiple(Selector selectorSMultiple) {
        this.selectorSMultiple = selectorSMultiple;
    }

    public Selector getQtPc() {
        return QtPc;
    }

    public void setQtPc(Selector QtPc) {
        this.QtPc = QtPc;
    }
    
    
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Selector selectorModificado=((Selector)evt.getSource());
        String tipoSelector=selectorModificado.getNombreDeSelector();
        switch (tipoSelector) {
            case "SMostrar":
                if((int)evt.getNewValue()==1){
                    int idDepartamento=-1;
                    if(departamentoSeleccionado==null){
                            TablaListaDeItems.mostrarCabecera("TODOS LOS PRODUCTOS");
                    }else{
                        idDepartamento = departamentoSeleccionado.getIdDepartamento();
                    }
                    cargarListaDeProductos(idDepartamento);
                }else{
                    TablaListaDeItems.mostrarCabecera("DEPARTAMENTOS");
                    departamentoSeleccionado=null;
                    productos=null;
                    cargarListaDeDepartamentos();
                    mostrarListaDepartamentosCargadaEnTabla();
                    
                }
                break;
            case "SMultiple":
                try {
                        if((int)evt.getNewValue()==0){
                            TablaListaDeItems.getTabla().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                        }else{
                            TablaListaDeItems.getTabla().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                        }
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                break;
        }
        reiniciarBusqueda();
    }
}

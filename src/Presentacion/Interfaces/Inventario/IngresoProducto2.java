package Presentacion.Interfaces.Inventario;

import Datos.Entidades.Entrega;
import Datos.Entidades.Proveedor;
import Negocio.ControlInventario;
import Negocio.ControlProveedores;
import Presentacion.Interfaces.Buscador;
import Presentacion.Interfaces.FramePrincipal;
import Presentacion.Interfaces.ScrollBarCustom;
import Presentacion.Interfaces.VentanaEmergente;
import Presentacion.Utilidades.UtilidadesFuentes;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author sortizu
 */
public class IngresoProducto2 extends VentanaEmergente{
    private PanelDeInventario panelPrincipalDeModuloDeInventario;
    private Buscador buscadorProveedor;
    private JTable ListaDeProveedores;
    private DefaultTableModel modeloDeListaDeProveedores;
    private JScrollPane scrollPaneDeListaDeProveedores;
    private ScrollBarCustom scrollCustom;
    private ArrayList<Proveedor> proveedores;
    private Entrega nuevaEntrega;
    
    public IngresoProducto2(PanelDeInventario panelPrincipalDeModuloDeInventario,Entrega nuevaEntrega) {
        super("/Presentacion/Imagenes/Paneles/Inventario/PanelIngresoProducto2.png");
        this.panelPrincipalDeModuloDeInventario=panelPrincipalDeModuloDeInventario;
        cambiarDisposicionDePanelDeBotones(VentanaEmergente.ULTIMAVENTANA);
        proveedores=ControlProveedores.cargarListaDeProveedores();
        this.nuevaEntrega=nuevaEntrega;
        desactivarBotonAceptar();
        setTextoTitulo("INGRESO");
        setColorTitulo(Color.decode("#D9AA4F"));
        JPanel cuerpo = getCuerpo();
        cuerpo.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets=new Insets(5, 0, 10, 0);
        
        JLabel lblProveedor=new JLabel("Seleccione un proveedor");
        lblProveedor.setFont(UtilidadesFuentes.InterLight.deriveFont(20.0f));
        lblProveedor.setForeground(Color.decode("#8C8C8C"));
        lblProveedor.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1.0;
        cuerpo.add(lblProveedor,gbc);
        
        buscadorProveedor=new Buscador();
        buscadorProveedor.setPreferredSize(new Dimension(375,37));
        buscadorProveedor.getTxtABuscar().setFont(UtilidadesFuentes.InterRegular.deriveFont(20.0f));
        buscadorProveedor.getTxtABuscar().setForeground(Color.decode("#8C8C8C"));
        buscadorProveedor.setPreferredSize(new Dimension(375,37));
        buscadorProveedor.getTxtABuscar().addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscarEnTabla(buscadorProveedor.getTxtABuscar().getText());
            }
        });
        gbc.insets=new Insets(0,0,0,0);
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.CENTER;
        gbc.weightx=0;
        gbc.weighty=0;
        cuerpo.add(buscadorProveedor,gbc);
        
        scrollPaneDeListaDeProveedores=new JScrollPane();
        scrollPaneDeListaDeProveedores.setPreferredSize(new Dimension(400,0));
        scrollPaneDeListaDeProveedores.setBorder(BorderFactory.createEmptyBorder());
        scrollPaneDeListaDeProveedores.getViewport().setBackground(Color.WHITE);
        scrollPaneDeListaDeProveedores.setOpaque(false);
        
        ListaDeProveedores=new JTable();
        ListaDeProveedores.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                double newScrollBarHeight=Math.pow(scrollPaneDeListaDeProveedores.getHeight(),2)/(
                ListaDeProveedores.getRowHeight()*ListaDeProveedores.getRowCount());
        scrollCustom.setThumbSize((int)newScrollBarHeight);
                scrollPaneDeListaDeProveedores.setVerticalScrollBar(scrollCustom);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        ListaDeProveedores.getTableHeader().setUI(null);
        ListaDeProveedores.setAutoscrolls(false);
        ListaDeProveedores.setFocusable(false);
        ListaDeProveedores.setOpaque(false);
        ListaDeProveedores.setRequestFocusEnabled(false);
        ListaDeProveedores.setRowHeight(45);
        ListaDeProveedores.setRowSelectionAllowed(true);
        ListaDeProveedores.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ListaDeProveedores.setSelectionBackground(Color.decode("#23A020"));
        ListaDeProveedores.setSelectionForeground(Color.white);
        ListaDeProveedores.setShowGrid(false);
        ListaDeProveedores.getTableHeader().setResizingAllowed(false);
        ListaDeProveedores.getTableHeader().setReorderingAllowed(false);
        ListaDeProveedores.setUpdateSelectionOnSort(false);
        
        modeloDeListaDeProveedores=(DefaultTableModel)ListaDeProveedores.getModel();
        ListaDeProveedores.setFont(UtilidadesFuentes.InterExtraLight.deriveFont(25.0f));
        ListaDeProveedores.setForeground(Color.decode("#8C8C8C"));
        ListaDeProveedores.setIntercellSpacing(new Dimension(0,0));
        ((DefaultTableCellRenderer)ListaDeProveedores.getDefaultRenderer(String.class)).setHorizontalAlignment(JLabel.CENTER);
        modeloDeListaDeProveedores.addColumn(new String("nombre"));
        
        scrollPaneDeListaDeProveedores.setViewportView(ListaDeProveedores);
        
        ListaDeProveedores.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(ListaDeProveedores.getSelectedRows().length>0){
                    activarBotonAceptar();
                }else{
                    desactivarBotonAceptar();
                }
            }
        });
        
        scrollCustom=new ScrollBarCustom();
        scrollCustom.setUnitIncrement(16);
        scrollPaneDeListaDeProveedores.setVerticalScrollBar(scrollCustom);
        
        gbc.gridx=0;
        gbc.gridy=2;
        gbc.fill=GridBagConstraints.VERTICAL;
        gbc.weightx=0;
        gbc.weighty=1.0;
        cuerpo.add(scrollPaneDeListaDeProveedores,gbc);
        
        for(Proveedor p:proveedores){
            modeloDeListaDeProveedores.addRow(new Object[]{p.getRazonSocial()});
        }
    }

    private void buscarEnTabla(String textoBusqueda){
        TableRowSorter<DefaultTableModel> sorter =sorter = new TableRowSorter<DefaultTableModel>(modeloDeListaDeProveedores);
        ListaDeProveedores.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter("(?i)"+textoBusqueda));
    }

    @Override
    public void btnAceptarPresionado(MouseEvent evt) {
        if(!getBtnAceptar().getForeground().equals(Color.WHITE)){
            Proveedor p = proveedores.get(ListaDeProveedores.convertRowIndexToModel(ListaDeProveedores.getSelectedRow()));
            nuevaEntrega.setProveedor(p);
            nuevaEntrega.getItem().setStock(nuevaEntrega.getItem().getStock()+nuevaEntrega.getCantidad());
            ControlInventario.registrarEntrega(nuevaEntrega);
            if(panelPrincipalDeModuloDeInventario.departamentoActual!=null){
                panelPrincipalDeModuloDeInventario.cargarListaDeProductos(panelPrincipalDeModuloDeInventario.departamentoActual.getIdDepartamento());
            }else{
                    panelPrincipalDeModuloDeInventario.cargarListaDeProductos(-1);
            }
            panelPrincipalDeModuloDeInventario.reiniciarBusqueda();
            ((FramePrincipal)((JFrame) SwingUtilities.getWindowAncestor(this))).cerrarPanelesEmergentes();
        }
    }

    @Override
    public void btnCancelarPresionado(MouseEvent evt) {
        ((FramePrincipal)((JFrame) SwingUtilities.getWindowAncestor(this))).cerrarPanelesEmergentes();
    }

    @Override
    public void btnAtrasPresionado(MouseEvent evt) {
        ((FramePrincipal)((JFrame) SwingUtilities.getWindowAncestor(this))).cerrarPanelesEmergentes();
         IngresoProducto1 ingresoProducto1 = new IngresoProducto1(panelPrincipalDeModuloDeInventario,nuevaEntrega);
         ((FramePrincipal) SwingUtilities.getWindowAncestor(panelPrincipalDeModuloDeInventario)).mostrarPanelEmergente(ingresoProducto1);
         ingresoProducto1.requestFocus();
    }
    
    private void desactivarBotonAceptar(){
        getBtnAceptar().setCursor(null);
        getBtnAceptar().setForeground(Color.WHITE);
        getBtnAceptar().revalidate();
        getBtnAceptar().repaint();
    }
    
    private void activarBotonAceptar(){
        getBtnAceptar().setCursor(new Cursor(Cursor.HAND_CURSOR));
        getBtnAceptar().setForeground(Color.decode("#6CC564"));
        getBtnAceptar().revalidate();
        getBtnAceptar().repaint();
    }
}

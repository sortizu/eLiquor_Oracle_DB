package Presentacion.Interfaces.Ventas;

import Datos.Entidades.Cliente;
import Datos.Entidades.Entrega;
import Datos.Entidades.Proveedor;
import Negocio.ControlClientes;
import Negocio.ControlVentas;
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
public class DefinirCliente extends VentanaEmergente{
    private PanelDeVentas panelPrincipalDeModuloDeVentas;
    private Buscador buscadorCliente;
    private JTable ListaDeClientes;
    private DefaultTableModel modeloDeListaDeClientes;
    private JScrollPane scrollPaneDeListaDeClientes;
    private ScrollBarCustom scrollCustom;
    private ArrayList<Cliente> clientes;
    private Entrega nuevaEntrega;
    
    public DefinirCliente(PanelDeVentas panelPrincipalDeModuloDeVentas) {
        super("/Presentacion/Imagenes/Paneles/Inventario/PanelIngresoProducto2.png");
        this.panelPrincipalDeModuloDeVentas=panelPrincipalDeModuloDeVentas;
        cambiarDisposicionDePanelDeBotones(VentanaEmergente.BASICO);
        clientes=ControlClientes.cargarListaDeClientes();
        this.nuevaEntrega=nuevaEntrega;
        desactivarBotonAceptar();
        setTextoTitulo("SELECCIONAR CLIENTE");
        setColorTitulo(Color.decode("#D9AA4F"));
        JPanel cuerpo = getCuerpo();
        cuerpo.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets=new Insets(5, 0, 10, 0);
        
        JLabel lblProveedor=new JLabel("Seleccione un cliente");
        lblProveedor.setFont(UtilidadesFuentes.InterLight.deriveFont(20.0f));
        lblProveedor.setForeground(Color.decode("#8C8C8C"));
        lblProveedor.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1.0;
        cuerpo.add(lblProveedor,gbc);
        
        buscadorCliente=new Buscador();
        buscadorCliente.setPreferredSize(new Dimension(375,37));
        buscadorCliente.getTxtABuscar().setFont(UtilidadesFuentes.InterRegular.deriveFont(20.0f));
        buscadorCliente.getTxtABuscar().setForeground(Color.decode("#8C8C8C"));
        buscadorCliente.setPreferredSize(new Dimension(375,37));
        buscadorCliente.getTxtABuscar().addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscarEnTabla(buscadorCliente.getTxtABuscar().getText());
            }
        });
        gbc.insets=new Insets(0,0,0,0);
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.CENTER;
        gbc.weightx=0;
        gbc.weighty=0;
        cuerpo.add(buscadorCliente,gbc);
        
        scrollPaneDeListaDeClientes=new JScrollPane();
        scrollPaneDeListaDeClientes.setPreferredSize(new Dimension(400,0));
        scrollPaneDeListaDeClientes.setBorder(BorderFactory.createEmptyBorder());
        scrollPaneDeListaDeClientes.getViewport().setBackground(Color.WHITE);
        scrollPaneDeListaDeClientes.setOpaque(false);
        
        ListaDeClientes=new JTable();
        ListaDeClientes.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                double newScrollBarHeight=Math.pow(scrollPaneDeListaDeClientes.getHeight(),2)/(
                ListaDeClientes.getRowHeight()*ListaDeClientes.getRowCount());
        scrollCustom.setThumbSize((int)newScrollBarHeight);
                scrollPaneDeListaDeClientes.setVerticalScrollBar(scrollCustom);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        ListaDeClientes.getTableHeader().setUI(null);
        ListaDeClientes.setAutoscrolls(false);
        ListaDeClientes.setFocusable(false);
        ListaDeClientes.setOpaque(false);
        ListaDeClientes.setRequestFocusEnabled(false);
        ListaDeClientes.setRowHeight(45);
        ListaDeClientes.setRowSelectionAllowed(true);
        ListaDeClientes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ListaDeClientes.setSelectionBackground(Color.decode("#23A020"));
        ListaDeClientes.setSelectionForeground(Color.white);
        ListaDeClientes.setShowGrid(false);
        ListaDeClientes.getTableHeader().setResizingAllowed(false);
        ListaDeClientes.getTableHeader().setReorderingAllowed(false);
        ListaDeClientes.setUpdateSelectionOnSort(false);
        
        modeloDeListaDeClientes=(DefaultTableModel)ListaDeClientes.getModel();
        ListaDeClientes.setFont(UtilidadesFuentes.InterExtraLight.deriveFont(25.0f));
        ListaDeClientes.setForeground(Color.decode("#8C8C8C"));
        ListaDeClientes.setIntercellSpacing(new Dimension(0,0));
        ((DefaultTableCellRenderer)ListaDeClientes.getDefaultRenderer(String.class)).setHorizontalAlignment(JLabel.CENTER);
        modeloDeListaDeClientes.addColumn(new String("nombre"));
        
        scrollPaneDeListaDeClientes.setViewportView(ListaDeClientes);
        
        ListaDeClientes.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(ListaDeClientes.getSelectedRows().length>0){
                    activarBotonAceptar();
                }else{
                    desactivarBotonAceptar();
                }
            }
        });
        
        scrollCustom=new ScrollBarCustom();
        scrollCustom.setUnitIncrement(16);
        scrollPaneDeListaDeClientes.setVerticalScrollBar(scrollCustom);
        
        gbc.gridx=0;
        gbc.gridy=2;
        gbc.fill=GridBagConstraints.VERTICAL;
        gbc.weightx=0;
        gbc.weighty=1.0;
        cuerpo.add(scrollPaneDeListaDeClientes,gbc);
        
        for(Cliente p:clientes){
            modeloDeListaDeClientes.addRow(new Object[]{p.getNombre()});
        }
        if(panelPrincipalDeModuloDeVentas.ventaActual.getIdCliente()>0){
            for(int i =0;i<clientes.size();i++){
                if(clientes.get(i).getIdCliente()==panelPrincipalDeModuloDeVentas.ventaActual.getIdCliente()){
                    ListaDeClientes.setRowSelectionInterval(i, i);
                }
            }
        }
    }

    private void buscarEnTabla(String textoBusqueda){
        TableRowSorter<DefaultTableModel> sorter =sorter = new TableRowSorter<DefaultTableModel>(modeloDeListaDeClientes);
        ListaDeClientes.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter("(?i)"+textoBusqueda));
    }

    @Override
    public void btnAceptarPresionado(MouseEvent evt) {
        if(!getBtnAceptar().getForeground().equals(Color.WHITE)){
            Cliente c = clientes.get(ListaDeClientes.convertRowIndexToModel(ListaDeClientes.getSelectedRow()));
            panelPrincipalDeModuloDeVentas.ventaActual.setIdCliente(c.getIdCliente());
            panelPrincipalDeModuloDeVentas.reiniciarBusqueda();
            ((FramePrincipal)((JFrame) SwingUtilities.getWindowAncestor(this))).cerrarPanelesEmergentes();
        }
    }

    @Override
    public void btnCancelarPresionado(MouseEvent evt) {
        ((FramePrincipal)((JFrame) SwingUtilities.getWindowAncestor(this))).cerrarPanelesEmergentes();
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

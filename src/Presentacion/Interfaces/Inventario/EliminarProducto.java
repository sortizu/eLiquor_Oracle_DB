package Presentacion.Interfaces.Inventario;

import Datos.Entidades.Producto;
import Negocio.ControlInventario;
import Presentacion.Interfaces.FramePrincipal;
import Presentacion.Interfaces.ScrollBarCustom;
import Presentacion.Interfaces.VentanaEmergente;
import Presentacion.Utilidades.UtilidadesFuentes;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sortizu
 */
public class EliminarProducto extends VentanaEmergente{
    private int filasSeleccionadas[];
    private int columnasSeleccionadas[];
    private PanelDeInventario panelPrincipalDeModuloDeInventario;
    private JTable ListaDeProductosAEliminar;
    private DefaultTableModel modeloListaDeProductosAEliminar;
    private JScrollPane scrollPaneDeListaDeProductos;
    private ScrollBarCustom scrollCustom;
    private ArrayList<Producto> productosABorrar = new ArrayList<Producto>();
    
    public EliminarProducto(PanelDeInventario panelPrincipalDeModuloDeInventario, int filasSeleccionadas[],int columnasSeleccionadas[]) {
        super("/Presentacion/Imagenes/Paneles/PanelEliminacion.png");
        this.panelPrincipalDeModuloDeInventario=panelPrincipalDeModuloDeInventario;
        this.filasSeleccionadas=filasSeleccionadas;
        this.columnasSeleccionadas=columnasSeleccionadas;
        
        setTextoTitulo("CONFIRMACIÓN");
        setColorTitulo(Color.decode("#CD5F5F"));
        JPanel cuerpo = getCuerpo();
        cuerpo.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets=new Insets(10, 0, 0, 0);
        
        JLabel lblConfirmacion=new JLabel("Se eliminarán los siguientes departamentos:");
        lblConfirmacion.setFont(UtilidadesFuentes.InterLight.deriveFont(20.0f));
        lblConfirmacion.setForeground(Color.decode("#8C8C8C"));
        lblConfirmacion.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1.0;
        cuerpo.add(lblConfirmacion,gbc);
        
        scrollPaneDeListaDeProductos=new JScrollPane();
        scrollPaneDeListaDeProductos.setPreferredSize(new Dimension(400,0));
        scrollPaneDeListaDeProductos.setBorder(BorderFactory.createEmptyBorder());
        scrollPaneDeListaDeProductos.getViewport().setBackground(Color.WHITE);
        scrollPaneDeListaDeProductos.setOpaque(false);
        
        ListaDeProductosAEliminar=new JTable();
        ListaDeProductosAEliminar.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                double newScrollBarHeight=Math.pow(scrollPaneDeListaDeProductos.getHeight(),2)/(
                ListaDeProductosAEliminar.getRowHeight()*ListaDeProductosAEliminar.getRowCount());
        scrollCustom.setThumbSize((int)newScrollBarHeight);
                scrollPaneDeListaDeProductos.setVerticalScrollBar(scrollCustom);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        ListaDeProductosAEliminar.getTableHeader().setUI(null);
        ListaDeProductosAEliminar.setAutoscrolls(false);
        ListaDeProductosAEliminar.setFocusable(false);
        ListaDeProductosAEliminar.setOpaque(false);
        ListaDeProductosAEliminar.setRequestFocusEnabled(false);
        ListaDeProductosAEliminar.setRowHeight(45);
        ListaDeProductosAEliminar.setRowSelectionAllowed(false);
        ListaDeProductosAEliminar.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ListaDeProductosAEliminar.setShowGrid(false);
        ListaDeProductosAEliminar.getTableHeader().setResizingAllowed(false);
        ListaDeProductosAEliminar.getTableHeader().setReorderingAllowed(false);
        ListaDeProductosAEliminar.setUpdateSelectionOnSort(false);
        
        modeloListaDeProductosAEliminar=(DefaultTableModel)ListaDeProductosAEliminar.getModel();
        ListaDeProductosAEliminar.setFont(UtilidadesFuentes.InterExtraLight.deriveFont(25.0f));
        ListaDeProductosAEliminar.setForeground(Color.decode("#8C8C8C"));
        ListaDeProductosAEliminar.setIntercellSpacing(new Dimension(0,0));
        ((DefaultTableCellRenderer)ListaDeProductosAEliminar.getDefaultRenderer(String.class)).setHorizontalAlignment(JLabel.CENTER);
        modeloListaDeProductosAEliminar.addColumn(new String("nombre"));
        
        scrollPaneDeListaDeProductos.setViewportView(ListaDeProductosAEliminar);
        
        
        scrollCustom=new ScrollBarCustom();
        scrollCustom.setUnitIncrement(16);
        scrollPaneDeListaDeProductos.setVerticalScrollBar(scrollCustom);
        
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.VERTICAL;
        gbc.weightx=0;
        gbc.weighty=1.0;
        cuerpo.add(scrollPaneDeListaDeProductos,gbc);
        
        for(int columna: columnasSeleccionadas){
            for(int fila: filasSeleccionadas){
                /*Desconozco parte del funcionamiento de las tablas. Por lo que a la hora de seleccionar los departamentos 
                a eliminar no soy capaz de evitar que celdas sin informacion sean seleccionadas, lo que daria error en este
                procedimiento. Es por ello que uso este try catch. Es una solucion muy desprolija, lamentablemente no encontre
                el tiempo para hallar una solucion mejor.
                */
                try {
                    Producto productoABorrar = panelPrincipalDeModuloDeInventario.productos.get(fila*panelPrincipalDeModuloDeInventario.getTablaInventario().getColumnas()+columna);
                    productosABorrar.add(productoABorrar);
                    modeloListaDeProductosAEliminar.addRow(new Object[]{productoABorrar.getNombre()});
                } catch (Exception e) {}
            }
        }
        
    }
    
    @Override
    public void btnAceptarPresionado(MouseEvent evt) {
        panelPrincipalDeModuloDeInventario.productos.removeAll(productosABorrar);
        ControlInventario.eliminarProductos(productosABorrar);
        panelPrincipalDeModuloDeInventario.mostrarListaProductosCargadaEnTabla();
        ((FramePrincipal)((JFrame) SwingUtilities.getWindowAncestor(this))).cerrarPanelesEmergentes();
        panelPrincipalDeModuloDeInventario.reiniciarBusqueda();
    }

    @Override
    public void btnCancelarPresionado(MouseEvent evt) {
        ((FramePrincipal)((JFrame) SwingUtilities.getWindowAncestor(this))).cerrarPanelesEmergentes();
    }
}

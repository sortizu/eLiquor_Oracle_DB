package Presentacion.Interfaces.Proveedores;

import Datos.Entidades.Proveedor;
import Negocio.ControlProveedores;
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
public class EliminarProveedores extends VentanaEmergente{
    private PanelDeProveedores panelPrincipalDeModuloDeProveedores;
    private JTable ListaDeProveedoresAEliminar;
    private DefaultTableModel modeloListaDeProveedoresAEliminar;
    private JScrollPane scrollPaneDeListaDeProveedores;
    private ScrollBarCustom scrollCustom;
    private ArrayList<Proveedor> proveedoresABorrar = new ArrayList<Proveedor>();
    int [] filasSeleccionadas;
    
    public EliminarProveedores(PanelDeProveedores panelPrincipalDeModuloDeProveedores, int filasSeleccionadas[]) {
        super("/Presentacion/Imagenes/Paneles/PanelEliminacion.png");
        this.panelPrincipalDeModuloDeProveedores=panelPrincipalDeModuloDeProveedores;
        this.filasSeleccionadas=filasSeleccionadas;
        
        setTextoTitulo("CONFIRMACIÓN");
        setColorTitulo(Color.decode("#CD5F5F"));
        JPanel cuerpo = getCuerpo();
        cuerpo.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets=new Insets(10, 0, 0, 0);
        
        JLabel lblConfirmacion=new JLabel("Se eliminarán los siguientes proveedores:");
        lblConfirmacion.setFont(UtilidadesFuentes.InterLight.deriveFont(20.0f));
        lblConfirmacion.setForeground(Color.decode("#8C8C8C"));
        lblConfirmacion.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1.0;
        cuerpo.add(lblConfirmacion,gbc);
        
        scrollPaneDeListaDeProveedores=new JScrollPane();
        scrollPaneDeListaDeProveedores.setPreferredSize(new Dimension(400,0));
        scrollPaneDeListaDeProveedores.setBorder(BorderFactory.createEmptyBorder());
        scrollPaneDeListaDeProveedores.getViewport().setBackground(Color.WHITE);
        scrollPaneDeListaDeProveedores.setOpaque(false);
        
        ListaDeProveedoresAEliminar=new JTable();
        ListaDeProveedoresAEliminar.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                double newScrollBarHeight=Math.pow(scrollPaneDeListaDeProveedores.getHeight(),2)/(
                ListaDeProveedoresAEliminar.getRowHeight()*ListaDeProveedoresAEliminar.getRowCount());
        scrollCustom.setThumbSize((int)newScrollBarHeight);
                scrollPaneDeListaDeProveedores.setVerticalScrollBar(scrollCustom);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        ListaDeProveedoresAEliminar.getTableHeader().setUI(null);
        ListaDeProveedoresAEliminar.setAutoscrolls(false);
        ListaDeProveedoresAEliminar.setFocusable(false);
        ListaDeProveedoresAEliminar.setOpaque(false);
        ListaDeProveedoresAEliminar.setRequestFocusEnabled(false);
        ListaDeProveedoresAEliminar.setRowHeight(45);
        ListaDeProveedoresAEliminar.setRowSelectionAllowed(false);
        ListaDeProveedoresAEliminar.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ListaDeProveedoresAEliminar.setShowGrid(false);
        ListaDeProveedoresAEliminar.getTableHeader().setResizingAllowed(false);
        ListaDeProveedoresAEliminar.getTableHeader().setReorderingAllowed(false);
        ListaDeProveedoresAEliminar.setUpdateSelectionOnSort(false);
        
        modeloListaDeProveedoresAEliminar=(DefaultTableModel)ListaDeProveedoresAEliminar.getModel();
        ListaDeProveedoresAEliminar.setFont(UtilidadesFuentes.InterExtraLight.deriveFont(25.0f));
        ListaDeProveedoresAEliminar.setForeground(Color.decode("#8C8C8C"));
        ListaDeProveedoresAEliminar.setIntercellSpacing(new Dimension(0,0));
        ((DefaultTableCellRenderer)ListaDeProveedoresAEliminar.getDefaultRenderer(String.class)).setHorizontalAlignment(JLabel.CENTER);
        modeloListaDeProveedoresAEliminar.addColumn(new String("nombre"));
        
        scrollPaneDeListaDeProveedores.setViewportView(ListaDeProveedoresAEliminar);
        
        
        scrollCustom=new ScrollBarCustom();
        scrollCustom.setUnitIncrement(16);
        scrollPaneDeListaDeProveedores.setVerticalScrollBar(scrollCustom);
        
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.VERTICAL;
        gbc.weightx=0;
        gbc.weighty=1.0;
        cuerpo.add(scrollPaneDeListaDeProveedores,gbc);
        
        for(int fila: filasSeleccionadas){
                try {
                    Proveedor proveedorABorrar = panelPrincipalDeModuloDeProveedores.proveedores.get(fila);
                    proveedoresABorrar.add(proveedorABorrar);
                    modeloListaDeProveedoresAEliminar.addRow(new Object[]{proveedorABorrar.getRazonSocial()});
                } catch (Exception e) {}
            }
        
    }
    
    @Override
    public void btnAceptarPresionado(MouseEvent evt) {
        panelPrincipalDeModuloDeProveedores.proveedores.removeAll(proveedoresABorrar);
        for (int i = filasSeleccionadas.length-1;i>=0;i--) {
            panelPrincipalDeModuloDeProveedores.eliminarProveedorDeLaTabla(filasSeleccionadas[i]);
        }
        
        ControlProveedores.eliminarProveedores(proveedoresABorrar);
        ((FramePrincipal)((JFrame) SwingUtilities.getWindowAncestor(this))).cerrarPanelesEmergentes();
    }

    @Override
    public void btnCancelarPresionado(MouseEvent evt) {
        ((FramePrincipal)((JFrame) SwingUtilities.getWindowAncestor(this))).cerrarPanelesEmergentes();
    }
}

package Presentacion.Interfaces.Clientes;

import Datos.Entidades.Cliente;
import Negocio.ControlClientes;
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
public class EliminarClientes extends VentanaEmergente{
    private PanelDeClientes panelPrincipalDeModuloDeClientes;
    private JTable ListaDeClientesAEliminar;
    private DefaultTableModel modeloListaDeClientesAEliminar;
    private JScrollPane scrollPaneDeListaDeClientes;
    private ScrollBarCustom scrollCustom;
    private ArrayList<Cliente> ClientesABorrar = new ArrayList<Cliente>();
    int [] filasSeleccionadas;
    
    public EliminarClientes(PanelDeClientes panelPrincipalDeModuloDeClientes, int filasSeleccionadas[]) {
        super("/Presentacion/Imagenes/Paneles/PanelEliminacion.png");
        this.panelPrincipalDeModuloDeClientes=panelPrincipalDeModuloDeClientes;
        this.filasSeleccionadas=filasSeleccionadas;
        
        setTextoTitulo("CONFIRMACIÓN");
        setColorTitulo(Color.decode("#CD5F5F"));
        JPanel cuerpo = getCuerpo();
        cuerpo.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets=new Insets(10, 0, 0, 0);
        
        JLabel lblConfirmacion=new JLabel("Se eliminarán los siguientes Clientes:");
        lblConfirmacion.setFont(UtilidadesFuentes.InterLight.deriveFont(20.0f));
        lblConfirmacion.setForeground(Color.decode("#8C8C8C"));
        lblConfirmacion.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1.0;
        cuerpo.add(lblConfirmacion,gbc);
        
        scrollPaneDeListaDeClientes=new JScrollPane();
        scrollPaneDeListaDeClientes.setPreferredSize(new Dimension(400,0));
        scrollPaneDeListaDeClientes.setBorder(BorderFactory.createEmptyBorder());
        scrollPaneDeListaDeClientes.getViewport().setBackground(Color.WHITE);
        scrollPaneDeListaDeClientes.setOpaque(false);
        
        ListaDeClientesAEliminar=new JTable();
        ListaDeClientesAEliminar.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                double newScrollBarHeight=Math.pow(scrollPaneDeListaDeClientes.getHeight(),2)/(
                ListaDeClientesAEliminar.getRowHeight()*ListaDeClientesAEliminar.getRowCount());
        scrollCustom.setThumbSize((int)newScrollBarHeight);
                scrollPaneDeListaDeClientes.setVerticalScrollBar(scrollCustom);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        ListaDeClientesAEliminar.getTableHeader().setUI(null);
        ListaDeClientesAEliminar.setAutoscrolls(false);
        ListaDeClientesAEliminar.setFocusable(false);
        ListaDeClientesAEliminar.setOpaque(false);
        ListaDeClientesAEliminar.setRequestFocusEnabled(false);
        ListaDeClientesAEliminar.setRowHeight(45);
        ListaDeClientesAEliminar.setRowSelectionAllowed(false);
        ListaDeClientesAEliminar.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ListaDeClientesAEliminar.setShowGrid(false);
        ListaDeClientesAEliminar.getTableHeader().setResizingAllowed(false);
        ListaDeClientesAEliminar.getTableHeader().setReorderingAllowed(false);
        ListaDeClientesAEliminar.setUpdateSelectionOnSort(false);
        
        modeloListaDeClientesAEliminar=(DefaultTableModel)ListaDeClientesAEliminar.getModel();
        ListaDeClientesAEliminar.setFont(UtilidadesFuentes.InterExtraLight.deriveFont(25.0f));
        ListaDeClientesAEliminar.setForeground(Color.decode("#8C8C8C"));
        ListaDeClientesAEliminar.setIntercellSpacing(new Dimension(0,0));
        ((DefaultTableCellRenderer)ListaDeClientesAEliminar.getDefaultRenderer(String.class)).setHorizontalAlignment(JLabel.CENTER);
        modeloListaDeClientesAEliminar.addColumn(new String("nombre"));
        
        scrollPaneDeListaDeClientes.setViewportView(ListaDeClientesAEliminar);
        
        
        scrollCustom=new ScrollBarCustom();
        scrollCustom.setUnitIncrement(16);
        scrollPaneDeListaDeClientes.setVerticalScrollBar(scrollCustom);
        
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.VERTICAL;
        gbc.weightx=0;
        gbc.weighty=1.0;
        cuerpo.add(scrollPaneDeListaDeClientes,gbc);
        
        for(int fila: filasSeleccionadas){
                try {
                    Cliente ClienteABorrar = panelPrincipalDeModuloDeClientes.clientes.get(fila);
                    ClientesABorrar.add(ClienteABorrar);
                    modeloListaDeClientesAEliminar.addRow(new Object[]{ClienteABorrar.getNombre()});
                } catch (Exception e) {}
            }
        
    }
    
    @Override
    public void btnAceptarPresionado(MouseEvent evt) {
        panelPrincipalDeModuloDeClientes.clientes.removeAll(ClientesABorrar);
        for (int i = filasSeleccionadas.length-1;i>=0;i--) {
            panelPrincipalDeModuloDeClientes.eliminarClienteDeLaTabla(filasSeleccionadas[i]);
        }
        
        ControlClientes.eliminarClientes(ClientesABorrar);
        ((FramePrincipal)((JFrame) SwingUtilities.getWindowAncestor(this))).cerrarPanelesEmergentes();
    }

    @Override
    public void btnCancelarPresionado(MouseEvent evt) {
        ((FramePrincipal)((JFrame) SwingUtilities.getWindowAncestor(this))).cerrarPanelesEmergentes();
    }
}

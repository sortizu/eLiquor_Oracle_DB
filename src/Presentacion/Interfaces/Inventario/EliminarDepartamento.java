package Presentacion.Interfaces.Inventario;

import Datos.Entidades.Departamento;
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
public class EliminarDepartamento extends VentanaEmergente{
    private int filasSeleccionadas[];
    private int columnasSeleccionadas[];
    private PanelDeInventario panelPrincipalDeModuloDeInventario;
    private JTable ListaDeDepartamentosAEliminar;
    private DefaultTableModel modeloListaDeDepartamentoAEliminar;
    private JScrollPane scrollPaneDeListaDeDepartamentos;
    private ScrollBarCustom scrollCustom;
    private ArrayList<Departamento> departamentosABorrar = new ArrayList<Departamento>();
    
    public EliminarDepartamento(PanelDeInventario panelPrincipalDeModuloDeInventario, int filasSeleccionadas[],int columnasSeleccionadas[]) {
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
        
        scrollPaneDeListaDeDepartamentos=new JScrollPane();
        scrollPaneDeListaDeDepartamentos.setPreferredSize(new Dimension(400,0));
        scrollPaneDeListaDeDepartamentos.setBorder(BorderFactory.createEmptyBorder());
        scrollPaneDeListaDeDepartamentos.getViewport().setBackground(Color.WHITE);
        scrollPaneDeListaDeDepartamentos.setOpaque(false);
        
        ListaDeDepartamentosAEliminar=new JTable();
        ListaDeDepartamentosAEliminar.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                double newScrollBarHeight=Math.pow(scrollPaneDeListaDeDepartamentos.getHeight(),2)/(
                ListaDeDepartamentosAEliminar.getRowHeight()*ListaDeDepartamentosAEliminar.getRowCount());
        scrollCustom.setThumbSize((int)newScrollBarHeight);
                scrollPaneDeListaDeDepartamentos.setVerticalScrollBar(scrollCustom);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        ListaDeDepartamentosAEliminar.getTableHeader().setUI(null);
        ListaDeDepartamentosAEliminar.setAutoscrolls(false);
        ListaDeDepartamentosAEliminar.setFocusable(false);
        ListaDeDepartamentosAEliminar.setOpaque(false);
        ListaDeDepartamentosAEliminar.setRequestFocusEnabled(false);
        ListaDeDepartamentosAEliminar.setRowHeight(45);
        ListaDeDepartamentosAEliminar.setRowSelectionAllowed(false);
        ListaDeDepartamentosAEliminar.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ListaDeDepartamentosAEliminar.setShowGrid(false);
        ListaDeDepartamentosAEliminar.getTableHeader().setResizingAllowed(false);
        ListaDeDepartamentosAEliminar.getTableHeader().setReorderingAllowed(false);
        ListaDeDepartamentosAEliminar.setUpdateSelectionOnSort(false);
        
        modeloListaDeDepartamentoAEliminar=(DefaultTableModel)ListaDeDepartamentosAEliminar.getModel();
        ListaDeDepartamentosAEliminar.setFont(UtilidadesFuentes.InterExtraLight.deriveFont(25.0f));
        ListaDeDepartamentosAEliminar.setForeground(Color.decode("#8C8C8C"));
        ListaDeDepartamentosAEliminar.setIntercellSpacing(new Dimension(0,0));
        ((DefaultTableCellRenderer)ListaDeDepartamentosAEliminar.getDefaultRenderer(String.class)).setHorizontalAlignment(JLabel.CENTER);
        modeloListaDeDepartamentoAEliminar.addColumn(new String("nombre"));
        
        scrollPaneDeListaDeDepartamentos.setViewportView(ListaDeDepartamentosAEliminar);
        
        
        scrollCustom=new ScrollBarCustom();
        scrollCustom.setUnitIncrement(16);
        scrollPaneDeListaDeDepartamentos.setVerticalScrollBar(scrollCustom);
        
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.VERTICAL;
        gbc.weightx=0;
        gbc.weighty=1.0;
        cuerpo.add(scrollPaneDeListaDeDepartamentos,gbc);
        
        for(int columna: columnasSeleccionadas){
            for(int fila: filasSeleccionadas){
                /*Desconozco parte del funcionamiento de las tablas. Por lo que a la hora de seleccionar los departamentos 
                a eliminar no soy capaz de evitar que celdas sin informacion sean seleccionadas, lo que daria error en este
                procedimiento. Es por ello que uso este try catch. Es una solucion muy desprolija, lamentablemente no encontre
                el tiempo para hallar una solucion mejor.
                */
                try {
                    Departamento departamentoABorrar = panelPrincipalDeModuloDeInventario.departamentos.get(fila*7+columna);
                    departamentosABorrar.add(departamentoABorrar);
                    modeloListaDeDepartamentoAEliminar.addRow(new Object[]{departamentoABorrar.getNombre()});
                } catch (Exception e) {}
            }
        }
        
    }
    
    @Override
    public void btnAceptarPresionado(MouseEvent evt) {
        panelPrincipalDeModuloDeInventario.departamentos.removeAll(departamentosABorrar);
        ControlInventario.eliminarDepartamentos(departamentosABorrar);
        panelPrincipalDeModuloDeInventario.mostrarListaDepartamentosCargadaEnTabla();
        ((FramePrincipal)((JFrame) SwingUtilities.getWindowAncestor(this))).cerrarPanelesEmergentes();
        panelPrincipalDeModuloDeInventario.reiniciarBusqueda();
    }

    @Override
    public void btnCancelarPresionado(MouseEvent evt) {
        ((FramePrincipal)((JFrame) SwingUtilities.getWindowAncestor(this))).cerrarPanelesEmergentes();
    }
}

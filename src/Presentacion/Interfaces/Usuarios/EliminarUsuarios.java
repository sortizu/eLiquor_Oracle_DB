package Presentacion.Interfaces.Usuarios;

import Presentacion.Interfaces.Usuarios.*;
import Datos.Entidades.Usuario;
import Negocio.ControlUsuarios;
import Presentacion.Interfaces.FramePrincipal;
import Presentacion.Interfaces.ScrollBarCustom;
import Presentacion.Interfaces.VentanaEmergente;
import Presentacion.Utilidades.UtilidadSesion;
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
public class EliminarUsuarios extends VentanaEmergente{
    private PanelDeUsuarios panelPrincipalDeModuloDeUsuarios;
    private JTable ListaDeUsuariosAEliminar;
    private DefaultTableModel modeloListaDeUsuariosAEliminar;
    private JScrollPane scrollPaneDeListaDeUsuarios;
    private ScrollBarCustom scrollCustom;
    private ArrayList<Usuario> UsuariosABorrar = new ArrayList<Usuario>();
    int [] filasSeleccionadas;
    
    public EliminarUsuarios(PanelDeUsuarios panelPrincipalDeModuloDeUsuarios, int filasSeleccionadas[]) {
        super("/Presentacion/Imagenes/Paneles/PanelEliminacion.png");
        this.panelPrincipalDeModuloDeUsuarios=panelPrincipalDeModuloDeUsuarios;
        this.filasSeleccionadas=filasSeleccionadas;
        
        setTextoTitulo("CONFIRMACIÓN");
        setColorTitulo(Color.decode("#CD5F5F"));
        JPanel cuerpo = getCuerpo();
        cuerpo.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets=new Insets(10, 0, 0, 0);
        
        JLabel lblConfirmacion=new JLabel("Se eliminarán los siguientes usuarios:");
        lblConfirmacion.setFont(UtilidadesFuentes.InterLight.deriveFont(20.0f));
        lblConfirmacion.setForeground(Color.decode("#8C8C8C"));
        lblConfirmacion.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1.0;
        cuerpo.add(lblConfirmacion,gbc);
        
        scrollPaneDeListaDeUsuarios=new JScrollPane();
        scrollPaneDeListaDeUsuarios.setPreferredSize(new Dimension(400,0));
        scrollPaneDeListaDeUsuarios.setBorder(BorderFactory.createEmptyBorder());
        scrollPaneDeListaDeUsuarios.getViewport().setBackground(Color.WHITE);
        scrollPaneDeListaDeUsuarios.setOpaque(false);
        
        ListaDeUsuariosAEliminar=new JTable();
        ListaDeUsuariosAEliminar.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                double newScrollBarHeight=Math.pow(scrollPaneDeListaDeUsuarios.getHeight(),2)/(
                ListaDeUsuariosAEliminar.getRowHeight()*ListaDeUsuariosAEliminar.getRowCount());
        scrollCustom.setThumbSize((int)newScrollBarHeight);
                scrollPaneDeListaDeUsuarios.setVerticalScrollBar(scrollCustom);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        ListaDeUsuariosAEliminar.getTableHeader().setUI(null);
        ListaDeUsuariosAEliminar.setAutoscrolls(false);
        ListaDeUsuariosAEliminar.setFocusable(false);
        ListaDeUsuariosAEliminar.setOpaque(false);
        ListaDeUsuariosAEliminar.setRequestFocusEnabled(false);
        ListaDeUsuariosAEliminar.setRowHeight(45);
        ListaDeUsuariosAEliminar.setRowSelectionAllowed(false);
        ListaDeUsuariosAEliminar.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ListaDeUsuariosAEliminar.setShowGrid(false);
        ListaDeUsuariosAEliminar.getTableHeader().setResizingAllowed(false);
        ListaDeUsuariosAEliminar.getTableHeader().setReorderingAllowed(false);
        ListaDeUsuariosAEliminar.setUpdateSelectionOnSort(false);
        
        modeloListaDeUsuariosAEliminar=(DefaultTableModel)ListaDeUsuariosAEliminar.getModel();
        ListaDeUsuariosAEliminar.setFont(UtilidadesFuentes.InterExtraLight.deriveFont(25.0f));
        ListaDeUsuariosAEliminar.setForeground(Color.decode("#8C8C8C"));
        ListaDeUsuariosAEliminar.setIntercellSpacing(new Dimension(0,0));
        ((DefaultTableCellRenderer)ListaDeUsuariosAEliminar.getDefaultRenderer(String.class)).setHorizontalAlignment(JLabel.CENTER);
        modeloListaDeUsuariosAEliminar.addColumn(new String("nombre"));
        
        scrollPaneDeListaDeUsuarios.setViewportView(ListaDeUsuariosAEliminar);
        
        
        scrollCustom=new ScrollBarCustom();
        scrollCustom.setUnitIncrement(16);
        scrollPaneDeListaDeUsuarios.setVerticalScrollBar(scrollCustom);
        
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.VERTICAL;
        gbc.weightx=0;
        gbc.weighty=1.0;
        cuerpo.add(scrollPaneDeListaDeUsuarios,gbc);
        for(int fila: filasSeleccionadas){
                try {
                    Usuario UsuarioABorrar = panelPrincipalDeModuloDeUsuarios.usuarios.get(fila);
                    if (UsuarioABorrar.getIdUsuario()==UtilidadSesion.idUsuarioActual){
                    continue;
                    }
                    UsuariosABorrar.add(UsuarioABorrar);
                    modeloListaDeUsuariosAEliminar.addRow(new Object[]{UsuarioABorrar.getNombre()});
                } catch (Exception e) {}
            }
        
    }
    
    @Override
    public void btnAceptarPresionado(MouseEvent evt) {
        panelPrincipalDeModuloDeUsuarios.usuarios.removeAll(UsuariosABorrar);
        for (int i = filasSeleccionadas.length-1;i>=0;i--) {
            Usuario UsuarioABorrar = panelPrincipalDeModuloDeUsuarios.usuarios.get(i);
            if (UsuarioABorrar.getIdUsuario()==UtilidadSesion.idUsuarioActual){
                    continue;
                    }
            panelPrincipalDeModuloDeUsuarios.eliminarUsuarioDeLaTabla(filasSeleccionadas[i]);
        }
        
        ControlUsuarios.eliminarUsuarios(UsuariosABorrar);
        ((FramePrincipal)((JFrame) SwingUtilities.getWindowAncestor(this))).cerrarPanelesEmergentes();
    }

    @Override
    public void btnCancelarPresionado(MouseEvent evt) {
        ((FramePrincipal)((JFrame) SwingUtilities.getWindowAncestor(this))).cerrarPanelesEmergentes();
    }
}

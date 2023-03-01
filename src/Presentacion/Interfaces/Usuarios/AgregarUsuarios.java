package Presentacion.Interfaces.Usuarios;

import Presentacion.Interfaces.Proveedores.*;
import Presentacion.Interfaces.Inventario.*;
import Datos.Entidades.Departamento;
import Datos.Entidades.Proveedor;
import Datos.Entidades.Usuario;
import Negocio.ControlInventario;
import Negocio.ControlProveedores;
import Negocio.ControlUsuarios;
import Presentacion.Interfaces.FramePrincipal;
import Presentacion.Interfaces.PasswordFieldRedondeado;
import Presentacion.Interfaces.Selector;
import Presentacion.Interfaces.TextFieldRedondeado;
import Presentacion.Interfaces.VentanaEmergente;
import Presentacion.Utilidades.UtilidadesFuentes;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

/**
 *
 * @author sortizu
 */
public class AgregarUsuarios extends VentanaEmergente{
    
    /*Selectores de permisos*/
    SelectorNombresSINO selectorVentas = new SelectorNombresSINO("Ventas", Color.decode("#8C8C8C"),
                UtilidadesFuentes.InterLight.deriveFont(25.0f),"SelectorVenta");
        SelectorNombresSINO selectorInventario = new SelectorNombresSINO("Inventario", Color.decode("#8C8C8C"),
                UtilidadesFuentes.InterLight.deriveFont(25.0f), "SelectorInventario");
        SelectorNombresSINO selectorReportes = new SelectorNombresSINO("Reportes", Color.decode("#8C8C8C"),
                UtilidadesFuentes.InterLight.deriveFont(25.0f), "SelectorReportes");
        SelectorNombresSINO selectorClientes = new SelectorNombresSINO("Clientes", Color.decode("#8C8C8C"),
                UtilidadesFuentes.InterLight.deriveFont(25.0f),"SelectorClientes");
        SelectorNombresSINO selectorProveedores = new SelectorNombresSINO("Proveedores", Color.decode("#8C8C8C"),
                UtilidadesFuentes.InterLight.deriveFont(25.0f), "SelectorProveedores");
        SelectorNombresSINO selectorUsuarios = new SelectorNombresSINO("Usuarios", Color.decode("#8C8C8C"),
                UtilidadesFuentes.InterLight.deriveFont(25.0f), "SelectorUsuarios");
    
    private TextFieldRedondeado txtNombre;
    private JLabel lblAlertaNombre;
    private PasswordFieldRedondeado txtPIN;
    private JLabel lblAlertaPIN;
    private PanelDeUsuarios panelPrincipalDeModuloUsuarios;
    
    public AgregarUsuarios(PanelDeUsuarios panelPrincipalDeModuloUsuarios) {
        super("/Presentacion/Imagenes/Paneles/Usuarios/PanelAgregarUsuarios.png");
        this.panelPrincipalDeModuloUsuarios=panelPrincipalDeModuloUsuarios;
        setTextoTitulo("AGREGAR USUARIO");
        setColorTitulo(Color.decode("#6ECD5F"));
        JPanel cuerpo = getCuerpo();
        cuerpo.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        cuerpo.setBorder(new EmptyBorder(0,35,0,35));
        
        JLabel lblNombre=new JLabel("Nombre");
        lblNombre.setFont(UtilidadesFuentes.InterLight.deriveFont(25.0f));
        lblNombre.setForeground(Color.decode("#8C8C8C"));
        lblNombre.setHorizontalAlignment(JLabel.CENTER);
        gbc.insets=new Insets(0, 0, 5, 0);
        gbc.anchor=GridBagConstraints.PAGE_END;
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        gbc.weighty=1;
        cuerpo.add(lblNombre,gbc);
        gbc.weighty=0;
        Dimension dimNombre = new Dimension(350,40);
        
        txtNombre=new TextFieldRedondeado(0);
        txtNombre.setGrosorBorde(4);
        txtNombre.setRadioDeBorde(40);
        txtNombre.setColorBorde(Color.decode("#CACACA"));
        txtNombre.setFont(UtilidadesFuentes.InterLight.deriveFont(22.0f));
        txtNombre.setForeground(Color.decode("#8C8C8C"));
        txtNombre.setHorizontalAlignment(JLabel.CENTER);
        txtNombre.setBorder( BorderFactory.createEmptyBorder(2, 20, 0, 20) );
        txtNombre.setPreferredSize(dimNombre);
        gbc.insets=new Insets(0, 0, 0, 0);
        gbc.gridx=0;
        gbc.gridy=1;
        cuerpo.add(txtNombre,gbc);
        
        lblAlertaNombre = new JLabel("Asegúrese de poner una razón social");
        lblAlertaNombre.setFont(UtilidadesFuentes.InterRegular.deriveFont(17.0f));
        lblAlertaNombre.setForeground(Color.white);
        lblAlertaNombre.setHorizontalAlignment(JLabel.CENTER);
        gbc.insets=new Insets(0, 0, 5, 0);
        gbc.gridx=0;
        gbc.gridy=2;
        cuerpo.add(lblAlertaNombre,gbc);
        
        JLabel lblPIN=new JLabel("PIN");
        lblPIN.setFont(UtilidadesFuentes.InterLight.deriveFont(25.0f));
        lblPIN.setForeground(Color.decode("#8C8C8C"));
        lblPIN.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx=0;
        gbc.gridy=3;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        cuerpo.add(lblPIN,gbc);
        
        Dimension dimPIN = new Dimension(230,40);
        
        txtPIN=new PasswordFieldRedondeado(0);
        txtPIN.setGrosorBorde(4);
        txtPIN.setRadioDeBorde(40);
        txtPIN.setColorBorde(Color.decode("#CACACA"));
        txtPIN.setFont(UtilidadesFuentes.InterBlack.deriveFont(30.0f));
        txtPIN.setForeground(Color.decode("#8C8C8C"));
        txtPIN.setHorizontalAlignment(JLabel.CENTER);
        txtPIN.setBorder( BorderFactory.createEmptyBorder(2, 20, 0, 20) );
        txtPIN.setPreferredSize(dimPIN);
        gbc.gridx=0;
        gbc.gridy=4;
        cuerpo.add(txtPIN,gbc);
        
        lblAlertaPIN = new JLabel(" ");
        lblAlertaPIN.setFont(UtilidadesFuentes.InterRegular.deriveFont(17.0f));
        lblAlertaPIN.setForeground(Color.white);
        lblAlertaPIN.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx=0;
        gbc.gridy=5;
        gbc.weighty=1;
        gbc.anchor=GridBagConstraints.PAGE_START;
        cuerpo.add(lblAlertaPIN,gbc);
        
        JPanel SelectoresPermisos = new JPanel();
        SelectoresPermisos.setOpaque(false);
        SelectoresPermisos.setLayout(new GridBagLayout());
        gbc.gridx=1;
        gbc.gridy=0;
        gbc.gridheight=6;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=1;
        gbc.weighty=1;
        cuerpo.add(SelectoresPermisos,gbc);
        
        JLabel lblPermisos=new JLabel("Permisos");
        lblPermisos.setFont(UtilidadesFuentes.InterLight.deriveFont(25.0f));
        lblPermisos.setForeground(Color.decode("#8C8C8C"));
        lblPermisos.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridheight=1;
        gbc.gridwidth=4;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=0;
        gbc.weighty=0;
        gbc.insets=new Insets(0, 0, 0, 0);
        SelectoresPermisos.add(lblPermisos,gbc);
        
        gbc.gridwidth=1;
        gbc.gridy=1;
        gbc.gridx=0;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        gbc.weighty=0;
        gbc.insets=new Insets(0, 0, 0, 10);
        SelectoresPermisos.add(selectorVentas,gbc);
        gbc.gridy=1;
        gbc.gridx=1;
        SelectoresPermisos.add(selectorInventario,gbc);
        gbc.gridy=2;
        gbc.gridx=0;
        SelectoresPermisos.add(selectorReportes,gbc);
        gbc.gridy=2;
        gbc.gridx=1;
        SelectoresPermisos.add(selectorClientes,gbc);
        gbc.gridy=3;
        gbc.gridx=0;
        SelectoresPermisos.add(selectorProveedores,gbc);
        gbc.gridy=3;
        gbc.gridx=1;
        SelectoresPermisos.add(selectorUsuarios,gbc);
        
        
        PlainDocument documentNombre = (PlainDocument) txtNombre.getDocument();
        documentNombre.setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                lblAlertaNombre.setForeground(Color.white);
                super.replace(fb, offset, length, text, attrs);
            }
        });
        
        PlainDocument documentPass = (PlainDocument) txtPIN.getDocument();
        documentPass.setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                lblAlertaPIN.setForeground(Color.white);
                String string;
                if(!text.isEmpty())
                string = fb.getDocument().getText(0, fb.getDocument().getLength()) + text.substring(text.length()-1);
                else
                    string = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
                if (string.length() <= 4) {
                    if(text.matches("[0-9]+")){
                        super.replace(fb, offset, length, text, attrs);
                    }else{
                        lblAlertaPIN.setText("El PIN debe ser numérico");
                        lblAlertaPIN.setForeground(new java.awt.Color(224, 130, 130));
                    }
                }
            }
        });
    }

    @Override
    public void btnAceptarPresionado(MouseEvent evt) {
        if(txtPIN.getPassword().length==4){
            if(txtNombre.getText().isBlank()){
                lblAlertaNombre.setForeground(new java.awt.Color(224, 130, 130));
            }else{
                Usuario nuevoUsuario = new Usuario(ControlUsuarios.obtenerUltimoID()+1,txtNombre.getText(),
                Integer.parseInt(new String(txtPIN.getPassword())),selectorVentas.getValorDeSelector(),
                selectorUsuarios.getValorDeSelector(),selectorProveedores.getValorDeSelector(),
                selectorClientes.getValorDeSelector(),selectorInventario.getValorDeSelector(),
                selectorReportes.getValorDeSelector());
                nuevoUsuario.setFechaRegistro(LocalDate.now());
                nuevoUsuario.setEstado(true);
                panelPrincipalDeModuloUsuarios.usuarios.add(nuevoUsuario);
                panelPrincipalDeModuloUsuarios.agregarUsuarioATabla(nuevoUsuario);
                ControlUsuarios.agregarUsuario(nuevoUsuario);
                ((FramePrincipal)((JFrame) SwingUtilities.getWindowAncestor(this))).cerrarPanelesEmergentes();
            }
        }else{
            lblAlertaPIN.setText("El PIN debe tener 4 dígitos");
            lblAlertaPIN.setForeground(new java.awt.Color(224, 130, 130));
        }
    }

    @Override
    public void btnCancelarPresionado(MouseEvent evt) {
        ((FramePrincipal)((JFrame) SwingUtilities.getWindowAncestor(this))).cerrarPanelesEmergentes();
    }

    
    
}

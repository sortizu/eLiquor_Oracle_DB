package Presentacion.Interfaces.Usuarios;

import Datos.Entidades.Usuario;
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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
public class ModificarUsuarios extends VentanaEmergente implements PropertyChangeListener{
    
    private TextFieldRedondeado txtNombre;
    private JLabel lblAlertaNombre;
    private PasswordFieldRedondeado txtPIN;
    private JLabel lblAlertaPIN;
    private Selector selectorEstado;
    private Selector selectorRol;
    private PanelDeUsuarios panelPrincipalDeModuloUsuarios;
    private int indice;
    private  Usuario usuarioSeleccionado;
    private boolean estado;
    private Usuario.ROL rol;
    
    
    public ModificarUsuarios(PanelDeUsuarios panelPrincipalDeModuloUsuarios, int indice) {
        super("/Presentacion/Imagenes/Paneles/Usuarios/PanelModificarUsuario.png");
        this.panelPrincipalDeModuloUsuarios=panelPrincipalDeModuloUsuarios;
        this.indice=indice;
        usuarioSeleccionado = panelPrincipalDeModuloUsuarios.usuarios.get(indice);
        setTextoTitulo("MODIFICAR USUARIO");
        setColorTitulo(Color.decode("#5F7ECD"));
        JPanel cuerpo = getCuerpo();
        cuerpo.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        cuerpo.setBorder(new EmptyBorder(0,35,0,35));
        
        JLabel lblNombre=new JLabel("Nombre");
        lblNombre.setFont(UtilidadesFuentes.InterLight.deriveFont(25.0f));
        lblNombre.setFont(UtilidadesFuentes.InterLight.deriveFont(25.0f));
        lblNombre.setForeground(Color.decode("#8C8C8C"));
        lblNombre.setHorizontalAlignment(JLabel.CENTER);
        gbc.insets=new Insets(5, 0, 0, 20);
        //gbc.anchor=GridBagConstraints.PAGE_END;
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.NONE;
        cuerpo.add(lblNombre,gbc);
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
        txtNombre.setText(usuarioSeleccionado.getNombre());
        //gbc.insets=new Insets(0, 0, 5, 0);
        gbc.gridx=0;
        gbc.gridy=1;
        cuerpo.add(txtNombre,gbc);
        
        lblAlertaNombre = new JLabel("Asegúrese de poner una razón social");
        lblAlertaNombre.setFont(UtilidadesFuentes.InterRegular.deriveFont(17.0f));
        lblAlertaNombre.setForeground(Color.white);
        lblAlertaNombre.setHorizontalAlignment(JLabel.CENTER);
        //gbc.insets=new Insets(0, 0, 0, 0);
        gbc.gridx=0;
        gbc.gridy=2;
        cuerpo.add(lblAlertaNombre,gbc);
        
        JLabel lblPIN=new JLabel("PIN");
        lblPIN.setFont(UtilidadesFuentes.InterLight.deriveFont(25.0f));
        lblPIN.setForeground(Color.decode("#8C8C8C"));
        lblPIN.setHorizontalAlignment(JLabel.CENTER);
        //gbc.insets=new Insets(0, 0, 5, 0);
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
        //gbc.insets=new Insets(0, 0, 0, 0);
        gbc.gridx=0;
        gbc.gridy=4;
        cuerpo.add(txtPIN,gbc);
        
        lblAlertaPIN = new JLabel(" ");
        lblAlertaPIN.setFont(UtilidadesFuentes.InterRegular.deriveFont(17.0f));
        lblAlertaPIN.setForeground(Color.white);
        lblAlertaPIN.setHorizontalAlignment(JLabel.CENTER);
        //gbc.insets=new Insets(0, 0, 0, 0);
        gbc.gridx=0;
        gbc.gridy=5;
        gbc.weighty=0;
        cuerpo.add(lblAlertaPIN,gbc);
        
        JLabel lblEstado=new JLabel("Estado");
        lblEstado.setFont(UtilidadesFuentes.InterLight.deriveFont(25.0f));
        lblEstado.setForeground(Color.decode("#8C8C8C"));
        lblEstado.setHorizontalAlignment(JLabel.CENTER);
        gbc.insets=new Insets(5, 20, 0, 0);
        gbc.gridx=1;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        gbc.weighty=0;
        cuerpo.add(lblEstado,gbc);
        gbc.weighty=0;
        
        
        selectorEstado=new Selector(new String[]{"Activo","Inactivo"},124,40);
        selectorEstado.setFuenteDeOpcion(UtilidadesFuentes.InterRegular.deriveFont(20.0f));
        selectorEstado.setColorDeFuente(Color.decode("#8C8C8C"));
        selectorEstado.solicitarSeleccion(usuarioSeleccionado.isEstado()?0:1);
        estado=usuarioSeleccionado.isEstado();
        selectorEstado.setNombreDeSelector("SEST");
        selectorEstado.addPropertyChangeListener(this);
        //gbc.insets=new Insets(0, 0, 0, 0);
        gbc.gridx=1;
        gbc.gridy=1;
        cuerpo.add(selectorEstado,gbc);
        
        JLabel lblRol=new JLabel("Rol");
        lblRol.setFont(UtilidadesFuentes.InterLight.deriveFont(25.0f));
        lblRol.setForeground(Color.decode("#8C8C8C"));
        lblRol.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx=1;
        gbc.gridy=3;
        //gbc.insets=new Insets(0, 0, 0, 0);
        cuerpo.add(lblRol,gbc);
        
        selectorRol=new Selector(new String[]{"Empleado","Administrador"},145,40);
        selectorRol.setFuenteDeOpcion(UtilidadesFuentes.InterRegular.deriveFont(19.0f));
        selectorRol.setColorDeFuente(Color.decode("#8C8C8C"));
        selectorRol.solicitarSeleccion(usuarioSeleccionado.getRol()==Usuario.ROL.ADMINISTRADOR?1:0);
        rol=usuarioSeleccionado.getRol();
        selectorRol.setNombreDeSelector("SROL");
        selectorRol.addPropertyChangeListener(this);
        gbc.anchor=GridBagConstraints.PAGE_START;
        gbc.gridx=1;
        gbc.gridy=4;
        //gbc.weighty=1;
        //gbc.fill=GridBagConstraints.PAGE_START;
        //gbc.weightx=1;
        cuerpo.add(selectorRol,gbc);
        
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
                usuarioSeleccionado.setNombre(txtNombre.getText());
                usuarioSeleccionado.setEstado(estado);
                usuarioSeleccionado.setRol(rol);
                panelPrincipalDeModuloUsuarios.modificarUsuarioDeTabla(indice, usuarioSeleccionado);
                ControlUsuarios.modificarUsuario(indice,usuarioSeleccionado, new String(txtPIN.getPassword()));
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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Selector selectorModificado=((Selector)evt.getSource());
        String tipoSelector=selectorModificado.getNombreDeSelector();
        switch (tipoSelector) {
            case "SEST":
                estado=((int)evt.getNewValue())==0;
            break;
            case "SROL":
                rol=((int)evt.getNewValue())==0?Usuario.ROL.EMPLEADO:Usuario.ROL.ADMINISTRADOR;
            break;
        }
    }
}

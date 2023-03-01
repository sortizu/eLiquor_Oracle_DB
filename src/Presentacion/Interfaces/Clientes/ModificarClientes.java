package Presentacion.Interfaces.Clientes;

import Presentacion.Interfaces.Clientes.*;
import Datos.Entidades.Cliente;
import Negocio.ControlClientes;
import Presentacion.Interfaces.FramePrincipal;
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
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

/**
 *
 * @author sortizu
 */
public class ModificarClientes extends VentanaEmergente{
    
    private TextFieldRedondeado txtNombre;
    private JLabel lblAlertaNombre;
    private TextFieldRedondeado txtCorreo;
    private TextFieldRedondeado txtTelefono;
    private PanelDeClientes panelPrincipalDeModuloDeClientes;
    private Cliente ClienteModificado;
    private int fila;
    
    public ModificarClientes(PanelDeClientes panelPrincipalDeModuloDeClientes, int fila) {
        super("/Presentacion/Imagenes/Paneles/Proveedores/PanelAgregarProveedores.png");
        this.panelPrincipalDeModuloDeClientes=panelPrincipalDeModuloDeClientes;
        this.fila = fila;
        setTextoTitulo("MODIFICAR Cliente");
        setColorTitulo(Color.decode("#5F7ECD"));
        JPanel cuerpo = getCuerpo();
        cuerpo.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        Dimension dimTxt = new Dimension(0,40);
        Insets insetTxt = new Insets(0, 45, 5, 45);
        Insets insetLbl = new Insets(0, 75, 5, 45);
        
        JLabel lblRazonSocial=new JLabel("Razón Social");
        lblRazonSocial.setFont(UtilidadesFuentes.InterLight.deriveFont(25.0f));
        lblRazonSocial.setForeground(Color.decode("#8C8C8C"));
        lblRazonSocial.setHorizontalAlignment(JLabel.LEFT);
        gbc.insets=insetLbl;
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1.0;
        cuerpo.add(lblRazonSocial,gbc);
        
        txtNombre=new TextFieldRedondeado(0);
        txtNombre.setGrosorBorde(4);
        txtNombre.setRadioDeBorde(40);
        txtNombre.setColorBorde(Color.decode("#CACACA"));
        txtNombre.setFont(UtilidadesFuentes.InterLight.deriveFont(25.0f));
        txtNombre.setForeground(Color.decode("#8C8C8C"));
        txtNombre.setHorizontalAlignment(JLabel.CENTER);
        txtNombre.setBorder( BorderFactory.createEmptyBorder(2, 20, 0, 20) );
        txtNombre.setPreferredSize(dimTxt);
        gbc.insets=insetTxt;
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=0;
        cuerpo.add(txtNombre,gbc);
        
        lblAlertaNombre = new JLabel("Asegúrese de poner una razón social");
        lblAlertaNombre.setFont(UtilidadesFuentes.InterRegular.deriveFont(17.0f));
        lblAlertaNombre.setForeground(new java.awt.Color(224, 130, 130));
        lblAlertaNombre.setHorizontalAlignment(JLabel.CENTER);
        gbc.insets=new Insets(0, 0, 0, 0);
        gbc.gridx=0;
        gbc.gridy=2;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1;
        cuerpo.add(lblAlertaNombre,gbc);
        
        
        JLabel lblCorreo=new JLabel("Correo");
        lblCorreo.setFont(UtilidadesFuentes.InterLight.deriveFont(25.0f));
        lblCorreo.setForeground(Color.decode("#8C8C8C"));
        lblCorreo.setHorizontalAlignment(JLabel.LEFT);
        gbc.insets = insetLbl;
        gbc.gridx=0;
        gbc.gridy=3;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1.0;
        cuerpo.add(lblCorreo,gbc);
        
        txtCorreo=new TextFieldRedondeado(0);
        txtCorreo.setGrosorBorde(4);
        txtCorreo.setRadioDeBorde(40);
        txtCorreo.setColorBorde(Color.decode("#CACACA"));
        txtCorreo.setFont(UtilidadesFuentes.InterLight.deriveFont(25.0f));
        txtCorreo.setForeground(Color.decode("#8C8C8C"));
        txtCorreo.setHorizontalAlignment(JLabel.CENTER);
        txtCorreo.setBorder( BorderFactory.createEmptyBorder(2, 20, 0, 20) );
        txtCorreo.setPreferredSize(dimTxt);
        gbc.insets=insetTxt;
        gbc.gridx=0;
        gbc.gridy=4;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=0;
        cuerpo.add(txtCorreo,gbc);
        
        
        JLabel lblTelefono=new JLabel("Teléfono");
        lblTelefono.setFont(UtilidadesFuentes.InterLight.deriveFont(25.0f));
        lblTelefono.setForeground(Color.decode("#8C8C8C"));
        lblTelefono.setHorizontalAlignment(JLabel.LEFT);
        gbc.insets=insetLbl;
        gbc.gridx=0;
        gbc.gridy=5;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1.0;
        cuerpo.add(lblTelefono,gbc);
        
        txtTelefono=new TextFieldRedondeado(0);
        txtTelefono.setGrosorBorde(4);
        txtTelefono.setRadioDeBorde(40);
        txtTelefono.setColorBorde(Color.decode("#CACACA"));
        txtTelefono.setFont(UtilidadesFuentes.InterLight.deriveFont(25.0f));
        txtTelefono.setForeground(Color.decode("#8C8C8C"));
        txtTelefono.setHorizontalAlignment(JLabel.CENTER);
        txtTelefono.setBorder( BorderFactory.createEmptyBorder(2, 20, 0, 20) );
        txtTelefono.setPreferredSize(dimTxt);
        gbc.insets=insetTxt;
        gbc.gridx=0;
        gbc.gridy=6;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=0;
        cuerpo.add(txtTelefono,gbc);
        
        
        
        PlainDocument documentNombre = (PlainDocument) txtNombre.getDocument();
        documentNombre.setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                lblAlertaNombre.setVisible(false);
                super.replace(fb, offset, length, text, attrs);
            }
        });
        
        PlainDocument documentTelefono = (PlainDocument) txtTelefono.getDocument();
        documentTelefono.setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if(text.matches("[0-9]+")){
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
        
        lblAlertaNombre.setVisible(false);
        /*Cargando datos del departamento*/
        ClienteModificado = panelPrincipalDeModuloDeClientes.clientes.get(fila);
        txtNombre.setText(ClienteModificado.getNombre());
        txtCorreo.setText(ClienteModificado.getCorreo());
        txtTelefono.setText(""+ClienteModificado.getTelefono());
    }

    @Override
    public void btnAceptarPresionado(MouseEvent evt) {
        if(txtNombre.getText().isBlank()){
            lblAlertaNombre.setVisible(true);
        }else{
            int telefono=0;
            try{
                telefono=Integer.parseInt(txtTelefono.getText());
            }catch(Exception er){System.err.println(er);}
            ClienteModificado.setNombre(txtNombre.getText());
            ClienteModificado.setCorreo(txtCorreo.getText());
            ClienteModificado.setTelefono(telefono);
            panelPrincipalDeModuloDeClientes.modificarClienteDeTabla(fila, ClienteModificado);
            ControlClientes.modificarCliente(ClienteModificado);
            ((FramePrincipal)((JFrame) SwingUtilities.getWindowAncestor(this))).cerrarPanelesEmergentes();   
        }
    }

    @Override
    public void btnCancelarPresionado(MouseEvent evt) {
        ((FramePrincipal)((JFrame) SwingUtilities.getWindowAncestor(this))).cerrarPanelesEmergentes();
    }

    
    
}

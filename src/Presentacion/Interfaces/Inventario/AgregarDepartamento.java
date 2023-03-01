package Presentacion.Interfaces.Inventario;

import Datos.Entidades.Departamento;
import Negocio.ControlInventario;
import Presentacion.Interfaces.FramePrincipal;
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
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

/**
 *
 * @author sortizu
 */
public class AgregarDepartamento extends VentanaEmergente{
    
    private TextFieldRedondeado txtNombre;
    private JLabel lblAlertaNombre;
    private PanelDeInventario panelPrincipalDeModuloDeInventario;
    
    public AgregarDepartamento(PanelDeInventario panelPrincipalDeModuloDeInventario) {
        super("/Presentacion/Imagenes/Paneles/Inventario/PanelAgregarDepartamento.png");
        this.panelPrincipalDeModuloDeInventario=panelPrincipalDeModuloDeInventario;
        setTextoTitulo("AGREGAR DEPARTAMENTO");
        setColorTitulo(Color.decode("#6ECD5F"));
        JPanel cuerpo = getCuerpo();
        cuerpo.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets=new Insets(0, 0, 5, 0);
        
        JLabel lblNombre=new JLabel("Nombre");
        lblNombre.setFont(UtilidadesFuentes.InterLight.deriveFont(25.0f));
        lblNombre.setForeground(Color.decode("#8C8C8C"));
        lblNombre.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1.0;
        cuerpo.add(lblNombre,gbc);
        
        txtNombre=new TextFieldRedondeado(15);
        txtNombre.setGrosorBorde(4);
        txtNombre.setRadioDeBorde(40);
        txtNombre.setColorBorde(Color.decode("#CACACA"));
        txtNombre.setFont(UtilidadesFuentes.InterLight.deriveFont(25.0f));
        txtNombre.setForeground(Color.decode("#8C8C8C"));
        txtNombre.setHorizontalAlignment(JLabel.CENTER);
        txtNombre.setBorder( BorderFactory.createEmptyBorder(2, 20, 0, 20) );
        txtNombre.setPreferredSize(new Dimension(359,45));
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        cuerpo.add(txtNombre,gbc);
        
        lblAlertaNombre = new JLabel("Asegúrese de poner un nombre");
        lblAlertaNombre.setFont(UtilidadesFuentes.InterRegular.deriveFont(17.0f));
        lblAlertaNombre.setForeground(new java.awt.Color(224, 130, 130));
        lblAlertaNombre.setHorizontalAlignment(JLabel.CENTER);
        gbc.insets=new Insets(0, 0, 0, 0);
        gbc.gridx=0;
        gbc.gridy=2;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1;
        cuerpo.add(lblAlertaNombre,gbc);
        gbc.insets=new Insets(0, 0, 5, 0);
      
        
        PlainDocument documentNombre = (PlainDocument) txtNombre.getDocument();
        documentNombre.setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                lblAlertaNombre.setVisible(false);
                super.replace(fb, offset, length, text, attrs);
            }
        });
        lblAlertaNombre.setVisible(false);
    }

    @Override
    public void btnAceptarPresionado(MouseEvent evt) {
        if(txtNombre.getText().isBlank()){
                lblAlertaNombre.setVisible(true);
        }else{
                Departamento nuevoDepartamento = new Departamento(ControlInventario.obtenerUltimoIDDepartamento()+1,txtNombre.getText(),LocalDate.now());
                panelPrincipalDeModuloDeInventario.departamentos.add(nuevoDepartamento);
                panelPrincipalDeModuloDeInventario.agregarDepartamentoATabla(nuevoDepartamento);
                ControlInventario.agregarDepartamento(nuevoDepartamento);
                ((FramePrincipal)((JFrame) SwingUtilities.getWindowAncestor(this))).cerrarPanelesEmergentes();
                panelPrincipalDeModuloDeInventario.reiniciarBusqueda();
        }
    }

    @Override
    public void btnCancelarPresionado(MouseEvent evt) {
        ((FramePrincipal)((JFrame) SwingUtilities.getWindowAncestor(this))).cerrarPanelesEmergentes();
    }

    
    
}

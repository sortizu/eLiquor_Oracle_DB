package Presentacion.Interfaces.Inventario;

import Datos.Entidades.Entrega;
import Presentacion.Interfaces.FramePrincipal;
import Presentacion.Interfaces.TextFieldRedondeado;
import Presentacion.Interfaces.VentanaEmergente;
import Presentacion.Interfaces.Ventas.VentaPago2;
import Presentacion.Utilidades.UtilidadesFuentes;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
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
public class IngresoProducto1 extends VentanaEmergente{
    private PanelDeInventario panelPrincipalDeModuloDeInventario;
    private JLabel lblProducto;
    private JLabel lblCantidad;
    private JLabel btnSuma;
    private JLabel btnResta;
    private TextFieldRedondeado txtCantidad;
    private JLabel lblCosto;
    private TextFieldRedondeado txtCosto;
    private Entrega nuevaEntrega;
    public IngresoProducto1(PanelDeInventario panelPrincipalDeModuloDeInventario,Entrega nuevaEntrega) {
        super("/Presentacion/Imagenes/Paneles/Inventario/PanelIngresoProducto1.png");
        cambiarDisposicionDePanelDeBotones(VentanaEmergente.PRIMERVENTANA);
        this.panelPrincipalDeModuloDeInventario=panelPrincipalDeModuloDeInventario;
        this.nuevaEntrega=nuevaEntrega;
        setTextoTitulo("INGRESO");
        setColorTitulo(Color.decode("#D9AA4F"));
        JPanel cuerpo = getCuerpo();
        cuerpo.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets=new Insets(0, 0, 15, 0);
        lblProducto=new JLabel(nuevaEntrega.getItem().getNombre());
        lblProducto.setFont(UtilidadesFuentes.InterLight.deriveFont(25.0f));
        lblProducto.setForeground(Color.decode("#8C8C8C"));
        lblProducto.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1.0;
        gbc.gridwidth=3;
        cuerpo.add(lblProducto,gbc);
        
        lblCantidad=new JLabel("Cantidad");
        lblCantidad.setFont(UtilidadesFuentes.InterLight.deriveFont(25.0f));
        lblCantidad.setForeground(Color.decode("#8C8C8C"));
        lblCantidad.setHorizontalAlignment(JLabel.CENTER);
        gbc.insets=new Insets(0, 0, 5, 0);
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1.0;
        gbc.gridwidth=3;
        cuerpo.add(lblCantidad,gbc);
        
        btnSuma=new JLabel("+");
        btnSuma.setFont(UtilidadesFuentes.InterLight.deriveFont(40.0f));
        btnSuma.setForeground(Color.decode("#6EA6BE"));
        btnSuma.setHorizontalAlignment(JLabel.CENTER);
        btnSuma.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSuma.setPreferredSize(new Dimension(45,45));
        btnSuma.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                try {
                    int cantidad = Integer.parseInt(txtCantidad.getText());
                    txtCantidad.setText(""+(++cantidad));
                } catch (Exception e) {txtCantidad.setText("0");}
            }
        });
        gbc.anchor=GridBagConstraints.LINE_END;
        gbc.gridx=0;
        gbc.gridy=2;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0.4;
        gbc.gridwidth=1;
        cuerpo.add(btnSuma,gbc);
        
        txtCantidad=new TextFieldRedondeado(0);
        txtCantidad.setGrosorBorde(4);
        txtCantidad.setRadioDeBorde(40);
        txtCantidad.setColorBorde(Color.decode("#CACACA"));
        txtCantidad.setFont(UtilidadesFuentes.InterBlack.deriveFont(25.0f));
        txtCantidad.setForeground(Color.decode("#8C8C8C"));
        txtCantidad.setHorizontalAlignment(JLabel.CENTER);
        txtCantidad.setBorder( BorderFactory.createEmptyBorder(2, 20, 0, 20) );
        txtCantidad.setPreferredSize(new Dimension(1,45));
        txtCantidad.setMinimumSize(new Dimension(1,45));
        gbc.gridx=1;
        gbc.gridy=2;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1;
        cuerpo.add(txtCantidad,gbc);
        
        
        btnResta=new JLabel("-");
        btnResta.setFont(UtilidadesFuentes.InterLight.deriveFont(40.0f));
        btnResta.setForeground(Color.decode("#D0A47C"));
        btnResta.setHorizontalAlignment(JLabel.CENTER);
        btnResta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnResta.setPreferredSize(new Dimension(45,45));
        btnResta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                try {
                    int cantidad = Integer.parseInt(txtCantidad.getText());
                    if(cantidad>0){
                        txtCantidad.setText(""+(--cantidad));
                    }
                } catch (Exception e) {txtCantidad.setText("0");}
            }
        });
        gbc.anchor=GridBagConstraints.LINE_START;
        gbc.insets=new Insets(0, 0, 10, 0);
        gbc.gridx=2;
        gbc.gridy=2;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0.4;
        gbc.gridwidth=1;
        cuerpo.add(btnResta,gbc);
        
        
        lblCosto=new JLabel("Costo");
        lblCosto.setFont(UtilidadesFuentes.InterLight.deriveFont(25.0f));
        lblCosto.setForeground(Color.decode("#8C8C8C"));
        lblCosto.setHorizontalAlignment(JLabel.CENTER);
        gbc.insets=new Insets(0, 0, 5, 0);
        gbc.anchor=GridBagConstraints.CENTER;
        gbc.gridx=0;
        gbc.gridy=3;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1.0;
        gbc.gridwidth=3;
        cuerpo.add(lblCosto,gbc);
        
        txtCosto=new TextFieldRedondeado(0);
        txtCosto.setGrosorBorde(4);
        txtCosto.setRadioDeBorde(40);
        txtCosto.setColorBorde(Color.decode("#CACACA"));
        txtCosto.setFont(UtilidadesFuentes.InterBlack.deriveFont(25.0f));
        txtCosto.setForeground(Color.decode("#8C8C8C"));
        txtCosto.setHorizontalAlignment(JLabel.CENTER);
        txtCosto.setBorder( BorderFactory.createEmptyBorder(2, 20, 0, 20) );
        txtCosto.setPreferredSize(new Dimension(1,45));
        txtCosto.setMinimumSize(new Dimension(1,45));
        //gbc.anchor=GridBagConstraints.PAGE_START;
        gbc.insets=new Insets(0, 0, 5, 0);
        gbc.gridx=1;
        gbc.gridy=4;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1;
        //gbc.weighty=1;
        gbc.gridwidth=1;
        cuerpo.add(txtCosto,gbc);
        
        //Actualizando campos
        txtCantidad.setText(""+nuevaEntrega.getCantidad());
        txtCosto.setText(Double.toString(nuevaEntrega.getCosto()));
        
        //Dando formato de ingreso de datos a campos
        PlainDocument documentCant = (PlainDocument) txtCantidad.getDocument();
        documentCant.setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if(text.matches("[0-9]+")){
                        super.replace(fb, offset, length, text, attrs);
                }
            }
        });
        
         PlainDocument documentCos = (PlainDocument) txtCosto.getDocument();
        documentCos.setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if(text.matches("[0-9]+")||(text.matches("[.]")&&!txtCosto.getText().contains("."))){
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
        txtCantidad.setText(Integer.toString(nuevaEntrega.getCantidad()));
        txtCosto.setText(Double.toString(nuevaEntrega.getCosto()));
    }

    @Override
    public void btnSiguientePresionado(MouseEvent evt) {
        try{
            nuevaEntrega.setCantidad(Integer.parseInt(txtCantidad.getText()));
        }catch(NumberFormatException er){
            nuevaEntrega.setCantidad(0);
        }
        try{
            nuevaEntrega.setCosto(Double.parseDouble(txtCosto.getText()));
        }catch(NumberFormatException er){
            nuevaEntrega.setCosto(0);
        }
        ((FramePrincipal)((JFrame) SwingUtilities.getWindowAncestor(this))).cerrarPanelesEmergentes();
         IngresoProducto2 ingresoProducto2 = new IngresoProducto2(panelPrincipalDeModuloDeInventario,nuevaEntrega);
         ((FramePrincipal) SwingUtilities.getWindowAncestor(panelPrincipalDeModuloDeInventario)).mostrarPanelEmergente(ingresoProducto2);
         ingresoProducto2.requestFocus();
    }

    
    
    @Override
    public void btnCancelarPresionado(MouseEvent evt) {
        ((FramePrincipal)((JFrame) SwingUtilities.getWindowAncestor(this))).cerrarPanelesEmergentes();
    }
    
}

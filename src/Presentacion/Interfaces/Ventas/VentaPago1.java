package Presentacion.Interfaces.Ventas;

import Datos.Entidades.Venta;
import Presentacion.Interfaces.FramePrincipal;
import Presentacion.Interfaces.PanelRedondeado;
import Presentacion.Interfaces.TextFieldRedondeado;
import Presentacion.Interfaces.VentanaEmergente;
import Presentacion.Utilidades.UtilidadesFuentes;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
public class VentaPago1 extends VentanaEmergente{
    private PanelDeVentas panelPrincipalDeModuloDeVentas;
    private Venta ventaActual;
    private JLabel lblMonto;
    private TextFieldRedondeado txtMonto; //Valor de pago cliente
    private BotonMontoPago btn10;
    private BotonMontoPago btn20;
    private BotonMontoPago btn50;
    private BotonMontoPago btn100;
    private BotonMontoPago btn200;
    
    
    public VentaPago1(PanelDeVentas panelPrincipalDeModuloDeVentas, Venta ventaActualRef) {
        super("/Presentacion/Imagenes/Paneles/Ventas/PanelPago1.png");
        cambiarDisposicionDePanelDeBotones(VentanaEmergente.PRIMERVENTANA);
        this.panelPrincipalDeModuloDeVentas=panelPrincipalDeModuloDeVentas;
        setTextoTitulo("PAGO");
        ventaActual= new Venta();
        ventaActual.setDetallesVenta(ventaActualRef.getDetallesVenta());
        ventaActual.setPagoCliente(ventaActualRef.getPagoCliente());
        
        setColorTitulo(Color.decode("#8CC560"));
        JPanel cuerpo = getCuerpo();
        cuerpo.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets=new Insets(10, 0, 5, 0);
        
        lblMonto=new JLabel("Monto");
        lblMonto.setFont(UtilidadesFuentes.InterLight.deriveFont(25.0f));
        lblMonto.setForeground(Color.decode("#8C8C8C"));
        lblMonto.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1.0;
        gbc.weighty=0;
        cuerpo.add(lblMonto,gbc);
        
        txtMonto=new TextFieldRedondeado(0);
        txtMonto.setGrosorBorde(4);
        txtMonto.setRadioDeBorde(40);
        txtMonto.setColorBorde(Color.decode("#CACACA"));
        txtMonto.setFont(UtilidadesFuentes.InterBlack.deriveFont(25.0f));
        txtMonto.setForeground(Color.decode("#8C8C8C"));
        txtMonto.setHorizontalAlignment(JLabel.CENTER);
        txtMonto.setBorder( BorderFactory.createEmptyBorder(2, 20, 0, 20) );
        txtMonto.setPreferredSize(new Dimension(1,45));
        txtMonto.setMinimumSize(new Dimension(1,45));
        txtMonto.setText(Double.toString(ventaActual.getPagoCliente()));
        gbc.insets = new Insets(0, 50, 15, 50);
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1;
        gbc.weighty=0;
        cuerpo.add(txtMonto,gbc);
        
        JPanel panelBotonesMonto = new JPanel();
        panelBotonesMonto.setPreferredSize(new Dimension(0,0));
        panelBotonesMonto.setOpaque(false);
        panelBotonesMonto.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
        
        btn10=new BotonMontoPago(10,txtMonto);
        btn20=new BotonMontoPago(20,txtMonto);
        btn50=new BotonMontoPago(50,txtMonto);
        btn100=new BotonMontoPago(100,txtMonto);
        btn200=new BotonMontoPago(200,txtMonto);
        
        panelBotonesMonto.add(btn10);
        panelBotonesMonto.add(btn20);
        panelBotonesMonto.add(btn50);
        panelBotonesMonto.add(btn100);
        panelBotonesMonto.add(btn200);
        
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.gridx=0;
        gbc.gridy=2;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=1;
        gbc.weighty=1.0;
        cuerpo.add(panelBotonesMonto,gbc);
        
         PlainDocument documentCos = (PlainDocument) txtMonto.getDocument();
        documentCos.setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if(text.matches("[0-9[.]]+")){
                    if((text.matches("[.]")&&txtMonto.getText().contains(".")))
                        return;
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
    }

    @Override
    public void btnSiguientePresionado(MouseEvent evt) {
        try{
            ventaActual.setPagoCliente(Double.parseDouble(txtMonto.getText()));
        }catch(NumberFormatException er){
            ventaActual.setPagoCliente(0);
        }
        ((FramePrincipal)((JFrame) SwingUtilities.getWindowAncestor(this))).cerrarPanelesEmergentes();
         VentaPago2 ventaPago2 = new VentaPago2(panelPrincipalDeModuloDeVentas,ventaActual);
         ((FramePrincipal) SwingUtilities.getWindowAncestor(panelPrincipalDeModuloDeVentas)).mostrarPanelEmergente(ventaPago2);
         ventaPago2.requestFocus();
    }

    
    
    @Override
    public void btnCancelarPresionado(MouseEvent evt) {
        ((FramePrincipal)((JFrame) SwingUtilities.getWindowAncestor(this))).cerrarPanelesEmergentes();
    }
    
    class BotonMontoPago extends PanelRedondeado{
        private JLabel lblMontoPago;
        private double monto;
        private TextFieldRedondeado textFieldAModificar;
        public BotonMontoPago(double monto,TextFieldRedondeado textFieldAModificar){
            super(20, 3, new Color(0,0,0,0), Color.decode("#8C8C8C"));
            this.textFieldAModificar=textFieldAModificar;
            this.monto=monto;
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            setOpaque(false);
            setPreferredSize(new Dimension(160,70));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            //Creando texto del boton Pago
            lblMontoPago=new JLabel("S/."+Double.toString(monto),JLabel.CENTER);
            lblMontoPago.setFont(UtilidadesFuentes.InterRegular.deriveFont(20.0f));
            lblMontoPago.setForeground(getColorBorde());
            //Añadiendo Texto al boton Cliente
            gbc.insets=new Insets(0, 0, 0, 0);
            gbc.gridx=0;
            gbc.gridy=0;
            gbc.fill=GridBagConstraints.BOTH;
            gbc.weightx=1;
            gbc.weighty=1;
            add(lblMontoPago,gbc);
            //Dando funcionalidad al boton Pago
            addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    textFieldAModificar.setText(Double.toString(monto));
                }
                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    setColorFondo(getColorBorde());
                    lblMontoPago.setForeground(Color.WHITE);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    setColorFondo(new Color(0,0,0,0));
                    lblMontoPago.setForeground(getColorBorde());
                }
            
                });
        }
    }
    
}

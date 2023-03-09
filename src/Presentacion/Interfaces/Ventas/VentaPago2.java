package Presentacion.Interfaces.Ventas;

import Datos.Entidades.Venta;
import Negocio.ControlVentas;
import Presentacion.Interfaces.FramePrincipal;
import Presentacion.Interfaces.VentanaEmergente;
import Presentacion.Utilidades.UtilidadSesion;
import Presentacion.Utilidades.UtilidadesFuentes;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author sortizu
 */
public class VentaPago2 extends VentanaEmergente{
    private PanelDeVentas panelPrincipalDeModuloDeVentas;
    private Venta ventaActual;
    private JLabel lblTotal;
    private JLabel lblPago;
    private JLabel lblCambio;
    
    
    public VentaPago2(PanelDeVentas panelPrincipalDeModuloDeVentas, Venta ventaActualRef) {
        super("/Presentacion/Imagenes/Paneles/Ventas/PanelPago2.png");
        cambiarDisposicionDePanelDeBotones(VentanaEmergente.ULTIMAVENTANA);
        this.panelPrincipalDeModuloDeVentas=panelPrincipalDeModuloDeVentas;
        setTextoTitulo("PAGO");
        ventaActual= new Venta();
        ventaActual.setDetallesVenta(ventaActualRef.getDetallesVenta());
        ventaActual.setPagoCliente(ventaActualRef.getPagoCliente());
        ventaActual.setCambio(ventaActual.getPagoCliente()-ventaActual.getVentaNeta());
        setColorTitulo(Color.decode("#8CC560"));
        JPanel cuerpo = getCuerpo();
        cuerpo.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets=new Insets(10, 0, 5, 0);
        
        lblTotal=new JLabel(String.format("<html><body style='text-align: center;'>TOTAL<br>S/. %.2f</body></html>",ventaActual.getVentaNeta()));
        lblTotal.setFont(UtilidadesFuentes.InterLight.deriveFont(25.0f));
        lblTotal.setForeground(Color.decode("#8C8C8C"));
        lblTotal.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1.0;
        gbc.weighty=0;
        cuerpo.add(lblTotal,gbc);
        
        lblPago=new JLabel(String.format("<html><body style='text-align: center;'>PAGO<br>S/. %.2f</body></html>",ventaActual.getPagoCliente()));
        lblPago.setFont(UtilidadesFuentes.InterLight.deriveFont(25.0f));
        lblPago.setForeground(Color.decode("#8C8C8C"));
        lblPago.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx=1;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1.0;
        gbc.weighty=0;
        cuerpo.add(lblPago,gbc);
        
        lblCambio=new JLabel(String.format("<html><body style='text-align: center;'>CAMBIO<br>S/. %.2f</body></html>",ventaActual.getCambio()));
        lblCambio.setFont(UtilidadesFuentes.InterLight.deriveFont(30.0f));
        lblCambio.setForeground(Color.decode("#8C8C8C"));
        lblCambio.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1.0;
        gbc.weighty=0;
        gbc.gridwidth=2;
        cuerpo.add(lblCambio,gbc);
        /*
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
        });*/
    }

    @Override
    public void btnAceptarPresionado(MouseEvent evt) {
        Venta ventaARegistrar = panelPrincipalDeModuloDeVentas.ventaActual;
        ventaARegistrar.setPagoCliente(ventaActual.getPagoCliente());
        ventaARegistrar.setCambio(ventaActual.getCambio());
        ventaARegistrar.setFechaRegistro(LocalDate.now());
        ventaARegistrar.setIdUsuario(UtilidadSesion.idUsuarioActual);
        ventaARegistrar.setTotalCosto(ventaARegistrar.getTotalCosto());
        ventaARegistrar.setVentaBruta(ventaARegistrar.getVentaBruta());
        ventaARegistrar.setTotalImpuestos(ventaARegistrar.getTotalImpuestos());
        ventaARegistrar.setTotalDescuento(ventaARegistrar.getTotalDescuento());
        ControlVentas.registrarVenta(ventaARegistrar);
        ((FramePrincipal)((JFrame) SwingUtilities.getWindowAncestor(this))).cerrarPanelesEmergentes();
        panelPrincipalDeModuloDeVentas.ventaActual=new Venta();
        panelPrincipalDeModuloDeVentas.getTablaListaDeVenta().getModeloTabla().setRowCount(0);
        panelPrincipalDeModuloDeVentas.actualizarDatosVenta();
        if(panelPrincipalDeModuloDeVentas.departamentoSeleccionado!=null){
            panelPrincipalDeModuloDeVentas.cargarListaDeProductos(panelPrincipalDeModuloDeVentas.departamentoSeleccionado.getIdDepartamento());
        }else{
            panelPrincipalDeModuloDeVentas.getSelectorMostrar().solicitarSeleccion(0);
        }
        
        panelPrincipalDeModuloDeVentas.actualizarPanelItemRecientes();
        
    }
    
    @Override
    public void btnCancelarPresionado(MouseEvent evt) {
        ((FramePrincipal)((JFrame) SwingUtilities.getWindowAncestor(this))).cerrarPanelesEmergentes();
    }

    @Override
    public void btnAtrasPresionado(MouseEvent evt) {
        ((FramePrincipal)((JFrame) SwingUtilities.getWindowAncestor(this))).cerrarPanelesEmergentes();
        VentaPago1 ventaPago1 = new VentaPago1(panelPrincipalDeModuloDeVentas,ventaActual);
        ((FramePrincipal) SwingUtilities.getWindowAncestor(panelPrincipalDeModuloDeVentas)).mostrarPanelEmergente(ventaPago1);
        ventaPago1.requestFocus();
    }
    
}

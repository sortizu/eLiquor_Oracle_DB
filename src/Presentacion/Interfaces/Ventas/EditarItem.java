package Presentacion.Interfaces.Ventas;

import Datos.Entidades.DetalleVenta;
import Presentacion.Interfaces.FramePrincipal;
import Presentacion.Interfaces.Selector;
import Presentacion.Interfaces.TextFieldRedondeado;
import Presentacion.Interfaces.VentanaEmergente;
import Presentacion.Utilidades.UtilidadesFuentes;
import java.awt.Color;
import java.awt.Cursor;
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

/**
 *
 * @author sortizu
 */
public class EditarItem extends VentanaEmergente implements PropertyChangeListener{
    
    private PanelDeVentas panelPrincipalDeModuloDeVentas;
    private JLabel lblItem;
    private JLabel lblCantidad;
    private TextFieldRedondeado txtCantidad;
    private JLabel btnSuma;
    private JLabel btnResta;
    private JLabel lblPrecio;
    private TextFieldRedondeado txtPrecio;
    private JLabel lblDescuento;
    private TextFieldRedondeado txtDescuento;
    private JLabel lblImpuestos;
    private JLabel lblIGV;
    private JLabel lblIGVMonto;
    private JLabel lblISC;
    private JLabel lblISCMonto;
    private JLabel lblSubTotal;
    private JLabel lblTotal;
    private JLabel lblTotalImpuestos;
    DetalleVenta detalleVentaReferencia;
    private DetalleVenta detalleVentaAModificar;
    private int indiceDetalleVenta;
    
    public EditarItem(int indiceDetalleVenta, PanelDeVentas panelPrincipalDeModuloDeVentas) {
        super("/Presentacion/Imagenes/Paneles/Ventas/PanelEdicionListaVenta.png");
        this.panelPrincipalDeModuloDeVentas=panelPrincipalDeModuloDeVentas;
        detalleVentaReferencia = panelPrincipalDeModuloDeVentas.ventaActual.getDetallesVenta().get(indiceDetalleVenta);
        detalleVentaAModificar = new DetalleVenta(detalleVentaReferencia.getProducto(),detalleVentaReferencia.getCantidad(),detalleVentaReferencia.getPrecio(),detalleVentaReferencia.getDescuento());
        detalleVentaAModificar.setIGV(detalleVentaReferencia.isIGV());
        detalleVentaAModificar.setISC(detalleVentaReferencia.isISC());
        
        this.indiceDetalleVenta=indiceDetalleVenta;
        
        setTextoTitulo("EDITAR ITEM");
        setColorTitulo(Color.decode("#8C8C8C"));
        JPanel cuerpo = getCuerpo();
        cuerpo.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets=new Insets(0, 0, 5, 0);

        lblItem=new JLabel(detalleVentaAModificar.getProducto().getNombre());
        lblItem.setFont(UtilidadesFuentes.InterLight.deriveFont(25.0f));
        lblItem.setForeground(Color.decode("#8C8C8C"));
        lblItem.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1.0;
        gbc.gridwidth=2;
        cuerpo.add(lblItem,gbc);
        
        //Añadiendo campos del lado izquierdo
        JPanel mostradorCamposIzquierdo=new JPanel();
        mostradorCamposIzquierdo.setOpaque(false);
        mostradorCamposIzquierdo.setPreferredSize(new Dimension(0,0));
        mostradorCamposIzquierdo.setLayout(new GridBagLayout());
        
        lblCantidad=new JLabel("Cantidad");
        lblCantidad.setFont(UtilidadesFuentes.InterLight.deriveFont(25.0f));
        lblCantidad.setForeground(Color.decode("#8C8C8C"));
        lblCantidad.setHorizontalAlignment(JLabel.CENTER);
        gbc.insets=new Insets(0, 0, 5, 0);
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1.0;
        gbc.gridwidth=3;
        mostradorCamposIzquierdo.add(lblCantidad,gbc);
        
        btnSuma=new JLabel("+");
        btnSuma.setFont(UtilidadesFuentes.InterLight.deriveFont(40.0f));
        btnSuma.setForeground(Color.decode("#6EA6BE"));
        btnSuma.setHorizontalAlignment(JLabel.CENTER);
        btnSuma.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSuma.setPreferredSize(new Dimension(0,0));
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
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=0.15;
        gbc.gridwidth=1;
        mostradorCamposIzquierdo.add(btnSuma,gbc);
        
        txtCantidad=new TextFieldRedondeado(0);
        txtCantidad.setGrosorBorde(4);
        txtCantidad.setRadioDeBorde(40);
        txtCantidad.setColorBorde(Color.decode("#CACACA"));
        txtCantidad.setFont(UtilidadesFuentes.InterBlack.deriveFont(25.0f));
        txtCantidad.setForeground(Color.decode("#8C8C8C"));
        txtCantidad.setHorizontalAlignment(JLabel.CENTER);
        txtCantidad.setBorder( BorderFactory.createEmptyBorder(2, 20, 0, 20) );
        txtCantidad.setText(Integer.toString(detalleVentaAModificar.getCantidad()));
        txtCantidad.setPreferredSize(new Dimension(1,45));
        txtCantidad.setMinimumSize(new Dimension(1,45));
        gbc.gridx=1;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=0.7;
        mostradorCamposIzquierdo.add(txtCantidad,gbc);
        
        
        btnResta=new JLabel("-");
        btnResta.setFont(UtilidadesFuentes.InterLight.deriveFont(40.0f));
        btnResta.setForeground(Color.decode("#D0A47C"));
        btnResta.setHorizontalAlignment(JLabel.CENTER);
        btnResta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnResta.setPreferredSize(new Dimension(0,0));
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
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=0.15;
        gbc.gridwidth=1;
        mostradorCamposIzquierdo.add(btnResta,gbc);
        
        
        lblPrecio=new JLabel("Precio");
        lblPrecio.setFont(UtilidadesFuentes.InterLight.deriveFont(25.0f));
        lblPrecio.setForeground(Color.decode("#8C8C8C"));
        lblPrecio.setHorizontalAlignment(JLabel.CENTER);
     //   gbc.insets=new Insets(0, 60, 5, 0);
        gbc.gridx=0;
        gbc.gridy=2;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1;
        gbc.gridwidth=3;
        mostradorCamposIzquierdo.add(lblPrecio,gbc);
        
        txtPrecio=new TextFieldRedondeado(0);
        txtPrecio.setGrosorBorde(4);
        txtPrecio.setRadioDeBorde(40);
        txtPrecio.setColorBorde(Color.decode("#CACACA"));
        txtPrecio.setFont(UtilidadesFuentes.InterBlack.deriveFont(25.0f));
        txtPrecio.setForeground(Color.decode("#8C8C8C"));
        txtPrecio.setHorizontalAlignment(JLabel.CENTER);
        txtPrecio.setBorder( BorderFactory.createEmptyBorder(2, 20, 0, 20) );
        txtPrecio.setPreferredSize(new Dimension(1,45));
        txtPrecio.setMinimumSize(new Dimension(1,45));
        txtPrecio.setText(Double.toString(detalleVentaAModificar.getPrecio()));
        gbc.gridx=0;
        gbc.gridy=3;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1;
        mostradorCamposIzquierdo.add(txtPrecio,gbc);
        
        lblDescuento=new JLabel("Descuento");
        lblDescuento.setFont(UtilidadesFuentes.InterLight.deriveFont(25.0f));
        lblDescuento.setForeground(Color.decode("#8C8C8C"));
        lblDescuento.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx=0;
        gbc.gridy=4;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1;
        mostradorCamposIzquierdo.add(lblDescuento,gbc);
        
        txtDescuento=new TextFieldRedondeado(0);
        txtDescuento.setGrosorBorde(4);
        txtDescuento.setRadioDeBorde(40);
        txtDescuento.setColorBorde(Color.decode("#CACACA"));
        txtDescuento.setFont(UtilidadesFuentes.InterBlack.deriveFont(25.0f));
        txtDescuento.setForeground(Color.decode("#8C8C8C"));
        txtDescuento.setHorizontalAlignment(JLabel.CENTER);
        txtDescuento.setBorder(BorderFactory.createEmptyBorder(2, 20, 0, 20));
        txtDescuento.setPreferredSize(new Dimension(1,45));
        txtDescuento.setMinimumSize(new Dimension(1,45));
        txtDescuento.setText(Double.toString(detalleVentaAModificar.getDescuento()));
        gbc.gridx=0;
        gbc.gridy=5;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1;
        mostradorCamposIzquierdo.add(txtDescuento,gbc);
        
        //Añadiendo campos del lado derecho
        JPanel mostradorCamposDerecho=new JPanel();
        mostradorCamposDerecho.setOpaque(false);
        mostradorCamposDerecho.setPreferredSize(new Dimension(0,0));
        mostradorCamposDerecho.setLayout(new GridBagLayout());
        
        
        lblImpuestos=new JLabel("Impuestos");
        lblImpuestos.setFont(UtilidadesFuentes.InterLight.deriveFont(25.0f));
        lblImpuestos.setForeground(Color.decode("#8C8C8C"));
        lblImpuestos.setHorizontalAlignment(JLabel.LEFT);
        gbc.anchor=GridBagConstraints.CENTER;
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=0;
        gbc.weighty=0;
        mostradorCamposDerecho.add(lblImpuestos,gbc);
        
        lblIGV=new JLabel("IGV");
        lblIGV.setFont(UtilidadesFuentes.InterLight.deriveFont(22.0f));
        lblIGV.setForeground(Color.decode("#8C8C8C"));
        lblIGV.setHorizontalAlignment(JLabel.CENTER);
        gbc.insets=new Insets(0, 30, 5, 0);
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1;
        gbc.weighty=0;
        gbc.gridwidth=1;
        mostradorCamposDerecho.add(lblIGV,gbc);
        gbc.insets=new Insets(0, 0, 5, 0);
        
        
        lblIGVMonto =new JLabel("Monto: S/. 0.00");
        lblIGVMonto.setFont(UtilidadesFuentes.InterLight.deriveFont(22.0f));
        lblIGVMonto.setForeground(Color.decode("#8C8C8C"));
        lblIGVMonto.setHorizontalAlignment(JLabel.RIGHT);
        gbc.gridx=1;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1;
        //gbc.gridwidth=2;
        mostradorCamposDerecho.add(lblIGVMonto,gbc);
        
        
        lblISC=new JLabel("ISC");
        lblISC.setFont(UtilidadesFuentes.InterLight.deriveFont(22.0f));
        lblISC.setForeground(Color.decode("#8C8C8C"));
        lblISC.setHorizontalAlignment(JLabel.CENTER);
        gbc.insets=new Insets(0, 30, 5, 0);
        gbc.anchor=GridBagConstraints.PAGE_START;
        gbc.gridx=0;
        gbc.gridy=2;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1;
        gbc.weighty=1;
        gbc.gridwidth=1;
        mostradorCamposDerecho.add(lblISC,gbc);
        gbc.insets=new Insets(0, 0, 5, 0);
        
        
        lblISCMonto =new JLabel("Monto: S/. 0.00");
        lblISCMonto.setFont(UtilidadesFuentes.InterLight.deriveFont(22.0f));
        lblISCMonto.setForeground(Color.decode("#8C8C8C"));
        lblISCMonto.setHorizontalAlignment(JLabel.RIGHT);
        gbc.anchor=GridBagConstraints.PAGE_START;
        gbc.gridx=1;
        gbc.gridy=2;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1;
        gbc.weighty=1;
        //gbc.gridwidth=2;
        mostradorCamposDerecho.add(lblISCMonto,gbc);
        //Panel inferior
        JPanel mostradorCamposInferior=new JPanel();
        mostradorCamposInferior.setOpaque(false);
        mostradorCamposInferior.setPreferredSize(new Dimension(0,0));
        mostradorCamposInferior.setLayout(new GridBagLayout());
        
        lblSubTotal =new JLabel(String.format("<html><body style='text-align: center;'>SUBTOTAL<br>S/. %.2f</body></html>",detalleVentaAModificar.getSubTotal()));
        lblSubTotal.setFont(UtilidadesFuentes.InterLight.deriveFont(25.0f));
        lblSubTotal.setForeground(Color.decode("#8C8C8C"));
        lblSubTotal.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=1;
        gbc.gridwidth=1;
        mostradorCamposInferior.add(lblSubTotal,gbc);
        
        lblTotal =new JLabel(String.format("<html><body style='text-align: center;'>TOTAL<br>S/. %.2f</body></html>",detalleVentaAModificar.getSubTotal()-detalleVentaAModificar.getDescuento()));
        lblTotal.setFont(UtilidadesFuentes.InterLight.deriveFont(25.0f));
        lblTotal.setForeground(Color.decode("#8C8C8C"));
        lblTotal.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx=1;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=1;
        gbc.gridwidth=1;
        mostradorCamposInferior.add(lblTotal,gbc);
        
        lblTotalImpuestos =new JLabel(String.format("<html><body style='text-align: center;'>TOTAL. IMP<br>S/. %.2f</body></html>",detalleVentaAModificar.getImpuestos()));
        lblTotalImpuestos.setFont(UtilidadesFuentes.InterLight.deriveFont(25.0f));
        lblTotalImpuestos.setForeground(Color.decode("#8C8C8C"));
        lblTotalImpuestos.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx=2;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=1;
        gbc.gridwidth=1;
        mostradorCamposInferior.add(lblTotalImpuestos,gbc);
        
       //Agregando campos a la pantalla
        
        gbc.insets=new Insets(0, 60, 5, 10);
        gbc.anchor=GridBagConstraints.CENTER;
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=0.5;
        gbc.weighty=0.75;
        gbc.gridwidth=1;
        cuerpo.add(mostradorCamposIzquierdo,gbc);
        gbc.insets=new Insets(0, 10, 5, 60);
        gbc.gridx=1;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=0.5;
        gbc.weighty=0.75;
        cuerpo.add(mostradorCamposDerecho,gbc);
        gbc.insets=new Insets(0, 60, 5, 40);
        gbc.gridx=0;
        gbc.gridy=2;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=1;
        gbc.weighty=0.25;
        gbc.gridwidth=2;
        cuerpo.add(mostradorCamposInferior,gbc);
        
        //Criterio de campos

        DocumentFilter filtroNumericoFlotante = new DocumentFilter(){
            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if(text.matches("[0-9[.]]+")||text.isEmpty()){
                    super.replace(fb, offset, length, text, attrs);
                }
            }
            
        };
        
        DocumentFilter filtroNumericoEntero = new DocumentFilter(){
            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if(text.matches("[0-9]+")||text.isEmpty()){
                    super.replace(fb, offset, length, text, attrs);
                }
            }
            
        };
        
        ((PlainDocument)txtCantidad.getDocument()).setDocumentFilter(filtroNumericoEntero);
        ((PlainDocument)txtPrecio.getDocument()).setDocumentFilter(filtroNumericoFlotante);
        ((PlainDocument)txtDescuento.getDocument()).setDocumentFilter(filtroNumericoFlotante);
        
        //Actualizacion de detalle de venta por campos
        DocumentListener docuPrecioCant = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                try {
                    double precio = Double.parseDouble(txtPrecio.getText());
                    int cantidad = (int)Double.parseDouble(txtCantidad.getText());
                    detalleVentaAModificar.setCantidad(cantidad);
                    detalleVentaAModificar.setPrecio(precio);
                    lblIGVMonto.setText(String.format("Monto: S/. %.2f",precio*0.17*cantidad));           
                    lblISCMonto.setText(String.format("Monto: S/. %.2f",2.72*cantidad));
                } catch (NumberFormatException er) {
                    detalleVentaAModificar.setPrecio(detalleVentaAModificar.getProducto().getPrecio());
                    lblIGVMonto.setText(String.format("Monto: S/. %.2f",0f));
                    lblISCMonto.setText(String.format("Monto: S/. %.2f",0f));
                }
                actualizarDatosItem();
            }

    };
        
        
        txtCantidad.getDocument().addDocumentListener(docuPrecioCant);
        
        txtPrecio.getDocument().addDocumentListener(docuPrecioCant);
        
        txtDescuento.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                try {
                    double descuento = Double.parseDouble(txtDescuento.getText());
                    if(descuento<0){
                        throw new NumberFormatException("El descuento ingresado es negativo");
                    }else{
                        detalleVentaAModificar.setDescuento(descuento);
                    }
                } catch (NumberFormatException er) {
                    detalleVentaAModificar.setDescuento(0);
                    System.err.println(e);
                }
                actualizarDatosItem();
            }

    });
        lblIGVMonto.setText(String.format("Monto: S/. %.2f",detalleVentaAModificar.getPrecio()*0.17*detalleVentaAModificar.getCantidad()));
        
        lblISCMonto.setText(String.format("Monto: S/. %.2f",2.72*detalleVentaAModificar.getCantidad()));
        actualizarDatosItem();
        
        if(!detalleVentaAModificar.getProducto().isActivarDescuentos()){
            txtDescuento.setEnabled(false);
        }
        if(!detalleVentaAModificar.getProducto().isPrecioVariable()){
            txtPrecio.setEnabled(false);
        }
    }
    
    private void actualizarDatosItem(){
        lblSubTotal.setText(String.format("<html><body style='text-align: center;'>SUBTOTAL<br>S/. %.2f</body></html>",detalleVentaAModificar.getSubTotal()));
        lblTotal.setText(String.format("<html><body style='text-align: center;'>TOTAL<br>S/. %.2f</body></html>",detalleVentaAModificar.getTotal()));
        lblTotalImpuestos.setText(String.format("<html><body style='text-align: center;'>TOTAL. IMP<br>S/. %.2f</body></html>",detalleVentaAModificar.getImpuestos()));
    }
    
    @Override
    public void btnAceptarPresionado(MouseEvent evt) {
        detalleVentaReferencia.setCantidad(detalleVentaAModificar.getCantidad());
        detalleVentaReferencia.setPrecio(detalleVentaAModificar.getPrecio());
        detalleVentaReferencia.setDescuento(detalleVentaAModificar.getDescuento());
        detalleVentaReferencia.setIGV(detalleVentaAModificar.isIGV());
        detalleVentaReferencia.setISC(detalleVentaAModificar.isISC());
        panelPrincipalDeModuloDeVentas.modificarDetalleVentaATabla(indiceDetalleVenta, detalleVentaAModificar);
        ((FramePrincipal)((JFrame) SwingUtilities.getWindowAncestor(this))).cerrarPanelesEmergentes();
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
            case "SIGV":
                try {
                    double precio = detalleVentaAModificar.getPrecio();
                    int cantidad = detalleVentaAModificar.getCantidad();
                    detalleVentaAModificar.setIGV((int)evt.getNewValue()==0);
                    if((int)evt.getNewValue()==1){
                        throw new NumberFormatException();
                    }else{
                        double montoIGV = precio*0.17*cantidad;
                        lblIGVMonto.setText(String.format("Monto: S/. %.2f",montoIGV));
                    }
                } catch (NumberFormatException e) {
                    lblIGVMonto.setText("Monto: S/. 0.00");
                }
            break;
            case "SISC":
                detalleVentaAModificar.setISC((int)evt.getNewValue()==0);
                if((int)evt.getNewValue()==0){
                    /*Este sistema no recoge el volumen de los productos,
                    por lo que no es posible obtener un valor de ISC exacto.
                    En este caso estamos suponiendo que las botellas son de
                    750 ml.
                    */
                    double montoISC =2.72*detalleVentaAModificar.getCantidad();
                    lblISCMonto.setText(String.format("Monto: S/. %.2f",montoISC));
                }else{
                    lblISCMonto.setText("Monto: S/. 0.00");
                }
            break;
        }
        actualizarDatosItem();
    }
    
    
}

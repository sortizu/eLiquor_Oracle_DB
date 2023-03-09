package Presentacion.Interfaces.Inventario;

import Datos.Entidades.Producto;
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
public class ModificarProducto extends VentanaEmergente implements PropertyChangeListener{
    
    private PanelDeInventario panelPrincipalDeModuloDeInventario;
    private JLabel lblDPTO;
    private JLabel lblNombre;
    private JLabel lblAlertaNombre;
    private TextFieldRedondeado txtNombre;
    private JLabel lblPrecio;
    private TextFieldRedondeado txtPrecio;
    private JLabel lblCosto;
    private TextFieldRedondeado txtCosto;
    private JLabel lblStock;
    private TextFieldRedondeado txtStock;
    private JLabel lblMostrarEnCaja;
    private Selector selectorMostrarEnCaja;
    private JLabel lblPrecioVariable;
    private Selector selectorPrecioVariable;
    private JLabel lblDescuentos;
    private Selector selectorDescuentos;
    private int[] indice;
    private Producto productoModificado;
    
    public ModificarProducto(PanelDeInventario panelPrincipalDeModuloDeInventario, int[] indice, Producto productoModificado){
        super("/Presentacion/Imagenes/Paneles/Inventario/PanelAgregarProducto.png");
        this.panelPrincipalDeModuloDeInventario=panelPrincipalDeModuloDeInventario;
        this.productoModificado=productoModificado;
        this.indice=indice;
        setTextoTitulo("MODIFICAR PRODUCTO");
        setColorTitulo(Color.decode("#5F7ECD"));
        JPanel cuerpo = getCuerpo();
        cuerpo.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets=new Insets(0, 0, 5, 0);

        lblDPTO=new JLabel(panelPrincipalDeModuloDeInventario.getTablaInventario().getLblTitulo().getText());
        lblDPTO.setFont(UtilidadesFuentes.InterLight.deriveFont(25.0f));
        lblDPTO.setForeground(Color.decode("#8C8C8C"));
        lblDPTO.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1.0;
        gbc.gridwidth=3;
        cuerpo.add(lblDPTO,gbc);
        
        lblNombre=new JLabel("Nombre");
        lblNombre.setFont(UtilidadesFuentes.InterLight.deriveFont(25.0f));
        lblNombre.setForeground(Color.decode("#8C8C8C"));
        lblNombre.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1.0;
        gbc.gridwidth=3;
        cuerpo.add(lblNombre,gbc);
        
        txtNombre=new TextFieldRedondeado(25);
        txtNombre.setGrosorBorde(4);
        txtNombre.setRadioDeBorde(40);
        txtNombre.setColorBorde(Color.decode("#CACACA"));
        txtNombre.setFont(UtilidadesFuentes.InterLight.deriveFont(25.0f));
        txtNombre.setForeground(Color.decode("#8C8C8C"));
        txtNombre.setHorizontalAlignment(JLabel.CENTER);
        txtNombre.setBorder( BorderFactory.createEmptyBorder(2, 20, 0, 20) );
        txtNombre.setPreferredSize(new Dimension(359,45));
        gbc.gridx=0;
        gbc.gridy=2;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        gbc.gridwidth=3;
        cuerpo.add(txtNombre,gbc);
        
        lblAlertaNombre = new JLabel("Asegúrese de poner un nombre");
        lblAlertaNombre.setFont(UtilidadesFuentes.InterRegular.deriveFont(17.0f));
        lblAlertaNombre.setForeground(Color.white);
        lblAlertaNombre.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx=0;
        gbc.gridy=3;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=0;
        gbc.gridwidth=3;
        cuerpo.add(lblAlertaNombre,gbc);
        
        //Añadiendo campos del lado izquierdo
        JPanel mostradorCamposIzquierdo=new JPanel();
        mostradorCamposIzquierdo.setOpaque(false);
        mostradorCamposIzquierdo.setPreferredSize(new Dimension(2,2));
        mostradorCamposIzquierdo.setLayout(new GridBagLayout());
        
        lblPrecio=new JLabel("Precio");
        lblPrecio.setFont(UtilidadesFuentes.InterLight.deriveFont(25.0f));
        lblPrecio.setForeground(Color.decode("#8C8C8C"));
        lblPrecio.setHorizontalAlignment(JLabel.CENTER);
     //   gbc.insets=new Insets(0, 60, 5, 0);
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1;
        gbc.gridwidth=1;
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
        
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1;
        mostradorCamposIzquierdo.add(txtPrecio,gbc);
        
        lblCosto=new JLabel("Costo");
        lblCosto.setFont(UtilidadesFuentes.InterLight.deriveFont(25.0f));
        lblCosto.setForeground(Color.decode("#8C8C8C"));
        lblCosto.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx=0;
        gbc.gridy=2;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1;
        mostradorCamposIzquierdo.add(lblCosto,gbc);
        
        txtCosto=new TextFieldRedondeado(0);
        txtCosto.setGrosorBorde(4);
        txtCosto.setRadioDeBorde(40);
        txtCosto.setColorBorde(Color.decode("#CACACA"));
        txtCosto.setFont(UtilidadesFuentes.InterBlack.deriveFont(25.0f));
        txtCosto.setForeground(Color.decode("#8C8C8C"));
        txtCosto.setHorizontalAlignment(JLabel.CENTER);
        txtCosto.setBorder(BorderFactory.createEmptyBorder(2, 20, 0, 20));
        txtCosto.setPreferredSize(new Dimension(1,45));
        txtCosto.setMinimumSize(new Dimension(1,45));
        
        gbc.gridx=0;
        gbc.gridy=3;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1;
        mostradorCamposIzquierdo.add(txtCosto,gbc);
        
        lblStock=new JLabel("Stock");
        lblStock.setFont(UtilidadesFuentes.InterLight.deriveFont(25.0f));
        lblStock.setForeground(Color.decode("#8C8C8C"));
        lblStock.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx=0;
        gbc.gridy=4;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1;
        mostradorCamposIzquierdo.add(lblStock,gbc);
        
        txtStock=new TextFieldRedondeado(0);
        txtStock.setGrosorBorde(4);
        txtStock.setRadioDeBorde(40);
        txtStock.setColorBorde(Color.decode("#CACACA"));
        txtStock.setFont(UtilidadesFuentes.InterBlack.deriveFont(25.0f));
        txtStock.setForeground(Color.decode("#8C8C8C"));
        txtStock.setHorizontalAlignment(JLabel.CENTER);
        txtStock.setBorder( BorderFactory.createEmptyBorder(2, 20, 0, 20) );
        txtStock.setPreferredSize(new Dimension(1,45));
        txtStock.setMinimumSize(new Dimension(1,45));
        gbc.anchor=GridBagConstraints.NORTH;
        gbc.gridx=0;
        gbc.gridy=5;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1;
        gbc.weighty=1;
        mostradorCamposIzquierdo.add(txtStock,gbc);
        
        
        //Añadiendo campos del lado centro
        JPanel mostradorCamposCentro=new JPanel();
        mostradorCamposCentro.setOpaque(false);
        mostradorCamposCentro.setPreferredSize(new Dimension(2,2));
        mostradorCamposCentro.setLayout(new GridBagLayout());
        
        
        lblMostrarEnCaja=new JLabel("Mostrar en Caja");
        lblMostrarEnCaja.setFont(UtilidadesFuentes.InterLight.deriveFont(25.0f));
        lblMostrarEnCaja.setForeground(Color.decode("#8C8C8C"));
        lblMostrarEnCaja.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1;
        gbc.weighty=0;
        mostradorCamposCentro.add(lblMostrarEnCaja,gbc);
        
        
        selectorMostrarEnCaja=new Selector(new String[]{"SI","NO"},50,45);
        selectorMostrarEnCaja.setFuenteDeOpcion(UtilidadesFuentes.InterRegular.deriveFont(15.0f));
        selectorMostrarEnCaja.setColorDeFuente(Color.decode("#8C8C8C"));
        selectorMostrarEnCaja.solicitarSeleccion(0);
        selectorMostrarEnCaja.addColorDeOpcion(Color.decode("#72AD57"));
        selectorMostrarEnCaja.addColorDeOpcion(Color.decode("#AD5757"));
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        mostradorCamposCentro.add(selectorMostrarEnCaja,gbc);
        
        
        lblPrecioVariable=new JLabel("Precio Variable");
        lblPrecioVariable.setFont(UtilidadesFuentes.InterLight.deriveFont(25.0f));
        lblPrecioVariable.setForeground(Color.decode("#8C8C8C"));
        lblPrecioVariable.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx=0;
        gbc.gridy=2;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1;
        mostradorCamposCentro.add(lblPrecioVariable,gbc);
        
        
        selectorPrecioVariable=new Selector(new String[]{"SI","NO"},50,45);
        selectorPrecioVariable.setFuenteDeOpcion(UtilidadesFuentes.InterRegular.deriveFont(15.0f));
        selectorPrecioVariable.setColorDeFuente(Color.decode("#8C8C8C"));
        selectorPrecioVariable.solicitarSeleccion(0);
        selectorPrecioVariable.addColorDeOpcion(Color.decode("#72AD57"));
        selectorPrecioVariable.addColorDeOpcion(Color.decode("#AD5757"));
        gbc.gridx=0;
        gbc.gridy=3;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        mostradorCamposCentro.add(selectorPrecioVariable,gbc);
        
        
        lblDescuentos=new JLabel("Descuentos");
        lblDescuentos.setFont(UtilidadesFuentes.InterLight.deriveFont(25.0f));
        lblDescuentos.setForeground(Color.decode("#8C8C8C"));
        lblDescuentos.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx=0;
        gbc.gridy=4;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1;
        mostradorCamposCentro.add(lblDescuentos,gbc);
        
        
        selectorDescuentos=new Selector(new String[]{"SI","NO"},50,45);
        selectorDescuentos.setFuenteDeOpcion(UtilidadesFuentes.InterRegular.deriveFont(15.0f));
        selectorDescuentos.setColorDeFuente(Color.decode("#8C8C8C"));
        selectorDescuentos.solicitarSeleccion(0);
        selectorDescuentos.addColorDeOpcion(Color.decode("#72AD57"));
        selectorDescuentos.addColorDeOpcion(Color.decode("#AD5757"));
        gbc.anchor=GridBagConstraints.NORTH;
        gbc.gridx=0;
        gbc.gridy=5;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        gbc.weighty=1;
        mostradorCamposCentro.add(selectorDescuentos,gbc);
        
        //Añadiendo campos del lado derecho
        JPanel mostradorCamposDerecho=new JPanel();
        mostradorCamposDerecho.setOpaque(false);
        mostradorCamposDerecho.setPreferredSize(new Dimension(2,2));
        mostradorCamposDerecho.setLayout(new GridBagLayout());
        
        
        
       //Agregando campos a la pantalla
        
        gbc.insets=new Insets(0, 60, 5, 0);
        gbc.anchor=GridBagConstraints.CENTER;
        gbc.gridx=0;
        gbc.gridy=4;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=0.5;
        gbc.weighty=1;
        gbc.gridwidth=1;
        cuerpo.add(mostradorCamposIzquierdo,gbc);
        gbc.insets=new Insets(0, 0, 5, 0);
        gbc.gridx=1;
        gbc.gridy=4;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=0.5;
        gbc.weighty=1;
        cuerpo.add(mostradorCamposCentro,gbc);
        
        //Criterio de campos
        PlainDocument documentNombre = (PlainDocument) txtNombre.getDocument();
        documentNombre.setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                lblAlertaNombre.setForeground(Color.white);
                super.replace(fb, offset, length, text, attrs);
            }
        });
        
        txtPrecio.getDocument().addDocumentListener(new DocumentListener() {
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
            }

    });

        txtNombre.setText(productoModificado.getNombre());
        txtPrecio.setText(""+productoModificado.getPrecio());
        txtCosto.setText(""+productoModificado.getCosto());
        txtStock.setText(""+productoModificado.getStock());
        selectorMostrarEnCaja.solicitarSeleccion(productoModificado.isMostrarEnCaja()?0:1);
        selectorPrecioVariable.solicitarSeleccion(productoModificado.isPrecioVariable()?0:1);
        selectorDescuentos.solicitarSeleccion(productoModificado.isActivarDescuentos()?0:1);
        
    }

    @Override
    public void btnAceptarPresionado(MouseEvent evt) {
        if(txtNombre.getText().isBlank()){
                lblAlertaNombre.setForeground(new java.awt.Color(224, 130, 130));
        }else{
                
                productoModificado.setNombre(txtNombre.getText());
                try {
                    double precio = Double.parseDouble(txtPrecio.getText());
                    if(precio<0){
                        throw new NumberFormatException("El precio ingresado es negativo");
                    }else{
                        productoModificado.setPrecio(precio);
                    }
                } catch (NumberFormatException e) {
                    productoModificado.setPrecio(0);
                    System.err.println(e);
                }
                try {
                    double costo = Double.parseDouble(txtCosto.getText());
                    if(costo<0){
                        throw new NumberFormatException("El precio ingresado es negativo");
                    }else{
                        productoModificado.setCosto(costo);
                    }
                } catch (NumberFormatException e) {
                    productoModificado.setCosto(0);
                    System.err.println(e);
                }
                try {
                    int stock = Integer.parseInt(txtStock.getText());
                    if(stock<0){
                        throw new NumberFormatException("El precio ingresado es negativo");
                    }else{
                        productoModificado.setStock(stock);
                    }
                } catch (NumberFormatException e) {
                    productoModificado.setStock(0);
                    System.err.println(e);
                }
                productoModificado.setMostrarEnCaja(selectorMostrarEnCaja.getOpcionSeleccionada()==0);
                productoModificado.setPrecioVariable(selectorPrecioVariable.getOpcionSeleccionada()==0);
                productoModificado.setActivarDescuentos(selectorDescuentos.getOpcionSeleccionada()==0);
                ControlInventario.modificarProducto(productoModificado);
                ((FramePrincipal)((JFrame) SwingUtilities.getWindowAncestor(this))).cerrarPanelesEmergentes();
                if(panelPrincipalDeModuloDeInventario.departamentoActual!=null){
                    panelPrincipalDeModuloDeInventario.cargarListaDeProductos(panelPrincipalDeModuloDeInventario.departamentoActual.getIdDepartamento());
                }else{
                    panelPrincipalDeModuloDeInventario.cargarListaDeProductos(-1);
                }
                panelPrincipalDeModuloDeInventario.reiniciarBusqueda();
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
            case "SIGV":
            break;
            case "SISC":
            break;
        }
    }
    
    
}

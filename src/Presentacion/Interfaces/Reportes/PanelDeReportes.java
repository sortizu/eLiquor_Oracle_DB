package Presentacion.Interfaces.Reportes;

import Datos.Entidades.Cliente;
import Datos.Entidades.DetalleVenta;
import Datos.Entidades.Usuario;
import Datos.Entidades.Venta;
import Negocio.ControlReportes;
import Presentacion.Interfaces.BotonRedondeado;
import Presentacion.Interfaces.BotonRedondeadoMultiple;
import Presentacion.Interfaces.PanelModulo;
import Presentacion.Interfaces.PanelRedondeado;
import Presentacion.Interfaces.ScrollBarCustom;
import Presentacion.Interfaces.TablaDefault;
import Presentacion.Interfaces.TextFieldRedondeado;
import Presentacion.Interfaces.ToolTipIcon;
import Presentacion.Utilidades.UtilidadesFuentes;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

/**
 *
 * @author sortizu
 */
public class PanelDeReportes extends JPanel{
    
    private ArrayList<Venta> ventasCargadas=new ArrayList<Venta>();
    
    private PanelModulo panelModuloReportes;
    private Container parent;
    private JPanel cuerpo;
    private TextFieldRedondeado txtFechaInicio;
    private LocalDate fechaInicio;
    private TextFieldRedondeado txtFechaFin;
    private LocalDate fechaFin;
    private BotonRedondeado btnGenerar;
    
    private JScrollPane panelScrollSegmentoResumen;
    private LabelSegmentoResumen lblNumVentas;
    private LabelSegmentoResumen lblValorMercancia;
    private LabelSegmentoResumen lblPromedioVentas;
    private LabelSegmentoResumen lblValorOrdenPromedio;
    private LabelSegmentoResumen lblProductosVendidos;
    private LabelSegmentoResumen lblClientesRegistrados;
    
    private TablaDefault tablaVentas;
    
    private JLabel lblValID;
    private JLabel lblValFecha;
    private JLabel lblValCliente;
    private JLabel lblValCajero;
    private JLabel lblValNumProd;
    private JLabel lblValTotalImp;
    private JLabel lblValTotDesc;
    private JLabel lblValPago;
    private JLabel lblValCambio;
    private JLabel lblValVentaBruta;
    private JLabel lblValVentaNeta;
    private JLabel lblValUtilBruta;
    private JLabel lblValTotCosto;
    private JLabel lblValUtilNeta;
    
    private BotonRedondeadoMultiple botonesAccionReporte;
    private PanelDeReportes panelPrincipalReportes;
    
    public PanelDeReportes(Container parent) {
        this.parent=parent;
        panelPrincipalReportes=this;
        iniciarComponentes();
    }
    private void iniciarComponentes(){
        setOpaque(false);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        panelModuloReportes=new PanelModulo(parent,"/Presentacion/Imagenes/Paneles/Boton Ayuda/TutorialReportes.png");
        panelModuloReportes.setTituloPanelModulo("R E P O R T E S", Color.decode("#408D9D"));
        gbc.insets = new Insets((int)(8.0/panelModuloReportes.basePanelHeight*panelModuloReportes.getPreferredSize().getHeight()), 0, 0, 0);
        gbc.gridx=0;
        gbc.gridy=0;
        add(panelModuloReportes,gbc);
        iniciarComponentesCuerpo();
        MouseAdapter limpiarSeleccion = new MouseAdapter(){
            @Override
            public void mouseReleased(MouseEvent e) {
                tablaVentas.getTabla().clearSelection();
            }
        };
        addMouseListener(limpiarSeleccion);
        panelScrollSegmentoResumen.addMouseListener(limpiarSeleccion);
        actualizarResumen();
    }
    
    private void iniciarComponentesCuerpo(){
        cuerpo = panelModuloReportes.getPanelContenedorComponentes().getCuerpo();
        int width = (int)panelModuloReportes.getPreferredSize().getWidth();
        int height = (int)panelModuloReportes.getPreferredSize().getHeight();
        
        iniciarComponentesCuerpoSuperior(width, height);
        iniciarComponentesCuerpoMedio(width, height);
        iniciarComponentesCuerpoInferior(width, height);
    }
    
    private void iniciarComponentesCuerpoSuperior(int width, int height){
        GridBagConstraints gbc = new GridBagConstraints();
        //Parte superior del cuerpo: Seleccion de fechas
        JPanel panelSeleccionFechas = new JPanel(new GridBagLayout());
        panelSeleccionFechas.setOpaque(false);
        panelSeleccionFechas.setPreferredSize(new Dimension(0,(int)(92.0/panelModuloReportes.basePanelHeight*height)));
        gbc.insets=new Insets((int)(10.0/panelModuloReportes.basePanelHeight*height),0, 0, 0);
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1.0;
        gbc.weighty=0;
        cuerpo.add(panelSeleccionFechas,gbc);
        
        JPanel panelSeleccionFechaInicio = new JPanel(new GridBagLayout());
        panelSeleccionFechaInicio.setOpaque(false);
        gbc.insets=new Insets(0,0, 0, 0);
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.VERTICAL;
        gbc.weightx=0;
        gbc.weighty=1.0;
        panelSeleccionFechas.add(panelSeleccionFechaInicio,gbc);
        
        JLabel lblFechaInicio = new JLabel("FECHA INICIO",JLabel.CENTER);
        lblFechaInicio.setFont(UtilidadesFuentes.InterRegular.deriveFont((float)(21.0/panelModuloReportes.basePanelHeight*height)));
        lblFechaInicio.setForeground(Color.decode("#8C8C8C"));
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=0;
        gbc.weighty=0;
        panelSeleccionFechaInicio.add(lblFechaInicio,gbc);
        
        fechaInicio=LocalDate.now().minusMonths(1);
        
        txtFechaInicio=new TextFieldRedondeado(0);
        txtFechaInicio.setFont(UtilidadesFuentes.InterRegular.deriveFont((float)(28.0/panelModuloReportes.basePanelHeight*height)));
        txtFechaInicio.setForeground(Color.decode("#8C8C8C"));
        txtFechaInicio.setColorBorde(Color.decode("#8C8C8C"));
        txtFechaInicio.setRadioDeBorde((int)(50.0/panelModuloReportes.basePanelWidth*width));
        txtFechaInicio.setGrosorBorde((int)Math.ceil(4.0/panelModuloReportes.basePanelWidth*width));
        txtFechaInicio.setText(fechaInicio.format(DateTimeFormatter.ofPattern("dd/MM/YYYY")));
        txtFechaInicio.setHorizontalAlignment(JLabel.CENTER);
        txtFechaInicio.setPreferredSize(new Dimension((int)(216.0/panelModuloReportes.basePanelWidth*width),0));
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.VERTICAL;
        gbc.weightx=0;
        gbc.weighty=1.0;
        panelSeleccionFechaInicio.add(txtFechaInicio,gbc);
        PlainDocument documentFechInicio = (PlainDocument) txtFechaInicio.getDocument();
        documentFechInicio.setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                String proxDig = fb.getDocument().getText(offset, length+1);
                if(text.matches("[0-9]+")&&!(proxDig.length()>1)){
                    if(proxDig.equals("/")){
                        super.replace(fb, offset+1, length+1, text, attrs);
                        txtFechaInicio.setCaretPosition(offset+2);
                    }else{
                        super.replace(fb, offset, length+1, text, attrs);
                    }
                }
            }

            @Override
            public void remove(DocumentFilter.FilterBypass fb, int offset, int length) throws BadLocationException {
            }
        });
        
        JLabel lblSeparacionFecha = new JLabel("   -   ",JLabel.CENTER);
        lblSeparacionFecha.setFont(UtilidadesFuentes.InterBlack.deriveFont((float)(35.0/panelModuloReportes.basePanelHeight*height)));
        lblSeparacionFecha.setForeground(Color.decode("#8C8C8C"));
        gbc.gridx=1;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.VERTICAL;
        gbc.weightx=0;
        gbc.weighty=0;
        panelSeleccionFechaInicio.add(lblSeparacionFecha,gbc);
        
        
        JPanel panelSeleccionFechaFin = new JPanel(new GridBagLayout());
        panelSeleccionFechaFin.setOpaque(false);
        gbc.gridx=1;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.VERTICAL;
        gbc.weightx=0;
        gbc.weighty=1.0;
        panelSeleccionFechas.add(panelSeleccionFechaFin,gbc);
        
        JLabel lblFechaFin = new JLabel("FECHA FIN",JLabel.CENTER);
        lblFechaFin.setFont(UtilidadesFuentes.InterRegular.deriveFont((float)(21.0/panelModuloReportes.basePanelHeight*height)));
        lblFechaFin.setForeground(Color.decode("#8C8C8C"));
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=0;
        gbc.weighty=0;
        panelSeleccionFechaFin.add(lblFechaFin,gbc);
        
        fechaFin=LocalDate.now();
        
        txtFechaFin=new TextFieldRedondeado(0);
        txtFechaFin.setFont(UtilidadesFuentes.InterRegular.deriveFont((float)(28.0/panelModuloReportes.basePanelHeight*height)));
        txtFechaFin.setForeground(Color.decode("#8C8C8C"));
        txtFechaFin.setColorBorde(Color.decode("#8C8C8C"));
        txtFechaFin.setRadioDeBorde((int)(50.0/panelModuloReportes.basePanelWidth*width));
        txtFechaFin.setGrosorBorde((int)Math.ceil(4.0/panelModuloReportes.basePanelWidth*width));
        txtFechaFin.setText(fechaFin.format(DateTimeFormatter.ofPattern("dd/MM/YYYY")));
        txtFechaFin.setHorizontalAlignment(JLabel.CENTER);
        txtFechaFin.setPreferredSize(new Dimension((int)(216.0/panelModuloReportes.basePanelWidth*width),0));
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.VERTICAL;
        gbc.weightx=0;
        gbc.weighty=1.0;
        panelSeleccionFechaFin.add(txtFechaFin,gbc);
        PlainDocument documentFechaFin = (PlainDocument) txtFechaFin.getDocument();
        documentFechaFin.setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                String proxDig = fb.getDocument().getText(offset, length+1);
                if(text.matches("[0-9]+")&&!(proxDig.length()>1)){
                    if(proxDig.equals("/")){
                        super.replace(fb, offset+1, length+1, text, attrs);
                        txtFechaFin.setCaretPosition(offset+2);
                    }else{
                        super.replace(fb, offset, length+1, text, attrs);
                    }
                }
            }

            @Override
            public void remove(DocumentFilter.FilterBypass fb, int offset, int length) throws BadLocationException {
            }
            
        });
        
        
        btnGenerar = new BotonRedondeado((int)(20.0/panelModuloReportes.basePanelWidth*width), (int)Math.floor(5.0/panelModuloReportes.basePanelWidth*width), Color.decode("#8C8C8C"),"GENERAR", UtilidadesFuentes.InterBold.deriveFont((float)(21.0/panelModuloReportes.basePanelHeight*height))){
            @Override
            public void botonPresionado() {
                try{
                    String [] strFechaInicio=txtFechaInicio.getText().split("/");
                    String [] strFechaFin=txtFechaFin.getText().split("/");
                    LocalDate nuevaFechaInicio=LocalDate.of(Integer.parseInt(strFechaInicio[2]),Integer.parseInt(strFechaInicio[1]),Integer.parseInt(strFechaInicio[0]));
                    LocalDate nuevaFechaFin=LocalDate.of(Integer.parseInt(strFechaFin[2]),Integer.parseInt(strFechaFin[1]),Integer.parseInt(strFechaFin[0]));
                    ventasCargadas=ControlReportes.cargarVentas(nuevaFechaInicio, nuevaFechaFin);
                    fechaInicio=nuevaFechaInicio;
                    fechaFin=nuevaFechaFin;
                    mostrarVentasCargadasEnTabla();
                    actualizarResumen();
                }
                catch(Exception er){System.err.println(er);}
            }
            
        };
        btnGenerar.setPreferredSize(new Dimension((int)(140.0/panelModuloReportes.basePanelWidth*width),(int)(55.0/panelModuloReportes.basePanelHeight*height)));
        gbc.insets=new Insets(0,(int)(35.0/panelModuloReportes.basePanelWidth*width), 0, 0);
        gbc.gridx=1;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        gbc.weighty=0;
        panelSeleccionFechaFin.add(btnGenerar,gbc);
    }
    
    private void iniciarComponentesCuerpoMedio(int width, int height){
        GridBagConstraints gbc = new GridBagConstraints();
        PanelRedondeado panelRedondeadoContenedorReporte = new PanelRedondeado((int)(20.0/panelModuloReportes.basePanelWidth*width),(int)Math.ceil(1.0/panelModuloReportes.basePanelWidth*width),new  Color(0,0,0,0),Color.decode("#D0D0D0"));
        panelRedondeadoContenedorReporte.setOpaque(false);
        panelRedondeadoContenedorReporte.setPreferredSize(new Dimension((int)(1687.0/panelModuloReportes.basePanelWidth*width),(int)(658.0/panelModuloReportes.basePanelHeight*height)));
        panelRedondeadoContenedorReporte.setLayout(new GridBagLayout());
        gbc.insets=new Insets((int)(10.0/panelModuloReportes.basePanelHeight*height),0, 0, 0);
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        gbc.weighty=0;
        cuerpo.add(panelRedondeadoContenedorReporte,gbc);
        
        Dimension dimPanelSeg = new Dimension((int)(535.0/(double)panelModuloReportes.basePanelWidth*(double)width),0);
        
        panelScrollSegmentoResumen = new JScrollPane();
        panelScrollSegmentoResumen.setPreferredSize(dimPanelSeg);
        panelScrollSegmentoResumen.setMaximumSize(dimPanelSeg);
        panelScrollSegmentoResumen.setMinimumSize(dimPanelSeg);
        panelScrollSegmentoResumen.setOpaque(false);
        panelScrollSegmentoResumen.getViewport().setOpaque(false);
        int bordePanelScroll = (int)(10.0/panelModuloReportes.basePanelWidth*(double)width);
        panelScrollSegmentoResumen.setBorder(new EmptyBorder(bordePanelScroll, bordePanelScroll, bordePanelScroll, bordePanelScroll));
        gbc.insets=new Insets(0, 0, 0, 0);
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.VERTICAL;
        gbc.weightx=0;
        gbc.weighty=1.0;
        gbc.gridheight=3;
        panelRedondeadoContenedorReporte.add(panelScrollSegmentoResumen,gbc);
        
        JPanel panelContenedorSegmentos = new JPanel(new GridBagLayout());
        panelContenedorSegmentos.setOpaque(false);
        panelScrollSegmentoResumen.setViewportView(panelContenedorSegmentos);
        
        ScrollBarCustom scrollBarPanelSegmentos=new ScrollBarCustom(panelScrollSegmentoResumen);
        scrollBarPanelSegmentos.setPreferredSize(new Dimension((int)(30.0/panelModuloReportes.basePanelWidth*width),0));
        scrollBarPanelSegmentos.setUnitIncrement(20);
        panelScrollSegmentoResumen.setVerticalScrollBar(scrollBarPanelSegmentos);
        panelScrollSegmentoResumen.setHorizontalScrollBar(null);
        
        
        
        lblNumVentas=new LabelSegmentoResumen("NÚMERO DE VENTAS", "0", "Cantidad de ventas realizadas entre la fecha de inicio y fin", new Dimension((int)(230.0/panelModuloReportes.basePanelWidth*width),(int)(220.0/panelModuloReportes.basePanelHeight*height)));
        gbc.insets=new Insets(0, 0, 0, (int)(15.0/(double)panelModuloReportes.basePanelWidth*(double)width));
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        gbc.weighty=0;
        gbc.gridheight=1;
        panelContenedorSegmentos.add(lblNumVentas,gbc);
        
        lblValorMercancia=new LabelSegmentoResumen("VALOR DE MERCANCÍA EN TIENDA", "S/.0.0", "Suma total del costo de todos los productos registrados en el inventario", new Dimension((int)(230.0/panelModuloReportes.basePanelWidth*width),(int)(220.0/panelModuloReportes.basePanelHeight*height)));
        gbc.gridx=1;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        gbc.weighty=0;
        panelContenedorSegmentos.add(lblValorMercancia,gbc);
        
        lblPromedioVentas=new LabelSegmentoResumen("PROMEDIO DE VENTAS POR DÍA", "0", "Promedio de ventas realizadas en un día dentro de la fecha inicio y fin indicadas", new Dimension((int)(230.0/panelModuloReportes.basePanelWidth*width),(int)(220.0/panelModuloReportes.basePanelHeight*height)));
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        gbc.weighty=0;
        panelContenedorSegmentos.add(lblPromedioVentas,gbc);
        
        lblValorOrdenPromedio=new LabelSegmentoResumen("VALOR DE ORDEN PROMEDIO", "S/.0.0", "Promedio del valor de Venta Bruta de cada orden de venta producida entre la fecha inicio y fin indicadas", new Dimension((int)(230.0/panelModuloReportes.basePanelWidth*width),(int)(220.0/panelModuloReportes.basePanelHeight*height)));
        gbc.gridx=1;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        gbc.weighty=0;
        panelContenedorSegmentos.add(lblValorOrdenPromedio,gbc);
        
        lblProductosVendidos=new LabelSegmentoResumen("PRODUCTOS VENDIDOS", "0", "Cantidad de productos vendidos entre las fecha inicio y fin indicadas", new Dimension((int)(230.0/panelModuloReportes.basePanelWidth*width),(int)(220.0/panelModuloReportes.basePanelHeight*height)));
        gbc.gridx=0;
        gbc.gridy=2;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        gbc.weighty=0;
        panelContenedorSegmentos.add(lblProductosVendidos,gbc);
        
        lblClientesRegistrados=new LabelSegmentoResumen("CLIENTES REGISTRADOS", "0", "Cantidad de clientes registrados por todos los usuarios en el sistema", new Dimension((int)(230.0/panelModuloReportes.basePanelWidth*width),(int)(220.0/panelModuloReportes.basePanelHeight*height)));
        gbc.gridx=1;
        gbc.gridy=2;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        gbc.weighty=0;
        panelContenedorSegmentos.add(lblClientesRegistrados,gbc);
        
        JSeparator separadorResumenTabla=new JSeparator(JSeparator.VERTICAL);
        separadorResumenTabla.setForeground(Color.decode("#D0D0D0"));
        separadorResumenTabla.setBorder(new LineBorder(Color.decode("#D0D0D0"),(int)(1.0/panelModuloReportes.basePanelWidth*width)));
        gbc.insets=new Insets(0, 0, 0,0);
        gbc.gridx=1;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.VERTICAL;
        gbc.weightx=0;
        gbc.weighty=0;
        gbc.gridheight=3;
        panelRedondeadoContenedorReporte.add(separadorResumenTabla,gbc);
        
        Dimension dimPanelTabla = new Dimension(0,(int)(420.0/(double)panelModuloReportes.basePanelHeight*(double)height));
        tablaVentas = new TablaDefault(new String[]{"ID","Fecha","Cajero","#Productos","Venta Bruta","Venta Neta"}, new int[]{100,100,200,100,100,100},panelModuloReportes);
        int bordeTabla = (int)(10.0/(double)panelModuloReportes.basePanelHeight*(double)height);
        tablaVentas.setPreferredSize(dimPanelTabla);
        tablaVentas.setMaximumSize(dimPanelTabla);
        tablaVentas.setMinimumSize(dimPanelTabla);
        tablaVentas.getTabla().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaVentas.getScrollBarTabla().setPreferredSize(new Dimension((int)(30.0/panelModuloReportes.basePanelWidth*width),0));
        tablaVentas.getTabla().getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                actualizarDetalleVentas(tablaVentas.getTabla().getSelectedRow());
            }
        });
        gbc.insets=new Insets(bordeTabla, bordeTabla, 0, bordeTabla);
        gbc.gridx=2;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1.0;
        gbc.weighty=0;
        gbc.gridheight=1;
        panelRedondeadoContenedorReporte.add(tablaVentas,gbc);
        
        JSeparator separadorDetalleTabla=new JSeparator(JSeparator.HORIZONTAL);
        separadorDetalleTabla.setForeground(Color.decode("#D0D0D0"));
        separadorDetalleTabla.setOpaque(false);
        separadorDetalleTabla.setBorder(new LineBorder(Color.decode("#D0D0D0"),(int)Math.ceil(1.0/panelModuloReportes.basePanelHeight*height)));
        gbc.insets=new Insets(0, 0, 0,0);
        gbc.gridx=2;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=0;
        gbc.weighty=0;
        panelRedondeadoContenedorReporte.add(separadorDetalleTabla,gbc);
        
        //Panel Detalle Venta
        JPanel panelDetalleVenta=new JPanel(new GridBagLayout());
        panelDetalleVenta.setOpaque(false);
        gbc.gridx=2;
        gbc.gridy=2;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=0;
        gbc.weighty=0;
        panelRedondeadoContenedorReporte.add(panelDetalleVenta,gbc);
        
        
        double dimFuenteDetalleVenta=21;
        Font fuenteDetalleVenta = UtilidadesFuentes.InterRegular.deriveFont((float)Math.ceil(dimFuenteDetalleVenta/panelModuloReportes.basePanelHeight*height));
        Color colorfuenteDetalleventa = Color.decode("#8C8C8C");
        Dimension dimlbl=new Dimension((int)(235/panelModuloReportes.basePanelWidth*width),(int)(25/panelModuloReportes.basePanelHeight*height));
        Dimension dimlblVal=new Dimension((int)(140/panelModuloReportes.basePanelWidth*width),(int)(25/panelModuloReportes.basePanelHeight*height));
        double separacionlbl=10/panelModuloReportes.basePanelWidth*width;
        Insets margenlbl = new Insets((int)(separacionlbl/1.5), (int)separacionlbl, 0, 0);
        
        JLabel lblDetalleVenta=new JLabel("DETALLE VENTA",JLabel.LEFT);
        lblDetalleVenta.setForeground(colorfuenteDetalleventa);
        lblDetalleVenta.setFont(fuenteDetalleVenta);
        gbc.insets=margenlbl;
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=0;
        gbc.weighty=0;
        gbc.gridwidth=4;
        panelDetalleVenta.add(lblDetalleVenta,gbc);
        
        JLabel lblID=new JLabel("ID:",JLabel.RIGHT);
        lblID.setPreferredSize(dimlbl);
        lblID.setMaximumSize(dimlbl);
        lblID.setMinimumSize(dimlbl);
        lblID.setForeground(colorfuenteDetalleventa);
        lblID.setFont(fuenteDetalleVenta);
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        gbc.weighty=0;
        gbc.gridwidth=1;
        panelDetalleVenta.add(lblID,gbc);
        
        lblValID=new JLabel("");
        lblValID.setPreferredSize(dimlblVal);
        lblValID.setMaximumSize(dimlblVal);
        lblValID.setMinimumSize(dimlblVal);
        lblValID.setForeground(colorfuenteDetalleventa);
        lblValID.setFont(fuenteDetalleVenta);
        gbc.gridx=1;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        gbc.weighty=0;
        panelDetalleVenta.add(lblValID,gbc);
        
        JLabel lblTotDesc=new JLabel("TOTAL DESCUENTOS:",JLabel.RIGHT);
        lblTotDesc.setPreferredSize(dimlbl);
        lblTotDesc.setMaximumSize(dimlbl);
        lblTotDesc.setMinimumSize(dimlbl);
        lblTotDesc.setForeground(colorfuenteDetalleventa);
        lblTotDesc.setFont(fuenteDetalleVenta);
        gbc.gridx=2;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        gbc.weighty=0;
        panelDetalleVenta.add(lblTotDesc,gbc);
        
        lblValTotDesc=new JLabel("");
        lblValTotDesc.setPreferredSize(dimlblVal);
        lblValTotDesc.setMaximumSize(dimlblVal);
        lblValTotDesc.setMinimumSize(dimlblVal);
        lblValTotDesc.setForeground(colorfuenteDetalleventa);
        lblValTotDesc.setFont(fuenteDetalleVenta);
        gbc.gridx=3;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        gbc.weighty=0;
        panelDetalleVenta.add(lblValTotDesc,gbc);
        
        JLabel lblFecha=new JLabel("FECHA:",JLabel.RIGHT);
        lblFecha.setPreferredSize(dimlbl);
        lblFecha.setMaximumSize(dimlbl);
        lblFecha.setMinimumSize(dimlbl);
        lblFecha.setForeground(colorfuenteDetalleventa);
        lblFecha.setFont(fuenteDetalleVenta);
        gbc.gridx=0;
        gbc.gridy=2;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        gbc.weighty=0;
        gbc.gridwidth=1;
        panelDetalleVenta.add(lblFecha,gbc);
        
        lblValFecha=new JLabel("");
        lblValFecha.setPreferredSize(dimlblVal);
        lblValFecha.setMaximumSize(dimlblVal);
        lblValFecha.setMinimumSize(dimlblVal);
        lblValFecha.setForeground(colorfuenteDetalleventa);
        lblValFecha.setFont(fuenteDetalleVenta);
        gbc.gridx=1;
        gbc.gridy=2;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        gbc.weighty=0;
        panelDetalleVenta.add(lblValFecha,gbc);
        
        JLabel lblPago=new JLabel("PAGO:",JLabel.RIGHT);
        lblPago.setPreferredSize(dimlbl);
        lblPago.setMaximumSize(dimlbl);
        lblPago.setMinimumSize(dimlbl);
        lblPago.setForeground(colorfuenteDetalleventa);
        lblPago.setFont(fuenteDetalleVenta);
        gbc.gridx=2;
        gbc.gridy=2;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        gbc.weighty=0;
        panelDetalleVenta.add(lblPago,gbc);
        
        lblValPago=new JLabel("");
        lblValPago.setPreferredSize(dimlblVal);
        lblValPago.setMaximumSize(dimlblVal);
        lblValPago.setMinimumSize(dimlblVal);
        lblValPago.setForeground(colorfuenteDetalleventa);
        lblValPago.setFont(fuenteDetalleVenta);
        gbc.gridx=3;
        gbc.gridy=2;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        gbc.weighty=0;
        panelDetalleVenta.add(lblValPago,gbc);
        
        JLabel lblCliente=new JLabel("CLIENTE:",JLabel.RIGHT);
        lblCliente.setPreferredSize(dimlbl);
        lblCliente.setMaximumSize(dimlbl);
        lblCliente.setMinimumSize(dimlbl);
        lblCliente.setForeground(colorfuenteDetalleventa);
        lblCliente.setFont(fuenteDetalleVenta);
        gbc.gridx=0;
        gbc.gridy=3;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        gbc.weighty=0;
        gbc.gridwidth=1;
        panelDetalleVenta.add(lblCliente,gbc);
        
        lblValCliente=new JLabel("");
        lblValCliente.setPreferredSize(dimlblVal);
        lblValCliente.setMaximumSize(dimlblVal);
        lblValCliente.setMinimumSize(dimlblVal);
        lblValCliente.setForeground(colorfuenteDetalleventa);
        lblValCliente.setFont(fuenteDetalleVenta);
        gbc.gridx=1;
        gbc.gridy=3;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        gbc.weighty=0;
        panelDetalleVenta.add(lblValCliente,gbc);
        
        JLabel lblCambio=new JLabel("CAMBIO:",JLabel.RIGHT);
        lblCambio.setPreferredSize(dimlbl);
        lblCambio.setMaximumSize(dimlbl);
        lblCambio.setMinimumSize(dimlbl);
        lblCambio.setForeground(colorfuenteDetalleventa);
        lblCambio.setFont(fuenteDetalleVenta);
        gbc.gridx=2;
        gbc.gridy=3;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        gbc.weighty=0;
        panelDetalleVenta.add(lblCambio,gbc);
        
        lblValCambio=new JLabel("");
        lblValCambio.setPreferredSize(dimlblVal);
        lblValCambio.setMaximumSize(dimlblVal);
        lblValCambio.setMinimumSize(dimlblVal);
        lblValCambio.setForeground(colorfuenteDetalleventa);
        lblValCambio.setFont(fuenteDetalleVenta);
        gbc.gridx=3;
        gbc.gridy=3;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        gbc.weighty=0;
        panelDetalleVenta.add(lblValCambio,gbc);
        
        JLabel lblCajero=new JLabel("CAJERO:",JLabel.RIGHT);
        lblCajero.setPreferredSize(dimlbl);
        lblCajero.setMaximumSize(dimlbl);
        lblCajero.setMinimumSize(dimlbl);
        lblCajero.setForeground(colorfuenteDetalleventa);
        lblCajero.setFont(fuenteDetalleVenta);
        gbc.gridx=0;
        gbc.gridy=4;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        gbc.weighty=0;
        gbc.gridwidth=1;
        panelDetalleVenta.add(lblCajero,gbc);
        
        lblValCajero=new JLabel("");
        lblValCajero.setPreferredSize(dimlblVal);
        lblValCajero.setMaximumSize(dimlblVal);
        lblValCajero.setMinimumSize(dimlblVal);
        lblValCajero.setForeground(colorfuenteDetalleventa);
        lblValCajero.setFont(fuenteDetalleVenta);
        gbc.gridx=1;
        gbc.gridy=4;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        gbc.weighty=0;
        panelDetalleVenta.add(lblValCajero,gbc);
        
        JLabel lblVentaBruta=new JLabel("VENTA BRUTA:",JLabel.RIGHT);
        lblVentaBruta.setPreferredSize(dimlbl);
        lblVentaBruta.setMaximumSize(dimlbl);
        lblVentaBruta.setMinimumSize(dimlbl);
        lblVentaBruta.setForeground(colorfuenteDetalleventa);
        lblVentaBruta.setFont(fuenteDetalleVenta);
        gbc.gridx=2;
        gbc.gridy=4;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        gbc.weighty=0;
        panelDetalleVenta.add(lblVentaBruta,gbc);
        
        lblValVentaBruta=new JLabel("");
        lblValVentaBruta.setPreferredSize(dimlblVal);
        lblValVentaBruta.setMaximumSize(dimlblVal);
        lblValVentaBruta.setMinimumSize(dimlblVal);
        lblValVentaBruta.setForeground(colorfuenteDetalleventa);
        lblValVentaBruta.setFont(fuenteDetalleVenta);
        gbc.gridx=3;
        gbc.gridy=4;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        gbc.weighty=0;
        panelDetalleVenta.add(lblValVentaBruta,gbc);
        
        JLabel lblNumProd=new JLabel("# PRODUCTOS:",JLabel.RIGHT);
        lblNumProd.setPreferredSize(dimlbl);
        lblNumProd.setMaximumSize(dimlbl);
        lblNumProd.setMinimumSize(dimlbl);
        lblNumProd.setForeground(colorfuenteDetalleventa);
        lblNumProd.setFont(fuenteDetalleVenta);
        gbc.gridx=0;
        gbc.gridy=5;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        gbc.weighty=0;
        gbc.gridwidth=1;
        panelDetalleVenta.add(lblNumProd,gbc);
        
        lblValNumProd=new JLabel("");
        lblValNumProd.setPreferredSize(dimlblVal);
        lblValNumProd.setMaximumSize(dimlblVal);
        lblValNumProd.setMinimumSize(dimlblVal);
        lblValNumProd.setForeground(colorfuenteDetalleventa);
        lblValNumProd.setFont(fuenteDetalleVenta);
        gbc.gridx=1;
        gbc.gridy=5;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        gbc.weighty=0;
        panelDetalleVenta.add(lblValNumProd,gbc);
        
        JLabel lblVentaNeta=new JLabel("VENTA NETA:",JLabel.RIGHT);
        lblVentaNeta.setPreferredSize(dimlbl);
        lblVentaNeta.setMaximumSize(dimlbl);
        lblVentaNeta.setMinimumSize(dimlbl);
        lblVentaNeta.setForeground(colorfuenteDetalleventa);
        lblVentaNeta.setFont(fuenteDetalleVenta);
        gbc.gridx=2;
        gbc.gridy=5;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        gbc.weighty=0;
        panelDetalleVenta.add(lblVentaNeta,gbc);
        
        lblValVentaNeta=new JLabel("");
        lblValVentaNeta.setPreferredSize(dimlblVal);
        lblValVentaNeta.setMaximumSize(dimlblVal);
        lblValVentaNeta.setMinimumSize(dimlblVal);
        lblValVentaNeta.setForeground(colorfuenteDetalleventa);
        lblValVentaNeta.setFont(fuenteDetalleVenta);
        gbc.gridx=3;
        gbc.gridy=5;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        gbc.weighty=0;
        panelDetalleVenta.add(lblValVentaNeta,gbc);
        
        JLabel lblTotImp=new JLabel("TOTAL IMPUESTOS:",JLabel.RIGHT);
        lblTotImp.setPreferredSize(dimlbl);
        lblTotImp.setMaximumSize(dimlbl);
        lblTotImp.setMinimumSize(dimlbl);
        lblTotImp.setForeground(colorfuenteDetalleventa);
        lblTotImp.setFont(fuenteDetalleVenta);
        gbc.anchor=GridBagConstraints.PAGE_START;
        gbc.gridx=0;
        gbc.gridy=6;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        gbc.weighty=1;
        gbc.gridwidth=1;
        panelDetalleVenta.add(lblTotImp,gbc);
        
        lblValTotalImp=new JLabel("");
        lblValTotalImp.setPreferredSize(dimlblVal);
        lblValTotalImp.setMaximumSize(dimlblVal);
        lblValTotalImp.setMinimumSize(dimlblVal);
        lblValTotalImp.setForeground(colorfuenteDetalleventa);
        lblValTotalImp.setFont(fuenteDetalleVenta);
        gbc.anchor=GridBagConstraints.PAGE_START;
        gbc.gridx=1;
        gbc.gridy=6;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        gbc.weighty=1;
        panelDetalleVenta.add(lblValTotalImp,gbc);
        
        
        JSeparator separadorDetalleGanancia=new JSeparator(JSeparator.VERTICAL);
        separadorDetalleTabla.setForeground(Color.decode("#D0D0D0"));
        separadorDetalleTabla.setOpaque(false);
        separadorDetalleTabla.setBorder(new LineBorder(Color.decode("#D0D0D0"),(int)Math.ceil(1.0/panelModuloReportes.basePanelWidth*width)));
        gbc.insets=new Insets(0, 0, 0,0);
        gbc.gridx=4;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.VERTICAL;
        gbc.weightx=0;
        gbc.weighty=1;
        gbc.gridheight=7;
        panelDetalleVenta.add(separadorDetalleGanancia,gbc);
        
        //Ganancias

        JLabel lblGanancia=new JLabel("GANANCIAS",JLabel.LEFT);
        lblGanancia.setForeground(colorfuenteDetalleventa);
        lblGanancia.setFont(fuenteDetalleVenta);
        gbc.anchor=GridBagConstraints.FIRST_LINE_START;
        gbc.insets=margenlbl;
        gbc.gridx=5;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1;
        gbc.weighty=0;
        gbc.gridwidth=2;
        panelDetalleVenta.add(lblGanancia,gbc);
        
        JLabel lblTotCosto=new JLabel("TOTAL COSTO:",JLabel.RIGHT);
        lblTotCosto.setForeground(colorfuenteDetalleventa);
        lblTotCosto.setFont(fuenteDetalleVenta);
        gbc.gridx=5;
        gbc.gridy=1;
        gbc.weightx=0;
        gbc.weighty=0;
        gbc.gridwidth=1;
        panelDetalleVenta.add(lblTotCosto,gbc);
        
        lblValTotCosto=new JLabel("");
        lblValTotCosto.setForeground(colorfuenteDetalleventa);
        lblValTotCosto.setFont(fuenteDetalleVenta);
        gbc.gridx=6;
        gbc.gridy=1;
        gbc.weightx=0;
        gbc.weighty=0;
        panelDetalleVenta.add(lblValTotCosto,gbc);
        
        JLabel lblUtilBruta=new JLabel("UTILIDAD BRUTA:",JLabel.RIGHT);
        lblUtilBruta.setForeground(colorfuenteDetalleventa);
        lblUtilBruta.setFont(fuenteDetalleVenta);
        gbc.gridx=5;
        gbc.gridy=2;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=0;
        gbc.weighty=0;
        gbc.gridwidth=1;
        panelDetalleVenta.add(lblUtilBruta,gbc);
        
        lblValUtilBruta=new JLabel("");
        lblValUtilBruta.setForeground(colorfuenteDetalleventa);
        lblValUtilBruta.setFont(fuenteDetalleVenta);
        gbc.insets=margenlbl;
        gbc.gridx=6;
        gbc.gridy=2;
        gbc.weightx=0;
        gbc.weighty=0;
        panelDetalleVenta.add(lblValUtilBruta,gbc);
        
        JLabel lblUtilNeta=new JLabel("UTILIDAD NETA:",JLabel.RIGHT);
        lblUtilNeta.setForeground(colorfuenteDetalleventa);
        lblUtilNeta.setFont(fuenteDetalleVenta);
        gbc.gridx=5;
        gbc.gridy=3;
        gbc.weightx=0;
        gbc.weighty=0;
        gbc.gridwidth=1;
        panelDetalleVenta.add(lblUtilNeta,gbc);
        
        lblValUtilNeta=new JLabel("");
        lblValUtilNeta.setForeground(colorfuenteDetalleventa);
        lblValUtilNeta.setFont(fuenteDetalleVenta);
        gbc.gridx=6;
        gbc.gridy=3;
        gbc.weightx=0;
        gbc.weighty=0;
        panelDetalleVenta.add(lblValUtilNeta,gbc);
    }
    
    private void iniciarComponentesCuerpoInferior(int width, int height){
        /*GridBagConstraints gbc = new GridBagConstraints();
        botonesAccionReporte=new BotonRedondeadoMultiple((int)(20.0/panelModuloReportes.basePanelHeight*height), (int)Math.ceil(3.0/panelModuloReportes.basePanelHeight*height), Color.decode("#8C8C8C"), new String[]{"EXPORTAR","IMPRIMIR"},UtilidadesFuentes.InterRegular.deriveFont((float)(28.0/panelModuloReportes.basePanelHeight*height)),new Dimension((int)(235.0/panelModuloReportes.basePanelWidth*width),(int)(75.0/panelModuloReportes.basePanelHeight*height))){
            @Override
            public void botonOpcionPresionado(int opcionPresionada) {
                if(opcionPresionada==0){
                    ExportarReporte exportarReporte = new ExportarReporte(panelModuloReportes);
                    ((FramePrincipal) SwingUtilities.getWindowAncestor(panelPrincipalReportes)).mostrarPanelEmergente(exportarReporte);
                exportarReporte.requestFocus();
                }
            }        
        };
        gbc.insets=new Insets((int)(15.0/panelModuloReportes.basePanelHeight*height),0, 0, 0);
        gbc.gridx=0;
        gbc.gridy=2;
        gbc.fill=GridBagConstraints.NONE;
        gbc.anchor=GridBagConstraints.PAGE_START;
        gbc.weightx=0;
        gbc.weighty=1.0;
        cuerpo.add(botonesAccionReporte,gbc);*/
    }
    
    private void actualizarResumen(){
        long cantidadDias=ChronoUnit.DAYS.between(fechaInicio,fechaFin);
        int numVentas = ventasCargadas.size();
        double valorMercancia= ControlReportes.obtenerValorMercanciaEnTienda();
        long promVentas=numVentas;
        double valorOrdenProm=0;
        int prodVendidos=0;
        int clientesRegistrados=ControlReportes.obtenerTotalClientesRegistrados();
        
        for (Venta v:ventasCargadas) {
            valorOrdenProm+=v.getVentaBruta();
            for(DetalleVenta dv:v.getDetallesVenta()){
                prodVendidos+=dv.getCantidad();
            }
        }
        if(numVentas>0){
            valorOrdenProm=valorOrdenProm/numVentas;
        }
        if(cantidadDias>0){
            promVentas=promVentas/cantidadDias;
        }
        
         lblNumVentas.getValorInfoResumen().setText(Integer.toString(numVentas));
         lblNumVentas.setToolTipText(Integer.toString(numVentas));
         lblValorMercancia.getValorInfoResumen().setText("S/."+Double.toString(valorMercancia));
         lblValorMercancia.setToolTipText("S/."+Double.toString(valorMercancia));
         lblPromedioVentas.getValorInfoResumen().setText(Long.toString(promVentas));
         lblPromedioVentas.setToolTipText(Long.toString(promVentas));
         lblValorOrdenPromedio.getValorInfoResumen().setText("S/."+Double.toString(valorOrdenProm));
         lblValorOrdenPromedio.setToolTipText("S/."+Double.toString(valorOrdenProm));
         lblProductosVendidos.getValorInfoResumen().setText(Integer.toString(prodVendidos));
         lblProductosVendidos.setToolTipText(Integer.toString(prodVendidos));
         lblClientesRegistrados.getValorInfoResumen().setText(Integer.toString(clientesRegistrados));
         lblClientesRegistrados.setToolTipText(Integer.toString(clientesRegistrados));
    }
    
    private void mostrarVentasCargadasEnTabla(){
        tablaVentas.getModeloTabla().setRowCount(0);
        for(Venta v:ventasCargadas){
            Usuario usuarioventa=ControlReportes.cargarUsuario(v.getIdUsuario());
            
            Object [] datosventa = new Object[6];
            datosventa[0]=v.getIdVenta();
            datosventa[1]=v.getFechaRegistro().format(DateTimeFormatter.ofPattern("dd/MM/YYYY"));
            if(usuarioventa!=null){
                datosventa[2]=usuarioventa.getNombre();
            }else{
                datosventa[2]="No especificado";
            }
            int numProd=0;
            for(DetalleVenta dv:v.getDetallesVenta()){
                numProd+=dv.getCantidad();
            }
            datosventa[3]=numProd;
            datosventa[4]=v.getVentaBruta();
            datosventa[5]=v.getVentaNeta();
            tablaVentas.getModeloTabla().addRow(datosventa);
        }
    }
    
    private void actualizarDetalleVentas(int fila){
        if(fila>=0){
            Venta v=ventasCargadas.get(fila);
            Usuario usuarioVenta=ControlReportes.cargarUsuario(v.getIdUsuario());
            Cliente clienteVenta=ControlReportes.cargarCliente(v.getIdCliente());
            lblValID.setText(Integer.toString(v.getIdVenta()));
            lblValFecha.setText(v.getFechaRegistro().format(DateTimeFormatter.ofPattern("dd/MM/YYYY")));
            if(clienteVenta!=null){
                lblValCliente.setText(clienteVenta.getNombre());
            }else{
                lblValCliente.setText("General");
            }
            if(usuarioVenta!=null){
                lblValCajero.setText(usuarioVenta.getNombre());
            }else{
                lblValCajero.setText("No especificado");
            }
            int numProd=0;
            for(DetalleVenta dv:v.getDetallesVenta()){
                numProd+=dv.getCantidad();
            }
            lblValNumProd.setText(Integer.toString(numProd));
            lblValTotalImp.setText("S/."+Double.toString(v.getTotalImpuestos()));
            lblValTotDesc.setText("S/."+Double.toString(v.getTotalDescuento()));
            lblValPago.setText("S/."+Double.toString(v.getPagoCliente()));
            lblValCambio.setText("S/."+Double.toString(v.getCambio()));
            lblValVentaBruta.setText("S/."+Double.toString(v.getVentaBruta()));
            lblValVentaNeta.setText("S/."+Double.toString(v.getVentaNeta()));
            lblValUtilBruta.setText("S/."+Double.toString(v.getVentaBruta()-v.getTotalCosto()));
            lblValTotCosto.setText("S/."+Double.toString(v.getTotalCosto()));
            lblValUtilNeta.setText(String.format("S/.%.2f", v.getVentaBruta()-v.getTotalCosto()-v.getTotalImpuestos()));
        }else{
            lblValID.setText("");
            lblValFecha.setText("");
            lblValCliente.setText("");
            lblValCajero.setText("");
            lblValNumProd.setText("");
            lblValTotalImp.setText("");
            lblValTotDesc.setText("");
            lblValPago.setText("");
            lblValCambio.setText("");
            lblValVentaBruta.setText("");
            lblValVentaNeta.setText("");
            lblValUtilBruta.setText("");
            lblValTotCosto.setText("");
            lblValUtilNeta.setText("");
        }
    }
    
    class LabelSegmentoResumen extends JPanel{
        
        private JLabel TituloInfoResumen;
        private float dimFuenteBaseTitulo = 28f;
        private Dimension dimBaseTitulo=new Dimension(202,122);
        
        private JLabel valorInfoResumen;
        private float dimFuenteBaseValor = 37.5f;
        private Dimension dimBaseValor=new Dimension(202,98);
        
        private ToolTipIcon toolTipDeSegmento;
        private int dimBaseToolTip=28;
        
        private Dimension dimSegmentoBase=new Dimension(230,220);
        private Dimension dimSegmentoAsignada;
        
        private String plantillaHTML = "<html><body style='word-wrap: break-word; text-align:center;'>%s</body></html>";

        public LabelSegmentoResumen(String tituloInfo, String valorInfo) {
            setLayout(new GridBagLayout());
            //setOpaque(false);
            setOpaque(false);
            GridBagConstraints gbc = new GridBagConstraints();
            TituloInfoResumen=new JLabel(String.format(plantillaHTML, tituloInfo),JLabel.CENTER);
            TituloInfoResumen.setPreferredSize(dimBaseTitulo);
            TituloInfoResumen.setFont(UtilidadesFuentes.InterRegular.deriveFont(dimFuenteBaseTitulo));
            TituloInfoResumen.setForeground(Color.decode("#8C8C8C"));
            
            gbc.gridx=0;
            gbc.gridy=0;
            add(TituloInfoResumen,gbc);
            
            valorInfoResumen=new JLabel(String.format(plantillaHTML, valorInfo),JLabel.CENTER);
            valorInfoResumen.setPreferredSize(dimBaseValor);
            valorInfoResumen.setFont(UtilidadesFuentes.InterRegular.deriveFont(dimFuenteBaseValor));
            valorInfoResumen.setVerticalAlignment(JLabel.NORTH);
            valorInfoResumen.setForeground(Color.decode("#8C8C8C"));
            gbc.gridx=0;
            gbc.gridy=1;
            add(valorInfoResumen,gbc);
        }

        public LabelSegmentoResumen(String tituloInfo, String valorInfo, String mensajeToolTip, Dimension dimensionSegmentoAsignada) {
            this(tituloInfo, valorInfo);
            GridBagConstraints gbc = new GridBagConstraints();
            toolTipDeSegmento = new ToolTipIcon(dimBaseToolTip,mensajeToolTip);
            gbc.gridx=1;
            gbc.gridy=0;
            gbc.gridheight=2;
            gbc.anchor=GridBagConstraints.PAGE_START;
            add(toolTipDeSegmento,gbc);
            this.dimSegmentoAsignada=dimensionSegmentoAsignada;
            setDimSegmentoAsignada(dimSegmentoAsignada);
        }

        public Dimension getDimSegmentoAsignada() {
            return dimSegmentoAsignada;
        }

        public void setDimSegmentoAsignada(Dimension dimSegmentoAsignada) {
            this.dimSegmentoAsignada = dimSegmentoAsignada;
            double nuevoAnchoTitulo=(double)dimBaseTitulo.width/(double)dimSegmentoBase.width*(double)this.dimSegmentoAsignada.width;
            double nuevoAltoTitulo=(double)dimBaseTitulo.height/(double)dimSegmentoBase.height*(double)this.dimSegmentoAsignada.height;
            TituloInfoResumen.setPreferredSize(new Dimension((int)nuevoAnchoTitulo,(int)nuevoAltoTitulo));
            TituloInfoResumen.setFont(UtilidadesFuentes.InterRegular.deriveFont((float)Math.ceil((double)dimFuenteBaseTitulo/(double)dimSegmentoBase.height*(double)this.dimSegmentoAsignada.height)));
            
            
            double nuevoAnchoValor=(double)dimBaseValor.width/(double)dimSegmentoBase.width*(double)this.dimSegmentoAsignada.width;
            double nuevoAltoValor=(double)dimBaseValor.height/(double)dimSegmentoBase.height*(double)this.dimSegmentoAsignada.height;
            valorInfoResumen.setPreferredSize(new Dimension((int)nuevoAnchoValor,(int)nuevoAltoValor));
            valorInfoResumen.setFont(UtilidadesFuentes.InterRegular.deriveFont((float)Math.ceil((double)dimFuenteBaseValor/(double)dimSegmentoBase.height*(double)this.dimSegmentoAsignada.height)));
            
            if(toolTipDeSegmento!=null){
                double nuevaEscala = (double)dimBaseToolTip/(double)dimSegmentoBase.height*(double)this.dimSegmentoAsignada.height;
                toolTipDeSegmento.setEscalaPX((int)Math.floor(nuevaEscala));
            }
        }

        public JLabel getTituloInfoResumen() {
            return TituloInfoResumen;
        }

        public void setTituloInfoResumen(JLabel TituloInfoResumen) {
            this.TituloInfoResumen = TituloInfoResumen;
        }

        public JLabel getValorInfoResumen() {
            return valorInfoResumen;
        }

        public void setValorInfoResumen(JLabel valorInfoResumen) {
            this.valorInfoResumen = valorInfoResumen;
        }
     
        
    }
}
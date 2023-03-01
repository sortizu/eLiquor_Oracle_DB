package Presentacion.Interfaces;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import javax.swing.JLabel;

/**
 *
 * @author sortizu
 */
public class Selector extends PanelRedondeado{

    private int anchuraOpcion=120;
    private int alturaOpcion=40;
    private ArrayList<JLabel> opcionesBTN=new ArrayList<JLabel>();
    private int opcionSeleccionada=-1;
    private ArrayList<Color> coloresDeOpcion=new ArrayList<Color>();
    private Color colorDeFuente = Color.darkGray;
    private Font fuenteDeOpcion=new Font("Arial",Font.BOLD,15);
    private String nombreDeSelector="";
    
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    public Selector(){
        //Configuraciones default
        super(40,3,new Color(0,0,0,0),Color.decode("#8C8C8C"));
        setOpaque(false);
        FlowLayout layout = new FlowLayout();
        layout.setHgap(0);
        layout.setVgap(0);
        this.setLayout(layout);

        /*
        * Permitiendo que los botones del selector se escalen por cambios
        * de tamaño que se hacen al selector externamente
        */
        addPropertyChangeListener(new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if(opcionesBTN!=null&&opcionesBTN.size()>0){
                    int nuevaAltura=getPreferredSize().height;
                    int nuevaAnchura=getPreferredSize().width/opcionesBTN.size();
                    for(JLabel label:opcionesBTN){
                        label.setPreferredSize(new Dimension(nuevaAnchura,nuevaAltura));
                    }
                }
            }

        });
    }

    public Selector(String [] opciones){
        this();
        this.anchuraOpcion=120;
        this.alturaOpcion=40;
        for(String opcion:opciones){
            agregarOpcion(opcion, colorDeFuente);
        }
    }

    public Selector(String [] opciones, int anchuraOpcion, int alturaOpcion){
        this();
        this.anchuraOpcion=anchuraOpcion;
        this.alturaOpcion=alturaOpcion;
        for(String opcion:opciones){
            agregarOpcion(opcion,getColorBorde());
        }
    }

    public void agregarOpcion(String nombreDeOpcion, Color colordeOpcion){
        setPreferredSize(new Dimension(this.anchuraOpcion*(opcionesBTN.size()+1),this.alturaOpcion));
        JLabel nuevaOpcion = new JLabel(nombreDeOpcion);
        nuevaOpcion.setCursor(new Cursor(Cursor.HAND_CURSOR));
        nuevaOpcion.setHorizontalAlignment(JLabel.CENTER);
        nuevaOpcion.setFont(fuenteDeOpcion);
        nuevaOpcion.setForeground(colorDeFuente);
        nuevaOpcion.setPreferredSize(new Dimension(anchuraOpcion,alturaOpcion));
        nuevaOpcion.addMouseListener(new java.awt.event.MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                propertyChangeSupport.firePropertyChange("opcionSeleccionada", opcionSeleccionada,opcionesBTN.indexOf(e.getSource()));
                opcionSeleccionada=opcionesBTN.indexOf(e.getSource());
                for(int i=0; i<opcionesBTN.size();i++){
                    if(i==opcionSeleccionada){
                        opcionesBTN.get(i).setForeground(Color.white);
                    }else{
                        opcionesBTN.get(i).setForeground(colorDeFuente);
                    }
                }
                repaint();
                //solicitarSeleccion(opcionesBTN.indexOf(e.getSource()));
            }
        });
        opcionesBTN.add(nuevaOpcion);
        this.add(nuevaOpcion);
    }
    
    public void solicitarSeleccion(int indice){
        if(indice>=0 && indice < opcionesBTN.size()){
            propertyChangeSupport.firePropertyChange("opcionSeleccionada", opcionSeleccionada,indice);
            opcionSeleccionada=indice;
            for(int i=0; i<opcionesBTN.size();i++){
                    if(i==opcionSeleccionada){
                        opcionesBTN.get(i).setForeground(Color.white);
                    }else{
                        opcionesBTN.get(i).setForeground(colorDeFuente);
                    }
            }
            repaint();
        }else{
            System.out.println("La opcion indicada no existe");
        }
        
    }
    
    public int getOpcionSeleccionada() {
        return opcionSeleccionada;
    }

    public void setColorDeOpcion(int indice, Color color){
        if(indice<coloresDeOpcion.size()){
            coloresDeOpcion.set(indice, color);
        }else{
            System.out.println("El indice de la opcion indicada no existe");
        }
    }

    public void addColorDeOpcion(Color nuevoColorDeOpcion){
        coloresDeOpcion.add(nuevoColorDeOpcion);
    }

    public Color getColorDeOpcion(int indice){
        if(indice<coloresDeOpcion.size()){
            return coloresDeOpcion.get(indice);
        }else{
            System.out.println("El indice de la opcion indicada no existe");
            return null;
        }
    }

    public Color getColorDeFuente() {
        return colorDeFuente;
    }

    public void setColorDeFuente(Color colorDeFuente) {
        this.colorDeFuente = colorDeFuente;
        for(JLabel opcion:opcionesBTN){
            opcion.setForeground(colorDeFuente);
        }
    }

    public Font getFuenteDeOpcion() {
        return fuenteDeOpcion;
    }

    public void setFuenteDeOpcion(Font fuenteDeOpcion) {
        this.fuenteDeOpcion = fuenteDeOpcion;
        for(JLabel opcion:opcionesBTN){
            opcion.setFont(fuenteDeOpcion);
        }
    }

    public String getNombreDeSelector() {
        return nombreDeSelector;
    }

    public void setNombreDeSelector(String nombreDeSelector) {
        this.nombreDeSelector = nombreDeSelector;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics = (Graphics2D)g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if(opcionesBTN!=null){
            if(opcionesBTN.size()>0){
                int width = anchuraOpcion;
                int height = alturaOpcion;
                int grosorDeBorde=getGrosorDeBorde();
                int radioDeEsquina=getRadioDeEsquina();
                Color colorDeOpcion = getColorBorde();
                if(coloresDeOpcion!=null)
                    if(opcionSeleccionada>=0&&opcionSeleccionada < coloresDeOpcion.size())
                        colorDeOpcion = coloresDeOpcion.get(opcionSeleccionada);
                if(opcionSeleccionada==0){
                    Area area = new Area(new RoundRectangle2D.Double(grosorDeBorde/2, grosorDeBorde/2, width-grosorDeBorde, height-grosorDeBorde, radioDeEsquina, radioDeEsquina));
                    area.add(new Area(new Rectangle2D.Double(radioDeEsquina / 2, 0, width - radioDeEsquina / 2, height)));
                    graphics.setColor(colorDeOpcion);
                    graphics.fill(area);
                }else if(opcionSeleccionada==opcionesBTN.size()-1){
                    Area area = new Area(new RoundRectangle2D.Double(width*(opcionesBTN.size()-1)+grosorDeBorde/2, grosorDeBorde/2,width-grosorDeBorde, height-grosorDeBorde, radioDeEsquina, radioDeEsquina));
                    area.add(new Area(new Rectangle2D.Double(width*(opcionesBTN.size()-1), 0, width - radioDeEsquina / 2, height)));
                    graphics.setColor(colorDeOpcion);
                    graphics.fill(area);
                }else{
                    Area area=new Area(new Rectangle2D.Double(width*opcionSeleccionada, 0, width, height));
                    graphics.setColor(colorDeOpcion);
                    graphics.fill(area);
                }
            }
        }
        super.paintComponent(g);
    }
    
}

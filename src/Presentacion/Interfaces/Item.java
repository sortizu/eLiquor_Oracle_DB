package Presentacion.Interfaces;

import Presentacion.Utilidades.UtilidadesFuentes;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.EventHandler;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author sortizu
 */
public class Item extends PanelImagen{
    
    private JTextPane nombre;
    private JTextPane subtitulo;
    private int alto=138;
    private int ancho=138;
    private boolean seleccionado=false;
    
    public Item() {
        super("/Presentacion/Imagenes/Paneles/Inventario/itemNoSeleccionado.png");
        setPreferredSize(new Dimension(ancho,alto));
        setLayout(new GridBagLayout());
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        GridBagConstraints gbc = new GridBagConstraints();
        
        nombre=new JTextPane();
        nombre.setOpaque(false);
        subtitulo=new JTextPane();
        subtitulo.setOpaque(false);
        nombre.setFont(UtilidadesFuentes.InterLight.deriveFont(17.0f));
        subtitulo.setFont(UtilidadesFuentes.InterLight.deriveFont(17.0f));
        nombre.setForeground(Color.decode("#8C8C8C"));
        subtitulo.setForeground(Color.decode("#8C8C8C"));
        nombre.setHighlighter(null);
        nombre.setEditable(false);
        subtitulo.setHighlighter(null);
        subtitulo.setEditable(false);
        //nombre.setBackground(new Color(0,0,0,0));
        //cantidad.setBackground(new Color(0,0,0,0));
        
        StyledDocument docNombre = nombre.getStyledDocument();
        StyledDocument docCantidad = subtitulo.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        
        docNombre.setParagraphAttributes(0, docNombre.getLength(), center, false);
        docCantidad.setParagraphAttributes(0, docCantidad.getLength(), center, false);
        
        gbc.weightx=1.0;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.gridx=0;
        gbc.gridy=0;
        add(nombre,gbc);
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        add(subtitulo,gbc);
        
        final Container padreItem = (Container)this;
        MouseListener mouseListener=new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {
                e.setSource(padreItem);
                padreItem.dispatchEvent(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        };
       nombre.addMouseListener(mouseListener);
       subtitulo.addMouseListener(mouseListener);
    }
    
    public Item(String nombre, String cantidad) {
        this();
        this.nombre.setText(nombre);
        this.subtitulo.setText(cantidad);
    }
    
    public void seleccionarItem(){
        seleccionado=true;
        setImagen("/Presentacion/Imagenes/Paneles/Inventario/itemSeleccionado.png");
    }
    
    public void DeseleccionarItem(){
        seleccionado=false;
        setImagen("/Presentacion/Imagenes/Paneles/Inventario/itemNoSeleccionado.png");
    }
    
    public boolean getSeleccionado(){
        return seleccionado;
    }

    public JTextPane getNombre() {
        return nombre;
    }

    public void setNombre(JTextPane nombre) {
        this.nombre = nombre;
    }

    public JTextPane getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(JTextPane subtitulo) {
        this.subtitulo = subtitulo;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
        setPreferredSize(new Dimension(ancho,alto));
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
        setPreferredSize(new Dimension(ancho,alto));
    }
    
    
}

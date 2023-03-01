package Presentacion.Interfaces;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;

public class PanelImagen extends JPanel{
    private Image imagen;
    private String direcciondeimagen;
    public PanelImagen(String direcciondeimagen){
        super();
        this.direcciondeimagen=direcciondeimagen;
        imagen=new ImageIcon(PanelImagen.class.getResource(direcciondeimagen)).getImage();
    }
    
    public void setImagen(String direcciondeimagen){
        this.direcciondeimagen=direcciondeimagen;
        imagen=new ImageIcon(this.getClass().getResource(direcciondeimagen)).getImage();
    }

    public Image getImagen() {
        return imagen;
    }
    
    @Override
    public void paintComponent(Graphics g){
        Graphics2D graphics = (Graphics2D)g;
        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        graphics.drawImage(imagen,0,0,getWidth(),getHeight(),this);
        setOpaque(false);
        super.paintComponent(g);
    }
}

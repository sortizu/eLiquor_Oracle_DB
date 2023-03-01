package Presentacion.Interfaces;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 *
 * @author sortizu
 */
public class PanelRedondeado extends JPanel
{
    private Color colorFondo=new Color(0,0,0,0);
    private Color colorBorde=Color.black;
    private int radioDeEsquina = 15;
    private int grosorDeBorde=1;

    public PanelRedondeado(){
        super();
    }

    public PanelRedondeado(int radius) {
        super();
        radioDeEsquina = radius;
    }

    public PanelRedondeado(int radius, int grosorDeBorde){
        super();
        radioDeEsquina = radius;
        this.grosorDeBorde=grosorDeBorde;
    }

    public PanelRedondeado(int radius, int grosorDeBorde, Color colorFondo) {
        super();
        radioDeEsquina = radius;
        this.grosorDeBorde=grosorDeBorde;
        this.colorFondo = colorFondo;
    }

    public PanelRedondeado(int radius, int grosorDeBorde, Color colorFondo, Color colorBorde) {
        super();
        radioDeEsquina = radius;
        this.grosorDeBorde=grosorDeBorde;
        this.colorFondo = colorFondo;
        this.colorBorde=colorBorde;
    }

    public Color getColorFondo() {
        return colorFondo;
    }

    public void setColorFondo(Color colorFondo) {
        this.colorFondo = colorFondo;
    }

    public Color getColorBorde() {
        return colorBorde;
    }

    public void setColorBorde(Color colorBorde) {
        this.colorBorde = colorBorde;
    }

    public int getRadioDeEsquina() {
        return radioDeEsquina;
    }

    public void setRadioDeEsquina(int radioDeEsquina) {
        this.radioDeEsquina = radioDeEsquina;
    }

    public int getGrosorDeBorde() {
        return grosorDeBorde;
    }

    public void setGrosorDeBorde(int grosorDeBorde) {
        this.grosorDeBorde = grosorDeBorde;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension arcs = new Dimension(radioDeEsquina, radioDeEsquina);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics.setColor(colorFondo);
        graphics.fillRoundRect(grosorDeBorde/2, grosorDeBorde/2, width-grosorDeBorde, height-grosorDeBorde, arcs.width, arcs.height); 

        graphics.setColor(colorBorde);
        graphics.setStroke(new BasicStroke(grosorDeBorde));
        graphics.drawRoundRect(grosorDeBorde/2, grosorDeBorde/2, width-grosorDeBorde, height-grosorDeBorde, arcs.width, arcs.height);
    }
}
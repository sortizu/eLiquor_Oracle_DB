/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion.Interfaces;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPasswordField;

/**
 *
 * @author sortizu
 */
public class PasswordFieldRedondeado extends JPasswordField{
    private Shape shape;
    private int grosorBorde;
    private Color colorBorde;
    private Color colorFondo;
    private int radioDeBorde;
    public PasswordFieldRedondeado() {
        super(4);
        setOpaque(false); // As suggested by @AVD in comment.
    }
    
    public PasswordFieldRedondeado(int columnas) {
        super(columnas);
        setOpaque(false); // As suggested by @AVD in comment.
    }

    public int getGrosorBorde() {
        return grosorBorde;
    }

    public void setGrosorBorde(int grosorBorde) {
        this.grosorBorde = grosorBorde;
    }

    public Color getColorBorde() {
        return colorBorde;
    }

    public void setColorBorde(Color colorBorde) {
        this.colorBorde = colorBorde;
    }

    public Color getColorFondo() {
        return colorFondo;
    }

    public void setColorFondo(Color colorFondo) {
        this.colorFondo = colorFondo;
    }

    public int getRadioDeBorde() {
        return radioDeBorde;
    }

    public void setRadioDeBorde(int radioDeBorde) {
        this.radioDeBorde = radioDeBorde;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
         g.setColor(getBackground());
         g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, radioDeBorde, radioDeBorde);
         super.paintComponent(g);
    }
    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(colorBorde);
        //((Graphics2D)g)
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(grosorBorde));
        g.drawRoundRect(grosorBorde/2, grosorBorde/2, getWidth()-grosorBorde, getHeight()-grosorBorde, radioDeBorde, radioDeBorde);
    }
    @Override
    public boolean contains(int x, int y) {
         if (shape == null || !shape.getBounds().equals(getBounds())) {
             shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, radioDeBorde, radioDeBorde);
         }
         return shape.contains(x, y);
    }
}

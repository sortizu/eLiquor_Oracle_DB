package Presentacion.Interfaces;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class ScrollBarCustom extends JScrollBar {
    private ModernScrollBarUI modernScrollBarUI;
    private JScrollPane scrollPane;
    private int baseMinimumThumbHeight=40;
    private int baseMinimumThumbWidth=20;
    
    public ScrollBarCustom() {
        modernScrollBarUI=new ModernScrollBarUI();
        setUI(modernScrollBarUI);
        setPreferredSize(new Dimension(20, 20));
        setForeground(Color.decode("#D0D0D0"));
        setBackground(Color.WHITE);
    }
    
    public ScrollBarCustom(JScrollPane scrollPane){
        this();
        this.scrollPane=scrollPane;
        this.scrollPane.getViewport().getView().addComponentListener(new ComponentAdapter(){
            @Override
            public void componentResized(ComponentEvent e) {
                ajustarDimensionesScroll();
            }
        });
        
    }

    public void setThumbSize(int thumbSize) {
        modernScrollBarUI.setThumbSize(thumbSize);
    }
    
    
    /**Ajusta la altura de la barra en base al contenido 
     * mostrado en el scrollPane que lo contiene.
     */
    public void ajustarDimensionesScroll(){
        double alturaScrollPane=0;
        double alturaContenido=0;
        double newScrollBarHeight=0;
        try {
            alturaScrollPane=scrollPane.getViewport().getExtentSize().getHeight();
            alturaContenido=scrollPane.getViewport().getView().getHeight();
            newScrollBarHeight=Math.pow(alturaScrollPane,2)/alturaContenido;
        } catch (Exception e) {System.err.println(e);}
        setThumbSize((int)newScrollBarHeight);
        revalidate();
        repaint();
    }
}

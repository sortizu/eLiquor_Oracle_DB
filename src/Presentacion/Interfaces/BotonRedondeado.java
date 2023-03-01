package Presentacion.Interfaces;

import Presentacion.Utilidades.UtilidadesFuentes;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

/**
 *
 * @author sortizu
 */
public class BotonRedondeado extends PanelRedondeado{

    JLabel lblBoton;
    
    public BotonRedondeado(int radio, int grosorBorde, Color colorBoton) {
        super(radio,grosorBorde,new Color(0,0,0,0),colorBoton);
        setLayout(new GridBagLayout());
        setOpaque(false);
        //Creando texto del boton
        lblBoton = new JLabel("<html><body style='word-wrap: break-word;'>BOTÓN</body></html>",JLabel.CENTER);
        lblBoton.setForeground(colorBoton);
        lblBoton.setFont(UtilidadesFuentes.InterRegular.deriveFont(10.0f));
        //Añadiendo Texto al boton Cliente
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets=new Insets(5, 5, 5, 5);
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=1;
        gbc.weighty=1;
        //add(textoBoton,gbc);
        add(lblBoton,gbc);

        //Dando funcionalidad al boton Pago
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        java.awt.event.MouseAdapter adaptador = new java.awt.event.MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                botonPresionado();
            }
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                activarColorBoton();
                lblBoton.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                desactivarColorBoton();
                lblBoton.setForeground(getColorBorde());
            }
            
        };
        addMouseListener(adaptador);
        //textoBoton.addMouseListener(adaptador);
    }

    public BotonRedondeado(int radio, int grosorBorde, Color colorBoton,String textoDeBoton, Font fuente) {
        this(radio,grosorBorde,colorBoton);
        lblBoton.setText("<html><body style='word-wrap: break-word;'>"+textoDeBoton+"</body></html>");
        lblBoton.setFont(fuente);
    }
    
    public void botonPresionado(){
    
    }

    public void activarColorBoton(){
        setColorFondo(getColorBorde());
        repaint();
    }
    
    public void desactivarColorBoton(){
        setColorFondo(new Color(0,0,0,0));
        repaint();
    }

    public JLabel getLblBoton() {
        return lblBoton;
    }
}
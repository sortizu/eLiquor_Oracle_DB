/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion.Interfaces;

import Presentacion.Utilidades.UtilidadesFuentes;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;

/**
 *
 * @author sortizu
 */
public class BotonRedondeadoMultiple extends PanelRedondeado{
    
    private ArrayList<JLabel> lblOpciones=new ArrayList<JLabel>();
    private Dimension dimensionBotones=new Dimension(470,75);
    private final Color baseColor=Color.decode("#8C8C8C");
    private final Font baseFuente=UtilidadesFuentes.InterRegular.deriveFont(20.0f);
    private MouseAdapter adaptadorOpcionBoton =  new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                try {
                    int indice=lblOpciones.indexOf((JLabel)e.getSource());
                    if(indice<0){
                        throw new NullPointerException("Hubo un error buscando el boton en la lista");
                    }else{
                        if(lblOpciones.get(indice).isEnabled()){
                            botonOpcionPresionado(indice);
                        }
                    }
                } catch (Exception er) {System.err.println(er);}
            }
        };
    
    public BotonRedondeadoMultiple(String[]opciones) {
        super(10,3,new Color(0,0,0,0),Color.decode("#8C8C8C"));
        setLayout(new GridBagLayout());
        setOpaque(false);
        Color [] coloresTexto=new Color[opciones.length];
        Font [] fuenteTexto=new Font[opciones.length];
        for(int c=0;c<coloresTexto.length;c++){coloresTexto[c]=baseColor;}
        for(int f=0;f<fuenteTexto.length;f++){fuenteTexto[f]=baseFuente;}
        agregarBotones(opciones, coloresTexto, fuenteTexto);
    }
    

    public BotonRedondeadoMultiple(String[]opciones,Dimension dimensionBotones) {
        this(opciones);
        this.dimensionBotones=dimensionBotones;
        actualizarBotones();
    }
    
    
    public void setColorOpcion(int indice, Color color){
        if(indice>=lblOpciones.size()||indice<0){
            System.out.println("la opcion seleccionada no existe");
        }else{
            lblOpciones.get(indice).setForeground(color);
        }
    }
    
    public void setTextoOpcion(int indice, String texto){
        if(indice>=lblOpciones.size()||indice<0){
            System.out.println("la opcion seleccionada no existe");
        }else{
            lblOpciones.get(indice).setText("<html><body style='word-wrap: break-word;'>"+texto+"</body></html>");
        }
    }
    
    public void setFuenteOpcion(int indice, Font fuenteOpcion){
        if(indice>=lblOpciones.size()||indice<0){
            System.out.println("la opcion seleccionada no existe");
        }else{
            lblOpciones.get(indice).setFont(fuenteOpcion);
        }
    }
    
    public void agregarBoton(String textoBoton, Color colorTexto, Font fuenteTexto){
        JLabel lblOpcion = new JLabel("<html><body style='word-wrap: break-word;'>"+textoBoton+"</body></html>",JLabel.CENTER);
        lblOpcion.setCursor(new Cursor(Cursor.HAND_CURSOR) {
        });
        lblOpcion.setForeground(colorTexto);
        lblOpcion.setFont(fuenteTexto);
        lblOpcion.addMouseListener(adaptadorOpcionBoton);
        lblOpciones.add(lblOpcion);
        actualizarBotones();
    }
    
    public void agregarBotones(String[] textoBoton, Color[] coloresTexto, Font[] fuenteTexto){
        if(textoBoton.length==coloresTexto.length){
            if(textoBoton.length==fuenteTexto.length){
                for(int i = 0;i<textoBoton.length;i++){
                    JLabel lblOpcion = new JLabel("<html><body style='word-wrap: break-word;'>"+textoBoton[i]+"</body></html>",JLabel.CENTER);
                    lblOpcion.setCursor(new Cursor(Cursor.HAND_CURSOR) {
                    });
                    lblOpcion.setForeground(coloresTexto[i]);
                    lblOpcion.setFont(fuenteTexto[i]);
                    lblOpcion.addMouseListener(adaptadorOpcionBoton);
                    lblOpciones.add(lblOpcion);
                }
            }else{
                System.out.println("La cantidad de fuentes indicadas no coincide con la cantidad de botones deseado");
            }
        }else{
            System.out.println("La cantidad de colores indicados no coincide con la cantidad de botones deseado");
        }
        actualizarBotones();
    }
    
    public void actualizarBotones(){
        removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx=0;
        gbc.weighty=1.0;
        for(int i=0;i<lblOpciones.size();i++){
            JLabel lblOpcion = lblOpciones.get(i);
            lblOpcion.setPreferredSize(dimensionBotones);
            gbc.fill=GridBagConstraints.NONE;
            add(lblOpcion,gbc);
            gbc.gridx=gbc.gridx++;
            gbc.fill=GridBagConstraints.VERTICAL;
            if(i!=lblOpciones.size()-1){
                JSeparator separadorDeBotones=new JSeparator(JSeparator.VERTICAL);
                separadorDeBotones.setBorder(new LineBorder(getColorBorde(),getGrosorDeBorde()));
                add(separadorDeBotones,gbc);
                gbc.gridx=gbc.gridx++;
            }
        }
    }
    
    public void desactivarBoton(int indice){
        if(indice>=0 && indice<lblOpciones.size()){
            lblOpciones.get(indice).setEnabled(false);
        }
    }
    
    public void activarBoton(int indice){
        if(indice>=0 && indice<lblOpciones.size()){
            lblOpciones.get(indice).setEnabled(true);
        }
    }

    public ArrayList<JLabel> getLblOpciones() {
        return lblOpciones;
    }

    public void setLblOpciones(ArrayList<JLabel> lblOpciones) {
        this.lblOpciones = lblOpciones;
    }
    
    public void botonOpcionPresionado(int opcionPresionada){
        System.out.println("Presionado boton: ["+opcionPresionada+"]("+lblOpciones.get(opcionPresionada).getText()+")");
    }
}

package Presentacion.Interfaces;

import Presentacion.Utilidades.UtilidadesFuentes;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;

/**
 *
 * @author sortizu
 */
public class VentanaEmergente extends PanelImagen{
    
    private JLabel titulo;
    private JLabel btnCancelar;
    private JLabel btnAceptar;
    private JLabel btnAtras;
    private JLabel btnSiguiente;
    private JPanel cuerpo;
    private JPanel panelBotones;
    // Modos
    public static int BASICO=0;
    public static int PRIMERVENTANA=1;
    public static int MEDIAVENTANA=2;
    public static int ULTIMAVENTANA=3;
    
    public VentanaEmergente(String direcciondeimagen) {
        super(direcciondeimagen);
        int y=getImagen().getHeight(null);
        int x=getImagen().getWidth(null);
        setPreferredSize(new Dimension(x,y));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc= new GridBagConstraints();
        setBorder( BorderFactory.createEmptyBorder(0, 4, 8, 4) );
        
        titulo = new JLabel("TITULO");
        titulo.setFont(UtilidadesFuentes.InterLight.deriveFont(30.0f));
        titulo.setForeground(Color.decode("#8C8C8C"));
        //titulo.setOpaque(true);
        //titulo.setBackground(Color.red);
        titulo.setHorizontalAlignment(JLabel.CENTER);
        titulo.setPreferredSize(new Dimension(0,65));
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1.0;
        add(titulo,gbc);
        
        JSeparator cabecera = new JSeparator(javax.swing.SwingConstants.HORIZONTAL);
        cabecera.setPreferredSize(new Dimension(0,1));
        cabecera.setBorder(new LineBorder(Color.decode("#D0D0D0"),1));
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1.0;
        //gbc.weighty=1.0;
        add(cabecera,gbc);
        
        cuerpo=new JPanel();
        //cuerpo.setBackground(Color.blue);
        cuerpo.setOpaque(false);
        gbc.gridx=0;
        gbc.gridy=2;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=1.0;
        gbc.weighty=1.0;
        add(cuerpo,gbc);
        
        JSeparator pie = new JSeparator(javax.swing.SwingConstants.HORIZONTAL);
        pie.setPreferredSize(new Dimension(0,1));
        pie.setBorder(new LineBorder(Color.decode("#D0D0D0"),1));
        gbc.gridx=0;
        gbc.gridy=3;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1.0;
        gbc.weighty=0.0;
        add(pie,gbc);
        
        panelBotones=new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.LINE_AXIS));
        panelBotones.setOpaque(false);
        gbc.gridx=0;
        gbc.gridy=4;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1.0;
        add(panelBotones,gbc);
        
        Dimension btnTamanio=new Dimension(90,65);
        
        btnCancelar=new JLabel("×");
        btnCancelar.setFont(UtilidadesFuentes.InterBlack.deriveFont(30.0f));
        btnCancelar.setForeground(Color.decode("#AEAEAE"));
        btnCancelar.setPreferredSize(btnTamanio);
        btnCancelar.setHorizontalAlignment(JLabel.CENTER);
        btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnCancelarPresionado(evt);
            }
        });
        
        btnAceptar=new JLabel("✓");
        btnAceptar.setFont(UtilidadesFuentes.InterBlack.deriveFont(30.0f));
        btnAceptar.setForeground(Color.decode("#6CC564"));
        btnAceptar.setPreferredSize(btnTamanio);
        btnAceptar.setHorizontalAlignment(JLabel.CENTER);
        btnAceptar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAceptar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnAceptarPresionado(evt);
            }
        });
        
        btnSiguiente=new JLabel("→");
        btnSiguiente.setFont(UtilidadesFuentes.InterBlack.deriveFont(30.0f));
        btnSiguiente.setForeground(Color.decode("#AEAEAE"));
        btnSiguiente.setPreferredSize(btnTamanio);
        btnSiguiente.setHorizontalAlignment(JLabel.CENTER);
        btnSiguiente.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSiguiente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSiguientePresionado(evt);
            }
        });
        
        btnAtras=new JLabel("←");
        btnAtras.setFont(UtilidadesFuentes.InterBlack.deriveFont(30.0f));
        btnAtras.setForeground(Color.decode("#AEAEAE"));
        btnAtras.setPreferredSize(btnTamanio);
        btnAtras.setHorizontalAlignment(JLabel.CENTER);
        btnAtras.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAtras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnAtrasPresionado(evt);
            }
        });
        
        cambiarDisposicionDePanelDeBotones(BASICO);
    }
    
    public VentanaEmergente(String direcciondeimagen,String Titulo) {
        this(direcciondeimagen);
        
    }
    
    public void cambiarDisposicionDePanelDeBotones(int modo){
        if(panelBotones.getComponentCount()>0){
            panelBotones.removeAll();
        }
        switch (modo) {
            case 1:
                panelBotones.add(btnCancelar);
                panelBotones.add(Box.createHorizontalGlue());
                panelBotones.add(btnSiguiente);
                break;
            case 2:
                panelBotones.add(btnAtras);
                panelBotones.add(Box.createHorizontalGlue());
                panelBotones.add(btnCancelar);
                panelBotones.add(Box.createHorizontalGlue());
                panelBotones.add(btnSiguiente);
                break;
            case 3:
                panelBotones.add(btnAtras);
                panelBotones.add(Box.createHorizontalGlue());
                panelBotones.add(btnCancelar);
                panelBotones.add(Box.createHorizontalGlue());
                panelBotones.add(btnAceptar);
                break;
            case 0:
            default:
                panelBotones.add(btnCancelar);
                panelBotones.add(Box.createHorizontalGlue());
                panelBotones.add(btnAceptar);
            break;
        }
    }
    
    public JLabel getTitulo() {
        return titulo;
    }

    public void setTitulo(JLabel titulo) {
        this.titulo = titulo;
    }

    public void setTextoTitulo(String textoTitulo){
        titulo.setText(textoTitulo);
    }
    
    public void setColorTitulo(Color color){
        titulo.setForeground(color);
    }
    
    public JPanel getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(JPanel cuerpo) {
        this.cuerpo = cuerpo;
    }
    
    public JLabel getBtnCancelar() {
        return btnCancelar;
    }

    public void setBtnCancelar(JLabel btnCancelar) {
        this.btnCancelar = btnCancelar;
    }

    public JLabel getBtnAceptar() {
        return btnAceptar;
    }

    public void setBtnAceptar(JLabel btnAceptar) {
        this.btnAceptar = btnAceptar;
    }

    public JLabel getBtnAtras() {
        return btnAtras;
    }

    public void setBtnAtras(JLabel btnAtras) {
        this.btnAtras = btnAtras;
    }

    public JLabel getBtnSiguiente() {
        return btnSiguiente;
    }

    public void setBtnSiguiente(JLabel btnSiguiente) {
        this.btnSiguiente = btnSiguiente;
    }
    
    public void btnCancelarPresionado(java.awt.event.MouseEvent evt){}
    
    public void btnAceptarPresionado(java.awt.event.MouseEvent evt){}
    
    public void btnSiguientePresionado(java.awt.event.MouseEvent evt){}
    
    public void btnAtrasPresionado(java.awt.event.MouseEvent evt){}
    
}

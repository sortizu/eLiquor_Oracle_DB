/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion.Interfaces;

import Presentacion.Utilidades.UtilidadesFuentes;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author sortizu
 */
public class VentanaSalir extends VentanaEmergente{

    private Selector selectorSalir;
    private Container parent;
    
    public VentanaSalir(Container parent) {
        super("/Presentacion/Imagenes/Paneles/PanelSalir.png");
        this.parent=parent;
        cambiarDisposicionDePanelDeBotones(VentanaEmergente.BASICO);
        setTextoTitulo("SALIR");
        setColorTitulo(Color.decode("#8C8C8C"));
        JPanel cuerpo = getCuerpo();
        GridBagConstraints gbc = new GridBagConstraints();
        cuerpo.setBorder(new EmptyBorder(0,35,0,35));
        cuerpo.setLayout(new GridBagLayout());
        
        JLabel lblNombre=new JLabel("¿Qué desea hacer?");
        lblNombre.setFont(UtilidadesFuentes.InterLight.deriveFont(20.0f));
        lblNombre.setForeground(Color.decode("#8C8C8C"));
        lblNombre.setHorizontalAlignment(JLabel.CENTER);
        gbc.insets=new Insets(10, 0, 10, 0);
        gbc.anchor=GridBagConstraints.CENTER;
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1;
        gbc.weighty=0;
        cuerpo.add(lblNombre,gbc);
        
        selectorSalir=new Selector(new String[]{"Cerrar Sesión","Cerrar Sistema"},190,45);
        selectorSalir.setPreferredSize(new Dimension(380,45));
        selectorSalir.setFuenteDeOpcion(UtilidadesFuentes.InterRegular.deriveFont(20.0f));
        selectorSalir.setColorDeFuente(Color.decode("#8C8C8C"));
        selectorSalir.solicitarSeleccion(0);
        gbc.anchor=GridBagConstraints.PAGE_START;
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.CENTER;
        gbc.weighty=1;
        cuerpo.add(selectorSalir,gbc);
    }

    @Override
    public void btnAceptarPresionado(MouseEvent evt) {
        int opcionSeleccionada = selectorSalir.getOpcionSeleccionada();
        if(opcionSeleccionada==0){
            Container cardPanel = parent.getParent();
            CardLayout layout= (CardLayout) cardPanel.getLayout();
            layout.show(cardPanel, "login");
            cardPanel.remove(parent);
            ((FramePrincipal)((JFrame) SwingUtilities.getWindowAncestor(this))).cerrarPanelesEmergentes();
        }else{
            ((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
        }
    }

    @Override
    public void btnCancelarPresionado(MouseEvent evt) {
        ((FramePrincipal)((JFrame) SwingUtilities.getWindowAncestor(this))).cerrarPanelesEmergentes();
    }
    
    
}

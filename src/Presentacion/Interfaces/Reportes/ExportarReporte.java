/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion.Interfaces.Reportes;

import Presentacion.Interfaces.FramePrincipal;
import Presentacion.Interfaces.PanelModulo;
import Presentacion.Interfaces.VentanaEmergente;
import Presentacion.Utilidades.UtilidadesFuentes;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author sortizu
 */
public class ExportarReporte extends VentanaEmergente{

    Container contenedorModulo;
    
    public ExportarReporte(Container contenedorModulo) {
        super("/Presentacion/Imagenes/Paneles/Reportes/PanelExportar.png");
        this.contenedorModulo=contenedorModulo;
        int y=getImagen().getHeight(null);
        int x=getImagen().getWidth(null);
        //setPreferredSize(new Dimension((int)(x/PanelModulo.basePanelWidth*contenedorModulo.getWidth()),(int)(y/PanelModulo.basePanelHeight*contenedorModulo.getHeight())));
        setTextoTitulo("EXPORTAR");
        setColorTitulo(Color.decode("#8C8C8C"));
        //getTitulo().setFont(UtilidadesFuentes.InterLight.deriveFont((float)(30/PanelModulo.basePanelHeight*contenedorModulo.getHeight())));
        JPanel cuerpo = getCuerpo();
        cuerpo.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
    }
 
    @Override
    public void btnCancelarPresionado(MouseEvent evt) {
        ((FramePrincipal)((JFrame) SwingUtilities.getWindowAncestor(this))).cerrarPanelesEmergentes();
    }
}

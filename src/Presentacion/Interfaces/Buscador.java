/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion.Interfaces;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 *
 * @author sortizu
 */
public class Buscador extends PanelRedondeado{
    private JLabel btnBuscar = new JLabel(new javax.swing.ImageIcon(getClass().getResource("/Presentacion/Imagenes/lupa.png")));
    private JTextField txtABuscar=new JTextField(0);
    
    public Buscador() {
        super(30,3,Color.white,Color.decode("#8C8C8C"));
        setOpaque(false);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc= new GridBagConstraints();
        
        setBorder( BorderFactory.createEmptyBorder(0, 10, 0, 0) );
        
        txtABuscar.setPreferredSize(new Dimension(0,0));
        txtABuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        txtABuscar.setOpaque(false);
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=1;
        gbc.weighty=1;
        this.add(txtABuscar,gbc);
        
        JSeparator separador = new JSeparator(javax.swing.SwingConstants.VERTICAL);
        separador.setPreferredSize(new Dimension(getGrosorDeBorde(),0));
        separador.setBorder(new LineBorder(getColorBorde(),getGrosorDeBorde()));
        gbc.gridx=1;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.VERTICAL;
        gbc.weightx=0;
        gbc.weighty=1;
        this.add(separador,gbc);
        
        btnBuscar.setHorizontalAlignment(JLabel.CENTER);
        btnBuscar.setPreferredSize(new Dimension(40,0));
        btnBuscar.setBorder( BorderFactory.createEmptyBorder(0, 0, 0, 5) );
        gbc.gridx=2;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.VERTICAL;
        gbc.weightx=0;
        gbc.weighty=1;
        this.add(btnBuscar,gbc);
        this.setPreferredSize(new Dimension(0,37));
        
    }

    public JLabel getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(JLabel btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public JTextField getTxtABuscar() {
        return txtABuscar;
    }

    public void setTxtABuscar(JTextField txtABuscar) {
        this.txtABuscar = txtABuscar;
    }
    
}

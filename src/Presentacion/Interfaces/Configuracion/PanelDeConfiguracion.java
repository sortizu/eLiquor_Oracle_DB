/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion.Interfaces.Configuracion;

import Presentacion.Interfaces.BotonRedondeado;
import Datos.Entidades.Configuracion;
import Negocio.ControlConfiguracion;
import Presentacion.Interfaces.Configuracion.*;
import Presentacion.Interfaces.PanelModulo;
import Presentacion.Interfaces.TextFieldRedondeado;
import Presentacion.Utilidades.UtilidadSesion;
import Presentacion.Utilidades.UtilidadesFuentes;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author sortizu
 */
public class PanelDeConfiguracion extends JPanel{
    private PanelModulo panelModuloConfiguracion;
    private PanelDeConfiguracion panelPrincipalConfiguracion;
    private Container parent;
    private JPanel cuerpo;
    
    private TextFieldRedondeado txtRazonSocial;
    private TextFieldRedondeado txtRUC;
    private TextFieldRedondeado txtNumTerminal;
    private TextFieldRedondeado txtCodigoTienda;
    private TextFieldRedondeado txtTelefono;
    private TextFieldRedondeado txtProvincia;
    private TextFieldRedondeado txtDistrito;
    private TextFieldRedondeado txtCiudad;
    private TextFieldRedondeado txtDireccion;
    private TextFieldRedondeado txtCodigoPostal;
    private BotonRedondeado btnGuardarCambios;
    private JLabel lblDatosGuardados;
    private Timer timerAlertaGuardado;
    
    public PanelDeConfiguracion(Container parent) {
        
        this.parent=parent;
        panelPrincipalConfiguracion=this;
        iniciarComponentes();
        cargarListaDeConfiguracion();
        timerAlertaGuardado = new Timer(1500,new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        lblDatosGuardados.setForeground(new Color(0,0,0,0));
                    }
                });
        timerAlertaGuardado.setRepeats(false);
        panelModuloConfiguracion.getPanelContenedorComponentes().desactivarBtnAyuda(); 
    }
    private void iniciarComponentes(){
        setOpaque(false);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        panelModuloConfiguracion=new PanelModulo(parent);
        panelModuloConfiguracion.setTituloPanelModulo("C O N F I G U R A C I Ó N", Color.decode("#8C8C8C"));
        gbc.insets = new Insets((int)(8.0/panelModuloConfiguracion.basePanelHeight*panelModuloConfiguracion.getPreferredSize().getHeight()), 0, 0, 0);
        gbc.gridx=0;
        gbc.gridy=0;
        add(panelModuloConfiguracion,gbc);
        iniciarComponentesCuerpo();
        //Cargando datos
        txtRazonSocial.setText(UtilidadSesion.configuracionActual.getRazonSocial());
        txtRUC.setText(UtilidadSesion.configuracionActual.getRUC());
        txtNumTerminal.setText(""+UtilidadSesion.configuracionActual.getNumeroTerminal());
        txtCodigoTienda.setText(UtilidadSesion.configuracionActual.getCodigoTienda());
        txtTelefono.setText(""+UtilidadSesion.configuracionActual.getTelefono());
        txtProvincia.setText(UtilidadSesion.configuracionActual.getProvincia());
        txtDistrito.setText(UtilidadSesion.configuracionActual.getDistrito());
        txtCiudad.setText(UtilidadSesion.configuracionActual.getCiudad());
        txtDireccion.setText(UtilidadSesion.configuracionActual.getDireccion());
        txtCodigoPostal.setText(""+UtilidadSesion.configuracionActual.getCodigoPostal());
    }

    private void iniciarComponentesCuerpo(){
        cuerpo = panelModuloConfiguracion.getPanelContenedorComponentes().getCuerpo();
        int width = (int)panelModuloConfiguracion.getPreferredSize().getWidth();
        int height = (int)panelModuloConfiguracion.getPreferredSize().getHeight();
        
        iniciarComponentesCuerpoSuperior(width, height);
        iniciarComponentesCuerpoInferior(width, height);
    }
    
    private void iniciarComponentesCuerpoSuperior(int width, int height){
        GridBagConstraints gbc = new GridBagConstraints();
        Insets insetTxt = new Insets(0, 65, 25, 45);
        Insets insetLbl = new Insets(0, 95, 5, 45);
        
        cuerpo.setBorder(new EmptyBorder(20,0,0,0));
        
        JLabel lblRazonSocial=new JLabel("Razón Social");
        lblRazonSocial.setFont(UtilidadesFuentes.InterLight.deriveFont(25.0f));
        lblRazonSocial.setForeground(Color.decode("#8C8C8C"));
        lblRazonSocial.setHorizontalAlignment(JLabel.LEFT);
        gbc.insets=insetLbl;
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1;
        cuerpo.add(lblRazonSocial,gbc);
        
        Dimension dimtxtRazonSocial=new Dimension(530,45);
        
        txtRazonSocial=new TextFieldRedondeado(0);
        txtRazonSocial.setGrosorBorde(4);
        txtRazonSocial.setRadioDeBorde(40);
        txtRazonSocial.setColorBorde(Color.decode("#CACACA"));
        txtRazonSocial.setFont(UtilidadesFuentes.InterLight.deriveFont(22.0f));
        txtRazonSocial.setForeground(Color.decode("#8C8C8C"));
        txtRazonSocial.setHorizontalAlignment(JLabel.LEFT);
        txtRazonSocial.setBorder( BorderFactory.createEmptyBorder(2, 20, 0, 20) );
        txtRazonSocial.setPreferredSize(dimtxtRazonSocial);
        txtRazonSocial.setMinimumSize(dimtxtRazonSocial);
        gbc.insets=insetTxt;
        gbc.anchor=GridBagConstraints.LINE_START;
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        cuerpo.add(txtRazonSocial,gbc);
        
        Dimension nuevoDim1=new Dimension(345,45);
        JLabel lblRUC=new JLabel("RUC");
        lblRUC.setFont(UtilidadesFuentes.InterLight.deriveFont(25.0f));
        lblRUC.setForeground(Color.decode("#8C8C8C"));
        lblRUC.setHorizontalAlignment(JLabel.LEFT);
        gbc.insets=insetLbl;
        gbc.gridx=0;
        gbc.gridy=2;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        cuerpo.add(lblRUC,gbc);
        
        txtRUC=new TextFieldRedondeado(0);
        txtRUC.setGrosorBorde(4);
        txtRUC.setRadioDeBorde(40);
        txtRUC.setColorBorde(Color.decode("#CACACA"));
        txtRUC.setFont(UtilidadesFuentes.InterLight.deriveFont(22.0f));
        txtRUC.setForeground(Color.decode("#8C8C8C"));
        txtRUC.setHorizontalAlignment(JLabel.LEFT);
        txtRUC.setBorder( BorderFactory.createEmptyBorder(2, 20, 0, 20) );
        txtRUC.setPreferredSize(nuevoDim1);
        txtRUC.setMinimumSize(nuevoDim1);
        gbc.insets=insetTxt;
        gbc.anchor=GridBagConstraints.LINE_START;
        gbc.gridx=0;
        gbc.gridy=3;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        cuerpo.add(txtRUC,gbc);
        
        JLabel lblNumTerm=new JLabel("Número de terminal");
        lblNumTerm.setFont(UtilidadesFuentes.InterLight.deriveFont(25.0f));
        lblNumTerm.setForeground(Color.decode("#8C8C8C"));
        lblNumTerm.setHorizontalAlignment(JLabel.LEFT);
        gbc.insets=insetLbl;
        gbc.gridy++;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        cuerpo.add(lblNumTerm,gbc);
        
        txtNumTerminal=new TextFieldRedondeado(0);
        txtNumTerminal.setGrosorBorde(4);
        txtNumTerminal.setRadioDeBorde(40);
        txtNumTerminal.setColorBorde(Color.decode("#CACACA"));
        txtNumTerminal.setFont(UtilidadesFuentes.InterLight.deriveFont(22.0f));
        txtNumTerminal.setForeground(Color.decode("#8C8C8C"));
        txtNumTerminal.setHorizontalAlignment(JLabel.LEFT);
        txtNumTerminal.setBorder( BorderFactory.createEmptyBorder(2, 20, 0, 20) );
        txtNumTerminal.setPreferredSize(nuevoDim1);
        txtNumTerminal.setMinimumSize(nuevoDim1);
        gbc.insets=insetTxt;
        gbc.anchor=GridBagConstraints.LINE_START;
        gbc.gridy++;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        cuerpo.add(txtNumTerminal,gbc);
        
        JLabel lblCodTienda=new JLabel("Código de tienda");
        lblCodTienda.setFont(UtilidadesFuentes.InterLight.deriveFont(25.0f));
        lblCodTienda.setForeground(Color.decode("#8C8C8C"));
        lblCodTienda.setHorizontalAlignment(JLabel.LEFT);
        gbc.insets=insetLbl;
        gbc.gridy++;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        cuerpo.add(lblCodTienda,gbc);
        
        txtCodigoTienda=new TextFieldRedondeado(0);
        txtCodigoTienda.setGrosorBorde(4);
        txtCodigoTienda.setRadioDeBorde(40);
        txtCodigoTienda.setColorBorde(Color.decode("#CACACA"));
        txtCodigoTienda.setFont(UtilidadesFuentes.InterLight.deriveFont(22.0f));
        txtCodigoTienda.setForeground(Color.decode("#8C8C8C"));
        txtCodigoTienda.setHorizontalAlignment(JLabel.LEFT);
        txtCodigoTienda.setBorder( BorderFactory.createEmptyBorder(2, 20, 0, 20) );
        txtCodigoTienda.setPreferredSize(nuevoDim1);
        txtCodigoTienda.setMinimumSize(nuevoDim1);
        gbc.insets=insetTxt;
        gbc.anchor=GridBagConstraints.LINE_START;
        gbc.gridy++;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        cuerpo.add(txtCodigoTienda,gbc);
        
        JLabel lblTelefono=new JLabel("Teléfono");
        lblTelefono.setFont(UtilidadesFuentes.InterLight.deriveFont(25.0f));
        lblTelefono.setForeground(Color.decode("#8C8C8C"));
        lblTelefono.setHorizontalAlignment(JLabel.LEFT);
        gbc.insets=insetLbl;
        gbc.gridy++;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        cuerpo.add(lblTelefono,gbc);
        
        txtTelefono=new TextFieldRedondeado(0);
        txtTelefono.setGrosorBorde(4);
        txtTelefono.setRadioDeBorde(40);
        txtTelefono.setColorBorde(Color.decode("#CACACA"));
        txtTelefono.setFont(UtilidadesFuentes.InterLight.deriveFont(22.0f));
        txtTelefono.setForeground(Color.decode("#8C8C8C"));
        txtTelefono.setHorizontalAlignment(JLabel.LEFT);
        txtTelefono.setBorder( BorderFactory.createEmptyBorder(2, 20, 0, 20) );
        txtTelefono.setPreferredSize(nuevoDim1);
        txtTelefono.setMinimumSize(nuevoDim1);
        gbc.insets=insetTxt;
        //gbc.anchor=GridBagConstraints.FIRST_LINE_START;
        gbc.gridy++;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=1;
        gbc.weighty=1;
        cuerpo.add(txtTelefono,gbc);
        
        gbc.gridx=1;
        gbc.weighty=0;
        
        JLabel lblProvincia=new JLabel("Provincia");
        lblProvincia.setFont(UtilidadesFuentes.InterLight.deriveFont(25.0f));
        lblProvincia.setForeground(Color.decode("#8C8C8C"));
        lblProvincia.setHorizontalAlignment(JLabel.LEFT);
        gbc.insets=insetLbl;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        cuerpo.add(lblProvincia,gbc);
        
        Dimension nuevoDim2=new Dimension(248,45);
        
        txtProvincia=new TextFieldRedondeado(0);
        txtProvincia.setGrosorBorde(4);
        txtProvincia.setRadioDeBorde(40);
        txtProvincia.setColorBorde(Color.decode("#CACACA"));
        txtProvincia.setFont(UtilidadesFuentes.InterLight.deriveFont(22.0f));
        txtProvincia.setForeground(Color.decode("#8C8C8C"));
        txtProvincia.setHorizontalAlignment(JLabel.LEFT);
        txtProvincia.setBorder( BorderFactory.createEmptyBorder(2, 20, 0, 20) );
        txtProvincia.setPreferredSize(nuevoDim2);
        txtProvincia.setMinimumSize(nuevoDim2);
        gbc.insets=insetTxt;
        gbc.anchor=GridBagConstraints.LINE_START;
        gbc.gridy++;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        cuerpo.add(txtProvincia,gbc);
        
        JLabel lblCiudad=new JLabel("Ciudad");
        lblCiudad.setFont(UtilidadesFuentes.InterLight.deriveFont(25.0f));
        lblCiudad.setForeground(Color.decode("#8C8C8C"));
        lblCiudad.setHorizontalAlignment(JLabel.LEFT);
        gbc.insets=insetLbl;
        gbc.gridy++;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        cuerpo.add(lblCiudad,gbc);
        
        txtCiudad=new TextFieldRedondeado(0);
        txtCiudad.setGrosorBorde(4);
        txtCiudad.setRadioDeBorde(40);
        txtCiudad.setColorBorde(Color.decode("#CACACA"));
        txtCiudad.setFont(UtilidadesFuentes.InterLight.deriveFont(22.0f));
        txtCiudad.setForeground(Color.decode("#8C8C8C"));
        txtCiudad.setHorizontalAlignment(JLabel.LEFT);
        txtCiudad.setBorder( BorderFactory.createEmptyBorder(2, 20, 0, 20) );
        txtCiudad.setPreferredSize(nuevoDim2);
        txtCiudad.setMinimumSize(nuevoDim2);
        gbc.insets=insetTxt;
        gbc.anchor=GridBagConstraints.LINE_START;
        gbc.gridy++;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=1;
        cuerpo.add(txtCiudad,gbc);
        
        JLabel lblDireccion=new JLabel("Dirección");
        lblDireccion.setFont(UtilidadesFuentes.InterLight.deriveFont(25.0f));
        lblDireccion.setForeground(Color.decode("#8C8C8C"));
        lblDireccion.setHorizontalAlignment(JLabel.LEFT);
        gbc.insets=insetLbl;
        gbc.gridy++;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        cuerpo.add(lblDireccion,gbc);
        
        txtDireccion=new TextFieldRedondeado(0);
        txtDireccion.setGrosorBorde(4);
        txtDireccion.setRadioDeBorde(40);
        txtDireccion.setColorBorde(Color.decode("#CACACA"));
        txtDireccion.setFont(UtilidadesFuentes.InterLight.deriveFont(22.0f));
        txtDireccion.setForeground(Color.decode("#8C8C8C"));
        txtDireccion.setHorizontalAlignment(JLabel.LEFT);
        txtDireccion.setBorder( BorderFactory.createEmptyBorder(2, 20, 0, 20) );
        txtDireccion.setPreferredSize(nuevoDim1);
        txtDireccion.setMinimumSize(nuevoDim1);
        gbc.insets=new Insets(0, 65, 25, 65);
        gbc.anchor=GridBagConstraints.LINE_START;
        gbc.gridy++;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=1;
        gbc.gridwidth=2;
        cuerpo.add(txtDireccion,gbc);
        
        gbc.gridx=2;
        
        JLabel lblDistrito=new JLabel("Distrito");
        lblDistrito.setFont(UtilidadesFuentes.InterLight.deriveFont(25.0f));
        lblDistrito.setForeground(Color.decode("#8C8C8C"));
        lblDistrito.setHorizontalAlignment(JLabel.LEFT);
        gbc.anchor=GridBagConstraints.LINE_START;
        gbc.insets=new Insets(0, 30, 5, 45);
        gbc.gridy=0;
        gbc.gridwidth=1;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=1;
        cuerpo.add(lblDistrito,gbc);
        
        txtDistrito=new TextFieldRedondeado(0);
        txtDistrito.setGrosorBorde(4);
        txtDistrito.setRadioDeBorde(40);
        txtDistrito.setColorBorde(Color.decode("#CACACA"));
        txtDistrito.setFont(UtilidadesFuentes.InterLight.deriveFont(22.0f));
        txtDistrito.setForeground(Color.decode("#8C8C8C"));
        txtDistrito.setHorizontalAlignment(JLabel.LEFT);
        txtDistrito.setBorder( BorderFactory.createEmptyBorder(2, 20, 0, 20) );
        txtDistrito.setPreferredSize(nuevoDim2);
        txtDistrito.setMinimumSize(nuevoDim2);
        gbc.insets=new Insets(0, 0, 25, 45);
        gbc.anchor=GridBagConstraints.LINE_START;
        gbc.gridy++;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=1;
        cuerpo.add(txtDistrito,gbc);
        
        JLabel lblCodigoPostal=new JLabel("Código Postal");
        lblCodigoPostal.setFont(UtilidadesFuentes.InterLight.deriveFont(25.0f));
        lblCodigoPostal.setForeground(Color.decode("#8C8C8C"));
        lblCodigoPostal.setHorizontalAlignment(JLabel.LEFT);
        gbc.anchor=GridBagConstraints.LINE_START;
        gbc.insets=new Insets(0, 30, 5, 45);
        gbc.gridy++;
        gbc.gridwidth=1;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=1;
        cuerpo.add(lblCodigoPostal,gbc);
        
        txtCodigoPostal=new TextFieldRedondeado(0);
        txtCodigoPostal.setGrosorBorde(4);
        txtCodigoPostal.setRadioDeBorde(40);
        txtCodigoPostal.setColorBorde(Color.decode("#CACACA"));
        txtCodigoPostal.setFont(UtilidadesFuentes.InterLight.deriveFont(22.0f));
        txtCodigoPostal.setForeground(Color.decode("#8C8C8C"));
        txtCodigoPostal.setHorizontalAlignment(JLabel.LEFT);
        txtCodigoPostal.setBorder( BorderFactory.createEmptyBorder(2, 20, 0, 20) );
        txtCodigoPostal.setPreferredSize(nuevoDim2);
        txtCodigoPostal.setMinimumSize(nuevoDim2);
        gbc.insets=new Insets(0, 0, 25, 45);
        gbc.anchor=GridBagConstraints.LINE_START;
        gbc.gridy++;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=1;
        cuerpo.add(txtCodigoPostal,gbc);
    }
    
    private void iniciarComponentesCuerpoInferior(int width, int height){
        Dimension dimBoton = new Dimension(230,50);
        GridBagConstraints gbc = new GridBagConstraints();
        btnGuardarCambios = new BotonRedondeado(10, 3, Color.decode("#8C8C8C"),"GUARDAR CAMBIOS",UtilidadesFuentes.InterRegular.deriveFont(20.0f)){
            @Override
            public void botonPresionado() {
                Configuracion configuracionAGuardar = new Configuracion();
                try{
                    configuracionAGuardar.setNumeroTerminal(Integer.parseInt(txtNumTerminal.getText()));
                    configuracionAGuardar.setTelefono(Integer.parseInt(txtTelefono.getText()));
                    configuracionAGuardar.setCodigoPostal(Integer.parseInt(txtCodigoPostal.getText()));
                }catch (Exception er) {System.err.println(er);}
                configuracionAGuardar.setRazonSocial(txtRazonSocial.getText());
                configuracionAGuardar.setRUC(txtRUC.getText());
                
                configuracionAGuardar.setCodigoTienda(txtCodigoTienda.getText());
                
                configuracionAGuardar.setProvincia(txtProvincia.getText());
                configuracionAGuardar.setDistrito(txtDistrito.getText());
                configuracionAGuardar.setCiudad(txtCiudad.getText());
                configuracionAGuardar.setDireccion(txtDireccion.getText());
                
                UtilidadSesion.configuracionActual=configuracionAGuardar;
                ControlConfiguracion.aplicarCambios(configuracionAGuardar);
                lblDatosGuardados.setForeground(Color.decode("#8C8C8C"));
                if(!timerAlertaGuardado.isRunning()){
                    timerAlertaGuardado.start();
                }
            }
        };
        btnGuardarCambios.setPreferredSize(dimBoton);
        btnGuardarCambios.setMinimumSize(dimBoton);
        gbc.gridx=0;
        gbc.gridy=10;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=1;
        gbc.weighty=0;
        gbc.gridwidth=3;
        cuerpo.add(btnGuardarCambios,gbc);
        
        lblDatosGuardados = new JLabel("Datos Guardados");
        lblDatosGuardados.setForeground(new Color(0,0,0,0));
        lblDatosGuardados.setFont(UtilidadesFuentes.InterRegular.deriveFont(15.0f));
        gbc.insets=new Insets(0, 0, 15, 0);
        gbc.anchor=GridBagConstraints.PAGE_START;
        gbc.gridx=0;
        gbc.gridy=11;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=1;
        gbc.weighty=1;
        gbc.gridwidth=3;
        cuerpo.add(lblDatosGuardados,gbc);
    }

    public void cargarListaDeConfiguracion(){
    }
 
}

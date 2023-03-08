package Presentacion.Interfaces.Menu;

import Presentacion.Interfaces.FramePrincipal;
import Presentacion.Interfaces.Inventario.ModificarDepartamento;
import Presentacion.Interfaces.PanelImagen;
import Presentacion.Interfaces.VentanaSalir;
import Presentacion.Utilidades.UtilidadSesion;
import Presentacion.Utilidades.UtilidadesFuentes;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author sortizu
 */
public class Menu extends javax.swing.JPanel {

    /**
     * Creates new form Menu
     */
    public Menu(String nombreUsuario, boolean [] permisos) {
        initComponents();
        setOpaque(false);
        btnModuloVentas.setEnabled(permisos[0]);
        btnModuloUsuarios.setEnabled(permisos[1]);
        btnModuloProveedores.setEnabled(permisos[2]);
        btnModuloClientes.setEnabled(permisos[3]);
        btnModuloInventario.setEnabled(permisos[4]);
        btnModuloReportes.setEnabled(permisos[5]);
        btnConfiguracion.setEnabled(permisos[6]);
        lblUsuario.setText("¡Bienvenido, "+nombreUsuario.split(" ")[0]+"!");
        LocalDate fecha = LocalDate.now();
        lblDia.setText(fecha.format(DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' YYYY", new Locale("es", "ES"))));
        
        addComponentListener(new ComponentAdapter(){
            @Override
            public void componentShown(ComponentEvent e) {
                lblRazonSocial.setText(UtilidadSesion.configuracionActual.getRazonSocial());
                lnlNumTerminal.setText("Terminal: "+UtilidadSesion.configuracionActual.getNumeroTerminal());
                lblCodigoTienda.setText("Código de Tienda: "+UtilidadSesion.configuracionActual.getCodigoTienda());
            }
        });

    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        btnSalir = new javax.swing.JLabel();
        PanelSistema = new PanelImagen("/Presentacion/Imagenes/Paneles/Menu/PanelMenu.png");
        lblRazonSocial = new javax.swing.JLabel();
        cabecera = new javax.swing.JSeparator();
        lblUsuario = new javax.swing.JLabel();
        lblDia = new javax.swing.JLabel();
        SeparadorDatos1 = new javax.swing.JSeparator();
        lblCodigoTienda = new javax.swing.JLabel();
        lnlNumTerminal = new javax.swing.JLabel();
        SeparadorDatos2 = new javax.swing.JSeparator();
        PanelDeBotonesDeModulos = new javax.swing.JPanel();
        btnModuloVentas = new javax.swing.JLabel();
        btnModuloInventario = new javax.swing.JLabel();
        btnModuloReportes = new javax.swing.JLabel();
        btnModuloClientes = new javax.swing.JLabel();
        btnModuloProveedores = new javax.swing.JLabel();
        btnModuloUsuarios = new javax.swing.JLabel();
        lblMENU = new javax.swing.JLabel();
        pie = new javax.swing.JSeparator();
        lblNombreDeSistema = new javax.swing.JLabel();
        lvlversion = new javax.swing.JLabel();
        btnConfiguracion = new javax.swing.JLabel();
        btnAyuda = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(1360, 768));
        setPreferredSize(new java.awt.Dimension(1360, 768));
        setLayout(new java.awt.GridBagLayout());

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentacion/Imagenes/BotonSalirModulo.png"))); // NOI18N
        btnSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSalirMousePressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(btnSalir, gridBagConstraints);

        PanelSistema.setBackground(new java.awt.Color(255, 255, 255));
        PanelSistema.setMinimumSize(new java.awt.Dimension(1073, 768));
        PanelSistema.setPreferredSize(new java.awt.Dimension(1073, 768));
        PanelSistema.setLayout(new java.awt.GridBagLayout());

        lblRazonSocial.setFont(UtilidadesFuentes.InterRegular.deriveFont(15.0f));
        lblRazonSocial.setForeground(new java.awt.Color(140, 140, 140));
        lblRazonSocial.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRazonSocial.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
        PanelSistema.add(lblRazonSocial, gridBagConstraints);

        cabecera.setForeground(new java.awt.Color(208, 208, 208));
        cabecera.setPreferredSize(new java.awt.Dimension(1025, 10));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 40, 0, 40);
        PanelSistema.add(cabecera, gridBagConstraints);

        lblUsuario.setFont(UtilidadesFuentes.InterBlack.deriveFont(64.0f));
        lblUsuario.setForeground(new java.awt.Color(88, 88, 88));
        lblUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsuario.setText("¡Bienvenido, Usuario!");
        lblUsuario.setPreferredSize(new java.awt.Dimension(684, 77));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(50, 0, 0, 0);
        PanelSistema.add(lblUsuario, gridBagConstraints);

        lblDia.setFont(UtilidadesFuentes.InterBlack.deriveFont(40.0f));
        lblDia.setForeground(new java.awt.Color(88, 88, 88));
        lblDia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDia.setText("9 de octubre de 2020");
        lblDia.setPreferredSize(new java.awt.Dimension(850, 16));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 30, 0);
        PanelSistema.add(lblDia, gridBagConstraints);

        SeparadorDatos1.setForeground(new java.awt.Color(208, 208, 208));
        SeparadorDatos1.setPreferredSize(new java.awt.Dimension(700, 10));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 100, 0, 100);
        PanelSistema.add(SeparadorDatos1, gridBagConstraints);

        lblCodigoTienda.setFont(UtilidadesFuentes.InterRegular.deriveFont(25.0f));
        lblCodigoTienda.setForeground(new java.awt.Color(140, 140, 140));
        lblCodigoTienda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCodigoTienda.setText("Codigo de Tienda: XW962");
        lblCodigoTienda.setMaximumSize(new java.awt.Dimension(280, 50));
        lblCodigoTienda.setMinimumSize(new java.awt.Dimension(280, 50));
        lblCodigoTienda.setPreferredSize(new java.awt.Dimension(280, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 80, 10, 20);
        PanelSistema.add(lblCodigoTienda, gridBagConstraints);

        lnlNumTerminal.setFont(UtilidadesFuentes.InterRegular.deriveFont(25.0f));
        lnlNumTerminal.setForeground(new java.awt.Color(140, 140, 140));
        lnlNumTerminal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lnlNumTerminal.setText("Terminal: 1");
        lnlNumTerminal.setMaximumSize(new java.awt.Dimension(280, 50));
        lnlNumTerminal.setMinimumSize(new java.awt.Dimension(280, 50));
        lnlNumTerminal.setPreferredSize(new java.awt.Dimension(280, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 80);
        PanelSistema.add(lnlNumTerminal, gridBagConstraints);

        SeparadorDatos2.setForeground(new java.awt.Color(208, 208, 208));
        SeparadorDatos2.setPreferredSize(new java.awt.Dimension(800, 10));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 80, 0, 80);
        PanelSistema.add(SeparadorDatos2, gridBagConstraints);

        PanelDeBotonesDeModulos.setBackground(new java.awt.Color(255, 255, 255));
        PanelDeBotonesDeModulos.setOpaque(false);
        PanelDeBotonesDeModulos.setPreferredSize(new java.awt.Dimension(800, 310));
        PanelDeBotonesDeModulos.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10));

        btnModuloVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentacion/Imagenes/Boton Ventas.png"))); // NOI18N
        btnModuloVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModuloVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnModuloVentasMouseReleased(evt);
            }
        });
        PanelDeBotonesDeModulos.add(btnModuloVentas);

        btnModuloInventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentacion/Imagenes/Boton Inventario.png"))); // NOI18N
        btnModuloInventario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModuloInventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnModuloInventarioMouseReleased(evt);
            }
        });
        PanelDeBotonesDeModulos.add(btnModuloInventario);

        btnModuloReportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentacion/Imagenes/Boton Reportes.png"))); // NOI18N
        btnModuloReportes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModuloReportes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnModuloReportesMouseReleased(evt);
            }
        });
        PanelDeBotonesDeModulos.add(btnModuloReportes);

        btnModuloClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentacion/Imagenes/Boton Clientes.png"))); // NOI18N
        btnModuloClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModuloClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnModuloClientesMouseReleased(evt);
            }
        });
        PanelDeBotonesDeModulos.add(btnModuloClientes);

        btnModuloProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentacion/Imagenes/Boton Proveedores.png"))); // NOI18N
        btnModuloProveedores.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModuloProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnModuloProveedoresMouseReleased(evt);
            }
        });
        PanelDeBotonesDeModulos.add(btnModuloProveedores);

        btnModuloUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentacion/Imagenes/Boton Usuarios.png"))); // NOI18N
        btnModuloUsuarios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModuloUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnModuloUsuariosMouseReleased(evt);
            }
        });
        PanelDeBotonesDeModulos.add(btnModuloUsuarios);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        PanelSistema.add(PanelDeBotonesDeModulos, gridBagConstraints);

        lblMENU.setFont(UtilidadesFuentes.InterRegular.deriveFont(35.0f));
        lblMENU.setForeground(new java.awt.Color(140, 140, 140));
        lblMENU.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMENU.setText("MENÚ");
        lblMENU.setMaximumSize(new java.awt.Dimension(280, 50));
        lblMENU.setMinimumSize(new java.awt.Dimension(280, 50));
        lblMENU.setPreferredSize(new java.awt.Dimension(850, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        PanelSistema.add(lblMENU, gridBagConstraints);

        pie.setForeground(new java.awt.Color(208, 208, 208));
        pie.setPreferredSize(new java.awt.Dimension(850, 2));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 40, 0, 40);
        PanelSistema.add(pie, gridBagConstraints);

        lblNombreDeSistema.setFont(UtilidadesFuentes.InterRegular.deriveFont(15.0f));
        lblNombreDeSistema.setForeground(new java.awt.Color(140, 140, 140));
        lblNombreDeSistema.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNombreDeSistema.setText("POS eLiquor");
        lblNombreDeSistema.setPreferredSize(new java.awt.Dimension(425, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 40, 5, 0);
        PanelSistema.add(lblNombreDeSistema, gridBagConstraints);

        lvlversion.setFont(UtilidadesFuentes.InterRegular.deriveFont(15.0f));
        lvlversion.setForeground(new java.awt.Color(140, 140, 140));
        lvlversion.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lvlversion.setText("V 2.0");
        lvlversion.setPreferredSize(new java.awt.Dimension(425, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 40);
        PanelSistema.add(lvlversion, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(PanelSistema, gridBagConstraints);

        btnConfiguracion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentacion/Imagenes/BotonConfigModulo.png"))); // NOI18N
        btnConfiguracion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConfiguracion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnConfiguracionMouseReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 1.0;
        add(btnConfiguracion, gridBagConstraints);

        btnAyuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentacion/Imagenes/BotonAyudaModulo.png"))); // NOI18N
        btnAyuda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAyuda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnAyudaMouseReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        add(btnAyuda, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirMousePressed
        /*Utilizando utilidades de swing para obtener la ventana principal (FramePrincipal)
        y cerrar todo el programa*/
         VentanaSalir ventanaSalir = new VentanaSalir(this);
         ((FramePrincipal) SwingUtilities.getWindowAncestor(this)).mostrarPanelEmergente(ventanaSalir);
         ventanaSalir.requestFocusInWindow();
    }//GEN-LAST:event_btnSalirMousePressed

    private void btnModuloUsuariosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModuloUsuariosMouseReleased
        if(btnModuloUsuarios.isEnabled()){
            JPanel parent = (JPanel)getParent();
            CardLayout layout = (CardLayout) parent.getLayout();
            Presentacion.Interfaces.Usuarios.Usuarios usuarios = new Presentacion.Interfaces.Usuarios.Usuarios(parent);
            parent.add("usuarios",usuarios);
            layout.show(parent, "usuarios");
        }
    }//GEN-LAST:event_btnModuloUsuariosMouseReleased

    private void btnModuloVentasMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModuloVentasMouseReleased
        if(btnModuloVentas.isEnabled()){
            JPanel parent = (JPanel)getParent();
            CardLayout layout = (CardLayout) parent.getLayout();
            Presentacion.Interfaces.Ventas.Ventas ventas = new Presentacion.Interfaces.Ventas.Ventas(parent);
            parent.add("ventas",ventas);
            layout.show(parent, "ventas");
        }
    }//GEN-LAST:event_btnModuloVentasMouseReleased

    private void btnModuloInventarioMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModuloInventarioMouseReleased
        if(btnModuloInventario.isEnabled()){
            JPanel parent = (JPanel)getParent();
            CardLayout layout = (CardLayout) parent.getLayout();
            Presentacion.Interfaces.Inventario.Inventario inventario = new Presentacion.Interfaces.Inventario.Inventario(parent);
            parent.add("inventario",inventario);
            layout.show(parent, "inventario");
        }
    }//GEN-LAST:event_btnModuloInventarioMouseReleased

    private void btnModuloReportesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModuloReportesMouseReleased
        if(btnModuloReportes.isEnabled()){
            JPanel parent = (JPanel)getParent();
            CardLayout layout = (CardLayout) parent.getLayout();
            Presentacion.Interfaces.Reportes.Reportes reportes = new Presentacion.Interfaces.Reportes.Reportes(parent);
            parent.add("reportes",reportes);
            layout.show(parent, "reportes");
        }
    }//GEN-LAST:event_btnModuloReportesMouseReleased

    private void btnModuloProveedoresMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModuloProveedoresMouseReleased
        if(btnModuloProveedores.isEnabled()){
            JPanel parent = (JPanel)getParent();
            CardLayout layout = (CardLayout) parent.getLayout();
            Presentacion.Interfaces.Proveedores.Proveedores reportes = new Presentacion.Interfaces.Proveedores.Proveedores(parent);
            parent.add("proveedores",reportes);
            layout.show(parent, "proveedores");
        }
    }//GEN-LAST:event_btnModuloProveedoresMouseReleased

    private void btnModuloClientesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModuloClientesMouseReleased
        if(btnModuloClientes.isEnabled()){
            JPanel parent = (JPanel)getParent();
            CardLayout layout = (CardLayout) parent.getLayout();
            Presentacion.Interfaces.Clientes.Clientes clientes = new Presentacion.Interfaces.Clientes.Clientes(parent);
            parent.add("clientes",clientes);
            layout.show(parent, "clientes");
        }
    }//GEN-LAST:event_btnModuloClientesMouseReleased

    private void btnConfiguracionMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfiguracionMouseReleased
        if (btnConfiguracion.isEnabled()){
            JPanel parent = (JPanel)getParent();
            CardLayout layout = (CardLayout) parent.getLayout();
            Presentacion.Interfaces.Configuracion.Configuracion configuracion = new Presentacion.Interfaces.Configuracion.Configuracion(parent);
            parent.add("configuracion",configuracion);
            layout.show(parent, "configuracion");
        }
    }//GEN-LAST:event_btnConfiguracionMouseReleased

    private void btnAyudaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAyudaMouseReleased
        PanelImagen panelTutorial=new PanelImagen("/Presentacion/Imagenes/Paneles/Boton Ayuda/TutorialMenu.png");
        panelTutorial.setPreferredSize(new Dimension(panelTutorial.getImagen().getWidth(null),panelTutorial.getImagen().getHeight(null)));
        panelTutorial.setOpaque(false);
        panelTutorial.setLayout(null);
        JLabel lblBotonAyudaCerrar = new JLabel(btnAyuda.getIcon());
        lblBotonAyudaCerrar.setBounds(btnAyuda.getX(),btnAyuda.getY(),btnAyuda.getWidth(),btnAyuda.getHeight());
        lblBotonAyudaCerrar.setCursor(btnAyuda.getCursor());
        lblBotonAyudaCerrar.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseReleased(MouseEvent e) {
                ((FramePrincipal) SwingUtilities.getWindowAncestor(e.getComponent())).cerrarPanelesEmergentes();
            }
        });
        panelTutorial.add(lblBotonAyudaCerrar);
        ((FramePrincipal) SwingUtilities.getWindowAncestor(this)).mostrarPanelEmergente(panelTutorial);
    }//GEN-LAST:event_btnAyudaMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelDeBotonesDeModulos;
    private javax.swing.JPanel PanelSistema;
    private javax.swing.JSeparator SeparadorDatos1;
    private javax.swing.JSeparator SeparadorDatos2;
    private javax.swing.JLabel btnAyuda;
    private javax.swing.JLabel btnConfiguracion;
    private javax.swing.JLabel btnModuloClientes;
    private javax.swing.JLabel btnModuloInventario;
    private javax.swing.JLabel btnModuloProveedores;
    private javax.swing.JLabel btnModuloReportes;
    private javax.swing.JLabel btnModuloUsuarios;
    private javax.swing.JLabel btnModuloVentas;
    private javax.swing.JLabel btnSalir;
    private javax.swing.JSeparator cabecera;
    private javax.swing.JLabel lblCodigoTienda;
    private javax.swing.JLabel lblDia;
    private javax.swing.JLabel lblMENU;
    private javax.swing.JLabel lblNombreDeSistema;
    private javax.swing.JLabel lblRazonSocial;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JLabel lnlNumTerminal;
    private javax.swing.JLabel lvlversion;
    private javax.swing.JSeparator pie;
    // End of variables declaration//GEN-END:variables
}

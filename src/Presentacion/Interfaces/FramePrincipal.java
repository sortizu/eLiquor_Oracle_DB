/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Presentacion.Interfaces;

import Datos.DAO.Conexion;
import Presentacion.Interfaces.Clientes.Clientes;
import Presentacion.Utilidades.UtilidadSesion;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import javax.swing.JPanel;

//Paquetes importados para debug
import Presentacion.Interfaces.Inventario.Inventario;
import Presentacion.Interfaces.Login.Login;
import Presentacion.Interfaces.Proveedores.Proveedores;
import Presentacion.Interfaces.Reportes.Reportes;
import Presentacion.Interfaces.Usuarios.Usuarios;
import Presentacion.Interfaces.Ventas.Ventas;
import Presentacion.Interfaces.Configuracion.Configuracion;
import Presentacion.Interfaces.Ventas.PanelDeVentas;
import java.awt.Dimension;
import java.awt.Toolkit;
/**
 *
 * @author sortizu
 */
public class FramePrincipal extends javax.swing.JFrame {

    CardLayout layoutContenedorPaneles;
    
    public FramePrincipal() {
        initComponents();
        setLocationRelativeTo(null);
        ContenedorPaneles.setOpaque(false);
        layoutContenedorPaneles=(CardLayout)(ContenedorPaneles.getLayout());
        setSize(new Dimension(1360,768));
        setLocationRelativeTo(null);
        //setSize(Toolkit.getDefaultToolkit().getScreenSize());
        //setExtendedState(MAXIMIZED_BOTH);
        //Configuracion del inicio de sesion a la Base de datos
        Conexion cn = new Conexion();
        cn.setStaticRootConfiguration();
        //Configuracion del overlay (Ventanas desplegables)
        JPanel overlay=new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(getBackground());
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
            
        };
        overlay.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        overlay.setOpaque(false);
        overlay.setBounds(0, 0, getWidth(), getHeight());
        overlay.setBackground(new Color(129,129,129,114));
        overlay.addMouseListener( new MouseAdapter() { } );
        setGlassPane(overlay);
        //-------------------------------------------------------------------------------------------------------------------------
        //Considerar este codigo como el modo "Debug"
        //Instancia el contenedor de modulo que desees probar y este aparecera 
        //al ejecutar el programa
        //ACLARACION: Todas las interfaces relacionadas a un modulo se almacenan en "contenedores"
        //Existe entonces el contenedor "Clientes","Usuarios",etc. dentro de paquetes con el mismo nombre
        //en el paquete de interfaces. Si deseas ver las interfaces de un modulo asegurate que estas instanciando
        //su contenedor, caso contrario puede que las interfaces no funcionen correctamente.
        //Para ver el inicio normal del sistema, debes comentar toda la zona de debug.
        //Inicio de zona de debug
        {
        UtilidadSesion.nombreUsuarioActual="Debug";
        
        //Cambiar a partir de aqui
        
        //Descomenta las tres lineas de abajo para entrar directamente al modulo de login
        /*Login login = new Login();
        ContenedorPaneles.add("login",login);
        layoutContenedorPaneles.show(ContenedorPaneles, "login");
        */
            
        //Descomenta las tres lineas de abajo para entrar directamente al modulo de usuarios
        Usuarios usuarios = new Usuarios(ContenedorPaneles);
        ContenedorPaneles.add("usuarios",usuarios);
        layoutContenedorPaneles.show(ContenedorPaneles, "usuarios");
        
        
        //Descomenta las tres lineas de abajo para entrar directamente al modulo de inventario
        /*Inventario inventario = new Inventario(ContenedorPaneles);
        ContenedorPaneles.add("inventario",inventario);
        layoutContenedorPaneles.show(ContenedorPaneles, "inventario");*/
        
        //Descomenta las tres lineas de abajo para entrar directamente al modulo de ventas
        /*Ventas ventas = new Ventas(ContenedorPaneles);
        ContenedorPaneles.add("ventas",ventas);
        layoutContenedorPaneles.show(ContenedorPaneles, "ventas");*/

        
        //Descomenta las tres lineas de abajo para entrar directamente al modulo de reportes
        /*Reportes reportes = new Reportes(ContenedorPaneles);
        ContenedorPaneles.add("reportes",reportes);
        layoutContenedorPaneles.show(ContenedorPaneles, "reportes");
        */
        //Descomenta las tres lineas de abajo para entrar directamente al modulo de proveedores
        /*Proveedores proveedores = new Proveedores(ContenedorPaneles);
        ContenedorPaneles.add("proveedores",proveedores);
        layoutContenedorPaneles.show(ContenedorPaneles, "proveedores");
        */
        //Descomenta las tres lineas de abajo para entrar directamente al modulo de cliente
        /*Clientes clientes = new Clientes(ContenedorPaneles);
        ContenedorPaneles.add("clientes",clientes);
        layoutContenedorPaneles.show(ContenedorPaneles, "clientes");
        */
        //Descomenta las tres lineas de abajo para entrar directamente a la configuracion
       /* Configuracion configuracion = new Configuracion(ContenedorPaneles);
        ContenedorPaneles.add("configuracion",configuracion);
        layoutContenedorPaneles.show(ContenedorPaneles, "configuracion");*/
        //Fin de zona de debug
        //-------------------------------------------------------------------------------------------------------------------------
    }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Fondo = new PanelImagen("/Presentacion/Imagenes/FondoBlanco.png");
        ContenedorPaneles = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        Fondo.setLayout(new java.awt.CardLayout());

        ContenedorPaneles.setLayout(new java.awt.CardLayout());
        Fondo.add(ContenedorPaneles, "card2");

        getContentPane().add(Fondo, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FramePrincipal().setVisible(true);
            }
        });
    }
    
    public void cerrarPanelesEmergentes(){
        JPanel overlay=(JPanel) getGlassPane();
        overlay.removeAll();
        overlay.setVisible(false);
    }
    
    public void mostrarPanelEmergente(JPanel panelEmergente){
        JPanel overlay= (JPanel)getGlassPane();
        overlay.add(panelEmergente);
        overlay.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ContenedorPaneles;
    private javax.swing.JPanel Fondo;
    // End of variables declaration//GEN-END:variables
}

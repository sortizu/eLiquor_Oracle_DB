package Presentacion.Interfaces;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author sortizu
 */
class BotonNavegacion extends JPanel{
    
    public static final int SALIR=0;
    public static final int MENU=1;
    public static final int CONFIGURACION=2;
    public static final int VOLVER=3;
    public static final int AYUDA=4;
    private PanelImagen imagenBoton;
    private Container parentContainer;
    private JPanel panelModulo;
    
    public BotonNavegacion(int opcion,int width,int height,Container parentContainer) {
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.panelModulo=panelModulo;
        setLayout(new BorderLayout(0,0));
        setOpaque(false);
        this.parentContainer=parentContainer;
        String direccionImagen;
        switch (opcion) {
            case 1:
                direccionImagen="/Presentacion/Imagenes/BotonHomeModulo.png";
                addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        funcionMenu();
                    }
                });
                break;
            case 2:
                direccionImagen="/Presentacion/Imagenes/BotonConfigModulo.png";
                addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        funcionConfig();
                    }
                });
                break;
            case 3:
                direccionImagen="/Presentacion/Imagenes/BotonVolver.png";
                addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        funcionMenu();
                    }
                });
                break;
            case 4:
                direccionImagen="/Presentacion/Imagenes/BotonAyudaPequeño.png";
                addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        funcionAyuda("");
                    }
                });
                break;
            case 0:
            default:
                direccionImagen="/Presentacion/Imagenes/BotonSalirModulo.png";
                addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        funcionSalir();
                    }
                });
        }
        setPreferredSize(new Dimension(width,height));
        setMaximumSize(new Dimension(width,height));
        setMinimumSize(new Dimension(width,height));
        imagenBoton = new PanelImagen(direccionImagen);
        add(imagenBoton,BorderLayout.CENTER);
    }
    
    protected void funcionSalir(){
        ((JFrame) SwingUtilities.getWindowAncestor(parentContainer)).dispose();
    }
    protected void funcionMenu(){
        PanelModulo.PanelContenedorComponentes panelContenedorComponentes = (PanelModulo.PanelContenedorComponentes)parentContainer;
        PanelModulo panelModulo = (PanelModulo)panelContenedorComponentes.getParentContainer();
        JPanel panelContenedorPrincipal = (JPanel)panelModulo.getParentContainer();
        CardLayout layout= (CardLayout) panelContenedorPrincipal.getLayout();
        layout.show(panelContenedorPrincipal, "menu");
        panelContenedorPrincipal.remove(panelModulo);
    }
    protected void funcionConfig(){}
    
    protected void funcionAyuda(String imagenTutorial){
        if(!imagenTutorial.isEmpty()){
            PanelImagen panelTutorial=new PanelImagen(imagenTutorial);
            panelTutorial.setPreferredSize(new Dimension(panelTutorial.getImagen().getWidth(null),panelTutorial.getImagen().getHeight(null)));
            panelTutorial.setOpaque(false);
            panelTutorial.setLayout(null);
            
            PanelImagen panelNuevoBotonAyuda=new PanelImagen("/Presentacion/Imagenes/BotonAyudaPequeño.png");
            Point p = SwingUtilities.convertPoint(this,this.getX(),this.getY(),((JFrame) SwingUtilities.getWindowAncestor(parentContainer)));
            panelNuevoBotonAyuda.setBounds(this.getX(),(int)p.getY(),this.getWidth(),this.getHeight());
            panelNuevoBotonAyuda.setCursor(this.getCursor());
            panelNuevoBotonAyuda.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseReleased(MouseEvent e) {
                    ((FramePrincipal) SwingUtilities.getWindowAncestor(e.getComponent())).cerrarPanelesEmergentes();
                }
            });
            panelTutorial.add(panelNuevoBotonAyuda);
            ((FramePrincipal) SwingUtilities.getWindowAncestor(this)).mostrarPanelEmergente(panelTutorial);
            panelTutorial.requestFocusInWindow();
        }
    }
}
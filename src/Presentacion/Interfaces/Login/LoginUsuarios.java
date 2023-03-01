package Presentacion.Interfaces.Login;

import Negocio.ControlLogin;
import Presentacion.Interfaces.PanelImagen;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import Presentacion.Interfaces.ScrollBarCustom;
import Presentacion.Utilidades.UtilidadesFuentes;
import java.awt.CardLayout;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author sortizu
 */
public class LoginUsuarios extends javax.swing.JPanel {

    DefaultTableCellRenderer nuevoCellRenderer = new DefaultTableCellRenderer(){
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            
            if(value instanceof JLabel){
                JLabel lbl=(JLabel)value;
                return lbl;
            }
            
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
        }
    };
    DefaultTableModel modeloListaDeUsuarios;
    ScrollBarCustom scrollCustom;
    
    //Arreglo para almacenar los usuarios cargados de la base de datos
    /*(Propuesta) Se podria usar solo un arreglo para los ID (String)
    Pues es el unico dato que necesitamos, el nombre puede cargarse
    en el metodo mostrarUsuariosEnLista
    */
    Object[][] datosDeUsuarios;
    public LoginUsuarios() {
        initComponents();
        btnSiguiente.setEnabled(false);
        btnSiguiente.setVisible(false);
        //setBackground(new Color(0,0,0,0));
        setOpaque(false);
     
        modeloListaDeUsuarios=(DefaultTableModel)ListaDeUsuarios.getModel();
        ListaDeUsuarios.setFont(UtilidadesFuentes.InterExtraLight.deriveFont(25.0f));
        ListaDeUsuarios.setForeground(Color.decode("#8C8C8C"));
        ListaDeUsuarios.setDefaultRenderer(Object.class, nuevoCellRenderer);
        ListaDeUsuarios.setSelectionBackground(Color.decode("#23A020"));
        ListaDeUsuarios.setSelectionForeground(Color.white);
        ListaDeUsuarios.setIntercellSpacing(new Dimension(0,0));
        ListaDeUsuarios.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.getClickCount()==2){
                    btnSiguienteMousePressed(e);
                }
            }
        });
        
        
        jScrollPane1.setBorder(BorderFactory.createEmptyBorder());
        jScrollPane1.getViewport().setBackground(Color.WHITE);
        jScrollPane1.setOpaque(false);
        scrollCustom=new ScrollBarCustom();
        scrollCustom.setUnitIncrement(16);
        jScrollPane1.setVerticalScrollBar(scrollCustom);
        ListaDeUsuarios.getTableHeader().setUI(null);
        //Seleccion de fila para iconos
        ListaDeUsuarios.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
        public void valueChanged(ListSelectionEvent event) {
            btnSiguiente.setEnabled(true);
            btnSiguiente.setVisible(true);
            for (int i = 0; i < ListaDeUsuarios.getRowCount(); i++) {
                JLabel icono=(JLabel)ListaDeUsuarios.getValueAt(i, 0);
                if(i==ListaDeUsuarios.getSelectedRow()){
                    icono.setBackground(ListaDeUsuarios.getSelectionBackground());
                }else{
                    icono.setBackground(ListaDeUsuarios.getBackground());
                }
                icono.setOpaque(true);
                ListaDeUsuarios.setValueAt(icono, i, 0);
            }
        }
    });
        
        addHierarchyListener(new HierarchyListener(){
            @Override
            public void hierarchyChanged(HierarchyEvent e) {
                btnSiguiente.setEnabled(false);
                btnSiguiente.setVisible(false);
                mostrarUsuariosEnLista();
            }
        
        });

    }
    
    private void mostrarUsuariosEnLista(){
    //Metodo donde se cargaran los usuarios de la BD y se mostraran en la lista
    //Se debe ejecutar al inicio
        modeloListaDeUsuarios.setRowCount(0);
        datosDeUsuarios=ControlLogin.mostrarListaDeUsuarios();
        for (int i = 0; i < datosDeUsuarios.length; i++) {
            añadirUsuarioALista((String)datosDeUsuarios[i][1]);
        }
    }
    
    public void añadirUsuarioALista(String nombre){
        JLabel imagenUsuario=new JLabel(new ImageIcon(getClass().getResource("/Presentacion/Imagenes/iconoCuenta.png")));
        modeloListaDeUsuarios.addRow(new Object[]{imagenUsuario,nombre});
        //Adaptando el tamaño de la barra de Scroll
        double newScrollBarHeight=Math.pow(jScrollPane1.getPreferredSize().getHeight(),2)/(
                ListaDeUsuarios.getRowHeight()*ListaDeUsuarios.getRowCount());
        
        scrollCustom.setThumbSize((int)newScrollBarHeight);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        eLiquor = new javax.swing.JLabel();
        PanelListaDeUsuarios = new PanelImagen("/Presentacion/Imagenes/Paneles/Login/PanelListaDeUsuarios.png");
        btnSiguiente = new javax.swing.JLabel();
        cabecera = new javax.swing.JSeparator();
        pieDeLista = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        ListaDeUsuarios = new javax.swing.JTable();
        TITULO1 = new javax.swing.JLabel();
        btnSalir = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1360, 768));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        eLiquor.setFont(UtilidadesFuentes.InterBold.deriveFont(140.0f));
        eLiquor.setForeground(new java.awt.Color(255, 255, 255));
        eLiquor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        eLiquor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentacion/Imagenes/Titulo.png"))); // NOI18N
        eLiquor.setPreferredSize(new java.awt.Dimension(569, 201));
        add(eLiquor, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 278, 790, 250));

        PanelListaDeUsuarios.setBackground(new java.awt.Color(255, 255, 255));
        PanelListaDeUsuarios.setPreferredSize(new java.awt.Dimension(435, 768));
        PanelListaDeUsuarios.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnSiguiente.setFont(UtilidadesFuentes.InterBlack.deriveFont(30.0f)
        );
        btnSiguiente.setForeground(Color.decode("#AEAEAE"));
        btnSiguiente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnSiguiente.setText("→");
        btnSiguiente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSiguiente.setPreferredSize(new java.awt.Dimension(70, 70));
        btnSiguiente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSiguienteMousePressed(evt);
            }
        });
        PanelListaDeUsuarios.add(btnSiguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 560, -1, -1));

        cabecera.setForeground(new java.awt.Color(208, 208, 208));
        cabecera.setPreferredSize(new java.awt.Dimension(350, 10));
        PanelListaDeUsuarios.add(cabecera, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 370, -1));

        pieDeLista.setForeground(new java.awt.Color(208, 208, 208));
        pieDeLista.setPreferredSize(new java.awt.Dimension(350, 10));
        PanelListaDeUsuarios.add(pieDeLista, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 550, 370, -1));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(370, 460));

        ListaDeUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "icono", "nombre"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ListaDeUsuarios.setAutoscrolls(false);
        ListaDeUsuarios.setFocusable(false);
        ListaDeUsuarios.setOpaque(false);
        ListaDeUsuarios.setRequestFocusEnabled(false);
        ListaDeUsuarios.setRowHeight(90);
        ListaDeUsuarios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ListaDeUsuarios.setShowGrid(false);
        ListaDeUsuarios.getTableHeader().setResizingAllowed(false);
        ListaDeUsuarios.getTableHeader().setReorderingAllowed(false);
        ListaDeUsuarios.setUpdateSelectionOnSort(false);
        jScrollPane1.setViewportView(ListaDeUsuarios);
        if (ListaDeUsuarios.getColumnModel().getColumnCount() > 0) {
            ListaDeUsuarios.getColumnModel().getColumn(0).setResizable(false);
            ListaDeUsuarios.getColumnModel().getColumn(0).setPreferredWidth(300);
            ListaDeUsuarios.getColumnModel().getColumn(1).setResizable(false);
            ListaDeUsuarios.getColumnModel().getColumn(1).setPreferredWidth(800);
        }

        PanelListaDeUsuarios.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, 410));

        TITULO1.setFont(UtilidadesFuentes.InterExtraLight.deriveFont(25.0f));
        TITULO1.setForeground(new java.awt.Color(140, 140, 140));
        TITULO1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TITULO1.setText("SELECCIONE UN USUARIO");
        TITULO1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        PanelListaDeUsuarios.add(TITULO1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 370, 90));

        add(PanelListaDeUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(132, 0, -1, -1));

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentacion/Imagenes/BotonSalirModulo.png"))); // NOI18N
        btnSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSalirMousePressed(evt);
            }
        });
        add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 674, -1, 90));
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirMousePressed
        /*Utilizando utilidades de swing para obtener la ventana principal (FramePrincipal)
        y cerrar todo el programa*/
        ((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
    }//GEN-LAST:event_btnSalirMousePressed

    private void btnSiguienteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSiguienteMousePressed
        int ordenUsuarioSeleccionado = ListaDeUsuarios.getSelectedRow();
        if(datosDeUsuarios!=null){
            if(ordenUsuarioSeleccionado!=-1){
                Login contenedorLogin = (Login)getParent();
                contenedorLogin.setIdUsuarioSeleccionado((int)datosDeUsuarios[ordenUsuarioSeleccionado][0]);
                contenedorLogin.setIndiceSeleccion(ordenUsuarioSeleccionado);
                contenedorLogin.setNombreDeUsuarioSeleccionado((String)datosDeUsuarios[ordenUsuarioSeleccionado][1]);
                CardLayout layout = (CardLayout) contenedorLogin.getLayout();
                layout.show(contenedorLogin, "loginPIN");
            }
        }
    }//GEN-LAST:event_btnSiguienteMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable ListaDeUsuarios;
    private javax.swing.JPanel PanelListaDeUsuarios;
    private javax.swing.JLabel TITULO1;
    private javax.swing.JLabel btnSalir;
    private javax.swing.JLabel btnSiguiente;
    private javax.swing.JSeparator cabecera;
    private javax.swing.JLabel eLiquor;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator pieDeLista;
    // End of variables declaration//GEN-END:variables
}

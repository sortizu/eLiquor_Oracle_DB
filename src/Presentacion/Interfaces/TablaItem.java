
package Presentacion.Interfaces;

import Presentacion.Utilidades.UtilidadesFuentes;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sortizu
 */
public class TablaItem extends JPanel{

    private JTable tabla;
    private JScrollPane scrollPaneTabla;
    private ScrollBarCustom scrollBarTabla;
    private DefaultTableModel modeloTabla;
    public JLabel lblTitulo;
    private JSeparator separadorCabecera;
    private JSeparator separadorLblTitulo;
    private Dimension dimBase;
    private Container parent;
    private int[] cursorTabla = new int[]{0,0};
    private int columnas;
    
    DefaultTableCellRenderer nuevoCellRenderer = new DefaultTableCellRenderer(){
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            if(value instanceof Item){
                Item item=(Item)value;
                return item;
            }
            
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
        }
    };
    
    public TablaItem(int columnas,int separacionAncho,int separacionAlto,Container parent) {
        scrollPaneTabla = new javax.swing.JScrollPane();
        this.columnas=columnas;
        this.parent=parent;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        lblTitulo= new JLabel(" A ");
        lblTitulo.setFont(UtilidadesFuentes.InterExtraLight.deriveFont(25.0f));
        lblTitulo.setForeground(Color.decode("#8C8C8C"));
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=0;
        gbc.weighty=0;
        add(lblTitulo,gbc);
        separadorCabecera=new JSeparator(JSeparator.HORIZONTAL);
        separadorCabecera.setForeground(Color.decode("#D0D0D0"));
        separadorCabecera.setOpaque(false);
        separadorCabecera.setBorder(new LineBorder(Color.decode("#D0D0D0"),1));
        gbc.insets=new Insets(5, 0, 0,0);
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.weightx=0;
        gbc.weighty=0;
        add(separadorCabecera,gbc);
        tabla = new javax.swing.JTable(){
            @Override
            public boolean isCellEditable(int row, int column) {                
                return false;               
            };
        };
        dimBase=new Dimension((int)PanelModulo.basePanelWidth,(int)PanelModulo.basePanelHeight);
        setOpaque(false);
        tabla.setAutoscrolls(true);
        tabla.setCellSelectionEnabled(false);
        tabla.setRowSelectionAllowed(true);
        tabla.setFocusable(false);
        tabla.setGridColor(new java.awt.Color(0, 0, 0));
        tabla.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tabla.setShowGrid(false);
        tabla.setDefaultRenderer(Object.class, nuevoCellRenderer);
        scrollPaneTabla.setViewportView(tabla);
        
        //Formato de Tabla 
        modeloTabla=(DefaultTableModel)tabla.getModel();
        tabla.getTableHeader().setUI(null);
        tabla.setSelectionBackground(Color.WHITE);
        tabla.setSelectionForeground(Color.WHITE);
        tabla.setIntercellSpacing(new Dimension(separacionAncho,separacionAlto));
        
        scrollPaneTabla.setBorder(BorderFactory.createEmptyBorder());
        scrollPaneTabla.getViewport().setOpaque(false);
        scrollPaneTabla.setOpaque(false);
        
        scrollBarTabla=new ScrollBarCustom(scrollPaneTabla);
        scrollBarTabla.setUnitIncrement(20);
        scrollPaneTabla.setVerticalScrollBar(scrollBarTabla);
        scrollPaneTabla.setHorizontalScrollBar(null);
        //Añadiendo columnas
        for(int i=0;i<columnas;i++){
            modeloTabla.addColumn("");
        }
        for(int i=0;i<columnas;i++){
            tabla.getColumnModel().getColumn(i).setResizable(false);
        }
        
        //Añadiendo tabla al panel
        gbc.gridx=0;
        gbc.gridy=2;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=1.0;
        gbc.weighty=1.0;
        add(scrollPaneTabla,gbc);
        addComponentListener(new ComponentAdapter(){
            @Override
            public void componentResized(ComponentEvent e) {
                ajustarDimensionesDeComponentes();
            }
        });
    }
    
    private void ajustarDimensionesDeComponentes(){
      /*
        tabla.setRowHeight((int)Math.ceil(altoFilaBase/dimBase.getHeight()*parent.getHeight()));
        renderDeCabecera.setBordeCabecera((int)Math.ceil(renderDeCabecera.getBordeCabeceraBase()/dimBase.getHeight()*parent.getHeight()));*/
        if(modeloTabla.getRowCount()>0){
            
        }
        tabla.setRowHeight(tabla.getColumnModel().getColumn(0).getWidth());
        revalidate();
        repaint();
    }

    public JTable getTabla() {
        return tabla;
    }

    public void setTabla(JTable tabla) {
        this.tabla = tabla;
    }

    public JScrollPane getScrollPaneTabla() {
        return scrollPaneTabla;
    }

    public void setScrollPaneTabla(JScrollPane scrollPaneTabla) {
        this.scrollPaneTabla = scrollPaneTabla;
    }

    public ScrollBarCustom getScrollBarTabla() {
        return scrollBarTabla;
    }

    public void setScrollBarTabla(ScrollBarCustom scrollBarTabla) {
        this.scrollBarTabla = scrollBarTabla;
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    public void setModeloTabla(DefaultTableModel modeloTabla) {
        this.modeloTabla = modeloTabla;
    }

    public Dimension getDimBase() {
        return dimBase;
    }

    public void setDimBase(Dimension dimBase) {
        this.dimBase = dimBase;
    }

    public JLabel getLblTitulo() {
        return lblTitulo;
    }

    public void setLblTitulo(JLabel lblTitulo) {
        this.lblTitulo = lblTitulo;
    }
    
    public void agregarItem(Item item){
        if(cursorTabla[0]==columnas){
            modeloTabla.addRow(new Object[columnas]);
            cursorTabla[0]=0;
            cursorTabla[1]++;
        }else if(modeloTabla.getRowCount()==0){
            modeloTabla.addRow(new Object[columnas]);
        }
        modeloTabla.setValueAt(item, cursorTabla[1], cursorTabla[0]++);
    }
    
    public void modificarItem(int [] indice,Item item){
        modeloTabla.setValueAt(item, indice[0], indice[1]);
    }
    
    public void limpiarTabla(){
        modeloTabla.setRowCount(0);
    }

    public int[] getCursorTabla() {
        return cursorTabla;
    }

    public void setCursorTabla(int[] cursorTabla) {
        this.cursorTabla = cursorTabla;
    }
    
    public void mostrarCabecera(String titulo){
        separadorCabecera.setVisible(true);
        lblTitulo.setText(titulo);
        lblTitulo.setVisible(true);
    }
    
    public void ocultarCabecera(){
        separadorCabecera.setVisible(false);
        lblTitulo.setVisible(false);
    }

    public int getColumnas() {
        return columnas;
    }

}

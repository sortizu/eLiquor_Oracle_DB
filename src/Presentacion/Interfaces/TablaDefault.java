
package Presentacion.Interfaces;

import Presentacion.Utilidades.UtilidadesFuentes;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sortizu
 */
public class TablaDefault extends JPanel{

    private JTable tabla;
    private JScrollPane scrollPaneTabla;
    private ScrollBarCustom scrollBarTabla;
    private DefaultTableModel modeloTabla;
    private RenderDeCabecera renderDeCabecera;
    private float dimensionFuenteCabecera=22;
    private float dimensionFuenteFila=27;
    private Dimension dimBase;
    private int altoFilaBase = 45;
    private Container parent;
    
    public TablaDefault(String[] columnas, int [] anchoColumnas,Container parent) {
        scrollPaneTabla = new javax.swing.JScrollPane();
        this.parent=parent;
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
        tabla.setRowHeight(altoFilaBase);
        tabla.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tabla.setShowGrid(false);
        scrollPaneTabla.setViewportView(tabla);
        
        //Formato de Tabla 
        renderDeCabecera = new RenderDeCabecera(tabla.getTableHeader().getDefaultRenderer());
        modeloTabla=(DefaultTableModel)tabla.getModel();
        tabla.getTableHeader().setDefaultRenderer(renderDeCabecera);
        tabla.getTableHeader().setBackground(Color.WHITE);
        
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.getTableHeader().setFont(UtilidadesFuentes.InterLight.deriveFont(dimensionFuenteCabecera));
        tabla.getTableHeader().setForeground(Color.decode("#8C8C8C"));
        tabla.setFont(UtilidadesFuentes.InterExtraLight.deriveFont(dimensionFuenteFila));
        tabla.setForeground(Color.decode("#8C8C8C"));
        tabla.setSelectionBackground(Color.decode("#23A020"));
        tabla.setSelectionForeground(Color.white);
        tabla.setIntercellSpacing(new Dimension(0,0));
        
        scrollPaneTabla.setBorder(BorderFactory.createEmptyBorder());
        scrollPaneTabla.getViewport().setOpaque(false);
        scrollPaneTabla.setOpaque(false);
        
        scrollBarTabla=new ScrollBarCustom(scrollPaneTabla);
        scrollBarTabla.setUnitIncrement(20);
        scrollPaneTabla.setVerticalScrollBar(scrollBarTabla);
        scrollPaneTabla.setHorizontalScrollBar(null);
        
        //Añadiendo columnas
        for(int i=0;i<columnas.length;i++){
            modeloTabla.addColumn(columnas[i]);
        }
        for(int i=0;i<columnas.length;i++){
            tabla.getColumnModel().getColumn(i).setResizable(false);
            tabla.getColumnModel().getColumn(i).setPreferredWidth(anchoColumnas[i]);
        }
        //Añadiendo tabla al panel
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx=0;
        gbc.gridy=0;
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
        tabla.setFont(UtilidadesFuentes.InterExtraLight.deriveFont((float)Math.ceil(dimensionFuenteFila/dimBase.getWidth()*parent.getWidth())));
      
        tabla.getTableHeader().setFont(UtilidadesFuentes.InterExtraLight.deriveFont((float)Math.ceil(dimensionFuenteCabecera/dimBase.getWidth()*parent.getWidth())));
        tabla.setRowHeight((int)Math.ceil(altoFilaBase/dimBase.getHeight()*parent.getHeight()));
        renderDeCabecera.setBordeCabecera((int)Math.ceil(renderDeCabecera.getBordeCabeceraBase()/dimBase.getHeight()*parent.getHeight()));
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

    public int getAltoFilaBase() {
        return altoFilaBase;
    }

    public void setAltoFilaBase(int altoFilaBase) {
        this.altoFilaBase = altoFilaBase;
        //ajustarDimensionesDeComponentes();
    }

    public Dimension getDimBase() {
        return dimBase;
    }

    public void setDimBase(Dimension dimBase) {
        this.dimBase = dimBase;
    }
    
    
}

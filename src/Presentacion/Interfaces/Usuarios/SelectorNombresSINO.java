package Presentacion.Interfaces.Usuarios;

import Presentacion.Interfaces.Selector;
import Presentacion.Utilidades.UtilidadesFuentes;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author sortizu
 */
public class SelectorNombresSINO extends JPanel{
    private Selector selector;
    private JLabel labelTituloSelector;
        
        public SelectorNombresSINO(String Titulo, Color fgColor, Font fuente, String nombreSelector){
            setOpaque(false);
             selector = new Selector(new String[]{"SI","NO"},50,37);
            ((Selector)selector).setFuenteDeOpcion(UtilidadesFuentes.InterRegular.deriveFont(15.0f));

            ((Selector)selector).setColorDeFuente(Color.decode("#8C8C8C"));

            ((Selector)selector).solicitarSeleccion(0);

            ((Selector)selector).addColorDeOpcion(Color.decode("#72AD57"));

            ((Selector)selector).addColorDeOpcion(Color.decode("#AD5757"));

            ((Selector)selector).setNombreDeSelector(nombreSelector);
            
            labelTituloSelector=new JLabel(Titulo);
            labelTituloSelector.setFont(fuente);
            labelTituloSelector.setForeground(fgColor);

            
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx=0;
            gbc.gridy=0;
            add(labelTituloSelector,gbc);
            gbc.gridx=0;
            gbc.gridy=1;
            add(selector,gbc);
        
        }

        public Selector getSelector() {
            return selector;
        }

        public void setSelector(Selector selector) {
            this.selector = selector;
        }

        public JLabel getLabelTituloSelector() {
            return labelTituloSelector;
        }

        public void setLabelTituloSelector(JLabel labelTituloSelector) {
            this.labelTituloSelector = labelTituloSelector;
        }
        
        public boolean getValorDeSelector(){
            return selector.getOpcionSeleccionada() == 0? true:false;
        }
        
}

package Presentacion.Interfaces;

import Presentacion.Utilidades.UtilidadesFuentes;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;

/**
 *
 * @author sortizu
 */
public class ToolTipIcon extends PanelRedondeado{
    int escalaPX=28;
    int escalaPXBase=28;
    JLabel simboloToolTip;
    String mensajeToolTip;
    
    public ToolTipIcon(int escalaPX, String mensajeToolTip) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        setOpaque(false);
        
        this.escalaPX=escalaPX;
        this.mensajeToolTip=mensajeToolTip;
        setPreferredSize(new Dimension(escalaPX,escalaPX));
        setColorBorde(Color.decode("#8C8C8C"));
        setRadioDeEsquina(escalaPX);
        
        int nuevoGrosor=(int)Math.ceil((2.0/(double)escalaPXBase)*(double)escalaPX);
        setGrosorDeBorde(nuevoGrosor);
        simboloToolTip=new JLabel("i");
        simboloToolTip.setFont(UtilidadesFuentes.InterBold.deriveFont((float)Math.ceil((15.0/(double)escalaPXBase)*(double)escalaPX)));
        simboloToolTip.setForeground(Color.decode("#8C8C8C"));
        simboloToolTip.setHorizontalAlignment(JLabel.CENTER);
        simboloToolTip.setToolTipText(mensajeToolTip);
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=1.0;
        gbc.weighty=1.0;
        add(simboloToolTip,gbc);
    }

    public int getEscalaPX() {
        return escalaPX;
    }

    public void setEscalaPX(int escalaPX) {
        this.escalaPX = escalaPX;
        int nuevoGrosor=(int)Math.ceil((2.0/(double)escalaPXBase)*(double)escalaPX);
        setGrosorDeBorde(nuevoGrosor);
        simboloToolTip.setFont(UtilidadesFuentes.InterBold.deriveFont((float)Math.ceil((15.0/(double)escalaPXBase)*(double)escalaPX)));
        setPreferredSize(new Dimension(escalaPX,escalaPX));
    }
    
    
}

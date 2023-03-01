package Presentacion.Utilidades;

import java.awt.Font;
import java.io.InputStream;

/**
 *
 * @author sortizu
 */
public final class UtilidadesFuentes {
    public static final Font InterExtraLight;
    public static final Font InterLight;
    public static final Font InterBlack;
    public static final Font InterBold;
    public static final Font InterRegular;
    static{
        InterExtraLight = cargarFuente("/Presentacion/Fuentes/Inter-ExtraLight.ttf",12);
        InterLight = cargarFuente("/Presentacion/Fuentes/Inter-Light.ttf",12);
        InterBlack = cargarFuente("/Presentacion/Fuentes/Inter-Black.ttf",12);
        InterBold = cargarFuente("/Presentacion/Fuentes/Inter-Bold.ttf",12);
        InterRegular = cargarFuente("/Presentacion/Fuentes/Inter-Regular.ttf",12);
    }
    private static Font cargarFuente(String nombreDeFuente, int tamanio){
        Font nuevaFuente;
        try {
            InputStream rs = UtilidadesFuentes.class.getResourceAsStream(nombreDeFuente);
            nuevaFuente=Font.createFont(Font.TRUETYPE_FONT, rs);
        } catch (Exception e) {
            System.err.println("No se pudo cargar la fuente");
            nuevaFuente = new Font("Arial",Font.PLAIN,14);
        }
        return nuevaFuente;
    }
}

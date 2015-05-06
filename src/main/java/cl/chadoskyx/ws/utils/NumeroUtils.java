package cl.chadoskyx.ws.utils;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Sebastián Salazar Molina <sebasalazar@gmail.com>
 */
public class NumeroUtils implements Serializable {

    private static final Locale localeChileno = new Locale("es", "CL");
    private static final Logger logger = LoggerFactory.getLogger(NumeroUtils.class);

    public static Double crearNumero(String texto) {
        Double numero = null;
        try {
            numero = (Double) NumberFormat.getInstance(localeChileno).parse(texto);
        } catch (Exception e) {
            numero = null;
            logger.error("Error al convertir numero: {}", e.toString());
        }
        return numero;
    }
}

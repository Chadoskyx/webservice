package cl.chadoskyx.utils;

import au.com.bytecode.opencsv.CSVReader;
import java.io.FileReader;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Valery Soto Lastra <valery.soto17@gmail.com>
 */
public class CsvUtils implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(CsvUtils.class);

    private CsvUtils() {
        throw new AssertionError();
    }

    public static Map<Calendar, Double> leerUf(String ruta) {
        Map<Calendar, Double> mapa = new HashMap<Calendar, Double>();
        try {
            // Si existe la ruta
            if (StringUtils.isNotBlank(ruta)) {
                CSVReader lector = new CSVReader(new FileReader(ruta), ';');
                String[] linea;
                while ((linea = lector.readNext()) != null) {
                    Calendar fecha = FechaUtils.convertirCalendario(linea[0]);
                    Double uf = NumeroUtils.crearNumero(linea[1]);
                    
                    if (fecha != null && uf != null) {
                        mapa.put(fecha, uf);
                    }
                }
                lector.close();
            }
        } catch (Exception e) {
            mapa = new HashMap<Calendar, Double>();
            logger.error("Error al cargar mapa de UFs: {}", e.toString());
        }
        return mapa;
    }
}

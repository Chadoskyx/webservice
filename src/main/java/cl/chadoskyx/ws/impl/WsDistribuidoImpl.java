package cl.chadoskyx.ws.impl;

import cl.chadoskyx.ws.WsDistribuido;
import cl.chadoskyx.ws.utils.CsvUtils;
import cl.chadoskyx.ws.utils.FechaUtils;
import cl.chadoskyx.ws.utils.NumeroaLetra;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import javax.jws.WebService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Sebastián Salazar Molina <sebasalazar@gmail.com>
 */
@WebService(endpointInterface = "cl.chadoskyx.ws.WsDistribuido")
public class WsDistribuidoImpl implements WsDistribuido, Serializable {

    private final static Logger logger = LoggerFactory.getLogger(WsDistribuidoImpl.class);

    @Override
    public String obtenerTexto(Integer numero) {
        String texto = StringUtils.EMPTY;
        try {
            if (numero != null) {
                texto = NumeroaLetra.convierteNumeroaLetra(numero);
            }
        } catch (Exception e) {
            texto = StringUtils.EMPTY;
            logger.error("No pude convertir el numero: {}", e.toString());
            logger.debug("No pude convertir el numero: {}", e.toString(), e);
        }
        return texto;
    }

    @Override
    public Double consultarUF(int dia, int mes, int anio) {
        Double uf = null;
        try {
            Date fecha = FechaUtils.crearFecha(dia, mes, anio);
            Map<Date, Double> mapa = CsvUtils.leerUf("/tmp/salida.csv");
            for ( Map.Entry<Date, Double> entrada : mapa.entrySet()) {
                if (DateUtils.isSameDay(fecha, entrada.getKey())) {
                    uf = entrada.getValue();
                    break;
                }
            }

        } catch (Exception e) {
            logger.error("No pude convertir el numero: {}", e.toString());
            logger.debug("No pude convertir el numero: {}", e.toString(), e);
        }
        return uf;
    }

}

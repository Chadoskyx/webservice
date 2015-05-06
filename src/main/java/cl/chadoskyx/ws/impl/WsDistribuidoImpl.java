package cl.chadoskyx.ws.impl;

import cl.chadoskyx.ws.WsDistribuido;
import cl.chadoskyx.utils.CsvUtils;
import cl.chadoskyx.utils.FechaUtils;
import cl.chadoskyx.utils.NumeroaLetraUtils;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import javax.jws.WebService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Valery Soto Lastra <valery.soto17@gmail.com>
 */
@WebService(endpointInterface = "cl.chadoskyx.ws.WsDistribuido")
public class WsDistribuidoImpl implements WsDistribuido, Serializable {

    private final static Logger logger = LoggerFactory.getLogger(WsDistribuidoImpl.class);

    @Override
    public String obtenerTexto(Integer numero) {
        String texto = StringUtils.EMPTY;
        try {
            if (numero != null) {
                texto = NumeroaLetraUtils.convierteNumeroaLetra(numero);
                // Si el texto no es vacío
                if (StringUtils.isNotBlank(texto)) {
                    // Quito la palabra PESOS con StringUtils.remove
                    texto = StringUtils.trimToEmpty(StringUtils.remove(texto, "PESOS"));
                }
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
            if (FechaUtils.esFechaValida(dia, mes, anio)) {
                Date fecha = FechaUtils.crearFecha(dia, mes, anio);
                String llave = FechaUtils.obtenerFechaISOstr(fecha);
                if (StringUtils.isNoneBlank(llave)) {
                    Map<String, Double> mapa = CsvUtils.leerUf("/var/tmp/ufs.csv");
                    uf = mapa.get(llave);
                }
            }
        } catch (Exception e) {
            logger.error("No pude convertir el numero: {}", e.toString());
            logger.debug("No pude convertir el numero: {}", e.toString(), e);
        }
        return uf;
    }

}

package cl.chadoskyx.ws;

import java.util.Date;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Sebastián Salazar Molina <sebasalazar@gmail.com>
 */
@WebService
public interface WsDistribuido {

    public String obtenerTexto(@WebParam(name = "numero") Integer numero);
    
    public Double consultarUF(@WebParam(name="dia") int dia,@WebParam(name="mes") int mes, @WebParam(name="anio") int anio);
}

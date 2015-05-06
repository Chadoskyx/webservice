package cl.chadoskyx.ws;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Valery Soto Lastra <valery.soto17@gmail.com>
 */
@WebService
public interface WsDistribuido {

    public String obtenerTexto(@WebParam(name = "numero") Integer numero);
    
    public Double consultarUF(@WebParam(name="dia") int dia,@WebParam(name="mes") int mes, @WebParam(name="anio") int anio);
}

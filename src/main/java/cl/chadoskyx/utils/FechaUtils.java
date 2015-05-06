package cl.chadoskyx.utils;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Valery Soto Lastra <valery.soto17@gmail.com>
 */
public class FechaUtils implements Serializable {

    // Esta variable nos permite usar las características regionales de nuestro país (para formato de fechas, números y moneda)
    private static final Locale localeChileno = new Locale("es", "CL");
    // Definimos una clase que nos permitirá almacenar información en archivos de texto (logs)
    private static final Logger logger = LoggerFactory.getLogger(FechaUtils.class);

    /**
     * Constructor. Las clases utilitarias no se pueden instanciar. Si alguien
     * lo intenta, le enviaremos una excepción.
     */
    private FechaUtils() {
        throw new AssertionError();
    }

    /**
     * Crea un objeto Date a partir de su año, mes y día.
     *
     * @param dia Día del mes
     * @param mes Mes del año
     * @param anio año
     * @return un objeto fecha o null en cualquier otro caso
     */
    public static Date crearFecha(int dia, int mes, int anio) {
        Date fecha = null;
        try {
            // creamos un objeto calendario (utiliza la fecha actual)
            Calendar calendario = new GregorianCalendar();
            // seteamos el día definido por el usuario
            calendario.set(Calendar.DAY_OF_MONTH, dia);
            // definimos el mes del usuario, los meses van de 0 a 11, por este motivo restamos 1
            calendario.set(Calendar.MONTH, mes - 1);
            // seteamos el año
            calendario.set(Calendar.YEAR, anio);

            // Seteamos las 0 horas, para que el objeto parte a primera hora del día
            calendario.set(Calendar.HOUR, 0);
            calendario.set(Calendar.MINUTE, 0);
            calendario.set(Calendar.SECOND, 0);
            calendario.set(Calendar.MILLISECOND, 0);

            // transforma el calendario a un objeto Date
            fecha = calendario.getTime();
        } catch (Exception e) {
            // En caso de error seteo el objeto a null y logueo la excepción
            fecha = null;
            logger.error("Error al crear fecha: {}", e.toString());
        }
        return fecha;
    }

    /**
     * Crea un objeto Calendar a partir de su año, mes y día.
     *
     * @param dia Día del mes
     * @param mes Mes del año
     * @param anio año
     * @return un objeto calendar o null en cualquier otro caso
     */
    public static Calendar crearCalendario(int dia, int mes, int anio) {
        // creamos un objeto calendario (utiliza la fecha actual)
        Calendar calendario = new GregorianCalendar();
        try {
            // seteamos el día definido por el usuario
            calendario.set(Calendar.DAY_OF_MONTH, dia);
            // definimos el mes del usuario, los meses van de 0 a 11, por este motivo restamos 1
            calendario.set(Calendar.MONTH, mes - 1);
            // seteamos el año
            calendario.set(Calendar.YEAR, anio);

            // Seteamos las 0 horas, para que el objeto parte a primera hora del día
            calendario.set(Calendar.HOUR, 0);
            calendario.set(Calendar.MINUTE, 0);
            calendario.set(Calendar.SECOND, 0);
            calendario.set(Calendar.MILLISECOND, 0);
        } catch (Exception e) {
            // En caso de error seteo el objeto a null y logueo la excepción
            calendario = null;
            logger.error("Error al crear fecha: {}", e.toString());
        }
        return calendario;
    }

    /**
     * Obtiene la fecha como string en formato completo, desde un objeto Date
     *
     * @param fecha Fecha
     * @return la fecha bien formateada o vacío en cualquier otro caso
     */
    public static String fechaEscrita(Date fecha) {
        String texto = StringUtils.EMPTY;
        try {
            // verificamos que el objeto no sea nulo.
            if (fecha != null) {
                // obtenemos el formarto que queremos y las caracteristicas de localización
                DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, localeChileno);
                // formateamos y Capitalizamos el resultado
                texto = WordUtils.capitalize(StringUtils.lowerCase(df.format(fecha)));
            }
        } catch (Exception e) {
            // En caso de cualquier error, enviamos un string vacío y logueamos el error
            texto = StringUtils.EMPTY;
            logger.error("Error al obtener fecha escrita: {}", e.toString());
        }
        return texto;
    }

    public static Date convertirFecha(String texto) {
        Date fecha = null;
        try {
            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy", localeChileno);
            Date tiempo = formateador.parse(texto);

            Calendar calendario = new GregorianCalendar();
            // seteamos el día definido por el usuario
            calendario.setTime(tiempo);

            // Seteamos las 0 horas, para que el objeto parte a primera hora del día
            calendario.set(Calendar.HOUR, 0);
            calendario.set(Calendar.MINUTE, 0);
            calendario.set(Calendar.SECOND, 0);
            calendario.set(Calendar.MILLISECOND, 0);

            // transforma el calendario a un objeto Date
            fecha = calendario.getTime();
        } catch (Exception e) {
            fecha = null;
            logger.error("Error al convertir Fecha: {}", e.toString());
        }
        return fecha;
    }

    public static Calendar convertirCalendario(String texto) {
        Calendar calendario = new GregorianCalendar();
        try {
            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy", localeChileno);
            Date tiempo = formateador.parse(texto);

            // seteamos el día definido por el usuario
            calendario.setTime(tiempo);

            // Seteamos las 0 horas, para que el objeto parte a primera hora del día
            calendario.set(Calendar.HOUR, 0);
            calendario.set(Calendar.MINUTE, 0);
            calendario.set(Calendar.SECOND, 0);
            calendario.set(Calendar.MILLISECOND, 0);

        } catch (Exception e) {
            calendario = null;
            logger.error("Error al convertir Calendario: {}", e.toString());
        }
        return calendario;
    }

    /**
     *
     * @param fecha
     * @return Retorna la fecha actual como String en formato yyyy-MM-dd (Ej:
     * 1985-03-13)
     */
    public static String obtenerFechaISOstr(Date fecha) {
        String fechaStr = StringUtils.EMPTY;
        try {
            if (fecha != null) {
                String patron = "yyyy-MM-dd";
                SimpleDateFormat sdf = new SimpleDateFormat(patron);
                fechaStr = sdf.format(fecha);
            }
        } catch (Exception e) {
            fechaStr = StringUtils.EMPTY;
            logger.error("Error al obtener fecha como string: {}", e.toString());
        }
        return fechaStr;
    }

    public static boolean esFechaValida(int dia, int mes, int anio) {
        boolean ok = false;
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            df.setLenient(false); // Desactivamos el arreglo de fechas
            df.parse(String.format("%d/%d/%d", dia, mes, anio));
            ok = true;
        } catch (Exception e) {
            ok = false;
            logger.error("Fecha inválida: {}", e.toString());
        }
        return ok;
    }
}

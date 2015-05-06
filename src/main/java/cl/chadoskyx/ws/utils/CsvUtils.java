/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.chadoskyx.ws.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.math.NumberUtils;

/**
 *
 * @author Sebastián Salazar Molina <sebasalazar@gmail.com>
 */
public class CsvUtils implements Serializable {

    private CsvUtils() {
        throw new AssertionError();
    }

    public static Map<Date, Double> leerUf(String ruta) {
        Map<Date, Double> mapa = new HashMap<Date, Double>();
        //String csvFile = c;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";

        try {

            br = new BufferedReader(new FileReader(ruta));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] arreglo = line.split(cvsSplitBy);

                Date fecha = FechaUtils.convertirFecha(arreglo[0]);
                Double uf = NumeroUtils.crearNumero(arreglo[1]);
                if (fecha != null && uf != null) {
                    mapa.put(fecha, uf);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Done");
        return mapa;
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.excepciones;

import ec.edu.ups.biblioteca.utils.Idioma;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.ResourceBundle;

/**
 *
 * @author jimen
 */
public class Validador {

    public static void validarNoVacio(String valor, String nombreCampo) throws CampoObligatorioException {
        if (valor == null || valor.trim().isEmpty()) {
            ResourceBundle bundle = Idioma.getBundle();
            throw new CampoObligatorioException(nombreCampo + " " + bundle.getString("error.campoObligatorio"));
        }
    }

    public static void validarLongitud(String valor, int minimo, int maximo, String nombreCampo) throws LongitudInvalidaException {
        if (valor.length() < minimo || valor.length() > maximo) {
            ResourceBundle bundle = Idioma.getBundle();
            throw new LongitudInvalidaException(nombreCampo + " " + bundle.getString("error.longitudInvalida")
                    + " (" + minimo + "-" + maximo + ")");
        }
    }

    public static int validarEntero(String texto, String nombreCampo) throws ValorNumericoInvalidoException {
        try {
            return Integer.parseInt(texto.trim());
        } catch (NumberFormatException e) {
            ResourceBundle bundle = Idioma.getBundle();
            throw new ValorNumericoInvalidoException(nombreCampo + " " + bundle.getString("error.numeroInvalido"));
        }
    }

    public static LocalDate validarFecha(String texto, String nombreCampo) throws FechaInvalidaException {
        try {
            return LocalDate.parse(texto.trim());
        } catch (DateTimeParseException e) {
            ResourceBundle bundle = Idioma.getBundle();
            throw new FechaInvalidaException(nombreCampo + " " + bundle.getString("error.fechaInvalida"));
        }
    }
    public static Date validarFecha(String texto, String patron, String nombreCampo) throws FechaInvalidaException {
        try {
            SimpleDateFormat formato = new SimpleDateFormat(patron);
            formato.setLenient(false);
            return formato.parse(texto.trim());
        } catch (ParseException e) {
            ResourceBundle bundle = Idioma.getBundle();
            throw new FechaInvalidaException(nombreCampo + " " + bundle.getString("error.fechaInvalida"));
        }  
    }
}

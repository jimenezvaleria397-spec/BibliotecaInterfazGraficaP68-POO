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

    public static void validarSoloNumeros(String texto, String nombreCampo) throws FormatoInvalidoException {
        if (texto == null || !texto.trim().matches("\\d+")) {
            ResourceBundle bundle = Idioma.getBundle();
            throw new FormatoInvalidoException(nombreCampo + " " + bundle.getString("error.soloNumeros"));
        }
    }

    public static void validarCorreo(String correo, String nombreCampo) throws FormatoInvalidoException {
        if (correo == null || !correo.trim().matches("^[\\w.-]+@[\\w.-]+\\.com$")) {
            ResourceBundle bundle = Idioma.getBundle();
            throw new FormatoInvalidoException(nombreCampo + " " + bundle.getString("error.correoInvalido"));
        }
    }
    public static void validarCedula(String cedula)throws CedulaInvalidaException{
        if(cedula == null|| cedula.length() !=10){
            throw new CedulaInvalidaException("La cedula debe contener exactamente 10 numeros");
        }
    }
}

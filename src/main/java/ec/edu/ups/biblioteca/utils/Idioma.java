/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.utils;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author jimen
 */
public class Idioma {
    private static Locale localeActual = new Locale("es");

    public static void setIdioma(String codigo) {
        localeActual = new Locale(codigo);
    }

    public static ResourceBundle getBundle() {
        return ResourceBundle.getBundle("ec.edu.ups.biblioteca.i18n.mensajes", localeActual);
    }
}

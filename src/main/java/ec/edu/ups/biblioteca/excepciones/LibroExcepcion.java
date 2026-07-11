/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.excepciones;

/**
 *
 * @author Lenovo
 */
public class LibroExcepcion {
    public static int parsearEntero(String texto, String nombreCampo) {
        try {
            return Integer.parseInt(texto);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(nombreCampo + " debe ser un número válido.");
        }
    }

    public static void validarNoVacio(String texto, String nombreCampo) {
        if (texto == null || texto.isEmpty()) {
            throw new IllegalArgumentException(nombreCampo + " es obligatorio.");
        }
    }
    
}

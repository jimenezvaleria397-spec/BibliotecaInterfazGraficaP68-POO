/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.dao.archivos;

import ec.edu.ups.biblioteca.dao.DAO;
import ec.edu.ups.biblioteca.models.EjemplarLibro;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class EjemplarLibroDAOArchivo implements DAO<EjemplarLibro> {
    
    private final String RUTA = "c:/carpeta1/ejemplaresRandomico.ups";
    private final int TAMANO_REGISTRO = 128; // Tamaño calculado: 4 Strings = 128 bytes

    @Override
    public void agregar(EjemplarLibro objeto) {
        try (RandomAccessFile archivo = new RandomAccessFile(RUTA, "rw")) {
            archivo.seek(archivo.length());
            
            archivo.writeUTF(completarEspacios(objeto.getCodigoBarras(), 15));
            archivo.writeUTF(completarEspacios(objeto.getUbicacion(), 20));
            archivo.writeUTF(completarEspacios("", 10)); // Espacio reservado para libro
            archivo.writeUTF(completarEspacios("", 15)); // Espacio reservado para estadoEjemplar
            
        } catch (IOException e) {
            System.out.println("Error de escritura en ejemplar");
        }
    }

    @Override
    public EjemplarLibro buscarPorCodigo(String codigo) {
        try (RandomAccessFile archivo = new RandomAccessFile(RUTA, "r")) {
            long totalRegistros = archivo.length() / TAMANO_REGISTRO;
            
            for (int i = 0; i < totalRegistros; i++) {
                archivo.seek(i * TAMANO_REGISTRO);
                
                String codBarras = archivo.readUTF().trim();
                String ubicacion = archivo.readUTF().trim();
                String libroPlaceholder = archivo.readUTF().trim();
                String estadoPlaceholder = archivo.readUTF().trim();
                
                if (codBarras.equals(codigo.trim())) {
                    // Usamos tu constructor pasando null en libro y estadoEjemplar
                    return new EjemplarLibro(codBarras, ubicacion, null, null);
                }
            }
        } catch (IOException e) {
            System.out.println("Error de lectura en ejemplar");
        }
        return null;
    }

    @Override
    public void actualizar(String codigo) {
        try (RandomAccessFile archivo = new RandomAccessFile(RUTA, "rw")) {
            long totalRegistros = archivo.length() / TAMANO_REGISTRO;
            
            // Objeto temporal respetando tu constructor para que no dé error de compilación
            EjemplarLibro nuevoObjeto = new EjemplarLibro(codigo, "Ubicacion Temp", null, null);

            for (int i = 0; i < totalRegistros; i++) {
                archivo.seek(i * TAMANO_REGISTRO);
                String codBarras = archivo.readUTF().trim();
                
                if (codBarras.equals(codigo.trim())) {
                    archivo.seek(i * TAMANO_REGISTRO); // Regresar al inicio del registro
                    
                    archivo.writeUTF(completarEspacios(nuevoObjeto.getCodigoBarras(), 15));
                    archivo.writeUTF(completarEspacios(nuevoObjeto.getUbicacion(), 20));
                    archivo.writeUTF(completarEspacios("", 10)); 
                    archivo.writeUTF(completarEspacios("", 15)); 
                    return;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al actualizar ejemplar");
        }
    }

    @Override
    public void eliminar(String codigo) {
        try (RandomAccessFile archivo = new RandomAccessFile(RUTA, "rw")) {
            long totalRegistros = archivo.length() / TAMANO_REGISTRO;
            
            for (int i = 0; i < totalRegistros; i++) {
                archivo.seek(i * TAMANO_REGISTRO);
                String codBarras = archivo.readUTF().trim();
                
                if (codBarras.equals(codigo.trim())) {
                    archivo.seek(i * TAMANO_REGISTRO);
                    archivo.writeUTF(completarEspacios("", 15)); // Borrado lógico (vaciamos el código)
                    return;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al eliminar ejemplar");
        }
    }

    @Override
    public List<EjemplarLibro> listar() {
        List<EjemplarLibro> lista = new ArrayList<>();
        try (RandomAccessFile archivo = new RandomAccessFile(RUTA, "r")) {
            long totalRegistros = archivo.length() / TAMANO_REGISTRO;
            
            for (int i = 0; i < totalRegistros; i++) {
                archivo.seek(i * TAMANO_REGISTRO);
                
                String codBarras = archivo.readUTF().trim();
                String ubicacion = archivo.readUTF().trim();
                String libroPlaceholder = archivo.readUTF().trim();
                String estadoPlaceholder = archivo.readUTF().trim();
                
                if (!codBarras.isEmpty()) {
                    lista.add(new EjemplarLibro(codBarras, ubicacion, null, null));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al listar ejemplares");
        }
        return lista;
    }

    private String completarEspacios(String texto, int longitudMax) {
        if (texto.length() > longitudMax) {
            return texto.substring(0, longitudMax);
        }
        return String.format("%-" + longitudMax + "s", texto);
    }
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.dao.archivos;

import ec.edu.ups.biblioteca.dao.DAO;
import ec.edu.ups.biblioteca.enumeraciones.CategoriaLibro;
import ec.edu.ups.biblioteca.models.Autor;
import ec.edu.ups.biblioteca.models.Libro;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class LibroDAOArchivo implements DAO<Libro>{

    private final String RUTA = "c:/carpeta1/usuariosRandomico.dat";
    private final int TAMANO_REGISTRO = 184; 

    @Override
    public void agregar(Libro objeto) {
        try (RandomAccessFile archivo = new RandomAccessFile(RUTA, "rw")) {
            archivo.seek(archivo.length());
            
            archivo.writeUTF(completarEspacios(objeto.getCodigo(), 10));
            archivo.writeUTF(completarEspacios(objeto.getTitulo(), 30));
            // Guardamos el código/cédula del autor vinculado
            archivo.writeUTF(completarEspacios(objeto.getAutor().getCodigoAutor(), 10)); 
            archivo.writeUTF(completarEspacios(objeto.getEditorial(), 20));
            // Guardamos el nombre o string del enum de la categoría
            archivo.writeUTF(completarEspacios(objeto.getCategoria().toString(), 15));
            archivo.writeInt(objeto.getAnio());
            
        } catch (IOException e) {
            System.out.println("Error al escribir el libro");
        }
    }

    @Override
    public Libro buscarPorCodigo(String codigo) {
         try (RandomAccessFile archivo = new RandomAccessFile(RUTA, "r")) {
            long totalRegistros = archivo.length() / TAMANO_REGISTRO;
            
            for (int i = 0; i < totalRegistros; i++) {
                archivo.seek(i * TAMANO_REGISTRO);
                
                String cod = archivo.readUTF().trim();
                String titulo = archivo.readUTF().trim();
                String autorPlaceholder = archivo.readUTF().trim(); 
                String editorial = archivo.readUTF().trim();
                String categoriaPlaceholder = archivo.readUTF().trim();
                int anio = archivo.readInt();
                
                if (cod.equals(codigo.trim())) {
                    // Usamos tu constructor exacto pasando null en los objetos complejos
                    return new Libro(cod, titulo, null, editorial, null, anio);
                }
            }
        } catch (IOException e) {
            System.out.println("Error de lectura");
        }
        return null;
    
    }

    @Override
    public void actualizar(String codigo) {
        try (RandomAccessFile archivo = new RandomAccessFile(RUTA, "rw")) {
            long totalRegistros = archivo.length() / TAMANO_REGISTRO;

            Libro nuevoObjeto = new Libro(codigo, "Titulo Temporal", null, "Editorial Temp", null, 2026);

            for (int i = 0; i < totalRegistros; i++) {
                archivo.seek(i * TAMANO_REGISTRO);
                String cod = archivo.readUTF().trim();

                if (cod.equals(codigo.trim())) {
                    archivo.seek(i * TAMANO_REGISTRO);

                    // Estas líneas se mantendrán idénticas y ya no darán error:
                    archivo.writeUTF(completarEspacios(nuevoObjeto.getCodigo(), 10));
                    archivo.writeUTF(completarEspacios(nuevoObjeto.getTitulo(), 30));
                    archivo.writeUTF(completarEspacios("", 10)); // placeholder autor
                    archivo.writeUTF(completarEspacios(nuevoObjeto.getEditorial(), 20));
                    archivo.writeUTF(completarEspacios("", 15)); // placeholder categoria
                    archivo.writeInt(nuevoObjeto.getAnio());
                    return;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al actualizar libro");
        }
    }    

    @Override
    public void eliminar(String codigo) {
        try (RandomAccessFile archivo = new RandomAccessFile(RUTA, "rw")) {
            long totalRegistros = archivo.length() / TAMANO_REGISTRO;
            
            for (int i = 0; i < totalRegistros; i++) {
                archivo.seek(i * TAMANO_REGISTRO);
                String codigoAutor = archivo.readUTF().trim();
                
                if (codigo.equals(codigo.trim())) {
                    archivo.seek(i * TAMANO_REGISTRO);
                    archivo.writeUTF(completarEspacios("", 10)); // Borrado lógico (vaciar código)
                    return;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al eliminar");
        }
    }

    @Override
    public List<Libro> listar() {
        List<Libro> lista = new ArrayList<>();
        try (RandomAccessFile archivo = new RandomAccessFile(RUTA, "r")) {
            long totalRegistros = archivo.length() / TAMANO_REGISTRO;
            
            for (int i = 0; i < totalRegistros; i++) {
                archivo.seek(i * TAMANO_REGISTRO);
                
                String cod = archivo.readUTF().trim();
                String titulo = archivo.readUTF().trim();
                String autorPlaceholder = archivo.readUTF().trim();
                String editorial = archivo.readUTF().trim();
                String categoriaPlaceholder = archivo.readUTF().trim();
                int anio = archivo.readInt();
                
                if (!cod.isEmpty()) {
                    // Usamos tu constructor exacto sin lógicas avanzadas
                    lista.add(new Libro(cod, titulo, null, editorial, null, anio));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al listar");
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
    


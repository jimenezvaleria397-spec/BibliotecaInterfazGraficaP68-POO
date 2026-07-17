/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.dao.archivos;

import ec.edu.ups.biblioteca.dao.DAO;
import ec.edu.ups.biblioteca.models.Prestamo;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class PrestamoDAOArchivo implements DAO<Prestamo>{

    private final String RUTA = "c:/carpeta1/prestamosRandomico.ups";
    private final int TAMANO_REGISTRO = 142; // Tamaño calculado: 6 Strings = 142 bytes

    @Override
    public void agregar(Prestamo objeto) {
        try (RandomAccessFile archivo = new RandomAccessFile(RUTA, "rw")) {
            archivo.seek(archivo.length());
 
            archivo.writeUTF(completarEspacios(objeto.getCodigo(), 10));
            archivo.writeUTF(completarEspacios("", 10)); // usuario
            archivo.writeUTF(completarEspacios("", 10)); // ejemplar
            archivo.writeUTF(completarEspacios("", 10)); // fechaPrestamo
            archivo.writeUTF(completarEspacios("", 10)); // fechaDevolucion
            archivo.writeUTF(completarEspacios("", 15)); // estado
            
        } catch (IOException e) {
            System.out.println("Error de escritura en prestamo");
        }
    }

    @Override
    public Prestamo buscarPorCodigo(String codigo) {
        try (RandomAccessFile archivo = new RandomAccessFile(RUTA, "r")) {
            long totalRegistros = archivo.length() / TAMANO_REGISTRO;
            
            for (int i = 0; i < totalRegistros; i++) {
                archivo.seek(i * TAMANO_REGISTRO);
                
                String cod = archivo.readUTF().trim();
                String usuarioPlaceholder = archivo.readUTF().trim();
                String ejemplarPlaceholder = archivo.readUTF().trim();
                String fechaPPlaceholder = archivo.readUTF().trim();
                String fechaDPlaceholder = archivo.readUTF().trim();
                String estadoPlaceholder = archivo.readUTF().trim();
                
                if (cod.equals(codigo.trim())) {
                    // Retornamos el objeto Prestamo usando null para los objetos y fechas complejas
                    return new Prestamo(cod, null, null, null, null, null);
                }
            }
        } catch (IOException e) {
            System.out.println("Error de lectura en prestamo");
        }
        return null;
    }

    @Override
    public void actualizar(String codigo) {
        try (RandomAccessFile archivo = new RandomAccessFile(RUTA, "rw")) {
            long totalRegistros = archivo.length() / TAMANO_REGISTRO;
           
            Prestamo nuevoObjeto = new Prestamo(codigo, null, null, null, null, null);

            for (int i = 0; i < totalRegistros; i++) {
                archivo.seek(i * TAMANO_REGISTRO);
                String cod = archivo.readUTF().trim();
                
                if (cod.equals(codigo.trim())) {
                    archivo.seek(i * TAMANO_REGISTRO); // Regresar al inicio del registro
                    
                    archivo.writeUTF(completarEspacios(nuevoObjeto.getCodigo(), 10));
                    archivo.writeUTF(completarEspacios("", 10)); 
                    archivo.writeUTF(completarEspacios("", 10)); 
                    archivo.writeUTF(completarEspacios("", 10)); 
                    archivo.writeUTF(completarEspacios("", 10)); 
                    archivo.writeUTF(completarEspacios("", 15)); 
                    return;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al actualizar prestamo");
        }
    }

    @Override
    public void eliminar(String codigo) {
        try (RandomAccessFile archivo = new RandomAccessFile(RUTA, "rw")) {
            long totalRegistros = archivo.length() / TAMANO_REGISTRO;
            
            for (int i = 0; i < totalRegistros; i++) {
                archivo.seek(i * TAMANO_REGISTRO);
                String cod = archivo.readUTF().trim();
                
                if (cod.equals(codigo.trim())) {
                    archivo.seek(i * TAMANO_REGISTRO);
                    archivo.writeUTF(completarEspacios("", 10)); 
                    return;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al eliminar prestamo");
        }
    }

    @Override
    public List<Prestamo> listar() {
        List<Prestamo> lista = new ArrayList<>();
        try (RandomAccessFile archivo = new RandomAccessFile(RUTA, "r")) {
            long totalRegistros = archivo.length() / TAMANO_REGISTRO;
            
            for (int i = 0; i < totalRegistros; i++) {
                archivo.seek(i * TAMANO_REGISTRO);
                
                String cod = archivo.readUTF().trim();
                String usuarioPlaceholder = archivo.readUTF().trim();
                String ejemplarPlaceholder = archivo.readUTF().trim();
                String fechaPPlaceholder = archivo.readUTF().trim();
                String fechaDPlaceholder = archivo.readUTF().trim();
                String estadoPlaceholder = archivo.readUTF().trim();
                
                if (!cod.isEmpty()) {
                    lista.add(new Prestamo(cod, null, null, null, null, null));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al listar prestamos");
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

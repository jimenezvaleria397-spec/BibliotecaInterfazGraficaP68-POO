/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.dao.archivos;

import ec.edu.ups.biblioteca.dao.DAO;
import ec.edu.ups.biblioteca.models.Bibliotecario;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class BibliotecarioDAOArchivo implements DAO<Bibliotecario>{
    private final String RUTA = "c:/carpeta1/bibliotecariosRandomico.ups";
    private final int TAMANO_REGISTRO = 56; // Tamaño calculado: 1 int (4) + 1 String (52) = 56 bytes

    @Override
    public void agregar(Bibliotecario objeto) {
        try (RandomAccessFile archivo = new RandomAccessFile(RUTA, "rw")) {
            archivo.seek(archivo.length());
            
            archivo.writeInt(objeto.getIdBibliotecario());
            archivo.writeUTF(completarEspacios(objeto.getEmail(), 25));
            
        } catch (IOException e) {
            System.out.println("Error de escritura en bibliotecario");
        }
    }

    @Override
    public Bibliotecario buscarPorCodigo(String codigo) {
        try (RandomAccessFile archivo = new RandomAccessFile(RUTA, "r")) {
            long totalRegistros = archivo.length() / TAMANO_REGISTRO;
            int idBuscado = Integer.parseInt(codigo.trim()); // Convertimos el String a int
            
            for (int i = 0; i < totalRegistros; i++) {
                archivo.seek(i * TAMANO_REGISTRO);
                
                int id = archivo.readInt();
                String email = archivo.readUTF().trim();
                
                if (id == idBuscado) {
                    return new Bibliotecario(id, email);
                }
            }
        } catch (IOException e) {
            System.out.println("Error de lectura en bibliotecario");
        } catch (NumberFormatException e) {
            System.out.println("El código ingresado no es un número válido");
        }
        return null;
    }

    @Override
    public void actualizar(String codigo) {
        try (RandomAccessFile archivo = new RandomAccessFile(RUTA, "rw")) {
            long totalRegistros = archivo.length() / TAMANO_REGISTRO;
            int idBuscado = Integer.parseInt(codigo.trim());
            
            // Objeto temporal respetando tu constructor (id, email)
            Bibliotecario nuevoObjeto = new Bibliotecario(idBuscado, "temporal@correo.com");

            for (int i = 0; i < totalRegistros; i++) {
                archivo.seek(i * TAMANO_REGISTRO);
                int id = archivo.readInt();
                
                if (id == idBuscado) {
                    archivo.seek(i * TAMANO_REGISTRO); // Regresar al inicio del registro
                    
                    archivo.writeInt(nuevoObjeto.getIdBibliotecario());
                    archivo.writeUTF(completarEspacios(nuevoObjeto.getEmail(), 25));
                    return;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al actualizar bibliotecario");
        } catch (NumberFormatException e) {
            System.out.println("Código no válido para actualizar");
        }
    }

    @Override
    public void eliminar(String codigo) {
        try (RandomAccessFile archivo = new RandomAccessFile(RUTA, "rw")) {
            long totalRegistros = archivo.length() / TAMANO_REGISTRO;
            int idBuscado = Integer.parseInt(codigo.trim());
            
            for (int i = 0; i < totalRegistros; i++) {
                archivo.seek(i * TAMANO_REGISTRO);
                int id = archivo.readInt();
                
                if (id == idBuscado) {
                    archivo.seek(i * TAMANO_REGISTRO);
                    archivo.writeInt(0); 
                    return;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al eliminar bibliotecario");
        } catch (NumberFormatException e) {
            System.out.println("Código no válido para eliminar");
        }
    }

    @Override
    public List<Bibliotecario> listar() {
        List<Bibliotecario> lista = new ArrayList<>();
        try (RandomAccessFile archivo = new RandomAccessFile(RUTA, "r")) {
            long totalRegistros = archivo.length() / TAMANO_REGISTRO;
            
            for (int i = 0; i < totalRegistros; i++) {
                archivo.seek(i * TAMANO_REGISTRO);
                
                int id = archivo.readInt();
                String email = archivo.readUTF().trim();
                
                // Si el id no es 0, significa que es un registro activo (no eliminado)
                if (id != 0) {
                    lista.add(new Bibliotecario(id, email));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al listar bibliotecarios");
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

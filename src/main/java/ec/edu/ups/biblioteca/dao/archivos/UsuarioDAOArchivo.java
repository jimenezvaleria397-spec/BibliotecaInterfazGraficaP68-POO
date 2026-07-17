/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.dao.archivos;

import ec.edu.ups.biblioteca.dao.DAO;
import ec.edu.ups.biblioteca.models.Usuario;
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
public class UsuarioDAOArchivo implements DAO<Usuario> {

    private String ruta = "c:/carpeta1/usuariosRandomico.ups";
    private final int TAMANO_REGISTRO = 126; 

    @Override
    public void agregar(Usuario objeto) {
        try (RandomAccessFile archivoEscritura = new RandomAccessFile(ruta, "rw")) {
            archivoEscritura.seek(archivoEscritura.length());
            
            archivoEscritura.writeUTF(completarEspacios(objeto.getCedula(), 10)); // 10 caracteres
            archivoEscritura.writeUTF(completarEspacios(objeto.getNombre(), 25)); // 25 caracteres
            archivoEscritura.writeUTF(completarEspacios(objeto.getCorreo(), 25)); // 25 caracteres
            
        } catch (FileNotFoundException e) {
            System.out.println("Ruta de archivo no encontrada");
        } catch (IOException e) {
            System.out.println("Error de escritura");
        }
    }

    @Override
    public Usuario buscarPorCodigo(String codigo) {
        try (RandomAccessFile archivoLectura = new RandomAccessFile(ruta, "r")) {
            long totalRegistros = archivoLectura.length() / TAMANO_REGISTRO;
            
            for (int i = 0; i < totalRegistros; i++) {
                archivoLectura.seek(i * TAMANO_REGISTRO);
                String cedula = archivoLectura.readUTF().trim();
                String nombre = archivoLectura.readUTF().trim();
                String correo = archivoLectura.readUTF().trim();
                
                if (cedula.equals(codigo.trim())) {
                    return new Usuario(cedula, nombre, correo);
                }
            }
        } catch (IOException e) {
            System.out.println("Error de lectura");
        }
        return null;
    }

    @Override
    public void actualizar(String codigo) {
        try (RandomAccessFile archivo = new RandomAccessFile(ruta, "rw")) {
            long totalRegistros = archivo.length() / TAMANO_REGISTRO;
            Usuario nuevoObjeto = new Usuario(codigo, "Nombre Editado", "correo@editado.com");

            for (int i = 0; i < totalRegistros; i++) {
                archivo.seek(i * TAMANO_REGISTRO);
                String cedula = archivo.readUTF().trim();

                if (cedula.equals(codigo.trim())) {
                    archivo.seek(i * TAMANO_REGISTRO);

                    archivo.writeUTF(completarEspacios(nuevoObjeto.getCedula(), 10));
                    archivo.writeUTF(completarEspacios(nuevoObjeto.getNombre(), 25));
                    archivo.writeUTF(completarEspacios(nuevoObjeto.getCorreo(), 25));
                    return;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al actualizar");
        }
    }
    

    @Override
    public void eliminar(String codigo) {
    
        try (RandomAccessFile archivo = new RandomAccessFile(ruta, "rw")) {
            long totalRegistros = archivo.length() / TAMANO_REGISTRO;
            for (int i = 0; i < totalRegistros; i++) {
                archivo.seek(i * TAMANO_REGISTRO);
                String cedula = archivo.readUTF().trim();
                
                if (cedula.equals(codigo.trim())) {
                    archivo.seek(i * TAMANO_REGISTRO);
                    archivo.writeUTF(completarEspacios("", 10)); 
                    return;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al eliminar");
        }
    }

    @Override
    public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<>();
        try (RandomAccessFile archivoLectura = new RandomAccessFile(ruta, "r")) {
            long totalRegistros = archivoLectura.length() / TAMANO_REGISTRO;
            
            for (int i = 0; i < totalRegistros; i++) {
                archivoLectura.seek(i * TAMANO_REGISTRO);
                String cedula = archivoLectura.readUTF().trim();
                String nombre = archivoLectura.readUTF().trim();
                String correo = archivoLectura.readUTF().trim();
  
                if (!cedula.isEmpty()) {
                    lista.add(new Usuario(cedula, nombre, correo));
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
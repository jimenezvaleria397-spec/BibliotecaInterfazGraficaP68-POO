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
    private static final String RUTA = "datos/bibliotecarios.ups";
    private static final int LONG_EMAIL = 40;

    private static final int TAMANIO_REGISTRO = 4 + (LONG_EMAIL * 2);
    static {
        File carpeta = new File("datos");
        if (!carpeta.exists()) {
            carpeta.mkdir();
        }
    }

    private void escribirString(RandomAccessFile raf, String valor, int longitud) throws IOException {
        if (valor == null) valor = "";
        if (valor.length() > longitud) {
            valor = valor.substring(0, longitud);
        } else {
            while (valor.length() < longitud) {
                valor += " ";
            }
        }
        raf.writeChars(valor);
    }

    private String leerString(RandomAccessFile raf, int longitud) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < longitud; i++) {
            sb.append(raf.readChar());
        }
        return sb.toString().trim();
    }

    @Override
    public void agregar(Bibliotecario bibliotecario) {
        try (RandomAccessFile archivoEscritura = new RandomAccessFile(RUTA, "rw")) {
            archivoEscritura.seek(archivoEscritura.length());
            archivoEscritura.writeInt(bibliotecario.getIdBibliotecario());
            escribirString(archivoEscritura, bibliotecario.getEmail(), LONG_EMAIL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Bibliotecario buscarPorCodigo(String codigo) {
        int idBuscado = Integer.parseInt(codigo);
        try (RandomAccessFile archivoLectura = new RandomAccessFile(RUTA, "r")) {
            long totalRegistros = archivoLectura.length() / TAMANIO_REGISTRO;
            for (int i = 0; i < totalRegistros; i++) {
                archivoLectura.seek(i * TAMANIO_REGISTRO);
                int id = archivoLectura.readInt();
                String email = leerString(archivoLectura, LONG_EMAIL);
                if (id == idBuscado) {
                    return new Bibliotecario(id, email);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void actualizar(String codigo) {
        // Requerido por la interfaz DAO; la lógica real está en actualizar(Bibliotecario)
    }

    public void actualizar(Bibliotecario bibliotecario) {
        try (RandomAccessFile archivoLectura = new RandomAccessFile(RUTA, "rw")) {
            long totalRegistros = archivoLectura.length() / TAMANIO_REGISTRO;
            for (int i = 0; i < totalRegistros; i++) {
                archivoLectura.seek(i * TAMANIO_REGISTRO);
                int id = archivoLectura.readInt();
                if (id == bibliotecario.getIdBibliotecario()) {
                    archivoLectura.seek(i * TAMANIO_REGISTRO);
                    archivoLectura.writeInt(bibliotecario.getIdBibliotecario());
                    escribirString(archivoLectura, bibliotecario.getEmail(), LONG_EMAIL);
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(String codigo) {
        int idBuscado = Integer.parseInt(codigo);
        List<Bibliotecario> todos = listar();
        todos.removeIf(b -> b.getIdBibliotecario() == idBuscado);

        try (RandomAccessFile archivoEscritura = new RandomAccessFile(RUTA, "rw")) {
            archivoEscritura.setLength(0);
            for (Bibliotecario b : todos) {
                archivoEscritura.seek(archivoEscritura.length());
                archivoEscritura.writeInt(b.getIdBibliotecario());
                escribirString(archivoEscritura, b.getEmail(), LONG_EMAIL);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Bibliotecario> listar() {
        List<Bibliotecario> lista = new ArrayList<>();
        try (RandomAccessFile archivoLectura = new RandomAccessFile(RUTA, "r")) {
            long totalRegistros = archivoLectura.length() / TAMANIO_REGISTRO;
            for (int i = 0; i < totalRegistros; i++) {
                archivoLectura.seek(i * TAMANIO_REGISTRO);
                int id = archivoLectura.readInt();
                String email = leerString(archivoLectura, LONG_EMAIL);
                lista.add(new Bibliotecario(id, email));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
}

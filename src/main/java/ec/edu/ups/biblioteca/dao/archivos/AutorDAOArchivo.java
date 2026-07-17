/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.dao.archivos;

import ec.edu.ups.biblioteca.dao.DAO;
import ec.edu.ups.biblioteca.models.Autor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class AutorDAOArchivo implements DAO<Autor> {
    private static final String RUTA = "autores.ups";
    private static final int LONG_CODIGO = 10;
    private static final int LONG_NOMBRE = 30;
    private static final int LONG_NACIONALIDAD = 20;
    
    private static final int TAMANIO_REGISTRO
            = (LONG_CODIGO * 2) + (LONG_NOMBRE * 2) + (LONG_NACIONALIDAD * 2) + 12;
    
    private void escribirString(RandomAccessFile archivo, String valor, int longitud) throws IOException {
        if (valor == null) valor = "";
        if (valor.length() > longitud) {
            valor = valor.substring(0, longitud);
        } else {
            while (valor.length() < longitud) valor += " ";
        }
        archivo.writeChars(valor);
    }

    private String leerString(RandomAccessFile archivo, int longitud) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < longitud; i++) sb.append(archivo.readChar());
        return sb.toString().trim();
    }

    @Override
    public void agregar(Autor autor) {
        try {
            RandomAccessFile archivoEscritura;
            archivoEscritura = new RandomAccessFile(RUTA, "rw");
            archivoEscritura.seek(archivoEscritura.length());
            escribirString(archivoEscritura, autor.getCodigoAutor(), LONG_CODIGO);
            escribirString(archivoEscritura, autor.getNombre(), LONG_NOMBRE);
            escribirString(archivoEscritura, autor.getNacionalidad(), LONG_NACIONALIDAD);
            archivoEscritura.writeInt(autor.getFechadeNac().getYear());
            archivoEscritura.writeInt(autor.getFechadeNac().getMonthValue());
            archivoEscritura.writeInt(autor.getFechadeNac().getDayOfMonth());
            archivoEscritura.close();
        } catch (FileNotFoundException e1) {
            System.out.println("Ruta de archivo no encontrada");
        } catch (IOException e1) {
            System.out.println("Error de escritura");
        } catch (Exception e1) {
            System.out.println("Error General");
        }
    }

    @Override
    public Autor buscarPorCodigo(String codigo) {
        Autor encontrado = null;
        try {
            RandomAccessFile archivoLectura;
            archivoLectura = new RandomAccessFile(RUTA, "r");
            long totalRegistros = archivoLectura.length() / TAMANIO_REGISTRO;

            for (int i = 0; i < totalRegistros; i++) {
                archivoLectura.seek(i * TAMANIO_REGISTRO);
                String codigoLeido = leerString(archivoLectura, LONG_CODIGO);
                String nombre = leerString(archivoLectura, LONG_NOMBRE);
                String nacionalidad = leerString(archivoLectura, LONG_NACIONALIDAD);
                int anio = archivoLectura.readInt();
                int mes = archivoLectura.readInt();
                int dia = archivoLectura.readInt();

                if (codigoLeido.equals(codigo)) {
                    encontrado = new Autor(nombre, nacionalidad, codigoLeido,
                            LocalDate.of(anio, mes, dia), new ArrayList<>());
                }
            }
            archivoLectura.close();
        } catch (FileNotFoundException e1) {
            System.out.println("Ruta de archivo no encontrada");
        } catch (IOException e1) {
            System.out.println("Error de lectura");
        } catch (Exception e1) {
            System.out.println("Error General");
        }
        return encontrado;
    }

    @Override
    public void actualizar(String codigo) {
    }

    public void actualizar(Autor autor) {
        try {
            RandomAccessFile archivo;
            archivo = new RandomAccessFile(RUTA, "rw");
            long totalRegistros = archivo.length() / TAMANIO_REGISTRO;

            for (int i = 0; i < totalRegistros; i++) {
                archivo.seek(i * TAMANIO_REGISTRO);
                String codigoLeido = leerString(archivo, LONG_CODIGO);

                if (codigoLeido.equals(autor.getCodigoAutor())) {
                    archivo.seek(i * TAMANIO_REGISTRO);
                    escribirString(archivo, autor.getCodigoAutor(), LONG_CODIGO);
                    escribirString(archivo, autor.getNombre(), LONG_NOMBRE);
                    escribirString(archivo, autor.getNacionalidad(), LONG_NACIONALIDAD);
                    archivo.writeInt(autor.getFechadeNac().getYear());
                    archivo.writeInt(autor.getFechadeNac().getMonthValue());
                    archivo.writeInt(autor.getFechadeNac().getDayOfMonth());
                }
            }
            archivo.close();
        } catch (FileNotFoundException e1) {
            System.out.println("Ruta de archivo no encontrada");
        } catch (IOException e1) {
            System.out.println("Error de escritura");
        } catch (Exception e1) {
            System.out.println("Error General");
        }
    }

    @Override
    public void eliminar(String codigo) {
        List<Autor> todos = listar();
        todos.removeIf(a -> a.getCodigoAutor().equals(codigo));

        try {
            RandomAccessFile archivo;
            archivo = new RandomAccessFile(RUTA, "rw");
            archivo.setLength(0);
            for (Autor a : todos) {
                archivo.seek(archivo.length());
                escribirString(archivo, a.getCodigoAutor(), LONG_CODIGO);
                escribirString(archivo, a.getNombre(), LONG_NOMBRE);
                escribirString(archivo, a.getNacionalidad(), LONG_NACIONALIDAD);
                archivo.writeInt(a.getFechadeNac().getYear());
                archivo.writeInt(a.getFechadeNac().getMonthValue());
                archivo.writeInt(a.getFechadeNac().getDayOfMonth());
            }
            archivo.close();
        } catch (FileNotFoundException e1) {
            System.out.println("Ruta de archivo no encontrada");
        } catch (IOException e1) {
            System.out.println("Error de escritura");
        } catch (Exception e1) {
            System.out.println("Error General");
        }
    }

    @Override
    public List<Autor> listar() {
        List<Autor> lista = new ArrayList<>();
        try {
            RandomAccessFile archivoLectura;
            archivoLectura = new RandomAccessFile(RUTA, "r");
            long totalRegistros = archivoLectura.length() / TAMANIO_REGISTRO;

            for (int i = 0; i < totalRegistros; i++) {
                archivoLectura.seek(i * TAMANIO_REGISTRO);
                String codigo = leerString(archivoLectura, LONG_CODIGO);
                String nombre = leerString(archivoLectura, LONG_NOMBRE);
                String nacionalidad = leerString(archivoLectura, LONG_NACIONALIDAD);
                int anio = archivoLectura.readInt();
                int mes = archivoLectura.readInt();
                int dia = archivoLectura.readInt();
                lista.add(new Autor(nombre, nacionalidad, codigo,
                        LocalDate.of(anio, mes, dia), new ArrayList<>()));
            }
            archivoLectura.close();
        } catch (FileNotFoundException e1) {
            System.out.println("Ruta de archivo no encontrada");
        } catch (IOException e1) {
            System.out.println("Error de lectura");
        } catch (Exception e1) {
            System.out.println("Error General");
        }
        return lista;
    }
    
    
}

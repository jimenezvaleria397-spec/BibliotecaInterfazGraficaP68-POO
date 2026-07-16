/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.dao.archivos;

import ec.edu.ups.biblioteca.dao.DAO;
import ec.edu.ups.biblioteca.models.Autor;
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
    public void agregar(Autor autor) {
        try (RandomAccessFile archivoLectura = new RandomAccessFile(RUTA, "rw")) {
            archivoLectura.seek(archivoLectura.length());
            escribirString(archivoLectura, autor.getCodigoAutor(), LONG_CODIGO);
            escribirString(archivoLectura, autor.getNombre(), LONG_NOMBRE);
            escribirString(archivoLectura, autor.getNacionalidad(), LONG_NACIONALIDAD);
            archivoLectura.writeInt(autor.getFechadeNac().getYear());
            archivoLectura.writeInt(autor.getFechadeNac().getMonthValue());
            archivoLectura.writeInt(autor.getFechadeNac().getDayOfMonth());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Autor buscarPorCodigo(String codigo) {
        try (RandomAccessFile archivoLectura = new RandomAccessFile(RUTA, "r")) {
            long totalRegistros = archivoLectura.length() / TAMANIO_REGISTRO;

            for (int i = 0; i < totalRegistros; i++) {
                archivoLectura.seek(i * TAMANIO_REGISTRO);
                String codigoLeido = leerString(archivoLectura, LONG_CODIGO);

                if (codigoLeido.equals(codigo)) {
                    String nombre = leerString(archivoLectura, LONG_NOMBRE);
                    String nacionalidad = leerString(archivoLectura, LONG_NACIONALIDAD);
                    int anio = archivoLectura.readInt();
                    int mes = archivoLectura.readInt();
                    int dia = archivoLectura.readInt();
                    LocalDate fechaNac = LocalDate.of(anio, mes, dia);
                    return new Autor(nombre, nacionalidad, codigoLeido, fechaNac, new ArrayList<>());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void actualizar(String codigo) {
    }
    
    public void actualizar(Autor autor) {
        try (RandomAccessFile archivoLectura = new RandomAccessFile(RUTA, "rw")) {
            long totalRegistros = archivoLectura.length() / TAMANIO_REGISTRO;

            for (int i = 0; i < totalRegistros; i++) {
                archivoLectura.seek(i * TAMANIO_REGISTRO);
                String codigoLeido = leerString(archivoLectura, LONG_CODIGO);

                if (codigoLeido.equals(autor.getCodigoAutor())) {
                    // Volvemos al inicio del registro para sobrescribirlo completo
                    archivoLectura.seek(i * TAMANIO_REGISTRO);
                    escribirString(archivoLectura, autor.getCodigoAutor(), LONG_CODIGO);
                    escribirString(archivoLectura, autor.getNombre(), LONG_NOMBRE);
                    escribirString(archivoLectura, autor.getNacionalidad(), LONG_NACIONALIDAD);
                    archivoLectura.writeInt(autor.getFechadeNac().getYear());
                    archivoLectura.writeInt(autor.getFechadeNac().getMonthValue());
                    archivoLectura.writeInt(autor.getFechadeNac().getDayOfMonth());
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
}

    @Override
    public void eliminar(String codigo) {
        List<Autor> todos = listar();
        todos.removeIf(a -> a.getCodigoAutor().equals(codigo));

        try (RandomAccessFile archivoEscritura = new RandomAccessFile(RUTA, "rw")) {
            archivoEscritura.setLength(0); // vaciamos el archivo
            for (Autor autor : todos) {
                archivoEscritura.seek(archivoEscritura.length());
                escribirString(archivoEscritura, autor.getCodigoAutor(), LONG_CODIGO);
                escribirString(archivoEscritura, autor.getNombre(), LONG_NOMBRE);
                escribirString(archivoEscritura, autor.getNacionalidad(), LONG_NACIONALIDAD);
                archivoEscritura.writeInt(autor.getFechadeNac().getYear());
                archivoEscritura.writeInt(autor.getFechadeNac().getMonthValue());
                archivoEscritura.writeInt(autor.getFechadeNac().getDayOfMonth());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Autor> listar() {
        List<Autor> lista = new ArrayList<>();
        try (RandomAccessFile archivoLectura = new RandomAccessFile(RUTA, "r")) {
            long totalRegistros = archivoLectura.length() / TAMANIO_REGISTRO;

            for (int i = 0; i < totalRegistros; i++) {
                archivoLectura.seek(i * TAMANIO_REGISTRO);
                String codigo = leerString(archivoLectura, LONG_CODIGO);
                String nombre = leerString(archivoLectura, LONG_NOMBRE);
                String nacionalidad = leerString(archivoLectura, LONG_NACIONALIDAD);
                int anio = archivoLectura.readInt();
                int mes = archivoLectura.readInt();
                int dia = archivoLectura.readInt();
                LocalDate fechaNac = LocalDate.of(anio, mes, dia);

                lista.add(new Autor(nombre, nacionalidad, codigo, fechaNac, new ArrayList<>()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    
}

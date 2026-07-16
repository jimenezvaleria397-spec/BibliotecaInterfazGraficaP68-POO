/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.dao.archivos;

import ec.edu.ups.biblioteca.dao.DAO;
import ec.edu.ups.biblioteca.models.EjemplarLibro;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class EjemplarLibroDAOArchivo implements DAO<EjemplarLibro> {
    
    private static final String ARCHIVO = "ejemplares.ups";
    private List<EjemplarLibro> ejemplares;

    public EjemplarLibroDAOArchivo(List<EjemplarLibro> ejemplares) {
        this.ejemplares = ejemplares;
    }
    
    

    @Override
    public void agregar(EjemplarLibro objeto) {
        ejemplares.add(objeto);
        guardarEnArchivo();
    }

    @Override
    public EjemplarLibro buscarPorCodigo(String codigo) {
        for (EjemplarLibro e : ejemplares) {
            if (e.getCodigoBarras().equals(codigo)) {
                return e;
            }
        }
        return null;
    }

    @Override
    public void actualizar(String codigo) {
            }

    @Override
    public void eliminar(String codigo) {
        EjemplarLibro existente = buscarPorCodigo(codigo);
        if (existente != null) {
            ejemplares.remove(existente);
            guardarEnArchivo();
        }    
    }

    @Override
    public List<EjemplarLibro> listar() {
        return ejemplares;
    }
    
    @SuppressWarnings("unchecked")
    private List<EjemplarLibro> cargarDesdeArchivo() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (List<EjemplarLibro>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al leer el archivo binario: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private void guardarEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            oos.writeObject(ejemplares);
        }    
    }
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.dao;

import ec.edu.ups.biblioteca.models.Libro;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class LibroDAO implements DAO<Libro> {
    private List<Libro> libros;  
    
    public LibroDAO(){
        this.libros = new ArrayList<>();
    }

    @Override
    public void agregar(Libro objeto) {
        libros.add(objeto);
    }

    @Override
    public Libro buscarPorCodigo(String codigo) {
        for (Libro libro : libros) {
            if(libro.getCodigo().equals(codigo)){
                return libro;
            }
        }
        return null;
    }

    @Override
    public void actualizar(String codigo) {
    }
    public void actualizar(Libro libroAc){
        Libro existente = buscarPorCodigo(libroAc.getCodigo());
        if(existente != null){
            existente.setAnio(libroAc.getAnio());
            existente.setAutor(libroAc.getAutor());
            existente.setCodigo(libroAc.getCodigo());
            existente.setEditorial(libroAc.getEditorial());
            existente.setEjemplares(libroAc.getEjemplares());
            existente.setGenero(libroAc.getGenero());
            existente.setTitulo(libroAc.getTitulo());
        }
    }

    @Override
    public void eliminar(String codigo) {
        Libro existente = buscarPorCodigo(codigo);
        if (existente != null) {
            libros.remove(existente);
        }
    }

    @Override
    public List<Libro> listar() {
        return libros;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.controllers;
import ec.edu.ups.biblioteca.dao.LibroDAO;
import ec.edu.ups.biblioteca.models.Libro;
import java.util.List;
/**
 *
 * @author jimen
 */

public class LibroController {
    

    private LibroDAO libroDAO;

    public LibroController() {
        libroDAO = new LibroDAO();
    }

    public void agregar(Libro libro) {
        libroDAO.agregar(libro);
    }

    public Libro buscarPorCodigo(String codigo) {
        return libroDAO.buscarPorCodigo(codigo);
    }

    public void actualizar(Libro libro) {
        libroDAO.actualizar(libro);
    }

    public void eliminar(String codigo) {
        libroDAO.eliminar(codigo);
    }

    public List<Libro> listar() {
        return libroDAO.listar();
    }
}
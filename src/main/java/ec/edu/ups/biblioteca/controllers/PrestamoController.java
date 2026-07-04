/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.controllers;
import ec.edu.ups.biblioteca.dao.LibroDAO;
import ec.edu.ups.biblioteca.dao.PrestamoDAO;
import ec.edu.ups.biblioteca.dao.UsuarioDAO;
import ec.edu.ups.biblioteca.models.Prestamo;
import java.util.List;
/**
 *
 * @author jimen
 */

public class PrestamoController {
    
    private PrestamoController prestamoController = new PrestamoController();
    private PrestamoDAO prestamoDAO;

    public PrestamoController() {

        LibroDAO libroDAO = new LibroDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        prestamoDAO = new PrestamoDAO(libroDAO, usuarioDAO);
    }

    public void agregar(Prestamo prestamo) {
        prestamoDAO.agregar(prestamo);
    }

    public Prestamo buscarPorCodigo(String codigo) {
        return prestamoDAO.buscarPorCodigo(codigo);
    }

    public void actualizar(Prestamo prestamo) {
        prestamoDAO.actualizar(prestamo);
    }

    public void eliminar(String codigo) {
        prestamoDAO.eliminar(codigo);
    }

    public List<Prestamo> listar() {
        return prestamoDAO.listar();
    }

}
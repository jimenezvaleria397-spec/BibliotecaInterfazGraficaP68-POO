/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.controllers;
import ec.edu.ups.biblioteca.dao.LibroDAO;
import ec.edu.ups.biblioteca.dao.PrestamoDAO;
import ec.edu.ups.biblioteca.dao.UsuarioDAO;
import ec.edu.ups.biblioteca.models.Prestamo;
/**
 *
 * @author jimen
 */

public class DevolucionController {

    private PrestamoDAO prestamoDAO;

    public DevolucionController() {
        LibroDAO libroDAO = LibroDAO.getLibroDAO();
        UsuarioDAO usuarioDAO = UsuarioDAO.getUsuarioDAO();
        prestamoDAO = PrestamoDAO.getPrestamoDAO(libroDAO, usuarioDAO);
    }

    public Prestamo buscarPorCodigo(String codigo) {
        return prestamoDAO.buscarPorCodigo(codigo);
    }

    public void registrarDevolucion(Prestamo prestamo) {
        prestamo.setEstado(false); // false cuando el préstamo ya se acabo (devolucion)
        prestamoDAO.actualizar(prestamo);
    }
}

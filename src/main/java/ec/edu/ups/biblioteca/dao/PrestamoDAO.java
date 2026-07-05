/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.dao;

import ec.edu.ups.biblioteca.models.Prestamo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class PrestamoDAO implements DAO<Prestamo>{
    private static PrestamoDAO prestamoDAO;
    private List<Prestamo> prestamos;
    private LibroDAO libroDAO;
    private UsuarioDAO usuarioDAO;

    private PrestamoDAO(LibroDAO libroDAO, UsuarioDAO usuarioDAO) {
        this.libroDAO = libroDAO;
        this.usuarioDAO = usuarioDAO;
        this.prestamos = new ArrayList<>();
    }
    
    public static PrestamoDAO getPrestamoDAO(LibroDAO libroDAO, UsuarioDAO usuarioDAO) {
        if (prestamoDAO == null) {
            prestamoDAO = new PrestamoDAO(libroDAO, usuarioDAO);
        }
        return prestamoDAO;
    }

    @Override
    public void agregar(Prestamo prestamo) {
           prestamos.add(prestamo);
    }

    @Override
    public Prestamo buscarPorCodigo(String codigo) {
        for (Prestamo prestamo : prestamos) {
            if(prestamo.getCodigo().equals(codigo)){
                return prestamo;
            }
        }
        return null;
    }

    @Override
    public void actualizar(String codigo){
    }
    
    public void actualizar(Prestamo prestamo) {
        Prestamo existente = buscarPorCodigo(prestamo.getCodigo());
        if (existente != null) {
            existente.setFechaPrestamo(prestamo.getFechaPrestamo());
            existente.setFechaDevolucion(prestamo.getFechaDevolucion());
            existente.setEstado(prestamo.isEstado());
        }
    }

    @Override
    public void eliminar(String codigo) {
        Prestamo existente = buscarPorCodigo(codigo);
        if (existente != null) {
            prestamos.remove(existente);
        }
    }

    @Override
    public List<Prestamo> listar() {
        return prestamos;
    }
}

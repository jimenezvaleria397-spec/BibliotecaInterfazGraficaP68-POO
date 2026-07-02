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
    private List<Prestamo> prestamos;
    private LibroDAO libroDAO;
    private UsuarioDAO usuarioDAO;

    public PrestamoDAO(List prestamos, LibroDAO libroDAO, UsuarioDAO usuarioDAO) {
        this.prestamos = new ArrayList<>();
        this.libroDAO = libroDAO;
        this.usuarioDAO = usuarioDAO;
    }

    @Override
    public void agregar(Prestamo prestamo) {
           prestamos.add(prestamo);
    }

    @Override
    public Prestamo buscarPorCodigo(String codigo) {
        for (Prestamo p : prestamos) {
            if(p.getCodigo().equals(codigo)){
                return p;
            }
        }
        return null;
    }

    @Override
    public void actualizar(String codigo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(String codigo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Prestamo> listar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}

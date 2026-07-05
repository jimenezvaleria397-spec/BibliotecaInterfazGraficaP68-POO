/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.controllers;
import ec.edu.ups.biblioteca.dao.UsuarioDAO;
import ec.edu.ups.biblioteca.models.Usuario;
import java.util.List;
/**
 *
 * @author jimen
 */



public class UsuarioController {

    private UsuarioDAO usuarioDAO;

    public UsuarioController() { // constructor publico en controller
        usuarioDAO = UsuarioDAO.getUsuarioDAO();
    }

    public void agregar(Usuario usuario) {
        usuarioDAO.agregar(usuario);
    }

    public Usuario buscarPorCodigo(String codigo) {
        return usuarioDAO.buscarPorCodigo(codigo);
    }

    public void actualizar(Usuario usuario) {
        usuarioDAO.actualizar(usuario);
    }

    public void eliminar(String codigo) {
        usuarioDAO.eliminar(codigo);
    }

    public List<Usuario> listar() {
        return usuarioDAO.listar();
    }
}
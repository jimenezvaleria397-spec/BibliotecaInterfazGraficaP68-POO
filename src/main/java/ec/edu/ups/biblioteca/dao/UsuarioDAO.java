/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.dao;

import ec.edu.ups.biblioteca.models.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class UsuarioDAO implements DAO<Usuario>{
    private static UsuarioDAO usuarioDAO;
    private List<Usuario> usuarios;
    
    private UsuarioDAO(){ // constructor privado en DAO
        this.usuarios = new ArrayList<>();
    }
    public static UsuarioDAO getUsuarioDAO() {
        if (usuarioDAO == null) {
            usuarioDAO = new UsuarioDAO();
        }
        return usuarioDAO;
    }

    @Override
    public void agregar(Usuario objeto) {
        usuarios.add(objeto);
    }

    @Override
    public Usuario buscarPorCodigo(String codigo) {
        for (Usuario usuario : usuarios) {
            if(usuario.getCedula().equals(codigo)){
                return usuario;
            }
        }
        return null;
    }

    @Override
    public void actualizar(String codigo) {
    }
    public void actualizar(Usuario usuarioAC){
        Usuario existente = buscarPorCodigo(usuarioAC.getCedula());
        if(existente != null){
            existente.setCedula(usuarioAC.getCedula());
            existente.setCorreo(usuarioAC.getCorreo());
            existente.setNombre(usuarioAC.getNombre());
        }
    }

    @Override
    public void eliminar(String codigo) {
        Usuario existente = buscarPorCodigo(codigo);
        if(existente != null){
            usuarios.remove(existente);
        }
    }

    @Override
    public List<Usuario> listar() {
        return usuarios;
    }
    
}

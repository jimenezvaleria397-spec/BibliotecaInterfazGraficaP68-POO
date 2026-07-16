/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.dao.archivos;

import ec.edu.ups.biblioteca.dao.DAO;
import ec.edu.ups.biblioteca.models.Usuario;
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
public class UsuarioDAOArchivo implements DAO<Usuario>{
    private final String archivo = "usuarios.ups";
    private List<Usuario> listaUsuarios;

    public UsuarioDAOArchivo() {
        listaUsuarios = leerArchivo();
    }

    @Override
    public void agregar(Usuario objeto) {
        usuarios.add(usuario);
        guardarArchivo();
    }

    @Override
    public Usuario buscarPorCodigo(String codigo) {
        for (Usuario usuario : usuarios) {
            if (usuario.getCedula().equals(codigo)) {
                return usuario;
            }
        }
        return null;
    }

    @Override
    public void actualizar(String codigo) {
    }
    
    public void actualizar(Usuario usuario) {
        Usuario existente = buscarPorCodigo(usuario.getCedula());

        if (existente != null) {
            existente.setNombre(usuario.getNombre());
            existente.setCorreo(usuario.getCorreo());
            guardarArchivo();
        }
    }
    
    @Override
    public void eliminar(String codigo) {
        Usuario existente = buscarPorCodigo(codigo);

        if (existente != null) {
            usuarios.remove(existente);
            guardarArchivo();
        }
    }

    @Override
    public List<Usuario> listar() {
        return usuarios;
    }

    
}

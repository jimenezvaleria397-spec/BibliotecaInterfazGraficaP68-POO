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
public class UsuarioDAOArchivo implements DAO<Usuario> {

    private final String archivo = "usuarios.ups";
    private List<Usuario> usuarios;

    public UsuarioDAOArchivo() {
        usuarios = leerArchivo();
    }

    @Override
    public void agregar(Usuario objeto) {
        usuarios.add(objeto);
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
        // no se usa aquí; existe solo porque la interfaz DAO lo exige
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

    //@SuppressWarnings("unchecked");

    private void guardarArchivo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private List<Usuario> leerArchivo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.controllers;
import ec.edu.ups.biblioteca.dao.UsuarioDAO;
import ec.edu.ups.biblioteca.excepciones.ValidacionException;
import ec.edu.ups.biblioteca.excepciones.Validador;
import ec.edu.ups.biblioteca.models.Usuario;
import ec.edu.ups.biblioteca.views.UsuarioView;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author jimen
 */
public class UsuarioController {

    private UsuarioDAO usuarioDAO;
    private UsuarioView usuarioView;
    private boolean creandoNuevo = false;

    public UsuarioController(UsuarioDAO usuarioDAO, UsuarioView usuarioView) {
        this.usuarioDAO = usuarioDAO;
        this.usuarioView = usuarioView;
        configurarEventos();
        listarUsuarios();
        
    }
    
    public void configurarEventos(){
        configurarEventosCrearUsuario();
        configurarEventosBusquedaUsuario();
        configurarEventosActualizarUsuario();
        configurarEventosEliminarUsuario();
        configurarEventosListarUsuario(); 
        configurarEventosLimpiarUsuario(); 
    }
    
    private void configurarEventosCrearUsuario(){
        usuarioView.getBtnCrear().addActionListener(e -> crearUsuario());
    }
    
    private void crearUsuario(){
        if(!creandoNuevo){
            usuarioView.limpiar();
            usuarioView.habilitar();
            usuarioView.getTxtCedula().setEditable(true);
            usuarioView.getBtnCrear().setText("Guardar");
            creandoNuevo = true;
        } else {
            try {
                String cedula = usuarioView.getTxtCedula().getText();
                String nombre = usuarioView.getTxtNombre().getText();
                String correo = usuarioView.getTxtCorreo().getText();

                Validador.validarNoVacio(cedula, "Cédula");
                Validador.validarNoVacio(nombre, "Nombre");

                Usuario usuario = new Usuario();
                usuario.setCedula(cedula);
                usuario.setNombre(nombre);
                usuario.setCorreo(correo);
                agregar(usuario);

                JOptionPane.showMessageDialog(usuarioView, "Usuario registrado con éxito.");
                usuarioView.limpiar();
                usuarioView.bloquear();
                usuarioView.getTxtCedula().setEditable(true);
                listarUsuarios();
                usuarioView.getBtnCrear().setText("Crear");
                creandoNuevo = false;
            } catch (ValidacionException e) {
                JOptionPane.showMessageDialog(usuarioView, e.getMessage());
            }
        }
    }
    
    private void configurarEventosBusquedaUsuario(){
        usuarioView.getBtnBuscar().addActionListener(e -> buscarUsuario());
    }
    
    private void buscarUsuario(){
        String cedula = usuarioView.getTxtCedula().getText();
        Usuario usuario = buscarPorCodigo(cedula);

        if (usuario != null) {
            usuarioView.getTxtNombre().setText(usuario.getNombre());
            usuarioView.getTxtCorreo().setText(usuario.getCorreo());
            usuarioView.modoActualizar();
        } else {
            JOptionPane.showMessageDialog(usuarioView, "Usuario no encontrado");
        }
    }
    
    private void configurarEventosActualizarUsuario(){
        usuarioView.getBtnActualizar().addActionListener(e -> actualizarUsuario());
    }
    
    private void actualizarUsuario(){
        String cedula = usuarioView.getTxtCedula().getText();
        Usuario existente = buscarPorCodigo(cedula);

        if (existente == null) {
            JOptionPane.showMessageDialog(usuarioView, "No existe un usuario con esa cédula.");
            return;
        }

        existente.setNombre(usuarioView.getTxtNombre().getText());
        existente.setCorreo(usuarioView.getTxtCorreo().getText());
        actualizar(existente);

        JOptionPane.showMessageDialog(usuarioView, "Usuario actualizado.");
        usuarioView.limpiar();
        usuarioView.bloquear();
        usuarioView.getTxtCedula().setEditable(true);
        listarUsuarios();
    }
    
    private void configurarEventosEliminarUsuario(){
        usuarioView.getBtnEliminar().addActionListener(e -> eliminarUsuario());
    }
    
    private void eliminarUsuario(){
        String cedula = usuarioView.getTxtCedula().getText();
        Usuario existente = buscarPorCodigo(cedula);

        if (existente == null) {
            JOptionPane.showMessageDialog(usuarioView, "No existe un usuario con esa cédula.");
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(usuarioView,
                "¿Eliminar este usuario?", "Confirmar", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            eliminar(cedula);
            JOptionPane.showMessageDialog(usuarioView, "Usuario eliminado.");
            usuarioView.limpiar();
            usuarioView.bloquear();
            usuarioView.getTxtCedula().setEditable(true);
            listarUsuarios();
        }
    }
    
    private void configurarEventosListarUsuario(){
        usuarioView.getBtnListar().addActionListener(e -> listarUsuarios());
    }
    
    private void listarUsuarios() {
        List<Usuario> usuarios = listar();
        usuarioView.listar(usuarios);
    }
    
    private void configurarEventosLimpiarUsuario(){
        usuarioView.getBtnLimpiar().addActionListener(e -> limpiarUsuario());
    }
    
    private void limpiarUsuario(){
        usuarioView.limpiar();
        usuarioView.bloquear();
        usuarioView.getTxtCedula().setEditable(true);
        usuarioView.getBtnCrear().setText("Crear");
        creandoNuevo = false; 
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
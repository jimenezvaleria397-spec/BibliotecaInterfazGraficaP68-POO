    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.controllers;
import ec.edu.ups.biblioteca.dao.AutorDAO;
import ec.edu.ups.biblioteca.excepciones.ValidacionException;
import ec.edu.ups.biblioteca.excepciones.Validador;
import ec.edu.ups.biblioteca.models.Autor;
import ec.edu.ups.biblioteca.views.AutorView;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author jimen
 */
public class AutorController {
    private AutorDAO autorDAO;
    private AutorView autorView;

    public AutorController(AutorDAO autorDAO, AutorView autorView) {
        this.autorDAO = autorDAO;
        this.autorView = autorView;
        configurarEventos();
        listarAutores();
    }

    public void configurarEventos(){
        configurarEventosCrearAutor();
        configurarEventosBusquedaAutor();
        configurarEventosActualizarAutor();
        configurarEventosEliminarAutor();
        configurarEventosListarAutor();
        configurarEventosLimpiarAutor();
    }

    private void configurarEventosCrearAutor() {
        autorView.getBtnCrearAutor().addActionListener(e -> crearAutor());
    }

    public void crearAutor(){
        try {
            String codigoAutor = autorView.getTxtCodigoAutor().getText();
            String nombre = autorView.getTxtNombre().getText();
            String nacionalidad = autorView.getTxtNacionalidad().getText();
            java.util.Date fechaSeleccionada = autorView.getDateChooserFechaNac().getDate();

            Validador.validarNoVacio(codigoAutor, "Código");
            Validador.validarSoloNumeros(codigoAutor, "Código");
            Validador.validarNoVacio(nombre, "Nombre");
            Validador.validarLongitud(nombre, 2, 50, "Nombre");

            if (fechaSeleccionada == null) {
                JOptionPane.showMessageDialog(autorView, "Debes seleccionar una fecha de nacimiento.");
                return;
            }
            LocalDate fechaNac = fechaSeleccionada.toInstant()
                    .atZone(java.time.ZoneId.systemDefault()).toLocalDate();

            if (buscarPorCodigo(codigoAutor) != null) {
                JOptionPane.showMessageDialog(autorView, "Ya existe un autor con ese código.");
                return;
            }

            Autor autorAc = new Autor(nombre, nacionalidad, codigoAutor, fechaNac, new ArrayList<>());
            agregar(autorAc);
            JOptionPane.showMessageDialog(autorView, "Autor registrado con éxito.");
            autorView.limpiarCampos();
            listarAutores();
        } catch (ValidacionException e) {
            JOptionPane.showMessageDialog(autorView, e.getMessage());
        }
    }

    private void configurarEventosBusquedaAutor() {
        autorView.getBtnBuscarAutor().addActionListener(e -> buscarAutor());
    }

    public void buscarAutor(){
        String codigo = autorView.getTxtCodigoAutor().getText();
        Autor encontrado = buscarPorCodigo(codigo);
        if (encontrado != null) {
            autorView.mostrarAutorEnCampos(encontrado);
        } else {
            JOptionPane.showMessageDialog(autorView, "No se encontró un autor con ese código.");
            autorView.limpiarCampos();
        }
    }

    private void configurarEventosActualizarAutor(){
        autorView.getBtnActualizarAutor().addActionListener(e -> actualizarAutor());
    }

    private void actualizarAutor(){
        String codigo = autorView.getTxtCodigoAutor().getText();
        Autor existente = buscarPorCodigo(codigo);

        if (existente == null) {
            JOptionPane.showMessageDialog(autorView, "No existe un autor con ese código.");
            return;
        }

        java.util.Date fechaSeleccionada = autorView.getDateChooserFechaNac().getDate();
        if (fechaSeleccionada == null) {
            JOptionPane.showMessageDialog(autorView, "Debes seleccionar una fecha de nacimiento.");
            return;
        }

        existente.setNombre(autorView.getTxtNombre().getText());
        existente.setNacionalidad(autorView.getTxtNacionalidad().getText());
        existente.setFechadeNac(fechaSeleccionada.toInstant()
                .atZone(java.time.ZoneId.systemDefault()).toLocalDate());
        actualizar(existente);

        JOptionPane.showMessageDialog(autorView, "Autor actualizado con éxito.");
        autorView.limpiarCampos();
        listarAutores();
    }

    
    private void configurarEventosEliminarAutor(){
        autorView.getBtnEliminarAutor().addActionListener(e -> eliminarAutor());
    }

    private void eliminarAutor(){
        String codigo = autorView.getTxtCodigoAutor().getText();
        Autor existente = buscarPorCodigo(codigo);

        if (existente == null) {
            JOptionPane.showMessageDialog(autorView, "No existe un autor con ese código.");
            return;
        }
        int confirmacion = JOptionPane.showConfirmDialog(autorView,
                "¿Eliminar este autor?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            eliminar(codigo);
            JOptionPane.showMessageDialog(autorView, "Autor eliminado.");
            autorView.limpiarCampos();
            listarAutores();
        }
    }

    private void configurarEventosListarAutor(){
        autorView.getBtnListarAutor().addActionListener(e -> listarAutores());
    }

    private void configurarEventosLimpiarAutor(){
        autorView.getBtnLimpiarCampos().addActionListener(e -> autorView.limpiarCampos());
    }

    private void listarAutores(){
        List<Autor> autores = listar();
        autorView.actualizarTabla(autores);
    }

    public void agregar(Autor autor) { // métodos wrapper
        autorDAO.agregar(autor);
    }

    public Autor buscarPorCodigo(String codigo) {
        return autorDAO.buscarPorCodigo(codigo);
    }

    public void actualizar(Autor autor) {
        autorDAO.actualizar(autor);
    }

    public void eliminar(String codigo) {
        autorDAO.eliminar(codigo);
    }

    public List<Autor> listar() {
        return autorDAO.listar();
    }
}

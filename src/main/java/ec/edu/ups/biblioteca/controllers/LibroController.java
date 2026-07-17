/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.controllers;
import ec.edu.ups.biblioteca.dao.LibroDAO;
import ec.edu.ups.biblioteca.enumeraciones.CategoriaLibro;
import ec.edu.ups.biblioteca.excepciones.ValidacionException;
import ec.edu.ups.biblioteca.excepciones.Validador;
import ec.edu.ups.biblioteca.models.Autor;
import ec.edu.ups.biblioteca.models.Libro;
import ec.edu.ups.biblioteca.views.LibroView;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author jimen
 */

public class LibroController {
    private LibroDAO libroDAO;
    private LibroView libroView;
    private AutorController autorController;
    private EjemplarLibroController ejemplarLibroController;
    private boolean creandoNuevo = false;

    public LibroController(LibroDAO libroDAO, LibroView libroView, AutorController autorController, EjemplarLibroController ejemplarLibroController) {
        this.libroDAO = libroDAO;
        this.libroView = libroView;
        this.autorController = autorController;
        this.ejemplarLibroController = ejemplarLibroController;
        configurarEventos();
        cargarAutoresCombo();
        listarLibros();
    }
    
    public void configurarEventos(){
        configurarEventosCrearLibro();
        configurarEventosBusquedaLibro();
        configurarEventosActualizarLibro();
        configurarEventosEliminarLibro();
        configurarEventosListarLibros();
        configurarEventosLimpiarLibro();
        configurarEventosSeleccionTabla();
         configurarEventosComboAutores(); 
    }
    private void configurarEventosComboAutores() {
    libroView.getCbxAutores().addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
        @Override
        public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent e) {
            cargarAutoresCombo();
        }
        @Override
        public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent e) {}
        @Override
        public void popupMenuCanceled(javax.swing.event.PopupMenuEvent e) {}
    });
}
    
    private void configurarEventosCrearLibro(){
        libroView.getBtnCrear().addActionListener(e -> crearLibro());
    }
    
    public void crearLibro(){
        if (!creandoNuevo) {
            libroView.limpiar();
            libroView.habilitarCampos();
            libroView.getTxtCodigo().setEditable(true);
            libroView.getBtnCrear().setText("Guardar");
            cargarAutoresCombo(); 
            creandoNuevo = true;
        } else {
            try {
                String codigo = libroView.getTxtCodigo().getText();
                String titulo = libroView.getTxtTitulo().getText();
                Autor autorSeleccionado = (Autor) libroView.getCbxAutores().getSelectedItem();
                String editorial = libroView.getTxtEditorial().getText();
                CategoriaLibro categoria = (CategoriaLibro)libroView.getCbxCategoria().getSelectedItem();
                int anio = libroView.getJYearChooser1().getYear();
                int cantidadEjemplares = Validador.validarEntero(libroView.getTxtEjemplares().getText(), "Ejemplares");

                Validador.validarNoVacio(codigo, "Código");
                Validador.validarSoloNumeros(codigo, "Código");
                Validador.validarNoVacio(titulo, "Título");
                if (autorSeleccionado == null) {
                    JOptionPane.showMessageDialog(libroView, "Debes seleccionar un autor.");
                    return;
                }

                Libro libro = new Libro();
                libro.setCodigo(codigo);
                libro.setTitulo(titulo);
                libro.setAutor(autorSeleccionado);
                libro.setEditorial(editorial);
                libro.setCategoria(categoria);
                libro.setAnio(anio);

                registrarLibro(libro, cantidadEjemplares);

                JOptionPane.showMessageDialog(libroView, "Libro registrado con éxito.");
                listarLibros();
                libroView.limpiar();
                libroView.bloquearCampos();
                libroView.getBtnCrear().setText("Nuevo");
                creandoNuevo = false;
            } catch (ValidacionException e) {
                JOptionPane.showMessageDialog(libroView, e.getMessage());
            }   
        }    
    }
    
    private void configurarEventosBusquedaLibro(){
        libroView.getBtnBuscar().addActionListener(e -> busquedaLibro());
    }
    
    public void busquedaLibro(){
        String codigo = libroView.getTxtCodigo().getText();
        Libro libro = buscarPorCodigo(codigo);

        if (libro != null) {
            libroView.mostrarLibroCampos(libro);
            int disponibles = ejemplarLibroController.contarDisponibles(codigo);
            libroView.getTxtDisponibles().setText(String.valueOf(disponibles));
            libroView.habilitarCampos();
        } else {
            JOptionPane.showMessageDialog(libroView, "No encontrado");
        }
    }
    
    private void configurarEventosActualizarLibro(){
        libroView.getBtnActualizar().addActionListener(e -> actualizarLibro());
    }
    
    public void actualizarLibro(){
        int anio = libroView.getJYearChooser1().getYear();

        Libro libro = new Libro();
        libro.setCodigo(libroView.getTxtCodigo().getText());
        libro.setTitulo(libroView.getTxtTitulo().getText());
        libro.setAutor((Autor) libroView.getCbxAutores().getSelectedItem());
        libro.setEditorial(libroView.getTxtEditorial().getText());
        libro.setCategoria((CategoriaLibro) libroView.getCbxCategoria().getSelectedItem());
        libro.setAnio(anio);

        actualizar(libro);
        listarLibros();
        libroView.limpiar();
    }
    
    private void configurarEventosEliminarLibro(){
        libroView.getBtnEliminar().addActionListener(e -> eliminarLibro());
    }
    
    public void eliminarLibro(){
        String codigo = libroView.getTxtCodigo().getText();
        eliminar(codigo);
        listarLibros();
        libroView.limpiar();
    }
    
    private void configurarEventosListarLibros(){
        libroView.getBtnListar().addActionListener(e -> listarLibros());
    }

    public void listarLibros(){
        List<Libro> libros = listar();
        List<Object[]> filas = new ArrayList<>();
        for (Libro l : libros) {
            int disponibles = ejemplarLibroController.contarDisponibles(l.getCodigo());
            filas.add(new Object[]{
                l.getCodigo(), l.getTitulo(), l.getAutor(),
                l.getEditorial(), l.getCategoria(), l.getAnio(), disponibles
            });
        }
        libroView.listarLibros(filas);
    }
    
    private void configurarEventosLimpiarLibro(){
        libroView.getBtnLimpiar().addActionListener(e -> libroView.limpiar());
    }
    
    public void cargarAutoresCombo(){
        List<Autor> autores = autorController.listar();
        libroView.cargarAutores(autores);
    }
    
    private void configurarEventosSeleccionTabla() {
        libroView.getTblLibros().getSelectionModel().addListSelectionListener(evt -> {
            if (!evt.getValueIsAdjusting() && libroView.getTblLibros().getSelectedRow() != -1) {
                int fila = libroView.getTblLibros().getSelectedRow();
                String codigo = libroView.getTblLibros().getValueAt(fila, 0).toString();
                int disponibles = ejemplarLibroController.contarDisponibles(codigo);
                libroView.mostrarDisponibles(disponibles);
            }
        });
    }
    
    private void mostrarDisponiblesDeSeleccion() {
        int fila = libroView.getTblLibros().getSelectedRow();
        String codigo = libroView.getTblLibros().getValueAt(fila, 0).toString();
        Libro libro = buscarPorCodigo(codigo);
        int disponibles = ejemplarLibroController.contarDisponibles(codigo);
        libroView.mostrarDisponibles(disponibles);
    }
    
    public void agregar(Libro libro) {
        libroDAO.agregar(libro);
    }

    public Libro buscarPorCodigo(String codigo) {
        return libroDAO.buscarPorCodigo(codigo);
    }

    public void actualizar(Libro libro) {
        libroDAO.actualizar(libro);
    }

    public void eliminar(String codigo) {
        libroDAO.eliminar(codigo);
    }

    public List<Libro> listar() {
        return libroDAO.listar();
    }
    
    public void registrarLibro(Libro libro, int cantidadEjemplares) {
        agregar(libro);
        ejemplarLibroController.crearEjemplares(libro, cantidadEjemplares);
    }
}
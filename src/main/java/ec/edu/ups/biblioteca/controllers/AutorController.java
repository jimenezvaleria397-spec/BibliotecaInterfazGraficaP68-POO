    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.controllers;
import ec.edu.ups.biblioteca.dao.AutorDAO;
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
    }
    
    public void configurarEventos(){
        configurarEventosCrearAutor();
        configurarEventosBusquedaAutor();
        configurarEventosActualizarAutor();
        configurarEventosEliminarAutor();
        configurarEventosListarAutor();
    }
    
    private void configurarEventosCrearAutor() {
        autorView.getBtnCrearAutor().addActionListener(e -> crearAutor());
    }
    
    public void crearAutor(){
        String codigoAutor = autorView.getTxtCodigoAutor().getText();
        String nombre = autorView.getTxtNombre().getText();
        String nacionalidad = autorView.getTxtNacionalidad().getText();
        String fechaTexto = autorView.getTxtFechaNac().getText();
        
        if (codigoAutor.isEmpty() || nombre.isEmpty()) {
            JOptionPane.showMessageDialog(autorView, "Código y nombre son obligatorios.");
            return;
        }
        try {
            LocalDate fechaNac = LocalDate.parse(fechaTexto);
            Autor autorAc = new Autor(nombre, nacionalidad, codigoAutor, fechaNac, new ArrayList<>());
            agregar(autorAc);
            JOptionPane.showMessageDialog(autorView, "Autor registrado con éxito.");
            autorView.limpiarCampos();
            listarAutores();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(autorView, "Fecha inválida. Use el formato AAAA-MM-DD.");
        }
    }

    private void configurarEventosBusquedaAutor() {
        autorView.getBtnBuscarAutor().addActionListener(e -> buscarAutor());
    }
    
    public void buscarAutor(){
        String codigo = autorView.getTxtCodigoAutor().getText();
        Autor encontrado = buscarPorCodigo(codigo);   // ← usa el wrapper público
        if (encontrado != null) {
            autorView.mostrarAutorEnCampos(encontrado);
        } else {
            JOptionPane.showMessageDialog(autorView, "No se encontró un autor con ese código.");
            autorView.limpiarCampos();
        }
    }
        
    
    private void configurarEventosActualizarAutor(){
        
    }
    
    private void configurarEventosEliminarAutor(){
        
    }
    
    private void configurarEventosListarAutor(){
        
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
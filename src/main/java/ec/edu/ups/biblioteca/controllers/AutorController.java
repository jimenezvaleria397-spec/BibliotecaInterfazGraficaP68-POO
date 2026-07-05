    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.controllers;
import ec.edu.ups.biblioteca.dao.AutorDAO;
import ec.edu.ups.biblioteca.models.Autor;
import java.util.List;
/**
 *
 * @author jimen
 */
public class AutorController {
    private AutorDAO autorDAO;

    public AutorController() {
        autorDAO = new AutorDAO();
    }

    public void agregar(Autor autor) {
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
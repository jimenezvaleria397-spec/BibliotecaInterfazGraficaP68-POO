/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.controllers;

import ec.edu.ups.biblioteca.dao.EjemplarLibroDAO;
import ec.edu.ups.biblioteca.models.EjemplarLibro;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class EjemplarLibroController {
    private EjemplarLibroDAO ejemplarDAO;

    public EjemplarLibroController() {
        this.ejemplarDAO = EjemplarLibroDAO.getEjemplarLibroDAO();  
    }

    public EjemplarLibroController(EjemplarLibroDAO ejemplarDAO) {
        this.ejemplarDAO = ejemplarDAO;
    }

    public void setEjemplarDAO(EjemplarLibroDAO ejemplarDAO) {
        this.ejemplarDAO = ejemplarDAO;
    }

    public EjemplarLibroDAO getEjemplarDAO() {
        return ejemplarDAO;
    }
    
    public List<EjemplarLibro> listarDisponibles() {
        return ejemplarDAO.listarDisponibles();
    }

    public List<EjemplarLibro> listarDisponiblesPorLibro(String codigoLibro) {
        return ejemplarDAO.listarDisponiblesPorLibro(codigoLibro);
    }

    public int contarDisponibles(String codigoLibro) {
        return ejemplarDAO.contarDisponibles(codigoLibro);
    }

    public int contarTotal(String codigoLibro) {
        return ejemplarDAO.contarTotal(codigoLibro);
    }
    
}

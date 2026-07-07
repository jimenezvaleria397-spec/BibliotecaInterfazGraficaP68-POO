/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.controllers;

import ec.edu.ups.biblioteca.dao.EjemplarLibroDAO;
import ec.edu.ups.biblioteca.models.EjemplarLibro;
import java.util.List;
import java.util.stream.Collectors;

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
    
    public int contarDisponibles(String codigoLibro) {
        return (int) ejemplarDAO.listar().stream()
            .filter(e -> e.getLibro().getCodigo().equals(codigoLibro))
            .filter(EjemplarLibro::isDisponible)
            .count();   
    }
    
    public int contarTotal(String codigoLibro) {
        return (int) ejemplarDAO.listar().stream()
            .filter(e -> e.getLibro().getCodigo().equals(codigoLibro))
            .count();
    }
    
    public List<EjemplarLibro> listarDisponibles() {
        return ejemplarDAO.listar( ).stream()
                .filter(EjemplarLibro::isDisponible)
                .collect(Collectors.toList());
    }
    
    public List<EjemplarLibro> listarDisponiblesPorLibro(String codigoLibro) {
        return ejemplarDAO.listar().stream()
                .filter(e -> e.getLibro().getCodigo().equals(codigoLibro))
                .filter(EjemplarLibro::isDisponible)
                .collect(Collectors.toList());
    }
}

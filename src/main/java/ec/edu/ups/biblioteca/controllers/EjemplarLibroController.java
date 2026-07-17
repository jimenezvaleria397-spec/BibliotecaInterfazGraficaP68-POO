/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.controllers;

import ec.edu.ups.biblioteca.dao.EjemplarLibroDAO;
import ec.edu.ups.biblioteca.enumeraciones.EstadoEjemplarLibro;
import ec.edu.ups.biblioteca.models.EjemplarLibro;
import ec.edu.ups.biblioteca.models.Libro;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class EjemplarLibroController {
    private EjemplarLibroDAO ejemplarDAO;

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
        if (ec.edu.ups.biblioteca.views.MenuBibliotecaView.USAR_ARCHIVOS) {
            List<EjemplarLibro> lista = new ec.edu.ups.biblioteca.dao.archivos.EjemplarLibroDAOArchivo().listar();
            List<EjemplarLibro> filtrada = new java.util.ArrayList<>();
            for (EjemplarLibro e : lista) {
                if (e.getEstadoEjemplar() == ec.edu.ups.biblioteca.enumeraciones.EstadoEjemplarLibro.DISPONIBLE) {
                    filtrada.add(e);
                }
            }
            return filtrada;
        } else {
            return ejemplarDAO.listarDisponibles();
        }
    }

    public List<EjemplarLibro> listarDisponiblesPorLibro(String codigoLibro) {
        if (ec.edu.ups.biblioteca.views.MenuBibliotecaView.USAR_ARCHIVOS) {
            List<EjemplarLibro> lista = new ec.edu.ups.biblioteca.dao.archivos.EjemplarLibroDAOArchivo().listar();
            List<EjemplarLibro> filtrada = new java.util.ArrayList<>();
            for (EjemplarLibro e : lista) {
                if (e.getLibro().getCodigo().equals(codigoLibro) && e.getEstadoEjemplar() == ec.edu.ups.biblioteca.enumeraciones.EstadoEjemplarLibro.DISPONIBLE) {
                    filtrada.add(e);
                }
            }
            return filtrada;
        } else {
            return ejemplarDAO.listarDisponiblesPorLibro(codigoLibro);
        }
    }

    public int contarDisponibles(String codigoLibro) {
        if (ec.edu.ups.biblioteca.views.MenuBibliotecaView.USAR_ARCHIVOS) {
            int contador = 0;
            List<EjemplarLibro> lista = new ec.edu.ups.biblioteca.dao.archivos.EjemplarLibroDAOArchivo().listar();
            for (EjemplarLibro e : lista) {
                if (e.getLibro().getCodigo().equals(codigoLibro) && e.getEstadoEjemplar() == ec.edu.ups.biblioteca.enumeraciones.EstadoEjemplarLibro.DISPONIBLE) {
                    contador++;
                }
            }
            return contador;
        } else {
            return ejemplarDAO.contarDisponibles(codigoLibro);
        }
    }

    public int contarTotal(String codigoLibro) {
        if (ec.edu.ups.biblioteca.views.MenuBibliotecaView.USAR_ARCHIVOS) {
            int contador = 0;
            List<EjemplarLibro> lista = new ec.edu.ups.biblioteca.dao.archivos.EjemplarLibroDAOArchivo().listar();
            for (EjemplarLibro e : lista) {
                if (e.getLibro().getCodigo().equals(codigoLibro)) {
                    contador++;
                }
            }
            return contador;
        } else {
            return ejemplarDAO.contarTotal(codigoLibro);
        }
    }
    
    public void actualizar(EjemplarLibro ejemplar) {
        if (ec.edu.ups.biblioteca.views.MenuBibliotecaView.USAR_ARCHIVOS) {
            new ec.edu.ups.biblioteca.dao.archivos.EjemplarLibroDAOArchivo().actualizar(ejemplar.getCodigoBarras());
        } else {
            ejemplarDAO.actualizar(ejemplar);
        }
    }
    
    public void crearEjemplares(Libro libro, int cantidad) {
        for (int i = 1; i <= cantidad; i++) {
            String codigoBarras = libro.getCodigo() + "-" + i;
            EjemplarLibro ejemplar = new EjemplarLibro(codigoBarras, "Estante A", libro, ec.edu.ups.biblioteca.enumeraciones.EstadoEjemplarLibro.DISPONIBLE);
            
            if (ec.edu.ups.biblioteca.views.MenuBibliotecaView.USAR_ARCHIVOS) {
                new ec.edu.ups.biblioteca.dao.archivos.EjemplarLibroDAOArchivo().agregar(ejemplar);
            } else {
                ejemplarDAO.agregar(ejemplar);
            }
        }
    }
    
}

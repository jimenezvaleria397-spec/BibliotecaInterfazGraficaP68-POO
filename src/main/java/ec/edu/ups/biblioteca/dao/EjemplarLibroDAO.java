/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.dao;

import ec.edu.ups.biblioteca.enumeraciones.EstadoEjemplarLibro;
import ec.edu.ups.biblioteca.models.EjemplarLibro;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class EjemplarLibroDAO implements DAO<EjemplarLibro>{
    private List<EjemplarLibro> ejemplarDAO;
    private static EjemplarLibroDAO ejemplarLibroDAO;
    
    public EjemplarLibroDAO(){
        this.ejemplarDAO = new ArrayList<>();
    }

    public List<EjemplarLibro> getEjemplarDAO() {
        return ejemplarDAO;
    }

    public void setEjemplarDAO(List<EjemplarLibro> ejemplarDAO) {
        this.ejemplarDAO = ejemplarDAO;
    }
    
    public static EjemplarLibroDAO getEjemplarLibroDAO() {
        if (ejemplarLibroDAO == null) {
            ejemplarLibroDAO = new EjemplarLibroDAO();
        }
        return ejemplarLibroDAO;
    }
    
    @Override
    public void agregar(EjemplarLibro objeto) {
        ejemplarDAO.add(objeto);
    }

    @Override
    public EjemplarLibro buscarPorCodigo(String codigo) {
        for (EjemplarLibro eL : ejemplarDAO) {
            if (eL.getCodigoBarras().equals(codigo)) {
                return eL;
            }
        }
        return null;
    }

    @Override
    public void actualizar(String codigo) {
    }
    public void actualizar(EjemplarLibro ejemplarAC){
        EjemplarLibro existente = buscarPorCodigo(ejemplarAC.getCodigoBarras());
        if(existente != null){
            existente.setCodigoBarras(ejemplarAC.getCodigoBarras());
            existente.setUbicacion(ejemplarAC.getUbicacion());
        }
    }

    @Override
    public void eliminar(String codigo) {
        EjemplarLibro existente = buscarPorCodigo(codigo);
        if(existente != null){
            ejemplarDAO.remove(existente);
        }
    }

    @Override
    public List<EjemplarLibro> listar() {
        return ejemplarDAO;
    }
    
    public List<EjemplarLibro> listarDisponibles() {
    List<EjemplarLibro> resultado = new ArrayList<>();
        for (EjemplarLibro e : listar()) {
            if (e.getEstadoEjemplar() == EstadoEjemplarLibro.DISPONIBLE) {
                resultado.add(e);
            }
        }
        return resultado;
    }

    public List<EjemplarLibro> listarDisponiblesPorLibro(String codigoLibro) {
        List<EjemplarLibro> resultado = new ArrayList<>();
        for (EjemplarLibro e : listar()) {
            if (e.getLibro().getCodigo().equals(codigoLibro) 
                    && e.getEstadoEjemplar() == EstadoEjemplarLibro.DISPONIBLE) {
                resultado.add(e);
            }
        }
        return resultado;
    }

    public int contarDisponibles(String codigoLibro) {
        int contador = 0;
        for (EjemplarLibro e : listar()) {
            if (e.getLibro().getCodigo().equals(codigoLibro) 
                    && e.getEstadoEjemplar() == EstadoEjemplarLibro.DISPONIBLE) {
                contador++;
            }
        }
        return contador;
    }

    public int contarTotal(String codigoLibro) {
        int contador = 0;
        for (EjemplarLibro e : listar()) {
            if (e.getLibro().getCodigo().equals(codigoLibro)) {
                contador++;
            }
        }
        return contador;
    }
    
}

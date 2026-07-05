/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.dao;

import ec.edu.ups.biblioteca.models.EjemplarLibro;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class EjemplarLibroDAO implements DAO<EjemplarLibro>{
    private List<EjemplarLibro> ejemplarLibro;
    
    public EjemplarLibroDAO(){
        this.ejemplarLibro = new ArrayList<>();
    }

    @Override
    public void agregar(EjemplarLibro objeto) {
        ejemplarLibro.add(objeto);
    }

    @Override
    public EjemplarLibro buscarPorCodigo(String codigo) {
        for (EjemplarLibro eL : ejemplarLibro) {
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
            ejemplarLibro.remove(existente);
        }
    }

    @Override
    public List<EjemplarLibro> listar() {
        return ejemplarLibro;
    }
    
}

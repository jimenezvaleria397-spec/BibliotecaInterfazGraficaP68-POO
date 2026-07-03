/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.dao;

import ec.edu.ups.biblioteca.models.Autor;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author USERo
 */
public class AutorDAO implements DAO<Autor>{
    private List<Autor> autores;
    
    public AutorDAO(){
        this.autores = new ArrayList<>();
    }

    @Override
    public void agregar(Autor objeto) {
        autores.add(objeto);
    }

    @Override
    public Autor buscarPorCodigo(String codigo) {
        for (Autor autor : autores) {
            if (autor.getCodigoAutor().equals(codigo)) {
                return autor;
            }
        }
        return null;
    }

    @Override
    public void actualizar(String codigo) {
    }
    public void actualizar(Autor autorAc){
        Autor existente = buscarPorCodigo(autorAc.getCodigoAutor());
        if(existente != null){
            existente.setNombre(autorAc.getNombre());
            existente.setFechadeNac(autorAc.getFechadeNac());
            existente.setNacionalidad(autorAc.getNacionalidad());
            existente.setTitulos(autorAc.getTitulos());
        }
        
    }

    @Override
    public void eliminar(String codigo) {
        Autor existente = buscarPorCodigo(codigo);
        if(existente != null){
            autores.remove(existente);
        }
    }

    @Override
    public List<Autor> listar() {
        return autores;
    }
}

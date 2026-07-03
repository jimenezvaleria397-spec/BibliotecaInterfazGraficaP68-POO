/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.dao;

import ec.edu.ups.biblioteca.models.Bibliotecario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class BibliotecarioDAO implements DAO<Bibliotecario>{
    private List<Bibliotecario> bibliotecarios;
    
    public BibliotecarioDAO(){
        this.bibliotecarios = new ArrayList<>();
    }

    @Override
    public void agregar(Bibliotecario objeto) {
        bibliotecarios.add(objeto);
    }

    @Override
    public Bibliotecario buscarPorCodigo(String codigo) {
        for (Bibliotecario bibliotecario : bibliotecarios) {
            if(String.valueOf(bibliotecario.getIdBibliotecario()).equals(codigo)){
                return bibliotecario;
            }
            
        }
        return null;
    }

    @Override
    public void actualizar(String codigo) {
    }
    public void actualizar(Bibliotecario bAC){
        Bibliotecario existente = buscarPorCodigo(String.valueOf(bAC.getIdBibliotecario()));
        if(existente != null){
            existente.setEmail(bAC.getEmail());
        }
    }

    @Override
    public void eliminar(String codigo) {
        Bibliotecario existente = buscarPorCodigo(codigo);
        if(existente != null){
            bibliotecarios.remove(existente);
        }
    }

    @Override
    public List<Bibliotecario> listar() {
        return bibliotecarios;
    }
    
}

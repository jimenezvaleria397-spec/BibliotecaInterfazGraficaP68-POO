/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.dao;

import java.util.List;

/**
 *
 * @author USER
 */
public interface DAO<T> {
    void agregar(T objeto);
    T buscarPorCodigo(String codigo);
    void actualizar(String codigo);
    void eliminar(String codigo);
    List<T> listar();
    
 
}

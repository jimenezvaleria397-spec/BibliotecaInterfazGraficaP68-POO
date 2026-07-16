/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.models;

import ec.edu.ups.biblioteca.enumeraciones.CategoriaLibro;
import java.util.Objects;

/**
 *
 * @author jimen
 */
public class Libro {

    private String codigo;
    private String titulo;
    private Autor autor;
    private String editorial;
    private CategoriaLibro categoria;
    private int anio;
    //private int ejemplares;

    public Libro() {
    }

    public Libro(String codigo, String titulo, Autor autor, String editorial,
                CategoriaLibro categoria, int anio) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.categoria = categoria;
        this.anio = anio;
        //this.ejemplares = ejemplares;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public CategoriaLibro getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaLibro categoria) {
        this.categoria = categoria;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.codigo);
        hash = 31 * hash + Objects.hashCode(this.titulo);
        hash = 31 * hash + Objects.hashCode(this.autor);
        hash = 31 * hash + Objects.hashCode(this.editorial);
        hash = 31 * hash + Objects.hashCode(this.categoria);
        hash = 31 * hash + this.anio;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Libro other = (Libro) obj;
        if (this.anio != other.anio) {
            return false;
        }
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        if (!Objects.equals(this.editorial, other.editorial)) {
            return false;
        }
        if (!Objects.equals(this.autor, other.autor)) {
            return false;
        }
        return this.categoria == other.categoria;
    }
    
    


    @Override
    public String toString() {
        return codigo +"-"  + titulo ;
    }
    
}
    

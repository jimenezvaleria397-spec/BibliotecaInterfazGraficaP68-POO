/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.models;
import java.util.Objects;

/**
 *
 * @author jimen
 */
public class EjemplarLibro {
    private String codigoBarras;
    private String ubicacion;
    private Libro libro; // datos del libro ya creado
    private boolean disponible;
    
    public EjemplarLibro() {
    }

    public EjemplarLibro(String codigoBarras, String ubicacion, 
            Libro libro, boolean disponible) {
        this.codigoBarras = codigoBarras;
        this.ubicacion = ubicacion;
        this.libro = libro;
        this.disponible = disponible;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public boolean isDisponible() {
        return disponible;
    }
    
    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.codigoBarras);
        hash = 97 * hash + Objects.hashCode(this.ubicacion);
        hash = 97 * hash + Objects.hashCode(this.libro);
        hash = 97 * hash + (this.disponible ? 1 : 0);
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
        final EjemplarLibro other = (EjemplarLibro) obj;
        if (this.disponible != other.disponible) {
            return false;
        }
        if (!Objects.equals(this.codigoBarras, other.codigoBarras)) {
            return false;
        }
        if (!Objects.equals(this.ubicacion, other.ubicacion)) {
            return false;
        }
        return Objects.equals(this.libro, other.libro);
    }

    @Override
    public String toString() {
        return  ubicacion  + libro  + disponible;
    }
}

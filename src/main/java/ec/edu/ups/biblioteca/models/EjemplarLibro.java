/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.models;
import ec.edu.ups.biblioteca.enumeraciones.EstadoEjemplarLibro;
import java.util.Objects;

/**
 *
 * @author jimen
 */
public class EjemplarLibro {
    private String codigoBarras;
    private String ubicacion;
    private Libro libro; // datos del libro ya creado
    private EstadoEjemplarLibro estadoEjemplar;
    
    public EjemplarLibro() {
    }

    public EjemplarLibro(String codigoBarras, String ubicacion, Libro libro, EstadoEjemplarLibro estadoEjemplar) {
        this.codigoBarras = codigoBarras;
        this.ubicacion = ubicacion;
        this.libro = libro;
        this.estadoEjemplar = estadoEjemplar;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public EstadoEjemplarLibro getEstadoEjemplar() {
        return estadoEjemplar;
    }

    public void setEstadoEjemplar(EstadoEjemplarLibro estadoEjemplar) {
        this.estadoEjemplar = estadoEjemplar;
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
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.codigoBarras);
        hash = 47 * hash + Objects.hashCode(this.ubicacion);
        hash = 47 * hash + Objects.hashCode(this.libro);
        hash = 47 * hash + Objects.hashCode(this.estadoEjemplar);
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
        if (!Objects.equals(this.codigoBarras, other.codigoBarras)) {
            return false;
        }
        if (!Objects.equals(this.ubicacion, other.ubicacion)) {
            return false;
        }
        if (!Objects.equals(this.libro, other.libro)) {
            return false;
        }
        return this.estadoEjemplar == other.estadoEjemplar;  
    }

    @Override
    public String toString() {
        return  "E_l= " + libro + " - " + estadoEjemplar;
    }
}

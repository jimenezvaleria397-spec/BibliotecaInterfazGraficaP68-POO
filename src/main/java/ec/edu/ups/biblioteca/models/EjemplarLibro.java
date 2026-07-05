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

    public EjemplarLibro() {
    }

    public EjemplarLibro(String codigoBarras, String ubicacion) {
        this.codigoBarras = codigoBarras;
        this.ubicacion = ubicacion;
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
        hash = 79 * hash + Objects.hashCode(this.codigoBarras);
        hash = 79 * hash + Objects.hashCode(this.ubicacion);
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
        return Objects.equals(this.ubicacion, other.ubicacion);
    }

    @Override
    public String toString() {
        return "EjemplarLibro{" + "codigoBarras=" + codigoBarras + ", ubicacion=" + ubicacion + '}';
    }

}

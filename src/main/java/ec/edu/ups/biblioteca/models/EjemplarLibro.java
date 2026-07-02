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
    private String estadoL;
    private String ubicacion;

    public EjemplarLibro() {
    }

    public EjemplarLibro(String codigoBarras, String estadoL, String ubicacion) {
        this.codigoBarras = codigoBarras;
        this.estadoL = estadoL;
        this.ubicacion = ubicacion;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getEstadoL() {
        return estadoL;
    }

    public void setEstadoL(String estadoL) {
        this.estadoL = estadoL;
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
        hash = 47 * hash + Objects.hashCode(this.codigoBarras);
        hash = 47 * hash + Objects.hashCode(this.estadoL);
        hash = 47 * hash + Objects.hashCode(this.ubicacion);
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
        if (!Objects.equals(this.estadoL, other.estadoL)) {
            return false;
        }
        return Objects.equals(this.ubicacion, other.ubicacion);
    }
    
    

    @Override
    public String toString() {
        return "EjemplarLibro{" + "codigoBarras=" + codigoBarras + ", estadoL=" + estadoL + ", ubicacion=" + ubicacion + '}';
    }
    
}

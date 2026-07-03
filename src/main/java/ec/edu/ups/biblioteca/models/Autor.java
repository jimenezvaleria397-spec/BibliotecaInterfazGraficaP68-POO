/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author jimen
 */
public class Autor {
    private String nombre;
    private String nacionalidad;
    private String codigoAutor;
    private LocalDate fechadeNac;
    private ArrayList<String> titulos; 

    public Autor() {
    }

    public Autor(String nombre, String nacionalidad, String codigoAutor, LocalDate fechadeNac, ArrayList<String> titulos) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.codigoAutor = codigoAutor;
        this.fechadeNac = fechadeNac;
        this.titulos = titulos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getCodigoAutor() {
        return codigoAutor;
    }

    public void setCodigoAutor(String codigoAutor) {
        this.codigoAutor = codigoAutor;
    }

    public LocalDate getFechadeNac() {
        return fechadeNac;
    }

    public void setFechadeNac(LocalDate fechadeNac) {
        this.fechadeNac = fechadeNac;
    }

    public ArrayList<String> getTitulos() {
        return titulos;
    }

    public void setTitulos(ArrayList<String> titulos) {
        this.titulos = titulos;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.nombre);
        hash = 53 * hash + Objects.hashCode(this.nacionalidad);
        hash = 53 * hash + Objects.hashCode(this.codigoAutor);
        hash = 53 * hash + Objects.hashCode(this.fechadeNac);
        hash = 53 * hash + Objects.hashCode(this.titulos);
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
        final Autor other = (Autor) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.nacionalidad, other.nacionalidad)) {
            return false;
        }
        if (!Objects.equals(this.codigoAutor, other.codigoAutor)) {
            return false;
        }
        if (!Objects.equals(this.fechadeNac, other.fechadeNac)) {
            return false;
        }
        return Objects.equals(this.titulos, other.titulos);
    }

    @Override
    public String toString() {
        return "Autor{" + "nombre=" + nombre + ", nacionalidad=" + nacionalidad + ", codigoAutor=" + codigoAutor + ", fechadeNac=" + fechadeNac + ", titulos=" + titulos + '}';
    }
}

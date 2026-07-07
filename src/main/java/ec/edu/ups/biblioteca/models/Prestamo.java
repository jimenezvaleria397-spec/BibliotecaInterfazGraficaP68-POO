/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.models;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author jimen
 */
public class Prestamo {

    private String codigo;
    private Usuario usuario;
    private EjemplarLibro ejemplar;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private boolean estado;

    public Prestamo() {
    }

    public Prestamo(String codigo, Usuario usuario, EjemplarLibro ejemplar, Date fechaPrestamo, 
            Date fechaDevolucion, boolean estado) {
        this.codigo = codigo;
        this.usuario = usuario;
        this.ejemplar = ejemplar;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public EjemplarLibro getEjemplar() {
        return ejemplar;
    }

    public void setEjemplar(EjemplarLibro ejemplar) {
        this.ejemplar = ejemplar;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }
    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.codigo);
        hash = 89 * hash + Objects.hashCode(this.usuario);
        hash = 89 * hash + Objects.hashCode(this.ejemplar);
        hash = 89 * hash + Objects.hashCode(this.fechaPrestamo);
        hash = 89 * hash + Objects.hashCode(this.fechaDevolucion);
        hash = 89 * hash + (this.estado ? 1 : 0);
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
        final Prestamo other = (Prestamo) obj;
        if (this.estado != other.estado) {
            return false;
        }
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        if (!Objects.equals(this.ejemplar, other.ejemplar)) {
            return false;
        }
        if (!Objects.equals(this.fechaPrestamo, other.fechaPrestamo)) {
            return false;
        }
        return Objects.equals(this.fechaDevolucion, other.fechaDevolucion);
    }

    @Override
    public String toString() {
        return "Prestamo{" + "codigo=" + codigo + ", usuario=" + usuario + ", ejemplar=" + ejemplar 
                + ", fechaPrestamo=" + fechaPrestamo + ", fechaDevolucion=" + fechaDevolucion + ", estado=" + estado + '}';
    }
}

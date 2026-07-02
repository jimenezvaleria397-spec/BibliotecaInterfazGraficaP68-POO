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
public class Bibliotecario {
    private int idBibliotecario;
    private String email;

    public Bibliotecario() {
    }

    public Bibliotecario(int idBibliotecario, String email) {
        this.idBibliotecario = idBibliotecario;
        this.email = email;
    }

    public int getIdBibliotecario() {
        return idBibliotecario;
    }

    public void setIdBibliotecario(int idBibliotecario) {
        this.idBibliotecario = idBibliotecario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.idBibliotecario;
        hash = 97 * hash + Objects.hashCode(this.email);
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
        final Bibliotecario other = (Bibliotecario) obj;
        if (this.idBibliotecario != other.idBibliotecario) {
            return false;
        }
        return Objects.equals(this.email, other.email);
    }
    
    

    @Override
    public String toString() {
        return "Bibliotecario{" + "idBibliotecario=" + idBibliotecario + ", email=" + email + '}';
    }
    
    
    
}

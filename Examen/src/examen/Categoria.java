/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen;

import java.util.Objects;

/**
 *
 * @author Ma. Cecilia
 */
public class Categoria {
    private String nombre;
    private String imagen;
    
    public Categoria(String n , String img){
        this.imagen= img;
        this.nombre= n ;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.nombre);
        return hash;
    }
    
    //Es igual si su nombre es igual.
    //Lo uso solamente para obtener la categoría.

    @Override
    public boolean equals(Object obj) {
        String string = (String) obj;
        if (string == nombre) {
            return true;
        }else{
            return false;
        }   
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return   nombre +  imagen ;
    }
    
    
    
}

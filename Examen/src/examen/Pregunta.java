/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Ma. Cecilia
 */
public class Pregunta implements Serializable{
    private String texto;
    private List<String> opciones;
    private String respuesta;
 
    
    public Pregunta(String t , List <String> op , String r){
        this.opciones = op;
        this.texto = t;
        this.respuesta = r ;
        
    }

    public String getTexto() {
        return texto;
    }

    public List<String> getOpciones() {
        return opciones;
    }

    public String getRespuesta() {
        return respuesta;
    }
    
    
    
    
}

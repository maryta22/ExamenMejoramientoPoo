/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ma. Cecilia
 */
public class Examen {
    private Map<Categoria, List<Pregunta>> mapaPreguntas = new HashMap<>();
    private int tiempo;
    
    public Examen(int tiempo) throws IOException{
        
    }
    
    /**private Map<Categoria,List<Pregunta>> construirMapa(){
        
    }**/

    public Map<Categoria, List<Pregunta>> getMapaPreguntas() {
        return mapaPreguntas;
    }

    public int getTiempo() {
        return tiempo;
    }
    
    
    
}

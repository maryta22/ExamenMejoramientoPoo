/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Archivos;

import examen.Pregunta;
import java.util.ArrayList;
import java.util.List;


public final class Preguntas {
    List<Pregunta> biologia = new ArrayList<>();
    List<Pregunta> geografia = new ArrayList<>();
    List<Pregunta> literatura = new ArrayList<>();

 
    Preguntas(){
        crearPreguntasBiología();
        crearPreguntasGeografia();
        crearPreguntasLiteratura();
    }
  
    public void crearPreguntasBiología(){
        
        List<String> pregunta1 = new ArrayList<>();
        List<String> pregunta2 = new ArrayList<>();
        List<String> pregunta3 = new ArrayList<>();
        
        pregunta1.add("Unidad funcional de la vida");
        pregunta1.add("Unidad mínima de la materia");
        pregunta1.add("Conjunto de átomos es seres inertes");
        pregunta1.add("Conjunto de tejidos");
        pregunta1.add("Electrones");
        
        pregunta2.add("Vacuola");
        pregunta2.add("Mitocondria");
        pregunta2.add("Pared Celular");
        pregunta2.add("Reticulo Endoplasmático");
        pregunta2.add("Cloroplastos");
        
        pregunta3.add("Metafase");
        pregunta3.add("Telofase");
        pregunta3.add("Anafase");
        pregunta3.add("Infase");
        pregunta3.add("Profase");
        
        biologia.add(new Pregunta("¿Qué es la célula?", pregunta1 , "Unidad funcional de la vida"));
        biologia.add(new Pregunta("¿En donde se realiza la fotosíntesis?", pregunta2 , "Cloroplastos"));
        biologia.add(new Pregunta("¿Cuál no es una fase de la mitosis?", pregunta3 , "Infase"));
    }
    
    public void crearPreguntasGeografia(){
        
        List<String> pregunta1 = new ArrayList<>();
        List<String> pregunta2 = new ArrayList<>();
        List<String> pregunta3 = new ArrayList<>();
        
        pregunta1.add("Guayaquil");
        pregunta1.add("Quito");
        pregunta1.add("Zamora Chimchipe");
        pregunta1.add("Guayas");
        pregunta1.add("Pichincha");
        
        pregunta2.add("2");
        pregunta2.add("4");
        pregunta2.add("6");
        pregunta2.add("5");
        pregunta2.add("3");
        
        pregunta3.add("Océano Atlántico");
        pregunta3.add("Océano Índico");
        pregunta3.add("Océano Pacífico");
        pregunta3.add("Océano Atlártico");
        pregunta3.add("Océano Ártico");
        
        geografia.add(new Pregunta("¿Cuál es la capital del Ecuador?", pregunta1 , "Quito"));
        geografia.add(new Pregunta("¿Cuántos continentes hay?", pregunta2 , "6"));
        geografia.add(new Pregunta("¿Cuál es el océano más grande del mundo en superficie?", pregunta3 , "Océano Pacífico"));
    }
    
    public void crearPreguntasLiteratura(){
        
        List<String> pregunta1 = new ArrayList<>();
        List<String> pregunta2 = new ArrayList<>();
        List<String> pregunta3 = new ArrayList<>();
        
        pregunta1.add("Marco Tulio Cicerón");
        pregunta1.add("Homero");
        pregunta1.add("Séneca");
        pregunta1.add("Heródoto");
        pregunta1.add("Galileo");
        
        pregunta2.add("Alejandro Magno");
        pregunta2.add("Julio César");
        pregunta2.add("El sueño de una noche de Verano");
        pregunta2.add("Hamlet");
        pregunta2.add("Otelo");
        
        pregunta3.add("El Señor de los Anillos (Saga)");
        pregunta3.add("Star Wards");
        pregunta3.add("Citas del Presidente Mao");
        pregunta3.add("La Biblia");
        pregunta3.add("Historia de dos ciudades");
        
        literatura.add(new Pregunta("¿Quién escribió 'La Ilíada'?", pregunta1 , "Homero"));
        literatura.add(new Pregunta("¿Cuál de estas obras no es de William Shakespeare?", pregunta2 , "Alejandro Magno"));
        literatura.add(new Pregunta("¿Cuál es el libro más vendido de los últimos 50 años?", pregunta3 , "La Biblia"));
    }

    public List<Pregunta> getBiologia() {
        return biologia;
    }

    public List<Pregunta> getGeografia() {
        return geografia;
    }

    public List<Pregunta> getLiteratura() {
        return literatura;
    }
    
    
}

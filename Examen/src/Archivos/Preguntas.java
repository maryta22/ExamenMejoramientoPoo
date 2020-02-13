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
    private List<Pregunta> biologia = new ArrayList<>();
    private List<Pregunta> geografia = new ArrayList<>();
    private List<Pregunta> literatura = new ArrayList<>();

 
    Preguntas(){
        crearPreguntasBiología();
        crearPreguntasGeografia();
        crearPreguntasLiteratura();
    }
  
    public void crearPreguntasBiología(){
        
        ArrayList<String> bpregunta1 = new ArrayList<>();
        ArrayList<String> bpregunta2 = new ArrayList<>();
        ArrayList<String> bpregunta3 = new ArrayList<>();
        
        bpregunta1.add("Unidad funcional de la vida");
        bpregunta1.add("Unidad mínima de la materia");
        bpregunta1.add("Conjunto de átomos es seres inertes");
        bpregunta1.add("Conjunto de tejidos");
        bpregunta1.add("Electrones");
        
        bpregunta2.add("Vacuola");
        bpregunta2.add("Mitocondria");
        bpregunta2.add("Pared Celular");
        bpregunta2.add("Reticulo Endoplasmático");
        bpregunta2.add("Cloroplastos");
        
        bpregunta3.add("Metafase");
        bpregunta3.add("Telofase");
        bpregunta3.add("Anafase");
        bpregunta3.add("Infase");
        bpregunta3.add("Profase");
        
        biologia.add(new Pregunta("¿Qué es la célula?", bpregunta1 , "Unidad funcional de la vida"));
        biologia.add(new Pregunta("¿En donde se realiza la fotosíntesis?", bpregunta2 , "Cloroplastos"));
        biologia.add(new Pregunta("¿Cuál no es una fase de la mitosis?", bpregunta3 , "Infase"));
    }
    
    public void crearPreguntasGeografia(){
        
        ArrayList<String> gpregunta1 = new ArrayList<>();
        ArrayList<String> gpregunta2 = new ArrayList<>();
        ArrayList<String> gpregunta3 = new ArrayList<>();
        
        gpregunta1.add("Guayaquil");
        gpregunta1.add("Quito");
        gpregunta1.add("Zamora Chimchipe");
        gpregunta1.add("Guayas");
        gpregunta1.add("Pichincha");
        
        gpregunta2.add("2");
        gpregunta2.add("4");
        gpregunta2.add("6");
        gpregunta2.add("5");
        gpregunta2.add("3");
        
        gpregunta3.add("Océano Atlántico");
        gpregunta3.add("Océano Índico");
        gpregunta3.add("Océano Pacífico");
        gpregunta3.add("Océano Atlártico");
        gpregunta3.add("Océano Ártico");
        
        geografia.add(new Pregunta("¿Cuál es la capital del Ecuador?", gpregunta1 , "Quito"));
        geografia.add(new Pregunta("¿Cuántos continentes hay?", gpregunta2 , "6"));
        geografia.add(new Pregunta("¿Cuál es el océano más grande del mundo en superficie?", gpregunta3 , "Océano Pacífico"));
    }
    
    public void crearPreguntasLiteratura(){
        
        ArrayList<String> lpregunta1 = new ArrayList<>();
        ArrayList<String> lpregunta2 = new ArrayList<>();
        ArrayList<String> lpregunta3 = new ArrayList<>();
        
        lpregunta1.add("Marco Tulio Cicerón");
        lpregunta1.add("Homero");
        lpregunta1.add("Séneca");
        lpregunta1.add("Heródoto");
        lpregunta1.add("Galileo");
        
        lpregunta2.add("Alejandro Magno");
        lpregunta2.add("Julio César");
        lpregunta2.add("El sueño de una noche de Verano");
        lpregunta2.add("Hamlet");
        lpregunta2.add("Otelo");
        
        lpregunta3.add("El Señor de los Anillos (Saga)");
        lpregunta3.add("Star Wards");
        lpregunta3.add("Citas del Presidente Mao");
        lpregunta3.add("La Biblia");
        lpregunta3.add("Historia de dos ciudades");
        
        literatura.add(new Pregunta("¿Quién escribió 'La Ilíada'?", lpregunta1 , "Homero"));
        literatura.add(new Pregunta("¿Cuál de estas obras no es de William Shakespeare?", lpregunta2 , "Alejandro Magno"));
        literatura.add(new Pregunta("¿Cuál es el libro más vendido de los últimos 50 años?", lpregunta3 , "La Biblia"));
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

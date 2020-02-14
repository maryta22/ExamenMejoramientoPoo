/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ma. Cecilia
 */
public class Examen {
    private final Map<Categoria, List<Pregunta>> mapaPreguntas = new HashMap<>();
    private int tiempo;
    
    public Examen(int tiempo) throws IOException{
        this.tiempo = tiempo;
        construirMapa();
    }

    
    private void construirMapa(){
        try (BufferedReader archivo = new BufferedReader(new FileReader("src/recursos/categorias.txt"))) {
            String linea = archivo.readLine();
            while (linea != null) {
                String[] lista = linea.split(",");
                mapaPreguntas.put(new Categoria(lista[0],lista[1]), leerArchivo(lista[0]));
                linea = archivo.readLine();
            }
            archivo.close();

        } catch (FileNotFoundException ex) {
            System.out.println("CARPETA NO ENCONTRADA");
        } catch (IOException ex) {
            System.out.println("IO EXCEPTION EN LECTURA DEL ARCHIVO DE TEXTO");
        }
    }
    
    public List<Pregunta> leerArchivo(String nombreCategoria) {
            try (ObjectInputStream archivo = new ObjectInputStream(new FileInputStream("src/recursos/" + nombreCategoria))) {
                List<Pregunta> lista = (List<Pregunta>) archivo.readObject();
                archivo.close();
                return lista;
            } catch (FileNotFoundException ex) {
                System.out.println("Archivo  no encontrado");
            } catch (EOFException ex) {
                System.out.println("Archivo culminado");
            } catch (IOException ex) {
                System.out.println("IO EXCEPTION EN LA LECTURA DEL ARCHIVO");
            } catch (ClassNotFoundException ex) {
                System.out.println("ClassNotFoundException");
            }
            return null;
        }

    public Map<Categoria, List<Pregunta>> getMapaPreguntas() {
        
        return mapaPreguntas;
    }

    public int getTiempo() {
        return tiempo;
    }
    
    
    
}

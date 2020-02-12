/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen;

import Archivos.Archivos;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Ma. Cecilia
 */
public class VistaExamen extends Application {
    private Examen e;
    private VBox root;
    private int correctas;
    private int tTranscurridos;
    private Label lTiempo = new Label("0");
    private HBox tiempo;
    private HBox categoria;
    private ComboBox categorias;
    private List<String> listaCategorias = new ArrayList<>();
    private List<String> rutaImagenes = new ArrayList<>();
    /**public Pregunta obtenerPregunta(List<Pregunta> p ){
        
    }**/
    
    @Override
    public void start(Stage primaryStage) {
        
        categorias = new ComboBox();
        
        categoria = new HBox();
        categoria.getChildren().addAll(new Label("Seleccione Categoría: "),categorias);
        
        tiempo = new HBox();
        tiempo.getChildren().addAll(new Label("Tiempo: "), lTiempo);
        
        root = new VBox();
        root.getChildren().addAll(tiempo,categoria);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Examen");
        primaryStage.setScene(scene);
        primaryStage.show();
        System.out.println(listaCategorias);
    }
    
    @Override
    public void init(){
        Archivos archivos = new Archivos();
        archivos.generarCategorias();
        archivos.generarBinarios();
        leerCategorias();
        
    }
    
    public void leerCategorias(){
        try (BufferedReader archivo = new BufferedReader( new FileReader ("src/recursos/categorias.txt") )){
            String linea = archivo.readLine();
            while(linea != null ){
                String[] lista = linea.split(",");
                listaCategorias.add(lista[0]);
                rutaImagenes.add(lista[1]);
                linea = archivo.readLine();
            }
            archivo.close();
            
        } catch (FileNotFoundException ex) {
            System.out.println("CARPETA NO ENCONTRADA");
        } catch (IOException ex){
            System.out.println("IO EXCEPTION EN LECTURA DEL ARCHIVO DE TEXTO");
        }
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

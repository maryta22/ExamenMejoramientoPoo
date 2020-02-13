/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen;

import Archivos.Archivos;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private List<String> listaCategorias = FXCollections.observableArrayList();
    private List<String> rutaImagenes = FXCollections.observableArrayList();
    /**public Pregunta obtenerPregunta(List<Pregunta> p ){
        
    }**/
    
    @Override
    public void start(Stage primaryStage) {
        
        categorias = new ComboBox();
        ObservableList paraCombo = (ObservableList) listaCategorias;
        categorias.setItems(paraCombo);
        
        categorias.setOnAction(new crearPreguntas());
        
        categoria = new HBox();
        categoria.getChildren().addAll(new Label("Seleccione Categoría: "),categorias);
        
        tiempo = new HBox();
        tiempo.getChildren().addAll(new Label("Tiempo: "), lTiempo);
        
        root = new VBox();
        root.getChildren().addAll(tiempo,categoria);
        
        Scene scene = new Scene(root, 750, 500);
        
        primaryStage.setTitle("Examen");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
       
    }
    
    private class crearPreguntas implements EventHandler<ActionEvent>{
        String nombreCategoria;
        String ruta;
        
        HBox avanzar;
        ImageView imagen;
        Label pregunta;
        Label opcion1;
        Label opcion2;
        Label opcion3;
        Label opcion4;
        Label opcion5;
        Label acierto;
        Button seguir;
        
        boolean leer;
        
        crearPreguntas(){
            leer = true;
            pregunta = new Label();
        }
        
        @Override
        public void handle(ActionEvent event) {
            FileInputStream inputstream = null;
            try {
                //establecer categoria
                nombreCategoria = (String)categorias.getValue();
                //establecer ruta
                for( String c: listaCategorias){
                    if(c.equals(nombreCategoria)){
                        int indice = listaCategorias.indexOf(c);
                        ruta = rutaImagenes.get(indice);
                    }
                }   
                //añadir imagen
                inputstream = new FileInputStream("src/recursos/"+ruta);
                Image i = new Image(inputstream);
                imagen =new ImageView(i);
                imagen.setFitHeight(200);
                imagen.setFitWidth(200);
                categoria.getChildren().add(imagen);
                //bloqueo ComboBox
                categorias.setDisable(true);
                
                List<Pregunta> preguntas= leerArchivo();
                Iterator<Pregunta> iterator = preguntas.iterator();
                while(iterator.hasNext()){
                    root.getChildren().removeAll(pregunta);
                    Pregunta p = iterator.next();
                    pregunta.setText(p.getTexto());
                    root.getChildren().add(pregunta);
                    
                }
            } catch (FileNotFoundException ex) {
                System.out.println("Imagen no encontrada");
            } finally {
                try {
                    inputstream.close();
                } catch (IOException ex) {
                    System.out.println("io exception imagen");
                }
            }
            
           
        }
        
        public List<Pregunta> leerArchivo(){
            try(ObjectInputStream archivo = new ObjectInputStream(new FileInputStream("src/recursos/"+nombreCategoria))) {
                while(leer){
                    List<Pregunta> lista = (List<Pregunta>)archivo.readObject();
                    return lista;
                }
            } catch (FileNotFoundException ex) {
                System.out.println("Archivo  no encontrado");
            }catch(EOFException ex){
                System.out.println("Archivo culminado");
                leer= false;
            }catch (IOException ex) {
                System.out.println("IO EXCEPTION EN LA LECTURA DEL ARCHIVO");        
            } catch (ClassNotFoundException ex) {
                System.out.println("ClassNotFoundException");
            }    
        return null;
        }
        
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

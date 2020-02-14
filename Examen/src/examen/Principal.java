/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen;

import Archivos.Archivos;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Ma. Cecilia
 */
public class Principal extends Application {
    VistaExamen interfaz = new VistaExamen();

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(interfaz.getRoot(), 750, 500);

        primaryStage.setTitle("Examen");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @Override
    public void init() {
        Archivos archivos = new Archivos();
        archivos.generarCategorias();
        archivos.generarBinarios();

    }

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void stop(){
        interfaz.setParar(true);
    }

}

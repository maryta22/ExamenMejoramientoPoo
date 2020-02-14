/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Archivos;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

/**
 *
 * @author Ma. Cecilia
 */
public class Archivos{

    Preguntas preguntas = new Preguntas();

    public void generarCategorias() {

        try {
            PrintWriter archivo = new PrintWriter(new BufferedWriter(new FileWriter("src/recursos/categorias.txt")));
            archivo.print("biologia,imagen1.png");
            archivo.println();
            archivo.print("literatura,imagen2.png");
            archivo.println();
            archivo.print("geografia,imagen3.png");
            archivo.close();
        } catch (IOException ex) {
            System.out.println("No se pudo crear el archivo Categorias");
        }
    }

    public void generarBinarios() {
        try (ObjectOutputStream biologia = new ObjectOutputStream(new FileOutputStream("src/recursos/biologia"));
                ObjectOutputStream geografia = new ObjectOutputStream(new FileOutputStream("src/recursos/geografia"));
                ObjectOutputStream literatura = new ObjectOutputStream(new FileOutputStream("src/recursos/literatura"))) {

            biologia.writeObject(preguntas.getBiologia());
            biologia.close();

            geografia.writeObject(preguntas.getGeografia());
            geografia.close();

            literatura.writeObject(preguntas.getLiteratura());
            literatura.close();

        } catch (IOException ex) {
            System.out.println("No se pudo crear un archivo");
        }

    }

}

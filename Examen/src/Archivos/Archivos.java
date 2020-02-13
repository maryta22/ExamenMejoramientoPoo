/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Archivos;

import examen.Categoria;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

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

            biologia.writeObject(preguntas.biologia);
            biologia.close();

            geografia.writeObject(preguntas.geografia);
            geografia.close();

            literatura.writeObject(preguntas.literatura);
            literatura.close();

        } catch (IOException ex) {
            System.out.println("No se pudo crear un archivo");
        }

    }

}

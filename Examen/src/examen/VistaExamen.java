/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Ma. Cecilia
 */
public final class VistaExamen {

    private Examen e;
    private VBox root;

    private int correctas;
    private int tTranscurridos;
    private boolean parar;
    private int tiempoContando;
    private int totalCorrectas;
    private int totalIncorrectas;

    private Label lTiempo;
    private HBox tiempo;
    private HBox categoria;
    private ComboBox categorias;

    private Button seguir;
    private Button finalizar;

    private Button opcion1;
    private Button opcion2;
    private Button opcion3;
    private Button opcion4;
    private Button opcion5;

    private String nombreCategoria;
    private String ruta;
    
    private VBox paraPregunta;
    private HBox avanzar;
    private ImageView imagen;

    private Label pregunta;
    private Label acierto;

    private List<Pregunta> preguntas;
    private Pregunta p;

    private Map<Categoria, List<Pregunta>> preguntasMapa;

    private final List<String> listaCategorias = FXCollections.observableArrayList();
    private ObservableList paraCombo = (ObservableList) listaCategorias;
    private final Map<String, String> rutaImagenes = new HashMap<>();

    Iterator<Map.Entry<Categoria, List<Pregunta>>> iteratorMapa;
    private Iterator<Pregunta> iterator;

    //llena las listas para el comboBox y obtener las imagenes.
    public VistaExamen() {

        try {
            e = new Examen(0);
            preguntasMapa = e.getMapaPreguntas();
            llenarListas();

            totalCorrectas = 0;
            totalIncorrectas = 0;

            lTiempo = new Label();

            
            categorias = new ComboBox();
            categorias.setItems(paraCombo);

            categorias.setOnAction(new crearPreguntas());

            categoria = new HBox();
            categoria.getChildren().addAll(new Label("Seleccione Categoría: "), categorias);
            

            tiempo = new HBox();
            tiempo.getChildren().addAll(new Label("Tiempo: "), lTiempo);

            root = new VBox(10);
            root.getChildren().addAll(tiempo, categoria);

        } catch (IOException ex) {
            System.out.println("Io exception creacion examen");
        }

    }

    //clase interna cuando se selecciona una opcion del combo.
    private class crearPreguntas implements EventHandler<ActionEvent> {

        crearPreguntas() {
            paraPregunta= new VBox();
            paraPregunta.setMinWidth(100);
            paraPregunta.setAlignment(Pos.CENTER);
            
            pregunta = new Label();
                 
            opcion1 = new Button();
            opcion2 = new Button();
            opcion3 = new Button();
            opcion4 = new Button();
            opcion5 = new Button();
            
            acierto = new Label("                    ");
            seguir = new Button("Siguiente");
            
            avanzar = new HBox(10);
            avanzar.setMinWidth(100);
            avanzar.setAlignment(Pos.CENTER);
            
            finalizar = new Button(" FINALIZAR ");

        }

        @Override
        public void handle(ActionEvent event) {
            FileInputStream inputstream = null;
            try {
                         
                correctas = 0;
                categoria.getChildren().removeAll(imagen);
                listaCategorias.remove(nombreCategoria);
                
 
                //establecer categoria
                nombreCategoria = (String) categorias.getValue();
                //establecer ruta
                buscarRutaImagen();
                //añadir imagen
                inputstream = new FileInputStream("src/recursos/" + ruta);
                Image i = new Image(inputstream);
                imagen = new ImageView(i);
                imagen.setFitHeight(200);
                imagen.setFitWidth(200);
                categoria.getChildren().add(imagen);

                //bloqueo ComboBox
                categorias.setDisable(true);

                //Preguntas y respuestas
                preguntas = obtenerPreguntas();
                iterator = preguntas.iterator();

                //añadir primera pregunta
                siguientePregunta();

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

    }

    //hilo para el contador
    class Contador implements Runnable {

        Contador() {
            tiempoContando = 60;
            parar = false;
        }

        @Override
        public void run() {
            while (!parar) {
                try {
                    tiempoContando -= 1;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            lTiempo.setText(String.valueOf(tiempoContando));
                        }
                    });
                    Thread.sleep(1000);
                    if (tiempoContando <= 0) {
                        parar = true;
                        tTranscurridos += 60;
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                agregarPregunta();
                            }
                        });
                    }
                } catch (InterruptedException ex) {
                    System.out.println("Hilo Interrumpido");
                }

            }
        }

    }

    //Obtiene la categoría con el String obtenido en el combo.
    public Categoria obtenerCategoria() {
        for (Map.Entry<Categoria, List<Pregunta>> c : preguntasMapa.entrySet()) {
            if (c.getKey().equals(nombreCategoria)) {
                return c.getKey();
            }
        }
        return null;
    }

    //obtener ruta de la imagen
    public void buscarRutaImagen() {
        ruta = rutaImagenes.get(nombreCategoria);
    }

    //Obtenemos la lista de opciones de respuesta de la categoría escogida.
    public List<Pregunta> obtenerPreguntas() {
        return preguntasMapa.get(obtenerCategoria());
    }

    //limpia el root al presionar siguiente y muestra la siguiente pregunta.
    public void siguientePregunta() {
        
        limpiarRoot();
        desbloquearBotones();

        root.getChildren().remove(paraPregunta);

        p = iterator.next();

        Contador cronometro = new Contador();
        Thread hilo = new Thread(cronometro);
        hilo.start();

        pregunta.setText(p.getTexto());

        List<String> opciones = p.getOpciones();

        opcion1.setText(opciones.get(0));
        opcion2.setText(opciones.get(1));
        opcion3.setText(opciones.get(2));
        opcion4.setText(opciones.get(3));
        opcion5.setText(opciones.get(4));

        correcto(opcion1);
        correcto(opcion2);
        correcto(opcion3);
        correcto(opcion4);
        correcto(opcion5);

        seguir.setDisable(true);
        Siguiente(seguir);
        
        paraPregunta.getChildren().add(pregunta);
        root.getChildren().add(paraPregunta);
        root.getChildren().addAll(opcion1, opcion2, opcion3, opcion4, opcion5);
        avanzar.getChildren().addAll(acierto, seguir);

        root.getChildren().add(avanzar);

    }

    //verifica si el botón presionado es correcto o incorrecto.
    public void correcto(Button boton) {
        boton.setOnAction(e -> {
            if (!boton.getText().equals(p.getRespuesta())) {
                acierto.setText("INCORRECTO");
                totalIncorrectas += 1;
            } else {
                acierto.setText("CORRECTO");
                correctas += 1;
                totalCorrectas += 1;
            }
            seguir.setDisable(false);
            bloquearBotones();
            parar = true;
            tTranscurridos += (60 - tiempoContando);

        });
    }

    //accion de presionar al siguiente botón
    public void Siguiente(Button boton) {
        //accion del botón siguiente
        boton.setOnAction(e -> {
            agregarPregunta();
        });
    }

    //verifica si hay preguntas para añadir y añade, si no hay muestra resultados.
    public void agregarPregunta() {
        if (iterator.hasNext()) {
            siguientePregunta();
        } else {
            Resultados();
        }
    }

    //muestra los resultados
    public void Resultados() {
        limpiarRoot();
        categorias.setDisable(false);
        acierto.setText("SE TERMINÓ EL TIEMPO "
                + " - " + correctas
                + " respuestas correctas ");
        root.getChildren().add(acierto);
        lTiempo.setText("");
        

        if (listaCategorias.size() == 1) {
            categorias.setDisable(true);
            root.getChildren().add(finalizar);
            finalizarPrueba(finalizar);
        }

    }

    //bloquea los botones de opciones.
    public void bloquearBotones() {
        opcion1.setDisable(true);
        opcion2.setDisable(true);
        opcion3.setDisable(true);
        opcion4.setDisable(true);
        opcion5.setDisable(true);

    }

    //desbloquea los botones de opciones.
    public void desbloquearBotones() {
        opcion1.setDisable(false);
        opcion2.setDisable(false);
        opcion3.setDisable(false);
        opcion4.setDisable(false);
        opcion5.setDisable(false);

    }

    //limpia el root.
    public void limpiarRoot() {
        acierto.setText("                    ");
        paraPregunta.getChildren().removeAll(pregunta);
        avanzar.getChildren().removeAll(acierto, seguir);
        root.getChildren().removeAll(paraPregunta, opcion1, opcion2, opcion3, opcion4, opcion5, avanzar);
    }

    public void llenarListas() {
        iteratorMapa = preguntasMapa.entrySet().iterator();
        while (iteratorMapa.hasNext()) {
            //no llamar el iterator en varias lineas
            Map.Entry<Categoria, List<Pregunta>> entry = iteratorMapa.next();
            listaCategorias.add(entry.getKey().getNombre());
            rutaImagenes.put(entry.getKey().getNombre(), entry.getKey().getImagen());
        }

    }

    public void finalizarPrueba(Button boton) {
        boton.setOnAction(e -> {
            root.getChildren().remove(finalizar);
            root.getChildren().add(new Label(" Prueba Finalizada "));
            registrarExamen();
        });
    }

    public void registrarExamen() {
        String linea = " Prueba Finalizada en : " + String.valueOf(tTranscurridos) + " segundos "
                + "  Correctas - " + String.valueOf(totalCorrectas)
                + "  Incorrectas -  " + String.valueOf(totalIncorrectas);
        root.getChildren().add(new Label(linea));
        guardarRegistro(linea);
    }

    public void guardarRegistro(String linea) {
        File file = new File("src/recursos/registros.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
                try (BufferedWriter bf = new BufferedWriter(new FileWriter(file.getAbsoluteFile(), true));) {
                    bf.write(linea + "\n");
                } catch (IOException ex) {
                    System.out.println("io exception escritura en el registro");
                }
            } catch (IOException ex) {
                System.out.println("io exception en crear archivo para el ");
            }
        } else {
            try (BufferedWriter bf = new BufferedWriter(new FileWriter(file.getAbsoluteFile(), true));) {
                bf.write(linea + "\n");
            } catch (IOException ex) {
                System.out.println("io exception escritura en el registro");
            }
        }

    }

    public VBox getRoot() {
        return root;
    }

    public void setParar(boolean parar) {
        this.parar = parar;
    }

}

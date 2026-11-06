package vista;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class Ventana {

    VBox contenedorPrincipal = new VBox();
    TextField input;
    TextField input2;
    Button empezar;
    Button limpiar;


    public VBox crearVista() {

        HBox cabecera = new HBox();

        cabecera.setAlignment(Pos.CENTER);
        cabecera.setSpacing(20);

        contenedorPrincipal.setAlignment(Pos.CENTER);
        contenedorPrincipal.setSpacing(20);
        contenedorPrincipal.setPadding(new Insets(30));

        input = crearInput();

        input2 = crearInput();

        empezar = crearBotonEmpezar();

        limpiar = crearBotonLimpiar();

        cabecera.getChildren().add(input);
        cabecera.getChildren().add(input2);
        cabecera.getChildren().add(empezar);
        cabecera.getChildren().add(limpiar);

        contenedorPrincipal.getChildren().add(cabecera);

        return contenedorPrincipal;
    }

    private TextField crearInput() {

        TextField in = new TextField();

        in.textProperty().addListener((
                observable, valorAnterior, valorNuevo) -> {

            String texto = in.getText();

            if (!texto.matches("[0-9]+")) {

                in.setStyle("""
                        -fx-border-color: #FF0000;
                        -fx-border-width: 2px;
                        -fx-border-radius: 5px;
                        -fx-backgroud-radius: 5px;
                        """);

            } else if (texto.isBlank()) {
                in.setStyle("""
                        -fx-border-color: #646464;
                        -fx-border-width: 2px;
                        -fx-border-radius: 5px;
                        -fx-background-radius: 5px;
                        -fx-background-color: white;
                        """);

            } else {

                in.setStyle("""
                        -fx-border-color: #00BC22;
                        -fx-border-width: 2px;
                        -fx-border-radius: 5px;
                        -fx-barckround-radius: 5px;
                        """);
            }
        });
        return in;
    }

    public Button crearBotonEmpezar() {

        Button empezar = new Button("Empezar");

        empezar.setOnAction(event -> {

            Thread hilo = new Thread(() -> {

                Platform.runLater(()->
                        contenedorPrincipal.getChildren().removeIf(nodo -> nodo instanceof Label));

                empezar.setDisable(true);

                boolean valido1 = validarInputIntegerPositivo(input.getText());

                Label linea = new Label();

                Platform.runLater(()-> contenedorPrincipal.getChildren().add(linea));

                int repeticiones = Integer.parseInt(input2.getText());

                String cadena = input.getText();

                if (valido1) {

                    for (int i = 0; i < repeticiones; i++) {

                        StringBuilder sb = new StringBuilder();
                        int contadorNumeros = 0;

                        for (int j = 0; j < cadena.length(); j++) {

                            contadorNumeros++;

                            if (j == cadena.length() - 1) {

                                sb.append(contadorNumeros)
                                        .append(" ")
                                        .append(cadena.charAt(j));

                                Platform.runLater(() -> linea.setText(sb.toString()));


                            } else {

                                if (cadena.charAt(j) != cadena.charAt(j + 1)) {

                                    sb.append(contadorNumeros)
                                            .append(" ")
                                            .append(cadena.charAt(j))
                                            .append(" ");

                                    contadorNumeros = 0;
                                }
                            }
                        }

                        cadena = sb.toString().replace(" ", "");

                        String finalCadena = cadena;
                        Platform.runLater(() ->{
                            input.setText(finalCadena);
                            linea.setText(sb.toString());
                        });
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                empezar.setDisable(false);
            });

            hilo.start();
        });
        return empezar;

    }

    private Button crearBotonLimpiar() {

        Button limpiar = new Button("Limpiar");

        limpiar.setOnAction(event -> {

            contenedorPrincipal.getChildren().removeIf(nodo -> nodo instanceof Label);

            input.clear();
            input2.clear();

            input.setStyle("""
                    -fx-border-color: #646464;
                        -fx-border-width: 2px;
                        -fx-border-radius: 5px;
                        -fx-background-radius: 5px;
                        -fx-background-color: white;
                    """);

            input2.setStyle("""
                    -fx-border-color: #646464;
                        -fx-border-width: 2px;
                        -fx-border-radius: 5px;
                        -fx-background-radius: 5px;
                        -fx-background-color: white;
                    """);

        });

        return limpiar;

    }

    private boolean validarInputIntegerPositivo(String input) {

        boolean correcto = true;


        return correcto;

    }
}
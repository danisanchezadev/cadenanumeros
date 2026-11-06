package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import vista.Ventana;

public class AppCadenaNumeros extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        Ventana ventana = new Ventana();

        Scene escena = new Scene(ventana.crearVista(), 800, 200);

        stage.setScene(escena);

        stage.setTitle("Juego de cadena de números.");

        stage.show();
    }
}

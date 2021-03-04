package sample.Menu;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Juego.Controller;
import sample.Jugador.Jugador;
import sample.Ventana.Ventana;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MenuController implements Initializable {

    @FXML
    private TextField textFieldNombre;

    // Abro la nueva ventana de jugar
    public void clickJugar(ActionEvent event) throws IOException {
        // Cuando se haya escrito un nombre
        if (!textFieldNombre.getText().isBlank()) {
            actualizarJugadorActual();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Juego/juego.fxml"));
            Parent root = loader.load();
            // Se actualiza la escena y se carga
            Ventana.scene = new Scene(root);
            Ventana.stage.setScene(Ventana.scene);
            Ventana.stage.setTitle("3 en Raya");

            Ventana.scene.getStylesheets().add(Ventana.tema);
            Ventana.stage.setResizable(false);
            Ventana.stage.show();
        }
        // El nombre esta en blanco
        else {
            textFieldNombre.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
        }
    }

    // Cerrar
    public void clickSalir(ActionEvent event) {
        Platform.exit();
    }

    // Actualizo el jugador actual de la partida
    private void actualizarJugadorActual() {
        Controller.jugadorActivo = new Jugador(textFieldNombre.getText());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setScene(Scene scene) {
        Ventana.scene = scene;
    }

    public void setStage(Stage stage) {
        Ventana.stage = stage;
    }

}


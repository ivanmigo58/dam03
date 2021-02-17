package sample.Menu;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Juego.Controller;
import sample.Jugador.Jugador;

import java.io.IOException;


public class MenuController {

    @FXML
    private TextField textFieldNombre;

    // Abro la nueva ventana de jugar
    public void clickJugar(ActionEvent event) throws IOException {
        // Cuando se haya escrito un nombre
        if (!textFieldNombre.getText().isBlank()) {
            actualizarJugadorActual();
            Parent root = FXMLLoader.load(getClass().getResource("../Juego/juego.fxml"));
            Stage stage = new Stage();
            stage.setTitle("3 en Raya");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node) (event.getSource())).getScene().getWindow().hide();
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
}


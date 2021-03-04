package sample.Ranking;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sample.Juego.Controller;
import sample.Jugador.Jugador;

import java.net.URL;
import java.util.ResourceBundle;

public class RankingController implements Initializable {

    @FXML
    private VBox vboxList;

    private String[] resultados = {"Ganadas", "Empatadas", "Perdidas"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Muestro los resultados por cada jugador
        for (Jugador jugador : Controller.jugadorList) {
            HBox hBox = new HBox(3);
            hBox.getStyleClass().add("HBoxStyle");
            for (int i = 0; i < resultados.length; i++) {
                Label labelTxt = new Label(resultados[i] + "\n");
                labelTxt.getStyleClass().add("labelResultados");
                Label labelResultado = new Label(String.valueOf(jugador.getResultados(i)));
                hBox.getChildren().addAll(labelTxt, labelResultado);
            }
            TitledPane titledPane = new TitledPane(jugador.getNombre(), hBox);
            vboxList.getChildren().add(titledPane);
        }

    }
}

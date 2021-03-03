package sample.Ranking;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class RankingController implements Initializable {

    @FXML
    private VBox vboxList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TitledPane titledPane = new TitledPane("The Title", new Label("The content inside the TitledPane"));
        vboxList.getChildren().add(titledPane);

    }
}

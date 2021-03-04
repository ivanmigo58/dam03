package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Menu.MenuController;
import sample.Ventana.Ventana;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu/sample.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("3 en Raya");
        primaryStage.setResizable(false);
        Ventana.scene = new Scene(root);
        Ventana.stage = primaryStage;
        Ventana.scene.getStylesheets().add(Ventana.tema);

        primaryStage.setScene(Ventana.scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

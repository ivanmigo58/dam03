package sample.Juego;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.Jugador.Jugador;
import sample.Menu.MenuController;
import sample.Ventana.Ventana;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Label labelJugador;
    @FXML
    private RadioButton radioButton1;
    @FXML
    private RadioButton radioButton2;
    @FXML
    private RadioButton radioButton3;
    @FXML
    private GridPane panelBotones;
    @FXML
    private Button botonInicio;
    @FXML
    ToggleGroup escoger;

    Juego juego;
    static  public List<Jugador> jugadorList = new ArrayList<>();
    static public Jugador jugadorActivo;
    private final String[] opcionesRadioButton = {"vs PC", "PC vs PC", "vs jugador 2"};
    private RadioButton[] radioButtons;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Añado el jugador a la lista
        jugadorList.add(jugadorActivo);
        // Renuevo el label de nombre
        labelJugador.setText(labelJugador.getText() + jugadorActivo.getNombre());
        // Actualizo el texto de los RadioButton
        opcionesRadioButton[0] = jugadorActivo.getNombre() + " " + opcionesRadioButton[0];
        opcionesRadioButton[2] = jugadorActivo.getNombre() + " " + opcionesRadioButton[2];
        // Inicializo el array de RadioButtons
        radioButtons = new RadioButton[]{radioButton1, radioButton2, radioButton3};
        // Actualizo el texto de los RadioButtons
        for (int i = 0; i < radioButtons.length; i++) {
            radioButtons[i].setText(opcionesRadioButton[i]);
        }
    }

    // Click en el boton start
    public void clickStart(ActionEvent actionEvent) {
        // Obtengo el checkbutton clicado
        RadioButton selectedRadioButton = (RadioButton) escoger.getSelectedToggle();
        String id = selectedRadioButton.getId();
        int modoJuego = 0;
        for (int i = 0; i < radioButtons.length; i++) {
            // Si el boton clicado es ese, pongo el modo de juego
            if (id.equals(radioButtons[i].getId())) {
                modoJuego = i + 1;
            }
        }
        // Creo el juego
        juego = new Juego(modoJuego, this);
        // Rellena los botones
        for (Node button : panelBotones.getChildren()) {
            juego.buttons.add((Button)button);
        }
        juego.habilitarBotones();
        // Reseteo el texto de los botones
        restartButtons();
        botonInicio.setVisible(false);
        // Modo ordenador vs ordenador
        if (modoJuego == 2) {
            juego.botonClick(null);
        }
    }


    // Click en el tres en ralla
    public void clickButton(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        if (!juego.checkBotonPulsado(button)) {
            juego.botonClick(button);
        }
    }


    // Restablece el texo de los botones
    private void restartButtons() {
        for (Button button : juego.buttons) {
            button.setText(null);
            button.getStyleClass().removeAll("buttonsGanadores");
        }
    }

    // Restablece el juego
    void restartGame() {
        juego.deshabilitarBotones();
        botonInicio.setVisible(true);
    }

    // Abrir ranking
    public void abrirRanking(ActionEvent actionEvent) throws IOException {
        // Creo la nueva ventana de clasificacion y la abro
        Parent root = FXMLLoader.load(getClass().getResource("../Ranking/ranking.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("styles.css");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    // Cambio de usuario
    public void cambioUsuario(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Menu/sample.fxml"));
        Parent root = loader.load();
        // Actualizo la scena del menu y la cargo
        Ventana.scene = new Scene(root);
        Ventana.scene.getStylesheets().add(Ventana.tema);
        Ventana.stage.setScene(Ventana.scene);
        Ventana.stage.setTitle("Tres en raya");
        Ventana.stage.setResizable(false);

    }

    // Para cambiar el tema en vivo
    public void cambiarTema(ActionEvent actionEvent) {
        MenuItem menuItem = (MenuItem) actionEvent.getSource();
        // Tema claro
        if (menuItem.getId().equals("claro")) {
            Ventana.scene.getStylesheets().remove("styles_dark.css");
            Ventana.tema = "styles.css";
        }
        // Tema oscuro
        else if (menuItem.getId().equals("oscuro")) {
            Ventana.scene.getStylesheets().remove("styles.css");
            Ventana.tema = "styles_dark.css";
        }
        // Aplico el tema
        Ventana.scene.getStylesheets().add(Ventana.tema);
    }

    // Salir
    public void salir(ActionEvent actionEvent) {
        new MenuController().clickSalir(actionEvent);
    }

}

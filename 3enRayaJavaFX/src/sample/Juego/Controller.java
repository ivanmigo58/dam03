package sample.Juego;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import sample.Jugador.Jugador;

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
    List<Jugador> jugadorList = new ArrayList<>();
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
        juego.botonClick(button);
    }


    // Restablece el texo de los botones
    private void restartButtons() {
        for (Button button : juego.buttons) {
            button.setText(null);
        }
    }

    // Restablece el juego
    void restartGame() {
        juego.deshabilitarBotones();
        botonInicio.setVisible(true);
    }

}

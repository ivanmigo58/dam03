package sample.Juego;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import sample.Jugador.Jugador;

import java.awt.*;
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
    private int modoJuego = 0;
    private RadioButton[] radioButtons;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Oculto los botones del juego
        panelBotones.setVisible(false);
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
        for (int i = 0; i < radioButtons.length; i++) {
            // Si el boton clicado es ese, pongo el modo de juego
            if (id.equals(radioButtons[i].getId())) {
                modoJuego = i + 1;
            }
        }
        // Creo el juego
        juego = new Juego(modoJuego);
        // Muestro los botones del juego
        panelBotones.setVisible(true);
        botonInicio.setVisible(false);
    }


    // Cuando se clica en el tablero
    public void click(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        // Si el boton no se ha clicado
        if (modoJuego == 1) {
            jugarVSpc(button);
        } else if (modoJuego == 2) {
            jugarPCvsPC(button);
        } else if (modoJuego == 3) {
            System.out.println("yes");
            jugarVSjugador(button);
        }
    }

    // Modo 1
    private void jugarVSpc(javafx.scene.control.Button button) {

    }

    // Modo 2
    private void jugarPCvsPC(javafx.scene.control.Button button) {

    }

    // Modo 3
    private void jugarVSjugador(javafx.scene.control.Button button) {
        // Si el boton no se ha pulsado
        if (!juego.botonPulsado(button.getId())) {
            button.setText(String.valueOf(juego.turno));
            // Compruebo si hay un ganador
            if (!juego.comprobarResultado()) {
                juego.cambioTurno();
            }
            // Hay un ganador
            else if (juego.comprobarResultado()) {
                System.out.println("HAY UN GANADOR");
            }
        }
    }
}

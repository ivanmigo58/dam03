package sample.Juego;

import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

public class Juego {

    List<String> botonesSeleccionados = new ArrayList<>();
    private int modoJuego;

    private Turno tablero[][] = new Turno[3][3];
    Turno turno = Turno.X;
    private int[] botonesGanadores = new int[3];
    private Button buttonClick;
    List<Button> buttons = new ArrayList<>();
    private Controller controller;
    private String[] posicionesGanadoras;

    public Juego(int modoJuego, Controller controller) {
        this.modoJuego = modoJuego;
        this.controller = controller;
    }


    private enum Turno {
        X, O;
    }


    // Reviso si ese boton ya se ha pulsado
    void botonClick(Button button) {
        this.buttonClick = button;
        if (modoJuego == 1) {
            jugadorVSpc();
        } else if (modoJuego == 2) {
            pcVSpc();
        } else if (modoJuego == 3) {
            jugadorVSjugador();
        }

        if (botonesSeleccionados.size() >= buttons.size()) {
            controller.restartGame();
        }
    }

    // Modo 1
    private void jugadorVSpc() {
        // Si le toca al usuario
        if (turno.equals(Turno.X)) {
            jugadorVSjugador();
            // Si todavia no ha ganado
            if (!comprobarResultado()) {
                jugadorVSpc();
            }
        }
        // Si le toca al ordenador
        else {
            // Siempre que no se hayan pulsado todos los botones
            if (botonesSeleccionados.size() < buttons.size()) {
                String idButton = null;
                // Escojo una posicion random
                do {
                    int randomX = (int) (Math.random() * 3 + 0);
                    int randomXX = (int) (Math.random() * 3 + 0);
                    idButton = "B" + randomX + randomXX;
                } while (botonClicado(idButton));
                // Marco el boton de esa posicion
                for (Button button : buttons) {
                    if (button.getId().equals(idButton)) {
                        button.setText(String.valueOf(turno));
                        break;
                    }
                }
                if (comprobarResultado()) {
                    controller.restartGame();
                    mostrarLineaGanadora(posicionesGanadoras);

                } else {
                    cambioTurno();
                }
            }
        }
    }

    // Modo 2
    private void pcVSpc() {
        // Siempre que no se hayan pulsado todos los botones
        if (botonesSeleccionados.size() < buttons.size()) {
            String idButton = null;
            // Escojo una posicion random
            do {
                int randomX = (int) (Math.random() * 3 + 0);
                int randomXX = (int) (Math.random() * 3 + 0);
                idButton = "B" + randomX + randomXX;
            } while (botonClicado(idButton));
            // Marco el boton de esa posicion
            for (Button button : buttons) {
                if (button.getId().equals(idButton)) {
                    button.setText(String.valueOf(turno));
                    break;
                }
            }
            if (!comprobarResultado()) {
                cambioTurno();
                pcVSpc();
            } else {
                controller.restartGame();
                mostrarLineaGanadora(posicionesGanadoras);
            }
        }
    }

    // Modo 3
    private void jugadorVSjugador() {
        // Si el boton no se ha pulsado
        if (!botonClicado(buttonClick.getId())) {
            buttonClick.setText(String.valueOf(turno));
            // Compruebo si hay un ganador
            if (comprobarResultado()) {
                controller.restartGame();
                mostrarLineaGanadora(posicionesGanadoras);
            }
            // NO hay un ganador
            else {
                cambioTurno();
            }
        }
    }

    // Comprueba si ese boton ya ha sido pulsado para el tres en raya
    boolean botonClicado(String id) {
        boolean pulsado = false;
        for (String boton : botonesSeleccionados) {
            if (boton.equals(id)) {
                pulsado = true;
                break;
            }
        }
        // Siempre que el boton no haya sido pulsado anteriormente
        if (!pulsado) {
            botonesSeleccionados.add(id);
            // Remplazo la B
            id = id.replace("B", "");
            // Lo añado en el tablero como pulsado
            tablero[Integer.parseInt(String.valueOf(id.charAt(0)))][Integer.parseInt(String.valueOf(id.charAt(1)))] = turno;
        }
        return pulsado;
    }

    boolean checkBotonPulsado(Button botonPulsado) {
        boolean pulsado = false;
        for (String idButton : botonesSeleccionados) {
            if (idButton.equals(botonPulsado.getId())) {
                pulsado = true;
                break;
            }
        }
        return pulsado;
    }


    // Comprueba si hay un ganador
    boolean comprobarResultado() {
        boolean ganador = false;

        // Primera linea horizontal
        if ( (tablero[0][0] != (null)) && (tablero[0][0].equals(tablero[0][1]) && tablero[0][1].equals(tablero[0][2]) )) {
            posicionesGanadoras = new String[]{"00", "01", "02"};
            ganador = true;
        }
        // Segunda linea horizontal
        else if ( (tablero[1][0] != (null)) && (tablero[1][0].equals(tablero[1][1]) && tablero[1][1].equals(tablero[1][2]) )) {
            posicionesGanadoras = new String[]{"10", "11", "12"};
            ganador = true;
        }
        // Tercera linea horizontal
        else if ( (tablero[2][0] != (null)) && (tablero[2][0].equals(tablero[2][1]) && tablero[2][1].equals(tablero[2][2]) )) {
            posicionesGanadoras = new String[]{"20", "21", "22"};
            ganador = true;
        }

        // Primera linea vertical
        else if ( (tablero[0][0] != (null)) && (tablero[0][0].equals(tablero[1][0]) && tablero[1][0].equals(tablero[2][0]) )) {
            posicionesGanadoras = new String[]{"00", "10", "20"};
            ganador = true;
        }
        // Segunda linea vertical
        else if ( (tablero[0][1] != (null)) && (tablero[0][1].equals(tablero[1][1]) && tablero[1][1].equals(tablero[2][1]) )) {
            posicionesGanadoras = new String[]{"01", "11", "21"};
            ganador = true;
        }
        // Tercera vertical
        else if ( (tablero[0][2] != (null)) && (tablero[0][2].equals(tablero[1][2]) && tablero[1][2].equals(tablero[2][2]) )) {
            posicionesGanadoras = new String[]{"02", "12", "22"};
            ganador = true;
        }
        // Diagonal de izquierda a derecha
        else if ( (tablero[0][0] != (null)) && (tablero[0][0].equals(tablero[1][1]) && tablero[1][1].equals(tablero[2][2]) )) {
            posicionesGanadoras = new String[]{"00", "11", "22"};
            ganador = true;
        }
        // Diagonal de derecha a izquierda
        else if ( (tablero[0][2] != (null)) && (tablero[0][2].equals(tablero[1][1]) && tablero[1][1].equals(tablero[2][0]) )) {
            posicionesGanadoras = new String[]{"02", "11", "20"};
            ganador = true;
        }
        else {
            posicionesGanadoras = null;
        }
        return ganador;
    }


    // Cambio el turno
    void cambioTurno() {
        if (turno.equals(Turno.X)) {
            turno = turno.O;
        } else {
            turno = Turno.X;
        }
    }

    // Muestra los botones que han hecho tres en raya pasando el ID de estos
    private void mostrarLineaGanadora(String[] idButtons) {
        for (String id : idButtons) {
            for (Button button : buttons) {
                if (button.getId().equals("B" + id)) {
                    button.getStyleClass().add("buttonsGanadores");
                }
            }
        }
    }

    // Deshabilita los botones y borra el estilo ganador
    void deshabilitarBotones() {
        for (Button button : buttons) {
            button.setDisable(true);
            button.getStyleClass().removeAll("buttonsGanadores");
        }
    }

    // Activar los botones
    void habilitarBotones() {
        for (Button button : buttons) {
            button.setDisable(false);
        }
    }

}

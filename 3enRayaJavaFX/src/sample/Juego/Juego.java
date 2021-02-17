package sample.Juego;

import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

public class Juego {

    List<String> botonesSeleccionados = new ArrayList<>();
    private int modoJuego;

    public Juego(int modoJuego) {
        this.modoJuego = modoJuego;
    }


    private enum Turno {
        X, O;
    }

    private Turno tablero[][] = new Turno[3][3];
    Turno turno = Turno.X;
    private int[] botonesGanadores = new int[3];
    private Button buttonClick;
    List<Button> buttons = new ArrayList<>();


    // Reviso si ese boton ya se ha pulsado
    void botonClick(Button button) {
        this.buttonClick = button;
        if (modoJuego == 1) {
            jugadorVSpc();
        } else if (modoJuego == 2) {
            PCvsPC();
        } else if (modoJuego == 3) {
            jugadorVSjugador();
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
                } while (botonClick(idButton));
                // Marco el boton de esa posicion
                for (Button button : buttons) {
                    if (button.getId().equals(idButton)) {
                        button.setText(String.valueOf(turno));
                        break;
                    }
                }
                if (!comprobarResultado()) {
                    cambioTurno();
                } else {
                    hayGanador();
                }
            }
        }
    }

    // Modo 2
    private void PCvsPC() {
        // Siempre que no se hayan pulsado todos los botones
        if (botonesSeleccionados.size() < buttons.size()) {
            String idButton = null;
            // Escojo una posicion random
            do {
                int randomX = (int) (Math.random() * 3 + 0);
                int randomXX = (int) (Math.random() * 3 + 0);
                idButton = "B" + randomX + randomXX;
            } while (botonClick(idButton));
            // Marco el boton de esa posicion
            for (Button button : buttons) {
                if (button.getId().equals(idButton)) {
                    button.setText(String.valueOf(turno));
                    break;
                }
            }
            if (!comprobarResultado()) {
                cambioTurno();
                PCvsPC();
            }
        }
    }

    // Modo 3
    private void jugadorVSjugador() {
        // Si el boton no se ha pulsado
        if (!botonClick(buttonClick.getId())) {
            buttonClick.setText(String.valueOf(turno));
            // Compruebo si hay un ganador
            if (!comprobarResultado()) {
                cambioTurno();
            }
            // Hay un ganador
            else {
                hayGanador();
            }
        }
    }

    // Comprueba si ese boton ya ha sido pulsado para el tres en raya
    boolean botonClick(String id) {
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

    // Comprueba si hay un ganador
    boolean comprobarResultado() {
        boolean ganador = false;

        // Primera linea horizontal
        if ( (tablero[0][0] != (null)) && (tablero[0][0].equals(tablero[0][1]) && tablero[0][1].equals(tablero[0][2]) )) {
            ganador = true;
        }
        // Segunda linea horizontal
        else if ( (tablero[1][0] != (null)) && (tablero[1][0].equals(tablero[1][1]) && tablero[1][1].equals(tablero[1][2]) )) {
            ganador = true;
        }
        // Tercera linea horizontal
        else if ( (tablero[2][0] != (null)) && (tablero[2][0].equals(tablero[2][1]) && tablero[2][1].equals(tablero[2][2]) )) {
            ganador = true;
        }

        // Primera linea vertical
        else if ( (tablero[0][0] != (null)) && (tablero[0][0].equals(tablero[1][0]) && tablero[1][0].equals(tablero[2][0]) )) {
            ganador = true;
        }
        // Segunda linea vertical
        else if ( (tablero[0][1] != (null)) && (tablero[0][1].equals(tablero[1][1]) && tablero[1][1].equals(tablero[2][1]) )) {
            ganador = true;
        }
        // Tercera vertical
        else if ( (tablero[0][2] != (null)) && (tablero[0][2].equals(tablero[1][2]) && tablero[1][2].equals(tablero[2][2]) )) {
            ganador = true;
        }
        // Diagonal de izquierda a derecha
        else if ( (tablero[0][0] != (null)) && (tablero[0][0].equals(tablero[1][1]) && tablero[1][1].equals(tablero[2][2]) )) {
            ganador = true;
        }
        // Diagonal de derecha a izquierda
        else if ( (tablero[0][2] != (null)) && (tablero[0][2].equals(tablero[1][1]) && tablero[1][1].equals(tablero[2][0]) )) {
            ganador = true;
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

    // Se ejecuta cuando hay un ganador
    private void hayGanador() {

    }
}

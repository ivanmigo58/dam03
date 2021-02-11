package sample.Juego;

import java.util.ArrayList;
import java.util.List;

public class Juego {

    private List<String> botonesSeleccionados = new ArrayList<>();
    private int modoJuego;

    public Juego(int modoJuego) {
        this.modoJuego = modoJuego;
    }


    private enum Turno {
        X, O;
    }

    private Turno tablero[][] = new Turno[3][3];
    Turno turno = Turno.X;

    // Reviso si ese boton ya se ha pulsado
    boolean botonPulsado(String id) {
        boolean pulsado = false;
        for (String boton : botonesSeleccionados) {
            if (boton.equals(id)) {
                pulsado = true;
                break;
            }
        }
        botonesSeleccionados.add(id);
        // Cambio la B
        id = id.replace("B", "");
        // Lo ponemos en el tablero como pulsado
        tablero[Integer.parseInt(String.valueOf(id.charAt(0)))][Integer.parseInt(String.valueOf(id.charAt(1)))] = turno;
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
}

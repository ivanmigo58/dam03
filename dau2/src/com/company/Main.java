package com.company;

import java.util.Scanner;

public class Main {
    public static Scanner scanner;

    public Main() {
    }

    public static int menu() {
        System.out.println("-----------------");
        System.out.println("1 - Tirar dados");
        System.out.println("0 - Acabar");
        System.out.println("-----------------");
        System.out.println("Escoge: ");
        return scanner.nextInt();
    }

    public static void main(String[] args) {
	    int lanzamientos = 0;
	    int ganadas = 0;
	    JocDaus joc = new JocDaus();

        for (int opciones = menu(); opciones != 0; opciones = menu()) {
            ++lanzamientos;
            boolean b = joc.jugar();
            System.out.println(joc);
            if (b) {
                System.out.println("Has ganado!");
                ++ganadas;
            }
        }

        System.out.println("En " + lanzamientos + " lanzamientos");
        System.out.println("Has ganado " + ganadas + " partidas");
    }


    static {
        scanner = new Scanner((System.in));
    }
}

package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] intervalo = new int[12];

        System.out.println("Cuantas tiradas quieres hacer? ");
        int tiradas = scanner.nextInt();

        int dado1;
        int dado2;

        for (dado1 = 0; dado1 < 12; dado1++) {
            intervalo[dado1] = 0;
        }

        int i;
        for ( i = 0; i < tiradas; i++) {
            dado1 = (int) Math.floor(Math.random()*6+1);
            dado2 = (int) Math.floor(Math.random()*6+1);
            int sumaDados = dado1 + dado2;
            ++intervalo[sumaDados - 1];
        }

        System.out.println("Resultado: ");

        for(i = 1; i < 12; ++i) {
            System.out.println(i + 1 + " ---> " + intervalo[i] + " veces");
        }
    }
}

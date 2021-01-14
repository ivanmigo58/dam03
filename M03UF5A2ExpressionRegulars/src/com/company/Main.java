package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static String[] palabras = {"\\*<]:-DOo", ">:o\\)", "[^\\*]<]:-D"};
    private static String[] palabras2 = {"*<]:-DOo", ">:o)", "<]:-D"};
    private static int[] vecesEncontrado = new int[3];
    private static String[] nombres = {"Pare Noel", "Ren", "Follets"};
    private static File file = new File("/home/ivanmigo58/git/dam03/santaco.txt");


    public static void main(String[] args) throws IOException {
        // regularExpression();
        sinRegularExpression();
    }

    // Hacerlo con la expresion regular
    private static void regularExpression() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        Pattern pattern;
        Matcher matcher;

        String linea = bufferedReader.readLine();
        // Por cada linea
        while (linea != null) {
            int x = 0;
            resetArray();
            // Por cada texto a buscar
            for (String palabra : palabras) {
                pattern = Pattern.compile(palabra);
                matcher = pattern.matcher(linea);
                // Mientras encuentre coincidencia
                while (matcher.find()) {
                    vecesEncontrado[x]++;
                }
                x++;
            }
            mostrarResultados(vecesEncontrado);
            linea = bufferedReader.readLine();
        }
        bufferedReader.close();
    }

    // Sin expresion regular
    private static void sinRegularExpression() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String linea = bufferedReader.readLine();
        // Por cada linea
        while (linea != null) {
            resetArray();
            int x = 0;
            // Por cada una de las palabras
            for (String palabra : palabras2) {
                // Cada letra de la frase
                for (int i = 0; i < linea.length(); i++) {
                    // Siempre que no sea la ultima
                    if (i < linea.length() - 1) {
                        // Cada letra de la palabra
                        for (int j = 0; j < palabra.length(); j++) {
                            if (linea.charAt(i + j) != palabra.charAt(j)) {
                                break;
                            }
                            // Si ha llegado hasta aqui, ha completado la palabra entera
                            if (j == palabra.length() - 1) {
                                // TODO NO FUNCIONA
                                if ( palabra.equals(nombres[2]) && (linea.charAt(i - 1) == palabras2[0].charAt(0)) ) {
                                    break;
                                }
                                vecesEncontrado[x]++;
                            }
                        }
                    }
                }
                x++;
            }
            mostrarResultados(vecesEncontrado);
            linea = bufferedReader.readLine();
        }

        bufferedReader.close();
    }

    // Resetea el array de los numeros
    private static void resetArray() {
        for (int i = 0; i < vecesEncontrado.length; i++) {
            vecesEncontrado[i] = 0;
        }
    }

    // Muestra los resultados
    private static void mostrarResultados(int[] vecesEncontrado) {
        for (int i = 0; i < vecesEncontrado.length; i++) {
            // Siempre que haya un resultado
            if (vecesEncontrado[i] > 0) {
                System.out.print(nombres[i] + "(" + vecesEncontrado[i] + ") ");
            }
        }
        System.out.println("");

    }
}

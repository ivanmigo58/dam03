package com.company.Operaciones;

import com.company.Calle;

import java.util.*;

public class Consultar {

        private Scanner scanner = new Scanner(System.in);

        public void menu(List<Calle> calleList) {
            boolean salir = false;
            while (!salir) {

                System.out.println("CONSULTAR");
                System.out.println("----------------");
                System.out.println("1. Consultar todos los datos");
                System.out.println("2. Consultar calle");
                System.out.println("3. Consultar por tipo de via");
                System.out.println("0. Volver");

                System.out.print("Escoge una opcion: ");
                int opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        consultarCallesAlfabeticamente(calleList);
                        break;
                    case 2:
                        consultarDatosCalle(calleList);
                        break;
                    case 3:
                        consultarTipoVia(calleList);
                        break;
                    case 0:
                        salir = true;
                        break;

                }
            }
        }




    private void consultarCallesAlfabeticamente(List<Calle> calleList) {
        System.out.println("1. Ordenador alfabeticamente las calles de la A-Z");
        System.out.println("2. Ordenador alfabeticamente las calles de la Z-A");
        System.out.print("Escoge una opcion: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        // Lista de nombres ordenados alfabeticamente
        List<String> alfabeticamenteOrdenado = new ArrayList<>();
        for (Calle calle: calleList) {
            alfabeticamenteOrdenado.add(calle.getNom_curt());
        }

        // De la A-Z
        if (opcion == 1) {
            Collections.sort(alfabeticamenteOrdenado);
            for (String calleNombre : alfabeticamenteOrdenado) {
                System.out.println(calleNombre);
            }
        }

        // De la Z-A
        if (opcion == 2) {
            Collections.sort(alfabeticamenteOrdenado);
            for (int i = alfabeticamenteOrdenado.size() -1 ; i >= 0; i--) {
                System.out.println(alfabeticamenteOrdenado.get(i));
            }
        }
    }

    private void consultarDatosCalle(List<Calle> calleList) {
        System.out.print("Introduce el nombre corto de la calle: ");
        String nombreCalle = scanner.nextLine();
        calleList.stream().filter(calle -> calle.getNom_curt().equals(nombreCalle)).forEach(System.out::println);
    }

    private void consultarTipoVia(List<Calle> calleList) {
        System.out.println("1. Mostrar todas las calles dependiendo el tipo de via");
        System.out.println("2. Contar las calles que tienen cada tipo de via");
        System.out.print("Escoge una opcion: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Escribe el tipo de via: ");
        String tipoVia = scanner.nextLine();

        // Mostrar todas las calles de ese tipo de via
        if (opcion == 1) {
            calleList.stream().filter(calle -> calle.getTipus_via().equals(tipoVia)).forEach(System.out::println);
        }
        // Contar las calles de un tipo de via
        else if (opcion == 2) {
            int numeroCalles = (int) calleList.stream().filter(calle -> calle.getTipus_via().equals(tipoVia)).count();
            System.out.println("Hay " + numeroCalles + " con el tipo de via " + tipoVia + " en Barcelona.");
        }
    }
}

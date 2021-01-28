package com.company;

import com.company.Operaciones.Consultar;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    List<Calle> calleList;

    public Menu(List<Calle> calleList) {
        this.calleList = calleList;
    }

    public void menuPrincipal() {
        while (true) {
            System.out.println("\n     MENU CALLES BCN    ");
            System.out.println("-------------------------------------");
            System.out.println("\t1. Consultar datos calles BCN");
            System.out.print("Escoge la opcion que deses: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    new Consultar().menu(calleList);
                    break;
            }
        }
    }
}

package com.company;

import com.company.Control.OperacionsBanc;
import com.company.Exceptions.*;
import com.company.Model.Client;
import com.company.Model.CompteEstalvi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    // Lista de cuentas
    public static ArrayList<CompteEstalvi> compteEstalviList = new ArrayList<>();
    private List<Client> clientList = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public void menuPrincipal() {
        while (true) {
            System.out.println("------------------");
            System.out.println("  MENU PRINCIPAL  ");
            System.out.println("------------------");
            System.out.println("1. Clientes");
            System.out.println("2. Cuentas");
            System.out.println("0. Salir");

            System.out.print("Escoge una opcion: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion == 1) {
                menuClientes();
            } else if (opcion == 2) {
                menuCuentas();
            } else if (opcion == 0) {
                break;
            }
        }

        System.out.println("HASTA LA PROXIMA...");

    }

    private void menuClientes() {
        Client client;
        while (true) {
            System.out.println("CLIENTES");
            System.out.println("---------");
            System.out.println("1. Crear cliente");
            System.out.println("2. Mostrar clientes");
            System.out.println("0. Volver");
            System.out.print("Escoge una opcion: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();
            // Crea cliente
            if (opcion == 1) {
                System.out.println("--- NUEVO CLIENTE ---");
                System.out.print("Escribe el nombre: ");
                String nombre = scanner.nextLine();
                System.out.print("Escribe los apellidos: ");
                String apellidos = scanner.nextLine();
                System.out.print("Escribe el DNI: ");
                String dni = scanner.nextLine();

                try {
                    client = new Client(nombre, apellidos, dni);
                    clientList.add(client);
                    System.out.println("Cliente creado!");
                } catch (ClientAccountException e) {
                    System.out.println(e.getMessage());
                }
                break;
            } else if (opcion == 2) {
                mostrarClientes();
            } else if (opcion == 0) {
                break;
            }
        }
    }

    private void menuCuentas() {
        while (true) {
            System.out.println("CUENTAS");
            System.out.println("--------");
            System.out.println("1. Crear cuenta");
            System.out.println("2. Operar en cuenta");
            System.out.println("3. Mostrar cuentas");
            System.out.println("0. Volver");
            System.out.print("Escoge una opcion: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            // Nueva cuenta
            if (opcion == 1) {
                System.out.println("--- NUEVA CUENTA ---");
                System.out.print("Numero de cuenta: ");
                String numeroCuenta = scanner.nextLine();
                CompteEstalvi compteEstalvi = new CompteEstalvi(numeroCuenta);
                compteEstalviList.add(compteEstalvi);
                System.out.println("Cuenta creada.");
            } else if (opcion == 2) {
                System.out.print("Escribe el numero de la cuenta sobre el que deseas operar: ");
                String numeroCuenta = scanner.nextLine();
                // Compruebo que exista el numero de cuenta
                try {
                    CompteEstalvi compteEstalvi = OperacionsBanc.verifyAccount(numeroCuenta);
                    menuOperarCuenta(compteEstalvi);
                } catch (AccountNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            } else if (opcion == 3) {
                mostrarCuentas();
            } else if (opcion == 0) {
                break;
            }

        }
    }

    private void menuOperarCuenta(CompteEstalvi compteEstalvi) {
        while (true) {
            System.out.println("OPERAR EN CUENTA");
            System.out.println("- - - - - - - - - -");
            System.out.println("1. Añadir cliente a cuenta");
            System.out.println("2. Eliminar cliente de cuenta");
            System.out.println("3. Ingresar dinero");
            System.out.println("4. Retirar dinero");
            System.out.println("5. Realizar transferencia");
            System.out.println("0. Volver");

            System.out.print("Escoge una opcion: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            // Añadir cliente a cuenta
            if (opcion == 1) {
                try {
                    System.out.print("Escribe el DNI del cliente que deseas agregar a esta cuenta: ");
                    String dni = scanner.nextLine();
                    Client client = OperacionsBanc.buscarCliente(dni, clientList);
                    // Añado el cliente a la cuenta
                    compteEstalvi.addUser(client);
                    System.out.println("Cliente añadido a la cuenta "+compteEstalvi.getNumCompte()+" con exito.");
                } catch (ClientNotFound clientNotFound) {
                    System.out.println(clientNotFound.getMessage());
                }
            }
            // Eliminar cliente de cuenta
            else if (opcion == 2) {
                try {
                    System.out.print("Escribe el DNI del cliente que deseas eliminar de esa cuenta: ");
                    String dni = scanner.nextLine();
                    // Compruebo que exista el dni del cliente
                    OperacionsBanc.buscarCliente(dni, clientList);
                    compteEstalvi.removeUser(dni);
                    System.out.println("Usuario retirado de la cuenta " + compteEstalvi.getNumCompte());
                } catch (BankAccountException e) {
                    System.out.println(e.getMessage());
                } catch (ClientNotFound clientNotFound) {
                    System.out.println(clientNotFound.getMessage());
                }
            }
            // Ingresar dinero
            else if (opcion == 3) {
                System.out.print("Dinero a ingresar: ");
                double dinero = scanner.nextDouble();
                scanner.nextLine();
                compteEstalvi.ingressar(dinero);
                System.out.println("Dinero ingresado con exito.");
            }
            // Retirar dinero
            else if (opcion == 4) {
                System.out.print("Dinero a retirar: ");
                double dinero = scanner.nextDouble();
                scanner.nextLine();
                try {
                    compteEstalvi.treure(dinero);
                    System.out.println("Dinero retirado con exito.");
                } catch (BankAccountException e) {
                    System.out.println(e.getMessage());
                }
            }
            // Hacer transferencia
            else if (opcion == 5) {
                System.out.print("Escribe el numero de cuenta del destinatario: ");
                String cuentaDestinatario = scanner.nextLine();
                try {
                    CompteEstalvi compteDestinatario = OperacionsBanc.verifyAccount(cuentaDestinatario);
                    System.out.print("Dinero a trasnferir: ");
                    double dinero = scanner.nextDouble();
                    scanner.nextLine();
                    compteEstalvi.transferencia(cuentaDestinatario, dinero);
                    System.out.println("Transferencia realizada con exito");
                } catch (TransferException e) {
                    System.out.println(e.getMessage());
                } catch (AccountNotFoundException e) {
                    e.printStackTrace();
                }
            }

            else if (opcion == 0) {
                break;
            }
        }
    }

    // Muestra los clientes
    private void mostrarClientes() {
        for (Client client : clientList) {
            System.out.println("Nombre: " + client.getNom() + " - Apellidos: " + client.getCognoms() + " - DNI: " + client.getDNI());
        }
    }

    // Muestra todas las cuentas y los datos de ella
    private void mostrarCuentas() {
        for (CompteEstalvi compteEstalvi : compteEstalviList) {
            System.out.println("-----------------------");
            System.out.println("Numero de cuenta: " + compteEstalvi.getNumCompte());
            System.out.println("Clientes:");
            for (Client client : compteEstalvi.getLlista_usuaris()) {
                System.out.println("\t- Nombre: " + client.getNom() + " - DNI: " + client.getDNI());
            }
            System.out.println("\nSaldo: " + compteEstalvi.getSaldo());
            System.out.println("-----------------------");
        }
    }


}

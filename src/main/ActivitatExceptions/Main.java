package ActivitatExceptions;

import ActivitatExceptions.Model.Client;
import ActivitatExceptions.Model.CompteEstalvi;
import Exceptions.ClientAccountException;


import java.util.HashSet;
import java.util.Scanner;

public class Main {
    private static HashSet<Client> clients;
    private static HashSet<CompteEstalvi> compteEstalvis;
    public static void main(String[] args) throws ClientAccountException {
       Scanner in = new Scanner(System.in);
       clients = new HashSet<>();
       compteEstalvis = new HashSet<>();

       int opcion = 0;
       // Menu caso base
        mainMenu();
        while ((opcion = in.nextInt()) != 3) {
            // Limpiar buffer
            in.nextLine();
            switch (opcion){
                case 1:
                    menuClientes();
                    break;
                case 2:
                    menuCuentas();
                    break;

            }
            mainMenu();
       }

       /*
       try {
           Client clientA = new Client("Adrián", "García", "12345678Z");
           Client clientB = new Client("Pratik", "Patel", "12345678Z");
           clientB.setDNI("12345678A");
       }*/

    }

    private static void mainMenu(){
        System.out.println("Introduce una opcion: \n" +
                "1. Opciones clientes\n" +
                "2. Opciones cuenta\n" +
                "3. Salir\n" +
                "");
    }

    private static void menuClientes() throws ClientAccountException {
        Scanner in = new Scanner(System.in);
        System.out.println("Menu Clientes");
        System.out.println("Introduce una opcion: \n" +
                "1. Añadir cliente\n" +
                "2. Eliminar cliente\n" +
                "3. Volver"
        );
        int opcion = 0;
        while ((opcion = in.nextInt()) != 3){
            // Limpiar buffer
            in.nextLine();
            mainMenu();
            switch (opcion){
                case 1:
                    System.out.print("Introduce nombre, apellidos y dni separandolo con ';'");
                    String[] datosCliente = in.nextLine().split(";");
                    if (datosCliente.length == 3) {
                        clients.add(new Client(datosCliente[0], datosCliente[1], datosCliente[2]));
                        System.out.println("Cliente añadido correctamente");
                    }
                    else System.err.println("Error al añadir el cliente.");

            }
        }
    }

    private static void menuCuentas() throws ClientAccountException {
        Scanner in = new Scanner(System.in);
        System.out.println("Menu Cuentas");
        System.out.println("Introduce una opcion: \n" +
                "1. Añadir cuenta\n" +
                "2. Eliminar cuenta\n" +
                "3. Volver"
        );
        int opcion = 0;
        while ((opcion = in.nextInt()) != 3){
            // Limpiar buffer
            in.nextLine();
            mainMenu();
            switch (opcion){
                case 1:
                    menuClientes();

            }
        }
    }
}

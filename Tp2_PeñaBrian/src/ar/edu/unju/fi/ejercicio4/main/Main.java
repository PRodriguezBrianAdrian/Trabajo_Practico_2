package ar.edu.unju.fi.ejercicio4.main;

import ar.edu.unju.fi.ejercicio4.constantes.Posicion;
import ar.edu.unju.fi.ejercicio4.model.Jugador;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Jugador> jugadores = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        int opcion;
        do {
            System.out.println("Menú de opciones:");
            System.out.println("1 - Alta de jugador");
            System.out.println("2 - Mostrar todos los jugadores");
            System.out.println("3 - Modificar la posición de un jugador");
            System.out.println("4 - Eliminar un jugador");
            System.out.println("5 - Salir");
            System.out.print("Ingrese su opción: ");

            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); 

                switch (opcion) {
                    case 1:
                        altaJugador(jugadores, scanner);
                        break;
                    case 2:
                        mostrarJugadores(jugadores);
                        break;
                    case 3:
                        modificarPosicion(jugadores, scanner);
                        break;
                    case 4:
                        eliminarJugador(jugadores, scanner);
                        break;
                    case 5:
                        System.out.println("Gracias vuelva pronto");
                        break;
                    default:
                        System.out.println("Opción inválida. Intente de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese un número entero.");
                scanner.nextLine(); 
                opcion = 0; 
            }

        } while (opcion != 5);

        scanner.close();
    }

    private static void altaJugador(ArrayList<Jugador> jugadores, Scanner scanner) {
        try {
            System.out.println("Ingrese el nombre del jugador:");
            String nombre = scanner.nextLine();

            System.out.println("Ingrese el apellido del jugador:");
            String apellido = scanner.nextLine();

            System.out.println("Ingrese la fecha de nacimiento del jugador (DD-MM-YYYY):");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate fechaNacimiento = LocalDate.parse(scanner.nextLine(), formatter);

            System.out.println("Ingrese la nacionalidad del jugador:");
            String nacionalidad = scanner.nextLine();

            System.out.println("Ingrese la estatura del jugador:");
            double estatura = scanner.nextDouble();

            System.out.println("Ingrese el peso del jugador:");
            double peso = scanner.nextDouble();

            System.out.println("Ingrese la posición del jugador (DELANTERO, MEDIO, DEFENSA, ARQUERO):");
            String posicionStr = scanner.next();
            Posicion posicion = Posicion.valueOf(posicionStr.toUpperCase());

            Jugador jugador = new Jugador(nombre, apellido, fechaNacimiento, nacionalidad, estatura, peso, posicion);
            jugadores.add(jugador);

            System.out.println("Jugador agregado correctamente.");
        } catch (DateTimeParseException e) {
            System.out.println("Error al ingresar la fecha de nacimiento. Formato incorrecto.");
        } catch (InputMismatchException e) {
            System.out.println("Error: Ingrese un número para la estatura y el peso.");
            scanner.nextLine(); 
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Posición del jugador no válida.");
        }
    }



    private static void mostrarJugadores(ArrayList<Jugador> jugadores) {
        if (jugadores.isEmpty()) {
            System.out.println("No hay jugadores registrados.");
        } else {
            System.out.println("Lista de jugadores:");
            for (Jugador jugador : jugadores) {
                System.out.println(jugador.toString());
            }
        }
    }

    private static void modificarPosicion(ArrayList<Jugador> jugadores, Scanner scanner) {
        System.out.println("Ingrese el nombre del jugador:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese el apellido del jugador:");
        String apellido = scanner.nextLine();

        Iterator<Jugador> iterator = jugadores.iterator();
        boolean encontrado = false;
        while (iterator.hasNext()) {
            Jugador jugador = iterator.next();
            if (jugador.getNombre().equalsIgnoreCase(nombre) && jugador.getApellido().equalsIgnoreCase(apellido)) {
                System.out.println("Ingrese la nueva posición del jugador (DELANTERO, MEDIO, DEFENSA, ARQUERO):");
                String nuevaPosicionStr = scanner.next();
                Posicion nuevaPosicion = Posicion.valueOf(nuevaPosicionStr.toUpperCase());
                jugador.setPosicion(nuevaPosicion);
                encontrado = true;
                System.out.println("Posición modificada correctamente.");
                break;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontró al jugador con ese nombre y apellido.");
        }
    }

    private static void eliminarJugador(ArrayList<Jugador> jugadores, Scanner scanner) {
        System.out.println("Ingrese el nombre del jugador:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese el apellido del jugador:");
        String apellido = scanner.nextLine();

        Iterator<Jugador> iterator = jugadores.iterator();
        boolean encontrado = false;
        while (iterator.hasNext()) {
            Jugador jugador = iterator.next();
            if (jugador.getNombre().equalsIgnoreCase(nombre) && jugador.getApellido().equalsIgnoreCase(apellido)) {
                iterator.remove();
                encontrado = true;
                System.out.println("Jugador eliminado correctamente.");
                break;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontró al jugador con ese nombre y apellido.");
        }
    }
}

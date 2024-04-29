package ar.edu.unju.fi.ejercicio2.main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import ar.edu.unju.fi.ejercicio2.constantes.Mes;
import ar.edu.unju.fi.ejercicio2.model.Efemeride;

public class Main {
    public static void main(String[] args) {
        ArrayList<Efemeride> efemerides = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("1 - Crear efeméride");
            System.out.println("2 - Mostrar efemérides");
            System.out.println("3 - Eliminar efeméride");
            System.out.println("4 - Modificar efeméride");
            System.out.println("5 - Salir");
            System.out.print("Seleccione una opción: ");
            try {
                opcion = scanner.nextInt();
                switch (opcion) {
                    case 1:
                        crearEfemeride(scanner, efemerides);
                        break;
                    case 2:
                        mostrarEfemerides(efemerides);
                        break;
                    case 3:
                        eliminarEfemeride(efemerides, scanner);
                        break;
                    case 4:
                        modificarEfemeride(scanner, efemerides);
                        break;
                    case 5:
                        System.out.println("Gracias Vuelva Pronto");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingrese un número entero.");
                scanner.nextLine();
                opcion = 0;
            }
        } while (opcion != 5);

        scanner.close();
    }

    private static void crearEfemeride(Scanner scanner, ArrayList<Efemeride> efemerides) {
        try {
            System.out.print("Ingrese el código: ");
            int codigo = scanner.nextInt();
            System.out.print("Ingrese el mes (1-12): ");
            int mes = scanner.nextInt();
            while (mes < 1 || mes > 12) {
                System.out.println("Mes no válido. Ingrese un número entre 1 y 12.");
                mes = scanner.nextInt();
            }
            System.out.print("Ingrese el día: ");
            int dia = scanner.nextInt();
            System.out.print("Ingrese el detalle: ");
            scanner.nextLine(); 
            String detalle = scanner.nextLine();

            efemerides.add(new Efemeride(codigo, Mes.values()[mes - 1], dia, detalle));
            System.out.println("Efeméride creada correctamente.");
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Asegúrese de ingresar números en el formato correcto.");
            scanner.nextLine();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Mes no válido.");
        }
    }

    private static void mostrarEfemerides(ArrayList<Efemeride> efemerides) {
        if (efemerides.isEmpty()) {
            System.out.println("No hay efemérides para mostrar.");
        } else {
            System.out.println("Efemérides:");
            for (Efemeride efemeride : efemerides) {
                System.out.println(efemeride.getCodigo() + ": " + efemeride.getMes() + " " + efemeride.getDia() + " - " + efemeride.getDetalle());
            }
        }
    }

    private static void eliminarEfemeride(ArrayList<Efemeride> efemerides, Scanner scanner) {
        if (efemerides.isEmpty()) {
            System.out.println("No hay efemérides para eliminar.");
            return;
        }

        try {
            System.out.print("Ingrese el código de la efeméride a eliminar: ");
            int codigo = scanner.nextInt();
            scanner.nextLine();

            boolean removed = efemerides.removeIf(efemeride -> efemeride.getCodigo() == codigo);

            if (removed) {
                System.out.println("Efeméride eliminada exitosamente.");
            } else {
                System.out.println("No se encontró ninguna efeméride con ese código.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Por favor ingrese un valor numérico válido.");
            scanner.nextLine();
        }
    }

    private static void modificarEfemeride(Scanner scanner, ArrayList<Efemeride> efemerides) {
        if (efemerides.isEmpty()) {
            System.out.println("No hay efemérides para modificar.");
        } else {
            mostrarEfemerides(efemerides);
            System.out.print("Ingrese el código de la efeméride que desea modificar: ");
            try {
                int codigo = scanner.nextInt();

                Efemeride efemerideAModificar = null;
                for (Efemeride efemeride : efemerides) {
                    if (efemeride.getCodigo() == codigo) {
                        efemerideAModificar = efemeride;
                        break;
                    }
                }

                if (efemerideAModificar != null) {
                    System.out.println("Ingrese los nuevos datos:");
                    System.out.print("Nuevo mes (1-12): ");
                    int mes = scanner.nextInt();
                    while (mes < 1 || mes > 12) {
                        System.out.println("Mes no válido. Ingrese un número entre 1 y 12.");
                        mes = scanner.nextInt();
                    }  
                } 
            } catch (InputMismatchException e) {
                System.out.println("Error: Por favor ingrese un valor numérico válido.");
                scanner.nextLine();
            }
        }
    }
}
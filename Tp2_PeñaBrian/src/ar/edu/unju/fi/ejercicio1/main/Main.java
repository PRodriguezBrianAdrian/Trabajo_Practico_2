
package ar.edu.unju.fi.ejercicio1.main;

import ar.edu.unju.fi.ejercicio1.model.Producto;
import ar.edu.unju.fi.ejercicio1.model.Producto.Categoria;
import ar.edu.unju.fi.ejercicio1.model.Producto.OrigenDeFabricacion;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		ArrayList<Producto> productos = new ArrayList<>();
		Scanner scanner = new Scanner(System.in);
		int opcion = 0;
		do {

			System.out.println("1) Crear producto");
			System.out.println("2) Mostrar productos");
			System.out.println("3) Modificar producto");
			System.out.println("4) Salir");

			System.out.print("Opcion: ");
			try {
				opcion = scanner.nextInt();
				scanner.nextLine();
				switch (opcion) {
				case 1:
					crearProducto(scanner, productos);
					break;
				case 2:
					mostrarProductos(productos);
					break;
				case 3:
					modificarProducto(scanner, productos);
					break;
				case 4:
					System.out.println("Adios");
					break;
				default:
					System.out.println("Opcion invalida");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Error: Debe ingresar un número entero.");
				scanner.nextLine();
			} catch (Exception e) {
				System.out.println("Error inesperado: " + e.getMessage());
			}
		} while (opcion != 4);
		scanner.close();
	}

	private static void crearProducto(Scanner scanner, ArrayList<Producto> productos) {
		try {

			System.out.print("Codigo: ");
			String codigo = scanner.next();
			scanner.nextLine();
			System.out.print("Descripcion: ");
			String descripcion = scanner.nextLine();
			System.out.print("Precio unitario: ");
			double precioUnitario = scanner.nextDouble();
			scanner.nextLine();

			System.out.println("1 - Telefonía");
			System.out.println("2 - Informática");
			System.out.println("3 - Electrohogar");
			System.out.println("4 - Herramientas");
			System.out.print("Elija una opción: ");
			int opcionCategoria = scanner.nextInt();
			Categoria categoria = null;
			switch (opcionCategoria) {
			case 1:
				categoria = Categoria.TELEFONIA;
				break;
			case 2:
				categoria = Categoria.INFORMATICA;
				break;
			case 3:
				categoria = Categoria.ELECTROHOGAR;
				break;
			case 4:
				categoria = Categoria.HERRAMIENTAS;
				break;
			default:
				System.out.println("Opción inválida");
				return;
			}

			System.out.println("1 - Argentina");
			System.out.println("2 - China");
			System.out.println("3 - Brasil");
			System.out.println("4 - Uruguay");
			System.out.print("Elija una opción: ");
			int opcionOrigen = scanner.nextInt();
			OrigenDeFabricacion origenDeFabricacion = null;
			switch (opcionOrigen) {
			case 1:
				origenDeFabricacion = OrigenDeFabricacion.ARGENTINA;
				break;
			case 2:
				origenDeFabricacion = OrigenDeFabricacion.CHINA;
				break;
			case 3:
				origenDeFabricacion = OrigenDeFabricacion.BRASIL;
				break;
			case 4:
				origenDeFabricacion = OrigenDeFabricacion.URUGUAY;
				break;
			default:
				System.out.println("Opción inválida");
				return;
			}
			Producto producto = new Producto(codigo, descripcion, precioUnitario, categoria, origenDeFabricacion);
			productos.add(producto);
		} catch (InputMismatchException e) {
			System.out.println("Error: Debe ingresar un número entero.");
			scanner.nextLine();
		} catch (Exception e) {
			System.out.println("Error inesperado: " + e.getMessage());
		}
	}

	private static void mostrarProductos(ArrayList<Producto> productos) {
		for (Producto p : productos) {
			System.out.println("Código: " + p.getCodigo());
			System.out.println("Descripción: " + p.getDescripcion());
			System.out.println("Precio Unitario: " + p.getPrecioUnitario());
			System.out.println("Categoría: " + p.getCategoria());
			System.out.println("Origen de Fabricación: " + p.getOrigenDeFabricacion());

		}
	}

	private static void modificarProducto(Scanner scanner, ArrayList<Producto> productos) {
		try {

			System.out.print("Codigo del producto a modificar: ");
			String codigoModificar = scanner.next();
			scanner.nextLine();
			for (Producto p : productos) {
				if (p.getCodigo().equals(codigoModificar)) {
					System.out.print("Descripcion: ");
					p.setDescripcion(scanner.nextLine());
					System.out.print("Precio unitario: ");
					p.setPrecioUnitario(scanner.nextDouble());

					System.out.println("1) Telefonía");
					System.out.println("2) Informática");
					System.out.println("3) Electrohogar");
					System.out.println("4) Herramientas");
					System.out.print("Elija una opción: ");
					int opcionCategoria = scanner.nextInt();
					Categoria categoria = null;
					switch (opcionCategoria) {
					case 1:
						categoria = Categoria.TELEFONIA;
						break;
					case 2:
						categoria = Categoria.INFORMATICA;
						break;
					case 3:
						categoria = Categoria.ELECTROHOGAR;
						break;
					case 4:
						categoria = Categoria.HERRAMIENTAS;
						break;
					default:
						System.out.println("Opción inválida");
						return;
					}

					System.out.println("1) Argentina");
					System.out.println("2) China");
					System.out.println("3) Brasil");
					System.out.println("4) Uruguay");
					System.out.print("Elija una opción: ");
					int opcionOrigenMod = scanner.nextInt();
					OrigenDeFabricacion origenDeFabricacionMod = null;
					switch (opcionOrigenMod) {
					case 1:
						origenDeFabricacionMod = OrigenDeFabricacion.ARGENTINA;
						break;
					case 2:
						origenDeFabricacionMod = OrigenDeFabricacion.CHINA;
						break;
					case 3:
						origenDeFabricacionMod = OrigenDeFabricacion.BRASIL;
						break;
					case 4:
						origenDeFabricacionMod = OrigenDeFabricacion.URUGUAY;
						break;
					default:
						System.out.println("Opción inválida");
						return;
					}
					p.setCategoria(categoria);
					p.setOrigenDeFabricacion(origenDeFabricacionMod);
				}
			}
		} catch (InputMismatchException e) {
			System.out.println("Error: Debe ingresar un número entero.");
			scanner.nextLine();
		} catch (Exception e) {
			System.out.println("Error inesperado: " + e.getMessage());
		}
	}
}
package ar.edu.unju.fi.ejercicio5.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import ar.edu.unju.fi.ejercicio5.model.PagoEfectivo;
import ar.edu.unju.fi.ejercicio5.model.PagoTarjeta;
import ar.edu.unju.fi.ejercicio5.model.Producto;
import ar.edu.unju.fi.ejercicio5.model.Producto.Categoria;
import ar.edu.unju.fi.ejercicio5.model.Producto.OrigenFabricacion;


	public class Main {

	public static void main(String[] args) {
		ArrayList<Producto> productos = new ArrayList<>();
		Scanner scanner = new Scanner(System.in);

		int opcion = 0;
		precargarProductos(productos);

		try {
			do {
				System.out.println("-----MENÚ DE OPCIONES-----");
				System.out.println("1) Mostrar productos");
				System.out.println("2) Realizar compra");
				System.out.println("3) Salir");
				System.out.print("Elija una opción: ");

				try {
					opcion = scanner.nextInt();
					scanner.nextLine();

					switch (opcion) {
					case 1:
						System.out.println("Mostrando productos disponibles:");
						mostrarProductos(productos, scanner);
						break;
					case 2:
						ArrayList<Producto> carrito = new ArrayList<>();
						System.out.println("Ajustando el carrito para su compra: ");
						realizarCompra(productos, carrito, scanner);
						break;
					case 3:
						System.out.println("¡Gracias Vuelva Pronto!");
						break;
					default:
						System.out.println("¡Opción no válida! Por favor, intente nuevamente.");
					}
				} catch (InputMismatchException e) {
					System.out.println("Error: por favor ingresa un número entero válido.");
					scanner.nextLine(); 
				}
			} while (opcion != 3);
		} finally {
			scanner.close(); 
		}
	}

	public static void realizarCompra(ArrayList<Producto> productos, ArrayList<Producto> carrito, Scanner scanner) {
		boolean continuarCompra = true;

		while (continuarCompra) {
			boolean agregarProducto = true;

			while (agregarProducto) {
				try {
					System.out.println("¿Desea agregar algún producto al carrito? (Ingrese 'S' para sí, 'N' para no):");
					String respuesta = scanner.next().toUpperCase();
					scanner.nextLine(); 

					if (respuesta.equals("N")) {
						continuarCompra = false;
						break;
					}

					System.out.println(
							"Por favor, ingrese el número correspondiente al producto que desea agregar al carrito:");
					int indiceProducto = scanner.nextInt();
					scanner.nextLine(); 

					if (indiceProducto < 1 || indiceProducto > productos.size()) {
						System.out.println("¡Ups! El número de producto ingresado es inválido.");
						continue;
					}

					Producto productoSeleccionado = productos.get(indiceProducto - 1);
					if (!productoSeleccionado.getEstado()) {
						System.out.println(
								"¡Ups! El producto seleccionado no se encuentra disponible en nuestro stock y no pudimos agregarlo al carrito.");
						continue;
					}

					carrito.add(productoSeleccionado);
					System.out.println(productoSeleccionado.getDescripcion() + " $"
							+ productoSeleccionado.getPrecioUnitario() + " ha sido agregado al carrito.");
				} catch (InputMismatchException e) {
					System.out.println("Error: por favor ingresa un número entero válido.");
					scanner.nextLine();
				}
			}

			double totalCompra = calcularTotal(carrito);

			if (totalCompra == 0) {
				System.out.println(
						"No hay productos en su carrito ¿Desea comprar algo?");
				System.out.println("1) Sí");
				System.out.println("2) No, volver al menú anterior");

				try {
					int opcion = scanner.nextInt();
					if (opcion == 2) {
						System.out.println("Volviendo al menú Principal...");
						return;
					}
				} catch (InputMismatchException e) {
					System.out.println("Error: por favor ingresa un número entero válido.");
					scanner.nextLine();
				}
			} else {
				try {
					System.out.println("Productos seleccionados:");
					for (Producto producto : carrito) {
						System.out.println("- " + producto.getDescripcion() + ": $" + producto.getPrecioUnitario());
					}
					System.out.println("El total de la compra es: $" + totalCompra);
					System.out.println("Por favor, seleccione el método de pago:");
					System.out.println("1) Pago en efectivo");
					System.out.println("2) Pago con tarjeta");
					System.out.print("Seleccione una opción de pago: ");

					int opcionPago = scanner.nextInt();

					switch (opcionPago) {
					case 1:
						procesarPagoEfectivo(totalCompra, scanner);
						continuarCompra = false;
						break;
					case 2:
						procesarPagoTarjeta(totalCompra, scanner);
						continuarCompra = false;
						break;
					default:
						System.out.println("¡La opción de pago seleccionada no es válida!");
					}
				} catch (InputMismatchException e) {
					System.out.println("Error: por favor ingresa un número entero válido.");
					scanner.nextLine(); 
				}
			}
		}
	}

	private static void procesarPagoEfectivo(double totalCompra, Scanner scanner) {
		try {
			PagoEfectivo pagoEfectivo = new PagoEfectivo(totalCompra, LocalDate.now());
			pagoEfectivo.realizarPago(totalCompra);
			pagoEfectivo.imprimirRecibo();
			scanner.nextLine(); 
		} catch (Exception e) {
			System.out.println("¡Error en el pago en efectivo!");
			System.out.println("Hemos detectado un error al pagar, por favor, intentelo nuevamente .");
		}
	}

	private static void procesarPagoTarjeta(double totalCompra, Scanner scanner) {
		System.out.println("Por favor, ingrese el número de tarjeta:");
		String numeroTarjeta = scanner.next();

		try {
			PagoTarjeta pagoTarjeta = new PagoTarjeta(numeroTarjeta, LocalDate.now(), totalCompra);
			pagoTarjeta.realizarPago(totalCompra);
			pagoTarjeta.imprimirRecibo();
			scanner.nextLine(); 
		} catch (Exception e) {
			System.out.println("¡Error en el pago con tarjeta!");
			System.out.println(
					"¡Ups! Numero de tarjeta invalida, intente ingresando nuevamente o utilice otra tarjeta.");
		}
	}

	public static double calcularTotal(ArrayList<Producto> carrito) {
		double total = 0;
		for (Producto producto : carrito) {
			total += producto.getPrecioUnitario() * cantidadDelProductoEnElCarrito(producto, carrito);
		}
		return total;
	}

	public static int cantidadDelProductoEnElCarrito(Producto producto, ArrayList<Producto> carrito) {
		int cantidad = 0;
		for (Producto p : carrito) {
			if (p.equals(producto)) {
				cantidad++;
			}
		}
		return cantidad;
	}

	public static void precargarProductos(ArrayList<Producto> productos) {
		if (productos.isEmpty()) {
			productos.add(new Producto("1", "Heladera Philips", 1200000, OrigenFabricacion.ARGENTINA,Categoria.ELECTROHOGAR, true));
			productos.add(new Producto("2", "Smartwatch Samsung", 250000, OrigenFabricacion.CHINA,Categoria.TELEFONIA, true));
			productos.add(new Producto("3", "Tablet HP", 1800000, OrigenFabricacion.ARGENTINA, Categoria.INFORMATICA,true));
			productos.add(new Producto("4", "Taladro Black & Decker", 95000, OrigenFabricacion.CHINA,Categoria.HERRAMIENTAS, true));
			productos.add(new Producto("5", "TV LG", 1700000, OrigenFabricacion.URUGUAY, Categoria.ELECTROHOGAR, true));
			productos.add(new Producto("6", "Teclado Logitech", 45000, OrigenFabricacion.CHINA, Categoria.INFORMATICA,true));
			productos.add(new Producto("7", "Cafetera Oster", 80000, OrigenFabricacion.BRASIL, Categoria.ELECTROHOGAR,false));
			productos.add(new Producto("8", "Horno eléctrico", 140000, OrigenFabricacion.URUGUAY,Categoria.ELECTROHOGAR, true));
			productos.add(new Producto("9", "Impresora Epson", 110000, OrigenFabricacion.CHINA, Categoria.INFORMATICA,true));
			productos.add(new Producto("10", "Martillo Stanley", 60000, OrigenFabricacion.CHINA,Categoria.HERRAMIENTAS, true));
			productos.add(new Producto("11", "Refrigerador Mabe", 1300000, OrigenFabricacion.BRASIL,Categoria.ELECTROHOGAR, false));
			productos.add(new Producto("12", "Router TP-Link", 75000, OrigenFabricacion.CHINA, Categoria.INFORMATICA,true));
			productos.add(new Producto("13", "Aspiradora Electrolux", 95000, OrigenFabricacion.URUGUAY,Categoria.ELECTROHOGAR, false));
			productos.add(new Producto("14", "Destornillador Phillips", 25000, OrigenFabricacion.CHINA,Categoria.HERRAMIENTAS, false));
			productos.add(new Producto("15", "Licuadora Philips", 70000, OrigenFabricacion.ARGENTINA,Categoria.ELECTROHOGAR, false));
		}
	}

	public static void mostrarProductos(ArrayList<Producto> productos, Scanner sc) {
		System.out.println("LISTA DE PRODUCTOS");
		for (Producto p : productos) {
			System.out.println("===================================");
			System.out.println("Código: " + p.getCodigo());
			System.out.println("Descripción: " + p.getDescripcion());
			System.out.println("Precio: $" + p.getPrecioUnitario());
			System.out.println("Estado: " + (p.getEstado() ? "Disponible" : "No disponible"));
			System.out.println("Categoría: " + p.getCategoria());
			System.out.println("Origen: " + p.getOrigenFabricacion());
			System.out.println("===================================");
		}
	}

}
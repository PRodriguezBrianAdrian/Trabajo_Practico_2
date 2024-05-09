package ar.edu.unju.fi.ejercicio7.main;

import ar.edu.unju.fi.ejercicio7.model.Producto.Categoria;
import ar.edu.unju.fi.ejercicio7.model.Producto.OrigenFabricacion;
import ar.edu.unju.fi.ejercicio7.model.Producto;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		ArrayList<Producto> productos = new ArrayList<>();

		precargarProductos(productos);

		Scanner scanner = new Scanner(System.in);
		int opcion;

		do {
			System.out.println(">>>>> Menú <<<<<");
			System.out.println("1) Mostrar productos (solo con estado true)");
			System.out.println("2) Mostrar productos faltantes (estado false)");
			System.out.println("3) Incrementar precios de los productos en un 20%");
			System.out.println("4) Mostrar productos de la categoría Electrohogar y disponibles");
			System.out.println("5) Ordenar productos por precio de forma descendente");
			System.out.println("6) Mostrar nombres de productos en mayúsculas");
			System.out.println("0) Salir");
			System.out.print("Ingrese una opción: ");
			opcion = scanner.nextInt();

			switch (opcion) {
			case 1:
			
				productos.stream().filter(Producto::isEstado).forEach(producto -> {
					System.out.println("Código: " + producto.getCodigo());
					System.out.println("Descripción: " + producto.getDescripcion());
					System.out.println("Precio Unitario: " + producto.getPrecioUnitario());
					System.out.println("Categoría: " + producto.getCategoria());
					System.out.println("Origen de Fabricación: " + producto.getOrigenFabricacion());
					System.out.println("Estado: " + producto.isEstado());
					System.out.println();
				});
				break;
			case 2:
				
				productos.stream().filter(producto -> !producto.isEstado()).forEach(producto -> {
					System.out.println("Código: " + producto.getCodigo());
					System.out.println("Descripción: " + producto.getDescripcion());
					System.out.println("Precio Unitario: " + producto.getPrecioUnitario());
					System.out.println("Categoría: " + producto.getCategoria());
					System.out.println("Origen de Fabricación: " + producto.getOrigenFabricacion());
					System.out.println("Estado: " + producto.isEstado());
					System.out.println();
				});
				break;
			case 3:
				
				ArrayList<Producto> productosIncrementados = new ArrayList<>();
				productos.stream().map(producto -> {
					double nuevoPrecio = producto.getPrecioUnitario() * 1.2;
					Producto p = new Producto(producto.getCodigo(), producto.getDescripcion(), nuevoPrecio,
							producto.getOrigenFabricacion(), producto.getCategoria(), producto.isEstado());
					productosIncrementados.add(p);
					return p;
				}).forEach(producto -> {
					System.out.println("Código: " + producto.getCodigo());
					System.out.println("Descripción: " + producto.getDescripcion());
					System.out.println("Precio con 20% de aumento: " + producto.getPrecioUnitario());
					System.out.println("Categoría: " + producto.getCategoria());
					System.out.println("Origen de Fabricación: " + producto.getOrigenFabricacion());
					System.out.println("Estado: " + producto.isEstado());
					System.out.println();
				});
				break;
			case 4:
			
				productos.stream().filter(
						producto -> producto.getCategoria() == Producto.Categoria.ELECTROHOGAR && producto.isEstado())
						.forEach(producto -> {
							System.out.println("Código: " + producto.getCodigo());
							System.out.println("Descripción: " + producto.getDescripcion());
							System.out.println("Precio Unitario: " + producto.getPrecioUnitario());
							System.out.println("Categoría: " + producto.getCategoria());
							System.out.println("Origen de Fabricación: " + producto.getOrigenFabricacion());
							System.out.println("Estado: " + producto.isEstado());
							System.out.println();
						});
				break;
			case 5:
			
				productos.stream().sorted((p1, p2) -> Double.compare(p2.getPrecioUnitario(), p1.getPrecioUnitario()))
						.forEach(producto -> {
							System.out.println("Código: " + producto.getCodigo());
							System.out.println("Descripción: " + producto.getDescripcion());
							System.out.println("Precio Unitario: " + producto.getPrecioUnitario());
							System.out.println("Categoría: " + producto.getCategoria());
							System.out.println("Origen de Fabricación: " + producto.getOrigenFabricacion());
							System.out.println("Estado: " + producto.isEstado());
							System.out.println();
						});
				break;
			case 6:
			
				productos.stream().map(producto -> {
					producto.setDescripcion(producto.getDescripcion().toUpperCase());
					return producto;
				}).forEach(producto -> {
					System.out.println("Código: " + producto.getCodigo());
					System.out.println("Descripción: " + producto.getDescripcion());
					System.out.println("Precio Unitario: " + producto.getPrecioUnitario());
					System.out.println("Categoría: " + producto.getCategoria());
					System.out.println("Origen de Fabricación: " + producto.getOrigenFabricacion());
					System.out.println("Estado: " + producto.isEstado());
					System.out.println();
				});
				break;
			case 0:

				System.out.println("Saliendo del programa... Gracias, Vuelva Pronto");
				break;
			default:
				System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
				break;
			}
		} while (opcion != 0);

		scanner.close();
	}

	private static void precargarProductos(ArrayList<Producto> productos) {
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
}
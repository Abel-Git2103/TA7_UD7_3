
import java.util.Hashtable;

import javax.swing.JOptionPane;

public class SupermercadoAPP {

	// DECLARAR VARIABLES
	static int IVA = 0;
	static int cantidad = 0;
	static String pedirValor = "";
	static double precioIVA = 0;
	static double precioBruto = 0;
	static double cantidadPaga = 0;
	static String articulo = "";

	public static void main(String[] args) {

		// CREAR HASHTABLE
		Hashtable<String, Double> stock = new Hashtable<String, Double>();

		// LLAMAR METODOS DESDE EL MAIN

		productosCarro();

		anadirArticulos(stock);

		seleccionaIVA();

		calculoIVA();

		cantidadPagada();

		funcionPrograma(stock);
	}

	// METODO PARA CONTABILIZAR CUANTOS ARTICULOS LLEVO EN EL CARRO
	public static void productosCarro() {

		pedirValor = JOptionPane.showInputDialog("¿Cuantos articulos llevas en el carro?");
		cantidad = Integer.parseInt(pedirValor);

		System.out.println("En el carro llevas " + cantidad + " productos");

	}

	// METODO PARA ALMACENAR LOS PRECIOS EN EL DICCIONARIO DE DATOS STOCK Y LAS
	// UNIDADES DE LOS PRODUCTOS
	public static void anadirArticulos(Hashtable<String, Double> stock) {

		// CREAR HASHTABLE

		double articulos[] = new double[cantidad];

		for (int i = 0; i < cantidad; i++) {
			articulo = JOptionPane.showInputDialog("Introduce articulo");
			pedirValor = JOptionPane.showInputDialog("Introduce precio del articulo");
			articulos[i] = Double.parseDouble(pedirValor);
			System.out.println("\nEl precio del articulo " + "es de " + articulos[i] + "€");

			stock.put(articulo, articulos[i]);

			pedirValor = JOptionPane.showInputDialog("¿Cuantas unidades llevas del articulo?");
			int unidadesArticulo = Integer.parseInt(pedirValor);
			System.out.println("Del articulo " + articulo + " llevas " + unidadesArticulo + " unidades");
			articulos[i] *= unidadesArticulo;
			precioBruto += articulos[i];
		}
		System.out.println("\nEl precio total bruto de los articulos comprados es de " + precioBruto + "€");

	}

	// METODO QUE PERMITE SELECCIONAR EL IVA
	public static void seleccionaIVA() {

		do {
			pedirValor = JOptionPane.showInputDialog("¿Cuál es el IVA a aplicar?");
			IVA = Integer.parseInt(pedirValor);
			if (IVA == 4 || IVA == 21) {
				System.out.println("Has seleccionado el " + IVA + "% de IVA");
			} else {
				System.out.println("El IVA introducido no es correcto, selecciona 4% o 21%");
			}
		} while (!(IVA == 4) && !(IVA == 21));
	}

	// METODO QUE CALCULA EL IVA APLICADO SEGUN LA SELECCION
	public static void calculoIVA() {

		if (IVA == 4) {
			precioIVA = precioBruto * 1.04;
			System.out.println("\nEl precio total de los articulos con 4% de IVA es de " + precioIVA + "€");

		} else if (IVA == 21) {
			precioIVA = precioBruto * 1.21;
			System.out.println("\nEl precio total de los articulos con el 21% de IVA es de " + precioIVA + "€");
		}

	}

	// METODO QUE GUARDA EL PAGO E IMPRIME EL CAMBIO
	public static void cantidadPagada() {

		pedirValor = JOptionPane.showInputDialog("¿Que cantidad vas a pagar?");
		cantidadPaga = Integer.parseInt(pedirValor);
		System.out.println("\nLa cantidad entregada para pagar es " + cantidadPaga + "€");

		if (cantidadPaga > precioIVA) {
			System.out.println("\nEl importe a devolver es de " + (cantidadPaga - precioIVA) + "€");
		} else {
			System.out.println("\nEl importe disponible no es suficiente para comprar los articulos añadidos al carro");
		}
	}

	public static void mostrarArticulo(Hashtable<String, Double> stock) {

		pedirValor = JOptionPane.showInputDialog("Que articulo deseas mostrar?");

		for (String i : stock.keySet()) {
			if (i.equals(pedirValor)) {
				System.out.println("El articulo " + i + " tiene un precio de " + stock.get(i) + "€");
			}
		}
		funcionPrograma(stock);
	}

	public static void mostrarStock(Hashtable<String, Double> stock) {

		for (String i : stock.keySet()) {
			System.out.println("El articulo " + i + " tiene un precio de " + stock.get(i) + "€");
		}
		funcionPrograma(stock);
	}

	public static void funcionPrograma(Hashtable<String, Double> stock) {

		int seleccion;

		do {
			pedirValor = JOptionPane.showInputDialog(
					"¿Que funcion deseas realizar ahora?\n1 - Mostrar un articulo del stock\n2 - Mostrar todos los articulos del stock\n3 - Introducir articulos\n4 - Salir");
			seleccion = Integer.parseInt(pedirValor);

			if (seleccion == 1) {
				mostrarArticulo(stock);
			} else if (seleccion == 2) {
				mostrarStock(stock);
			} else if (seleccion == 3) {
				anadirArticulos(stock);
			}
		} while (seleccion != 4);
	}
}

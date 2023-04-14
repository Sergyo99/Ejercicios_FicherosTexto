package view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Principal {

	final static String NOMBRE_FICHERO = "firmas.txt";

	public static void main(String[] args) {
		try {
			String opcion = "";
			do {
				System.out.print("Opción [L]eer [I]nsertar [S]alir : ");
				Scanner teclado = new Scanner(System.in);
				opcion = teclado.nextLine();
				switch (opcion.toUpperCase()) {
				case "L" -> leer();
				case "I" -> insertar();
				}
			} while (!opcion.equalsIgnoreCase("S"));
		} catch (Exception e) {
			System.err.println("ERROR con el teclado. " + e.getMessage());
		}
		System.out.println("Fin del programa.");
	}

	private static void leer() {
		String lineaLeida = "";
		BufferedReader fichero = null;
		try {
			fichero = new BufferedReader(new FileReader(NOMBRE_FICHERO));
			System.out.println("LIBRO DE FIRMAS");
			lineaLeida = fichero.readLine();
			while (lineaLeida != null) {
				System.out.println(lineaLeida);
				lineaLeida = fichero.readLine();
			}
		}
		catch (FileNotFoundException fnfe) { System.err.println("ERROR con el fichero. NO SE ENCUENTRA. " + fnfe.getMessage()); }
		catch (IOException ioe)            { System.err.println("ERROR con el fichero. LECTURA/ESCRITURA. " + ioe.getMessage());}
		catch (Exception e)                { System.err.println("ERROR GENERAL. " + e.getMessage());                            }
		
		finally {
			try {
				if (fichero != null)
					fichero.close();
			} catch (Exception e) {
				System.err.println("ERROR con el fichero. CIERRE. " + e.getMessage());
			}
		}
	}

	private static void insertar() {
		String firma = "";
		String lineaLeida = "";

		BufferedReader ficheroLectura = null;
		BufferedWriter ficheroEscritura = null;

		try {
			// Pedir al usuario el nombre de la firma
			System.out.print("Firma: ");
			Scanner teclado = new Scanner(System.in);
			firma = teclado.next();

			// Comprobar si esa firma ya existe en el fichero
			ficheroLectura = new BufferedReader(new FileReader(NOMBRE_FICHERO));
			lineaLeida = ficheroLectura.readLine();
			while (lineaLeida != null) {
				if (lineaLeida.equalsIgnoreCase(firma)) {
					throw new RepetidaException();
				}
				lineaLeida = ficheroLectura.readLine();
			}
			ficheroLectura.close();

			// Escribir el nombre de la firma en el fichero (modo append)
			ficheroEscritura = new BufferedWriter(new FileWriter(NOMBRE_FICHERO, true));
			ficheroEscritura.newLine();
			ficheroEscritura.write(firma);
			ficheroEscritura.close();
		}
		
		catch (RepetidaException re)       { System.err.println("Esa firma ya existe.");                                        }
		catch (FileNotFoundException fnfe) { System.err.println("ERROR con el fichero. NO SE ENCUENTRA. " + fnfe.getMessage()); }
		catch (IOException ioe)            { System.err.println("ERROR con el fichero. LECTURA/ESCRITURA. " + ioe.getMessage());}
		catch (Exception e)                { System.err.println("ERROR GENERAL. " + e.getMessage());                            }
		
		finally {
			try {
				if (ficheroLectura != null)   ficheroLectura.close();
				if (ficheroEscritura != null) ficheroEscritura.close();
			} catch (Exception e) {
				System.err.println("ERROR con el fichero. CIERRE. " + e.getMessage());
			}
		}
	}

}

class RepetidaException extends Exception {}
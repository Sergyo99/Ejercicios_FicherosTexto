package view;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Principal {

	public static void main(String[] args) {
		
		String nombreFch = "";
		try {
			BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Nombre del fichero: ");
			nombreFch = teclado.readLine();
			if (nombreFch.length() == 0) {
				nombreFch = "prueba.txt";
			}
			teclado.close();
		} catch (IOException e) {
			System.err.println("ERROR con el teclado. " + e.getMessage());
		}

		// Lectura línea a línea:
		try {
			BufferedReader fichero = new BufferedReader(new FileReader(nombreFch));
			String contenidoFch = "";
			String lineaLeida = fichero.readLine();
			while (lineaLeida != null) {
				contenidoFch += lineaLeida;
				lineaLeida = fichero.readLine();
				if (lineaLeida != null) {
					contenidoFch += "\n";
				}
			}
			fichero.close();
			System.out.println("\nLeyendo línea a línea:");
			System.out.println(contenidoFch);
		} catch (FileNotFoundException fnfe) {
			System.err.println("ERROR con el fichero. NO SE ENCUENTRA. " + fnfe.getMessage());
		} catch (IOException ioe) {
			System.err.println("ERROR con el fichero. LECTURA. " + ioe.getMessage());
		} catch (Exception e) {
			System.err.println("ERROR con el fichero. GENERAL. " + e.getMessage());
		}

		// Lectura caracter a caracter:
		try {
			FileReader fichero = new FileReader(nombreFch);
			String contenidoFch = "";
			int caracterLeido = fichero.read();
			while (caracterLeido != -1) {
				contenidoFch += (char) caracterLeido;
				caracterLeido = fichero.read();
			}
			fichero.close();
			System.out.println("\nLeyendo caracter a caracter:");
			System.out.println(contenidoFch);
		} catch (FileNotFoundException fnfe) {
			System.err.println("ERROR con el fichero. NO SE ENCUENTRA. " + fnfe.getMessage());
		} catch (IOException ioe) {
			System.err.println("ERROR con el fichero. LECTURA. " + ioe.getMessage());
		} catch (Exception e) {
			System.err.println("ERROR con el fichero. GENERAL. " + e.getMessage());
		}

	}

}

package view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Principal {

	public static void main(String[] args) {
		
		String nombre = "";
		Integer edad = 0;
		try {
			BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Escribe tu Nombre: ");
			nombre = teclado.readLine();
			System.out.print("Escribe tu Edad: ");
			edad = Integer.parseInt(teclado.readLine());
			teclado.close();
		} catch (NumberFormatException nfe) {
			System.err.println("ERROR de conversión. " + nfe.getMessage());
		} catch (IOException e) {
			System.err.println("ERROR con el teclado. " + e.getMessage());
		} catch (Exception e) {
			System.err.println("ERROR GENERAL. " + e.getMessage());
		}

		final String NOMBRE_FCH = "datos.txt";
		try {
			BufferedWriter fichero = new BufferedWriter(new FileWriter(NOMBRE_FCH));
			fichero.write(nombre);
			fichero.newLine();
			fichero.write(edad.toString());
			fichero.close();
			System.out.println("\nSe ha escrito correctamente el nombre y la edad en: " + NOMBRE_FCH);
		} catch (IOException ioe) {
			System.err.println("ERROR con el fichero. " + ioe.getMessage());
		} catch (Exception e) {
			System.err.println("ERROR GENERAL. " + e.getMessage());
		}
		
	}

}

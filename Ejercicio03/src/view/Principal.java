package view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Principal {

	public static void main(String[] args) {
		version1();
		version2();
	}

	private static void version1() {
		final String NOMBRE_FCH_ORIGINAL = "original.txt";
		final String NOMBRE_FCH_COPIA = "copia.txt";
		try {
			FileReader ficheroOriginal = new FileReader(NOMBRE_FCH_ORIGINAL);
			FileWriter ficheroCopia = new FileWriter(NOMBRE_FCH_COPIA);

			int caracterLeido = ficheroOriginal.read();
			while (caracterLeido != -1) {
				ficheroCopia.write(caracterLeido);
				caracterLeido = ficheroOriginal.read();
			}

			ficheroCopia.close();
			ficheroOriginal.close();
			System.out.println("Se ha copiado correctamente el fichero: " + NOMBRE_FCH_ORIGINAL + " como: " + NOMBRE_FCH_COPIA);
		} catch (FileNotFoundException fnfe) {
			System.err.println("ERROR con el fichero. NO SE ENCUENTRA. " + fnfe.getMessage());
		} catch (IOException ioe) {
			System.err.println("ERROR con el fichero. LECTURA/ESCRITURA. " + ioe.getMessage());
		} catch (Exception e) {
			System.err.println("ERROR GENERAL. " + e.getMessage());
		}
	}

	private static void version2() {
		String nombreFchOriginal = "";
		String nombreFchCopia = "";
		try {
			BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Escribe nombre del fichero fuente: ");
			nombreFchOriginal = teclado.readLine();
			teclado.close();
		} catch (IOException e) {
			System.err.println("ERROR con el teclado. " + e.getMessage());
		}

		nombreFchCopia = "copia_de_" + nombreFchOriginal;
		try {
			BufferedReader ficheroOriginal = new BufferedReader(new FileReader(nombreFchOriginal));
			BufferedWriter ficheroCopia = new BufferedWriter(new FileWriter(nombreFchCopia));

			String lineaLeida = ficheroOriginal.readLine();
			while (lineaLeida != null) {
				ficheroCopia.write(lineaLeida);
				lineaLeida = ficheroOriginal.readLine();
				if (lineaLeida != null) {
					ficheroCopia.newLine();
				}
			}

			ficheroCopia.close();
			ficheroOriginal.close();
			System.out.println("Se ha copiado correctamente el fichero: " + nombreFchOriginal + " como: " + nombreFchCopia);
		} catch (FileNotFoundException fnfe) {
			System.err.println("ERROR con el fichero. NO SE ENCUENTRA. " + fnfe.getMessage());
		} catch (IOException ioe) {
			System.err.println("ERROR con el fichero. LECTURA/ESCRITURA. " + ioe.getMessage());
		} catch (Exception e) {
			System.err.println("ERROR GENERAL. " + e.getMessage());
		}

	}

}
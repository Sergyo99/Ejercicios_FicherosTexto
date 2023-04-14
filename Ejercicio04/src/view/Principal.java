package view;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Principal {

	public static void main(String[] args) {
		porCaracter();
		porLinea();
	}

	private static void porLinea() {
		final String NOMBRE_FCH = "carta.txt";

		int contLinea = 0;
		int contPalabra = 0;
		int contConsonante = 0;
		int contVocal = 0;
		List<Character> vocales = Arrays.asList('A', 'E', 'I', 'O', 'U', 'a', 'e', 'i', 'o', 'u');

		try {
			StringTokenizer lstPalabras;
			String palabra;

			BufferedReader fichero = new BufferedReader(new FileReader(NOMBRE_FCH));

			String lineaLeida = fichero.readLine();
			while (lineaLeida != null) {
				contLinea++;
				lstPalabras = new StringTokenizer(lineaLeida);

				while (lstPalabras.hasMoreTokens()) {
					contPalabra++;
					palabra = lstPalabras.nextToken();
					for (int pos = 0; pos < palabra.length(); pos++) {
						if (vocales.contains(palabra.charAt(pos))) {
							contVocal++;
						} else {
							contConsonante++;
						}

					}
				}

				lineaLeida = fichero.readLine();
			}

			fichero.close();
			System.out.println("\nInformación del fichero: " + NOMBRE_FCH);
			System.out.println("Total líneas      = " + contLinea);
			System.out.println("Total Palabras    = " + contPalabra);
			System.out.println("Total Consonantes = " + contConsonante);
			System.out.println("Total Vocales     = " + contVocal);

		} catch (FileNotFoundException fnfe) {
			System.err.println("ERROR con el fichero. NO SE ENCUENTRA. " + fnfe.getMessage());
		} catch (IOException ioe) {
			System.err.println("ERROR con el fichero. LECTURA/ESCRITURA. " + ioe.getMessage());
		} catch (Exception e) {
			System.err.println("ERROR con el fichero. GENERAL. " + e.getMessage());
		}

	}

	private static void porCaracter() {
		final String NOMBRE_FCH = "carta.txt";

		int contLinea = 0;
		int contPalabra = 0;
		int contLetra = 0;
		int contVocal = 0;
		List<Integer> vocales = Arrays.asList(65, 69, 73, 79, 85, 97, 101, 105, 111, 117);

		try {
			FileReader fichero = new FileReader(NOMBRE_FCH);

			int caracterLeido = fichero.read();
			while (caracterLeido != -1) {

				if (caracterLeido == 13)
					contLinea++;
				if (caracterLeido == 32)
					contPalabra++;
				if ((caracterLeido >= 65 && caracterLeido >= 90) || (caracterLeido >= 97 && caracterLeido >= 122))
					contLetra++;
				if (vocales.contains(caracterLeido)) {
					contLetra--;
					contVocal++;
				}

				caracterLeido = fichero.read();
				
				if(caracterLeido == 13)
					contPalabra++;
			}
			contLinea++;
			contPalabra++;

			fichero.close();
			System.out.println("\nInformación del fichero: " + NOMBRE_FCH);
			System.out.println("Total líneas      = " + contLinea);
			System.out.println("Total Palabras    = " + contPalabra);
			System.out.println("Total Consonantes = " + contLetra);
			System.out.println("Total Vocales     = " + contVocal);

		} catch (FileNotFoundException fnfe) {
			System.err.println("ERROR con el fichero. NO SE ENCUENTRA. " + fnfe.getMessage());
		} catch (IOException ioe) {
			System.err.println("ERROR con el fichero. LECTURA/ESCRITURA. " + ioe.getMessage());
		} catch (Exception e) {
			System.err.println("ERROR con el fichero. GENERAL. " + e.getMessage());
		}
	}

}
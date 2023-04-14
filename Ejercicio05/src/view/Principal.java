package view;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Principal {

	public static void main(String[] args) {
		final String NOMBRE_FICHERO = "numeros.txt";

		Integer numero = null;
		Integer minimo = null;
		Integer maximo = null;
		String lineaLeida = "";

		BufferedReader fichero = null;
		
		try {
			fichero = new BufferedReader(new FileReader(NOMBRE_FICHERO));

			lineaLeida = fichero.readLine();
			if (lineaLeida != null) {
				numero = Integer.parseInt(lineaLeida);
				minimo = numero;
				maximo = numero;
			}
			while (lineaLeida != null) {
				numero = Integer.parseInt(lineaLeida);
				if (numero < minimo) minimo = numero;
				if (numero > maximo) maximo = numero;
				lineaLeida = fichero.readLine();
			}

			System.out.println("Mínimo: " + minimo);
			System.out.println("Máximo: " + maximo);
		}

		catch (NumberFormatException nfe)  { System.err.println("ERROR de conversión de tipos. " + nfe.getMessage());  }
		catch (FileNotFoundException fnfe) { System.err.println("ERROR con el fichero. NO SE ENCUENTRA. " + fnfe.getMessage());  }
		catch (IOException ioe)            { System.err.println("ERROR con el fichero. LECTURA/ESCRITURA. " + ioe.getMessage()); }
		catch (Exception e)                { System.err.println("ERROR GENERAL. " + e.getMessage());                             }

		finally {
			if (fichero != null) {
				try { fichero.close(); }
				catch (Exception e) { System.err.println("ERROR con el fichero. CIERRE. " + e.getMessage()); }
			}
		}
	}

}
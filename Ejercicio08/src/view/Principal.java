package view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Principal {

	public static void main(String[] args) {

		String nombre = "";
		Integer edad = 0;
		
		BufferedReader teclado = null;
		try {
			teclado = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Escribe tu nombre: ");
			nombre = teclado.readLine();
			System.out.print("Escribe tu edad: ");
			edad = Integer.parseInt(teclado.readLine());

			comprobarEdad(edad);
			escribirEnFichero(nombre, edad);
			
		}
		catch (EdadNegativaException | MenorDeEdadException edadEx) { escribirLog(edadEx, nombre); }
		catch (NumberFormatException nfe) { System.err.println("ERROR de conversión. " + nfe.getMessage()); }
		catch (IOException ioe)           { System.err.println("ERROR con el teclado. " + ioe.getMessage());  }
		catch (Exception e)               { System.err.println("ERROR GENERAL. " + e.getMessage());         }
		finally {
			try { if (teclado != null) teclado.close(); }
			catch (IOException e) { System.err.println("ERROR de E/S con el teclado. " + e.getMessage());   }
		}
		
		System.out.println("Fin del programa.");

	}

	private static void escribirEnFichero(String nombre, Integer edad) {
		final String NOMBRE_FCH = "datos.txt";
		BufferedWriter fichero = null;
		try {
			fichero = new BufferedWriter(new FileWriter(NOMBRE_FCH));
			fichero.write(nombre);
			fichero.newLine();
			fichero.write(edad.toString());
			System.out.println("\nSe ha escrito correctamente el nombre y la edad en: " + NOMBRE_FCH);
		}
		catch (IOException ioe) { System.err.println("ERROR con el fichero: " + NOMBRE_FCH + " " + ioe.getMessage()); }
		catch (Exception e)     { System.err.println("ERROR GENERAL. " + e.getMessage());          }
		finally {
			try { if (fichero != null) fichero.close(); }
			catch (IOException e) { System.err.println("ERROR cerrando el fichero: " + NOMBRE_FCH + " " + e.getMessage());        }
		}
	}

	private static void escribirLog(Exception edadEx, String nombre) {
		String patternFile = "yyyy-MM-dd";
		String patternInfo = "dd/MM/yyyy H:mm";

		String fileName = new SimpleDateFormat(patternFile).format(new Date()) + ".log";
		String date = new SimpleDateFormat(patternInfo).format(new Date());

		BufferedWriter fichero = null;
		try {
			fichero = new BufferedWriter(new FileWriter(fileName, true));
			fichero.write(date + " - " + edadEx.getClass().getSimpleName() + ": Imposible crear el usuario con nombre " + nombre + ". " + edadEx.getMessage());
			fichero.newLine();
		}
		catch (IOException ioe) { System.err.println("ERROR con el fichero: "+ fileName + " " + ioe.getMessage()); }
		catch (Exception e)     { System.err.println("ERROR GENERAL. " + e.getMessage()); }
		finally {
			try { if (fichero != null) fichero.close(); }
			catch (IOException e) { System.err.println("ERROR cerrando el fichero: " + fileName + " " + e.getMessage());        }
		}
	}

	private static void comprobarEdad(Integer edad) throws EdadNegativaException, MenorDeEdadException {
		if (edad < 0)  throw new EdadNegativaException(edad);
		if (edad < 18) throw new MenorDeEdadException(edad);
	}

}

@SuppressWarnings("serial")
class EdadNegativaException extends Exception {
	public EdadNegativaException(Integer edad) {
		super("La edad no puede ser negativa. (Edad indicada: " + edad + ").");
	}
}

@SuppressWarnings("serial")
class MenorDeEdadException extends Exception {
	public MenorDeEdadException(Integer edad) {
		super("El cliente no puede ser menor de edad (Edad indicada: " + edad + ").");
	}

}
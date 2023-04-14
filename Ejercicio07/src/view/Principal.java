package view;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Principal {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		final String NOMBRE_FCH_DEFAULT = "prueba.txt";
		final String TXT_PAUSA = "« pulse INTRO para continuar »";
		final String TXT_EOF = "« fin del fichero »";
		final Integer LINEAS_POR_PAGINA = 10;

		String nombreFch = "";
		String lineaLeida = "";
		int cont = 0;

		BufferedReader fichero = null;
		try {
			// Pedir el nombre del fichero al ususario
			System.out.print("Nombre del fichero [" + NOMBRE_FCH_DEFAULT + "] : ");
			Scanner teclado = new Scanner(System.in);
			nombreFch = teclado.nextLine();
			if (nombreFch.length() == 0) {
				nombreFch = NOMBRE_FCH_DEFAULT;
			}
			// Abrir el fichero para mostrar su contenido
			fichero = new BufferedReader(new FileReader(nombreFch));
			lineaLeida = fichero.readLine();
			while (lineaLeida != null) {
				System.out.println(lineaLeida);
				cont++;
				if (cont == LINEAS_POR_PAGINA) {
					cont = 0;
					System.out.print(TXT_PAUSA);
					teclado.nextLine();
				}
				lineaLeida = fichero.readLine();
			}
			System.out.print(TXT_EOF);
		}
		
		catch (FileNotFoundException fnfe) { System.err.println("ERROR con el fichero. NO SE ENCUENTRA. " + fnfe.getMessage()); }
		catch (IOException ioe)            { System.err.println("ERROR con el fichero. LECTURA/ESCRITURA. " + ioe.getMessage());}
		catch (Exception e)                { System.err.println("ERROR con el teclado. " + e.getMessage());                     }
		
		finally {
			try {
				if (fichero != null)
					fichero.close();
			} catch (Exception e) {
				System.err.println("ERROR con el fichero. CIERRE. " + e.getMessage());
			}
		}

	}

}
package view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Principal {

	public static void main(String[] args) {
		final String NOMBRE_FCH1 = "fichero1.txt";
		final String NOMBRE_FCH2 = "fichero2.txt";
		final String NOMBRE_FCHR = "resultado.txt";

		BufferedReader fichero1 = null;
		BufferedReader fichero2 = null;
		BufferedWriter ficheroR = null;

		String lineaFch1 = "";
		String lineaFch2 = "";

		Integer num1;
		Integer num2;

		try {
			fichero1 = new BufferedReader(new FileReader(NOMBRE_FCH1));
			fichero2 = new BufferedReader(new FileReader(NOMBRE_FCH2));
			ficheroR = new BufferedWriter(new FileWriter(NOMBRE_FCHR));

			lineaFch1 = fichero1.readLine();
			lineaFch2 = fichero2.readLine();

			while (lineaFch1 != null && lineaFch2 != null) {
				num1 = Integer.parseInt(lineaFch1);
				num2 = Integer.parseInt(lineaFch2);

				if (num1 <= num2) {
					ficheroR.write(num1.toString() + "\n");
					lineaFch1 = fichero1.readLine();
				} else {
					ficheroR.write(num2.toString() + "\n");
					lineaFch2 = fichero2.readLine();
				}
			}

			while (lineaFch1 != null) {
				num1 = Integer.parseInt(lineaFch1);
				ficheroR.write(num1.toString());
				lineaFch1 = fichero1.readLine();
				if (lineaFch1 != null) ficheroR.write("\n");
			}

			while (lineaFch2 != null) {
				num2 = Integer.parseInt(lineaFch2);
				ficheroR.write(num2.toString());
				lineaFch2 = fichero2.readLine();
				if (lineaFch2 != null) ficheroR.write("\n");
			}
			
			System.out.println("Se ha generado correctamente el fichero: " + NOMBRE_FCHR);
		}
		
		catch (FileNotFoundException fnfe) { System.err.println("ERROR con el fichero. LOCALIZACIÓN. " + fnfe.getMessage());     }
		catch (IOException ioe)            { System.err.println("ERROR con el fichero. LECTURA/ESCRITURA. " + ioe.getMessage()); }
		catch (Exception e)                { System.err.println("ERROR GENERAL. " + e.getMessage());                             }
		
		finally {
			try {
				if (fichero1 != null) fichero1.close();
				if (fichero2 != null) fichero2.close();
				if (ficheroR != null) ficheroR.close();
			} catch (IOException ioe) {
				System.err.println("ERROR con el fichero. CIERRE. " + ioe.getMessage());
			}
		}
	}
}
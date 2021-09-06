import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Clase auxiliar del programa Mayoritario
 * @Autor: Ubikitina
 * @version: 1.0
 * @Asignatura: Programación y Estructura de Datos Avanzadas (PED 1) 
 * @Descripción: Clase auxiliar. Contiene los métodos utilizados para la lectura por consola y lectura por archivo.
 */

public class Lectura {
	
	int longitud_vector;
	String valores_introducidos;
	
	/* Constructor*/
	public Lectura() {
		longitud_vector = 0;
		valores_introducidos = "";
	}
	
	/* Método para la lectura por consola*/
	public void lecturaConsola() {
		Scanner entrada = new Scanner(System.in);
		System.out.println("Introduce la longitud del vector:");
		try {
			longitud_vector = Integer.parseInt(entrada.nextLine());
		}catch (Exception e) {
			longitud_vector = 0;
		}
		System.out.println("Introduce los valores del vector separados por un espacio:");
		valores_introducidos = entrada.nextLine();
		entrada.close();
	}
	
	/* Método para la lectura por fichero.*/
	public void lecturaFichero(String rutaEntrada, boolean verTraza) throws Exception {
		
        // Apertura del fichero de entrada para poder volcar su contenido sobre las variables correspondientes
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(rutaEntrada)));
        }
        catch(FileNotFoundException e) {
            throw new IllegalArgumentException("No se ha podido encontrar el fichero '" + rutaEntrada + "'", e);
        }
        
        // Lectura primera línea
		try {
			longitud_vector = Integer.parseInt(bufferedReader.readLine());
			if(verTraza) { System.out.println("Linea 1: "+ longitud_vector); }
		} catch (Exception e1) {
			longitud_vector = 0;
		}
		// Lectura segunda línea
		valores_introducidos = bufferedReader.readLine();
		if(verTraza) { System.out.println("Linea 2: "+ valores_introducidos); }
        
        
        // Cierre del fichero de entrada
        try {
            bufferedReader.close();
        }
        catch(IOException e) {
            throw new IllegalStateException("No se ha podido cerrar correctamente el fichero '" + rutaEntrada + "'.", e);
        }
    }

	/* @return Entero (int) de la longitud del vector*/
	public int getLongitud_vector() {
		return longitud_vector;
	}

	/* @return String con los valores introducidos en el vector*/
	public String getValores_introducidos() {
		return valores_introducidos;
	}
}

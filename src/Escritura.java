import java.io.File;
import java.io.FileWriter;

/**
 * Clase auxiliar del programa Mayoritario
 * @Autor: Ubikitina
 * @version: 1.0
 * @Asignatura: Programación y Estructura de Datos Avanzadas (PED 1) 
 * @Descripción: Clase auxiliar. Contiene los métodos utilizados para la escritura en consola y escritura en archivo.
 */

public class Escritura {
	/* Constructor*/
	public Escritura() {
		
	}
	
	/* Método para escribir en consola el resultado*/
	public void escrituraConsola(String mayoritario) {
		System.out.println("El resultado obtenido es: " + mayoritario);
		
	}
	
	/* Método para comprobar si existe fichero de escritura (rutaSalida) antes de proceder a su escritura.
	 * @return Boolean indicando si el fichero existe (true) o no (false).*/
	public boolean existeFichero(String rutaSalida) {
		File f = new File (rutaSalida);
		if(f.exists()){
			return true;
		}else {
			return false;
		}
	}
	
	/* Método para la escritura del string mayoritario en el fichero de salida.*/
	public void escrituraFichero(String mayoritario, String rutaSalida) throws Exception {
		try {
			FileWriter escritor = new FileWriter(rutaSalida);
			for (int i =0; i<mayoritario.length(); i++){
				escritor.write(mayoritario.charAt(i));
			}
			escritor.close();			
		}catch (Exception e) {
			throw new Exception(e);
		}
	}	
}

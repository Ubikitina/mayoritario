import java.util.ArrayList;

/**
 * Clase auxiliar del programa Mayoritario
 * @Autor: Ubikitina
 * @version: 1.0
 * @Asignatura: Programación y Estructura de Datos Avanzadas (PED 1) 
 * @Descripción: Clase auxiliar. Esta clase contiene proporciona funcionalidades básicas para trabajar con arrays de strings y strings en general.
 */

public final class ArrayAndStrings {
	
	/* Indica si un String (argumento) está contenida en un array de String-s (argumentos).
	 * @return Boolean indicando si argumento está contenida en argumentos (true) o no (false)*/
	public static boolean contiene(String[] argumentos, String argumento) {
		boolean contiene = false;
		for (int i = 0; !contiene && i < argumentos.length; i++){
			contiene = argumentos[i].equals(argumento);
		}
		return contiene;
	}

	/* Devuelve un array de String con los String-s que puedan ser una ruta a fichero de entre las cadenas que haya en args.
	 * @return Array de String con los String-s que puedan ser una ruta a fichero.*/
	public static String[] buscarArgsRutasDeFicheros(String[] argumentos) {
		// Al principio se desconoce el número de argumentos String que hay que cumplan con las condiciones de ruta de fichero. Por tanto, se utiliza la estructura ArrayList.
		ArrayList<String> arrayListPosibleRuta = new ArrayList<String>();
		for (String argumento : argumentos) {
			// Suponemos que los ficheros tienen nombre, punto y extensión txt. Por tanto, buscamos Strings que contienen un .txt.
			if (argumento.contains(".txt")) {
				arrayListPosibleRuta.add(argumento);
			}
		}
		// Después, generamos un array de Strings. Ahora sí podemos generar el array de strings porque sabemos el número de rutas de ficheros que hay.
		String[] arrayPosibleRuta = null;
		if (!arrayListPosibleRuta.isEmpty()) {
			arrayPosibleRuta = new String[arrayListPosibleRuta.size()];
			arrayPosibleRuta = arrayListPosibleRuta.toArray(arrayPosibleRuta);
		}
		return arrayPosibleRuta; //Si no hay, se devuelve null.
	}

}

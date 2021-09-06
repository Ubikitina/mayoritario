import java.io.File;

/**
 * Clase principal del programa Mayoritario
 * @Autor: Ubikitina
 * @version: 1.0
 * @Asignatura: Programación y Estructura de Datos Avanzadas (PED 1) 
 * @Descripción: Clase principal. Contiene el método main. 
 * Comprueba los argumentos de entrada y muestra la ayuda del programa.
 */

public class Main {
	
	private static final String COMANDO_TRAZA = "-t"; //  Traza cada paso de manera que se describa el algoritmo utilizado.
    private static final String COMANDO_AYUDA = "-h"; // Muestra la sintaxis del comando a modo ayuda.
	
	private static void mostrarAyuda() {
        System.out.println("SINTAXIS:");
        System.out.println("mayoritario [-t] [-h] [fichero_entrada] [fichero_salida]");
        System.out.println("  -t                Traza las llamadas recursivas");
        System.out.println("  -h                Muestra esta ayuda");
        System.out.println("  fichero_entrada   Nombre del fichero de entrada.");
        System.out.println("  fichero_salida    Nombre del fichero de salida.");
        System.out.println("AVISO: Las rutas no tienen que contener espacios.");
    }

	public static void main(String[] args) {
		String ficheroEntrada = "";
		String ficheroSalida = "";
		
		// Recoger los argumentos que puedan ser posibles rutas a ficheros.
        String[]rutasDeFicheros = ArrayAndStrings.buscarArgsRutasDeFicheros(args);
        
        // Buscar en los argumentos si hay uno de traza y de ayuda.
        boolean verTraza = ArrayAndStrings.contiene(args, COMANDO_TRAZA);
        boolean verAyuda = ArrayAndStrings.contiene(args, COMANDO_AYUDA);
        
        // Si ponemos el parámetro "-h" obviamos la existencia de otros parámetros y solo sacamos la ayuda.
        if (verAyuda) {
            mostrarAyuda();
            System.exit(0);
        }
        
        if(verTraza) { System.out.println(); System.out.println("++ ENTRADA ++"); }
    	
        // Si solo hay una ruta, tengo que comprobar si existe o no. Si existe, lo incluyo como entrada. Si no existe, lo incluyo como salida.
        if (rutasDeFicheros != null && rutasDeFicheros.length == 1) {
        	if(existeFichero(rutasDeFicheros[0])) {
        		ficheroEntrada = rutasDeFicheros[0];
        		if(verTraza) { System.out.println("El fichero ENTRADA es "+ ficheroEntrada); }
        	}else {
        		ficheroSalida = rutasDeFicheros[0];
        		if(verTraza) { System.out.println("El fichero SALIDA es "+ ficheroSalida); }
        	}
        	
        // Si hay dos rutas, recojo entrada y salida
        }else if (rutasDeFicheros != null && rutasDeFicheros.length == 2){
        	ficheroEntrada = rutasDeFicheros[0];
            ficheroSalida = rutasDeFicheros[1];
            if(verTraza) { System.out.println("El fichero ENTRADA es "+ ficheroEntrada); System.out.println("El fichero SALIDA es "+ ficheroSalida); }
        }//en otro caso, no hago nada porque las rutas proporcionadas no son válidas
		
		
		//Hacemos la lectura. Por defecto intentamos hacer la lectura por fichero, pero si no consigue, hacemos por consola.
		Lectura l = new Lectura();
		try {
			if(verTraza) { System.out.println(); System.out.println("++ LECTURA ++"); }
			l.lecturaFichero(ficheroEntrada, verTraza);
		}catch (Exception e) {
			System.out.println("Ruta de entrada no introducida o incorrecta. Se procede a la lectura por consola.");
			l.lecturaConsola();
		}
		int longitud_vector = l.getLongitud_vector();
		String valores_introducidos = l.getValores_introducidos();
		
		//Hacemos la validacion de datos
		int mayoritario = 0;
		
		ValidaDatosCalculaMayoritario v = new ValidaDatosCalculaMayoritario(longitud_vector, valores_introducidos);
		//Si los datos introducidos son correctos, procedemos a calcular mayoritario y escribir resultado
		if(v.comprobacionValores(verTraza)) {
			//Cálculo mayoritario
			mayoritario = v.crearVectorCalcularMayoritario(verTraza);
			System.out.println("> Mayoritario calculado: SÍ");
			
			//Pasamos el número obtenido a string, para hacer la conversión de -1 a N según pide el enunciado
			String may = Integer.toString(mayoritario);
			if (may.equals(new String("-1"))) {
				may = "N";
			}
			
			//Procedemos con la escritura.  
			Escritura e = new Escritura();
			try {
				if(verTraza) { System.out.println(); System.out.println("++ ESCRITURA ++"); }
				//Se comprueba si el fichero salida existe. Si existe, el comando dará un error.
				if(e.existeFichero(ficheroSalida)) {
					System.out.println("Error - El fichero introducido ya existe. No se re-escribirá.");
				}else {//si no existe, procede a generar fichero
					e.escrituraFichero(may, ficheroSalida);
					System.out.println("Se ha generado el fichero de salida con el resultado.");
				}
			}catch (Exception ex){
				System.out.println("Ruta de salida no introducida o incorrecta. No se generará fichero. Se muestra el resultado en consola.");
				e.escrituraConsola(may);
			}
		//Si los datos introducidos no son correctos, no calculamos mayoritario
		}else {
			System.out.println("> Mayoritario calculado: NO, error en datos (longitud y/o vector) introducidos");
		}
	}
	
	/* Método para comprobar si existe un fichero.
	 * @return Boolean indicando si el fichero existe (true) o no (false).*/
	public static boolean existeFichero(String rutaSalida) {
		File f = new File (rutaSalida);
		if(f.exists()){
			return true;
		}else {
			return false;
		}
	}
}

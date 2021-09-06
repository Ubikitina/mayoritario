import java.util.Vector;

/**
 * Clase auxiliar del programa Mayoritario
 * @Autor: Ubikitina
 * @version: 1.0
 * @Asignatura: Programación y Estructura de Datos Avanzadas (PED 1) 
 * @Descripción: Clase auxiliar. Contiene el algoritmo recursivo para el cálculo mayoritario.
 */

public class AlgoritmoDyV {
	int n;
	int numeroLlamadaRecursiva;
	
	/*Constructor*/
	public AlgoritmoDyV(int n) {
		this.n = n;
		numeroLlamadaRecursiva = 0;
	}

	/* Método recursivo para el cálculo del valor mayoritario.
	 * Recibe como parámetro dos enteros (i y j) que representan posiciones inicio y fin del vector, 
	 * vector donde calcular mayoritario y un boolean para mostrar traza.
	 * @return Entero (int) del valor mayoritario del vector.*/
	public int Mayoritario(int i, int j, Vector<Integer> v, boolean verTraza) {
		int m;
		int s1;
		int s2;
		
		numeroLlamadaRecursiva++;
		if(verTraza) { System.out.println("# Nº llamada recursiva: "+ numeroLlamadaRecursiva + "; Vector: " + v.toString() + "; i: "+i+"; j: "+j); }
		
		if (i == j) {
			return v.get(i);
		}else {
			m = (i+j)/2;
			if(verTraza) { System.out.println("#   Coge subvector izquierdo"); }
			s1 = Mayoritario(i, m, v, verTraza);
			if(verTraza) { System.out.println("#   Coge subvector derecho"); }
			s2 = Mayoritario(m+1, j, v, verTraza);
			if(verTraza) { System.out.println("#   Combina vectores"); }
			return Combinar(s1, s2, v, verTraza, i, j);
		}
	}
	
	/* Método auxiliar en el cálculo recursivo de mayoritario.
	 * Cada vez que se haya calculado el valor mayoritario de dos subvectores, combina ambos resultados para obtener el valor mayoritario
	 * del vector resultante de la unión de ambos subvectores.
	 * @return Entero (int) del valor mayoritario del vector resultante.*/
	public int Combinar(int a, int b, Vector<Integer> v, boolean verTraza, int i, int j) {
		if (a == -1 && b == -1) {
			if(verTraza) { System.out.println("#      a == -1("+a+") && b == -1("+b+")"); System.out.println("#      No elige ninguno, elige valor -1"); }
			return -1;
		}else if(a == -1 && b != -1) {
			if(verTraza) { System.out.println("#      a == -1("+a+") && b != -1("+b+")"); }
			return ComprobarMayoritario(b, v, verTraza, i, j);
		}else if(a != -1 && b == -1) {
			if(verTraza) { System.out.println("#      a != -1("+a+") && b == -1("+b+")"); }
			return ComprobarMayoritario(a, v, verTraza, i, j);
		}else if(a != -1 && b != -1) {
			if(verTraza) { System.out.println("#      a != -1("+a+") && b != -1("+b+")"); }
			if (ComprobarMayoritario(a, v, verTraza, i, j)==a) {
				return a;
			}else if(ComprobarMayoritario(b, v, verTraza, i, j)==b) {
				return b;
			}else {
				return -1;
			}
		}
		return -1;
	}
	
	/* Método auxiliar del método combinar.
	 * Recibe un valor y un vector, y cuenta cuántas veces aparece dicho valor en el vector.
	 * @return Entero (int). Tras el conteo, si se trata un valor de más de la mitad, devuelve dicho valor. Si no, devuelve -1.*/
	public int ComprobarMayoritario(int x, Vector<Integer> v, boolean verTraza, int i, int j) {
		int c = 0;
		int k;
		/*Realizamos el conteo*/
		for (k = i; k <= j; k++){
			if (v.get(k) == x){
				c++;
			}
		}
		/*Devolvemos los resultados*/
		if (c > (j-i+1)/2) {
			if(verTraza) { System.out.println("#      Tras conteo de "+x+" elige " + x); }
			return x;
		}else {
			if(verTraza) { System.out.println("#      Tras conteo de "+x+" elige -1"); }
			return -1;
		}
	}

}

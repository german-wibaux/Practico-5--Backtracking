import java.util.ArrayList;
import java.util.List;

public class Ejercicio_7 {

	public static boolean esSolucion(int[][] tablero, int total) {
		int i = 0;
		int j = 0;
		boolean control = true;
		int parcial = 0;

		while (i < tablero.length && control) {
			while (j < tablero.length && control) {
				if (tablero[i][j] != -1) {
					parcial = parcial + tablero[i][j];
				} else {
					control = false;
				}
				if (j == tablero.length - 1) {
					if (parcial == total) {
						parcial = 0;
					} else {
						control = false; 
					}
				}
				j++;
			}
			i++;
			j = 0;
		}

		i = 0;
		j = 0;
		parcial = 0;

		while (i < tablero.length && control) {
			while (j < tablero.length && control) {
				if (tablero[j][i] != -1) {
					parcial = parcial + tablero[j][i];
				} else {
					control = false;
				}
				if (j == tablero.length - 1) {
					if (parcial == total) {
						parcial = 0;
					} else {
						control = false;
					}
				}
				j++;
			}
			i++;
			j = 0;
		}

		return control;

	}

	public static void imprimirTablero(int[][] tablero) {
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				System.out.print("|" + tablero[i][j] + "|");
			}
			System.out.println();
		}
		System.out.println("////////Tablero///////////");
	}

	public static boolean backTablero(int[][] tablero, boolean[] usados, int total, int k, int fila, int columna) {
		// Backtracking tablero magico
		if (fila == tablero.length) {
			if (esSolucion(tablero, total)) {
				return true;
			}
		} else {
			for (int i = 1; i <= k; i++) {
				if (usados[i - 1]) {
					tablero[fila][columna] = i;
					usados[i - 1] = false;

					if (columna < tablero.length - 1) {
						if (backTablero(tablero, usados, total, k, fila, columna + 1)) {
							return true;
						}
					} else if (fila < tablero.length) {
						if (backTablero(tablero, usados, total, k, fila + 1, 0)) {
							return true;
						}
					}
					tablero[fila][columna] = -1;
					usados[i - 1] = true;
				}
			}
		}
		return false;
	}
	
	public static boolean esSolucionPar (List<Integer> subc1, List<Integer> subc2, List<Integer> subc3) {
		boolean response = false;
		
		if(!subc1.isEmpty() && !subc2.isEmpty() && !subc3.isEmpty()) {
			int q = 0;
			int w = 0;
			int e = 0;
			for(int i=0;i <subc1.size(); i++) {
				q = q + subc1.get(i);
				//System.out.println(subc1.get(i));
			}
			//System.out.println("/////////////");
			for(int i=0;i <subc2.size(); i++) {
				w = w + subc2.get(i);
				//System.out.println(subc2.get(i));
			}
			//System.out.println("/////////////");
			for(int i=0;i <subc3.size(); i++) {
				e = e + subc3.get(i);
				//System.out.println(subc3.get(i));
			}
			//System.out.println("/////////////");
			if(q == 10 && w == 10 && e == 10) {
				response = true;
			}
			//System.out.println("//////FIN///////" + q + "//" +  w + "//" + e);
		}
		
		return response;
		
	}
	
	public static boolean backParcial (int[] conjunto, List<Integer> subc1, List<Integer> subc2, List<Integer> subc3, int nivel ) {
		if(esSolucionPar(subc1, subc2, subc3)) {
//			if (esSolucionPar(subc1, subc2, subc3)) {
//				return true;
//			}
			return true;
		} else {
			
//			if  {
//				return true;
//			}
			
			if (nivel == conjunto.length) {
				return false;
			}
			subc1.add(conjunto[nivel]);			
			if ( backParcial(conjunto, subc1, subc2, subc3, nivel+1) ) {
				return true;
			}			
			
			subc1.remove(subc1.size()-1);
			subc2.add(conjunto[nivel]);
			if ( backParcial(conjunto, subc1, subc2, subc3, nivel+1) ) {
				return true;
			}
			
			subc2.remove(subc2.size()-1);
			subc3.add(conjunto[nivel]);
			if ( backParcial(conjunto, subc1, subc2, subc3, nivel+1) ) {
				return true;
			}			
			subc3.remove(subc3.size()-1);
			
		}
		return false;
	}

	public static void main(String[] args) {
		// Inicializamos las variables necesarias
//		int k = 14;
//		int n = 3;
//		int[][] tableroMagico = new int[n][n];
//		boolean[] valoresAUtilizar = new boolean[k];
//
//		for (int i = 0; i < tableroMagico.length; i++) {
//			for (int j = 0; j < tableroMagico.length; j++) {
//				tableroMagico[i][j] = -1;
//			}
//		}
//
//		for (int i = 0; i < valoresAUtilizar.length; i++) {
//			valoresAUtilizar[i] = true;
//		}
//
//		if (backTablero(tableroMagico, valoresAUtilizar, 15, k, 0, 0)) {
//			imprimirTablero(tableroMagico);
//		}
		
		int [] conjunto = {1, 2, 3, 4, 5, 6, 9, 10, 11, 12, 13};
		
		List<Integer> subc1 = new ArrayList <Integer>();
		List<Integer> subc2 = new ArrayList <Integer>();
		List<Integer> subc3 = new ArrayList <Integer>();
		
		if (backParcial(conjunto, subc1, subc2, subc3, 0)) {
			System.out.println("Hay solucion");
			for(int i=0; i<subc1.size(); i++) {
				System.out.println(subc1.get(i));								
			}
			System.out.println("////Suconjunto 1/////");
			for(int i=0; i<subc2.size(); i++) {
				System.out.println(subc2.get(i));								
			}
			System.out.println("////Subconjunto 2/////");
			for(int i=0; i<subc3.size(); i++) {
				System.out.println(subc3.get(i));								
			}
			System.out.println("/////Subconjunto 3////");
		}

	}

}

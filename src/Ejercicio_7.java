
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

	public static void main(String[] args) {
		// Inicializamos las variables necesarias
		int k = 14;
		int n = 3;
		int[][] tableroMagico = new int[n][n];
		boolean[] valoresAUtilizar = new boolean[k];

		for (int i = 0; i < tableroMagico.length; i++) {
			for (int j = 0; j < tableroMagico.length; j++) {
				tableroMagico[i][j] = -1;
			}
		}

		for (int i = 0; i < valoresAUtilizar.length; i++) {
			valoresAUtilizar[i] = true;
		}

		if (backTablero(tableroMagico, valoresAUtilizar, 15, k, 0, 0)) {
			imprimirTablero(tableroMagico);
		} 

	}

}

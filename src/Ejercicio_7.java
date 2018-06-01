
public class Ejercicio_7 {
	
	public static boolean esHoja(int[][] hoja) {
		int i=0;
		int j=0;
		boolean control = true;
		while ((control) && (i < hoja.length)) {
			while ((control) && (j < hoja.length)) {
				if ( hoja[i][j] == -1 ) {
					control = false;
				}
				j++;
			}
			i++;
		}
		//imprimirTablero(hoja);
		return control;
	}
	
	public static boolean esSolucion (int[][] tablero, int total) {
		int parcial = 0;
		for(int i=0; i<tablero.length; i++) {
			for(int j=0;j<tablero.length; j++) {
				parcial += tablero[i][j];
			}
		}
		System.out.print(parcial + " " + total + "\n");
		return parcial == total;
	}
	
	public static void imprimirTablero (int[][] tablero) {
		for(int i=0; i<tablero.length; i++) {		
			for(int j=0;j<tablero[i].length; j++) {
				System.out.print("|" + tablero[i][j] + "|");			
			}		
			System.out.println();
		}
		
	}
	
	public static boolean estaDisponible(int i, boolean[] usados) {
		return usados[i -1] == true;
	}
	
	public static void backTablero(int[][] tablero, boolean[] usados, int total, int k, int fila, int columna ) {		
		if(esHoja(tablero)) {
			if (esSolucion(tablero, total)) {
				imprimirTablero(tablero);
			}
		} else {
			for(int i=1;i<=k;i++) {
				if(estaDisponible(i, usados))  {
					tablero[fila][columna] = i;
					usados[i - 1] = false;
					if(columna < tablero.length-1 ) {
						int proximo = columna + 1;
						backTablero(tablero, usados, total, k, fila, proximo);
					}					
					if (fila < tablero.length-1 ) {
						int proximo = fila + 1;
						backTablero(tablero, usados, total, k, proximo, 0);						
					}
					tablero[fila][columna] = -1;
					usados[i - 1] = true;
				}				
				
			}
		}
		
	}

	public static void main(String[] args) {
		//Inicializamos las variables necesarias
		int k = 15;
		int n = 3;
		int[][] tableroMagico = new int[n][n];
		boolean[] valoresAUtilizar = new boolean[k];
		
		for(int i=0; i<tableroMagico.length; i++) {
			for(int j=0; j<tableroMagico.length; j++) {
				tableroMagico[i][j] = -1;
			}
		}
		
		for(int i=0; i<valoresAUtilizar.length; i++) {
			valoresAUtilizar[i] = true;
		}	
		
		
		backTablero(tableroMagico, valoresAUtilizar, 25, k, 0, 0);
		//imprimirTablero(tableroMagico);
		
	}

}

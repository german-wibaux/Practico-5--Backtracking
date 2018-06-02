
public class Ejercicio_7 {
	
	public static boolean esHoja(int[][] hoja) {	
		boolean control = true;
		for(int i=0; i<hoja.length; i++) {
			for(int j=0;j<hoja.length; j++) {
				if (hoja[i][j] == -1) {
					control = false;
				}
			} 
		}
		return control;
	}
	
	public static boolean esSolucion (int[][] tablero, int total) {
		int parcial = 0;
		for(int i=0; i<tablero.length; i++) {
			for(int j=0;j<tablero.length; j++) {
				parcial = parcial + tablero[i][j]; 
			} 
		}	
		return parcial == total;
	}
	
	public static boolean imprimirTablero (int[][] tablero) {
		for(int i=0; i<tablero.length; i++) {		
			for(int j=0;j<tablero[i].length; j++) {
				System.out.print("|" + tablero[i][j] + "|");			
			}		
			System.out.println(); 
		}
		return true;
		
	}
	
	public static boolean estaDisponible(int i, boolean[] usados) {
		return usados[i -1] == true;
	}
	
	public static void backTablero(int[][] tablero, boolean[] usados, int total, int k, int fila, int columna , boolean control ) {		
		if( esHoja(tablero) ) {
			if (esSolucion(tablero, total)) {
				
				imprimirTablero(tablero);
				
			}
		} else  {
			for(int i=1;i<=k;i++) {
				if(estaDisponible(i, usados))  {
					if (fila < tablero.length && columna < tablero.length) {
						tablero[fila][columna] = i;
						usados[i - 1] = false;
					}			
					if(columna < tablero.length ) {
						backTablero(tablero, usados, total, k, fila, columna + 1, control);
					} else if (fila < tablero.length ) {			
						backTablero(tablero, usados, total, k, fila + 1, 0, control);					
					}									
				}	
				if (fila < tablero.length && columna < tablero.length) {
					tablero[fila][columna] = -1; 
					usados[i - 1] = true;
				}				
			}
		}
		
	}

	public static void main(String[] args) {
		//Inicializamos las variables necesarias
		int k = 10;
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
		
		backTablero(tableroMagico, valoresAUtilizar, 15, k, 0, 0, false);
		imprimirTablero(tableroMagico);
		
	}

}

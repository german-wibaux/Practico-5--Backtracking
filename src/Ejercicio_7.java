
public class Ejercicio_7 {
	
	public static boolean esHoja(int fila, int columna, int[][] hoja) {	
		boolean control = true;
		for(int i=0; i<hoja.length; i++) {
			for(int j=0;j<hoja.length; j++) {
				if (hoja[i][j] == -1) {
					control = false;
				}
			} 
		}
		return control;
//		return fila < hoja.length && columna < hoja.length;
	}
	
	public static boolean esSolucion (int[][] tablero, int total) {
//		int parcial = 0;
//		for(int i=0; i<tablero.length; i++) {
//			for(int j=0;j<tablero.length; j++) {
//				parcial = parcial + tablero[i][j]; 
//			} 
//		}	
//		return parcial == total;
		//imprimirTablero(tablero);
		int i = 0; 
		int j = 0;
		boolean control = true;
		int parcial = 0;
		
		while(i < tablero.length && control) {
			while(j < tablero.length && control) {
				if(tablero[i][j] != -1) {
					parcial = parcial + tablero[i][j];
				} else {
					control = false;
				}
				if (j == tablero.length-1) { 
					if(parcial == total) {
						parcial = 0;
					} else {
						control = false;
					}
				}
			j++;
			}
		i++;
		j=0;
		}
		
		i = 0;
		j = 0;
		parcial = 0;
		
		while(i < tablero.length && control) {
			while(j < tablero.length && control) {
				if(tablero[j][i] != -1) {
					parcial = parcial + tablero[j][i];
				} else {
					control = false;
				}
				if (j == tablero.length-1) {
					if(parcial == total) {
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
	
	public static boolean imprimirTablero (int[][] tablero) {
		for(int i=0; i<tablero.length; i++) {		
			for(int j=0;j<tablero[i].length; j++) {
				System.out.print("|" + tablero[i][j] + "|");			
			}		
			System.out.println(); 
		}
		System.out.println("////////////////////////////////////");
		return true;
		
	}
	
	//	public static boolean estaDisponible(int i, boolean[] usados) {
	//	return usados[i -1] == true;
	//}
	
	///** El parámetro "control" no se utiliza, se podría borrar **/
	///** El backtracking esta bien! **/
	public static void backTablero(int[][] tablero, boolean[] usados, int total, int k, int fila, int columna ) {		
		if( esHoja(fila, columna, tablero) ) {
			if (esSolucion(tablero, total)) {
				imprimirTablero(tablero);
				return;
			}
		} else  {
			for(int i=1;i<=k;i++) {
				if(usados[i-1])  {// /** if (usados[u-1]) { **/
					if (fila < tablero.length && columna < tablero.length) { /** Esta condicion no es necesaria, ya que habría entrado al "esHoja" **/ 
						tablero[fila][columna] = i;
						usados[i - 1] = false;
					}
					if(columna < tablero.length ) {
						backTablero(tablero, usados, total, k, fila, columna + 1);
					} else if (fila < tablero.length ) {			
						backTablero(tablero, usados, total, k, fila + 1, 0);					
					}
					if (fila < tablero.length && columna < tablero.length) {
						tablero[fila][columna] = -1; 
						usados[i - 1] = true;
					} 
			}	
								
		}
	}
	}
		
	

	public static void main(String[] args) {
		//Inicializamos las variables necesarias
		int k = 14;
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
		
		backTablero(tableroMagico, valoresAUtilizar, 15, k, 0, 0);
		//imprimirTablero(tableroMagico);
		
	}

}

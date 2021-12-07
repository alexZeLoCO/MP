import java.util.Scanner;

/**
 * Universidad de Oviedo. Trabajo fin de cuatrimestre. Asignatura: Introducción a la programación (IP).
 *
 * 	Proyecto "SumaTres". Se trata de un juego muy similar al "2048", pero utilizando múltiplos de 3 en lugar de 2.
 *
 * @author UO284095 ~ Celia Cuesta Loredo - PL05
 * @author UO282558 ~ Eva Álvarez Inda - PL03
 * @author UO281827 ~ Alejandro Rodríguez López - PL05
 */

public class Consola {
	
	private static int [][] tablero;

	/**
	 * Constructor Consola(). Llama a la subrutina iniciaJuego() para que prepare el tablero.
	 * @param teclado - Scanner.
	 */
	public Consola (Scanner teclado) {
		System.out.print("Introduzca número de filas y columnas separados por espacio: ");

		Consola.tablero = new int [teclado.nextInt()][teclado.nextInt()];
		for (int i=0;i<Consola.tablero.length;i++) {			//PARA CADA FILA DEL TABLERO
			for (int j=0;j<Consola.tablero[0].length;j++) {			//PARA CADA COLUMNA DEL TABLERO
				Consola.tablero[i][j]=0;		//DA VALOR NULO
			}
		}
		Consola.tablero=iniciaJuego(teclado);
	}

	/**
	 * Subrutina getTablero().
	 * @return tablero - int[][] representación matricial del tablero.
	 */
	public static int [][] getTablero() {
		return tablero;
	}

	/**
	 * Subrutina setTablero(). Coloca un valor en una posición específica del tablero.
	 * @param i	- FILA
	 * @param j - COLUMNA
	 * @param n - VALOR
	 */
	public static void setTablero(int i, int j, int n) {
		tablero[i][j]=n;
	}

	/**
	 *  Subrutina Debug(). La utilizamos para comprobar jugadas concretas. SÓLO USADO EN PRODUCCIÓN.
	 * @return Tablero de la jugada que queremos comprobar.
	 */
	public static int [][] debug () {
		int [][] tablero = {{12,2,2,2},
							{2,12,3,2},
							{2,48,12,2},
							{6,2,24,12}};
		return tablero;
	}

	/**
	 *  Subrutina help(). Imprime por pantalla los controles del modo consola.
	 */
	public static void help () {
		System.out.println();
		System.out.println("<-----------SECCIÓN DE AYUDA----------->");
		System.out.println("Evaluación de la puntuación: Sumatorio de valores de todas las casillas.");
		System.out.println();
		System.out.println();
		System.out.println("Para mover usar números del numpad.");
		System.out.println();
		System.out.println("                ARRIBA                ");
		System.out.println("                                      ");
		System.out.println("I                 8                   ");
		System.out.println("Z                                    D");
		System.out.println("Q                                    E");
		System.out.println("U                                    R");
		System.out.println("I    4            5             6    E");
		System.out.println("E                                    C");
		System.out.println("R                                    H");
		System.out.println("D                                    A");
		System.out.println("A                 2                   ");
		System.out.println("                                      ");
		System.out.println("                ABAJO                 ");
		System.out.println();
		System.out.println("<---------FIN SECCIÓN DE AYUDA--------->");
		System.out.println();

	}

	/**
	 *  Subrutina iniciaJuego(). Elige una combinación de 3 casillas y les da un valor a cada una entre [1,3].
	 * @return Tablero formado por las casillas aleatorias.
	 */
	public static int [][] iniciaJuego (Scanner teclado) {


		int [] a;			//CREA VECTOR DE VALORES
		for (int i=1;i<4;i++) {			//HACER 3 VALORES

			/*
			 * USAREMOS UN BUCLE DO WHILE PARA CREAR POSICIONES ALEATORIAS.
			 * SEGUIREMOS CREANDO POSICIONES HASTA QUE ENCONTREMOS UNA POSICIÓN QUE NO ESTÉ YA POBLADA POR UN VALOR.
			 */

			do {		//MIENTRAS
				a = nuevaPosición();			//NUEVA POSICIÓN ALEATORIA
			} while (tablero[a[0]][a[1]]!=0);		//SI LA POSICIÓN ALEATORIA ESTÁ POBLADA

			tablero[a[0]][a[1]]=i;			//NUEVO VALOR EN LA POSICIÓN NO POBLADA
		}

		return tablero;			//RETORNA TABLERO POBLADO POR 3 PIEZAS
	}

	/**
	 *  Subrutina imprimeTablero(). Muestra el tablero actual por pantalla.
	 * @param ronda - ronda actual.
	 */
	public static void imprimeTablero (int ronda) {
		
		System.out.println();		//SEPARADOR
		System.out.printf("~~~~~ RONDA: %d ~~~~~\n",ronda);			//RONDA
		
		for (int i=0;i<27;i++) {		//FORMATO
			System.out.print("=");			//FORMATO
		}

		System.out.println();			//SEPARADOR

		for (int i=0;i<getTablero().length;i++) {			//PARA CADA FILA
			System.out.print("||");			//FORMATO
			for (int j=0;j<getTablero()[i].length;j++) {			//PARA CADA COLUMNA
				System.out.printf("%4d",getTablero()[i][j]);			//IMPRIMR VALOR
			}
			System.out.print("   ||");		//FORMATO

			System.out.println();			//SEPARADOR
		}

		for (int i=0;i<27;i++) {			//FORMATO
			System.out.print("=");				//FORMATO
		}
		System.out.println();			//SEPARADOR
	}

	/**
	 * Subrutina movimiento. Mueve las piezas en la dirección pasada como parámetro.
	 * @param d - Dirección en la que mover las piezas.
	 * @return tablero - Tablero con las piezas movidas.
	 */
	public static int [][] movimiento (int d) {

		/*
		 * LA SUBRUTINA EVALÚA LA DIRECCIÓN D PASADA COMO PARÁMETRO.
		 * 	SI D ES 4 SIGNIFICA IZQUIERDA
		 *  SI D ES 8 SIGNIFICA ARRIBA
		 *  SI D ES 6 SIGNIFICA DERECHA
		 *  SI D ES 2 SIGNIFICA ABAJO
		 * 
		 * EN FUNCIÓN DE LA DIRECCIÓN ELEGIDA EVALUAREMOS LOS VALORES DE FORMAS DIFERENTES, PARA QUE SE SUMEN DE FORMA ADECUADA.
		 */


		/*
		 * ESTRATEGIA:
		 * 		UTILIZAREMOS UNA VARIABLE CONTADOR PARA CONTAR EL NÚMERO DE CAMBIOS QUE HEMOS REALIZADO YA. ÉSTO NOS SERVIRÁ PARA
		 * 		CONOCER SI LA PIEZA ESTÁ YA O NO COLOCADA EN SU POSICIÓN. EJEMPLO:
		 * 			PIEZA COLOCADA EN LA POSICIÓN [0][0], DEBEMOS MOVERLA A LA IZQUIERDA. LA PIEZA YA ESTÁ COLOCADA. COMO CONTADOR==[i]
		 * 			NO SE RECOLOCARÁ, PERO LA VARIABLE CONTADOR SÍ AUMENTARÁ EN 1.
		 * 
		 * 		LA VARIABLE CONTADOR NO TIENE POR QUÉ EMPEZAR EN 0 NI AUMENTAR EN 1. ESO DEPENDERÁ DE LA DIRECCIÓN EN LA QUE EVALUEMOS LAS PIEZAS.
		 * 
		 * 		SI LA PIEZA NO ESTUVIESE YA COLOCADA EN LA POSICIÓN CORRECTA, SE RECOLOCARÁ.
		 */

		//CASO DIRECCIÓN 4 ==> IZQUIERDA
		//ORDEN DE EVALUACIÓN: IZQUIERDA ARRIBA --> DERECHA ABAJO
		if (d==4) {
			for (int i=0;i<getTablero().length;i++) {				//FILA
				int contador = 0;			//CONTADOR EMPIEZA EN PRIMERA COLUMNA
				for (int j=0;j<getTablero()[i].length;j++) {					//COLUMNA
					if (getTablero()[i][j]!=0) {		//SI LA CASILLA ESTÁ POBLADA
						if (j!=contador) {		//Y LA POSICIÓN ES DIFERENTE AL CONTADOR (EL ELEMENTO NO ESTÁ YA COLOCADO)
							setTablero(i,contador,getTablero()[i][j]);			//RECOLOCAR PIEZA
							setTablero(i,j,0);			//ELIMINAR POSICIÓN INICIAL DE LA PIEZA
						}
						contador++;			//ACTUALIZAR CONTADOR
					}
				}
			}
		}

		//CASO DIRECCIÓN 6 ==> DERECHA
		//ORDEN DE EVALUACIÓN: DERECHA ARRIBA --> IZQUIERDA ABAJO
		if (d==6) {
			for (int i=0;i<getTablero().length;i++) {					//FILA
				int contador = getTablero()[i].length-1;			//CONTADOR EMPIEZA EN LA ÚLTIMA COLUMNA
				for (int j=getTablero()[i].length-1;j>=0;j--) {			//COLUMNA
					if (getTablero()[i][j]!=0) {		//SI LA CASILLA ESTÁ POBLADA
						if (j!=contador) {			//Y LA POSICIÓN ES DIFERENTE AL CONTADOR (EL ELEMENTO NO ESTÁ YA COLOCADO)
							setTablero(i,contador,getTablero()[i][j]);			//RECOLOCAR PIEZA
							setTablero(i,j,0);				//ELIMINAR POSICIÓN INICIAL DE LA PIEZA
						}
						contador--;			//ACTUALIZAR CONTADOR
					}
				}
			}
		}

		//CASO DIRECCIÓN 8 ==> ARRIBA
		//ORDEN EVALUACIÓN: ABAJO IZQUIERDA ---> ARRIBA DERECHA
		if (d==8) {
			for (int i=0;i<getTablero()[0].length;i++) {					//COLUMNA
				int contador = 0;		//CONTADOR EMPIEZA EN PRIMERA FILA
				for (int j=0;j<getTablero().length;j++) {					//FILA
					if (getTablero()[j][i]!=0) {			//SI LA CASILLA ESTÁ POBLADA
						if (j!=contador) {			//Y LA POSICIÓN ES DIFERENTE AL CONTADOR (EL ELEMENTO NO ESTÁ YA COLOCADO)
							setTablero(contador,i,getTablero()[j][i]);			//RECOLOCAR PIEZA
							setTablero(j,i,0);			//ELIMINAR POSICIÓN INICIAL DE LA PIEZA
						}
						contador++;			//ACTUALIZAR CONTADOR
					}
				}
			}
		}

		//CASO DIRECCIÓN 2 ==> ABAJO
		//ORDEN EVALUACIÓN: ARRIBA IZQUIERDA --> ABAJO DERECHA
		if (d==2) {
			for (int i=0;i<getTablero()[0].length;i++) {					//COLUMNA
				int contador = getTablero().length-1;		//CONTADOR EMPIEZA EN ÚLTIMA FILA
				for (int j=getTablero().length-1;j>=0;j--) {						//FILA
					if (getTablero()[j][i]!=0) {			//SI POSICIÓN ESTÁ POBLADA
						if (j!=contador) {				//Y CONTADOR ES DIFERENTE AL CONTADOR (EL ELEMENTO NO ESTÁ YA COLOCADO)
							setTablero(contador,i,getTablero()[j][i]);			//RECOLOCAR ELEMENTO
							setTablero(j,i,0);			//ELIMINAR POSICIÓN INICIAL DEL ELEMENTO
						}
						contador--;			//ACTUALIZAR CONTADOR
					}		
				}
			}
		}

		return getTablero();			//RETURN TABLERO ACTUALIZADO
	}

	/**
	 *  Subrutinas evaluar(). Evalúa si se pueden sumar valores, si es así, los suma.
	 * @param d - Dirección en la que evaluar.
	 * @return tablero - Tablero con las piezas sumadas.
	 */
	public static int [][] evaluar (int d) {


		/*
		 * MUY SIMILAR A LA SUBRUTINA MOVIMIENTO(). EN FUNCIÓN DE LA DIRECCIÓN PASADA COMO PARÁMETRO, COMPRUEBA LOS VALORES QUE SE DEBEN SUMAR,
		 * Y SI SE PUEDE, LOS SUMA EN LA DIRECCIÓN CORRECTA.
		 */

		//CASO DIRECCIÓN 4 ==> IZQUIERDA
		if (d==4) {
			for (int i=0;i<getTablero().length;i++) {			//FILA
				for (int j=0;j<getTablero()[i].length-1;j++) {				//COLUMNA
					if ((getTablero()[i][j]==getTablero()[i][j+1] && getTablero()[i][j]%3==0) || 		//VALORES IGUALES MÚLTIPLOS DE 3 
							(getTablero()[i][j]==1 && getTablero()[i][j+1]==2) || 					//VALORES 1 Y 2 
							(getTablero()[i][j]==2 && getTablero()[i][j+1]==1)) 	{					//VALORES 2 Y 1	

						setTablero(i,j,getTablero()[i][j]+getTablero()[i][j+1]);			//SUMAR VALORES
						setTablero(i,j+1,0);				//ELIMINAR VALOR RESTANTE
					}
				}
			}
		}

		//CASO DIRECCIÓN 6 ==> DERECHA
		if (d==6) {
			for (int i=0;i<getTablero().length;i++) {			//FILA
				for (int j=getTablero()[i].length-1;j>0;j--) {				//COLUMNA
					if ((getTablero()[i][j]==getTablero()[i][j-1] && getTablero()[i][j]%3==0) || 		//VALORES IGUALES MÚLTIPLOS DE 3
							(getTablero()[i][j]==1 && getTablero()[i][j-1]==2) || 					//VALORES 1 Y 2
							(getTablero()[i][j]==2 && getTablero()[i][j-1]==1)) {						//VALORES21 Y 1

						setTablero(i,j,getTablero()[i][j]+getTablero()[i][j-1]);			//SUMAR VALORES
						setTablero(i,j-1,0);				//ELIMINAR VALOR RESTANTE
					}
				}
			}
		}

		//CASO DIRECCIÓN 8 ==> ARRIBA
		if (d==8) {
			for (int i=0;i<getTablero()[0].length;i++) {			//COLUMNA
				for (int j=0;j<getTablero().length-1;j++) {				//FILA
					if ((getTablero()[j][i]==getTablero()[j+1][i] && getTablero()[j][i]%3==0) || 		//VALORES IGUALES MÚLTIPLOS DE 3 
							(getTablero()[j][i]==1 && getTablero()[j+1][i]==2) || 					//VALORES 1 Y 2 
							(getTablero()[j][i]==2 && getTablero()[j+1][i]==1)) {						//VALORES 2 Y 1

						setTablero(j,i,getTablero()[j][i]+getTablero()[j+1][i]);			//SUMAR VALORES
						setTablero(j+1,i,0);				//ELIMINAR VALOR RESTANTE
					}
				}
			}
		}

		//CASO DIRECCIÓN 2 ==> ABAJO
		if (d==2) {
			for (int i=0;i<getTablero()[0].length;i++) {			//COLUMNA
				for (int j=getTablero().length-1;j>0;j--) {				//FILA
					if ((getTablero()[j][i]==getTablero()[j-1][i] && getTablero()[j][i]%3==0) || 		//VALORES IGUALES MÚLTIPLOS DE 3 
							(getTablero()[j][i]==1 && getTablero()[j-1][i]==2) || 					//VALORES 1 Y 2 
							(getTablero()[j][i]==2 && getTablero()[j-1][i]==1)) {						//VALORES 2 Y 1

						setTablero(j,i,getTablero()[j][i]+getTablero()[j-1][i]);			//SUMAR VALORES
						setTablero(j-1,i,0);				//ELIMINAR VALOR RESTANTE
					}
				}
			}
		}
		return getTablero();			//RETURN TABLERO SUMADO
	}

	/**
	 *  Subrutina puntuación(). Calcula la puntuación del juego tal y como solicita el documento pdf. Suma cada ronda lo
	 *  que sumen los movimientos.
	 *
	 *  EL MODO CONSOLA FUNCIONA CON ESTE MÉTODO.
	 *
	 * @param d - Dirección del movimiento
	 * @return puntuación - Int equivalente al, valor que se debe sumar a la puntuación.
	 */
	public static int puntuación (int d) {

		int puntuación=0;

		//CASO DIRECCIÓN 4 ==> IZQUIERDA
		if (d==4) {
			for (int i=0;i<getTablero().length;i++) {			//FILA
				for (int j=0;j<getTablero()[i].length-1;j++) {				//COLUMNA
					if ((getTablero()[i][j]==getTablero()[i][j+1] && getTablero()[i][j]%3==0) || 		//VALORES IGUALES MÚLTIPLOS DE 3
							(getTablero()[i][j]==1 && getTablero()[i][j+1]==2) || 					//VALORES 1 Y 2
							(getTablero()[i][j]==2 && getTablero()[i][j+1]==1)) 	{					//VALORES 2 Y 1

						puntuación=getTablero()[i][j]+getTablero()[i][j+1];
					}
				}
			}
		}

		//CASO DIRECCIÓN 6 ==> DERECHA
		if (d==6) {
			for (int i=0;i<getTablero().length;i++) {			//FILA
				for (int j=getTablero()[i].length-1;j>0;j--) {				//COLUMNA
					if ((getTablero()[i][j]==getTablero()[i][j-1] && getTablero()[i][j]%3==0) || 		//VALORES IGUALES MÚLTIPLOS DE 3
							(getTablero()[i][j]==1 && getTablero()[i][j-1]==2) || 					//VALORES 1 Y 2
							(getTablero()[i][j]==2 && getTablero()[i][j-1]==1)) {						//VALORES 2 Y 1

						puntuación=getTablero()[i][j]+getTablero()[i][j-1];
					}
				}
			}
		}

		//CASO DIRECCIÓN 8 ==> ARRIBA
		if (d==8) {
			for (int i=0;i<getTablero()[0].length;i++) {			//COLUMNA
				for (int j=0;j<getTablero().length-1;j++) {				//FILA
					if ((getTablero()[j][i]==getTablero()[j+1][i] && getTablero()[j][i]%3==0) || 		//VALORES IGUALES MÚLTIPLOS DE 3
							(getTablero()[j][i]==1 && getTablero()[j+1][i]==2) || 					//VALORES 1 Y 2
							(getTablero()[j][i]==2 && getTablero()[j+1][i]==1)) {						//VALORES 2 Y 1

						puntuación=getTablero()[j][i]+getTablero()[j+1][i];
					}
				}
			}
		}

		//CASO DIRECCIÓN 2 ==> ABAJO
		if (d==2) {
			for (int i=0;i<getTablero()[0].length;i++) {			//COLUMNA
				for (int j=getTablero().length-1;j>0;j--) {				//FILA
					if ((getTablero()[j][i]==getTablero()[j-1][i] && getTablero()[j][i]%3==0) || 		//VALORES IGUALES MÚLTIPLOS DE 3
							(getTablero()[j][i]==1 && getTablero()[j-1][i]==2) || 					//VALORES 1 Y 2
							(getTablero()[j][i]==2 && getTablero()[j-1][i]==1)) {						//VALORES 2 Y 1

						puntuación=getTablero()[j][i]+getTablero()[j-1][i];
					}
				}
			}
		}

		return puntuación;
	}

	/**
	 *  Subrutina posibleJugada(). Evalúa si se pueden sumar valores, si es así, NO los suma.
	 * @return out - Boolean en función de si se pueden o no sumar algunos valores.
	 */
	public static boolean posibleJugada () {

		/*
		 * MUY SIMILAR A SUBRUTINA EVALUAR(). EN LUGAR DE DEVOLVER EL TABLERO ACTUALIZADO, RETORNA UN BOOLEAN TRUE SI SE PUEDEN REALIZAR
		 * JUGADAS, FALSE SI NO HAY NINGUNA JUGADA POSIBLE. LAS CONDICIONES DE LOS BUCLES SON LAS MISMAS QUE LAS DE LA SUBRUTINA EVALUAR().
		 * 
		 * NOTA: NO HAY CONDICIONES PORQUE EVALÚA POSIBLES JUGADAS EN CUALQUIER DIRECCIÓN.
		 */

		//---DIRECCIÓN IZQUIERDA---
		for (int i=0;i<getTablero().length;i++) {			//FILA
			for (int j=0;j<getTablero()[i].length-1;j++) {				//COLUMNA
				if ((getTablero()[i][j]==getTablero()[i][j+1] && getTablero()[i][j]%3==0) || 
						(getTablero()[i][j]==1 && getTablero()[i][j+1]==2) || 
						(getTablero()[i][j]==2 && getTablero()[i][j+1]==1)) {

					return true;				//JUGADA POSIBLE

				}
			}
		}


		//---DIRECCIÓN DERECHA---
		for (int i=0;i<getTablero().length;i++) {			//FILA
			for (int j=getTablero()[i].length-1;j>0;j--) {				//COLUMNA
				if ((getTablero()[i][j]==getTablero()[i][j-1] && getTablero()[i][j]%3==0) || 
						(getTablero()[i][j]==1 && getTablero()[i][j-1]==2) || 
						(getTablero()[i][j]==2 && getTablero()[i][j-1]==1)) {

					return true;				//JUGADA POSIBLE

				}
			}
		}


		//---DIRECCIÓN ARRIBA---
		for (int i=0;i<getTablero()[0].length;i++) {			//COLUMNA
			for (int j=0;j<getTablero().length-1;j++) {				//FILA
				if ((getTablero()[j][i]==getTablero()[j+1][i] && getTablero()[j][i]%3==0) || 
						(getTablero()[j][i]==1 && getTablero()[j+1][i]==2) || 
						(getTablero()[j][i]==2 && getTablero()[j+1][i]==1)) {

					return true;				//JUGADA POSIBLE	

				}
			}
		}


		//---DIRECCIÓN ABAJO---
		for (int i=0;i<getTablero()[0].length;i++) {			//COLUMNA
			for (int j=getTablero().length-1;j>0;j--) {				//FILA
				if ((getTablero()[j][i]==getTablero()[j-1][i] && getTablero()[j][i]%3==0) || 
						(getTablero()[j][i]==1 && getTablero()[j-1][i]==2) || 
						(getTablero()[j][i]==2 && getTablero()[j-1][i]==1)) {

					return true;				//JUGADA POSIBLE

				}
			}
		}

		return false;			//RETORNAR OUT
	}

	/**
	 *	Subrutina optimoValor(). Cuenta cuántos números básicos (1, 2 y 3) hay en el tablero.
	 * @return orden - Vector que en sus posiciones tiene el número de valores {número de 1, número de 2, número de 3}.
	 */
	public static int [] sumaBásicos () {
		int [] orden = {0,0,0};			//VECTOR. POSICIÓN 0=NÚMERO DE 1, POSICIÓN 1=NÚMERO DE 2, POSICIÓN 2=NÚMERO DE 3.

		for (int i=0;i<getTablero().length;i++) {		//PARA CADA FILA
			for (int j=0;j<getTablero()[0].length;j++) {		//PARA CADA COLUMNA
				if (getTablero()[i][j]==1 || getTablero()[i][j]==2 || getTablero()[i][j]==3) {		//SI EL VALOR ES 1 2 Ó 3
					orden[getTablero()[i][j] - 1]++;			//SUMAR 1 A LA POSICIÓN DEBIDA.
				}
			}
		}
		return orden;			//RETORNAR CUENTA
	}

	/**
	 * 	Subrutina valores(). Utilizando la subrutina optimoValor(), devuelve un vector con los números básicos (1, 2 y 3)
	 * 	ordenados en función de su necesidad.
	 *
	 * 	Necesidad: Conveniencia para el jugador de un número sobre otro.
	 * 			Si el número predominante es el 1, convienen los 2 para formar 3.
	 * 			Si el número predominante es el 2, convienen los 1 para formar 3.
	 * 			Si el número predominante es el 3, convienen los 3 para formar 6.
	 *
	 * @return - Vector con los números básicos ordenados en orden de necesidad.
	 */
	public static int [] optimoValor () {
		int [] optimo = sumaBásicos();			//RECIBE LA CUENTA DE NÚMEROS

		int [] valores = new int [3];			//CREA VECTOR.

		/*
			EN EL VECTOR VALORES, COLOCAREMOS LOS VALORES BÁSICOS (1, 2 Y 3) EN EL ORDEN QUE SEAN MÁS NECESITADOS.
		 */

		for (int i = 0; i < 3; i++) {			//REPETIR 3 VECES
			if (optimo[0] >= optimo[1] && optimo[2] <= optimo[0]) {		//SI EL MAYOR ES EL 2
				valores[i] = 2;		//VALOR 2
				optimo[0] = -1;		//RETIRAR VALOR
			} else {
				if (optimo[1] > optimo[0] && optimo[2] < optimo[1]) {			//SI EL MAYOR ES EL 1
					valores[i] = 1;			//VALOR 1
					optimo[1] = -1;			//RETIRAR VALOR
				} else {		//SI EL MAYOR ES EL 3
					valores[i] = 3;			//VALOR 3
					optimo[2] = -1;			//RETIRAR VALOR
				}
			}
		}

		return valores;			//RETORNAR VECTOR CON VALORES ORDENADOS
	}

	/**
	 *  Subrutina nuevoValor(). Crea un valor aleatorio entre [1,3] para una pieza nueva.
	 *
	 *  Utiliza la subrutina valores() para dar diferentes probabilidades a los valores en función de su necesidad.
	 *  	Al más necesitado se le da un 50% de probabilidad.
	 *  	Al segundo, un 30%
 	 *		Al último, un 20%
	 *
	 * @return valor - Entero entre [1,3]
	 */
	public static int nuevoValor() {

		int [] orden = optimoValor();			//RECIBE ORDEN DE VALORES NECESARIOS

		double valor=Math.random();			//DOBLE ENTRE 0 Y 1
		if (valor<=0.5) {			//50%
			valor = orden[0];			//VALOR MÁS NECESITADO
		} else {
			if (valor<=0.8) {			//30%
				valor = orden[1];			//VALOR NO TAN NECESITADO
				
			} else {						//20%
				valor = orden[2];				//VALOR MENOS NECESITADO
			}								
		}
		return (int)valor;			//DEVOLVER VALOR ENTERO
	}

	/**
	 *  Subrutina nuevaPosición(). Crea una posición (x,y).
	 * @return pos - Vector tal que [fila,columna]
	 */
	public static int[] nuevaPosición() {

		/*
		 * SIMPLE ALGORITMO PARA CREAR UNA POSICIÓN DEL TABLERO.
		 */
		
		int x=0;			//COORDENADA X
		int y=0;			//COORDENADA Y

		double a = Math.random();			//DOBLE ALEATORIO ENTRE 0 Y 1
		
		int i=1;			//MÚLTIPLO ~ SERÁ IGUAL AL NÚMERO DE FILA
		while (a>i*(1.0/tablero.length)) {			//BUSCAMOS EL MÚLTIPLO QUE SUPERE A a.
			i++;			//ACTUALIZAR I
		}
		x=i-1;			//AL SALIR I EXCEDE EN 1, SE LO RESTAMOS.

		a = Math.random();			//NUEVO DOBLE ALEATORIO ENTRE 0 Y 1	
		
		i=1;				//MÚLTIPLO ~ SERÁ IGUAL AL NÚMERO DE COLUMNA
		while (a>i*(1.0/tablero[0].length)) {		//BUSCAMOS EL MÚLTIPLO QUE SEUPERE A a.
			i++;			//ACTUALIZAR I
		}
		y=i-1;			//AL SAIR I EXCEDE EN 1, SE LO RESTAMOS.

		int [] pos = {(int)x,(int)y};			//VECTOR CON LAS POSICIONES
		return pos;			//RETORNAR VECTOR
	}

	/**
	 *  Subrutina lleno(). Evalúa si quedan o no espacios vacíos en el tablero.
	 * @return Boolean en función de si quedan o no 0 en el tablero.
	 */
	public static boolean lleno () {

		/*
		 * COMPRUEBA SI QUEDAN O NO CASILLAS NO POBLADAS
		 */

		int i=-1;			//INICIALIZA LÍNEA EN -1
		int j;				//INICIALIZA COLUMNA

		do {
			j=-1;				//ACTUALIZA COLUMNA EN -1
			i++;			//ACTUALIZA FILA
			do {
				j++;			//ACTUALIZA COLUMNA
			} while (j<Consola.getTablero()[i].length-1 && Consola.getTablero()[i][j]!=0);			//MIENTRAS HAYA Y  ESTÉ POBLADA
		} while  (i<Consola.getTablero().length-1 && Consola.getTablero()[i][j]!=0);			//MIENTRAS HAYA Y ESTÉ POBLADA
		return !(i==Consola.getTablero().length-1 && j==Consola.getTablero()[0].length-1 && Consola.getTablero()[i][j]!=0);
	}

	/**
	 *  Subrutina puntuaciónModificada(). Forma diferente de calcular la puntuación.
	 *
	 * CALCULA LA PUNTUACIÓN = SUMATORIO DE TODOS LOS VALORES Y MULTIPLICADORES.
	 * 
	 * 	SUMA DE TODOS LOS NÚMEROS EN EL TABLERO.
	 * 	MULTIPLICADOR 1: NÚMERO DE RONDA * 0.2
	 * 	MULTIPLICADOR 2: EN FUNCIÓN DE CUÁN ALTO SEA CADA NÚMERO:
	 * 			3   =  x0.025
	 * 			6   =  x0.05
	 * 			12  =  x0.1
	 * 			24  =  x0.2
	 * 			48  =  x0.4
	 * 			96  =  x0.8
	 * 			192 =  x1.6
	 * 			384 =  x3.2
	 * 			...
	 * 			-EL MULTIPLICADOR SIGUIENTE ES EL DOBLE DEL ANTERIOR-
	 * 
	 *  SE UTILIZA EN EL MODO GRÁFICO.
	 *
	 * @return Sumatorio de todos los valores.
	 */
	public static double puntuaciónModificada (int ronda) {

		double contador = 0;			//PUNTUACIÓN
		int m=1;
		
		for (int i=0;i<getTablero().length;i++) {			//PARA CADA FILA
			for (int j=0;j<getTablero()[i].length;j++) {			//PARA CADA COLUMNA
				int valor=getTablero()[i][j];
				
				//CALCULA MULTIPLICADOR 2
				while (valor>3) {
					m++;
					valor=valor/2;
				}
									//SUMA MULTIPLICADOR 2
				contador = contador + valor + 0.025*m;				//SUMAR VALOR A PUNTUACIÓN
			}
		}
					//MULTIPLICADOR 1
		return contador+ronda*0.2;			//RETORNAR PUNTUACIÓN
	}


	public static void main (String[] args) {

		Scanner teclado = new Scanner (System.in);			//CREA SCANNER

		Consola t = new Consola (teclado);
		int ronda=0;					//CONTADOR DE RONDA (MULTIPLICADOR)
		int puntuación =0;

		while (Consola.lleno() || Consola.posibleJugada()) {			//MIENTRAS SE PUEDA JUGAR
			
			ronda++; 		//ACTUALIZA RONDA
			
			int valor=nuevoValor();						//ANTICIPA SIGUIENTE VALOR
			Consola.imprimeTablero(ronda);				//IMPRIME TABLERO

			System.out.printf("Puntuación: %d.   <----->   ",puntuación);			//IMPRIME PUNTUACIÓN
			System.out.printf("Siguiente: %d.\n", valor);			//IMPRIME SIGUIENTE VALOR
			System.out.printf("Multiplicador de ronda: %.2f.\n", ronda*0.2);
			System.out.print("Jugada seleccionada (5 para ayuda): ");					//SOLICITA DIRECCIÓN
			int dir = teclado.nextInt();			//ALMACENA DIRECCIÓN

			while (dir!=4 && dir!=2 && dir!=6 && dir!=8) {		//MIENTRAS DIR NO SEA  UNA DIRECCIÓN
				
				if (dir==5) {			//SI INPUT ES 5 (AYUDA)
					help();			//IMPRIMIR AYUDA
				}
				System.out.print("Jugada seleccionada (5 para ayuda): ");					//SOLICITA DIRECCIÓN
				dir = teclado.nextInt();			//ALMACENA DIRECCIÓN
			}


			tablero=movimiento(dir);			//MUEVE PIEZAS
			puntuación=puntuación+puntuación(dir);		//SUMA PUNTUACIÓN -ANTES DE SUMAR LAS PIEZAS-
			tablero=evaluar(dir);				//SUMA PIEZAS
			tablero=movimiento(dir);			//MUEVE PIEZAS

			int [] a;			//CREA VECTOR PARA POSICIÓN DE NUEVA PIEZA
			do {			//MIENTRAS
				a = nuevaPosición();			//CREA POSICIÓN
			} while (tablero[a[0]][a[1]]!=0);			//HASTA QUE NO ESTÉ POBLADA
			tablero[a[0]][a[1]]=valor;			//CREA POSICIÓN Y DA VALOR ANTICIPADO
		}

		//FIN DE JUEGO
		imprimeTablero(ronda);			//IMPRIME TABLERO FINAL
		System.out.println("</---FIN DE LA PARTIDA--->");			//AVISO
		System.out.printf("Puntuación: %d.",puntuación);			//OUTPUT DE PUNTUACIÓN

	}

}

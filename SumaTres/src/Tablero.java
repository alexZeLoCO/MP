import java.awt.*;
import javax.swing.*;

/**
 * Universidad de Oviedo. Trabajo fin de cuatrimestre. Asignatura: Introducción a la programación (IP).
 *
 * 	Proyecto "SumaTres". Se trata de un juego muy similar al "2048", pero utilizando múltiplos de 3 en lugar de 2.
 *
 * @author UO284095 ~ Celia Cuesta Loredo - PL05
 * @author UO282558 ~ Eva Álvarez Inda - PL03
 * @author UO281827 ~ Alejandro Rodríguez López - PL05
 */

public class Tablero extends JPanel  {

	public Pieza [][] valores;			//MATRIZ DE VALORES -COPIA DE LA MATRIZ DE CONSOLA-.

	/**
	 * Constructor Tablero().
	 * @param fil - Número de filas.
	 * @param col - Número de columnas.
	 * @param matriz - Matriz de Consola.
	 */
	Tablero(int fil, int col, int[][] matriz) {
		
		setPreferredSize(new Dimension (100*col,100*fil));		//DA TAMAÑO

		
		setLayout(new GridLayout(fil,col));			//TIPO DE LAYOUT: GRID -COMO UNA MATRIZ-.
		valores = new Pieza [fil][col];			//ATRIBUTO VALORES, MATRIZ DE PIEZAS.
		for (int i=0;i<fil;i++) {		//PARA CADA FILA
			for (int j=0;j<col;j++) {			//PARA CADA COLUMNA
				valores[i][j]=new Pieza (matriz[i][j]);			//NUEVA PIEZA
				add(valores[i][j]);		//AÑADIR PIEZA
			}
		}
	}

	/**
	 * Subrutina setValor().
	 * @param fil - Posición de fila.
	 * @param col - Posición de columna.
	 * @param n	- Valor a colocar.
	 */
	private void setValor (int fil, int col, int n) {
		valores[fil][col].setValor(n);			//COLOCAR VALOR
	}

	/**
	 * Subrutina evalua(). Sustituye los valores de la matriz pasada en los de la atributo.
	 * 	Básicamente, 'copia' de la matriz de consola los valores.
	 * @param matriz - Matriz de consola.
	 */
	public void evalua(int [][] matriz) {
		for (int i=0;i<matriz.length;i++) {			//PARA CADA FILA
			for (int j=0;j<matriz[0].length;j++) {		//PARA CADA COLUMNA

				/*
					PROBLEMA DE JAVA USANDO VARIOS THREAD, DAR TIEMPO A UNO PARA ESPERAR A OTRO.
					Thread.sleep();
				 */

				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				setValor(i, j, matriz[i][j]);			//DA VALOR
			}
		}
	}

/*
//--------------MAIN PARA PROBAR TABLERO---------------
	public static void main(String args[]) { 
		JFrame frame = new JFrame();
		JPanel panel = new JPanel(); 
		Tablero tablero = new Tablero(4,4);
		panel.add(tablero); 
		frame.add(panel); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.pack();
		frame.setVisible(true);
		int [][] matriz =  {{1,1,1,1},
							{1,1,1,1},
							{1,1,1,1},
							{1,1,1,1}};
		tablero.evalua(matriz);
	}
*/
}

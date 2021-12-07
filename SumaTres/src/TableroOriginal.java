import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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


public class TableroOriginal extends JPanel {
	private int tamañoX;
	private int tamañoY;
	private boolean click = false;
	
	/**
	 * Constructor TableroOriginal().
	 * Añade el mouseListener, define su tamaño y distribución.
	 */
	TableroOriginal() {
		tamañoX=Consola.getTablero()[0].length*200;
		tamañoY=Consola.getTablero().length*200;
		setSize(tamañoX,tamañoY);			//TAMAÑO
		setLayout(new BorderLayout());			//TIPO LAYOUT: BORDER
		addMouseListener(new MouseHandler());		//AÑADIR MOUSELISTENER
	}
	
	/**
	 * Subrutina toggleClick(). Cambia la paridad de la variable boolean click. Si es false, la hace true, y viceversa.
	 */
	public void toggleClick() {
		click= !click;
	}

	/**
	 * Subrutina getClick(). Devuelve la paridad de la variable boolean click.
	 * @return click - Boolean. True ==> Esperando click, False ==> No esperando click.
	 */
	public boolean getClick() {
		return click;
	}
	
	private class MouseHandler extends MouseAdapter {
		/**
		 * Subrtuina mouseClicked().
		 * @param e
		 */
		public void mouseClicked(MouseEvent e) {

			//JOptionPane.showMessageDialog(null, String.format("Ratón %d,%d \n",e.getX(),e.getY()));
			
			if (click) {		//SÓLO SI ESTÁ ESPERANDO UN CLICK
				if (e.getY() <= 100) {				//COORDENADA Y MENOR QUE 100 (ARRIBA)
					//MÉTODOS PARA MOVER Y CONTAR HACIA ARRIBA.
					Consola.movimiento(8);
					Consola.evaluar(8);
					Consola.movimiento(8);
					
				} else {
					if (e.getY() >= tamañoY-100) {			//COORDENADA Y MAYOR QUE 500 (ABAJO)
						//MÉTODOS PARA MOVER Y CONTAR HACIA ARRIBA.
						Consola.movimiento(2);
						Consola.evaluar(2);
						Consola.movimiento(2);
						
					} else {
						if (e.getX() <= 100) {			//COORDENADA X MENOR QUE 100 (IZQUIERDA)
							//MÉTOODS PARA MOVER Y CONTAR HACIA LA IZQUIERDA.
							Consola.movimiento(4);
							Consola.evaluar(4);
							Consola.movimiento(4);
							
						} else {
							if (e.getX() >= tamañoX-100) {		//DERECHA
								//MÉTODOS PARA MOVER Y CONTAR HACIA LA DERECHA.
								Consola.movimiento(6);
								Consola.evaluar(6);
								Consola.movimiento(6);
							}
						}
					}
				}
				toggleClick();			//CLICK RECIBIDO ==> CAMBIAR PARIDAD DE CLICK
			}
					//SI NO SE CUMPLIÓ NINGUNA
			if (!((e.getY() <= 100) || (e.getY() >= tamañoY-100) || (e.getX() <= 100) || (e.getX() >= tamañoX-100))) {
				toggleClick();			//CLICK VÁLIDO NO RECIBIDO ==> CAMBIAR PARIDAD DE CLICK
			}
		}
	}
}
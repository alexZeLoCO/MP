import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 * Universidad de Oviedo. Trabajo fin de cuatrimestre. Asignatura: Introducci�n a la programaci�n (IP).
 *
 * 	Proyecto "SumaTres". Se trata de un juego muy similar al "2048", pero utilizando m�ltiplos de 3 en lugar de 2.
 *
 * @author UO284095 ~ Celia Cuesta Loredo - PL05
 * @author UO282558 ~ Eva �lvarez Inda - PL03
 * @author UO281827 ~ Alejandro Rodr�guez L�pez - PL05
 */


public class TableroOriginal extends JPanel {
	private int tama�oX;
	private int tama�oY;
	private boolean click = false;
	
	/**
	 * Constructor TableroOriginal().
	 * A�ade el mouseListener, define su tama�o y distribuci�n.
	 */
	TableroOriginal() {
		tama�oX=Consola.getTablero()[0].length*200;
		tama�oY=Consola.getTablero().length*200;
		setSize(tama�oX,tama�oY);			//TAMA�O
		setLayout(new BorderLayout());			//TIPO LAYOUT: BORDER
		addMouseListener(new MouseHandler());		//A�ADIR MOUSELISTENER
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

			//JOptionPane.showMessageDialog(null, String.format("Rat�n %d,%d \n",e.getX(),e.getY()));
			
			if (click) {		//S�LO SI EST� ESPERANDO UN CLICK
				if (e.getY() <= 100) {				//COORDENADA Y MENOR QUE 100 (ARRIBA)
					//M�TODOS PARA MOVER Y CONTAR HACIA ARRIBA.
					Consola.movimiento(8);
					Consola.evaluar(8);
					Consola.movimiento(8);
					
				} else {
					if (e.getY() >= tama�oY-100) {			//COORDENADA Y MAYOR QUE 500 (ABAJO)
						//M�TODOS PARA MOVER Y CONTAR HACIA ARRIBA.
						Consola.movimiento(2);
						Consola.evaluar(2);
						Consola.movimiento(2);
						
					} else {
						if (e.getX() <= 100) {			//COORDENADA X MENOR QUE 100 (IZQUIERDA)
							//M�TOODS PARA MOVER Y CONTAR HACIA LA IZQUIERDA.
							Consola.movimiento(4);
							Consola.evaluar(4);
							Consola.movimiento(4);
							
						} else {
							if (e.getX() >= tama�oX-100) {		//DERECHA
								//M�TODOS PARA MOVER Y CONTAR HACIA LA DERECHA.
								Consola.movimiento(6);
								Consola.evaluar(6);
								Consola.movimiento(6);
							}
						}
					}
				}
				toggleClick();			//CLICK RECIBIDO ==> CAMBIAR PARIDAD DE CLICK
			}
					//SI NO SE CUMPLI� NINGUNA
			if (!((e.getY() <= 100) || (e.getY() >= tama�oY-100) || (e.getX() <= 100) || (e.getX() >= tama�oX-100))) {
				toggleClick();			//CLICK V�LIDO NO RECIBIDO ==> CAMBIAR PARIDAD DE CLICK
			}
		}
	}
}
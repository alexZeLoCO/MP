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

public class Pieza extends JComponent{

	private static int valor=0;			//VALOR DE LA PIEZA
	final private static int lado=100;		//TAMAÑO
	final private static Font letra = new Font ("Consolas", Font.PLAIN, 40);		//FORMATO

	/**
	 * Constructor Pieza().
	 * @param n
	 */
	Pieza (int n) {
		setValor(n);		//DA EL VALOR
		setPreferredSize(new Dimension(lado,lado));			//DA LA DIMENSIÓN
	}

	/**
	 * Subrutina setValor(). Da un valor concreto a una pieza y la pinta.
	 * @param n
	 */
	public void setValor (int n) {

		/*
			PROBLEMA DE JAVA USANDO VARIOS THREAD, DAR TIEMPO A UNO PARA ESPERAR A OTRO.
			Thread.sleep();
		 */
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		valor=n;		//DA VALOR
		repaint();		//REPINTA
	}
	
	
	/**
	 * Subrutina getValor(). Devuelve el valor de la pieza.
	 * @return
	 */
	public int getValor () {
		return valor;
	}

	/**
	 * Subrutina getLado(). Devuelve la longitud de la pieza.
	 * @return
	 */
	public int getLado() {
		return lado;
	}

	/**
	 * Subrutina toString(). Devuelve el valor de la pieza como String.
	 * @return
	 */
	@Override
	public String toString() {
		return String.format("%s", this.valor);
	}

	/**
	 * Subrutina paintComponent(). Pinta la pieza.
	 * @param g
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);


				//APLICAR ANTIALISING -OPCIONAL, SE VEN MEJOR CON ANTIALISING-.
		((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		

		if (getValor()!=0) {

			/*
			ESTRATEGIA: CREAMOS UN VECTOR CON DIFERENTES COLORES. HALLAMOS EL MÚLTIPLO DE 2 QUE DA EL VALOR DE LA PIEZA
						Y LE DAMOS A ESA PIEZA EL COLOR EN POSICIÓN IGUAL A DICHO MÚLTIPLO.
			 */

				//VECTOR DE COLORES
			Color [] colores = {Color.black,Color.white,Color.green,Color.red};

			Color color;		//COLOR ELEGIDO
			int numero = getValor();		//RECIBIMOS VALOR DE PIEZA

			if (numero==0) {		
				color=colores[0];
			} else {			
				if (numero==1) {			
					color=colores[1];			
				} else {
					if (numero==8) {			
						color=colores[2];		
					} else {
						color=colores[3];
					}
					
					g.setColor(color);			//ELEGIR COLOR
					
				}
			}

			g.fillRoundRect(0, 0, lado, lado, lado/3, lado/3);			//PINTAR PIEZA
			
			/*
			g.setFont(letra);		//FUENTE DE PIEZA
			g.setColor(Color.black);			//COLOR DE LETRA = NEGRO
			FontMetrics fuente = getFontMetrics(letra);
			String cadena = toString();			//VALOR DE LA PIEZA EN STRING
					//ESCRIBIR VALOR
			g.drawString(cadena, (lado - fuente.stringWidth(cadena)) /2, lado/2 + fuente.getAscent()/3);
			*/
		}
	}

/*
//----------MAIN PARA PROBAR FUNCIONAMIENTO DE PIEZAS------------------
	public static void main(String args[]) { 
		JFrame frame = new JFrame();
		JPanel panel = new JPanel(); 
		panel.add(new Pieza(16)); 
		frame.add(panel); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.pack();
		frame.setVisible(true);
	}
*/
}

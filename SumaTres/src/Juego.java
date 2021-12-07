import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Universidad de Oviedo. Trabajo fin de cuatrimestre. Asignatura: Introducci�n a la programaci�n (IP).
 *
 * 	Proyecto "SumaTres". Se trata de un juego muy similar al "2048", pero utilizando m�ltiplos de 3 en lugar de 2.
 *
 * @author UO284095 ~ Celia Cuesta Loredo - PL05
 * @author UO282558 ~ Eva �lvarez Inda - PL03
 * @author UO281827 ~ Alejandro Rodr�guez L�pez - PL05
 */

public class Juego extends JFrame{
	
	public static void main(String[] args) {
		Scanner teclado = new Scanner (System.in);			//CREA TECLADO
		Consola tablero = new Consola (teclado);				//INICIALIZA TABLERO
		
		Juego ventana = new Juego();			//CREA VENTANA
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			//CERRAR VENTANA
		ventana.setSize(new Dimension(700,1000));			//TAMA�O DE VENTANA
		ventana.setResizable(false);			//BLOQUEAR TAMA�O DE VENTANA
		ventana.setLocationRelativeTo(null);			//CENTRAR VENTANA
		
		ventana.setLayout(new BorderLayout());		//TIPO DE LAYOUT: BORDER (NORTH TABLERO, SOUTH INFORMACI�N)
		
		JPanel info = new JPanel();			//PANEL INFORMACI�N (SOUTH VENTANA)
		info.setSize(700,400);		//TAMA�O PANEL
        info.setBackground(Color.gray);			//COLOR PANEL
        info.setLayout(new BorderLayout());				//TIPO LAYOUT: GRID
        ventana.add(info, BorderLayout.SOUTH);			//COLOCAR EN VENTANA, SOUTH
        JLabel Puntuaci�n = new JLabel();			//ETIQUETA PARA PUNTUACI�N
        String text = new String();			//CADENA PARA PUNTUACI�N
        Puntuaci�n.setText(text);			//COLOCAR CADENA EN ETIQUETA
        Puntuaci�n.setFont(new Font ("Consolas",Font.BOLD,20));			//FORMATO DE ETIQUETA
        Puntuaci�n.setForeground(Color.WHITE);			//COLOR DE TEXTO
        Puntuaci�n.setVisible(true);			//VISIBILIDAD ETIQUETA
        info.add(Puntuaci�n,BorderLayout.NORTH);			//A�ADIR EN NORTH
        info.setVisible(true);			//VISIBILIDAD PANEL INFORMACI�N
        
        JLabel finPartida = new JLabel("Fin de partida");			//ETIQUETA
        finPartida.setPreferredSize(new Dimension(300,100));
		finPartida.setForeground(Color.RED);			//COLOR TEXTO
		finPartida.setFont(new Font("Consolas",Font.BOLD,22));			//FORMATO DE ETIQUETA
		finPartida.setVisible(false);			//VISIBILIDAD ETIQUETA
		info.add(finPartida,BorderLayout.WEST);			//A�ADIR ETIQUETA
		
        JPanel siguiente = new JPanel();			//PANEL PARA SIGUIENTE VALOR
        siguiente.setLayout(new BorderLayout());
        JLabel txt = new JLabel("Siguiente Pieza: ");			//ETIQUETA SIGUIENTE VALOR
        txt.setFont(new Font ("Consolas",Font.BOLD,20));			//FORMATO ETIQUETA
        Pieza next = new Pieza(24);		//PIEZA SIGUIENTE
        siguiente.add(txt, BorderLayout.WEST);			//A�ADIR ETIQUETA A PANEL
        siguiente.add(next, BorderLayout.EAST);			//A�ADIR PIEZA A PANEL
        next.setVisible(true);			//VISIBILIDAD PIEZA
        info.add(siguiente, BorderLayout.CENTER);			//A�ADIR PANEL SIGUIENTE A PANEL INFO
        siguiente.setVisible(true);     			//VISIBILIDAD PANEL
		
		TableroOriginal principal = new TableroOriginal();			//TABLEROORIGINAL, CONTIENE EL MOUSELISTENER
				
		JPanel arriba = new JPanel();			//PANEL ARRIBA
		arriba.setBackground(Color.red);		//COLOR PANEL
		arriba.setPreferredSize(new Dimension (700,100));		//TAMA�O PANEL
		principal.add(arriba,BorderLayout.NORTH);		//POSICI�N PANEL
		JLabel arribaLabel = new JLabel("ARRIBA");			//ETIQUETA PANEL
		arribaLabel.setFont(new Font("Consolas",Font.BOLD,30));			//FORMATO ETIQUETA
		arribaLabel.setForeground(Color.CYAN);		//COLOR TEXTO
		arriba.add(arribaLabel);			//A�ADIR ETIQUETA

		//LO MISMO PARA LOS DEM�S PANELES
		JPanel abajo = new JPanel();
		abajo.setBackground(Color.blue);
		abajo.setPreferredSize(new Dimension (700,100));
		principal.add(abajo,BorderLayout.SOUTH);
		JLabel abajoLabel = new JLabel("ABAJO");
		abajoLabel.setFont(new Font("Consolas",Font.BOLD,30));
		abajoLabel.setForeground(Color.YELLOW);
		abajo.add(abajoLabel);
		
		JPanel derecha = new JPanel();
		derecha.setBackground(Color.green);
		derecha.setPreferredSize(new Dimension (100,600));
		principal.add(derecha,BorderLayout.EAST);
		JLabel derechaLabel = new JLabel("<HTML>D<br>E<br>R<br>E<br>C<br>H<br>A</HTML>");		//FORMATO TEXTO VERTICAL
		derechaLabel.setFont(new Font("Consolas",Font.BOLD,30));
		derechaLabel.setForeground(Color.BLUE);
		derecha.add(derechaLabel);
		
		JPanel izquierda = new JPanel();
		izquierda.setBackground(Color.yellow);
		izquierda.setPreferredSize(new Dimension (100,600));
		principal.add(izquierda,BorderLayout.WEST);
		JLabel izquierdaLabel = new JLabel("<HTML>I<br>Z<br>Q<br>U<br>I<br>E<br>R<br>D<br>A</HTML>");		//FORMATO TEXTO VERTICAL
		izquierdaLabel.setFont(new Font("Consolas",Font.BOLD,30));
		izquierdaLabel.setForeground(Color.BLUE);
		izquierda.add(izquierdaLabel);
		

		
		int ronda=0;					//CONTADOR DE RONDA (MULTIPLICADOR)
		
		Tablero Tablero = new Tablero(Consola.getTablero().length,Consola.getTablero()[0].length,Consola.getTablero());
				//CREA TABLERO, CONTIENE LA MATRIZ DE LA CLASE CONSOLA
		ventana.add(principal, BorderLayout.NORTH);			//A�ADIR ZONA PRINCIPAL A VENTANA
		ventana.setSize(Consola.getTablero()[0].length*200,Consola.getTablero().length*200+150);		//TAMA�O
		principal.add(Tablero, BorderLayout.CENTER);			//A�ADIR TABLERO A ZONA PRINCIPAL
		
		ventana.setVisible(true);			//VISIBILIDAD
		
		Timer revisor = new Timer ();			//TEMPORIZADOR
		TimerTask revisi�n = new TimerTask() {
			@Override
			public void run() {
				Tablero.evalua(Consola.getTablero());
			}
		};		//EVALUAR TABLERO	- PARA ACTUALIZAR IMAGEN PIEZAS -
		
		revisor.schedule(revisi�n,0,1);			//REPETIR TAREA CADA MIL�SIMA DE SEGUNDO.
		
		while (Consola.lleno() || Consola.posibleJugada()) {			//MIENTRAS SE PUEDA JUGAR
				
			ronda++; 		//ACTUALIZA RONDA
			
			int valor=Consola.nuevoValor();						//ANTICIPA SIGUIENTE VALOR
			next.setValor(valor);						//MODIFICA PIEZA GR�FICA
			
			Puntuaci�n.setText("Puntuaci�n: "+Consola.puntuaci�nModificada(ronda));			//MODIFICA ETIQUETA PUNTUACI�N
			
			
			Consola.imprimeTablero(ronda);				//IMPRIME TABLERO

			System.out.printf("Puntuaci�n: %.2f.   <----->   ",Consola.puntuaci�nModificada(ronda));			//IMPRIME PUNTUACI�N
			System.out.printf("Siguiente: %d.\n", valor);			//IMPRIME SIGUIENTE VALOR
			System.out.printf("Multiplicador de ronda: %.2f.\n", ronda*0.2);
			System.out.print("Jugada seleccionada (5 para ayuda): ");					//SOLICITA DIRECCI�N
			
			principal.toggleClick();		//CLICK TRUE
			while(principal.getClick()) {
				try {
					Thread.sleep(1);			
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//USUARIO HACE CLICK ==> CLICK FALSE
			}
			
			
			int [] a;			//CREA VECTOR PARA POSICI�N DE NUEVA PIEZA
			do {			//MIENTRAS
				a = Consola.nuevaPosici�n();			//CREA POSICI�N
			} while (Consola.getTablero()[a[0]][a[1]]!=0 && Consola.lleno());			//HASTA QUE NO EST� POBLADA
			if (Consola.lleno()) {
				Consola.setTablero(a[0],a[1],valor);			//CREA POSICI�N Y DA VALOR ANTICIPADO
			}
		}

		//FIN DE JUEGO
		finPartida.setVisible(false);			//VISIBILIDAD ETIQUETA
		Tablero.evalua(Consola.getTablero());			//EVALUA EL TABLERO UNA VEZ M�S
		Puntuaci�n.setText("Puntuaci�n: "+Consola.puntuaci�nModificada(ronda));
		Consola.imprimeTablero(ronda);			//IMPRIME TABLERO FINAL
		finPartida.setVisible(true);			//PANEL DE FIN DE PARTIDA VISIBLE
		System.out.println("</---FIN DE LA PARTIDA--->");			//AVISO
		String fin = "Puntuaci�n: "+Consola.puntuaci�nModificada(ronda)+"."; 		//CADENA
		System.out.printf(fin);			//OUTPUT DE PUNTUACI�N
		JOptionPane.showMessageDialog(ventana,fin);			//MENSAJE DE FIN
	}

}
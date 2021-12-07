import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Game extends JFrame{

	public static Node [][] matrix;
	protected static Player player = new Player();
	protected static Jimmy hunter = new Jimmy();
	
	public static void start (Console2 console) {
		
		
		Game window = new Game();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(new Dimension(Console2.getMap().length*30,Console2.getMap().length*30));
		window.setResizable(false);
		window.setLocationRelativeTo(null);
		JPanel main = new JPanel();
		main.setLayout(new GridLayout(33,33));

		matrix= new Node [33][33];
		for (int i=0;i<Console2.getMap().length;i++) {
			for (int j=0;j<Console2.getMap().length;j++) {
				matrix[i][j]=new Node (Console2.getMap()[i][j]);
				main.add(matrix[i][j]);
			}
		}
		
		main.setVisible(true);
		window.add(main);
		window.setVisible(true);

	}

	public static void evaluate (int [][] map) {
		for (int i=0;i<map.length;i++) {
			for (int j=0;j<map.length;j++) {
				matrix[i][j].setValue(map[i][j]);
			}
		}
	}


	public static void main(String[] args) {
		Console2 console = new Console2();
		Scanner kb = new Scanner (System.in);

		/*
		 * SUMMARY:
		 * 	0	-	Wall
		 * 	1	-	Hallway
		 * 	2	-	Room
		 * 	4	-	Player Spawn
		 * 
		 * 	8	-	Player
		 * 	9	-	Hunter
		 */
		
		start(console);
		Console2.add(player);
		Console2.add(hunter);
		
		System.out.println(console.toString());
		Timer check = new Timer ();			//TEMPORIZADOR
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				evaluate(Console2.getMap());
			}
		};		//EVALUAR TABLERO	- PARA ACTUALIZAR IMAGEN PIEZAS -

		check.schedule(task,0,1);
		while (true) {
			Console2.movement(kb.nextInt(),player);
		}
	}

}


import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Console {

	public final static int [][] fmap = new int [10][10];
	public static int [][] map = new int [10][10];
	
	/**
	 * Draws full map of hallways (0), rooms (1) and passages (2)
	 * @param map
	 */
	public static void start (int [][] map) {
		for (int i=0;i<map.length;i++) {
			for (int j=0;j<map[0].length;j++) {
				if ((i==0 && j==2) ||
						(i==1 && (j==2 || j>=5)) ||
						(i==2 && (j==5 || j==2)) ||
						(i==3 && j<=6) ||
						(i==4 && (j==6 || j==0)) ||
						(i==5 && (j>=4 && j<=7)) ||
						(i==6 && (j<=4 || j==7)) ||
						(i==7 && (j==4 || j>=7)) ||
						((i==8 || i==9) && (j==4 || j==7))) {
					map[i][j]=1;
				} else {
					if ((i==0 && (j==1 || j==5 || j==7 || j==9)) ||
							(i==1 && j==3) ||
							(i==2 && (j==0 || j==7 || j==9)) ||
							(i==4 && j==1) ||
							(i==5 && j==2) ||
							(i==6 && j==9) ||
							(i==7 && (j==0 || j==2 || j==5) ||
							(i==8 && (j==6 || j==9)) ||
							(i==9 && j==3))) {
						map[i][j]=2;
					} else {
						if ((i==4 && j==4) ||
							(i==5 && j==0)) {
							map[i][j]=3;
						} else {
							if (i==4 && j==7) {
								map[i][j]=4;
							} else {
								map [i][j]=0;
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Console representation of the map with and without entities
	 * @param map
	 * @param fmap
	 */
	public static void print (int [][] map, int [][] fmap) {
		for (int i=0;i<map.length;i++) {
			for (int j=0;j<map[0].length;j++) {
				System.out.printf("%2d",map[i][j]);
			}
			if (i==4 || i==5) {
				System.out.print("\t\t FULL MAP: \t");
				
			} else {
				System.out.print("\t\t\t\t");
			}
			for (int j=0;j<map[0].length;j++) {
				System.out.printf("%2d",fmap[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	/**
	 * Spawns both, player (8) in the starting point (4) and hunter in random branch (9)
	 * @param map
	 */
	public static void spawn (int [][] map) {
		map [4][7]=8;		//Player spawn
		//map [0][1]=9;
		
		switch (Dice.roll(7)) {			//Random hunter spawn
			//Spawns chosen in a clockwise direction starting from 0,0.
		case 1:
			map[0][2]=9;
			break;
		case 2:
			map[1][9]=9;
			break;
		case 3:
			map[7][9]=9;
			break;
		case 4:
			map[9][7]=9;
			break;
		case 5:
			map[9][4]=9;
			break;
		case 6:
			map[6][0]=9;
			break;
		case 7:
			map[4][0]=9;
			break;
		}
		
	}
	
	/**
	 * Finds target in the map
	 * @param target - target to be found
	 * @param map - map
	 * @return vector - position of target in map
	 */
	public static int [] find (int target, int [][] map) {
		int i=0, j=0;
		while (i<map.length && j<map.length && map[i][j]!=target) {
			while (j<map.length && map[i][j]!=target) {
				j++;
			}
			if (j>=map.length) {
				j=0;
			}
			if (map[i][j]!=target) {
				i++;
			}
		}
		if (i!=map.length || j!=map.length) {
			int [] pos = {i,j};
			return pos;
		} else {
			int [] pos = {map.length, map.length};
			return pos;
		}
	}
	
	/**
	 * Checks wether certain move in certain direction is possible or not
	 * @param d - Direction
	 * @param pos - Position
	 * @param map - Full map
	 * @param c - 8 or 9 -determine hunter or player-
	 * @return boolean - true if new position is not a wall
	 */
	public static boolean possibleMove (int d, int [] pos, int [][] map,int c) {
		if (c==8) {
			switch (d) {
			case 2:
				return (pos[0]+1<map.length && pos[1]<map.length && //Lower than max
						pos[0]+1>=0 && pos[1]>=0 &&					//Higher than min
						map[pos[0]+1][pos[1]]!=0);					//Not a wall
			case 4:
				return (pos[0]<map.length && pos[1]-1<map.length && 
						pos[0]>=0 && pos[1]-1>=0 &&					//Same
						map[pos[0]][pos[1]-1]!=0);
			case 8:
				return (pos[0]-1<map.length && pos[1]<map.length && 
						pos[0]-1>=0 && pos[1]>=0 &&					//Same
						map[pos[0]-1][pos[1]]!=0);
			case 6:
				return (pos[0]<map.length && pos[1]+1<map.length && 
						pos[0]>=0 && pos[1]+1>=0 &&
						map[pos[0]][pos[1]+1]!=0);
			}
		} else {
			switch (d) {
			case 2:
				return (pos[0]+1<map.length && pos[1]<map.length && //Lower than max
						pos[0]+1>=0 && pos[1]>=0 &&					//Higher than min
						fmap[pos[0]+1][pos[1]]!=3 &&					//Not a passage
						(map[pos[0]+1][pos[1]]!=8 ||				//If player inside
						fmap[pos[0]+1][pos[1]]==2) && 					//And it was a room
						map[pos[0]+1][pos[1]]!=0);					//Not a wall
			case 4:
				return (pos[0]<map.length && pos[1]-1<map.length && 
						pos[0]>=0 && pos[1]-1>=0 &&					//Same
						fmap[pos[0]][pos[1]-1]!=3 &&
						(map[pos[0]][pos[1]-1]!=8 ||
						fmap[pos[0]][pos[1]-1]==2) &&
						map[pos[0]][pos[1]-1]!=0);
			case 8:
				return (pos[0]-1<map.length && pos[1]<map.length && 
						pos[0]-1>=0 && pos[1]>=0 &&					//Same
						fmap[pos[0]-1][pos[1]]!=3 &&
						(map[pos[0]-1][pos[1]]!=8 ||
						fmap[pos[0]-1][pos[1]]==2) &&
						map[pos[0]-1][pos[1]]!=0);
			case 6:
				return (pos[0]<map.length && pos[1]+1<map.length && 
						pos[0]>=0 && pos[1]+1>=0 &&
						fmap[pos[0]][pos[1]+1]!=3 &&
						(map[pos[0]][pos[1]+1]!=8 ||
						fmap[pos[0]][pos[1]+1]==2) &&
						map[pos[0]][pos[1]+1]!=0);
			}
		}
		
		return false;
	}
	
	/**
	 * Moves player
	 * @param d - Direction
	 * @param map - Map
	 * @param fmap - Full map
	 */
	public static void movement (int d, int [][] map, int [][] fmap) {
		int [] pos = find (8,map);
		if (possibleMove(d,pos,map, 8)) {
			switch (d) {
			case 2:
				map[pos[0]][pos[1]]=fmap[pos[0]][pos[1]];		//Resets original position
				map[pos[0]+1][pos[1]]=8;		//Set new player position
				break;
			case 4:
				map[pos[0]][pos[1]]=fmap[pos[0]][pos[1]];
				map[pos[0]][pos[1]-1]=8;
				break;
			case 8:
				map[pos[0]][pos[1]]=fmap[pos[0]][pos[1]];
				map[pos[0]-1][pos[1]]=8;
				break;
			case 6:
				map[pos[0]][pos[1]]=fmap[pos[0]][pos[1]];
				map[pos[0]][pos[1]+1]=8;
				break;
			}
		}
	}
	
	/**
	 * Selects a random direction for the hunter
	 * @param map - Map
	 * @return int - Random direction
	 */
	public static int randomDirection (int [][]map) {
		int [] pos = find (9,map);
		int [] moves = {2,4,8,6};
		int d;
		do {
			d=moves[Dice.roll(4)-1];
		} while (!possibleMove(d,pos,map,9));
		return d;
	}
	
	/**
	 * Moves hunter
	 * @param d - direction
	 * @param map - Map
	 * @param fmap - Full map
	 */
	public static void movementHunter (int d, int [][] map, int [][] fmap) {
		int [] pos = find (9,map);
		switch (d) {
		case 2:
			map[pos[0]][pos[1]]=fmap[pos[0]][pos[1]];		//Resets original position
			map[pos[0]+1][pos[1]]=9;		//Set new hunter position
			break;
		case 4:
			map[pos[0]][pos[1]]=fmap[pos[0]][pos[1]];
			map[pos[0]][pos[1]-1]=9;
			break;
		case 8:
			map[pos[0]][pos[1]]=fmap[pos[0]][pos[1]];
			map[pos[0]-1][pos[1]]=9;
			break;
		case 6:
			map[pos[0]][pos[1]]=fmap[pos[0]][pos[1]];
			map[pos[0]][pos[1]+1]=9;
			break;
		}
		
	}
	
	/**
	 * Searches for player
	 * @param map - Map
	 * @return boolean - true if player is found
	 */
	public static boolean alive (int [][] map) {
		boolean alive=false;
		int i=0, j=0;
		while (i<map.length && j<map.length && map[i][j]!=8) {
			while (j<map.length && map[i][j]!=8) {
				j++;
			}
			if (j>=map.length) {
				j=0;
			}
			if (map[i][j]!=8) {
				i++;
			}
		}
		if (i!=map.length || j!=map.length) {
			alive = true;
		}
		return (alive);
	}
	
	/**
	 * Calculates distance between 2 points of map
	 * @param a - Point A
	 * @param b - Point B
	 * @return double - Distance
	 */
	public static double distance (int [] a, int [] b) {
		return Math.sqrt(Math.pow(a[0]-b[0], 2)+Math.pow(a[1]-b[1], 2));
	}
	
	/**
	 * Decides wether two entities are close or not (close if distance<Math.sqrt(50))
	 * @param a - Point A
	 * @param b - Point B
	 * @return boolean - true if close enough to be heard
	 */
	public static boolean close (int [] a, int [] b)  {
		return Math.sqrt(18)>distance (a,b);
	}
	
	/**
	 * True if two entities are close, 30% chance of being heard applied
	 * @param a - Point A
	 * @param b - Point B
	 * @return boolean - true if heard
	 */
	public static boolean heard (int [][] map) {
		int [] a = find (8,map);
		int [] b = find (9,map);
		return Dice.roll(3)==1 && close(a,b);
	}
	
	/**
	 * Checks wether between point A and point B -located among the same axis-
	 * have walls in between or not
	 * @param a	- Point A
	 * @param b - Point B
	 * @param map - Map
	 * @return boolean - true if there are no walls between A and B
	 */
	public static boolean noWalls (int [] a, int [] b, int [][] map) {
		int i,j;
		if (a[0]==b[0]) {		//Same x axis
			i=Math.min(a[1], b[1]);
			j=Math.max(a[1], b[1]);
			while (i<=j && map[a[0]][i]!=0 && map[a[0]][i]!=3) {
				i++;
			}
		}	else {				//Same y axis
			i=Math.min(a[0], b[0]);
			j=Math.max(a[0], b[0]);
			while (i<=j && map[i][a[1]]!=0 && map[i][a[1]]!=3) {
				i++;
			}
		}
		return (i>j);		//No walls found
	}
	
	/**
	 * Checks wether two entities are in line of sight or not
	 * @param a - Point A
	 * @param b - Point B
	 * @param map - Map
	 * @return boolean - true if they are in line of sight of each other
	 */
	public static boolean lineOfSight (int [][] map) {
		int [] a = find(8,map);
		int [] b = find(9,map);
		return ((a[0]==b[0] || a[1]==b[1]) && (noWalls(a, b, map)));
	}
	
	/**
	 * Activated when hunter has line of sight on player.
	 * The hunter will go to the player's location.
	 * @param map - Map
	 */
	public static void follow (int [][] map, int [][] fmap) {
		int [] player = find (8,map);
		int [] hunter = find (9,map);
		
		if (player[0]!=hunter[0]) {
			if (player[0]>hunter[0] && possibleMove(2,hunter,map,9)) {		//Y axis
				movementHunter(2,map,fmap);		//Player to the South
			} else {
				if (possibleMove(8,hunter,map,9)) {
					movementHunter(8,map,fmap);		//Player to the North
				} else {
					movementHunter(randomDirection(map),map,fmap);
				}
			}
		} else {
			if (player[1]>hunter[1] && possibleMove(6,hunter,map,9)) {		//X axis
				movementHunter(6,map,fmap);		//Player to the East
			} else {
				if (possibleMove(4,hunter,map,9)) {
					movementHunter(4,map,fmap);		//Player to the West
				} else {
					movementHunter(randomDirection(map),map,fmap);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner kb = new Scanner (System.in);
		/*
		 * SUMMARY:
		 * 	0	-	Wall
		 * 	1	-	Hallway
		 * 	2	-	Room
		 * 	3	- 	Passage
		 * 	4	-	Player Spawn
		 * 
		 * 	8	-	Player
		 * 	9	-	Hunter
		 */
		
		int round=0;
		start(fmap);
		start(map);
		Player player = new Player (4,7);
		Jimmy hunter = new Jimmy ();
		print(map,fmap);
		while (alive(map)) {
			round++;
			movement(kb.nextInt(),map,fmap);
			print(map,fmap);
			if (round>=5) {
				if (lineOfSight(map)) {
					follow(map,fmap);
				} else {
					movementHunter(randomDirection(map),map,fmap);
				}
			}
			print(map,fmap);
		}
		print (map,fmap);
	}

}

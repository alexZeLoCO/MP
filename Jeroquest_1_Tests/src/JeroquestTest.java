/**
 * Programming Methodology Practice.
 * Jeroquest - An example of Object Oriented Programming.
 * Main program
 * @author Jorge Puente Peinador y Ramiro Varela Arias
 * @author Juan Luis Mateo Cerdán
 * @version 1
 *
 */

public class JeroquestTest {

	public static void main(String[] args) {

		Mummy ramses = new Mummy("Ramses");
		Barbarian conan = new Barbarian("Conan2");

		System.out.println(ramses);
		System.out.println(conan);

		// Conan attacks Ramses
		int impacts = conan.attack();
		ramses.defend(impacts);

		// if Ramses is alive attacks Conan
		if (ramses.isAlive())
			conan.defend(ramses.attack());

		System.out.println(ramses);
		System.out.println(conan);

		//C)	
		System.out.println("\n C)");

		Mummy A = new Mummy ("A");
		Barbarian B = new Barbarian ("B");

		int a=0;
		while (a<10 && B.isAlive()) {
			B.defend(A.attack());
			a++;
		}
		if (B.isAlive()) {
			System.out.print(B.toString() + " has survived!");
		} else {
			System.out.print(B.toString() + " held for " + a + " rounds.");
		}

		//D)	
		System.out.println("\n D)");

		Barbarian [] barbarians = new Barbarian [10];
		Mummy [] mummies = new Mummy [10];
		for (int i=0;i<barbarians.length;i++) {
			barbarians[i]=new Barbarian (String.format("B%d",i));
			mummies[i]=new Mummy (String.format("M%d", i));
		}

		int rounds = 10;
		a=0;
		int idx=0;
		while(a<rounds && anyAlive(barbarians) && anyAlive(mummies)) {
			mummies[Dice.roll(mummies.length)-1].defend(barbarians[idx].attack());
			barbarians[Dice.roll(barbarians.length)-1].defend(mummies[idx].attack());
			a++;
			idx++;
			if (idx>=barbarians.length) {
				idx=idx-barbarians.length;
			}

		}
		if (a==rounds) {
			System.out.print("Draw");
		} else {
			if (!anyAlive(barbarians)) {
				System.out.print("Mummies win");
			} else {
				System.out.print("Barbarians win");
			}
		}

		//E)	
		System.out.println("\n E)");

		Barbarian [] barbarians2 = new Barbarian [10];
		Mummy [] mummies2 = new Mummy [10];		

		for (int i=0;i<barbarians2.length;i++) {
			barbarians2[i]=new Barbarian (String.format("B1%d",i));
			mummies2[i]=new Mummy (String.format("M1%d", i));
		}

		rounds = 10;
		a=0;
		idx=0;
		while(a<rounds && anyAlive(barbarians2) && anyAlive(mummies2)) {
			mummies2[highestH(mummies2)].defend(barbarians2[idx].attack());
			barbarians2[highestH(barbarians2)].defend(mummies2[idx].attack());
			a++;
			idx++;
			if (idx>=barbarians2.length) {
				idx=idx-barbarians2.length;
			}

		}
		if (a==rounds) {
			System.out.print("Draw");
		} else {
			if (!anyAlive(barbarians2)) {
				System.out.print("Mummies win");
			} else {
				System.out.print("Barbarians win");
			}
		}

		//F)
		System.out.println("\n F)");

		Barbarian [] barbariansT = new Barbarian [20];
		Mummy [] mummiesT = new Mummy [20];

		for (int i=0;i<barbariansT.length;i++) {
			if (i<10) {
				barbariansT[i]=barbarians[i];
			} else {
				barbariansT[i]=barbarians2[i-10];
			}
		}

		for (int i=0;i<mummiesT.length;i++) {
			if (i<10) {
				mummiesT[i]=mummies[i];
			} else {
				mummiesT[i]=mummies2[i-10];
			}
		}

		Barbarian barbarianMemory = new Barbarian("Memory");
		for (int i=0;i<barbariansT.length;i++) {
			for (int j=0;j<barbariansT.length;j++) {
				double salj=barbariansT[j].getBody();
				double sali=barbariansT[i].getBody();
				if (salj<sali) {
					barbarianMemory = barbariansT[i];
					barbariansT[i]=barbariansT[j];
					barbariansT[j]=barbarianMemory;
				}
			}
		}

		Mummy mummyMemory = new Mummy("Memory");
		for (int i=0;i<mummiesT.length;i++) {
			for (int j=0;j<mummiesT.length;j++) {
				double salj=mummiesT[j].getBody();
				double sali=mummiesT[i].getBody();
				if (salj<sali) {
					mummyMemory = mummiesT[i];
					mummiesT[i]=mummiesT[j];
					mummiesT[j]=mummyMemory;
				}
			}
		}
		
		int i=0;
		int j=0;

		while (i<barbariansT.length && j<mummiesT.length) {
			if (barbariansT[i].getBody()>mummiesT[j].getBody()) {
				System.out.println(barbariansT[i++]);
			} else {
				System.out.println(mummiesT[j++]);
			}
		}

		while (i<barbariansT.length) {
			System.out.println(barbariansT[i++]);
		}
		while (j<mummiesT.length) {
			System.out.println(mummiesT[j++]);
		}
	}

	/**
	 * Returns highest Body() index from Characters vector
	 * @param a - Characters vector
	 * @return int - position of highest Body() in vector
	 */
	public static int highestH (Character [] a) {
		int maxIdx=0;
		for (int i=1;i<a.length;i++) {
			if (a[maxIdx].getBody()<a[i].getBody()) {
				maxIdx=i;
			}
		}
		return maxIdx;
	}

	/**
	 * Checks if there is anybody alive in vector
	 * @param a - Vector
	 * @return boolean - true if there is someone alive
	 */
	public static boolean anyAlive(Character [] a) {
		int i=0;
		while (i<a.length && !a[i].isAlive()) {
			i++;
		}
		return i!=a.length;
	}
}

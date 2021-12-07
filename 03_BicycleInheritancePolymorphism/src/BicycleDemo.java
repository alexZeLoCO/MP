/**
 * Simple example using polymorphism and downcasting
 * 
 */

class BicycleDemo {
	public static void main(String[] args){
	    Bicycle bike01, bike02, bike03, bike04;
	    
	    bike01 = new Bicycle(20, 10, 1);
		bike02 = new MountainBike(20, 10, 5, "Dual");
		bike03 = new RoadBike(40, 20, 8, 23);
        
		// JVM calls the appropriate method for the referenced object
		// and not the method for the variable type
		//bike01.printStates();
		//bike02.printStates();
		//bike03.printStates();
		
		bike04 = new RoadBike (30,30,7,20);
		
		Bicycle [] vectBikes = {bike01, bike02, bike03, bike04};
		
		for (int i=0;i<vectBikes.length;i++) {
			if (vectBikes[i] instanceof MountainBike) {
				System.out.printf("This Mountain Bike has a suspension of: %s. \n",((MountainBike) vectBikes[i]).getSuspension());
			} else {
				if (vectBikes[i] instanceof RoadBike) {
					System.out.printf("This Road Bike has a tire width of: %s. \n",((RoadBike) vectBikes[i]).getTireWidth());
				} else {
					vectBikes[i].printStates();
				}
			}
			System.out.println();
		}
		
		/*
		for (int i = 0; i < vectBikes.length; i++){
			System.out.format("%nBycicle [%d]:%n",i);
			vectBikes[i].printStates();
		}
		
		// To use the specific methods for each class
		// it is necessary to do downcast
		
		//MountainBike m2 = (MountainBike) bike02;
		//m2.setSuspension("Mono");
		((MountainBike)bike02).setSuspension("Mono");
		
		for (int i = 0; i < vectBikes.length; i++){
			System.out.format("%nBycicle [%d]:%n",i);
			vectBikes[i].printStates();
		}
		*/
	}
}




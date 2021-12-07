// Fig. 10.1: PolymorphismTest.java
// Assigning superclass and subclass references to superclass and
// subclass variables.

public class PolymorphismMain  
{
   public static void main( String[] args ) 
   {
      // assign superclass reference to superclass variable
      CommissionEmployee commissionEmployee = new CommissionEmployee( 
         "Sue", "Jones", "222-22-2222", 10000, .06 );                    

      // assign subclass reference to subclass variable
      BasePlusCommissionEmployee basePlusCommissionEmployee = 
         new BasePlusCommissionEmployee( 
         "Bob", "Lewis", "333-33-3333", 5000, .04, 300 );         

      // invoke toString on superclass object using superclass variable
      System.out.printf( "%s %s:\n\n%s\n\n", 
         "Call CommissionEmployee's toString with superclass reference ",
         "to superclass object", commissionEmployee.toString() );

      // invoke toString on subclass object using subclass variable      
      System.out.printf( "%s %s:\n\n%s\n\n", 
         "Call BasePlusCommissionEmployee's toString with subclass",
         "reference to subclass object", 
         basePlusCommissionEmployee.toString() );

      // POLIMORFISM: invoke toString on subclass object using superclass variable
      CommissionEmployee commissionEmployee2 = 
         basePlusCommissionEmployee; 
      System.out.printf( "%s %s:\n\n%s\n", 
         "Call BasePlusCommissionEmployee's toString with superclass",
         "reference to subclass object", commissionEmployee2.toString() );
      
      
      System.out.println("\n");
      //2.1
      //2.1.1
      //b)
      
      CommissionEmployee E1 = new CommissionEmployee ("Neil","Armstrong","558-55421-22-14",15000,.7);
      CommissionEmployee E2 = new CommissionEmployee ("Marie","Curie","557-12345-24-70",20000,.9);
      BasePlusCommissionEmployee E3 = new BasePlusCommissionEmployee ("Issac","Newton","557-55420-21-13",10000,.5,5000);
      BasePlusCommissionEmployee E4 = new BasePlusCommissionEmployee ("Napoleon","Bonaparte","559-55422-24-27",15100,.7,3500);
      CommissionEmployee [] vector = {E1,E2,E3,E4};
      
      System.out.println("The employee that earns the most is: " + earnsTheMost(vector).toString());
      
      System.out.println("\n");
      //c)
      sortAscending(vector);
      for (int i=0;i<vector.length;i++) {
    	  System.out.println(vector[i].toString());
      }
      
      System.out.println("\n");
      //d)
      /*
       * Con el polimorfismo, no sólo nos ahorramos uno de los vectores -ya que ahora podemos usar el mismo para ambos tipos
       * de empleados-; sino que además el algoritmo para reordenar el vector es más corto y simple.
       */
   } // end main
   
   public static CommissionEmployee earnsTheMost(CommissionEmployee [] v) {
		double maxSalary = v[0].earnings();
		int idx=0;
		for (int i=1;i<v.length;i++) {
			if (maxSalary<v[i].earnings()) {
				maxSalary=v[i].earnings();
				idx=i;
			}
		}
		return v[idx];
   }
   
   public static void sortAscending(CommissionEmployee [] v) {
	   CommissionEmployee memory = new CommissionEmployee ("Commission","Memory","Employee",0,0);
	   for (int i=0;i<v.length-1;i++) {
		   int idx=i;
		   while (idx>=0 && v[idx].earnings()>v[idx+1].earnings()) {
			   memory=v[idx];
			   v[idx]=v[idx+1];
			   v[idx+1]=memory;
			   idx--;
		   }
	   }
   }
   
} // end class PolymorphismTest


/**************************************************************************
 * (C) Copyright 1992-2010 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/

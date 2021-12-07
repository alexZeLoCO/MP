import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;

public class perrosPeligrososTest1 {

    public static void main(String[] args) {
        Scanner in = null;
        String fileName = "censoPerrosPeligrosos.txt";

        int[] cantidades = { 0, 0, 0 };

        String raza;
        String tama�o;
        int cp;
        String barrio;
        int n;

        try {
            in = new Scanner(new FileInputStream(fileName));
            System.out.printf("%-33s%-10s%-7s%-25s%-3s\n", "RAZA", "TAMA�O", "CP", "BARRIO", "CANTIDAD");
            while (in.hasNext()) {
                raza = in.next();
                tama�o = in.next();
                cp = in.nextInt();
                barrio = in.next();
                n = in.nextInt();

                if ((int) cp / 1000 != 33) {
                    throw new Exception("Error: El CP " + cp + " no es de Gij�n.\n");
                }

                if (tama�o.equals("Grande")) {
                    cantidades[2] = cantidades[2] + n;
                } else if (tama�o.equals("Mediano")) {
                    cantidades[1] = cantidades[1] + n;
                } else {
                    cantidades[0] = cantidades[0] + n;
                }

                System.out.printf("%-33s%-10s%-7s%-25s%-3s\n", raza, tama�o, cp, barrio, n);
            }

            System.out.printf("\n%-10s%-10s%-10s\n", "PEQUE�OS", "MEDIANOS", "GRANDES");
            for (int cantidad : cantidades) {
                System.out.printf("%-10d", cantidad);
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            if (in != null)
                in.close();
        }
    }

}

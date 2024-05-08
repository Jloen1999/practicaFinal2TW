import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class RealcoinWays {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        double cantidad = Double.parseDouble(br.readLine());
        int waysCount = countWays(cantidad);
        System.out.println(waysCount);
    }

    public static int countWays(double cantidad) {
        int P = (int) (cantidad * 100); // // Convertir la cantidad a céntimos
        System.out.println("P: " + P);
        int[] c = {5, 10, 20, 50, 100, 200}; // tipos de cambio disponibles
        int[] D = new int[P + 1]; // Array para guardar las soluciones parciales de los subproblemas (cantidad de formas de devolver una cantidad)
        // Mostrar soluciones parciales iniciales
        D[0] = 1; // Caso base, si la cantidad es 0 centimos(no tomar ninguna moneda)

        for (int k = 0; k < c.length; k++) {
            for (int j = c[k]; j <= P; j++) {
                System.out.println("j: " + j + " c[k]: " + c[k]);
                System.out.println(D[j] + "-" + D[j - c[k]]);
                D[j] += D[j - c[k]];
                System.out.println(Arrays.toString(D));
            }
        }

        return D[P];
    }
}

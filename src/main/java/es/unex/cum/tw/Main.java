package es.unex.cum.tw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        double cantidad = Double.parseDouble(br.readLine());
        int numeroDevoluciones = devolverCantidadDeDevoluciones(cantidad);
        System.out.println(numeroDevoluciones);
    }

    public static int devolverCantidadDeDevoluciones(double cantidad) {
        int P = (int) (cantidad * 100); // Convertir la cantidad a céntimos
        int[] c = {5, 10, 20, 50, 100, 200}; // Tipos de cambios de monedas
        int[][] D = new int[c.length][P + 1]; // Definir la tabla de sub-problemas

        // Caso base: una sola forma de devolver 0 céntimos (no tomar ninguna moneda)
        for (int i = 0; i < c.length; i++) {
            D[i][0] = 1;
        }

        // Mostrar matriz inicial
        System.out.println("Matriz inicial:");
        mostrarMatriz(c, P, D);

        // Calcular el número de formas de devolver cada cantidad de céntimos
        for (int i = 0; i < c.length; i++) {
            for (int j = 1; j <= P; j++) {
                // Incluir formas de devolver sin usar la moneda actual
                int sinIncluirMoneda = (i > 0) ? D[i - 1][j] : 0;
                // Incluir formas de devolver usando la moneda actual
                int incluirMoneda = (j >= c[i]) ? D[i][j - c[i]] : 0;
                // Total de formas es la suma de ambas opciones
                D[i][j] = sinIncluirMoneda + incluirMoneda;
            }
        }

        // Mostrar matriz final
        System.out.println("Matriz final:");
        mostrarMatriz(c, P, D);

        // La solución está en D[c.length - 1][P]
        return D[c.length - 1][P];
    }

    private static void mostrarMatriz(int[] c, int P, int[][] D) {
        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j <= P; j++) {
                System.out.print(D[i][j] + " ");
            }
            System.out.println();
        }
    }
}

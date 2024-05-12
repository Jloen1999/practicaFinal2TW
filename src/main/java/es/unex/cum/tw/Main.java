package es.unex.cum.tw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Clase que dado una cantidad de céntimos, devuelve el número de formas de devolver dicha cantidad
 * @author Jose Luis Obiang Ela Nanguang
 * @version 1.0
 **/
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        double cantidad = Double.parseDouble(br.readLine()); // Introducir cantidad de centimos
        int numeroDevoluciones = devolverCantidadDeDevoluciones(cantidad); // Obtener formas de devolver dicha cantidad
        System.out.println(numeroDevoluciones);
    }

    public static int devolverCantidadDeDevoluciones(double cantidad) {
        int P = (int) (cantidad * 100); // Convertir la cantidad a céntimos
        int[] c = {5, 10, 20, 50, 100, 200}; // Tipos de cambios de monedas
        int[][] D = new int[c.length][P + 1]; // Definir la tabla/matriz de sub-problemas

        // Caso base: Almacenar en la primera columna de la matriz un 1
        for (int i = 0; i < c.length; i++) {
            D[i][0] = 1;
        }

        //Mostrar matriz inicial
            System.out.println("Matriz inicial:");
            mostrarMatriz(c, P, D);

        for (int i = 0; i < c.length; i++) { // Seleccionar tipo de moneda de cambio desde la cantidad más pequeña hasta la mayor
            for (int j = 1; j <= P; j++) { // Avanzar en la fila hasta la cantidad introducida
                int cantidadAnterior = cogerCantidadAnterior(D, i, j); // Obtener cantidad anterior
                int cantidadActual = cogerCantidadActual(D, c, i, j); // Obtener cantidad actual
                D[i][j] = cantidadAnterior + cantidadActual; // Sumar cantidad anterior con la cantidad actual / Recomponer solución
            }
        }


         //Mostrar matriz final
            System.out.println("Matriz final:");
            mostrarMatriz(c, P, D);

        // Devolver la solución en la última posición de la tabla/matriz
        return D[c.length - 1][P];
    }

    /**
     * Obtiene la cantidad actual
     * @param D Matriz de sub-problemas
     * @param c Tipos de monedas
     * @param i Índice de la moneda
     * @param j Índice de la cantidad actual
     * @return Cantidad actual
     */
    private static int cogerCantidadActual(int[][] D, int[] c, int i, int j) {
        if(j >= c[i]){
            return D[i][j - c[i]];
        }

        return 0;
    }

    /**
     * Obtiene la cantidad anterior
     * @param D Matriz de sub-problemas
     * @param i Índice de la moneda
     * @param j Índice de la cantidad actual
     * @return Cantidad anterior
     */
    private static int cogerCantidadAnterior(int[][] D, int i, int j) {
        if (i > 0) {
            return D[i - 1][j];
        }

        return 0;
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

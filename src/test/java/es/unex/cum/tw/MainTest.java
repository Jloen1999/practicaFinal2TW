/*
package es.unex.cum.tw;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {

    private int[] vector1;
    private int[] vector2;
    private int[] vector3;
    private int[] vector4;
    private int[] vector5;
    private int[] vector6;
    private int[] vector7;
    private int[] vector8;
    @BeforeEach
    void setUp() {
        vector1 = new int[]{4, 3, 2, 1};
        vector2 = new int[]{8, 7, 6, 5, 4, 1, 2, 3};
        vector3 = new int[]{7, 6, 5, 4, 3, 2, 1};
        vector4 = new int[]{1, 2, 3, 4};
        vector5 = new int[]{5, 4, 4, 3, 3};
        vector6 = new int[]{5};
        // Almacenar 10000 platos en el vector
        vector7 = new int[10000];
        for (int i = 0; i < vector7.length; i++) {
            vector7[i] = i + 1;
        }

        vector8 = new int[]{4, 2, 7, 1, 8, 3, 5, 6};

    }

    @AfterEach
    void tearDown() {
        vector1 = null;
        vector2 = null;
        vector3 = null;
        vector4 = null;
        vector5 = null;
        vector6 = null;
        vector7 = null;
        vector8 = null;
    }

    */
/**
     * Montón de platos ya ordenados de forma descendente.
     *//*

    @Test
    public void casoBasico() {
        int movimientos = Main.getMovimientos(vector1);
        assertEquals(0, movimientos);
    }

    */
/**
     * Montón de platos que necesita un solo movimiento para ordenarse.
     *//*

    @Test
    public void casoUnSoloMovimiento() {
        int movimientos = Main.getMovimientos(vector2);
        assertEquals(1, movimientos);
    }

    */
/**
     * Montón de platos que necesita varios movimientos para ordenarse.
     *//*

    @Test
    public void casoConVariosMovimientos() {
        int movimientos = Main.getMovimientos(vector3);
        assertEquals(0, movimientos);
    }

    */
/**
     * Montón de platos ya ordenados de forma invertida.
     *//*

    @Test
    public void casoMontonPlatosInvertidos() {
        int movimientos = Main.getMovimientos(vector4);
        assertEquals(1, movimientos);
    }

    */
/**
     * Montón de platos con platos repetidos.
     *//*

    @Test
    public void casoConPlatosRepetidos() {
        int movimientos = Main.getMovimientos(vector5);
        assertEquals(0, movimientos);
    }

    */
/**
     * Montón de platos con el menor número de platos posible.
     *//*

    @Test
    public void casoConMenorNumeroPlatos() {
        int movimientos = Main.getMovimientos(vector6);
        assertEquals(0, movimientos);
    }

    */
/**
     * Montón de platos con el maximo número de platos posible.
     *//*

    @Test
    public void casoConMaximoNumeroPlatos() {
        int movimientos = Main.getMovimientos(vector7);
        assertEquals(1, movimientos);
    }

    */
/**
     * Montón de platos con platos ordenados de forma aleatoria.
     *//*

    @Test
    public void casoConPlatosOrdenAleatorio() {
        int movimientos = Main.getMovimientos(vector8);
        assertEquals(2, movimientos);
    }
}*/

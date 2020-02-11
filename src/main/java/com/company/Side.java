package com.company;

import java.util.Arrays;

public class Side {
    int[] squares;
    int[][] matrSquares;
    int size;
    Side(int size) {
        squares = new int[size * size];
        matrSquares = new int[size][size];
        this.size = size;
    }

    void solveTheSide(int color) {
        Arrays.fill(squares, color);
        for (int i = 0; i < size; i++)
            System.arraycopy(squares, i * size, matrSquares[i], 0, size);
    }

    void showTheSide() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(matrSquares[i][j] + " ");
            }
            System.out.print('\n');
        }
    }
}

package com.company;

public class Side {
    int[][] squares;
    int size;
    Side(int size) {
        squares = new int[size][size];
        this.size = size;
    }

    void solveTheSide(int color) {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                squares[i][j] = color;
    }

    void showTheSide() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(squares[i][j] + " ");
            }
            System.out.print('\n');
        }
    }
}

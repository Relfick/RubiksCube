package com.company;

public class Side {
    int[] squares;
    Side() {
        squares = new int[9];
    }
    void solveTheSide(int color) {
        for (int i = 0; i < 9; i++)
            squares[i] = color;
    }

    void showTheSide() {
        System.out.print(squares[0] + " ");
        System.out.print(squares[1] + " ");
        System.out.print(squares[2] + " \n");
        System.out.print(squares[7] + " ");
        System.out.print(squares[8] + " ");
        System.out.print(squares[3] + " \n");
        System.out.print(squares[6] + " ");
        System.out.print(squares[5] + " ");
        System.out.print(squares[4] + " \n");
    }
}

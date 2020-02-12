package com.company;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Cube cube = new Cube(3);

//        cube.ChangeTheSquare(0, 0, 0, 7);
//        cube.ChangeTheSquare(0, 1, 1, 7);
//        cube.ChangeTheSquare(2, 0, 0, 8);
//        cube.ChangeTheSquare(2, 1, 1, 8);

        for (int i = 0; i < 6; i++) {
            cube.TurnTheSide(1, 2, 0);
            cube.TurnTheSide(2, 2, 0);
            cube.TurnTheSide(1, 2, 1);
            cube.TurnTheSide(2, 2, 1);
        }
        cube.showTheStatus();
    }
}

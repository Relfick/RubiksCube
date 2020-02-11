package com.company;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Cube cube = new Cube(3);
        cube.ChangeTheSquare(4, 0, 0, 7);
        cube.ChangeTheSquare(4, 1, 1, 7);
        cube.ChangeTheSquare(5, 0, 0, 8);
        cube.ChangeTheSquare(5, 1, 1, 8);
        cube.turnCubeToTheLeft();
        cube.turnCubeToTheLeft();
        cube.turnCubeToTheLeft();
        cube.showTheStatus();
    }
}

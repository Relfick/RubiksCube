package com.company;

public class Main {

    public static void main(String[] args) {
        Cube cube = new Cube(3);

        cube.TurnSides(1, 2, 0);
        cube.TurnSides(2, 2, 0);
        cube.TurnSides(1, 2, 1);
        cube.TurnSides(1, 2, 1);
        cube.ShowTheStatus();
    }
}

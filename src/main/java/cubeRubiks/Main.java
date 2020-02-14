package cubeRubiks;

public class Main {

    public static void main(String[] args) {
        Cube cube = new Cube(3);

        cube.turnSides(1, 2, 0);
        cube.turnSides(2, 2, 0);
        cube.turnSides(1, 2, 1);
        cube.turnSides(1, 2, 1);
        cube.showTheStatus();
    }
}

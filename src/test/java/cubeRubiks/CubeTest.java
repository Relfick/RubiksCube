package cubeRubiks;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class CubeTest {
    Cube cube;
    int[][] expectedSide;

    //Sides: 0 - front, 1 - right, 2 - back, 3 - left, 4 - top, 5 - bottom
    @Test
    void getTheSide() {
        cube = new Cube(3);
        for (int i = 0; i < 6; i++) {
            expectedSide = new int[][] {{i, i, i}, {i, i, i}, {i, i, i}};
            assertTrue(Arrays.deepEquals(expectedSide, cube.getTheSide(i)));
        }
    }

    // direction: 0-Up, 1-Down, 2-Left, 3-Right, 4-clockwise, 5-counterclockwise
    @Test
    void turnTheCube() {
        cube = new Cube(3);
        cube.turnTheCube(3); // turn right
        assertTrue(Arrays.deepEquals(new int[][] {{3, 3, 3}, {3, 3, 3}, {3, 3, 3}}, cube.getTheSide(0)));
        assertTrue(Arrays.deepEquals(new int[][] {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}, cube.getTheSide(1)));
        assertTrue(Arrays.deepEquals(new int[][] {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}}, cube.getTheSide(2)));
        assertTrue(Arrays.deepEquals(new int[][] {{2, 2, 2}, {2, 2, 2}, {2, 2, 2}}, cube.getTheSide(3)));
        assertTrue(Arrays.deepEquals(new int[][] {{4, 4, 4}, {4, 4, 4}, {4, 4, 4}}, cube.getTheSide(4)));
        assertTrue(Arrays.deepEquals(new int[][] {{5, 5, 5}, {5, 5, 5}, {5, 5, 5}}, cube.getTheSide(5)));

        cube.turnTheCube(2); // turn left
        for (int i = 0; i < 6; i++) {
            expectedSide = new int[][] {{i, i, i}, {i, i, i}, {i, i, i}};
            assertTrue(Arrays.deepEquals(expectedSide, cube.getTheSide(i)));
        }

        cube.turnTheCube(0); // turn up
        assertTrue(Arrays.deepEquals(new int[][] {{5, 5, 5}, {5, 5, 5}, {5, 5, 5}}, cube.getTheSide(0)));
        assertTrue(Arrays.deepEquals(new int[][] {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}}, cube.getTheSide(1)));
        assertTrue(Arrays.deepEquals(new int[][] {{4, 4, 4}, {4, 4, 4}, {4, 4, 4}}, cube.getTheSide(2)));
        assertTrue(Arrays.deepEquals(new int[][] {{3, 3, 3}, {3, 3, 3}, {3, 3, 3}}, cube.getTheSide(3)));
        assertTrue(Arrays.deepEquals(new int[][] {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}, cube.getTheSide(4)));
        assertTrue(Arrays.deepEquals(new int[][] {{2, 2, 2}, {2, 2, 2}, {2, 2, 2}}, cube.getTheSide(5)));

        cube.turnTheCube(1); // turn down
        for (int i = 0; i < 6; i++) {
            expectedSide = new int[][] {{i, i, i}, {i, i, i}, {i, i, i}};
            assertTrue(Arrays.deepEquals(expectedSide, cube.getTheSide(i)));
        }

        cube.turnTheCube(4); // turn clockwise
        assertTrue(Arrays.deepEquals(new int[][] {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}, cube.getTheSide(0)));
        assertTrue(Arrays.deepEquals(new int[][] {{4, 4, 4}, {4, 4, 4}, {4, 4, 4}}, cube.getTheSide(1)));
        assertTrue(Arrays.deepEquals(new int[][] {{2, 2, 2}, {2, 2, 2}, {2, 2, 2}}, cube.getTheSide(2)));
        assertTrue(Arrays.deepEquals(new int[][] {{5, 5, 5}, {5, 5, 5}, {5, 5, 5}}, cube.getTheSide(3)));
        assertTrue(Arrays.deepEquals(new int[][] {{3, 3, 3}, {3, 3, 3}, {3, 3, 3}}, cube.getTheSide(4)));
        assertTrue(Arrays.deepEquals(new int[][] {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}}, cube.getTheSide(5)));

        cube.turnTheCube(5); // turn counterclockwise
        for (int i = 0; i < 6; i++) {
            expectedSide = new int[][] {{i, i, i}, {i, i, i}, {i, i, i}};
            assertTrue(Arrays.deepEquals(expectedSide, cube.getTheSide(i)));
        }
    }

    // axis: y-0, x-1, z-2 (origin - back side, the bottom left square; y - front, to you; x - right; z - up)
    // layer: 0 - (size-1), count from origin
    // direction: 0 - clockwise, 1 - counterclockwise; (view against the axis)
    @Test
    void turnSides() {
        cube = new Cube(3);

        // Classic algorithm returns cube to the basics after 6 times
        for (int i = 0; i < 6; i++) {
            cube.turnSides(1, 2, 0);
            cube.turnSides(2, 2, 0);
            cube.turnSides(1, 2, 1);
            cube.turnSides(2, 2, 1);
        }

        for (int i = 0; i < 6; i++) {
            expectedSide = new int[][] {{i, i, i}, {i, i, i}, {i, i, i}};
            assertTrue(Arrays.deepEquals(expectedSide, cube.getTheSide(i)));
        }

        cube.turnSides(0, new int[] {1, 2}, new int[] {1, 0}); // front clockwise and central counterclockwise
        assertTrue(Arrays.deepEquals(new int[][] {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}, cube.getTheSide(0)));
        assertTrue(Arrays.deepEquals(new int[][] {{4, 5, 1}, {4, 5, 1}, {4, 5, 1}}, cube.getTheSide(1)));
        assertTrue(Arrays.deepEquals(new int[][] {{2, 2, 2}, {2, 2, 2}, {2, 2, 2}}, cube.getTheSide(2)));
        assertTrue(Arrays.deepEquals(new int[][] {{3, 4, 5}, {3, 4, 5}, {3, 4, 5}}, cube.getTheSide(3)));
        assertTrue(Arrays.deepEquals(new int[][] {{4, 4, 4}, {1, 1, 1}, {3, 3, 3}}, cube.getTheSide(4)));
        assertTrue(Arrays.deepEquals(new int[][] {{1, 1, 1}, {3, 3, 3}, {5, 5, 5}}, cube.getTheSide(5)));

        cube.turnSides(0, new int[] {1, 2}, new int[] {0, 1}); // front counterclockwise and central clockwise
        for (int i = 0; i < 6; i++) {
            expectedSide = new int[][] {{i, i, i}, {i, i, i}, {i, i, i}};
            assertTrue(Arrays.deepEquals(expectedSide, cube.getTheSide(i)));
        }
    }
}
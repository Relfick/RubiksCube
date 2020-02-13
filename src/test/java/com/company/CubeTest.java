package com.company;

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
            assertTrue(Arrays.deepEquals(expectedSide, cube.GetTheSide(i)));
        }
    }

    // direction: 0-Up, 1-Down, 2-Left, 3-Right, 4-clockwise, 5-counterclockwise
    @Test
    void turnTheCube() {
        cube = new Cube(3);
        cube.TurnTheCube(3); // turn right
        assertTrue(Arrays.deepEquals(new int[][] {{3, 3, 3}, {3, 3, 3}, {3, 3, 3}}, cube.GetTheSide(0)));
        assertTrue(Arrays.deepEquals(new int[][] {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}, cube.GetTheSide(1)));
        assertTrue(Arrays.deepEquals(new int[][] {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}}, cube.GetTheSide(2)));
        assertTrue(Arrays.deepEquals(new int[][] {{2, 2, 2}, {2, 2, 2}, {2, 2, 2}}, cube.GetTheSide(3)));
        assertTrue(Arrays.deepEquals(new int[][] {{4, 4, 4}, {4, 4, 4}, {4, 4, 4}}, cube.GetTheSide(4)));
        assertTrue(Arrays.deepEquals(new int[][] {{5, 5, 5}, {5, 5, 5}, {5, 5, 5}}, cube.GetTheSide(5)));

        cube.TurnTheCube(2); // turn left
        for (int i = 0; i < 6; i++) {
            expectedSide = new int[][] {{i, i, i}, {i, i, i}, {i, i, i}};
            assertTrue(Arrays.deepEquals(expectedSide, cube.GetTheSide(i)));
        }

        cube.TurnTheCube(0); // turn up
        assertTrue(Arrays.deepEquals(new int[][] {{5, 5, 5}, {5, 5, 5}, {5, 5, 5}}, cube.GetTheSide(0)));
        assertTrue(Arrays.deepEquals(new int[][] {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}}, cube.GetTheSide(1)));
        assertTrue(Arrays.deepEquals(new int[][] {{4, 4, 4}, {4, 4, 4}, {4, 4, 4}}, cube.GetTheSide(2)));
        assertTrue(Arrays.deepEquals(new int[][] {{3, 3, 3}, {3, 3, 3}, {3, 3, 3}}, cube.GetTheSide(3)));
        assertTrue(Arrays.deepEquals(new int[][] {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}, cube.GetTheSide(4)));
        assertTrue(Arrays.deepEquals(new int[][] {{2, 2, 2}, {2, 2, 2}, {2, 2, 2}}, cube.GetTheSide(5)));

        cube.TurnTheCube(1); // turn down
        for (int i = 0; i < 6; i++) {
            expectedSide = new int[][] {{i, i, i}, {i, i, i}, {i, i, i}};
            assertTrue(Arrays.deepEquals(expectedSide, cube.GetTheSide(i)));
        }

        cube.TurnTheCube(4); // turn clockwise
        assertTrue(Arrays.deepEquals(new int[][] {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}, cube.GetTheSide(0)));
        assertTrue(Arrays.deepEquals(new int[][] {{4, 4, 4}, {4, 4, 4}, {4, 4, 4}}, cube.GetTheSide(1)));
        assertTrue(Arrays.deepEquals(new int[][] {{2, 2, 2}, {2, 2, 2}, {2, 2, 2}}, cube.GetTheSide(2)));
        assertTrue(Arrays.deepEquals(new int[][] {{5, 5, 5}, {5, 5, 5}, {5, 5, 5}}, cube.GetTheSide(3)));
        assertTrue(Arrays.deepEquals(new int[][] {{3, 3, 3}, {3, 3, 3}, {3, 3, 3}}, cube.GetTheSide(4)));
        assertTrue(Arrays.deepEquals(new int[][] {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}}, cube.GetTheSide(5)));

        cube.TurnTheCube(5); // turn counterclockwise
        for (int i = 0; i < 6; i++) {
            expectedSide = new int[][] {{i, i, i}, {i, i, i}, {i, i, i}};
            assertTrue(Arrays.deepEquals(expectedSide, cube.GetTheSide(i)));
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
            cube.TurnSides(1, 2, 0);
            cube.TurnSides(2, 2, 0);
            cube.TurnSides(1, 2, 1);
            cube.TurnSides(2, 2, 1);
        }

        for (int i = 0; i < 6; i++) {
            expectedSide = new int[][] {{i, i, i}, {i, i, i}, {i, i, i}};
            assertTrue(Arrays.deepEquals(expectedSide, cube.GetTheSide(i)));
        }

        cube.TurnSides(0, new int[] {1, 2}, new int[] {1, 0}); // front clockwise and central counterclockwise
        assertTrue(Arrays.deepEquals(new int[][] {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}, cube.GetTheSide(0)));
        assertTrue(Arrays.deepEquals(new int[][] {{4, 5, 1}, {4, 5, 1}, {4, 5, 1}}, cube.GetTheSide(1)));
        assertTrue(Arrays.deepEquals(new int[][] {{2, 2, 2}, {2, 2, 2}, {2, 2, 2}}, cube.GetTheSide(2)));
        assertTrue(Arrays.deepEquals(new int[][] {{3, 4, 5}, {3, 4, 5}, {3, 4, 5}}, cube.GetTheSide(3)));
        assertTrue(Arrays.deepEquals(new int[][] {{4, 4, 4}, {1, 1, 1}, {3, 3, 3}}, cube.GetTheSide(4)));
        assertTrue(Arrays.deepEquals(new int[][] {{1, 1, 1}, {3, 3, 3}, {5, 5, 5}}, cube.GetTheSide(5)));

        cube.TurnSides(0, new int[] {1, 2}, new int[] {0, 1}); // front counterclockwise and central clockwise
        for (int i = 0; i < 6; i++) {
            expectedSide = new int[][] {{i, i, i}, {i, i, i}, {i, i, i}};
            assertTrue(Arrays.deepEquals(expectedSide, cube.GetTheSide(i)));
        }
    }
}
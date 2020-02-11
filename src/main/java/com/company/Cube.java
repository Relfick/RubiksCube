package com.company;

public class Cube {
    Side[] sides;
    Cube() {
        sides = new Side[6];
        for (int i = 0; i < 6; i++) {
            sides[i] = new Side();
            sides[i].solveTheSide(i); // инициализируем куб в собранном состоянии
        }
    }

    void showTheStatus() {
        for (int i = 0; i < 6; i++) {
            System.out.println("Сторона " + i);
            sides[i].showTheSide();
            System.out.print("\n");
        }
    }

    void turnCubeToTheRight() {
        Side temp = sides[3];
        for (int i = 3; i > 0; i--)
            sides[i] = sides[i - 1];
        sides[0] = temp;
        // добавить поворот верхней и нижней
    }

    void turnCubeToTheLeft() {
        Side temp = sides[0];
        for (int i = 0; i < 3; i++)
            sides[i] = sides[(i + 1) % 4];
        sides[3] = temp;
        // добавить поворот верхней и нижней
    }

    void R() { // поворот по часовой стрелке правой грани
        for (int i = 0; i < 8; i++)
            sides[1].squares[i] = sides[1].squares[(i + 6) % 8];

        int[] temp = new int[] {sides[4].squares[2], sides[4].squares[3], sides[4].squares[4]};

        for (int i = 2; i < 5; i++)
            sides[4].squares[i] = sides[0].squares[i];
        for (int i = 2; i < 5; i++)
            sides[0].squares[i] = sides[5].squares[i];
        for (int i = 2; i < 5; i++)
            sides[5].squares[i] = sides[2].squares[i];
        for (int i = 2; i < 5; i++)
            sides[2].squares[i] = temp[i-2];
    }

    void Rinv() {
        // Сделать
    }
}

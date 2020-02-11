package com.company;

public class Cube {
    Side[] sides;
    int size;
    Cube(int size) {
        sides = new Side[6];
        for (int i = 0; i < 6; i++) {
            sides[i] = new Side(size);
            sides[i].solveTheSide(i); // initialize the solved cube
        }
        this.size = size;
    }

    void showTheStatus() {
        for (int i = 0; i < 6; i++) {
            System.out.println("Сторона " + i);
            sides[i].showTheSide();
            System.out.print("\n");
        }
    }

    // for my tests
    void ChangeTheSquare(int side, int i, int j, int color) {
        this.sides[side].squares[i][j] = color;
    }

    // direction: 0-Up, 1-Down, 2-Left, 3-Right
    void turnTheCube(int direction) {
        Side temp;
        switch (direction) {
            case 0:
                temp = sides[0];
                sides[0] = sides[5];
                sides[5] = sides[2];
                sides[2] = sides[4];
                sides[4] = temp;
                TurnTheFace(1, 0);
                TurnTheFace(3, 1);
                break;
            case 1:
                temp = sides[0];
                sides[0] = sides[4];
                sides[4] = sides[2];
                sides[2] = sides[5];
                sides[5] = temp;
                TurnTheFace(1, 1);
                TurnTheFace(3, 0);
                break;
            case 2:
                temp = sides[0];
                for (int i = 0; i < 3; i++)
                    sides[i] = sides[(i + 1) % 4];
                sides[3] = temp;
                TurnTheFace(4, 0);
                TurnTheFace(5, 1);
                break;
            case 3:
                temp = sides[3];
                System.arraycopy(sides, 0, sides, 1, 3);
                sides[0] = temp;
                TurnTheFace(4, 1);
                TurnTheFace(5, 0);
                break;
        }
    }

    //direction: 0 - clockwise, 1 - counterclockwise
    private void TurnTheFace(int side, int direction) {
        int tmp;
        if (direction == 0) {
            for (int i = 0; i < size / 2; i++)
                for (int j = i; j < size - 1 - i; j++) {
                    tmp = this.sides[side].squares[i][j];
                    this.sides[side].squares[i][j] = this.sides[side].squares[size - j - 1][i];
                    this.sides[side].squares[size - j - 1][i] = this.sides[side].squares[size - i - 1][size - j - 1];
                    this.sides[side].squares[size - i - 1][size - j - 1] = this.sides[side].squares[j][size - i - 1];
                    this.sides[side].squares[j][size - i - 1] = tmp;
                }
        }
        else {
            for (int i = 0; i < size / 2; i++)
                for (int j = i; j < size - 1 - i; j++) {
                    tmp = this.sides[side].squares[i][j];
                    this.sides[side].squares[i][j] = this.sides[side].squares[j][size-1-i];
                    this.sides[side].squares[j][size-1-i] = this.sides[side].squares[size-1-i][size-1-j];
                    this.sides[side].squares[size-1-i][size-1-j] = this.sides[side].squares[size-1-j][i];
                    this.sides[side].squares[size-1-j][i] = tmp;
                }
        }
    }

    // axis: x, y, z - 0, 1, 2
    // side: 0 - (size-1), count from left
    // view against the axis, 0 - clockwise, 1 - counterclockwise
    void TurnTheSide(int axis, int side, int direction) {

    }

}

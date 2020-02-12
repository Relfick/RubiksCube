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

    // for tests
    void ChangeTheSquare(int side, int i, int j, int color) {
        this.sides[side].squares[i][j] = color;
    }

    // direction: 0-Up, 1-Down, 2-Left, 3-Right, 4-clockwise, 5-counterclockwise
    void TurnTheCube(int direction) {
        Side temp;
        switch (direction) {
            case 0:
                temp = sides[0];
                sides[0] = sides[5];
                sides[5] = sides[2];
                TurnTheFace(5, 0);
                TurnTheFace(5, 0);
                sides[2] = sides[4];
                TurnTheFace(2, 0);
                TurnTheFace(2, 0);
                sides[4] = temp;
                TurnTheFace(1, 0);
                TurnTheFace(3, 1);
                break;
            case 1:
                temp = sides[0];
                sides[0] = sides[4];
                sides[4] = sides[2];
                TurnTheFace(4, 0);
                TurnTheFace(4, 0);
                sides[2] = sides[5];
                TurnTheFace(2, 0);
                TurnTheFace(2, 0);
                sides[5] = temp;
                TurnTheFace(1, 1);
                TurnTheFace(3, 0);
                break;
            case 2:
                temp = sides[0];
                System.arraycopy(sides, 1, sides, 0, 3);
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
            case 4:
                temp = sides[1];
                sides[1] = sides[4]; TurnTheFace(1, 0);
                sides[4] = sides[3]; TurnTheFace(4, 0);
                sides[3] = sides[5]; TurnTheFace(3, 0);
                sides[5] = temp; TurnTheFace(5, 0);
                TurnTheFace(0, 0);
                TurnTheFace(2, 1);
                break;
            case 5:
                temp = sides[1];
                sides[1] = sides[5]; TurnTheFace(1, 1);
                sides[5] = sides[3]; TurnTheFace(5, 1);
                sides[3] = sides[4]; TurnTheFace(4, 1);
                sides[4] = temp; TurnTheFace(4, 1);
                TurnTheFace(0, 1);
                TurnTheFace(2, 0);
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

    private void TurnFrontLayerToRight(int layer) {
        int[] temp = new int[size];
        for (int i = 0; i < size; i++)
            temp[i] = sides[3].squares[layer][i]; // создали temp слоя грани 3

        for (int i = 3; i > 0; i--)
            for (int j = 0; j < size; j++)
                sides[i].squares[layer][j] = sides[i - 1].squares[layer][j];

        for (int i = 0; i < size; i++)
            sides[0].squares[layer][i] = temp[i];

        if (layer == 0) TurnTheFace(4, 1);
        if (layer == size - 1) TurnTheFace(5, 0);
    }

    private void TurnFrontLayerToLeft(int layer) {
        int[] temp = new int[size];
        for (int i = 0; i < size; i++)
            temp[i] = sides[0].squares[layer][i]; // создали temp слоя грани 0

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < size; j++)
                sides[i].squares[layer][j] = sides[i + 1].squares[layer][j];

        for (int i = 0; i < size; i++)
            sides[3].squares[layer][i] = temp[i];

        if (layer == 0) TurnTheFace(4, 0);
        if (layer == size - 1) TurnTheFace(5, 1);
    }

    // axis: y-0, x-1, z-2 (origin - back side, the bottom left square; y - front, to you; x - right; z - up)
    // layer: 0 - (size-1), count from origin
    // direction: 0 - clockwise, 1 - counterclockwise; (view against the axis)
    void TurnTheSide(int axis, int layer, int direction) {
        if (axis == 2) {
            if (direction == 1) TurnFrontLayerToRight(size - layer - 1);
            else TurnFrontLayerToLeft(size - layer - 1);
        }
        else if (axis == 1)  {
                TurnTheCube(4); // Clockwise
                if (direction == 0) TurnFrontLayerToRight(layer);
                else TurnFrontLayerToLeft(layer);
                TurnTheCube(5); // CounterClockwise
        }
        else if (axis == 0) {
                TurnTheCube(1); // Down
                if (direction == 0) TurnFrontLayerToRight(layer);
                else TurnFrontLayerToLeft(layer);
                TurnTheCube(0); // Up
        }
    }
}

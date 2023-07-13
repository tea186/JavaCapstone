package tetris;
import java.util.Random;
public class Shape {
    protected enum Tetrominoes {
        NoShape, ZShape, SShape, LineShape,
        TShape, SquareShape, LShape, MirroredLShape
    }
    private Tetrominoes pieceShape;
    private final int[][] cords;

    public Shape() {

        cords = new int[4][2];
        setShape(Tetrominoes.NoShape);
    }

    void setShape(Tetrominoes shape) {
        int[][][] cordsTable = new int[][][]{
                {{0, 0}, {0, 0}, {0, 0}, {0, 0}},
                {{0, -1}, {0, 0}, {-1, 0}, {-1, 1}},
                {{0, -1}, {0, 0}, {1, 0}, {1, 1}},
                {{0, -1}, {0, 0}, {0, 1}, {0, 2}},
                {{-1, 0}, {0, 0}, {1, 0}, {0, 1}},
                {{0, 0}, {1, 0}, {0, 1}, {1, 1}},
                {{-1, -1}, {0, -1}, {0, 0}, {0, 1}},
                {{1, -1}, {0, -1}, {0, 0}, {0, 1}}
        };

        for (int i = 0; i < 4; i++) {
            System.arraycopy(cordsTable[shape.ordinal()], 0, cords, 0, 4);
        }

        pieceShape = shape;
    }


    private void setX(int index, int x) {

        cords[index][0] = x;
    }

    private void setY(int index, int y) {

        cords[index][1] = y;
    }

    int x(int index) {

        return cords[index][0];
    }

    int y(int index) {

        return cords[index][1];
    }

    Tetrominoes getShape() {

        return pieceShape;
    }

    void setRandomShape() {

        var r = new Random();
        int x = Math.abs(r.nextInt()) % 7 + 1;

        Tetrominoes[] values = Tetrominoes.values();
        setShape(values[x]);
    }


    int minY() {

        int m = cords[0][1];

        for (int i = 0; i < 4; i++) {

            m = Math.min(m, cords[i][1]);
        }

        return m;
    }

    Shape rotateLeft() {

        if (pieceShape == Tetrominoes.SquareShape) {

            return this;
        }

        var result = new Shape();
        result.pieceShape = pieceShape;

        for (int i = 0; i < 4; i++) {

            result.setX(i, y(i));
            result.setY(i, -x(i));
        }

        return result;
    }

    Shape rotateRight() {

        if (pieceShape == Tetrominoes.SquareShape) {

            return this;
        }

        var result = new Shape();
        result.pieceShape = pieceShape;

        for (int i = 0; i < 4; i++) {

            result.setX(i, -y(i));
            result.setY(i, x(i));
        }

        return result;
    }
}
package board;

import entities.Snake;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Matrix {

    private static final char VOID = ' ';
    private static final char WALL = '║';
    private static final char FLOOR = '═';

    private static final char UPPER_LEFT_CORNER = '╔';
    private static final char UPPER_RIGHT_CORNER = '╗';
    private static final char DOWN_LEFT_CORNER = '╚';
    private static final char DOWN_RIGHT_CORNER = '╝';
    private static final char LIFE  = '♥';
    private static final char SNAKE_HEAD = '●';
    private static final char SNAKE_BODY = '○';
    private static final char FOOD = '*';

    int width;
    int height;
    char[][] matrix;
    private final JTextArea textArea;


    public Matrix(int height, int width, JTextArea textArea) {

        this.width = width;
        this.height = height;
        this.textArea = textArea;

        this.matrix = new char[height][width];
    }

    // создания первой доски
    public void initMatrix() {

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || i == height - 1) {
                    matrix[i][j] = FLOOR;
                } else if (j == 0 || j == width - 1) {
                    matrix[i][j] = WALL;
                } else {
                    matrix[i][j] = VOID;
                }
            }
        }
        spawn();
       // printLevels();
    }

    private void spawn() {
        matrix[0][0] = UPPER_LEFT_CORNER;
        matrix[height - 1][0] = DOWN_LEFT_CORNER;
        matrix[height - 1][width - 1] = DOWN_RIGHT_CORNER;
        matrix[0][width - 1] = UPPER_RIGHT_CORNER;
    }

    private void printLevels() {

        for (int levelY = 10; levelY < height; levelY += 10) {
            printLevel(levelY, width - 1);
        }

    }

    public void printLevel(int level, int distasion) {
        for (int i = 1; i < width - 1; i++) {
            if (i % distasion == 0) {
                continue;
            }
            matrix[level][i] = FLOOR;
        }
    }


    // вывод в терминал борда
    public void render() {
        StringBuilder sb = new StringBuilder(width * height * 2);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                sb.append(matrix[y][x]).append(' ');
            }
            sb.append('\n');
        }
        textArea.setText(sb.toString());
    }

    public void printMatrix() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println(' ');
        }
    }

    // рисовка змеек на доску
    public void printSnakeToBoard(Snake snake) {
        int ind = 0;
        for (Point segment : snake.getHeadSnake()) {
            if (ind == 0) {
                matrix[segment.x][segment.y] = SNAKE_HEAD;
            } else {
                matrix[segment.x][segment.y] = SNAKE_BODY;
            }
            ind++;
        }
    }

    // рисовка змеек на доску
    public void mySnakePrint(Snake snake) {
        for (Point segment : snake.getHeadSnake()) {
                matrix[segment.x][segment.y] = 'Q';
        }
    }


    // очиста змеек с доски
    public void clearSnake(Snake snake) {
        for (Point segment : snake.getHeadSnake()) {
            matrix[segment.x][segment.y] = VOID;
        }
    }

    public void generateSnakeToFood(Snake snake) {
        for (Point segment : snake.getHeadSnake()) {
            matrix[segment.x][segment.y] = FOOD;
        }
    }

    // валидация змейки
    public boolean validatePoint(Point nextPoint, Snake snake) {
        char boardPoint = matrix[nextPoint.x][nextPoint.y];

        switch (boardPoint) {
            case VOID -> {
                return true;
            }
            case WALL, FLOOR, SNAKE_BODY,SNAKE_HEAD -> {
                System.out.println("смерть");
                snake.deadSnake();
                return false;
            }
            case FOOD -> {
                snake.plusSegment();
                snake.plusSpeed();
                return true;
            }
            case LIFE -> {
                snake.addLife();
                return true;
            }
        }
        return true;
    }




    public void generateFood() {
        Random random = new Random();
        int x, y;
        do {
            x = random.nextInt(1, width - 1);
            y = random.nextInt(1, height - 1);
        } while (matrix[y][x] == FLOOR || matrix[y][x] == FOOD);
        {
            matrix[y][x] = FOOD;
        }

    }

    public void generateLIFE() {
        Random random = new Random();
        int x, y;
        do {
            x = random.nextInt(1, width - 1);
            y = random.nextInt(1, height - 1);
        } while (matrix[y][x] == FLOOR || matrix[y][x] == FOOD || matrix[y][x]== LIFE);
        {
            matrix[y][x] = LIFE;
        }

    }


    public void putChar(int x, int y, char putChar) {
        matrix[x][y] = putChar;
    }

    public char getWall() {
        return WALL;
    }

    public char getFood() {
        return FOOD;
    }

    public char getSnakeBody() {
        return SNAKE_BODY;
    }

    public char getFlour() {
        return FLOOR;
    }

    public char getCharMatrix(int x, int y) {
        return matrix[x][y];
    }

}

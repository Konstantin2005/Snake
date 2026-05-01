package entities;


import board.Matrix;

import java.awt.*;
import java.util.*;

public class AiBoots {

    static char WALL;
    static char FLOUR;
    static char FOOD;
    static char BODY;
    static int WIDTH;
    static int HEIGHT;
    static char LIFE;

    public AiBoots(char wall, char food, char body, char flour, int wight, int height) {
        BODY = body;
        FOOD = food;
        WALL = wall;
        WIDTH = wight;
        HEIGHT = height;
        FLOUR = flour;
        LIFE = '♥';
    }

    public static void RandomMoveToFood(Snake snake, Matrix matrix) {

        Map<Direction, Integer> minDirection = new HashMap<>();

        Map<Direction, Integer> allDirection = searchMOVE(minDirection, snake, matrix);

        snake.putMovedSnake(searchMinDirection(allDirection));
    }

    private static Map<Direction, Integer> searchMOVE(Map<Direction, Integer> minDirection, Snake snake, Matrix matrix) {
        Direction moved = snake.getMovedSnake();

        Direction notDirection = (moved != null) ? moved.opposite() : Direction.NONE;

        for (Direction dir : Direction.values()) {
            if (dir != notDirection && dir != Direction.NONE) {
                minDirection.put(dir, -1);
            }
        }

        minDirection.keySet().forEach(direction -> {
            int tempDirection = searchDirection(direction, snake.getHeadPoint(), matrix);
            minDirection.replace(direction, tempDirection);
        });

        minDirection.values().removeAll(Collections.singleton(-1));

        if (minDirection.isEmpty()) {
            snake.putMovedSnake(Direction.NONE);
            return minDirection;
        }
        return minDirection;
    }


    private static Direction searchMinDirection(Map<Direction, Integer> allDirection) {

        Direction minDir = Direction.NONE;
        int min = Integer.MAX_VALUE;

        for (Map.Entry<Direction, Integer> entry : allDirection.entrySet()) {
            if (entry.getValue() < min) {
                min = entry.getValue();
                minDir = entry.getKey();
            }
        }

        if (minDir == Direction.NONE) {
            return Direction.random();
        }

        return minDir;
    }


    private static int searchDirection(Direction direction, Point snake, Matrix matrix) {
        switch (direction) {
            case UP -> {
                return searchUP(snake, matrix);
            }
            case DOWN -> {
                return searchDOWN(snake, matrix);
            }
            case LEFT -> {
                return searchLEFT(snake, matrix);
            }
            case RIGHT -> {
                return searchRIGHT(snake, matrix);
            }
        }
        return -1;
    }

    private static int searchDOWN(Point snakeHead, Matrix matrix) {

        for (int col = snakeHead.x + 1; col < HEIGHT; col++) {
            char temp = matrix.getCharMatrix(col, snakeHead.y);
            if (temp == '═' || temp == WALL) {
                return -1;
            } else if (temp == FOOD || temp == LIFE) {
                return col - snakeHead.y;
            }
        }
        return -1;
    }

    private static int searchUP(Point snakeHead, Matrix matrix) {

        for (int col = snakeHead.x - 1; col >= 0; col--) {
            char temp = matrix.getCharMatrix(col, snakeHead.y);
            if (temp == WALL || temp == BODY || temp == FLOUR) {
                return -1;
            } else if (temp == FOOD || temp == LIFE) {
                return snakeHead.y - col;
            }
        }
        return -1;
    }

    private static int searchRIGHT(Point snakeHead, Matrix matrix) {

        for (int row = snakeHead.y + 1; row < WIDTH; row++) {
            char temp = matrix.getCharMatrix(snakeHead.x, row);
            if (temp == WALL || temp == BODY || temp == FLOUR) {
                return -1;
            } else if (temp == FOOD || temp == LIFE) {

                return row - snakeHead.x;
            }
        }

        return -1;
    }

    private static int searchLEFT(Point snakeHead, Matrix matrix) {
        for (int row = snakeHead.y - 1; row >= 0; row--) {
            char temp = matrix.getCharMatrix(snakeHead.x, row);
            if (temp == FLOUR || temp == WALL || temp == BODY) {
                return -1;
            } else if (temp == FOOD || temp == LIFE) {

                return snakeHead.x - row;
            }
        }
        return -1;
    }


}

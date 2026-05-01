package entities;

import java.awt.*;
import java.util.LinkedList;

public class Snake {

    private static final int SNAKE_LENGTH = 3;

    protected Direction movement = Direction.NONE;
    protected int alive = -100000000;
    protected int addSegment;
    private int speed = 2;
    private int coeficent = 100;
    // protected int scored;

    LinkedList<Point> snake;


    public Snake(int spawnSnakeX, int spawnSnakeY) {
        snake = new LinkedList<>();
        addPointToSnake(spawnSnakeX, spawnSnakeY);
    }


    public Point moveSnake() {
        Point head = snake.getFirst();
        Point nextPoint = null;

        switch (movement) {
            case LEFT -> nextPoint = new Point(head.x, head.y - 1);
            case RIGHT -> nextPoint = new Point(head.x, head.y + 1);
            case UP -> nextPoint = new Point(head.x - 1, head.y);
            case DOWN -> nextPoint = new Point(head.x + 1, head.y);
        }
        return nextPoint;
    }

    public void addNextHead(Point nextPoint) {
        snake.addFirst(nextPoint);
        if (addSegment > 0) {
            addSegment--;
            return;
        }
        snake.removeLast();
    }

    private void addPointToSnake(int spawnSnakeX, int spawnSnakeY) {
        for (int i = 0; i < SNAKE_LENGTH; i++) {
            snake.add(new Point(spawnSnakeX, spawnSnakeY));
            spawnSnakeX--;
        }
    }

    public void plusSegment() {
        addSegment++;
    }

    public Direction getMovedSnake() {
        return movement;
    }

    public void putMovedSnake(Direction mobe) {
        movement = mobe;
    }

    public LinkedList<Point> getHeadSnake() {
        return snake;
    }

    public Point getHeadPoint() {
        return snake.getFirst();
    }

    public void plusSpeed() {
        speed++;
    }

    public int getSpeed() {
//        if(speed <= coeficent){
//            return coeficent;
//        }
//
//        return 3 * Integer.parseInt(String.valueOf(speed).substring(0, 1));

    return 5;
    }

    public int getAlive() {
        return alive;
    }

    public void deadSnake() {
        alive++;
    }
    public void addLife() {
        alive--;
    }
}

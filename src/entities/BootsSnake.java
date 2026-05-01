package entities;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BootsSnake {


    private final List<Snake> snakes;


    public BootsSnake(int countSnake, int StartX, int StartY) {
        this.snakes = new ArrayList<>();
        addAllSnakeHorizon(countSnake, StartX, StartY);
    }

    public void addAllSnakeVertical(int countSnake, int StartX, int StartY) {
        int newPosishionX = StartX;
        for (int i = 0; i < countSnake; i++) {
            snakes.add(new Snake(newPosishionX, StartY));
            newPosishionX += 10;
        }
    }

    public void addAllSnakeHorizon(int countSnake, int StartX, int StartY) {
        int newPosishionY = StartY;
        for (int i = 0; i < countSnake; i++) {
            snakes.add(new Snake(StartX, newPosishionY));
            newPosishionY += 2;
        }
    }

    public List<Snake> getBootSnake() {
        return new ArrayList<>(snakes); // копия!
    }

    public int getLengthBootsSnake() {
        return snakes.toArray().length;
    }

    public void removeSnakeBoot(Snake snake) {
        snakes.remove(snake);
    }
}

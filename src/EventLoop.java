import entities.AiBoots;
import entities.BootsSnake;
import entities.Direction;
import entities.Snake;
import board.GameWindow;
import board.Matrix;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;


public class EventLoop {


    int count = 0;
    boolean gameOver = true;
    private final AtomicReference<Direction> currentDir;
    private static final int SPEED = 50;

    Matrix matrix;
    Snake snake;
    GameWindow window;
    BootsSnake BootsSnake;
    AiBoots aiBoots;
    ScheduledExecutorService executor;


    public EventLoop(int height, int width) {

        this.window = new GameWindow("Snake", 1920, 1080);

        makeWinod();

        this.matrix = new Matrix(height, width, window.getArea());
        this.snake = new Snake(height / 2, width / 2);
        this.executor = Executors.newSingleThreadScheduledExecutor();
        this.currentDir = new AtomicReference<>(Direction.NONE);
        this.BootsSnake = new BootsSnake(150, 5, 5);

        this.aiBoots = new AiBoots(matrix.getWall(), matrix.getFood(), matrix.getFlour(), matrix.getSnakeBody(), width, height);
        gameLoop();

    }


    public void gameLoop() {
        if (count == 100_000_000) {
            gameOver = false;
        }

        matrix.initMatrix();


        executor.scheduleAtFixedRate(() -> {
            matrix.clearSnake(snake);

            if (!gameOver) {
                executor.shutdown();
                return;
            }

            snake.putMovedSnake(currentDir.get());

            Point nextPoint = snake.moveSnake();

            if (nextPoint != null) {
                if (matrix.validatePoint(nextPoint, snake)) {
                    snake.addNextHead(nextPoint);
                }
            }


            for (Snake snakeBoot : BootsSnake.getBootSnake()) {
                if (snakeBoot.getAlive() >= 1) {
                    matrix.generateSnakeToFood(snakeBoot);
                    System.out.println(snakeBoot.getAlive());
                    BootsSnake.removeSnakeBoot(snakeBoot);
                    continue;
                }

                for (int countMoveSnake = 0; countMoveSnake < snakeBoot.getSpeed(); countMoveSnake++) {
                    moveBootSnake(snakeBoot);
                }
            }


            matrix.generateFood();



            if (count % 3 == 0) {
                matrix.generateLIFE();
            }


            // matrix.printMatrix();
            matrix.mySnakePrint(snake);
            matrix.render();
            count++;


            window.updateScore(BootsSnake.getLengthBootsSnake());

        }, 0, SPEED, TimeUnit.MILLISECONDS);
    }


    public void makeWinod() {
        // создаём окно
        window.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W, KeyEvent.VK_UP -> currentDir.set(Direction.UP);
                    case KeyEvent.VK_S, KeyEvent.VK_DOWN -> currentDir.set(Direction.DOWN);
                    case KeyEvent.VK_A, KeyEvent.VK_LEFT -> currentDir.set(Direction.LEFT);
                    case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> currentDir.set(Direction.RIGHT);
                }
            }
        });
        window.show();
    }


    private void moveBootSnake(Snake snakeBoot) {
        matrix.clearSnake(snakeBoot);

        AiBoots.RandomMoveToFood(snakeBoot, matrix);
        Point nextPointBoot = snakeBoot.moveSnake();

        if (nextPointBoot != null) {
            if (matrix.validatePoint(nextPointBoot, snakeBoot)) {
                snakeBoot.addNextHead(nextPointBoot);
            }
        }
        matrix.printSnakeToBoard(snakeBoot);
    }
}




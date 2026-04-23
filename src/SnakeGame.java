import java.awt.*;
import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

 class SnakeGame {
    // Начальные характиристики
    int speed = 1000;
    int width = 30;
    int height = 40;
    int coeficentSpeed = 5;





    // Временые переменые
    int score = 0;
    int count = 0;
    int AddSegment = 0;
    boolean gameOver = true;

    // Сложные штуки
    char[][] board;
    Deque<Point> snake = new LinkedList<>();









// Главыный цикл игры
public void run() throws IOException, InterruptedException {


    // Сканер для чтения из консоли
    Scanner scanner = new Scanner(System.in);

    // Направление движение змейки по умолчанию
    char ch1 = 'a';
    // Выходит по флагу
    while (gameOver) {


        // Счетчик общих итераций
        count++;

    }

    System.out.println("Game Over! Your score: " + score);

}




// Создание борда



















}
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


    // Символы для отрисовки
    char head = '●';
    char body = '○';
    char food = '*';


    char roof = '═';
    char wall = '║';
    char one = '╔';
    char two = '╚';
    char tree = '╝';
    char four = '╗';


    // Временые переменые
    int score = 0;
    int count = 0;
    int AddSegment = 0;
    boolean gameOver = true;

    // Сложные штуки
    char[][] board;
    Deque<Point> snake = new LinkedList<>();



    // Точка входа в приложение
    public static void main(String[] args) throws IOException, InterruptedException {
        // реализовать запуск игры
        SnakeGame game = new SnakeGame();

        game.run();
        }






// Главыный цикл игры
public void run() throws IOException, InterruptedException {

    // Базовый раскид
    initializeGame();

    // Сканер для чтения из консоли
    Scanner scanner = new Scanner(System.in);

    // Направление движение змейки по умолчанию
    char ch1 = 'a';
    // Выходит по флагу
    while (gameOver) {

        // Каждый 2 тика спавнить еду
        if (count % 2 == 0 ){generateFood();}

        ch1 = moveSnake(scanner,ch1);

        // Рисуем всю картинку
        printBoard();

        // Остоновливаем змейку
        Thread.sleep(speed-(count+coeficentSpeed) );

        // Счетчик общих итераций
        count++;

    }

    System.out.println("Game Over! Your score: " + score);

}

// Начальная конфигурация борда
private void initializeGame() {
    initBoard();
    initSnake();
    initSnakeBoard();
}


// Создание борда
private void initBoard() {
    board = new char[width][height];

    for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
            // Проверяем, находится ли текущая позиция на границе
            if (i == 0 || i == width - 1 ) {
                board[i][j] = roof; // Граница
            } else if (j == 0 || j == height - 1) {
                board[i][j] = wall;
            }else{
                board[i][j] = ' ';
            }
        }
        System.out.println();
    }
    spawn();
}

// Ставит 4 угла
private void spawn() {
    board[0][0] = one;
    board[width-1][0] = two;
    board[width-1][height-1] = tree;
    board[0][height-1] = four;
}

private void initSnake() {

    int startX = width-20;
    int startY = height-20;

    snake.add(new Point(startX,startY));
    snake.add(new Point(startX,startY+1));
    snake.add(new Point(startX,startY+2));
}
// добовление змейки на доску
private void initSnakeBoard() {
    int index = 0;
    for (Point segment : snake) {
        if (index == 0) {
            board[segment.x][segment.y] = head;
        } else {
            board[segment.x][segment.y] = body;
        }
        index++;
    }

}

private char moveSnake(Scanner scanner, char ch1) throws IOException  {
    // Удаляет старую змейку


    // Смотрит в консоль и проверяет нет ли там новых симоволов.
    // Если нет то скип и кидает старый параметр
    if (System.in.available() > 0) {
        String input = scanner.nextLine();
        int temp = input.charAt(0);
        if (temp == 'a' || temp == 's' || temp == 'w' || temp == 'd') {
            ch1 = input.charAt(0);
        }
    }
    clearSnake();
    // Двигаем змейку в какое-то направление
    move(ch1);


    // Рисуем новую змейку
    initSnakeBoard();
    return ch1;

}

// Движение змейки
private void move(char move) {

    switch (move){

        case 'w' -> {

            Point head = snake.getFirst();
            Point nextPoint = new Point(head.x-1, head.y);

            if(checkHead(nextPoint)){break;};

            snake.addFirst(nextPoint);
            RemoveLastSegemnt();

        }
        case 's' -> {
            Point head = snake.getFirst();

            Point nextPoint = new Point(head.x+1, head.y);

            if(checkHead(nextPoint)){break;};

            snake.addFirst(nextPoint);
            RemoveLastSegemnt();

        }
        case 'a' -> {
            Point head = snake.getFirst();
            Point nextPoint = new Point(head.x,head.y-1);

            if(checkHead(nextPoint)){break;};

            snake.addFirst(nextPoint);
            RemoveLastSegemnt();
        }
        case 'd' -> {
            Point head = snake.getFirst();
            Point nextPoint = new Point(head.x,head.y+1);

            if(checkHead(nextPoint)){break;};

            snake.addFirst(nextPoint);
            RemoveLastSegemnt();
        }
    }

}

// генерация еды
private void generateFood() {
    Random random = new Random();
    int x ,y ;
    do {
        x = random.nextInt(width-1);
        y = random.nextInt(height-1);
    }while(isSnakeAt(x,y) || isWallAt(x,y));{
        board[x][y] = food;
    };
}

// проверка на то что еда не генерировалась на стене
private boolean isWallAt(int x, int y) {
    for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
            // Проверяем, находится ли текущая позиция на границе
            if (i == x && j == y && (i == 0 || i == width - 1 || j == 0 || j == height - 1)) {
                return true;// Граница
            }
        }
    }
    return false;
}


// проверка на то что еда не генерировалась на змейке
private boolean isSnakeAt(int x, int y) {
    for (Point segment : snake) {
        if (segment.x == x && segment.y == y) {
            return true;
        }
    }
    return false;
}

// Чистим старую змейку, чтобы нарисовать новую.
private void clearSnake(){
    for (Point segment : snake) {
        board[segment.x][segment.y] = ' ';
    }
}


private boolean checkHead(Point nextPoint) {
    char temp = board[nextPoint.x][nextPoint.y];

    if ( temp == wall) {
        gameOver = false;
        return true;
    }
    if (temp== food) {
        score += 1;
        AddSegment++;
    }
    if (temp == body) {
        gameOver = false;
        return true;
    }
    if (isSnakeAt(nextPoint.x,nextPoint.y)){
        gameOver = false;
        return true;
    }

    return false;
}


// Добовляет новый сегмент змейки
private void RemoveLastSegemnt() {
    if (AddSegment > 0) {
        AddSegment--;
        return;
    }
    Point _ = snake.removeLast();
}

// принт доски
private void printBoard() {

    for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {

            System.out.print(board[i][j] + " ");
        }
        System.out.println();
    }
}

}
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

// Написть все класы которые будут наследоваться для управления
public class EventLoop {
    // змейки просто
    static Snake snake = new Snake(0, Color.GREEN, false);
    Snake[] ArrBootSnake = new Snake[33];


    // рандомайзер для движения ботов
    private static final Random random = new Random();

    // многопоточная фигная
    public enum Direction { UP, DOWN, LEFT, RIGHT, NONE }
    private final AtomicReference<Direction> currentDir =
            new AtomicReference<>(Direction.NONE);

    static Board board = new Board();
    boolean gameOver;
    int count = 1;
    int score = 0;

    public void EventLoop(int SnakeCount, int weight, int height) {


        for (int i = 0; i < SnakeCount; i++) {
            ArrBootSnake[i] = new Snake( 1000, Color.RED, false);
        }

        JFrame f = new JFrame("Snake");
        JTextArea area = new JTextArea();
        area.setFont(new Font("Consolas", Font.ITALIC, 25));
        area.setEditable(false);
        area.setFocusable(false);
        f.add(new JScrollPane(area));
        f.setSize(1920, 1080);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W, KeyEvent.VK_UP    -> currentDir.set(Direction.UP);
                    case KeyEvent.VK_S, KeyEvent.VK_DOWN  -> currentDir.set(Direction.DOWN);
                    case KeyEvent.VK_A, KeyEvent.VK_LEFT  -> currentDir.set(Direction.LEFT);
                    case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> currentDir.set(Direction.RIGHT);
                }
            }
        });
        f.setVisible(true);
        f.requestFocusInWindow();



        // создание базы
        gameOver = true;
        board.InitBoard(weight, height,area);

        // инициализация всего хорошего го
        initSnakes(weight, height);

                new Thread(() -> {
            while (gameOver) {

//                if (snake.alive){
//                    SpawnOnSnakeFood();
//                    snake.clearSnake();
//                }
                clearSnake();
                snake.movement = currentDir.get(); // штука читает покеде

                snake.move();

                    if(count % 2 == 0){
                    for (Snake bot : ArrBootSnake) {
                        clearBootSnake();
                        bot.movement = RandonMOVE();
                        bot.move();
                        PrintBootBoard();
                    }
                }
                if (count % 4 == 0) {
                    generateFood();
                }
                PrintSnakeBoard();

                board.render();

                //board.printBoard();
                try { Thread.sleep(80); } catch (InterruptedException ex) { break; }
                count++;
            }
        }, "game-loop").start();

    }


    private void SpawnOnSnakeFood(){
        for (Point segment : snake.HeadSnake()) {
            board.putPointBoard(segment.x, segment.y, '*');
        }
    }

    public static Direction RandonMOVE() {
        Direction[] directions = Direction.values();
        snake.countmove++;
        if (snake.countmove % 15 ==0){
            return Direction.LEFT;
        }
        if (snake.countmove % 17 ==0){
            return Direction.LEFT;
        }

        if (snake.countmove % 25 ==0){
            return Direction.UP;
        }
        if (snake.countmove % 26 ==0){
            return Direction.UP;
        }

        if (snake.countmove % 31 ==0){
            return Direction.LEFT;
        }
        if (snake.countmove % 32 ==0){
            return Direction.LEFT;
        }

        if (snake.countmove % 41 ==0){
            return Direction.DOWN;
        }
        if (snake.countmove % 42 ==0){
            return Direction.DOWN;
        }
        return directions[random.nextInt(directions.length)];

    }

    public static Direction RandomMoveToFood(){
        Point head = snake.HeadSnakePoint();



        return Direction.LEFT;
    }

    private void clearSnake(){
        for (Point segment : snake.HeadSnake()) {
                board.putPointBoard(segment.x, segment.y, ' ');
            }
        }

        private void clearBootSnake(){
            for(Snake bot : ArrBootSnake){
                for (Point segment : bot.HeadSnake()){
                    board.putPointBoard(segment.x, segment.y, ' ');
                }
            }
        }

    // генирирум начальную позицию на борде
    public void initSnakes(int weight, int height) {
        int startWeight = weight - 1;
        int startHeight = height -7;

        snake.initSnake(3, 7);

        int bootStartWeight = startWeight;
        for (Snake BotSnake : ArrBootSnake) {
            bootStartWeight -= 1;
            BotSnake.initSnake(bootStartWeight,startHeight);

        }

    }


    private void generateFood() {
        Random random = new Random();
        int x ,y ;
        do {
            x = random.nextInt(board.getWeight()-1);
            y = random.nextInt(board.getHeight()-1);
        }while(isSnakeAt(x,y) || isWallAt(x,y));{
            board.putPointBoard(x,y,board.food);
        };
    }


    // проверка на то что еда не генерировалась на змейке
    private boolean isSnakeAt(int x, int y) {
        for (Point segment : snake.HeadSnake()) {
            if (segment.x == x && segment.y == y) {
                return true;
            }
        }
        return false;
    }


    // проверка на то что еда не генерировалась на стене
    private boolean isWallAt(int x, int y) {
        for (int i = 0; i < board.getWeight(); i++) {
            for (int j = 0; j < board.getHeight(); j++) {
                // Проверяем, находится ли текущая позиция на границе
                if (i == x && j == y && (i == 0 || i == board.getWeight() - 1 || j == 0 || j == board.getHeight() - 1)) {
                    return true;// Граница
                }
            }
        }
        return false;
    }


    // кидаем снаки на борд
    private void PrintSnakeBoard() {
        int index = 0;
        for (Point segment : snake.HeadSnake()) {
            if (index == 0) {
                board.putPointBoard(segment.x, segment.y, snake.head);
            } else {
                board.putPointBoard(segment.x, segment.y, snake.body);
            }
            index++;
        }

    }

    private void PrintBootBoard() {
       for (Snake snake : ArrBootSnake) {
           int index = 0;
           for (Point segment : snake.HeadSnake()){
               if (index == 0) {
                   board.putPointBoard(segment.x, segment.y, snake.head);
               } else {
                   board.putPointBoard(segment.x, segment.y, snake.body);
               }
               index++;
           }

       }
       }

    public static char getBoard(int x, int y){
        return board.getPointCharBoard(x,y);
    }

    private char moveSnake(Scanner scanner, char ch1) throws IOException {
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
        return ch1;
    }


}

















//    // генерация еды
//    private void generateFood() {
//        Random random = new Random();
//        int x ,y ;
//        do {
//            x = random.nextInt(width-1);
//            y = random.nextInt(height-1);
//        }while(isSnakeAt(x,y) || isWallAt(x,y));{
//            getBoard[x][y] = food;
//        };
//    private void clearSnake(){
//        for (Point segment : snake) {
//            board[segment.x][segment.y] = ' ';
//        }
//    }



//    // проверка на то что еда не генерировалась на стене
//    private boolean isWallAt(int x, int y) {
//        for (int i = 0; i < board.getWeight(); i++) {
//            for (int j = 0; j < board.getHeight(); j++) {
//                // Проверяем, находится ли текущая позиция на границе
//                if (i == x && j == y && (i == 0 || i == board.getWeight() - 1 || j == 0 || j == board.getHeight() - 1)) {
//                    return true;// Граница
//                }
//            }
//        }
//        return false;


//    // проверка на то что еда не генерировалась на змейке
//    private boolean isSnakeAt(int x, int y) {
//
//        for (Point segment : Snake.HeadSnake()) {
//            if (segment.x == x && segment.y == y) {
//                return true;
//            }
//        }
//        return false;
//    }


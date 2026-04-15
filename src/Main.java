import java.awt.*;
import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;

class SnakeGame {
    // Начальные характиристики
    int spead = 1000;
    int width = 30;
    int height = 40;
    int coeficentspeed = 100;


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

        }
    }





// Главыный цикл игры
public void run() throws IOException, InterruptedException {

    // сделать глобальный цикл игры в котором будут реализованы все куски

}

// Начальная конфигурация борда
private void initializeGame() {
    initBoard();
    initSnake();
    initSnakeBoard();
}


// Создание борда
private void initBoard() {

    // вывыести борд со всеми стенами и нужным размером
    spawn();
}

// Ставит 4 угла
private void spawn() {
    // раскидать в правельные углы нужные элементы
}

private void initSnake() {
    // создать змейку линкед лист
    //
}
// добовление змейки на доску
private void initSnakeBoard() {


}

private char moveSnake(Scanner scanner,char ch1) throws IOException  {

    // Смотрит в консоль и проверяет нет ли там новых симоволов.

    // Если нет то скип и кидает старый параметр

    // Удаляет старую змейку

    // Двигаем змейку в какое-то направление

    // Рисуем новую змейку

}

// Движение змейки
private void move(char move) {

    // реализовать логику движения змейки и добовление новых сегментов

}

// генерация еды
private void generateFood() {
    // заспавнить в любом свободном месте еду

    // не на змейке, стене и не на старой еде
}

// проверка на то что еда не генерировалась на стене
private boolean isWallAt(int x, int y) {

}


// проверка на то что еда не генерировалась на змейке
private boolean isSnakeAt(int x, int y) {

}

// Чистим старую змейку, чтобы нарисовать новую.
private void clearSnake(){
    // итерируемся по
}


private boolean checkHead(Point nextPoint) {
    // реализовать проверку башки то что она наступила на
    // себя или стену. то умирает змея
    // на еду. то добовляет новый фрагмет
}


// Добовляет новый сегмент змейки
private void RemoveLastSegemnt() {

}

// принт доски
private void printBoard() {

    // выводим доску
}
}
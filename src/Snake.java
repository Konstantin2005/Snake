import java.awt.*;
import java.util.LinkedList;

public class Snake {

    protected LinkedList<Point> snake;
    protected Color color;
    protected int speed;
    protected EventLoop.Direction movement;
    protected boolean alive;
    protected int addSegment;
    protected int countmove;

    // Символы для отрисовки
    char head = '●';
    char body = '○';
    char food = '*';


    public Snake( int speed, Color color, boolean alive){
        this.snake = new LinkedList<Point>();
        this.movement = EventLoop.Direction.LEFT;
        this.speed = speed;
        this.color = color;
        this.alive = alive;
        this.addSegment = 0;
        this.countmove = 1;
    }

    // Движение змейки
    void move() {

        switch (movement) {

            case EventLoop.Direction.LEFT -> {

                Point head = snake.getFirst();
                Point nextPoint = new Point(head.x , head.y-1);

                if (checkHead(nextPoint)) {
                    break;
                }
                ;

                snake.addFirst(nextPoint);
                RemoveLastSegemnt();

            }
            case EventLoop.Direction.UP-> {
                Point head = snake.getFirst();
                Point nextPoint = new Point(head.x-1, head.y);

                if (checkHead(nextPoint)) {
                    break;
                }

                snake.addFirst(nextPoint);
                RemoveLastSegemnt();
            }
            case EventLoop.Direction.RIGHT  -> {
                Point head = snake.getFirst();
                Point nextPoint = new Point(head.x, head.y+1);

                if (checkHead(nextPoint)) {
                    break;
                }

                snake.addFirst(nextPoint);
                RemoveLastSegemnt();
            }
            case EventLoop.Direction.DOWN  -> {
                Point head = snake.getFirst();
                Point nextPoint = new Point(head.x+1, head.y);

                if (checkHead(nextPoint)) {
                    break;
                }

                snake.addFirst(nextPoint);
                RemoveLastSegemnt();
            }
        }
    }
    boolean checkHead(Point nextPoint) {
        char temp = EventLoop.getBoard(nextPoint.x, nextPoint.y);

        if ( temp == EventLoop.board.wall || temp == EventLoop.board.roof) {
            alive = true;
            return true;
        }
        if (temp ==food) {
            addSegment++;
        }
        if (temp == body) {
            alive = true;
            return true;
        }
        if (isSnakeAt(nextPoint.x,nextPoint.y)){
            alive = false;
            return true;
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

    // Добовляет новый сегмент змейки
    private void RemoveLastSegemnt() {
        if (addSegment> 0) {
            addSegment--;
            return;
        }
        Point _ = snake.removeLast();
    }


        public void initSnake(int startX, int startY) {

        snake.add(new Point(startX, startY));
        snake.add(new Point(startX , startY+1));
        snake.add(new Point(startX, startY+2));
        snake.add(new Point(startX, startY+3));
        snake.add(new Point(startX, startY+4));


    }

    public void initSnake2(int startX, int startY) {

        snake.add(new Point(startX, startY));
        snake.add(new Point(startX , startY-1));
        snake.add(new Point(startX, startY-2));

    }

    public void clearSnake(){
        while (!snake.isEmpty()) {
            snake.removeFirst();
        }
    }

    public Point HeadSnakePoint(){
    return snake.getFirst();
    }

    LinkedList<Point> HeadSnake(){return snake;}

}

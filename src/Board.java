import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Board {

private int width;
private int height;
public char[][] board;
final String RED = "\u001B[31m";
final String GREEN = "\u001B[32m";
final String YELLOW = "\u001B[33m";
final String RESET = "\u001B[0m";
        char roof = '═';
        char wall = '║';
        char one = '╔';
        char two = '╚';
        char tree = '╝';
        char four = '╗';
        char food = '*';
    private JTextArea textArea;



    public void InitBoard(int height,int width,JTextArea textArea){
               this.height = height;
               this.width = width;
               board = new char[height][width];
            this.textArea = textArea;

        for (int i = 0; i < height; i++) {
                   for (int j = 0; j < width; j++) {
                       // Проверяем, находится ли текущая позиция на границе
                       if (i == 0 || i == height - 1 ) {
                           board[i][j] = roof; // Граница
                       } else if (j == 0 || j == width - 1) {
                           board[i][j] = wall;
                       }else{
                           board[i][j] = ' ';
                       }
                   }System.out.println();
                }
                spawn();
       }

    // Ставит 4 угла
    private void spawn() {
        board[0][0] = one;
        board[height-1][0] = two;
        board[height-1][width-1] = tree;
        board[0][width-1] = four;
    }
    // генерация еды

     void printBoard() {

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void render() {
        StringBuilder sb = new StringBuilder(width * height * 2);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if(board[y][x] == EventLoop.snake.head){
                    sb.append(board[y][x]).append(' ');
                    continue;
                }
                sb.append(board[y][x]).append(' ');
            }
            sb.append('\n');
        }
        textArea.setText(sb.toString());
    }

    public void printToConsole() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) System.out.print(board[y][x] + " ");
            System.out.println();
        }
    }

public char[][] getBoard(){return board;}



public char getPointCharBoard(int PointX, int PointY){return board[PointY][PointX];}
public void putPointBoard(int PointX , int PointY , char str) {board[PointY][PointX] = str;}
    public int getWeight(){return width;}

    public int getHeight(){return height;}
}

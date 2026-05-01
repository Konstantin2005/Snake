package board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;

public class GameWindow {

    private final JFrame frame;
    private final JTextArea area;
    private final JLabel scoreLabel;

    public GameWindow(String title, int windowWidth, int windowHeight) {
        // глобальное окно
        frame = new JFrame(title);
        area = new JTextArea();


        area.setFont(new Font("Consolas", Font.ITALIC, 5));
        area.setEditable(false);
        area.setFocusable(false);


        area.setBackground(Color.BLACK);
        area.setForeground(new Color(0, 255, 70));
//
      //  area.setBackground(new Color(20, 20, 30));
    //   area.setForeground(new Color(200, 255, 200));

   //     area.setBackground(new Color(15, 10, 30));
  //      area.setForeground(new Color(255, 80, 200));

   //     area.setBackground(new Color(0, 43, 54));
  //      area.setForeground(new Color(131, 148, 150));


        // мини окно (панель с очками)
        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        scoreLabel = new JLabel("0");
        scoreLabel.setFont(new Font("Consolas", Font.BOLD, 28));

        infoPanel.setBackground(new Color(40, 40, 50));
        scoreLabel.setForeground(new Color(255, 215, 0));

        infoPanel.add(scoreLabel);

        // добавление компонентов
        frame.add(infoPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(area), BorderLayout.CENTER);

        // характеристики окна
        frame.setSize(windowWidth, windowHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void addKeyListener(KeyAdapter keyAdapter) {
        frame.addKeyListener(keyAdapter);
    }

    public void show() {
        frame.setVisible(true);
        frame.requestFocusInWindow();
    }

    public JTextArea getArea() {
        return area;
    }

    public void updateScore(int score) {
        scoreLabel.setText(String.valueOf(score));
    }
}
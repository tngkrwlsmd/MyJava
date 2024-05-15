package java13.java13_2;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class java13_2 extends JFrame{
  class MyPanel extends JPanel {
    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.setColor(Color.MAGENTA);
      int x = (int) (Math.random() * getWidth());
      int y = (int) (Math.random() * getHeight());
      g.drawOval(x, y, 50, 50);
    }
  }

  class MyThread extends Thread {
    MyPanel panel;
    public MyThread(MyPanel p) {
      panel = p;
    }
    @Override
    public void run() {
      while (true) {
        try {
          sleep(500);
          panel.repaint();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }
  
  public java13_2() {
    MyPanel panel = new MyPanel();
    MyThread th = new MyThread(panel);
    setTitle("원을 0.5초 간격으로 이동하는 frame");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setContentPane(panel);
    panel.setLayout(null);
    panel.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        th.start();
      }
    });
    setVisible(true);
    setSize(300, 300);
  }

  public static void main(String[] args) {
    new java13_2();
  }
}

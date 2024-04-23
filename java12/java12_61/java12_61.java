package java12.java12_61;
import java.awt.*;
import javax.swing.*;

public class java12_61 extends JFrame{
  Pan pan = new Pan();

  public java12_61() {
    setContentPane(pan);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
    setSize(300, 300);
  }

  class Pan extends JPanel {
    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      int bufX = pan.getWidth() / 10;
      int bufY = pan.getHeight() / 10;
      int x = 0, y = 0;
      while (x < pan.getWidth()) {
        g.drawLine(0, y, getWidth(), y);
        g.drawLine(x, 0, x, getHeight());
        x += bufX; y += bufY;
      }
    }
  }

  public static void main(String[] args) {
    new java12_61();
  }
}

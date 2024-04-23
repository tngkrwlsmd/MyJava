package java12.java12_62;
import java.awt.*;
import javax.swing.*;

public class java12_62 extends JFrame{
  Pan pan = new Pan();
  
  public java12_62() {

    setContentPane(pan);
    setVisible(true);
    setSize(300, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  class Pan extends JPanel {
    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      int w = getWidth();
      int h = getHeight();

      int minusW = 0, minusH = 0;
      for (int i = 0; i < 10; i++) {
        int xz[] = {w / 2, minusW, w / 2, w - minusW}; // 12시 방향부터 반시계방향
        int yz[] = {minusH, h / 2, h - minusH, h / 2};
        g.drawPolygon(xz, yz, 4);
        minusW += 10; minusH += 10;
      }
    }
  }

  public static void main(String[] args) {
    new java12_62();
  }
}

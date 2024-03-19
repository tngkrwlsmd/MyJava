package java9.java9_6;
import javax.swing.*;
import java.awt.*;

public class java9_6 extends JFrame{
  public java9_6() {
    setTitle("Random Labels");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(null);

    for(int i = 0; i < 20; i++) {
      JLabel label = new JLabel();
      label.setBackground(Color.BLUE);
      label.setOpaque(true);
      label.setSize(10, 10);
      int x = (int)(Math.random() * 200) + 50;
      int y = (int)(Math.random() * 200) + 50;
      label.setLocation(x, y);
      add(label);
    }

    setSize(300, 300);
    setVisible(true);
  }

  public static void main(String[] args) {
    new java9_6();
  }
}

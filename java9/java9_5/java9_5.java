package java9.java9_5;
import javax.swing.*;
import java.awt.*;

public class java9_5 extends JFrame{
  public java9_5() {
    setTitle("4x4 Color Frame");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new GridLayout(4, 4));

    for (int i = 0; i < 16; i++) {
      JLabel label = new JLabel(Integer.toString(i));
      int r = (int)(Math.random() * 255);
      int g = (int)(Math.random() * 255);
      int b = (int)(Math.random() * 255);
      label.setBackground(new Color(r, g, b));
      label.setOpaque(true);
      add(label);
    }

    setSize(800, 400);
    setVisible(true);
  }

  public static void main(String[] args) {
    new java9_5();
  }
}

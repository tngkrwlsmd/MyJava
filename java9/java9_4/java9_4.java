package java9.java9_4;
import javax.swing.*;
import java.awt.*;

public class java9_4 extends JFrame{
  public java9_4() {
    setTitle("Ten Color Buttons Frame");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new GridLayout());

    for(int i = 0; i < 10; i++) {
      JButton button = new JButton(Integer.toString(i));
      int r = (int)(Math.random() * 256);
      int g = (int)(Math.random() * 256);
      int b = (int)(Math.random() * 256);
      button.setBackground(new Color(r, g, b));
      add(button);
    }

    setSize(500, 250);
    setVisible(true);
  }

  public static void main(String[] args) {
    new java9_4();
  }
}

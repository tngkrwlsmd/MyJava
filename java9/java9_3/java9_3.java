package java9.java9_3;
import javax.swing.*;
import java.awt.*;

public class java9_3 extends JFrame{
  public java9_3() {
    setTitle("Ten Color Buttons Frame");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new GridLayout());
    for (int i = 0; i <= 9; i++) add(new JButton(Integer.toString(i)));
    setSize(500, 250);
    setVisible(true);
  }

  public static void main(String[] args) {
    new java9_3();
  }
}

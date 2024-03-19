package java9.java9_2;
import javax.swing.*;
import java.awt.*;

public class java9_2 extends JFrame{
  public java9_2() {
    setTitle("BorderLayout Practice");
    setLayout(new BorderLayout(5, 7));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    add(new JButton("North"), BorderLayout.NORTH);
    add(new JButton("West"), BorderLayout.WEST);
    add(new JButton("East"), BorderLayout.EAST);
    add(new JButton("South"), BorderLayout.SOUTH);
    add(new JButton("Center"), BorderLayout.CENTER);

    setSize(400, 200);
    setVisible(true);
  }

  public static void main(String[] args) {
    new java9_2();
  }
}

package java10.java10_5;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class java10_5 extends JFrame{
  private JLabel label = new JLabel("Love Java");

  public java10_5() {
    setTitle("+, -키로 폰트 크기 조절");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Container c = getContentPane();
    c.setLayout(new FlowLayout());
    label.setFont(new Font("Arial", Font.PLAIN, 10));
    c.add(label);

    c.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        Font f = label.getFont();
        int size = f.getSize();
        switch (e.getKeyCode()) {
          case 61:
          label.setFont(new Font("Arial", Font.PLAIN, size + 5));
          break;
          case 45:
          if (size > 5) label.setFont(new Font("Arial", Font.PLAIN, size - 5));
          break;
        }
      }
    });

    c.setFocusable(true);
    c.requestFocus();
    setSize(400, 400);
    setLocationRelativeTo(null);
    setVisible(true);
  }
  public static void main(String[] args) {
    new java10_5();
  }
}

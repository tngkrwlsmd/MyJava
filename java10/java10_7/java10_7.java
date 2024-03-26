package java10.java10_7;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class java10_7 extends JFrame {
  private JLabel label = new JLabel("Love Java");
  public java10_7() {
    setTitle("마우스 휠을 굴려 폰트 크기 조절");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Container c = getContentPane();
    c.setLayout(new FlowLayout());
    label.setFont(new Font("Arial", Font.PLAIN, 10));
    c.add(label);

    label.addMouseWheelListener(new MouseWheelListener() {
      @Override
      public void mouseWheelMoved(MouseWheelEvent e) {
        int n = e.getWheelRotation();
        Font font = label.getFont();
        int size = font.getSize();
        if (n > 0) label.setFont(new Font("Arail", Font.PLAIN, size + 5));
        else label.setFont(new Font("Arial", Font.PLAIN, size - 5));
      }
    });

    setSize(400, 400);
    setLocationRelativeTo(null);
    setVisible(true);
    c.setFocusable(true);
    c.requestFocus();
  }

  public static void main(String[] args) {
    new java10_7();
  }
}

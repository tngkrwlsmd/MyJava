package java10.java10_2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class java10_2 extends JFrame {
  public java10_2() {
    setTitle("드래깅동안 YELLOW");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Container c = getContentPane();
    c.setBackground(Color.GREEN);

    c.addMouseMotionListener(new MouseMotionAdapter() {
      @Override
      public void mouseDragged(MouseEvent e) {
        Container c = (Container)e.getSource();
        c.setBackground(Color.YELLOW);
      }
    });

    c.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseReleased(MouseEvent e) {
        Container c = (Container)e.getSource();
        c.setBackground(Color.GREEN);
      }
    });

    setSize(300, 300);
    setVisible(true);
    c.setFocusable(true);
    c.requestFocus();
  }
  public static void main(String[] args) {
    new java10_2();
  }
}

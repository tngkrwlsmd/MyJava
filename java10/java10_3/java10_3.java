package java10.java10_3;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class java10_3 extends JFrame {
  private JLabel label = new JLabel("Love Java");
  
  public java10_3() {
    setTitle("Left 키로 문자열 교체");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Container c = getContentPane();
    c.setLayout(new FlowLayout());
    c.add(label);

    c.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
          case KeyEvent.VK_LEFT:
          StringBuffer stringBuffer = new StringBuffer(label.getText());
          stringBuffer.reverse();
          label.setText(stringBuffer.toString());
          break;
          default: break;
        }
      }
    });

    c.setFocusable(true);
    c.requestFocus();
    setSize(300, 300);
    setLocationRelativeTo(null); //컴포넌트 중앙배치
    setVisible(true);
  }

  public static void main(String[] args) {
    new java10_3();
  }
}

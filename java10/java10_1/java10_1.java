package java10.java10_1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class java10_1 extends JFrame{
  private JLabel label = new JLabel("사랑해");

  public java10_1() {
    setTitle("마우스 올리기 내리기");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Container c = getContentPane();
    c.setLayout(new FlowLayout());
    c.add(label);

    label.addMouseListener(new MouseAdapter() {
      @Override
      //마우스가 컴포넌트 내부로 진입할 때
      public void mouseEntered(MouseEvent e) {
        JLabel change = (JLabel)e.getSource();
        change.setText("Love Java");
      }

      @Override
      //컴포넌트 외부로 나갈 때
      public void mouseExited(MouseEvent e) {
        JLabel change = (JLabel)e.getSource();
        change.setText("사랑해");
      }
    });

    setSize(300, 300);
    setVisible(true);
    c.setFocusable(true);
    c.requestFocus();
  }
  public static void main(String[] args) {
    new java10_1();
  }
}

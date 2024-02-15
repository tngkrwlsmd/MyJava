package java10;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClickAndDoubleClicked extends JFrame {
  public ClickAndDoubleClicked() {
    setTitle("Click and DoubleClick 예제");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Container c = getContentPane();
    c.addMouseListener(new MyMouseListener());
    setSize(300, 200);
    setVisible(true);
  }

  private class MyMouseListener extends MouseAdapter {
    public void mouseClicked(MouseEvent e) {
      if (e.getClickCount() == 2) {
        int r =(int)(Math.random() * 256);
        int g =(int)(Math.random() * 256);
        int b =(int)(Math.random() * 256);

        Component c = (Component)e.getSource(); //마우스가 클릭된 컴포넌트
        c.setBackground(new Color(r, b, g));
      }
    }
  }

  public static void main(String[] args) {
    new ClickAndDoubleClicked();
  }
}

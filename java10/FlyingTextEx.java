package java10;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class FlyingTextEx extends JFrame {
  private final int FLYING_UNIT = 10;
  private JLabel la = new JLabel("Hello");

  FlyingTextEx() {
    setTitle("상, 하, 좌, 우 키를 이용하여 텍스트 움직이기");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Container c = getContentPane();
    c.setLayout(null);
    c.addKeyListener(new MykeyListener());
    la.setLocation(50, 50);
    la.setSize(100, 20);
    c.add(la);

    setSize(300, 300);
    setVisible(true);

    c.setFocusable(true);
    c.requestFocus();

    //포커스를 잃은 경우, 마우스를 클릭하여 다시 지정하는 코드
    c.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        Component com = (Component)e.getSource();
        com.setFocusable(true);
        com.requestFocus();
      }
    });
  }

  private class MykeyListener extends KeyAdapter {
    public void keyPressed(KeyEvent e) {
      int keyCode = e.getKeyCode();

      switch (keyCode) {
        case KeyEvent.VK_UP: 
        if (la.getY() > 0) la.setLocation(la.getX(), la.getY() - FLYING_UNIT); break;
        case KeyEvent.VK_DOWN: 
        if (la.getY() < 240) la.setLocation(la.getX(), la.getY() + FLYING_UNIT); break;
        case KeyEvent.VK_LEFT: 
        if (la.getX() > 0) la.setLocation(la.getX() - FLYING_UNIT, la.getY()); break;
        case KeyEvent.VK_RIGHT:
        if (la.getX() < 250) la.setLocation(la.getX() + FLYING_UNIT, la.getY()); break;
      }
    }
  }

  public static void main(String[] args) {
    new FlyingTextEx();
  }
}

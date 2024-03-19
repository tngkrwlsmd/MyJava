package mobileJava.java4;
import java.awt.event.*;
import javax.swing.*;

class MyFrame3 extends JFrame {
    private final int FLYING_UNIT = 10;
    private JLabel label = new JLabel("자동차(이미지 대체)");

    public MyFrame3() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.addKeyListener(new MykeyListener());
        label.setLocation(0, 0);
        label.setSize(120, 20);
        this.add(label);

        setVisible(true);
        setSize(300, 300);
    }

    private class MykeyListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
          int keyCode = e.getKeyCode();
    
          switch (keyCode) {
            case KeyEvent.VK_UP: 
            if (label.getY() > 0) label.setLocation(label.getX(), label.getY() - FLYING_UNIT); break;
            case KeyEvent.VK_DOWN: 
            if (label.getY() < 240) label.setLocation(label.getX(), label.getY() + FLYING_UNIT); break;
            case KeyEvent.VK_LEFT: 
            if (label.getX() > 0) label.setLocation(label.getX() - FLYING_UNIT, label.getY()); break;
            case KeyEvent.VK_RIGHT:
            if (label.getX() < 170) label.setLocation(label.getX() + FLYING_UNIT, label.getY()); break;
          }
        }
      }
}

public class ActionEventTest3 {
    public static void main(String[] args) {
        new MyFrame3();
    }
}

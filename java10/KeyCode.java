package java10;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class KeyCodeEx extends JFrame {
  private JLabel la = new JLabel();

  KeyCodeEx() {
    setTitle("Key Code 예제 : F1키 : 초록색, %키 : 노란색");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Container c = getContentPane();
    c.addKeyListener(new MykeyListener());
    c.add(la);
    setSize(300, 150);
    setVisible(true);

    c.setFocusable(true);
    c.requestFocus();
  }

  private class MykeyListener extends KeyAdapter {
    public void keyPressed(KeyEvent e) {
      Container contentPane = (Container)e.getSource();

      la.setText(KeyEvent.getKeyText(e.getKeyCode()) + "키가 입력되었음.");
      
      if (e.getKeyChar() == '%') contentPane.setBackground(Color.YELLOW);
      else if (e.getKeyCode() == KeyEvent.VK_F1) contentPane.setBackground(Color.GREEN);
    }
  }

  public static void main(String[] args) {
    new KeyCodeEx();
  }
}
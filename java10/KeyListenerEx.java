package java10;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class KeyListenerEx extends JFrame {
    private JLabel[] keyMessage; //메시지 컴포넌트 배열, 아직 생성하지는 않음

    KeyListenerEx() {
        setTitle("KeyListener 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(new FlowLayout());
        c.addKeyListener(new MykeyListener());

        keyMessage = new JLabel[3];
        keyMessage[0] = new JLabel(" getKeyCode() ");
        keyMessage[1] = new JLabel(" getKeyChar() ");
        keyMessage[2] = new JLabel(" getKeyText() ");

        for (int i = 0; i < keyMessage.length; i++) {
            c.add(keyMessage[i]);
            keyMessage[i].setOpaque(true); //배경 불투명
            keyMessage[i].setBackground(Color.YELLOW);
        }

        setSize(300, 150);
        setVisible(true);

        c.setFocusable(true); //포커스 설정
        c.requestFocus(); // 키 입력 요청
    }

    class MykeyListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            char keyChar = e.getKeyChar();
            keyMessage[0].setText(Integer.toString(keyCode));
            keyMessage[1].setText(Character.toString(keyChar));
            keyMessage[2].setText(KeyEvent.getKeyText(keyCode));
            
            System.out.println("KeyPressed");
        }
        public void keyReleased(KeyEvent e) {
            System.out.println("keyReleased");
        }
        public void keyTyped(KeyEvent e) {
            System.out.println("KeyTyped");
        }
    }

    public static void main(String[] args) {
        new KeyListenerEx();
    }
}

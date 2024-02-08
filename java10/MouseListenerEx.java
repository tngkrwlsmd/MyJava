package java10;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class MouseListenerEx extends JFrame {
    private JLabel la = new JLabel("Hello");
    
    MouseListenerEx() {
        setTitle("Mouse 이벤트 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.addMouseListener(new MyMouseListener());

        c.setLayout(null);
        la.setSize(50, 20);
        la.setLocation(30, 30);
        c.add(la);

        setSize(250, 250);
        setVisible(true);
    }

    private class MyMouseListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            la.setLocation(x, y);
        }

        //Adapter 클래스를 상속받은 경우, 아래 메소드는 구현할 필요가 없음
        public void mouseReleased(MouseEvent e) {}
        public void mouseClicked(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
    }
    public static void main(String[] args) {
        new MouseListenerEx();
    }
}

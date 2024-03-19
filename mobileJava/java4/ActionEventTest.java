package mobileJava.java4;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MyFrame1 extends JFrame {
    private JButton button1, button2;
    private JPanel panel;

    public MyFrame1() {
        setSize(300, 200);
        setTitle("이벤트 예제");
        
        panel = new JPanel();
        button1 = new JButton("노란색");
        button1.addActionListener(new MyListener());
        panel.add(button1);
        button2 = new JButton("핑크색");
        button2.addActionListener(new MyListener());
        panel.add(button2);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(panel);
        setVisible(true);
    }

    private class MyListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == button1) {
                panel.setBackground(Color.YELLOW);
            }
            else if (e.getSource() == button2) {
                panel.setBackground(Color.PINK);
            }
        }
    }
}

public class ActionEventTest {
    public static void main(String[] args) {
        new MyFrame1();
    }
}

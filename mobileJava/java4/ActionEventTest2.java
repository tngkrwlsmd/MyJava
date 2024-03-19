package mobileJava.java4;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MyFrame2 extends JFrame {
    private JLabel label1, label2;
    private JPanel panel;
    private JButton button1, button2, button3;

    public MyFrame2() {
        setTitle("가위, 바위, 보");
        setLayout(new BorderLayout());

        label1 = new JLabel("아래의 버튼 중에서 하나를 클릭하시오!");
        label2 = new  JLabel("결과");
        add(label1, BorderLayout.NORTH);
        add(label2, BorderLayout.SOUTH);

        panel = new JPanel(new GridLayout(1, 3, 0, 0));
        button1 = new JButton("0: 가위");
        button2 = new JButton("1: 바위");
        button3 = new JButton("2: 보");
        button1.addActionListener(new MyGame());
        button2.addActionListener(new MyGame());
        button3.addActionListener(new MyGame());
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        add(panel, BorderLayout.CENTER);

        setSize(400, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private class MyGame implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int num = (int)(Math.random() * 3); //컴퓨터 선택
            if (e.getSource() == button1) {
                if (num == 0) label2.setText("인간: 0 컴퓨터: 0 무승부");
                else if (num == 1) label2.setText("인간: 0 컴퓨터 1 컴퓨터 승리");
                else if (num == 2) label2.setText("인간: 0 컴퓨터 2 인간 승리");
            }
            else if (e.getSource() == button2) {
                if (num == 0) label2.setText("인간: 1 컴퓨터: 0 인간 승리");
                else if (num == 1) label2.setText("인간: 1 컴퓨터 1 무승부");
                else if (num == 2) label2.setText("인간: 1 컴퓨터 2 컴퓨터 승리");
            }
            else if (e.getSource() == button3) {
                if (num == 0) label2.setText("인간: 2 컴퓨터: 0 컴퓨터 승리");
                else if (num == 1) label2.setText("인간: 2, 컴퓨터 1 인간 승리");
                else if (num == 2) label2.setText("인간: 2 컴퓨터 2 무승부");
            }
        }
    }
}

public class ActionEventTest2 {
    public static void main(String[] args) {
        new MyFrame2();
    }
}

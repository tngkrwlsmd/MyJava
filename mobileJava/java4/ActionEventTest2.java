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
            JButton button = (JButton) e.getSource();
            int computerChoice = (int)(Math.random() * 3); //컴퓨터 선택
            int userChoice = -1;

            if (button == button1) userChoice = 0;
            else if (button == button2) userChoice = 1;
            else if (button == button3) userChoice = 2;

            String result = getResult(userChoice, computerChoice);
            label2.setText(result);
        }
    }

    private String getResult(int userChoice, int computerChoice) {
        String result;

        if (userChoice == computerChoice) result = "무승부";
        else if ((userChoice == 0 && computerChoice == 2) ||
                 (userChoice == 1 && computerChoice == 0) ||
                 (userChoice == 2 && computerChoice == 1)) { result = "인간 승리"; }
        else result = "컴퓨터 승리";

        return "인간: " + userChoice + ", 컴퓨터: " + computerChoice + ", 결과: " + result;
    }

}

public class ActionEventTest2 {
    public static void main(String[] args) {
        new MyFrame2();
    }
}

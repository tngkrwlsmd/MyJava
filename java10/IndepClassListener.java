package java10;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class IndepClassListener extends JFrame {
    IndepClassListener() {
        setTitle("Action 이벤트 리스너 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new FlowLayout());
        JButton btn = new JButton("Action");
        btn.addActionListener(new MyActionListener());
        c.add(btn); //컨텐트팬에 버튼을 추가

        setSize(350, 150);
        setVisible(true);
    }
    public static void main(String[] args) {
        new IndepClassListener();
    }
}

class MyActionListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton)e.getSource(); //이벤트 소스 버튼 알아내기
        if (b.getText().equals("Action")) b.setText("액션");
        else b.setText("Action");
    }
}
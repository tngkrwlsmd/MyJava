package java9;
import javax.swing.*;

class MyFrame extends JFrame {
    MyFrame() {
        setTitle("300x300 스윙 프레임 만들기");
        setSize(300, 300);
        setVisible(true); //프레임 출력
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //윈도우 종료시, 프로그램도 종료
    }

    public static void main(String[] args) {
        new MyFrame();
    }
}
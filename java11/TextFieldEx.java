package java11;
import javax.swing.*;
import java.awt.*;

public class TextFieldEx extends JFrame {
  public TextFieldEx() {
    setTitle("텍스트 필드 만들기 예제");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Container c = getContentPane();
    c.setLayout(new FlowLayout());

    c.add(new JLabel("이름 "));
    c.add(new JTextField(20)); //열 개수 20
    c.add(new JLabel("학과 "));
    c.add(new JTextField("컴퓨터공학과", 20)); //"컴퓨터공학과"는 초기 설정
    c.add(new JLabel("주소 "));
    c.add(new JTextField("서울시 ...", 20));

    setSize(300, 200);
    setVisible(true);
  }

  public static void main(String[] args) {
    new TextFieldEx();
  }
}

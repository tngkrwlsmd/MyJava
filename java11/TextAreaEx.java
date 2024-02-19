package java11;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TextAreaEx extends JFrame {
  private JTextField tf = new JTextField(20);
  private JTextArea ta = new JTextArea(7, 20);

  public TextAreaEx() {
    setTitle("텍스트 영역 만들기 예제");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Container c = getContentPane();
    c.setLayout(new FlowLayout());

    c.add(new JLabel("입력 후 <Enter> 키를 입력하세요."));
    c.add(tf);
    c.add(new JScrollPane(ta));

    tf.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JTextField t = (JTextField)e.getSource();
        ta.append(t.getText() + "\n"); //텍스트 필드의 문자열을 텍스트 영역 창에 추가
        t.setText("");
      }
    });

    setSize(300, 300);
    setVisible(true);
  }

  public static void main(String[] args) {
    new TextAreaEx();
  }
}

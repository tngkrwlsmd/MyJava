package java11;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class JComponentEx extends JFrame {
  public JComponentEx() {
    setTitle("JComponent의 공통 메소드 예제");
    Container c = getContentPane();
    c.setLayout(new FlowLayout());
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JButton b1 = new JButton("Magenta/Yellow Button");
    JButton b2 = new JButton("  Dizabled Button ");
    JButton b3 = new JButton("getX(), getY()");

    b1.setBackground(Color.YELLOW); //배경색
    b1.setForeground(Color.MAGENTA); //글자색
    b1.setFont(new Font("Arial", Font.ITALIC, 20)); //폰트
    b2.setEnabled(false); //버튼 비활성화
    b3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JButton b= (JButton)e.getSource();
        JComponentEx frame = (JComponentEx)b.getTopLevelAncestor(); //b의 최상위 스윙 프레임 얻기
        frame.setTitle(b.getX() + ", " + b.getY());
      }
    });

    c.add(b1); c.add(b2); c.add(b3);

    setSize(260, 200);
    setVisible(true);
  }

  public static void main(String[] args) {
    new JComponentEx();
  }
}

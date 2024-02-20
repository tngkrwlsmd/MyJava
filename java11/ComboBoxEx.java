package java11;
import javax.swing.*;
import java.awt.*;

public class ComboBoxEx extends JFrame {
  private String[] fruits = {"apple", "banana", "kiwi", "mango", "pear", "peach", "berry", "strawberry", "blackberry"};
  private String[] names = {"kitae", "jeamoon", "hyosoo", "namyun"};

  public ComboBoxEx() {
    setTitle("콤보박스 만들기 예제");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Container c = getContentPane();
    c.setLayout(new FlowLayout());

    //fruits에 대한 콤보박스 생성
    JComboBox<String> strCombo = new JComboBox<>(fruits);
    c.add(strCombo);

    JComboBox<String> nameCombo = new JComboBox<>();
    for (int i = 0; i < names.length; i++) {
      nameCombo.addItem(names[i]); //콤보박스에 문자열 삽입
    }

    c.add(nameCombo);
    setSize(300, 300);
    setVisible(true);
  }

  public static void main(String[] args) {
    new ComboBoxEx();
  }
}

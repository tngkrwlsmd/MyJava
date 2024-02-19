package java11;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CheckBoxItemEventEx extends JFrame {
  private JCheckBox[] fruits = new JCheckBox[3];
  private String[] names = {"사과", "배", "체리"};
  private JLabel sumLabel;

  public CheckBoxItemEventEx() {
    setTitle("체크박스와 ItemEvent 예제");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Container c = getContentPane();
    c.setLayout(new FlowLayout());
    c.add(new JLabel("사과 100원, 배 500원, 체리 20000원"));

    MyItemListener listener = new MyItemListener();
    for (int i = 0; i < fruits.length; i++) {
      fruits[i] = new JCheckBox(names[i]); //names[]의 문자열로 체크박스 생성
      fruits[i].setBorderPainted(true); //체크박스 외곽선 생성
      c.add(fruits[i]); //컨탠트팬에 체크박스 삽입
      fruits[i].addItemListener(listener); //체크박스에 아이템 리스너 등록
    }

    sumLabel = new JLabel("현재 0 원입니다.");
    c.add(sumLabel);
    setSize(250, 200);
    setVisible(true);
  }

  private class MyItemListener implements ItemListener {
    private int sum = 0;

    public void itemStateChanged(ItemEvent e) { //아이템 상태의 변화
      if (e.getStateChange() == ItemEvent.SELECTED) { //선택된 경우
        if (e.getItem() == fruits[0]) sum += 100;
        else if (e.getItem() == fruits[1]) sum += 200;
        else if (e.getItem() == fruits[2]) sum += 20000; 
      }
      else {
        if (e.getItem() == fruits[0]) sum -= 100;
        else if (e.getItem() == fruits[1]) sum -= 200;
        else if (e.getItem() == fruits[2]) sum -= 20000;
      }
      sumLabel.setText("현재 " + sum + " 원입니다.");
    }
  }

  public static void main(String[] args) {
    new CheckBoxItemEventEx();
  }

}

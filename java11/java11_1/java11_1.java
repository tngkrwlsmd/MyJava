package java11.java11_1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class java11_1 extends JFrame{
  public java11_1() {
    setTitle("CheckBox Practice Frame");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new FlowLayout());

    JCheckBox ck1 = new JCheckBox("버튼 비활성화");
    JCheckBox ck2 = new JCheckBox("버튼 감추기");
    JButton btn = new JButton("test button");
    add(ck1); add(ck2); add(btn);

    ck1.addItemListener(new ItemListener() {
      @Override
      public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) btn.setEnabled(false);
        else btn.setEnabled(true);
      }
    });

    ck2.addItemListener(new ItemListener() {
      @Override
      public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.SELECTED) btn.setVisible(false);
        else btn.setVisible(true);
      }
    });

    setSize(300, 200);
    setLocationRelativeTo(null);
    setVisible(true);
    setFocusable(true);
    requestFocus();
  }

  public static void main(String[] args) {
    new java11_1();
  }
}

package java11.java11_5;
import javax.swing.*;
import javax.swing.event.*; // ChangeEvent 포함
import java.awt.*;

public class java11_5 extends JFrame{
  public java11_5() {
    setTitle("text");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new FlowLayout());

    JSlider js = new JSlider(JSlider.HORIZONTAL, 100, 200, 100);
    js.setMajorTickSpacing(20); // 큰 눈금 간격
    js.setPaintLabels(true); // 슬라이더 라벨 표현
    js.setPaintTicks(true); // 슬라이더 눈금 표현

    JLabel la = new JLabel();
    la.setHorizontalAlignment(JLabel.CENTER);
    la.setOpaque(true);
    la.setBackground(Color.GREEN);
    la.setText(Integer.toString(js.getValue()));
    add(js); add(la);
    
    js.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        JSlider s = (JSlider) e.getSource();
        la.setText(Integer.toString(s.getValue()));
      }
    });

    setSize(300, 300);
    setLocationRelativeTo(null);
    setVisible(true);
    setFocusable(true);
    requestFocus();
  }

  public static void main(String[] args) {
    new java11_5();
  }
}

package java11.java11_6;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class java11_6 extends JFrame {
  private JSlider js = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
  private JTextArea ja = new JTextArea(null, 10, 25);
  public java11_6() {
    setTitle("test");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new FlowLayout());

    js.setPaintLabels(true);
    js.setPaintTicks(true);
    js.setPaintTrack(true);
    js.setMajorTickSpacing(20);
    js.setMinorTickSpacing(5);
    ja.setText("");

    ja.addKeyListener(new KeyAdapter() {
      @Override
      public void keyTyped(KeyEvent e) {
        String str = ja.getText();
        int length = str.length();
        js.setValue(length); // 글자가 입력될 때마다 손잡이가 움직임
      }
    });

    js.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        JSlider s = (JSlider)e.getSource();
        String str = ja.getText();
        int length = str.length();
        if (s.getValue() > length) s.setValue(length);
        else ja.replaceRange("", s.getValue(), length);
      }
    });
    add(ja); add(js);

    setSize(300, 300);
    setLocationRelativeTo(null);
    setVisible(true);
    setFocusable(true);
    requestFocus();
  }

  public static void main(String[] args) {
    new java11_6();
  }
}

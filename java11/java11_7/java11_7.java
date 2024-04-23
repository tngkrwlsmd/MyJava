package java11.java11_7;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class java11_7 extends JFrame {
  private JSlider js = new JSlider(1, 100, 20);
  private JLabel la = new JLabel("I Love Java");

  public java11_7() {
    setTitle("test");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    js.setPaintLabels(true);
    js.setPaintTicks(true);
    js.setPaintTrack(true);
    js.setMajorTickSpacing(20);
    js.setMinorTickSpacing(5);
    la.setHorizontalAlignment(SwingConstants.CENTER);
    js.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        JSlider s = (JSlider) e.getSource();
        int value = s.getValue();
        la.setFont(new Font("TimeRoman", Font.PLAIN, value));
      }
    });
    add(js, BorderLayout.NORTH); add(la, BorderLayout.CENTER);

    setSize(300, 300);
    setLocationRelativeTo(null);
    setVisible(true);
    setFocusable(true);
    requestFocus();
  }

  public static void main(String[] args) {
    new java11_7();
  }
}

package java11.java11_2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class java11_2 extends JFrame {
  public java11_2() {
    setTitle("test");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new FlowLayout());

    JTextField tf = new JTextField(16);
    JComboBox<String> cb = new JComboBox<String>();
    add(tf); add(cb);

    tf.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JTextField jtf = (JTextField) e.getSource();
        cb.addItem(jtf.getText());
        jtf.setText("");
      }
    });

    setSize(400, 400);
    setLocationRelativeTo(null);
    setVisible(true);
    setFocusable(true);
    requestFocus();
  }

  public static void main(String[] args) {
    new java11_2();
  }
}

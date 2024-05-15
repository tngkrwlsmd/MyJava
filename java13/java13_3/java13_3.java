package java13.java13_3;
import java.awt.*;
import javax.swing.*;
import java.util.Calendar;

public class java13_3 extends JFrame {
  JLabel la = new JLabel();

  class Timer implements Runnable {
    @Override
    public void run() {
      while (true) {
        Calendar today = Calendar.getInstance();
        String hour = Integer.toString(today.get(Calendar.HOUR_OF_DAY));
        String minute = Integer.toString(today.get(Calendar.MINUTE));
        String second = Integer.toString(today.get(Calendar.SECOND));
        la.setText(hour + ":" + minute + ":" + second);
        repaint();
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          return;
        }
      }
    }
  }

  public java13_3() {
    Thread th = new Thread(new Timer());
    th.start();
    
    add(la, BorderLayout.CENTER);
    la.setHorizontalAlignment(SwingConstants.CENTER);
    la.setFont(new Font("나눔", Font.BOLD, 40));
    setTitle("디지털 시계 만들기");
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(400, 400);
  }

  public static void main(String[] args) {
    new java13_3();
  }
}

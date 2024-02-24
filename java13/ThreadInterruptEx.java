package java13;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class TimerRunnable implements Runnable {
  private JLabel timerLabel;

  public TimerRunnable(JLabel timerLabel) {
    this.timerLabel = timerLabel;
  }

  @Override
  public void run() {
    int n = 0;
    while (true) {
      timerLabel.setText(Integer.toString(n));
      n++;
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        return;
      }
    }
  }
}

public class ThreadInterruptEx extends JFrame {
  private Thread th;
  public ThreadInterruptEx() {
    setTitle("ThreadInterruptEx 예제");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Container c = getContentPane();
    c.setLayout(new FlowLayout());

    JLabel timerLabel = new JLabel();
    timerLabel.setFont(new Font("Gothic", Font.ITALIC, 80));

    TimerRunnable runnable = new TimerRunnable(timerLabel);
    th = new Thread(runnable);
    c.add(timerLabel);

    JButton btn = new JButton("kill Timer");
    btn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        th.interrupt(); //강제 인터럽트
        JButton btn = (JButton)e.getSource();
        btn.setEnabled(false); //버튼 비활성화
      }
    });
    c.add(btn);

    setSize(300, 170);
    setVisible(true);

    th.start();
  }

  public static void main(String[] args) {
    new ThreadInterruptEx();
  }
}
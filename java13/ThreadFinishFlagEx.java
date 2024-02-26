package java13;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class RandomThread extends Thread {
  private Container contentPane;
  private boolean flag = false; //스레드 종료 명령 변수, true일 때 종료

  public RandomThread(Container contentPane) {
    this.contentPane = contentPane;
  }

  public void finish() {
    flag = true;
  }

  @Override
  public void run() {
    while (true) {
      // 0.3초 간격으로 새로운 java 문자열이 생성 및 배치
      int x = ((int)(Math.random() * contentPane.getWidth()));
      int y = ((int)(Math.random() * contentPane.getHeight()));
      JLabel label = new JLabel("java");
      label.setSize(80, 30);
      label.setLocation(x, y);
      contentPane.add(label);
      contentPane.repaint(); //추가된 레이블을 다시 보이게 함
      try {
        Thread.sleep(300);
        if (flag == true) {
          contentPane.removeAll(); //모든 레이블 제거
          label = new JLabel("finish");
          label.setSize(80, 30);
          label.setLocation(100, 120);
          label.setForeground(Color.RED);
          contentPane.add(label);
          contentPane.repaint();
          return; //스레드 종료
        }
      } catch (InterruptedException e) {
        return;
      }
    }
  }
}

public class ThreadFinishFlagEx extends JFrame {
  private RandomThread th;

  public ThreadFinishFlagEx() {
    setTitle("ThreadFinishFlagEx 예제");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Container c = getContentPane();
    c.setLayout(null);

    c.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        th.finish(); //마우스를 클릭하면 스레드 종료
      }
    });
    setSize(300, 200);
    setVisible(true);

    th = new RandomThread(c);
    th.start();
  }
  
  public static void main(String[] args) {
    new ThreadFinishFlagEx();
  }
}

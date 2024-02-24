package java13;
import javax.swing.*;
import java.awt.*;

class FlickeringLabel extends JLabel implements Runnable {
  private long delay; //배경색이 바뀌는 지연시간

  public FlickeringLabel(String text, long delay) {
    super(text); //JLabel 생성자 호출
    this.delay = delay;
    setOpaque(true);

    //Runnable을 상속받아 run()을 구현한 클래스임을 명시(this)
    Thread th = new Thread(this);
    th.start();
  }

  @Override
  public void run() {
    int n = 0;
    while (true) {
      if (n == 0) setBackground(Color.YELLOW);
      else setBackground(Color.GREEN);
      if (n == 0) n = 1;
      else n = 0;
      try {
        Thread.sleep(delay);
      } catch (InterruptedException e) {
        return;
      }
    }
  }
}

public class FlickeringLabelEx extends JFrame {
  public FlickeringLabelEx() {
    setTitle("FlickeringLabelEx 예제");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Container c = getContentPane();
    c.setLayout(new FlowLayout());

    //0.5초 주기로 배경색 변경
    FlickeringLabel fLabel = new FlickeringLabel("깜박", 500);
    JLabel label = new JLabel("안깜빡");

    //0.3초 주기로 배경색 변경
    FlickeringLabel fLabel2 = new FlickeringLabel("여기도 깜박", 300);

    c.add(fLabel);
    c.add(label);
    c.add(fLabel2);

    setSize(300, 150);
    setVisible(true);
  }

  public static void main(String[] args) {
    new FlickeringLabelEx();
  }
}

package java13;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//누르면 보라색 bar가 오른쪽으로 증가, 가만히 있으면 0.2초 간격으로 감소

class MyLabel extends JLabel {
  private int barSize = 0; //현재 그려진 바의 크기
  private int maxBarSize;//바의 최대 크기

  public MyLabel(int maxBarSize) {
    this.maxBarSize = maxBarSize;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(Color.MAGENTA);
    int width = (int)(((double)(this.getWidth())) / maxBarSize * barSize);
    if (width == 0) return;
    g.fillRect(0, 0, width, this.getHeight());
  }

  synchronized public void fill() {
    if (barSize == maxBarSize) { //최대로 색칠한 경우
      try {
        wait(); //바의 크기가 최대면, 줄어들 때까지 대기
      } catch (InterruptedException e) {
        return;
      }
    }
    barSize++;
    repaint(); //다시 호출
    notify(); //기다리는 스레드 깨우기
  }

  synchronized public void consume() {
    if (barSize == 0) {
      try {
        wait(); //바의 크기가 0보다 커질 때까지 대기
      } catch (InterruptedException e) {
        return;
      }
    }
    barSize--;
    repaint();
    notify();
  }
}

class ConsumerThread extends Thread {
  private MyLabel bar;

  public ConsumerThread(MyLabel bar) {
    this.bar = bar;
  }

  @Override
  public void run() {
    while (true) {
      try {
        sleep(200);
        bar.consume(); // 0.2초마다 바의 크기를 1씩 감소
      } catch (InterruptedException e) {
        return;
      }
    }
  }
}

public class TabAndThreadEx extends JFrame {
  private MyLabel bar = new MyLabel(100); //바의 최대 크기 100

  public TabAndThreadEx(String title) {
    setTitle(title);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Container c = getContentPane();
    c.setLayout(null);
    bar.setBackground(Color.ORANGE);
    bar.setOpaque(true);
    bar.setLocation(20, 50);
    bar.setSize(300, 20);
    c.add(bar);

    //키를 누를 때마다 보라색 바 크기가 증가
    c.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        bar.fill();
      }
    });

    setSize(350, 200);
    setVisible(true);

    c.setFocusable(true);
    c.requestFocus();
    ConsumerThread th = new ConsumerThread(bar);
    th.start();
  }

  public static void main(String[] args) {
    new TabAndThreadEx("아무 키나 눌러 바 채우기");
  }
}

package java13.java13_1;
import javax.swing.*;
import java.util.Scanner;

public class java13_1 extends JFrame {
  class MyThread implements Runnable {
    @Override
    public void run() {
      System.out.println("스레드 실행 시작");
      for (int i = 0; i <= 10; i++) {
        System.out.print(i + " ");
      }
      System.out.println();
      System.out.println("스레드 종료");
    }
  }

  public java13_1() {
    Scanner scanner = new Scanner(System.in, "cp949");
    System.out.print("아무 키나 입력 >> ");
    scanner.nextLine();
    scanner.close();
    Thread th = new Thread(new MyThread());
    th.start();
  }

  public static void main(String[] args) {
    new java13_1();
  }
}

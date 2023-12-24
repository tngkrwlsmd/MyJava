package java2.java2_3;
import java.util.Scanner;

class java2_3 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("금액을 입력하시오 >> ");
    int money = scanner.nextInt();
    int won[] = {50000, 10000, 1000, 100, 50, 10, 1};
    String wons[] = {"오만", "만", "천", "백", "오십", "백", "일"};

    for (int i = 0; i < won.length; i++) {
      if (i < 3) System.out.println(wons[i] + "원권 " + money/won[i] + "매");
      else System.out.println(wons[i] + "원 " + money/won[i] + "개");
      money %= won[i];
    }

    scanner.close();
  }
}

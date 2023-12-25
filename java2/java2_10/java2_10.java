package java2.java2_10;
import java.util.Scanner;

class java2_10 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int circle[][] = new int[2][3];
    String circleString[] = {"첫", "두"};

    for (int i = 0; i < circle.length; i++) {
      System.out.print(circleString[i] + "번째 원의 중심과 반지름 입력 >> ");
      for (int j = 0; j < circle[i].length; j++) circle[i][j] = scanner.nextInt();
    }

    boolean isOverlap = isCircleOverlap(circle[0][0], circle[0][1], circle[0][2],
     circle[1][0], circle[1][1], circle[1][2]);

    if (isOverlap) System.out.println("두 원은 겹칩니다.");
    else System.out.println("두 원은 겹치지 않습니다.");
    scanner.close();
  }

  public static boolean isCircleOverlap(int x1, int y1, int r1, int x2, int y2, int r2) {
    double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    if (distance <= r1 + r2) return true;
    else return false;
  }
}

package java2.java2_8;
import java.util.Scanner;

class java2_8 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("점 (x1, y1)의 좌표를 입력하시오 >> ");
    int x1 = scanner.nextInt(), y1 = scanner.nextInt();

    System.out.print("점 (x2, y2)의 좌표를 입력하시오 >> ");
    int x2 = scanner.nextInt(), y2 = scanner.nextInt();

    if (inRect(x1, y1, 100, 100, 200, 200)) {
      if (inRect(x2, y2, 100, 100, 200, 200)) System.out.println("충돌하지 않습니다.");
      else System.out.println("충돌합니다.");
    }
    else {
      if (inRect(x2, y2, 100, 100, 200, 200)) System.out.println("충돌합니다.");
      else System.out.println("완전히 포함하거나 충돌하지 않습니다.");
    }
    scanner.close();
  }

  public static boolean inRect (int x, int y, int rectx1, int recty1, int rectx2, int recty2) {
    if ((x >= rectx1 && x <= rectx2) && (y >= recty1 && y <= recty2)) return true;
    else return false;
  }
}

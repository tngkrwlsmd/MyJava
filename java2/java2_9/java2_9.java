package java2.java2_9;
import java.util.Scanner;

class java2_9 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("원의 중심과 반지름 입력 >> ");
    int ox = scanner.nextInt(), oy = scanner.nextInt();
    double r = scanner.nextDouble();

    System.out.print("점 입력 >> ");
    int x = scanner.nextInt(), y = scanner.nextInt();

    if (inCircle(ox, oy, x, y, r)) System.out.println("점 (" + x + "," + y + ")는 원 안에 있다.");
    else System.out.println("점 (" + x + "," + y + ")는 원 안에 없다.");
    scanner.close();
  }

  public static boolean inCircle(int x1, int y1, int x2, int y2, double radius) {
    if (Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2)) < radius) return true;
    else return false;
  }
}

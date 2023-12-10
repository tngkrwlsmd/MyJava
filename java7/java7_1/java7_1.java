package java7_1;
import java.util.*;

class java7_1 {
  public static void main(String[] args) {
    Scanner s = new Scanner(System.in, "cp949");
    Vector<Integer> v = new Vector<Integer>();
    System.out.print("정수(-1이 입력될 때까지)>> ");

    while(true) {
      int num = s.nextInt();
      if (num == -1) break;
      v.add(num);
    }

    System.out.println("가장 큰 수는 " + Collections.max(v));
    s.close();
  }
}

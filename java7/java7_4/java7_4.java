package java7_4;
import java.util.*;

class java7_4 {
  public static void main(String[] args) {
    Scanner s = new Scanner(System.in, "cp949");
    Vector<Integer> v = new Vector<Integer>();

    while(true) {
      int sum = 0;
      System.out.print("강수량 입력(0 입력시 종료)>> ");
      int rain = s.nextInt();
      if (rain == 0) break;
      v.add(rain);
      for (int i = 0; i < v.size(); i++) {
        System.out.print(v.get(i) + " ");
        sum += v.get(i);
      }
      System.out.println("\n현재 평균 " + sum/v.size() + "\n");
    }
    
    s.close();
  }
}

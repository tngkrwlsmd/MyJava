package java7_2;
import java.util.*;

class java7_2 {
  public static void main(String[] args) {
    Scanner s = new Scanner(System.in, "cp949");
    ArrayList<Character> c = new ArrayList<Character>();
    System.out.print("6개의 학점을 빈 칸으로 분리 입력(A/B/C/D/F)>> ");
    double result = 0;

    for (int i = 0; i < 6; i++) {
      char ch = s.next().charAt(0);
      c.add(ch);
    }

    for(int i = 0; i < c.size(); i++) {
      char ch = c.get(i);
      if (ch == 'A') result += 4.0;
      else if (ch == 'B') result += 3.0;
      else if (ch == 'C') result += 2.0;
      else if (ch == 'D') result += 1.0;
    }
    
    System.out.println(result/c.size());
    s.close();
  }
}

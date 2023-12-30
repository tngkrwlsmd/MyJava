package java3.java3_14;
import java.util.Scanner;

class java3_14 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in, "cp949");
    String course[] = {"Java", "C++", "HTML5", "컴퓨터구조", "안드로이드"};
    int score[] = {95, 88, 76, 62, 55};
    while (true) {
      System.out.print("과목 이름 >> ");
      String input = scanner.next();
      if (input.equals("그만")) break;

      int index = 0;
      boolean flag = false;
      for (int i = 0; i < course.length; i++) {
        if (input.equals(course[i])) {
          index = i;
          flag = true;
          break;
        }
      }
      if(flag) System.out.println(course[index] + "의 점수는 " + score[index]);
      else System.out.println("없는 과목입니다.");
    }
    scanner.close();
  }
}

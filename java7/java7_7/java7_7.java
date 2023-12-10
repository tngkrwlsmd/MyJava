package java7_7;
import java.util.*;

class java7_7 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in,"cp949");
    HashMap<String, Double> student = new HashMap<String, Double>();
    System.out.println("미래장학금관리시스템입니다.");

    for (int i = 0; i < 5; i++) {
      System.out.print("이름과 학점 >> ");
      String name = scanner.next();
      double grade = scanner.nextDouble();
      student.put(name, grade);
    }

    System.out.print("장학금 선발 학점 기준 입력 >> ");
    double standard = scanner.nextDouble();

    System.out.print("장학생 명단 : ");
    Set<String> set = student.keySet();
    Iterator<String> it = set.iterator();
    
    while (it.hasNext()) {
      String name = it.next();
      double grade = student.get(name);
      if (grade >= standard) {
        System.out.print(name + " ");
      }
    }

    scanner.close();
  }
}

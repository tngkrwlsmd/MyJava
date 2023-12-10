package java7_5;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class StudentExample1 {
  class StudentExample {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in, "cp949");
    ArrayList<Student> studentArrayList = new ArrayList<Student>();
    Student[] student = new Student[4];
    System.out.println("학생 이름, 학과, 학번, 학점평균을 입력하세요.");

    for (int i = 0; i < student.length; i++) {
      System.out.print(">> ");
      String text = scanner.nextLine();
      StringTokenizer st = new StringTokenizer(text,",");
      String name = st.nextToken().trim();
      String major = st.nextToken().trim();
      int num = Integer.parseInt(st.nextToken().trim());
      double average = Double.parseDouble(st.nextToken().trim());
      student[i] = new Student(name, major, num, average);
      studentArrayList.add(student[i]);
    }

    for (int i = 0; i < studentArrayList.size(); i++) {
      System.out.println("------------------------------");
      Student s = studentArrayList.get(i);
      System.out.println("이름: " + s.getName());
      System.out.println("학과: " + s.getMajor());
      System.out.println("학번: " + s.getNum());
      System.out.println("학점평균: " + s.getAverage());
    }
    System.out.println("------------------------------");

    while(true) {
      System.out.print("학생 이름 >> ");
      String name = scanner.next();
      if(name.equals("그만")) break;
      /** ArrayList의 get()의 인자는 int형임  */
      for (int i = 0; i < studentArrayList.size(); i++) {
        Student s = studentArrayList.get(i);
        if (s.getName().equals(name)) {
          System.out.println(s.getName() + ", " + s.getMajor() + ", " +
          s.getNum() + ", " + s.getAverage());
          break;
        }
        else if (i == studentArrayList.size() - 1) {
          System.out.println(name + " 학생은 없습니다.");
        }
      }
    }

    scanner.close();
  }
}
}

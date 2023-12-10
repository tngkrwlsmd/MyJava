package java7_5;
import java.util.*;

public class StudentExample2 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in, "cp949");
    HashMap<String, Student> studentHashMap = new HashMap<String, Student>();
    Student[] student = new Student[4];
    System.out.println("학생 이름, 학과, 학번, 학점평균을 입력하세요.");

    for (int i = 0; i < student.length; i++) {
      System.out.println(">> ");
      String text = scanner.nextLine();
      StringTokenizer st = new StringTokenizer(text, ",");
      String name = st.nextToken().trim();
      String major = st.nextToken().trim();
      int num = Integer.parseInt(st.nextToken().trim());
      double average = Double.parseDouble(st.nextToken().trim());
      student[i] = new Student(name, major, num, average);
      studentHashMap.put(name, student[i]);
    }
    Set<String> set = studentHashMap.keySet();
    Iterator<String> it = set.iterator();

    while(it.hasNext()) {
      String name = it.next();
      System.out.println("----------------------------------");
      Student s = studentHashMap.get(name);
      System.out.println("이름 : " + s.getName());
      System.out.println("학과 : " + s.getMajor());
      System.out.println("학번 : " + s.getNum());
      System.out.println("학점평균 : " + s.getAverage());
    }

    while(true) {
      System.out.print("학생이름 >> ");
      String name = scanner.next();
      if (name.equals("그만")) break;
      Student s = studentHashMap.get(name);
      if (s == null) System.out.println(name + " 학생은 없습니다.");
      else {
        System.out.println(s.getName() + ", " + s.getMajor() + ", " +
          s.getNum() + ", " + s.getAverage());
      }
    }

    scanner.close();
  }
}

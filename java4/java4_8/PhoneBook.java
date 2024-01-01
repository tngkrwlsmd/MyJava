package java4.java4_8;
import java.util.Scanner;

class Phone {
  private String name, tel;
  Phone(String name, String tel) {
    this.name = name;
    this.tel = tel;
  }
  String getName() { return name; }
  String getTel() { return tel; }
}

class PhoneBook {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in, "cp949");
    Phone p[];
    
    System.out.print("인원수 >> ");
    int num = scanner.nextInt();
    p = new Phone[num];

    for (int i = 0; i < p.length; i++) {
      System.out.print("이름과 전화번호(이름과 전화번호는 빈칸없이 입력) >> ");
      String name = scanner.next();
      String tel = scanner.next();
      p[i] = new Phone(name, tel);
    }
    System.out.println("저장되었습니다...");

    while (true) {
      int flag = 0;
      System.out.print("검색할 이름 >> ");
      String name = scanner.next();
      if (name.equals("그만")) {
        System.out.println("프로그램을 종료합니다.");
        break;
      }
      for (int i = 0; i < p.length; i++) {
        if (name.equals(p[i].getName())) {
          flag = 1;
          System.out.println(name + "의 번호는 " + p[i].getTel() + "입니다.");
          break;
        }
      }
      if (flag == 0) System.out.println(name + "은 존재하지 않습니다.");
    }
    scanner.close();
  }
}

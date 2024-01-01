package java4.java4_7;
import java.util.Scanner;

class Day {
   private String work; //하루의 할 일을 나타내는 문자열
   public void set(String work) { this.work = work; }
   public String get() { return work; }
   public void show() {
      if(work == null) System.out.println("없습니다.");
      else System.out.println(work + "입니다.");
   }
}

class MonthSchedule {
  Scanner scanner = new Scanner(System.in, "cp949");
  Day d[];

  MonthSchedule (int day) {
    this.d = new Day[day];
    for (int i = 0; i < d.length; i++) d[i] = new Day();
  }

  void input() {
    System.out.print("날짜(1 ~ 30) >> ");
    int day = scanner.nextInt();
    if (day < 0 || day > 30) {
      System.out.println("날짜를 잘못 입력하였습니다.");
      return;
    }
    System.out.print("할일(빈칸 없이 입력) >> ");
    String work = scanner.next();
    d[day - 1].set(work); // day - 1 : 배열의 index는 실제 날짜보다 1 작음
    System.out.println();
  }

  void view() {
    System.out.print("날짜(1 ~ 30) >> ");
    int day = scanner.nextInt();
    if(day<0 || day>30){
      System.out.println("날짜를 잘못 입력하였습니다.");
      return;
    }
    System.out.print(day + "일의 할 일은 ");
    d[day - 1].show();
    System.out.println();
  }
}

class java4_7 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in, "cp949");
    System.out.println("이번 달 스케쥴 관리 프로그램.");
    MonthSchedule schedule = new MonthSchedule(30);
    while (true) {
      System.out.print("할일(입력: 1, 보기: 2, 끝내기: 3) >> ");
      int choice = scanner.nextInt();
      if (choice == 1) schedule.input();
      else if (choice == 2) schedule.view();
      else if (choice == 3) {
        System.out.println("프로그램을 종료합니다.");
        break;
      }
      else System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
    }
    scanner.close();
  }
}
package java3.java3_16;
import java.util.Scanner;

public class java3_16 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in, "cp949");
    String str[] = {"가위", "바위", "보"};
    System.out.println("컴퓨터와 가위 바위 보 게임을 합니다.");

    while (true) {
      System.out.print("가위 바위 보! >> ");
      String user = scanner.next();
      if (user.equals("그만")) {
        System.out.println("게임을 종료합니다.");
        break;
      }
      String computer = str[(int)(Math.random() * 3)]; // 0 ~ 2

      if(user.equals(computer)) {
        System.out.println("사용자 = " + user + ", 컴퓨터 = " + computer + ", 비겼습니다.");
      }
      else if ((user.equals("가위") && computer.equals("보"))
      || (user.equals("바위") && computer.equals("가위"))
      || (user.equals("보") && computer.equals("바위"))) {
        System.out.println("사용자 = " + user + ", 컴퓨터 = " + computer + ", 사용자가 이겼습니다.");
      }
      else if ((user.equals("바위") && computer.equals("보"))
      || (user.equals("보") && computer.equals("가위"))
      || (user.equals("가위") && computer.equals("바위"))){
        System.out.println("사용자 = " + user + ", 컴퓨터 = " + computer + ", 컴퓨터가 이겼습니다.");
      }
      else System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
    }
    scanner.close();
  }
}

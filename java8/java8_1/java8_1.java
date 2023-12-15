package java8.java8_1;
import java.io.*;
import java.util.*;

public class java8_1 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in,"cp949");
    FileWriter fileWriter = null;
    System.out.println("전화번호 입력 프로그램입니다.");
    try {
      fileWriter = new FileWriter("c:\\java\\java8\\java8_1\\phone.txt");
      while(true) {
        System.out.print("이름 전화번호 : ");
        String text = scanner.nextLine();
        if(text.equals("그만")) break;
        fileWriter.write(text);
        fileWriter.write("\n");
      }
      fileWriter.close();
      System.out.println("c:\\\\java\\\\java8\\\\java8_1\\\\phone.txt에 저장하였습니다.");
    } catch (IOException e) {
      e.printStackTrace(); //예외 정보 출력
    }
    scanner.close();
  }
}

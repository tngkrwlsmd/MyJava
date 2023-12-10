package java8;
import java.io.*;  
import java.util.*;

class java8_3ex {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in, "MS949");
    FileWriter fout = null;
    try {
      fout = new FileWriter("test.txt");
      while(true) {
        String line = scanner.nextLine();
        if (line.length() == 0) break; //아무런 문자도 입력받지 않음
        fout.write(line, 0, line.length()); 
        // 0번째 인덱스부터 line.length()개의 문자를 출력
        // /r: 현재 라인의 맨 앞으로 커서를 이동시키는 역할
        fout.write("\r\n", 0, 2);
      }
      fout.close();
    } catch (IOException e) {
      System.out.println("입출력 오류");
    }
    scanner.close();
  }
}

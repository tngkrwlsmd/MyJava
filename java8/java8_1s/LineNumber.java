package java8.java8_1s;
import java.io.*;
import java.util.Scanner;

class LineNumber {
  public static void main(String[] args) {
    BufferedReader br = null;
    Scanner sin = new Scanner(System.in);
    System.out.print("자바 소스 파일 이름을 입력하세요 >> ");
    String src = sin.nextLine();
    try {
      br = new BufferedReader(new FileReader(src));
      String line;
      int LineNumber = 1;
      while(true) {
        line = br.readLine();
        if (line == null) break; //파일이 비어있음
        System.out.printf("%4d", LineNumber++); //라인 번호를 출력하고 증가시킴
        System.out.println(":" + line); //소스 한 행 출력
        if (br != null) br.close(); //파일 끝까지 읽음
      }
    } catch (FileNotFoundException e) { //파일을 찾을 수 없음
      System.out.println("파일을 찾을 수 없습니다.");
    } catch (IOException e) {
      System.out.println("입출력 오류가 발생했습니다.");
    }
    sin.close();
  }
}
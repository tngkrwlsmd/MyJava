package java8;
import java.io.*;
import java.util.Scanner;

class java8_6ex {
  public static void main(String[] args) {
    FileReader fin = null;
    int c;
    try {
      fin = new FileReader("test2.txt");
      BufferedOutputStream out = new BufferedOutputStream(System.out, 5);
      while ((c = fin.read()) != -1) {
        out.write(c); //크기가 5 이상인 부분은 짜름
      }
      Scanner s = new Scanner(System.in);
      s.nextLine();
      out.flush(); //버퍼에 남은 나머지 문자를 출력
      fin.close();
      out.close();
      s.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

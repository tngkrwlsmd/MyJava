package java8;
import java.io.*;
/**
 * java.io.InputStreamReader : 입력 스트림
 * java.io.FileInputStream : 파일 입력 스트림
 */

class java8_2ex {
  public static void main(String[] args) {
    InputStreamReader in = null;
    FileInputStream fin = null;

    try {
      fin = new FileInputStream("hangul.txt");
      in = new InputStreamReader(fin);
      int c;

      System.out.println("인코딩 문자 집합은 " + in.getEncoding());
      while((c = in.read()) != -1) {
        System.out.print((char)c);
      }
      in.close();
      fin.close();

    } catch (Exception e) {
      System.out.println("입출력 오류");
    }
  }
}

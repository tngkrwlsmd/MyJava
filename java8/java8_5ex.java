package java8;
import java.io.*;

class java8_5ex {
  public static void main(String[] args) {
    byte b[] = new byte[6];
    try {
      FileInputStream fin = new FileInputStream("text.out");
      int n = 0, c;
      while((c = fin.read()) != -1) {
        b[n] = (byte)c;
        n++;
      }
      System.out.println("test.out에서 읽은 배열을 출력합니다.");
      for (int i = 0; i < b.length; i++) System.out.print(b[i] + " ");
      System.out.println();
      fin.close();
    } catch (IOException e) {
      System.out.println("test.out에서 읽지 못했습니다. 경로를 확인해주세요.");
    }
  }
}

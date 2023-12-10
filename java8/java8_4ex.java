package java8;
import java.io.*;

class java8_4ex {
  public static void main(String[] args) {
    byte b[] = {7,51,3,4,-1,24};
    try {
      FileOutputStream fout = new FileOutputStream("text.out");
      for (int i = 0; i < b.length; i++) fout.write(b[i]);
      fout.close();
    } catch (IOException e) {
      System.out.println("text.out에 저장할 수 없었습니다. 경로를 확인해주세요.");
      return;
    }
    System.out.println("text.out을 저장하였습니다.");
  }
}

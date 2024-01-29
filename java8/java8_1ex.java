package java8;
import java.io.*;

class java8_1ex {
  public static void main(String[] args) {
    FileReader fin = null;
    try {
      fin = new FileReader("c:/windows/system.ini");
      int c;
      while ((c = fin.read()) != -1) {
        System.out.print((char)c); //한 문자씩 파일 끝까지 읽기
      }
      fin.close();
    }
    catch (IOException e) { 
      System.out.println("입출력 오류");
    }
  }
}

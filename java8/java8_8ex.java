package java8;
import java.io.*;

class java8_8ex {
  public static void main(String[] args) {
    File src = new File("c:/windows/system.ini");
    File dest = new File("system.txt");
    int c;
    try {
      FileReader fr = new FileReader(src);
      FileWriter fw = new FileWriter(dest);
      while((c = fr.read()) != -1) fw.write((char)c); //문자 하나씩 읽고 쓰기
      fr.close(); fw.close();
      System.out.println(src.getPath() + "를 " + dest.getPath() + "로 복사하였습니다.");
    } catch (IOException e) {
      System.out.println("파일 복사 오류");
    }
  }
}

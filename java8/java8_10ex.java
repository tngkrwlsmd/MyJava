package java8;
import java.io.*;

class java8_10ex {
  public static void main(String[] args) {
    File src = new File("C:/Windows/Web/Wallpaper/Theme1/img1.jpg");
    File dest = new File("C:/java/java8/desert.jpg");
    try {
      FileInputStream fi = new FileInputStream(src);
      FileOutputStream fo = new FileOutputStream(dest);
      byte buf[] = new byte[1024 * 10];
      while(true) {
        int n = fi.read(buf); //버퍼 크기만큼 읽기
        fo.write(buf, 0, n); //buf[0]부터 n 바이트 쓰기
        if (n < buf.length) break;
      }
      fi.close(); fo.close();
      System.out.println(src.getPath() + "를 " + dest.getPath() + "로 복사하였습니다.");
    } catch (IOException e) {
      System.out.println("파일 복사 오류");
    }
  }
}

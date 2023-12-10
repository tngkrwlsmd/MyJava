package java8;
import java.io.*;

class java8_9ex {
  public static void main(String[] args) {
    File src = new File("C:\\Windows\\Web\\Wallpaper\\Theme1\\img1.jpg");
    File dest = new File("copying.jpg");
    int c;
    try {
      FileInputStream fi = new FileInputStream(src);
      FileOutputStream fo = new FileOutputStream(dest);
      while((c = fi.read()) != -1) fo.write((byte)c);
      fi.close(); fo.close();
      System.out.println(src.getPath() + "를 " + dest.getPath() + "로 복사하였습니다.");
    }
    catch (IOException e) {
      System.out.println("파일 복사 오류");
    }
  }
}

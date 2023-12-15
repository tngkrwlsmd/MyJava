package java8.java8_2s;
import java.io.*;

class FileCopy {
  public static void main(String[] args) {
    BufferedInputStream file1 = null;
    BufferedOutputStream file2 = null;

    try {
      file1 = new BufferedInputStream(new FileInputStream("a.jpg"));
      file2 = new BufferedOutputStream(new FileOutputStream("b.jpg"));

      File f = new File("a.jpg");
      long tenPercent = f.length() / 10;
      byte[] b = new byte[1024];
      int numRead = 1;
      long progress = 0;

      while (numRead > 0) { // numRead = 0 내용이 없음
        numRead = file1.read(b, 0, b.length); //0 ~ 파일 끝까지
        if (numRead > 0) {
          file2.write(b, 0, numRead);
          progress += numRead;
          if (progress >= tenPercent) { //10% 지점, 출력 후 다시 0으로 초기화
            System.out.println("*");
            progress = 0;
          }
        }
      }
      if (file1 != null) file1.close();
      if (file2 != null) file2.close();

    } catch (IOException e) {
      System.out.println("입출력 오류가 발생했습니다.");
    }
  }  
}

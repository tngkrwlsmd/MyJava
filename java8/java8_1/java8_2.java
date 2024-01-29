package java8.java8_1;
import java.io.*;

public class java8_2 {
  public static void main(String[] args) {
    try {
      File f = new File ("C/jav/java/java8_/phone.txt");
      BufferedReader br = new BufferedReader(new FileReader(f));
      System.out.println(f.getPath() + "를 출력합니다.");
      String line;
      while((line = br.readLine()) != null) System.out.println(line);
      br.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

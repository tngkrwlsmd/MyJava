package java8.java8_3;
import java.io.*;

public class java8_3 {
  public static void main(String[] args) {
    try {
      File f = new File("c:\\windows\\system.ini");
      BufferedReader br = new BufferedReader(new FileReader(f));
      String line;
      while((line= br.readLine()) != null) System.out.println(line.toUpperCase());
      br.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

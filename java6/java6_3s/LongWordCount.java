package java6.java6_3s;
import java.util.*;

class LongWordCount {
  public static void main(String[] args) {
    Scanner s = new Scanner(System.in, "cp949");
    String str, longestWord = null;
    System.out.print("문자열을 입력하세요 >> ");
    try {
      while(true) {
        str = s.nextLine();
        StringTokenizer st = new StringTokenizer(str);
        while(st.hasMoreTokens()) {
          String token = st.nextToken();
          if (longestWord == null || token.length() > longestWord.length()) {
            longestWord = token;
          }
        }
      }
    }
    catch (NoSuchElementException e) {
      System.out.println("가장 긴 단어는 \"" + longestWord + "\"");
    }
    s.close();
  }
}

package mobileJava.java3;

interface clickListener {
  public void print();
}

public class exam13 {
  public static void main(String[] args) {
    clickListener listener = (new clickListener() {
      public void print() {
        System.out.println("클릭 리스너입니다.");
      }
    });

    listener.print();
  }
}

package java3.java3_3;
import java.util.Scanner;

class java3_3 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("정수를 입력하시오 >> ");
    int num = scanner.nextInt();
    for (int i = 0; i < num; i++) {
      for (int j = num - i; j > 0; j--) {
        System.out.print("*");
      }
      System.out.println();
    }

    for (int i = num; i > 0; i--) {
      for (int j = 0; j < i; j++) System.out.print("*");
      System.out.println();
    }

    scanner.close();
  }  
}
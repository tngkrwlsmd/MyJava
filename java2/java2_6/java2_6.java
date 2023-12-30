package java2.java2_6;
import java.util.Scanner;

class java2_6 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("1 ~ 99 사이의 정수를 입력하시오 >> ");
    int num = scanner.nextInt();
    System.out.print("박수");

    int n = num;
    while (n > 0) {
      int digit = n % 10;
      if (digit != 0 && digit % 3 == 0) System.out.print("짝");
      n /= 10;
    }
    scanner.close();
  }  
}

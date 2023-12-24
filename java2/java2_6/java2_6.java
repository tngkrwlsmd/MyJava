package java2.java2_6;
import java.util.Scanner;

class java2_6 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("1 ~ 99 사이의 정수를 입력하시오 >> ");
    int num = scanner.nextInt();
    System.out.print("박수");

    int temp = num;
    while (temp > 0) {
      int digit = temp % 10;
      if (digit % 3 == 0) System.out.print("짝");
      temp /= 10;
    }
    scanner.close();
  }  
}

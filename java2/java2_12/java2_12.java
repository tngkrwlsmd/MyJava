package java2.java2_12;
import java.util.Scanner;

class java2_12 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("연산 >> ");
    Double a = scanner.nextDouble();
    char oper = scanner.next().charAt(0);
    Double b = scanner.nextDouble();
    double result = 0;
    boolean flag = true;
    switch (oper) {
      case '+': result = a + b; break;
      case '-': result = a - b; break;
      case '*': result = a * b; break;
      case '/': {
        if (b == 0) {
          System.out.println("0으로 나눌 수 없습니다.");
          flag = false; break;
        }
        else result = a / b;
      }
      default: System.out.println("잘못된 입력입니다."); break;
    }
    if (flag) System.out.println(a + " " + oper + " " + b + " 의 계산 결과는 " + result);
    scanner.close();
  }
}

package java4.java4_11;
import java.util.Scanner;

class Add {
  private int a, b;
  void setValue(int a, int b) {
    this.a = a;
    this.b = b;
  }
  double calculate() { return (double)a + b; }
}

class Sub {
  private int a, b;
  void setValue(int a, int b) {
    this.a = a;
    this.b = b;
  }
  double calculate() { return (double)a - b; }
}

class Mul {
  private int a, b;
  void setValue(int a, int b) {
    this.a = a;
    this.b = b;
  }
  double calculate() { return (double)a * b; }
}

class Div {
  private int a, b;
  void setValue(int a, int b) {
    this.a = a;
    this.b = b;
  }
  double calculate() { return (double)a / b; }
}

class java4_11 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("두 정수와 연산자를 입력하세요 >> ");
    int a = scanner.nextInt();
    int b = scanner.nextInt();
    char c = scanner.next().charAt(0);

    switch (c) {
      case '+': 
      Add add = new Add();
      add.setValue(a, b);
      System.out.println("결과: " + add.calculate());
      break;
      case '-':
      Sub sub = new Sub();
      sub.setValue(a, b);
      System.out.println("결과: " + sub.calculate());
      break;
      case '*':
      Mul mul = new Mul();
      mul.setValue(a, b);
      System.out.println("결과: " + mul.calculate());
      break;
      case '/':
      if (b == 0) {
        System.out.println("0으로 나눌 수 없습니다.");
        break;
      }
      Div div = new Div();
      div.setValue(a, b);
      System.out.println("결과: " + div.calculate());
      break;
      default:
      System.out.println("잘못된 입력입니다.");
      break;
    }

    scanner.close();
  }
}

package Calculater;
import java.util.Scanner;

abstract class Calc {
  private int a, b;
  int getA() { return a; }
  int getB() { return b; }
  void setValue(int a, int b) { this.a = a; this.b = b; }
  abstract int calculate();
}

class Add extends Calc {
  @Override
  int calculate() { return getA() + getB(); }
}

class Sub extends Calc {
  @Override
  int calculate() { return getA() - getB(); }
}

class Mul extends Calc {
  @Override
  int calculate() { return getA() * getB(); }
}

class Div extends Calc {
  @Override
  int calculate() { return getA() / getB(); }
}

class CalcExam {
  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    int a,b; 
    char c;

    System.out.print("두 정수와 연산자를 입력하세요: ");
    a = s.nextInt(); b = s.nextInt(); c = s.next().charAt(0);

    switch (c) {
      case '+': 
        Add add = new Add();
        add.setValue(a, b);
        System.out.println(add.calculate()); break;
      case '-': 
        Sub sub = new Sub();
        sub.setValue(a, b);
        System.out.println(sub.calculate()); break;
      case '*':
        Mul mul = new Mul();
        mul.setValue(a, b);
        System.out.println(mul.calculate()); break;
      case '/': 
        Div div = new Div();
        div.setValue(a, b); 
        System.out.println(div.calculate()); break;
      default:
        System.out.println("잘못된 연산입니다."); break;
    }

    s.close();
  }
}
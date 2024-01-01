package java5_9;
import java.util.Scanner;

interface Stack {
   int length(); // 현재 스택에 저장된 개수 리턴
   int capacity(); // 스택의 전체 저장 가능한 개수 리턴
   String pop(); // 스택의 톱(top)에 저장된 실수 리턴
   void push(String val); // 스택의 톱(top)에 실수 저장
}

class StringStack implements Stack {
  private int top; //스택의 현재 위치
  private int size; //스택의 크기
  private String[] stack; //문자열을 저장하는 스택 배열
  StringStack(int num) {
    top = -1;
    this.size = num;
    stack = new String[this.size];
  }
  @Override
  public int length() { return top + 1; }
  @Override
  public int capacity() { return size; }
  @Override
  public String pop() {
    String s;
    if (top == -1) { return null; }
    else {
      s = stack[top];
      top--;
      return s;
    }
  }
  @Override
  public void push(String val) {
    if (top + 1 == size) System.out.println("스택이 꽉 차서 푸시 불가!!");
    else {
      top++;
      stack[top] = val;
    }
  }
}

class StackApp {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in, "cp949");
    System.out.print("총 스택 저장 공간의 크기 입력 >> ");
    int num = scanner.nextInt();
    StringStack stack = new StringStack(num);
    while(true) {
      System.out.print("문자열 입력 >> ");
      String str = scanner.next();
      if (str.equals("그만")) break;
      stack.push(str);
    }
    System.out.print("스택에 저장된 모든 문자열 팝 : ");
    int len = stack.length();
    for (int i = 0; i < len; i++) {
      String s = stack.pop();
      System.out.print(s + " ");
    }
    scanner.close();
  }
}
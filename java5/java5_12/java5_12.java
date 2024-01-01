package java5_12;
import java.util.Scanner;

abstract class Shape {
  private Shape next;
  Shape() { next = null; }
  void setNext(Shape obj) { next = obj; }
  Shape getNext() { return next; }
  abstract void draw();
}

class Line extends Shape {
  String name = "line";
  void draw() { System.out.println("Line"); }
}

class Rect extends Shape {
  String name ="Rect";
  void draw() { System.out.println("Rect");}
}

 class Circle extends Shape {
  String name ="Circle";
  void draw() { System.out.println("Cicle");}
 }

 class GraphicEditor {
  private Shape head, tail;
  private Scanner scanner;

  GraphicEditor() {
    head = null;
    tail = null;
    scanner = new Scanner(System.in);
  }

  void run() {
    System.out.println("그래픽 에디터 beauty를 실행합니다.");
    while(true) {
      System.out.print("삽입(1), 삭제(2), 모두 보기(3), 종료(4) >> ");
      int num = scanner.nextInt();
      switch (num) {
        case 1:
        System.out.print("Line(1), Rect(2), Circle(3)>> ");
        num = scanner.nextInt();
        insert(num);
        break;
        case 2:
        System.out.print("삭제할 도형의 위치 >> ");
        num = scanner.nextInt();
        delete(num);
        break;
        case 3:
        print();
        break;
        case 4:
        System.out.println("beauty을 종료합니다.");
        scanner.close();
        return;
        default:
        System.out.println("잘못된 입력입니다.");
        break;
      }
    }
  }

  void insert(int draw) {
    Shape graphic;
    switch (draw) {
      case 1: graphic = new Line(); break;
      case 2: graphic= new Rect(); break;
      case 3: graphic = new Circle(); break;
      default:
      System.out.println("다시 입력해주세요.");
      return;
    }
    if (head == null) { //
      head = graphic;
      tail = graphic;
    }
    else {
      tail.setNext(graphic); // tail의 next로 graphic 연결
      tail = graphic; // 그리고 tail은 graphic을 가리킴
    }
  }

  void print() {
    Shape s = head;
    while (s != null) {
      s.draw();
      s = s.getNext();
    }
  }

  void delete(int num) {
    Shape current = head; //current = 현재 노드
    Shape temp = head; //temp = 임시 노드
    int i;
    if (num == 1) {
      if (head == tail) { //첫번째 노드는 별개
        head = null;
        tail = null;
        return;
      }
      else {
        head = head.getNext();
        return;
      }
    }
    for (i = 1; i < num; i++) { //삭제할 노드까지 이동
      temp = current; // temp = 직전 노드가 됨
      current = current.getNext();
      if (current == null) {
        System.out.println("삭제할 수 없습니다.");
        return;
      }
    }
    if (i == num) { //삭제할 노드에 도착(마지막 노드)
      temp.setNext(current.getNext()); // temp의 next에 current의 next를 연결
      tail = temp; //원래의 tail이 current 일때, 이를 temp로 변경
    }
    else temp.setNext(current.getNext()); //current를 건너 뜀
  }
 }

class java5_12 {
  public static void main(String[] args) {
    GraphicEditor editor = new GraphicEditor();
    editor.run();
  }
}

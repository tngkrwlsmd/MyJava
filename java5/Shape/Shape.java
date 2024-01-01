package Shape;

interface Shape {
  final double PI = 3.14;
  void draw();
  double getArea();
  default public void redraw() {
    System.out.print("--- 다시 그립니다.");
    draw();
  }
}

class Circle implements Shape {
  private int radius;
  Circle(int radius) { this.radius = radius;}
  public void draw() { 
    System.out.println("반지름이 " + radius + "인 원입니다.");
  }
  public double getArea() { return PI * radius * radius; }
}

class CircleExam {
  public static void main(String[] args) {
    Shape donut = new Circle(10);
    donut.redraw();
    System.out.println("면적은 " + donut.getArea());
  }
}

class Oval implements Shape { //타원
  private int a, b; //장반경, 단반경
  Oval(int a, int b) {
    this.a = a;
    this.b = b;
  }
  public void draw() {
    System.out.println(a + "x" + b + "에 내접하는 타원입니다.");
  }
  public double getArea() { return PI * a * b;}
}

class Rect implements Shape {
  private int width, height;
  Rect(int width, int height) {
    this.width = width;
    this.height = height;
  }
  public void draw() {
    System.out.println(width + "x" + height + "크기의 사각형입니다.");
  }
  public double getArea() { return width * height; }
}

class java4_14 {
  public static void main(String[] args) {
   Shape[] list = new Shape[3]; // Shape을 상속받은 클래스 객체의 레퍼런스 배열
   list[0] = new Circle(10); // 반지름이 10인 원 객체
   list[1] = new Oval(20, 30); // 20x30 사각형에 내접하는 타원
   list[2] = new Rect(10, 40); // 10x40 크기의 사각형
   for(int i=0; i<list.length; i++) list[i].redraw();
   for(int i=0; i<list.length; i++) System.out.println("면적은 "+ list[i].getArea());
  }
}
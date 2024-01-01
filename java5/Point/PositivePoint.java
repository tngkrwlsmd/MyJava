package Point;

class PositivePoint extends Point {
  PositivePoint() {
    super(0, 0); //기본 생성자가 없으면 인수 없이 객채 생성 불가능
  }
  PositivePoint(int x, int y) {
    super(x, y);
    if (x < 0 || y < 0) { // 하나라도 음수일 경우 원점으로 이동
      super.move(0, 0);
    }
  }
  public void move(int x, int y) {
    if (x > 0 && y > 0) super.move(x, y);
  }
  public String toString() {
    return "(" + getX() + "," + getY() + ")의 점";
  }
}

class PositivePointExam {
  public static void main(String[] args) {
    PositivePoint p = new PositivePoint();
    p.move(10, 10);
    System.out.println(p.toString()+"입니다.");
    p.move(-5,5); // 객체 p는 음수 공간으로 이동되지 않음
    System.out.println(p.toString()+"입니다.");
    PositivePoint p2 = new PositivePoint(-10, -10);
    System.out.println(p2.toString()+"입니다.");
  }
}
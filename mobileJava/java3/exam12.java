package mobileJava.java3;

interface iAnimal {
  abstract void eat();
}

public class exam12 {
  public static void main(String[] args) {
    iCat cat = new iCat();
    cat.eat();

    iTiger tiger = new iTiger();
    tiger.move();
    tiger.eat();
  }
}

class iCat implements iAnimal {
  public void eat() {
    System.out.println("생선을 좋아한다.");
  }
}

class iTiger extends Animal implements iAnimal {
  void move() {
    System.out.println("네 발로 이동한다.");
  }

  public void eat() {
    System.out.println("맷돼지를 잡아 먹는다.");
  }
}
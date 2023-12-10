package Polymorphism;

public class DriverExample {
  public static void main(String[] args) {
    Driver driver = new Driver();
    Bus bus = new Bus();
    Taxi taxi = new Taxi();

    //부모의 Vehicle 클래스로 변환 후 대입
    driver.drive(bus);
    driver.drive(taxi);
  }
}

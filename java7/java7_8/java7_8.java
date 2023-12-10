package java7_8;
import java.util.*;

class java7_8 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in, "cp949");
    HashMap<String, Integer> customer = new HashMap<String, Integer>();
    System.out.println("** 포인트 관리 프로그램입니다. **");
    
    while (true) {
      System.out.print("이름과 포인트 입력 >> ");
      String name = scanner.next();
      if (name.equals("그만")) break;
      int point = scanner.nextInt();
      if (customer.get(name) == null) customer.put(name, point);
      else customer.put(name, customer.get(name) + point);

      Set<String> set = customer.keySet();
      Iterator<String> it = set.iterator();

      while (it.hasNext()) {
        String person = it.next();
        int personPoint = customer.get(person);
        System.out.print("(" + person + "," + personPoint + ") ");
      }
      System.out.println();

    }
    scanner.close();
  }
}

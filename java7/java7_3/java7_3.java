package java7_3;
import java.util.*;

class java7_3 {
  public static void main(String[] args) {
    Scanner s = new Scanner(System.in,"cp949");
    HashMap<String, Integer> nations = new HashMap<String, Integer>();
    System.out.println("나라 이름과 인구를 입력하시오.(예: Korean 5000)");

    while(true) {
      System.out.print("나라 이름, 인구 >> ");
      String nation = s.next();
      if (nation.equals("그만")) break;
      int population = s.nextInt();
      nations.put(nation, population);
    }
    
    while(true) {
      System.out.print("인구 검색 >>");
      String nation = s.next();
      if (nation.equals("그만")) break;
      Integer population = nations.get(nation); //get()은 객체를 반환함
      if (population == null) System.out.println(nation + "의 인구는 없습니다.");
      else System.out.println(nation + "의 인구는 " + population); //population.intValue();
    }

    s.close();
  }
}

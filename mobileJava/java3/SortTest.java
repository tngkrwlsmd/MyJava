package mobileJava.java3;
import java.io.IOException;

public class SortTest {
  public static void main(String[] args) throws IOException {
    System.out.println("정렬 방식을 선택하세요.");
    System.out.println("B : BubbleSort");
    System.out.println("H : HeapSort");
    System.out.println("Q : QuickSort");

    int ch = System.in.read();
    Sort sort = null;
    if (ch == 'B' || ch == 'b') {
      sort = new BubbleSort();
    }
    else if (ch == 'H' || ch == 'h') {
      sort = new HeapSort();
    }
    else if (ch == 'Q' || ch == 'q') {
      sort = new QuickSort();
    }
    else {
      System.out.println("지원하지 않는 기능입니다.");
      return;
    }

    int[] arr = new int[10];
    sort.ascending(arr);
    sort.descending(arr);
    sort.description();
  }
}

interface Sort {
  void ascending(int[] arr);
  void descending(int[] arr);
  void description();
}

class BubbleSort implements Sort {

  public void ascending(int[] arr) {
    System.out.println("BubbleSort ascending");
  }
  public void descending(int[] arr) {
    System.out.println("BubbleSort descending");
  }
  public void description() {
    System.out.println("숫자를 정렬하는 알고리즘입니다.");
    System.out.println("BubbleSort입니다.");
  }
}

class QuickSort implements Sort {

  public void ascending(int[] arr) {
    System.out.println("QuickSort ascending");
  }
  public void descending(int[] arr) {
    System.out.println("QuickSort descending");
  }
  public void description() {
    System.out.println("숫자를 정렬하는 알고리즘입니다.");
    System.out.println("QuickSort입니다.");
  }
}

class HeapSort implements Sort {

  public void ascending(int[] arr) {
    System.out.println("HeapSort ascending");
  }
  public void descending(int[] arr) {
    System.out.println("HeapSort descending");
  }
  public void description() {
    System.out.println("숫자를 정렬하는 알고리즘입니다.");
    System.out.println("HeapSort입니다.");
  }
}
package java3.java3_8;
import java.util.Scanner;

class java3_8 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("정수 몇 개? ");
    int num = scanner.nextInt();
    int arr[] = new int[num];
    for (int i = 0; i < num; i++) {
      arr[i] = (int)(Math.random() * 100 + 1);
      for (int j = 0; j < i; j++) {
        if (arr[i] == arr[j]) {
          i--;
          break;
        }
      }
    }

    for (int i = 0; i < num; i++) {
      System.out.print(arr[i] + " ");
      if ((i + 1) % 10 == 0) System.out.println();
    }
    scanner.close();
  }
}

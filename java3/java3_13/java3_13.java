package java3.java3_13;

class java3_13 {
  public static void main(String[] args) {
    int n = 1;
    boolean applause = false;
    while (n < 100) {
      int temp = n;
      while (temp > 0) {
        int digit = temp % 10;
        if (digit != 0 && digit % 3 == 0) {
          System.out.print(n + " 박수 ");
          applause = true;  //자릿수 중 하나라도 3, 6, 9가 들어간 경우
          break;
        }
        temp /= 10;
      }

      if (applause) {
        while (temp > 0) {
          int digit = temp % 10;
          if (digit != 0 && digit % 3 == 0) System.out.print("짝");
          temp /= 10;
        }
      System.out.println();
      }
      applause = false;
      n++;
    }
  }  
}

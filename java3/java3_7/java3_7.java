package java3.java3_7;

class java3_7 {
  public static void main(String[] args) {
    int num[] = new int[10];
    int total = 0;
    double avg = 0;

    System.out.print("랜덤한 정수들 : ");
    for (int i = 0; i < num.length; i++) {
      num[i] = (int)(Math.random() * 10 + 1);
      System.out.print(num[i] + " ");
      total += num[i];
    }
    System.out.println();
    avg = (double)total / num.length;
    System.out.println("평균은 " + avg);
  }
}

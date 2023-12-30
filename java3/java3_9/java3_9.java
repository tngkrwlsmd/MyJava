package java3.java3_9;

class java3_9 {
  public static void main(String[] args) {
    int num[][] = new int[4][4];
    for (int i = 0; i < num.length; i++) {
      for (int j = 0; j < num[i].length; j++) {
        num[i][j] = (int)(Math.random() * 10 + 1);
        System.out.print(num[i][j] + " ");
      }
      System.out.println();
    }
  }
}

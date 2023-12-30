package java3.java3_10;

class java3_10 {
  public static void main(String[] args) {
    int num[][] = new int[4][4];
    for (int i = 0; i < num.length; i++) {
      for (int j = 0; j < num[i].length; j++) num[i][j] = 0;
    }

    int count = 0;
    while (count < 10) {
      int a = (int)(Math.random() * 4);
      int b = (int)(Math.random() * 4);
      if (num[a][b] == 0) {
        num[a][b] = (int)(Math.random() * 10 + 1);
        count++;
      }
      else continue;
    }

    for (int i = 0; i < num.length; i++) {
      for (int j = 0; j < num[i].length; j++) System.out.print(num[i][j] + " ");
      System.out.println();
    }
  }
}

package java3.java3_12;

class Add {
  public static void main(String[] args) {
    int sum = 0;

    for (int i = 0; i < args.length; i++) {
      try {
        sum += Integer.parseInt(args[i]);
      } catch (NumberFormatException e) {
        continue;
      }
    }
    System.out.println(sum);
  }
}

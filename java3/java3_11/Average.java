package java3.java3_11;

class Average {
  public static void main(String[] args) {
    int sum = 0;
    for (int i = 0; i < args.length; i++) sum += Integer.parseInt(args[i]);
    System.out.println((double)sum / args.length);
  } 
}

import java.util.StringTokenizer;

class java6_3 {
  public static void main(String[] args) {
    java.util.StringTokenizer st = new java.util.StringTokenizer("a=3,b=5,c=6",",");
    while(st.hasMoreTokens()) {
      System.out.println(st.nextToken());
    }
  }
}

class java6_4p {
  public static void main(String[] args) {
    StringTokenizer st = new StringTokenizer("a=3,b=5,c=6","=,");
    int sum = 0;
    while(st.hasMoreTokens()) {
      String next = st.nextToken();
      try {
        sum += Integer.parseInt(next);
        System.out.println(next);
      }
      catch (NumberFormatException e){
        System.out.println(next);
      }
    }
    System.out.println("합은 " + sum);
  }
}

class java6_6 {
  public static void main(String[] args) {
    String s1 =  Integer.toString(20); //정수 20을 문자열로 저장
    double d = Double.parseDouble("35.9");
    boolean b = Boolean.parseBoolean("true");
    String s2 = Integer.toBinaryString(30);
    String c = Character.toString('c');
    System.out.println(s1 + " " + d + " " + b + " " + s2 + " " + c);
  }
}
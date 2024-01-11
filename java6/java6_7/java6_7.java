package java6_7;
import java.util.Scanner;
import java.util.StringTokenizer;

class java6_7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in,"cp949");
        while (true) {
            System.out.print(">> ");
            String str = scanner.nextLine();
            if (str.equals("그만")) {
                System.out.println("종료합니다...");
                break;
            }
            StringTokenizer st = new StringTokenizer(str, " ");
            int num = st.countTokens();
            System.out.println("어절 개수는" + num);
        }
        scanner.close();
    }
}

class java6_7_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in, "cp949");
        while(true) {
            System.out.print(">> ");
            String str = scanner.nextLine();
            if (str.equals("그만")) {
                System.out.println("종료합니다.");
                break;
            }
            String token[] = str.split(" ");
            System.out.println("어절 개수는 " + token.length);
        }
        scanner.close();
    }
}
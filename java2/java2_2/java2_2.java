package java2.java2_2;
import java.util.Scanner;

class java2_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("2자리수 정수 입력(10~99)>> ");
        int num = scanner.nextInt();
        int a = num / 10;
        int b = num % 10;
        if (a == b) System.out.println("Yes! 10의 자리와 1의 자리가 같습니다.");
        else System.out.println("No! 10의 자리와 1의 자리가 다릅니다!");
        scanner.close();
    }
}

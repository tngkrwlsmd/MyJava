package java2.java2_1;
import java.util.Scanner;

class java2_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("원화를 입력하세요(단위 원)>> ");
        int money = scanner.nextInt();
        double dollor = (double)money/1100;
        System.out.println(money + "원은 $" + dollor + "입니다.");
        scanner.close();
    }    
}

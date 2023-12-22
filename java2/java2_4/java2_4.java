package java2.java2_4;
import java.util.Scanner;

class java2_4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("정수를 3개 입력 >> ");
        int num[] = new int[3];
        for (int i = 0; i < num.length; i++) num[i] = scanner.nextInt();
        int mid = num[0];

        if (num[0] > num[1] && num[0] < num[2]) mid = num[0];
        else if (num[1] > num[0] && num[1] < num[2]) mid = num[1];
        else if (num[2] > num[0] && num[2] < num[1]) mid = num[2];

        System.out.println("중간 값은 " + mid);
        scanner.close();
    }
}

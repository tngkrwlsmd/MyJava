package java2.java2_4;
import java.util.Scanner;

class java2_4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("정수를 3개 입력 >> ");
        int num[] = new int[3];
        for (int i = 0; i < num.length; i++) num[i] = scanner.nextInt();
        int min, mid, max;
        min = mid = max = num[0];

        for (int i = 0; i < num.length; i++) {
            if (num[i] > max) max = num[i];
            if (num[i] < min) min = num[i];
        }

        for (int i = 0; i < num.length; i++) {
            if (num[i] != max && num[i] != min) mid = num[i];
        }

        System.out.println("중간값은 " + mid);
        scanner.close();
    }
}

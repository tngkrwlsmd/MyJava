package java2.java2_5;
import java.util.Scanner;

class java2_5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int tri[] = new int[3];
        int flag = 1;
        System.out.print("정수 3개를 입력하시오 >> ");
        for (int i = 0; i < tri.length; i++) tri[i] = scanner.nextInt();

        for (int i = 0; i < tri.length; i++) {
            int twoSum = 0;
            for (int j = 0; j < tri.length; j++) {
                if (j != i) twoSum += tri[j]; //다른 두 변의 합
            }
            if (tri[i] >= twoSum) {
                flag = 0;
                break;
            }
        }

        if (flag == 1) System.out.println("삼각형이 될 수 있습니다.");
        else System.out.println("삼각형이 될 수 없습니다.");
        scanner.close();
    }    
}

package java2.java2_5;
import java.util.Scanner;

class java2_5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int tri[] = new int[3];
        System.out.print("정수 3개를 입력하시오 >> ");
        for (int i = 0; i < tri.length; i++) tri[i] = scanner.nextInt();

        if (tri[0] + tri[1] > tri[2] &&
            tri[1] + tri[2] > tri[0] &&
            tri[2] + tri[0] > tri[1]) {
                System.out.println("삼각형이 됩니다.");
            }
        else System.out.println("삼각형이 될 수 없습니다");


        scanner.close();
    }    
}

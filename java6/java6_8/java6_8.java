package java6_8;
import java.util.Scanner;

class java6_8 {
    public static void main(String[] args) {
        System.out.println("문자열을 입력하세요. 빈칸이나 있어도 되고 영어 한글 모두 됩니다.");
        Scanner scanner = new Scanner(System.in, "cp949");
        String word = scanner.nextLine();
        for (int i = 1; i < word.length(); i++) {
            System.out.print(word.substring(i));
            System.out.println(word.substring(0, i));
        }
        scanner.close();
    }    
}

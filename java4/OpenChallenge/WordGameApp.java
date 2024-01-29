package java4.OpenChallenge;
import java.util.Scanner;

public class WordGameApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in, "cp949");
        System.out.println("끝말잇기 게임을 시작합니다...");
        
        String word = "아버지";
        int lastIndex = word.length() - 1; //마지막 문자열 인덱스
        char lastChar = word.charAt(lastIndex); //마지막 문자
        char firstChar = word.charAt(0);
        
        System.out.print("게임에 참가하는 인원은 몇 명입니까?");
        int num = scanner.nextInt();
        Player[] p =new Player[num];

        for (int i = 0; i < num; i++) {
            System.out.print("참가자의 이름을 입력하세요 >> ");
            p[i] = new Player(scanner.next());
        }

        System.out.println("### 참가자 명단 ####");
        for (int i = 0; i < num; i++) System.out.print(p[i].getName() + " ");
        System.out.println("\n");

        int count = 0;
        while(true) {
            System.out.print(p[count].getName() + " >> ");
            String nextWord = scanner.next();
            firstChar = nextWord.charAt(0);

            if (firstChar != lastChar) {
                System.out.println(p[count].getName() + " Lose!!");
                System.out.println("게임 종료!!");
                break;
            }

            lastIndex = nextWord.length() - 1;
            lastChar = nextWord.charAt(lastIndex);
            count++;
            
            if (count >= num) count = 0; //로테이션
        }
        scanner.close();
    }
}

package java7_11;
import java.util.*;

public class CapitalGame2 {
    private Scanner scanner = new Scanner(System.in, "cp949");
    private HashMap<String, String> hs = new HashMap<>();

    public CapitalGame2() {
        hs.put("그리스", "아테네");
        hs.put("일본", "도쿄");
        hs.put("중국", "베이징");
        hs.put("멕시코", "멕시코시티");
        hs.put("영국", "런던");
        hs.put("독일", "베를린");
        hs.put("러시아", "모스크바");
    }

    void run() {
        System.out.println("**** 수도 맞추기 게임을 시작합니다. ****");
        while (true) {
            System.out.print("입력: 1, 퀴즈: 2, 종료: 3 >> ");
            try {
                int select = scanner.nextInt();
                switch (select) {
                    case 1: input(); break;
                    case 2: quiz(); break;
                    case 3: System.out.println("게임을 종료합니다.\n"); return;
                    default: System.out.println("없는 메뉴입니다. 다시 입력하세요.\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("없는 메뉴입니다. 다시 입력하세요.\n");
                scanner.nextLine();
            }
        }
    }

    void input() {
        int n = hs.size();
        System.out.println("현재 " + n + "개의 나라와 수도가 입력되어 있습니다.");
        n++;
        while (true) {
            System.out.print("나라와 수도 입력" + n + " >> ");
            String country = scanner.next();
            if (country.equals("그만")) break;
            String capital = scanner.next();
            if (hs.containsKey(country)) {
                System.out.println(country + "는 이미 있습니다!!");
                continue;
            }
            hs.put(country, capital);
            n++;
        }
    }

    void quiz() {
        Set<String> keys = hs.keySet(); //key 값을 Set 컬렉션으로 리턴
        Object[] array = keys.toArray(); //Set 컬렉션을 객체 배열로 리턴
        while (true) {
            int index = (int)(Math.random()*hs.size());
            String question = (String) array[index]; // 상위 객체에서 String으로 강제 캐스팅
            String answer = hs.get(question);

            System.out.print(question + "의 수도는? ");
            String think = scanner.next();
            if(think.equals("그만")) break;
            if(answer.equals(think)) System.out.println("정답!!");
            else System.out.println("아닙니다!!");
        }
    }

    public static void main(String[] args) {
        CapitalGame2 game = new CapitalGame2();
        game.run();
    }
}

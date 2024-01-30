package java7_11;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;

class CapitalGame {
    private Scanner scanner = new Scanner(System.in, "cp949");
    private Vector<Nation> v = new Vector<>();

    CapitalGame() {
        v.add(new Nation("그리스", "아테네"));
        v.add(new Nation("일본", "도쿄"));
        v.add(new Nation("중국", "베이징"));
        v.add(new Nation("멕시코", "멕시코시티"));
        v.add(new Nation("영국", "런던"));
        v.add(new Nation("독일", "베를린"));
        v.add(new Nation("러시아", "모스크바"));
    }

    void run() {
        System.out.println("**** 수도 맞추기 게임을 시작합니다. ****");
        while(true) {
            System.out.print("입력: 1, 퀴즈: 2, 종료: 3 >> ");
            try {
                int select = scanner.nextInt();
                switch (select) {
                    case 1: input(); break;
                    case 2: quiz(); break;
                    case 3: System.out.println("게임을 종료합니다."); return;
                    default: System.out.println("없는 메뉴입니다. 다시 입력하세요.\n");
                }   
            } catch (InputMismatchException e) {
                System.out.println("없는 메뉴입니다. 다시 입력하세요.\n");
                scanner.nextLine(); //입력값을 비워줌
            }
        }
    }

    void input() {
        int n = v.size();
        System.out.println("현재 " + n + "개 나라와 수도가 입력되어 있습니다.");
        n++;
        while (true) {
            System.out.print("나라와 수도 입력" + n + " >> ");
            String country = scanner.next();
            if (country.equals("그만")) break;
            String capital = scanner.next();
            if (exist(country)) {
                System.out.println(country + "는 이미 있습니다!!");
                continue;
            }
            v.add(new Nation(country, capital));
            n++;
        }
    }

    void quiz() {
        while (true) {
            int index = (int)(Math.random()*v.size()); //랜덤 나라 선택
            Nation nation = v.get(index);
            String question = nation.getCountry();
            String answer = nation.getCapital();

            System.out.print(question + "의 수도는? ");
            String think = scanner.next();
            if (think.equals("그만")) break;
            if (answer.equals(think)) System.out.println("정답!!");
            else System.out.println("아닙니다!!");
        }
    }

    boolean exist(String country) {
        for (int i = 0; i < v.size(); i++) {
            if (v.get(i).getCountry().equals(country)) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        CapitalGame game = new CapitalGame();
        game.run();
    }
}

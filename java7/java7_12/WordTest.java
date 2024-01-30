package java7_12;
import java.util.*;

class WordTest {
    Scanner scanner = new Scanner(System.in, "cp949");
    Vector<Word> v = new Vector<>();
    int[] questionQuiz = new int[4];

    WordTest() {
        v.add(new Word("love", "사랑"));
        v.add(new Word("animal", "동물"));
        v.add(new Word("picture", "그림"));
        v.add(new Word("emotion", "감정"));
        v.add(new Word("baby", "아기"));
        v.add(new Word("error", "오류"));
        v.add(new Word("society", "사회"));
        v.add(new Word("doll", "인형"));
        v.add(new Word("bear", "곰"));
        v.add(new Word("example", "보기"));
        v.add(new Word("deal", "거래"));
        v.add(new Word("photo", "사진"));
        v.add(new Word("human", "인간"));
        v.add(new Word("statue", "조각상"));
        v.add(new Word("eye", "눈"));
        v.add(new Word("hand", "손"));
        v.add(new Word("ant", "개미"));
        v.add(new Word("resume", "이력서"));
        v.add(new Word("concern", "걱정"));
        v.add(new Word("bad", "나쁜"));
        v.add(new Word("developer", "개발자"));
        v.add(new Word("word", "단어"));
    }

    void run() {
        System.out.println("*** 영어 단어 테스트 프로그램 \"명품영어\" 입니다. ****");
        while (true) {
            System.out.print("단어 테스트: 1, 단어 삽입: 2, 종료: 3 >> ");
            try {
                int n = scanner.nextInt();
                switch (n) {
                    case 1: 
                    System.out.println("현재 " + v.size() + "개의 단어가 들어있습니다. -1을 입력하면 종료합니다.\n");
                    quiz(); break;
                    case 2: 
                    System.out.println("영어 단어에 그만을 입력하면 입력을 종료합니다.");
                    insert(); break;
                    case 3:
                    System.out.println("\"명품영어\"를 종료합니다.");
                    return;
                    default: System.out.println("잘못 입력하였습니다.\n");
                } 
            } catch (InputMismatchException e) {
                System.out.println("잘못 입력하였습니다.\n");
                scanner.nextLine();
            }
        }
    }
    void make() {
        for (int i = 0; i < 4; i++) {
            questionQuiz[i] = (int)(Math.random()*v.size()); //랜덤 숫자 4개 저장
            for (int j = 0; j < i; j++) {
                if(questionQuiz[i] == questionQuiz[j]) {
                    i--;
                    continue; //단, 같은 것은 제외하여 뽑음
                }
            }
        }
    }
    void insert() {
        while (true) {
            System.out.print("영어 한글 입력 >> ");
            String eng = scanner.next();
            if (eng.equals("그만")) {
                System.out.println();
                break;
            }
            String kor = scanner.next();
            int count = 0;
            for (int i = 0; i < v.size(); i++) {
                String temp = v.get(i).getEng(); //입력한 영단어가 이미 존재하는가?
                if (temp.equals(eng)) System.out.println("이미 있는 단어입니다.");
                else count++;
            }
            if (count == v.size()) v.add(new Word(eng, kor));
        }
    }
    void quiz() {
        while (true) {
            make();
            int answerIndex = (int)(Math.random() * 4); //정답 번호(0~3)
            int answerNumber = questionQuiz[answerIndex]; //정답
            System.out.println(v.get(answerNumber).getEng() + "?");

            for (int i = 0; i < questionQuiz.length; i++) {
                System.out.print("(" + (i + 1) + ")");
                System.out.print(v.get(questionQuiz[i]).getKor() + " ");
            }
            System.out.print(">> ");
            try {
                int userSelect = scanner.nextInt();
                if (userSelect == -1) {
                    System.out.println();
                    break;
                }
                else if (userSelect < 1 || userSelect > 4) System.out.println("다시 입력하세요.\n");
                else if (userSelect - 1 == answerIndex) System.out.println("Excellent!!\n");
                else System.out.println("No....\n");
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("숫자를 입력하세요!!\n");

            }
        }
    }

    public static void main(String[] args) {
        WordTest wordTest = new WordTest();
        wordTest.run();
    }
}

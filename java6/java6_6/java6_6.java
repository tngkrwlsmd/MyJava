package java6_6;
import java.util.Calendar;
import java.util.Scanner;

class Person {
    Scanner scanner = new Scanner(System.in);
    Calendar c;
    private String name, enter;
    private int sec1, sec2;
    Person(String name) {
        this.name = name;
    }
    int game() {
        System.out.print(name + " 시작 <Enter>키 >> ");
        enter = scanner.nextLine();
        c = Calendar.getInstance();
        sec1 = c.get(Calendar.SECOND);
        System.out.println("\t현재 초 시간 = " + sec1);

        System.out.print("10초 예상 후 <Enter>키 >> ");
        enter = scanner.nextLine();
        c = Calendar.getInstance();
        sec2 = c.get(Calendar.SECOND);
        System.out.println("\t현재 초 시간 = " + sec2);
        System.out.println(enter); //enter 노란 줄 제거
        if (sec1 < sec2) return sec2 - sec1;
        else {
            sec2 += 60;
            return sec2 - sec1;
        }
    }
}

class java6_6 {
    public static void main(String[] args) {
        Person a = new Person("황기태");
        Person b = new Person("이재문");
        System.out.println("10초에 가까운 사람이 이기는 게임입니다.");
        int result1 = a.game();
        int result2 = b.game();

        //크기가 아닌, 10에 가장 가까운 사람이 승리
        if (Math.abs(result1 - 10) < Math.abs(result2 - 10)) {
        System.out.println("황기태 결과 : " + result1 + ", " + "이재문 결과 : " + result2 + ">> 승자 황기태");
        }
        else {
            System.out.println("황기태 결과 : " + result1 + ", " + "이재문 결과 : " + result2 + ">> 승자 이재문");
        }
    }
}

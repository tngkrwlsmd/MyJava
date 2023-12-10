package java6.java6_2s;

class StringSub {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("명령행 인자가 없습니다.");
            return;
        }
        String str = args[0];
        str = str.replace("is", "are");
        System.out.println(str);
    }
}
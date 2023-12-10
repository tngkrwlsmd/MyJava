package java7_2s;

public class MyClass<E> {
    private E s;
    public MyClass(E s) { this.s = s;}
    void setS(E s) { this.s = s; }
    E getS() { return s; }
}

class MyClassEx {
    public static void main(String[] args) {
        MyClass<String> s = new MyClass<String>("hello");
        System.out.println("처음 : " + s.getS());
        s.setS("java");
        System.out.println("세팅 : " + s.getS());
    }
}

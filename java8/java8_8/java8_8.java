package java8.java8_8;
import java.io.*;

class java8_8 {
    public static void main(String[] args) {
        File file = new File("c:/");
        File[] files = file.listFiles(); // c 드라이브 내 파일 리스트
        
        File big = null;
        long max = 0; // 가장 큰 파일의 크기, 만약의 경우로 long으로 설정
        for (int i = 0; i < files.length; i++) { //모든 파일 리스트 조사
            File f = files[i];
            if (!f.isDirectory()) continue; //비어있는 dir
            long size = f.length();
            if (max < size) {
                max = size;
                big = f;
            }
        }
        System.out.println("가장 큰 파일은 " + big.getPath() + " " + max + "byte 입니다.");
    }
}

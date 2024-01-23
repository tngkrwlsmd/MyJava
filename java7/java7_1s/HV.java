package java7_1s;
import java.util.*;

class HV {
    public static Vector<String> hashToVector(HashMap<String, String> h) {
        Vector<String> v = new Vector<String>();
        Set<String> keys = h.keySet();
        Iterator<String> it = keys.iterator();

        while(it.hasNext()) {
            String name = it.next();
            v.add(h.get(name));
        }
        return v;
    }
    public static void main(String[] args) {
        HashMap<String,String> h = new HashMap<String,String>();
        h.put("범죄","112");
        h.put("화재","119");
        h.put("전화번호","114");

        //벡터를 이용한 value 출력
        Vector<String> v = HV.hashToVector(h);
        for (int n = 0; n < v.size(); n++) {
            System.out.println(v.get(n));
        }

        Set<String> keys = h.keySet();
        Iterator<String> it = keys.iterator();
        
        System.out.println();

        //Iterator를 이용한 key, value 출력
        while(it.hasNext()) {
            String name = it.next();
            String tel = h.get(name);
            System.out.println(name + " "+ tel);
        }
    }
}

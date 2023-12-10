package java7_3s;
import java.util.*;

class Location {
    private String city;
    private int latitude, longitude; //위도, 경도
    
    Location(String city, int latitude, int longitude) {
        this.city = city; 
        this.latitude = latitude; 
        this.longitude = longitude;
    }

    String getCity() { return city; }
    int getLatitude() { return latitude; }
    int getLongitude() { return longitude; }
}

class LocationEx {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in, "cp949");
        HashMap<String, Location> map = new HashMap<String, Location>();
        Location[] location = new Location[5];

        System.out.println("도시, 경도, 위도를 입력하세요. ");
        for (int i = 0; i < 5; i++) {
            System.out.print(">>");
            String text = s.nextLine();
            StringTokenizer st = new StringTokenizer(text, ",");
            String city = st.nextToken().trim();
            int longitude = Integer.parseInt(st.nextToken().trim());
            int latitude = Integer.parseInt(st.nextToken().trim());
            location[i] = new Location(city, longitude, latitude);
            map.put(city, location[i]);
        }

        Set<String> set = map.keySet();
        Iterator<String> it = set.iterator();
        System.out.println("--------------------------------");
        while(it.hasNext()) {
            String city = it.next();
            Location loc = map.get(city);

            System.out.println(loc.getCity() + " " + loc.getLongitude() + 
            " " + loc.getLatitude());
        }
        System.out.println("--------------------------------");

        while (true) {
            System.out.print("도시 이름 >> ");
            String city = s.next();
            if(city.equals("그만")) break;
            Location loc = map.get(city);
            if (loc == null) System.out.println(city + "는 없습니다.");
            else {
                System.out.println(loc.getCity() + " " +
                loc.getLongitude() + " " + loc.getLatitude());
            }
        }
        s.close();
    }
}

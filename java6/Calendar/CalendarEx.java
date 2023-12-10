package Calendar;
import java.util.Calendar;

class CalendarEx {
    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        
        System.out.println("현재 시간은 " + hour + "시 " + minute + "분입니다.");
        if (hour >= 4 && hour < 12) System.out.println("Goob Morning");
        else if (hour >= 12 && hour < 18) System.out.println("Goob Afternoon");
        else System.out.println("Goob Night");
    }
}
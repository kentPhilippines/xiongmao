package parProject.com.test.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeText {
	public static void main(String[] args) {
		String lastDay = getLastDay("2019-09-09");
		System.out.println(lastDay); 
	}
	
	public static String getLastDay(String time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date date=null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(date);
        int day=calendar.get(Calendar.DATE);
        //                      此处修改为+1则是获取后一天
        calendar.set(Calendar.DATE,day-1);
        String lastDay = sdf.format(calendar.getTime());
        return lastDay;
    }
	
}

class HotelTimeTest {
	int IsAfternoon(int currentTime ) {
	System.out.println(currentTime);
	return currentTime - 9;
	}
}
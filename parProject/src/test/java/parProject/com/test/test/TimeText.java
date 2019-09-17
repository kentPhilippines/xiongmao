package parProject.com.test.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cn.hutool.core.date.DateUtil;

public class TimeText {
	public static void main(String[] args) {
		
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 String format = sdf.format(new Date());
		Date date = DateUtil.parse(format);
		Date beginOfDay = DateUtil.beginOfDay(date);
		Date endOfDay = DateUtil.endOfDay(date);
		String format2 = sdf1.format(beginOfDay);
		String format3 = sdf1.format(endOfDay);
		
		System.out.println(format);
		System.out.println(format2);
		System.out.println(format3);
		
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
package dirapp.database;

import java.util.Calendar;
import java.util.Date;

public class App 
{
    public static void main( String[] args )
    {
        // class Date untuk representasi dari tanggal, untuk melakukan manipulasi tanggal gunakan class Calendar
        Date tangggal = new Date();
        System.out.println(tangggal);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 1999);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 3);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date date = calendar.getTime();
        Long millisecond = date.getTime();

        System.out.println("tanggal hari ini : " + calendar.DAY_OF_MONTH);
        System.out.println("tanggal hari ini dalam milisecond : " + millisecond);
        System.out.println("tanggal setelah dicustom ; " + date);

    }
}

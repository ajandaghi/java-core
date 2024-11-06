import com.ibm.icu.util.GregorianCalendar;
import com.ibm.icu.util.PersianCalendar;
import com.ibm.icu.util.ULocale;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import com.ibm.icu.util.Calendar;

import com.ibm.icu.util.TimeZone;

public class Solar2Georgian {
    public static void main(String[] args) throws ParseException {
        ULocale PERSIAN_LOCALE = new ULocale("fa_IR@calendar=persian");
        ULocale GREGORIAN_LOCALE = new ULocale("en@calendar=gregorian");
        ZoneId IRAN_ZONE_ID = ZoneId.of("Asia/Tehran");
        Calendar gregoriancal = Calendar.getInstance(GREGORIAN_LOCALE);
        Calendar persiancal = Calendar.getInstance(PERSIAN_LOCALE);
        persiancal.setTimeZone(TimeZone.getTimeZone("Asia/Tehran"));
       // gregorianCalendar.setLenient(false);
        //gregoriancal.set(2025, Calendar.AUGUST, 1);

        //gregoriancal.set(2024,11,6,12,0,0);
        gregoriancal.setTime((new SimpleDateFormat("yyyy-MM-dd")).parse("2024-01-06"));
        System.out.println("Gregorian Calendar:\t" + (gregoriancal.get(Calendar.YEAR)) + "/" + ( gregoriancal.get(Calendar.MONTH )+1) + "/" + gregoriancal.get(Calendar.DATE));

        persiancal.clear();
        persiancal.setTime(gregoriancal.getTime());

        // Display the date.
        System.out.println("Persian Calendar:\t" + (persiancal.get(Calendar.YEAR) ) + "/" + ( persiancal.get (Calendar.MONTH) + 1 ) + "/" + persiancal.get(Calendar.DATE));


        gregoriancal.clear();
        persiancal.setTimeZone(TimeZone.getTimeZone("Asia/Tehran"));

        persiancal.set(1361,3,8,12,0,0);
        // gregorianCalendar.setLenient(false);
        //gregoriancal.set(2025, Calendar.AUGUST, 1);

        gregoriancal.setTime(persiancal.getTime());
        System.out.println("Gregorian Calendar:\t" + (gregoriancal.get(Calendar.YEAR)) + "/" + ( gregoriancal.get(Calendar.MONTH )) + "/" + gregoriancal.get(Calendar.DATE));


    }
}

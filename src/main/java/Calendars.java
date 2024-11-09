import com.ibm.icu.util.ULocale;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Scanner;

import com.ibm.icu.util.Calendar;

import com.ibm.icu.util.TimeZone;

public class Calendars {
    private ULocale PERSIAN_LOCALE = new ULocale("fa_IR@calendar=persian");
    private ULocale GREGORIAN_LOCALE = new ULocale("en@calendar=gregorian");
    private ZoneId IRAN_ZONE_ID = ZoneId.of("Asia/Tehran");
    private Calendar gregoriancal = Calendar.getInstance(GREGORIAN_LOCALE);
    private Calendar persiancal = Calendar.getInstance(PERSIAN_LOCALE);
    public static void main(String[] args) throws ParseException {
        Scanner scanner=new Scanner(System.in);
        Calendars cal=new Calendars();
        System.out.println("select num:");
        System.out.println("1.Gregorian2PersianSolar:");
        System.out.println("2.PersianSolar2Gregorian:");

        String select=scanner.nextLine();
        System.out.println("enter your date in format: yyyy/MM/dd");
        String dateStr=scanner.nextLine();

        switch (select){
            case "1":
                System.out.println(cal.gregorian2Solar(dateStr));
                break;
            case "2":
                System.out.println(cal.solar2Gregorian(dateStr));
                break;
        }


    }

    public String  gregorian2Solar(String gregorian) throws ParseException {

        persiancal.setTimeZone(TimeZone.getTimeZone("Asia/Tehran"));
        // gregorianCalendar.setLenient(false);
        //gregoriancal.set(2025, Calendar.AUGUST, 1);

        //gregoriancal.set(2024,11,6,12,0,0);
        gregoriancal.setTime((new SimpleDateFormat("yyyy/MM/dd")).parse(gregorian));

        persiancal.clear();
        persiancal.setTime(gregoriancal.getTime());

        // Display the date.
        return "Persian Calendar:\t" + (persiancal.get(Calendar.YEAR) ) + "/" + ( persiancal.get (Calendar.MONTH) + 1 ) + "/" + persiancal.get(Calendar.DATE);
    }

    public String solar2Gregorian(String solar) throws ParseException {
        persiancal.setTimeZone(TimeZone.getTimeZone("Asia/Tehran"));

        persiancal.setTime((new SimpleDateFormat("yyyy/MM/dd")).parse(solar));
        // gregorianCalendar.setLenient(false);
        //gregoriancal.set(2025, Calendar.AUGUST, 1);
        gregoriancal.clear();
        gregoriancal.setTime(persiancal.getTime());
        return "Gregorian Calendar:\t" + (gregoriancal.get(Calendar.YEAR)) + "/" + ( gregoriancal.get(Calendar.MONTH+1 )) + "/" + gregoriancal.get(Calendar.DATE);
    }
}

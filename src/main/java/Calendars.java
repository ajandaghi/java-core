import com.ibm.icu.util.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.chrono.HijrahChronology;
import java.time.chrono.HijrahDate;
import java.time.chrono.HijrahEra;
import java.time.chrono.IsoChronology;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;
import java.util.Date;
import java.util.Scanner;

public class Calendars {
    private ULocale PERSIAN_LOCALE = new ULocale("fa_IR@calendar=persian");
    private ULocale GREGORIAN_LOCALE = new ULocale("en@calendar=gregorian");
    private ZoneId IRAN_ZONE_ID = ZoneId.of("Asia/Tehran");

    private ULocale HIJRI_LOCALE=new  ULocale("@calendar=islamic");
    private Calendar gregoriancal = new GregorianCalendar(GREGORIAN_LOCALE);
    private Calendar persiancal = Calendar.getInstance(PERSIAN_LOCALE);

    private Calendar hijri=Calendar.getInstance(HIJRI_LOCALE);
    public static void main(String[] args) throws ParseException {
        Scanner scanner=new Scanner(System.in);
        Calendars cal=new Calendars();
        System.out.println("select num:");
        System.out.println("1.Gregorian2PersianSolar:");
        System.out.println("2.PersianSolar2Gregorian:");
        System.out.println("3.hijri2Solar:");
        System.out.println("4.solar2Hijri:");
        System.out.println("5.hijri2Gregorian:");
        System.out.println("6.gregorian2Hijri:");

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

            case "3":
                System.out.println(cal.hijri2Solar(dateStr));
                break;
            case "4":
                System.out.println(cal.solar2Hijri(dateStr));
                break;
            case "5":
                System.out.println(cal.hijri2Gregorian(dateStr));
                break;
            case "6":
                System.out.println(cal.gregorian2Hijri(dateStr));
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
        return  (persiancal.get(Calendar.YEAR) ) + "/" + ( persiancal.get (Calendar.MONTH) + 1 ) + "/" + persiancal.get(Calendar.DATE);
    }

    public String solar2Gregorian(String solar) throws ParseException {
        //persiancal.setTimeZone(TimeZone.getTimeZone("Asia/Tehran"));

        persiancal.set(Integer.parseInt(solar.split("/")[0]),Integer.parseInt(solar.split("/")[1])-1,Integer.parseInt(solar.split("/")[2]));
        // gregorianCalendar.setLenient(false);
        //gregoriancal.set(2025, Calendar.AUGUST, 1);

        gregoriancal.setTime(persiancal.getTime());

        return  (gregoriancal.get(Calendar.YEAR)) + "/" + ( gregoriancal.get(Calendar.MONTH )+1) + "/" + gregoriancal.get(Calendar.DATE);
    }

    public String solar2Hijri(String solar) throws ParseException {
        String dateStr=solar2Gregorian(solar);
        return gregorian2Hijri(dateStr);
    }

    public String hijri2Solar(String hijr) throws ParseException {
      return gregorian2Solar(hijri2Gregorian(hijr));
    }

    public String hijri2Gregorian(String hijr) throws ParseException {
        HijrahDate hijrahDate=HijrahDate.of(Integer.parseInt(hijr.split("/")[0]), Integer.parseInt(hijr.split("/")[1]), Integer.parseInt(hijr.split("/")[2]));

        LocalDate localDate=(IsoChronology.INSTANCE.date(hijrahDate));
        return  localDate.getYear()+ "/" + (localDate.get(ChronoField.MONTH_OF_YEAR)) + "/" + localDate.getDayOfMonth();
    }

    public String gregorian2Hijri(String gregorian) throws ParseException {
        Date date=(new SimpleDateFormat("yyyy/MM/dd")).parse(gregorian);
        LocalDate localDate=date.toInstant().atZone(ZoneId.of("Asia/Riyadh")).toLocalDate();
        HijrahDate hijri=HijrahDate.from(localDate);
        return hijri.get(ChronoField.YEAR) + "/" + (hijri.get(ChronoField.MONTH_OF_YEAR) ) + "/" + hijri.get(ChronoField.DAY_OF_MONTH);
    }
}

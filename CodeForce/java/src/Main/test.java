package src.Main;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class test {
    public static void main(String[] args) {
        String date = "19:00";

        DateTimeFormatterBuilder fmb = new DateTimeFormatterBuilder();
        fmb.parseCaseInsensitive();
        fmb.append(DateTimeFormatter.ofPattern("HH:mm"));

        LocalTime dt = LocalTime.parse(date, fmb.toFormatter());
        System.out.println(dt


        );
    }
}

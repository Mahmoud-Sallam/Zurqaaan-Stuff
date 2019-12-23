package step4_date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

public class LocalDateExample {


    public static void main(String[] args) {

        LocalDate localDate = LocalDate.now();

        System.out.println("localDate = " + localDate);

        LocalTime localTime = LocalTime.now();

        System.out.println("localTime = " + localTime);

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime = " + localDateTime);


        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/mm/yyyy HH:mm:ss");

        String formatedDateTime = localDateTime.format(dateTimeFormatter);

        System.out.println("formatedDateTime = " + formatedDateTime);

        LocalDateTime futureDateTime = localDateTime.withDayOfMonth(30).withYear(2020);

        System.out.println("futureDateTime = " + futureDateTime);


    }
}

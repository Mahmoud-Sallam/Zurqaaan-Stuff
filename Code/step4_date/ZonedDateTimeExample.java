package step4_date;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ZonedDateTimeExample {

    public static void main(String[] args) {

        ZonedDateTime zonedNow = ZonedDateTime.now();

        System.out.println("zonedNow = " + zonedNow);

        ZoneId auztZone = ZoneId.of("Australia/Sydney");

        ZonedDateTime zonedDateTimeAust = zonedNow.withZoneSameInstant(auztZone);

        System.out.println("zonedDateTimeAust = " + zonedDateTimeAust);

    }
}

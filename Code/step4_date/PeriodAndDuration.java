package step4_date;

import java.sql.Timestamp;
import java.time.*;
import java.time.temporal.ChronoUnit;

public class PeriodAndDuration {

    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        LocalDate future = LocalDate.of(2021, Month.JANUARY, 12);
        Period period = Period.between(now, future);
        System.out.println("period = " + period);

        LocalDate plus = now.plus(1, ChronoUnit.DAYS);
        Period period1 = Period.between(now, plus);
        System.out.println("period1 = " + period1);

        LocalTime timeNow = LocalTime.now();

        Duration duration = Duration.ofHours(2);
        LocalTime plustTime = timeNow.plus(duration);
        Duration between = Duration.between(timeNow, plustTime);
        System.out.println("between = " + between);

    }
}

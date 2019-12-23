package step4_date;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

public class TemporalAdjusterExample {

    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime adjusted = now.with(TemporalAdjusters.firstDayOfMonth());

        System.out.println("adjusted = " + adjusted);

    }
}

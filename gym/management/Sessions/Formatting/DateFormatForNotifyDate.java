package gym.management.Sessions.Formatting;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormatForNotifyDate extends DateFormatStrategy {
    @Override
    public String format(String date) {
        DateTimeFormatter input = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate res = LocalDate.parse(date, input);
        DateTimeFormatter output = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return res.format(output);
    }

}

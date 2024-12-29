package gym.management.Sessions.Formatting;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogDateFormat extends DateFormatStrategy {
    @Override
    public String format(String date) {
        DateTimeFormatter input = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(date, input);
        DateTimeFormatter output = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        return dateTime.format(output);
    }
}

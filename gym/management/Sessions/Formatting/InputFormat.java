package gym.management.Sessions.Formatting;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InputFormat implements DateFormatStrategy{
    private static final DateTimeFormatter  INPUT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    @Override
    public String format(String date) {
        LocalDateTime dateTime = LocalDateTime.parse(date, INPUT);
        return dateTime.toString();
    }
}

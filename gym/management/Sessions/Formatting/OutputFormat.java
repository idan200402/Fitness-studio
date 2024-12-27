package gym.management.Sessions.Formatting;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OutputFormat implements DateFormatStrategy {
    private static final DateTimeFormatter OUTPUT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    private static final DateTimeFormatter INPUT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    @Override
    public String format(String date) {
        LocalDateTime dateTime = LocalDateTime.parse(date, INPUT);
        return dateTime.format(OUTPUT);

    }
}

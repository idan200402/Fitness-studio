package gym.management.Sessions;

import java.util.ArrayList;
import java.util.stream.Collectors;

public enum SessionType {
    Pilates , MachinePilates , ThaiBoxing , Ninja;

    private static String convertToString(SessionType sessionType){
        return switch (sessionType) {
            case Pilates -> "Pilates";
            case MachinePilates -> "MachinePilates";
            case ThaiBoxing -> "ThaiBoxing";
            case Ninja -> "Ninja";
        };
    }
    public static String arrayListToString(ArrayList<SessionType> sessionTypes){
        return sessionTypes.stream().map(SessionType::convertToString).collect(Collectors.joining(", "));
    }
}

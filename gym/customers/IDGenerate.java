package gym.customers;

public class IDGenerate {
    private static int id = 1110;
    public static String generate(){
        return ""+id++;
    }
}

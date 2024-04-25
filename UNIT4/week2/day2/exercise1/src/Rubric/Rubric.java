package Rubric;
import java.util.HashMap;

public class Rubric {
    static private HashMap<String, Long> rubric = new HashMap<>();

    public static void populateRubric(String name, Long number) {
        rubric.put(name, number);
    }

    public static void removeFromRubric(String name) {
        rubric.remove(name);
    }

    public static Long getNumberByName(String name) {
        return rubric.get(name);
    }

    public static String getNameByNumber(Long number) {
        for (String name : rubric.keySet()) {
            Long phoneNumber = rubric.get(name);
            if (phoneNumber.equals(number)) {
                return name;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        populateRubric("Mario", 3423423432L);
        populateRubric("Giovanna", 3523423432L);
        populateRubric("Paolo", 3623423432L);
        populateRubric("Giulia", 3723423432L);

        removeFromRubric("Giulia");

        System.out.println("Get number by name:");

        System.out.println(getNumberByName("Paolo"));

        System.out.println();

        System.out.println("Get name by number:");

        System.out.println(getNameByNumber(3423423432L));

        System.out.println();

        System.out.println("Show all rubric:");

        System.out.println(rubric);
    }
}

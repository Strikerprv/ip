import dukeychatbot.dukeyexceptions.DukeyException;

public class Test {
    public static void main(String[] args) throws DukeyException {

        throw new DukeyException("here's error message");

//        String description = "deadline return book /by Sunday";
//
//        System.out.println("[D] " + "[X] " + description.substring(description.indexOf(" ") + 1));
    }
}

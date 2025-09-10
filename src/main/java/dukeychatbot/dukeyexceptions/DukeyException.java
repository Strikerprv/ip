package dukeychatbot.dukeyexceptions;

/**
 * Constructs DukeException error which are for errors specific for the chatbot.
 *
 * @author dongjun
 */
public class DukeyException extends Exception {

    public DukeyException(String message) {
        super(message);
    }

}

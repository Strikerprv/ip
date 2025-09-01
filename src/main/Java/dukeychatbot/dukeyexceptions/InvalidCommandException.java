package dukeychatbot.dukeyexceptions;

/**
 * Constructs InvalidCommandException error which inherits from DukeyException.
 *
 * <p>Returns error when user inputs a command is not provided.</p>
 */
public class InvalidCommandException extends DukeyException {

    public InvalidCommandException() {
        super("____________________________________________________________\n" +
              "WARNING: Invalid command. Valid commands are: 1) todo, 2) deadline, 3) event\n" +
              "____________________________________________________________\n");
    }
}

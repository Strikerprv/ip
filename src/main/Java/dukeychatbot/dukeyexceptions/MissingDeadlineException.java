package dukeychatbot.dukeyexceptions;

/**
 * Constructs MissingDeadlineException error which inherits from DukeyException.
 *
 * <p>Returns error when user fails to provide deadline for Deadline tasks.</p>
 */
public class MissingDeadlineException extends DukeyException {

    public MissingDeadlineException() {
        super("____________________________________________________________\n" +
                "WARNING: dukeychatbot.tasktypes.Deadline command requires a deadline.\n" +
                "Valid input requires '/by' keyword. Follow the format: deadline <task name> /by <date / timing>\n" +
                "E.g. deadline Wash Clothes /by 8 August\n" +
                "____________________________________________________________\n");
    }
}

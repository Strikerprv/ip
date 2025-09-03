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
                "Dates can be written in yyyy-mm-dd format or just plain text\n" +
                "E.g. deadline Wash Clothes /by 2025-08-12\n" +
                "____________________________________________________________\n");
    }
}

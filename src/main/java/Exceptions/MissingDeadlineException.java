package Exceptions;

public class MissingDeadlineException extends DukeException {

    public MissingDeadlineException() {
        super("____________________________________________________________\n" +
                "WARNING: Deadline command requires a deadline.\n" +
                "Valid input requires '/by' keyword. Follow the format: deadline <task name> /by <date / timing>\n" +
                "E.g. deadline Wash Clothes /by 8 August\n" +
                "____________________________________________________________\n");
    }
}

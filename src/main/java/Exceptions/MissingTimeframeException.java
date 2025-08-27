package Exceptions;

public class MissingTimeframeException extends DukeException {
    public MissingTimeframeException() {
        super("____________________________________________________________\n" +
                "WARNING: Event command requires a timeframe.\n" +
                "Valid input requires '/from' AND '/to' keyword. " +
                "Follow the format: event <task name> /from <date / timing> /to <date / timing> \n" +
                "E.g. event project meeting /from Mon 2pm /to 4pm\n" +
                "____________________________________________________________\n");
    }
}

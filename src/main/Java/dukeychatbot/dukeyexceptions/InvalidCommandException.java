package dukeychatbot.dukeyexceptions;

/**
 * Constructs InvalidCommandException error which inherits from DukeyException.
 *
 * <p>Returns error when user inputs a command is not provided.</p>
 */
public class InvalidCommandException extends DukeyException {

    public InvalidCommandException() {
        super("____________________________________________________________\n" +
              "WARNING: Invalid command. Valid commands are: 1) todo, 2) deadline, 3) event to add commands\n" +
              "To mark tasks as done or undone, use commands: 'mark <task number>' and 'unmark <task number>'\n" +
              "To delete tasks, use command: 'delete <task number>'\n" +
              "____________________________________________________________\n");
    }
}

package dukeychatbot.dukeyexceptions;

/**
 * Constructs InvalidCommandException error which inherits from DukeyException.
 *
 * <p>Returns error when user inputs a command is not provided.</p>
 */
public class InvalidCommandException extends DukeyException {

    /**
     * Constructs the InvalidCommandException object.
     */
    public InvalidCommandException() {
        super("WARNING: Invalid command. \n"
              + "- Valid commands are: 1) 'todo' [or 't'], 2) 'deadline' [or 'd'], 3) 'event' [or 'e'] to add tasks."
              + " Type them to see their functionality\n"
              + "- To mark tasks as done or undone, use commands 'mark' [or 'm'] and 'unmark' [or 'unm']: "
              + "'mark <task number>' and 'unmark <task number>'\n"
              + "- To delete tasks, use command 'delete' [or 'del']: 'delete <task number>'\n"
              + "- To find key words, use command 'find' [or 'f']: 'find <keyword>'\n");
    }
}

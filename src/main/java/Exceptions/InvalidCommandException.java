package Exceptions;

public class InvalidCommandException extends DukeException {

    public InvalidCommandException() {
        super("____________________________________________________________\n" +
              "WARNING: Invalid command. Valid commands are: 1) todo, 2) deadline, 3) event\n" +
              "____________________________________________________________\n");
    }
}

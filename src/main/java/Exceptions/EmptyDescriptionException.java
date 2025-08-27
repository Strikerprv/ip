package Exceptions;

public class EmptyDescriptionException extends DukeException{

    public EmptyDescriptionException() {
        super( "____________________________________________________________\n" +
                "WARNING: The description of a todo cannot be empty.\n" +
                "Input the command with a description after it as such: todo <task description> " +
                "or deadline <task description>\n" +
                "____________________________________________________________\n");
    }
}

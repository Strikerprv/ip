package dukeychatbot.tasktypes;

/**
 * Constructs Deadline class which inherits from Task class.
 * Overrides {@code toString()} method.
 */
public class Deadline extends Task{

    public Deadline(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * {@inheritDoc}
     *
     * Deadline {@code toString()} method includes "[D]" at the front of the string, and
     * the date the task has to be completed by.
     */
    @Override
    public String toString() {
        String description = this.getDescription();
        String formattedDescription;
        if (description.contains("/by")) {
            String[] splitDescription = description.split("/by");
            formattedDescription = splitDescription[0] + "(by:" + splitDescription[1] + ")";
        } else {
            formattedDescription = this.getDescription();
        }

        return "[D] " + "[" + this.getStatusIcon() + "] " + formattedDescription;
    }
}

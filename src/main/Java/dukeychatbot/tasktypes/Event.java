package dukeychatbot.tasktypes;

/**
 * Constructs Event class which inherits from Task class.
 * Overrides {@code toString()} method.
 */
public class Event extends Task{

    public Event(String description) {
        super(description);
    }

    /**
     * {@inheritDoc}
     *
     * Event {@code toString()} method includes "[E]" at the front of the string, and
     * the event's timeframe.
     */
    @Override
    public String toString() {
        String currentDescription = this.getDescription();
        String formattedDescription;

        if (currentDescription.contains("/from")) {
            String[] splitDescription = currentDescription.split("/from");
            if (splitDescription[1].contains("/to")) {
                String[] furtherSplitDescription = splitDescription[1].split("/to");
                formattedDescription = splitDescription[0] +
                        "(from: " + furtherSplitDescription[0].trim() +
                        " to: " + furtherSplitDescription[1].trim() + ")";
            } else {
                formattedDescription = splitDescription[0] + "(from:" + splitDescription[1];
            }
        } else {
            formattedDescription = currentDescription;
        }

        return "[E] " + "[" + this.getStatusIcon() + "] " + formattedDescription;
    }
}

public class Deadline extends Task{

    public Deadline(String description) {
        super(description);
    }

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

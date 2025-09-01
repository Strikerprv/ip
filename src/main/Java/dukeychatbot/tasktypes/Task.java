package dukeychatbot.tasktypes;

/**
 * Constructs Task class with fields of description and done status.
 * Includes basic functionality of getting and setting status, and getting description.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns whether the task has already been done.
     *
     * @return "X" if task is done and " " (a blank) if the task is not done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as done.
     */
    public void markDoneStatus() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmarkDoneStatus() {
        this.isDone = false;
    }

    /**
     * Returns a formatted string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}

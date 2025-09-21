package dukeychatbot;

import java.util.ArrayList;

import dukeychatbot.tasktypes.Task;

/**
 * Constructs the UI class which deals with interactions with the user.
 *
 * @author dongjun
 */
public class Ui {

    private final String horizontalLine = "______________________________________\n";

    public Ui() {}

    /**
     * Displays hello text to signal start of the chatbot.
     */
    public String hello(ArrayList<Task> tasks) {
        return horizontalLine
                + "Hello! I'm Dukey\n"
                + "You have " + tasks.size() + " tasks in your list.\n"
                + "What can I do for you?\n"
                + horizontalLine;
    }

    /**
     * Displays bye text to signal the termination of the chatbot.
     */
    public String bye() {
        return horizontalLine
                + "Bye. Hope to see you again soon!\n"
                + "This tab will close in 5 seconds.\n"
                + horizontalLine;
    }

    /**
     * Displays list of tasks.
     *
     * @param tasks ArrayList of type Task.
     */
    public String printList(String header, ArrayList<Task> tasks) {
        StringBuilder taskList = new StringBuilder();
        taskList.append(horizontalLine);
        for (int count = 1; count <= tasks.size(); count++) {
            Task currentTask = tasks.get(count - 1);
            taskList.append(count).append(". ").append(currentTask.toString()).append("\n");
        }
        taskList.append(horizontalLine);

        return taskList.toString();
    }

    /**
     * Displays error message for when task index is invalid.
     */
    public String invalidTaskIndex() {
        return horizontalLine
                + "Task number exceeds the number of tasks! Please amend command!\n"
                + horizontalLine;
    }

    /**
     * Displays error message for when number format was inputted wrongly.
     */
    public String numberFormatError() {
        return horizontalLine
                + "Mistake in task number input. Make sure you input an integer!\n"
                + horizontalLine;
    }

    /**
     * Displays message when no matching tasks are found.
     */
    public String noMatchingTasks() {
        return horizontalLine
                + "We do not have any task descriptions matching your keyword!\n"
                + "Please try finding another keyword!\n"
                + horizontalLine;
    }

    /**
     * Displays text to inform user that chatbox has been terminated.
     */
    public String chatboxClosedResponse() {
        return horizontalLine
               + "Chatbox has been terminated. Do re-open the chatbox if you wish to continue your chat!\n"
               + horizontalLine;
    }

    /**
     * Displays text for task removal from task list.
     */
    public String removeTaskResponse(String taskDescription, int taskNo) {
        return horizontalLine
               + "Understood. I have removed this task:\n    "
               + taskDescription
               + "\nYou now have " + taskNo + " tasks in the list.\n"
               + horizontalLine;
    }

    /**
     * Displays text to acknowledge marking of a task as done.
     */
    public String markDoneResponse(String taskDescription) {
        return horizontalLine
               + "Nice! I've marked this task as done.\n"
               + taskDescription
               + "\n"
               + horizontalLine;
    }

    /**
     * Displays text to acknowledge unmarking of a task as done.
     */
    public String unmarkDoneResponse(String taskDescription) {
        return horizontalLine
                + "Nice! I've unmarked this task as not done.\n   "
                + taskDescription
                + "\n"
                + horizontalLine;
    }

    /**
     * Displays text for successful task adding to the task list.
     */
    public String addTaskResponse(String taskDescription, int taskNo) {
        return horizontalLine
              + "Understood. I have added the task:\n"
              + taskDescription
              + "\nYou now have " + taskNo + " tasks in the list.\n"
              + horizontalLine;
    }

    /**
     * Displays formatted error response.
     */
    public String formattedErrorResponse(String errorMessage) {
        return horizontalLine + errorMessage + horizontalLine;
    }
}

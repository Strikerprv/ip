package dukeychatbot;

import dukeychatbot.tasktypes.Task;

import java.util.ArrayList;

/**
 * Constructs the UI class which deals with interactions with the user.
 *
 * @author dongjun
 */
public class Ui {

    private String HORIZONTAL_LINE = "______________________________________\n";

    public Ui() {}

    /**
     * Displays hello text to signal start of the chatbot.
     */
    public String hello(ArrayList<Task> tasks) {
        return  HORIZONTAL_LINE +
                "Hello! I'm Dukey\n" +
                "You have " + tasks.size() + " tasks in your list.\n" +
                "What can I do for you?\n" +
                HORIZONTAL_LINE;
    }

    /**
     * Displays bye text to signal the termination of the chatbot.
     */
    public String bye() {
        return  HORIZONTAL_LINE +
                "Bye. Hope to see you again soon!\n" +
                "This tab will close in 5 seconds.\n" +
                HORIZONTAL_LINE;
    }

    /**
     * Displays list of tasks.
     *
     * @param tasks ArrayList of type Task.
     */
    public String printList(String header, ArrayList<Task> tasks) {
        StringBuilder taskList = new StringBuilder();
        taskList.append(HORIZONTAL_LINE);
        for (int count = 1; count <= tasks.size(); count++) {
            Task currentTask = tasks.get(count - 1);
            taskList.append(count).append(". ").append(currentTask.toString()).append("\n");
        }
        taskList.append(HORIZONTAL_LINE);

        return taskList.toString();
    }

    /**
     * Displays error message for when task index is invalid.
     */
    public String invalidTaskIndex() {
        return  HORIZONTAL_LINE +
                "Task number exceeds the number of tasks! Please amend command!\n" +
                HORIZONTAL_LINE;
    }

    /**
     * Displays error message for when number format was inputted wrongly.
     */
    public String numberFormatError() {
        return  HORIZONTAL_LINE +
                "Mistake in task number input. Make sure you input an integer!\n" +
                HORIZONTAL_LINE;
    }

    /**
     * Displays message when no matching tasks are found.
     */
    public String noMatchingTasks() {
        return  HORIZONTAL_LINE +
                "We do not have any task descriptions matching your keyword!\n" +
                "Please try finding another keyword!\n" +
                HORIZONTAL_LINE;
    }

    public String chatboxClosedResponse() {
        return HORIZONTAL_LINE +
               "Chatbox has been terminated. Do re-open the chatbox if you wish to continue your chat!\n" +
               HORIZONTAL_LINE;
    }

    public String removeTaskResponse(String taskDescription, int taskNo) {
        return HORIZONTAL_LINE
               + "Understood. I have removed this task:\n    "
               + taskDescription
               + "\nYou now have " + taskNo + " tasks in the list.\n"
               + HORIZONTAL_LINE;
    }

    public String markDoneResponse(String taskDescription) {
        return HORIZONTAL_LINE
               + "Nice! I've marked this task as done.\n"
               + taskDescription
               + "\n"
               + HORIZONTAL_LINE;
    }

    public String unmarkDoneResponse(String taskDescription) {
        return HORIZONTAL_LINE
                + "Nice! I've unmarked this task as not done.\n   "
                + taskDescription
                + "\n"
                + HORIZONTAL_LINE;
    }

    public String addTaskResponse(String taskDescription, int taskNo) {
        return HORIZONTAL_LINE
              + "Understood. I have added the task:\n"
              + taskDescription
              + "\nYou now have " + taskNo + " tasks in the list.\n"
              + HORIZONTAL_LINE;
    }

    public String formattedErrorResponse(String errorMessage) {
        return HORIZONTAL_LINE + errorMessage + HORIZONTAL_LINE;
    }
}

package dukeychatbot;

import dukeychatbot.tasktypes.Task;

import java.util.ArrayList;

/**
 * Constructs the UI class which deals with interactions with the user.
 *
 * @author dongjun
 */
public class Ui {

    public Ui() {}

    /**
     * Displays hello text to signal start of the chatbot.
     */
    public void hello(ArrayList<Task> tasks) {
        System.out.println(
                "____________________________________________________________\n" +
                "Hello! I'm Dukey\n" +
                "You have " + tasks.size() + " tasks in your list.\n" +
                "What can I do for you?\n" +
                "____________________________________________________________\n");
    }

    /**
     * Displays bye text to signal the termination of the chatbot.
     */
    public void bye() {
        System.out.println(
                "____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
    }

    /**
     * Displays list of current tasks.
     *
     * @param tasks ArrayList of type Task.
     */
    public void printList(ArrayList<Task> tasks) {
        System.out.println("____________________________________________________________");
        for (int count = 1; count <= tasks.size(); count++) {
            Task currentTask = tasks.get(count - 1);
            System.out.println(count + ". " + currentTask.toString());
        }
        System.out.println("____________________________________________________________\n");
    }

    /**
     * Displays error message for when task index is invalid.
     */
    public void invalidTaskIndex() {
        System.out.println(
                "____________________________________________________________\n" +
                "Task number exceeds the number of tasks! Please amend command!\n" +
                "____________________________________________________________\n");
    }

    /**
     * Displays error message for when number format was inputted wrongly.
     */
    public void numberFormatError() {
        System.out.println(
                "____________________________________________________________\n" +
                "Mistake in task number input. Make sure you input an integer!\n" +
                "____________________________________________________________\n");
    }
}

package dukeychatbot;

import dukeychatbot.tasktypes.Task;

import java.util.ArrayList;

public class Ui {

    public Ui() {}

    public void hello(ArrayList<Task> tasks) {
        System.out.println(
                "____________________________________________________________\n" +
                " Hello! I'm Dukey\n" +
                " You have " + tasks.size() + " tasks in your list.\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");
    }

    public void bye() {
        System.out.println(
                "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
    }

    /**
     * Returns list of current tasks.
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

    public void invalidTaskIndex() {
        System.out.println(
                "____________________________________________________________\n" +
                "Task number exceeds the number of tasks! Please amend command!\n" +
                "____________________________________________________________\n");
    }
}

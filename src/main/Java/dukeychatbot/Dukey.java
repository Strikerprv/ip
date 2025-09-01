package dukeychatbot;

import dukeychatbot.dukeyexceptions.EmptyDescriptionException;
import dukeychatbot.dukeyexceptions.InvalidCommandException;
import dukeychatbot.dukeyexceptions.MissingDeadlineException;
import dukeychatbot.dukeyexceptions.MissingTimeframeException;
import dukeychatbot.tasktypes.Deadline;
import dukeychatbot.tasktypes.Event;
import dukeychatbot.tasktypes.Task;
import dukeychatbot.tasktypes.Todo;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Constructs a Chatbot called Dukey, which helps compile a to-do list.
 *
 * @author dongjun
 */
public class Dukey {

    /**
     * Returns Dukey chatbot.
     * Dukey helps users to compile a list of tasks,
     * with there being different types of tasks: todo, deadline and event.
     *
     */
    public static void main(String[] args) {
        String welcome =
                "____________________________________________________________\n" +
                " Hello! I'm Dukey\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        String bye =
                "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";

        System.out.println(welcome);

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        boolean isActive = true;

        while (isActive) {
            String command = sc.nextLine().trim();
            if (command.toLowerCase().equals("bye")) {
                System.out.println(bye);
                isActive = false;
            } else if (command.toLowerCase().equals("list")) {
                Dukey.printList(tasks);
            } else if (command.split(" ").length == 2 &&
                    command.split(" ")[0].toLowerCase().equals("mark")) {
                int taskNumber = Integer.parseInt(command.split(" ")[1]);
                if (taskNumber <= tasks.size()) {
                    markDone(tasks, taskNumber);
                } else {
                    System.out.println(
                            "____________________________________________________________\n" +
                            "dukeychatbot.tasktypes.Task number exceeds the number of tasks! Please amend command!\n" +
                            "____________________________________________________________\n");
                }
            } else if (command.split(" ").length == 2 &&
                    command.split(" ")[0].toLowerCase().equals("unmark")) {
                int taskNumber = Integer.parseInt(command.split(" ")[1]);
                if (taskNumber <= tasks.size()) {
                    unmarkDone(tasks, taskNumber);
                } else {
                    System.out.println(
                            "____________________________________________________________\n" +
                                    "dukeychatbot.tasktypes.Task number exceeds the number of tasks! Please amend command!\n" +
                            "____________________________________________________________\n");
                }
            } else if (command.split(" ").length == 2 &&
                    command.split(" ")[0].toLowerCase().equals("delete")) {
                int taskNumber = Integer.parseInt(command.split(" ")[1]);
                if (taskNumber <= tasks.size()) {
                    Dukey.removeTask(tasks, taskNumber);
                } else {
                    System.out.println(
                            "____________________________________________________________\n" +
                                    "dukeychatbot.tasktypes.Task number exceeds the number of tasks! Please amend command!\n" +
                                    "____________________________________________________________\n");
                }
            } else {
                try {
                    Dukey.addNewTask(tasks, command);
                } catch (InvalidCommandException | EmptyDescriptionException | MissingDeadlineException |
                        MissingTimeframeException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }


    /**
     * Returns list of current tasks.
     *
     * @param tasks ArrayList of type dukeychatbot.tasktypes.Task.
     */
    public static void printList(ArrayList<Task> tasks) {
        System.out.println("____________________________________________________________");
        for (int count = 1; count <= tasks.size(); count++) {
            Task currentTask = tasks.get(count - 1);
            System.out.println(count + ". " + currentTask.toString());
        }
        System.out.println("____________________________________________________________\n");
    }

    /**
     * Marks certain tasks as done.
     *
     * @param tasks ArrayList of type dukeychatbot.tasktypes.Task.
     * @param taskNumber Index of the task in the list.
     */
    public static void markDone(ArrayList<Task> tasks, int taskNumber) {
        Task currentTask = tasks.get(taskNumber - 1);
        currentTask.markDoneStatus();
        String successMessage =
                "____________________________________________________________\n" +
                        "Nice! I've marked this task as done.\n   " +
                        currentTask.toString() +
                        "\n____________________________________________________________\n";
        System.out.println(successMessage);
    }

    /**
     * Marks certain tasks as not done.
     *
     * @param tasks ArrayList of type dukeychatbot.tasktypes.Task.
     * @param taskNumber Index of the task in the list.
     */
    public static void unmarkDone(ArrayList<Task> tasks, int taskNumber) {
        Task currentTask = tasks.get(taskNumber - 1);
        currentTask.unmarkDoneStatus();
        String successMessage =
                "____________________________________________________________\n" +
                        "Nice! I've unmarked this task as not done.\n   " +
                        currentTask.toString() +
                        "\n____________________________________________________________\n";
        System.out.println(successMessage);
    }

    /**
     * Adds tasks to the list according to the task type.
     *
     * @param tasks ArrayList of type dukeychatbot.tasktypes.Task.
     * @param input User input for the new task.
     * @throws InvalidCommandException If command is not allowed.
     * @throws EmptyDescriptionException If task description is empty.
     * @throws MissingDeadlineException If deadline task did not indicate a deadline.
     * @throws MissingTimeframeException If event task did not indicate a time frame.
     */
    public static void addNewTask(ArrayList<Task> tasks, String input)
            throws InvalidCommandException, EmptyDescriptionException,
            MissingDeadlineException, MissingTimeframeException {
        Task newTask;

        switch (input.split(" ")[0].toLowerCase()) {
        case "todo" -> {
            String description = input.substring(input.indexOf(" ") + 1);
//            System.out.println("description: " + description);

            if (!input.trim().contains(" ")) {
                throw new EmptyDescriptionException();
            }

            newTask = new Todo(description);
            tasks.add(newTask);
        }
        case "deadline" -> {
            String description = input.substring(input.indexOf(" ") + 1);
//            System.out.println("description: " + description);

            if (!input.trim().contains(" ")) {
                throw new EmptyDescriptionException();
            } else if (!input.contains("/by")) {
                throw new MissingDeadlineException();
            }

            newTask = new Deadline(description);
            tasks.add(newTask);
        }
        case "event" -> {
            String description = input.substring(input.indexOf(" ") + 1);
//            System.out.println("description: " + description);

            if (!input.trim().contains(" ")) {
                throw new EmptyDescriptionException();
            } else if (!input.contains("/from") || !input.contains("/to")) {
                throw new MissingTimeframeException();
            }
            newTask = new Event(description);
            tasks.add(newTask);
        }
        default -> {
            throw new InvalidCommandException();
        }
        }

        System.out.println(
                "____________________________________________________________\n" +
                "Understood. I have added the task:\n    "
                + newTask.toString()
                + "\nYou now have " + tasks.size() + " tasks in the list."
                + "\n____________________________________________________________\n");
    }

    /**
     * Removes task from the list.
     *
     * @param tasks ArrayList of type dukeychatbot.tasktypes.Task.
     * @param taskNumber Index of the task in the list.
     */
    public static void removeTask(ArrayList<Task> tasks, int taskNumber) {
        System.out.println(
                "____________________________________________________________\n" +
                        "Understood. I have removed this task:\n    "
                        + tasks.get(taskNumber - 1).toString()
                        + "\nYou now have " + (tasks.size() - 1) + " tasks in the list."
                        + "\n____________________________________________________________\n");
        tasks.remove(taskNumber - 1);
    }
}


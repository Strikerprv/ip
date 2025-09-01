package dukeychatbot;

import dukeychatbot.dukeyexceptions.EmptyDescriptionException;
import dukeychatbot.dukeyexceptions.InvalidCommandException;
import dukeychatbot.dukeyexceptions.MissingDeadlineException;
import dukeychatbot.dukeyexceptions.MissingTimeframeException;
import dukeychatbot.tasktypes.Deadline;
import dukeychatbot.tasktypes.Event;
import dukeychatbot.tasktypes.Task;
import dukeychatbot.tasktypes.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

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
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        boolean isActive = true;

        String bye =
                "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";

        System.out.println(
                "____________________________________________________________\n" +
                " Hello! I'm Dukey\n" +
                " You have " + tasks.size() + " tasks in your list.\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");

        while (isActive) {
            String command = sc.nextLine().trim();
            if (command.toLowerCase().equals("bye")) {
                writeToFile(tasks);
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
                            "Task number exceeds the number of tasks! Please amend command!\n" +
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
                            "Task number exceeds the number of tasks! Please amend command!\n" +
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
                            "Task number exceeds the number of tasks! Please amend command!\n" +
                            "____________________________________________________________\n");
                }
            } else {
                try {
                    Dukey.addNewTask(tasks, command, false, false);
                } catch (InvalidCommandException | EmptyDescriptionException | MissingDeadlineException |
                        MissingTimeframeException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    /**
     * Reads from the dukey.txt file and initialises the task list.
     *
     * @param tasks ArrayList of type Task.
     */
    public static void readFromFile(ArrayList<Task> tasks) {
        try {
            File dukeyText = new File("./data/dukey.txt");
            Scanner myReader = new Scanner(dukeyText);
            while (myReader.hasNextLine()) {
                String input = myReader.nextLine();
                try {
                    // Need to format the input string so that it resembles a command
                    StringBuilder formattedCommand = new StringBuilder();

                    String[] inputArray = input.split(" ");
                    boolean taskIsDone = false;
                    if (inputArray[1].length() != 1) {
                        taskIsDone = true;
                    }

                    // Split according to the two [] and only retain the description portion
                    String description = input.split("]")[2].trim();
                    String type = String.valueOf(inputArray[0].charAt(1));

                    switch (type) {
                    case "T" -> {
                        formattedCommand.append("todo ");
                        formattedCommand.append(description);
                    }
                    case "D" -> {
                        formattedCommand.append("deadline ");
                        String[] splitDescription = description.split("\\(by:");
                        String deadline = splitDescription[1].trim();
                        String formattedDescription = splitDescription[0].trim() + " /by " +
                                deadline.substring(0, deadline.length() - 1);
                        formattedCommand.append(formattedDescription);
                    }
                    case "E" -> {
                        formattedCommand.append("event ");
                        String[] splitDescription = description.split("\\(from:");
                        String taskDescription = splitDescription[0].trim();
                        String[] timePeriod = splitDescription[1].split("to:");
                        String fromTime = timePeriod[0].trim();
                        String toTime = timePeriod[1].trim();
                        toTime = toTime.substring(0, toTime.length() - 1);
                        String compiledCommand = taskDescription + " /from " + fromTime + " /to " + toTime;
                        formattedCommand.append(compiledCommand);
                    }
                    }
                    Dukey.addNewTask(tasks, formattedCommand.toString(), taskIsDone, true);
                } catch (InvalidCommandException | EmptyDescriptionException | MissingDeadlineException |
                         MissingTimeframeException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File was not found: " + e.getMessage());
        }
    }
    /**
     * Saves the tasks in the hard disk by updating the dukey.txt file.
     *
     * @param tasks ArrayList of type Task.
     */
    public static void writeToFile(ArrayList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter("./data/dukey.txt");
            // Concatenate strings together to input into the text file
            StringBuilder resultText = new StringBuilder();

            for (int count = 1; count <= tasks.size(); count++) {
                Task currentTask = tasks.get(count - 1);
                if (count == tasks.size()) {
                    resultText.append(currentTask.toString());
                } else {
                    resultText.append(currentTask.toString()).append("\n");
                }
            }
            writer.write(resultText.toString());
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("File cannot be found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("An error occurred." + e.getMessage());
        }
    }

    /**
     * Returns list of current tasks.
     *
     * @param tasks ArrayList of type Task.
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
     * @param tasks ArrayList of type Task.
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
     * @param tasks ArrayList of type Task.
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
     * @param tasks ArrayList of type Task.
     * @param input User input for the new task.
     * @param isDone To initialise task list and indicate whether task has been completed already.
     * @param isInitialise State whether this method was for initialising the task list.
     * @throws InvalidCommandException If command is not allowed.
     * @throws EmptyDescriptionException If task description is empty.
     * @throws MissingDeadlineException If deadline task did not indicate a deadline.
     * @throws MissingTimeframeException If event task did not indicate a time frame.
     */
    public static void addNewTask(ArrayList<Task> tasks, String input, boolean isDone, boolean isInitialise)
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

            newTask = new Todo(description, isDone);
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

            newTask = new Deadline(description, isDone);
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
            newTask = new Event(description, isDone);
            tasks.add(newTask);
        }
        default -> {
            throw new InvalidCommandException();
        }
        }
        if (!isInitialise) {
            System.out.println(
                    "____________________________________________________________\n" +
                            "Understood. I have added the task:\n    "
                            + newTask.toString()
                            + "\nYou now have " + tasks.size() + " tasks in the list."
                            + "\n____________________________________________________________\n");
        }
    }

    /**
     * Removes task from the list.
     *
     * @param tasks ArrayList of type Task.
     * @param taskNumber Index of the task in the list.
     */
    public static void removeTask(ArrayList<Task> tasks, int taskNumber) {
        System.out.println(
                "____________________________________________________________\n" +
                "Understood. I have removed this task:\n    " +
                tasks.get(taskNumber - 1).toString() +
                "\nYou now have " + (tasks.size() - 1) + " tasks in the list." +
                "\n____________________________________________________________\n");
        tasks.remove(taskNumber - 1);
    }
}


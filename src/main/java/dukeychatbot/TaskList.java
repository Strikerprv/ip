package dukeychatbot;

import dukeychatbot.dukeyexceptions.EmptyDescriptionException;
import dukeychatbot.dukeyexceptions.InvalidCommandException;
import dukeychatbot.dukeyexceptions.MissingDeadlineException;
import dukeychatbot.dukeyexceptions.MissingTimeframeException;
import dukeychatbot.tasktypes.Deadline;
import dukeychatbot.tasktypes.Event;
import dukeychatbot.tasktypes.Task;
import dukeychatbot.tasktypes.Todo;

import java.util.ArrayList;

/**
 * Constructs the TaskList class which contains the task list and carry out task commands.
 *
 * @author dongjun
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskList(ArrayList<String> taskList) {
        this.initialiseTasks(taskList);
    }

    /**
     * Initialises the task list from the storage.
     *
     * @param taskList ArrayList of type String.
     */
    private void initialiseTasks(ArrayList<String> taskList) {
        for (String input : taskList) {
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
                this.addNewTask(formattedCommand.toString(), taskIsDone, true);
            } catch (InvalidCommandException | EmptyDescriptionException | MissingDeadlineException |
                     MissingTimeframeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Returns ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Adds new task to the tasks ArrayList.
     *
     * @param input User input
     * @param isDone Task completion status
     * @param isInitialise Whether this function is called to initialise the task list.
     */
    public void addNewTask(String input, boolean isDone, boolean isInitialise)
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
            this.tasks.add(newTask);
        }
        case "deadline" -> {
            String description = input.substring(input.indexOf(" ") + 1);
//            System.out.println("description: " + description);

            if (!input.trim().contains(" ")) {
                throw new EmptyDescriptionException();
            } else if (!input.contains("/by")) {
                throw new MissingDeadlineException();
            }
//            int index = -1;
//            for (int i = 0; i < deadlineTimeArray.length; i++) {
//                if (deadlineTimeArray[i].length() == 10 && deadlineTimeArray[i].matches(DATE_PATTERN)) {
//                    date = deadlineTimeArray[i];
//                    try {
//                        LocalDate dateInput = LocalDate.parse(date);
//                        dateInput.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
//                        hasDate = true;
//                        index = i;
//                        break;
//                    } catch (DateTimeParseException e) {
//                        System.out.println("Date parsed failed. Error message: " + e.getMessage());
//                    }
//                }
//            }
            newTask = new Deadline(description, isDone);
            this.tasks.add(newTask);
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
            this.tasks.add(newTask);
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
     * Removes task from tasks ArrayList.
     */
    public void removeTask(int taskNumber) {
        System.out.println(
                "____________________________________________________________\n" +
                        "Understood. I have removed this task:\n    " +
                        tasks.get(taskNumber - 1).toString() +
                        "\nYou now have " + (tasks.size() - 1) + " tasks in the list." +
                        "\n____________________________________________________________\n");
        tasks.remove(taskNumber - 1);
    }

    /**
     * Marks task as done.
     */
    public void markDone(int taskNumber) {
        Task currentTask = this.tasks.get(taskNumber - 1);
        currentTask.markDoneStatus();
        String successMessage =
                "____________________________________________________________\n" +
                        "Nice! I've marked this task as done.\n   " +
                        currentTask.toString() +
                        "\n____________________________________________________________\n";
        System.out.println(successMessage);
    }

    /**
     * Marks task as not done.
     */
    public void unmarkDone(int taskNumber) {
        Task currentTask = this.tasks.get(taskNumber - 1);
        currentTask.unmarkDoneStatus();
        String successMessage =
                "____________________________________________________________\n" +
                        "Nice! I've unmarked this task as not done.\n   " +
                        currentTask.toString() +
                        "\n____________________________________________________________\n";
        System.out.println(successMessage);
    }

    /**
     * Returns an ArrayList of tasks which have descriptions with the keyword in it.
     *
     * @param keyword Keyword to find
     * @return ArrayList of tasks with description that has the keyword in it.
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>(this.tasks); // Creates a shallow copy

        for (int i = matchingTasks.size() - 1; i >= 0; i--) {
            if (!matchingTasks.get(i).match(keyword)) {
                matchingTasks.remove(i);
            }
        }
        return matchingTasks;
    }
}

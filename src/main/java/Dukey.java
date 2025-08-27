import Exceptions.EmptyDescriptionException;
import Exceptions.InvalidCommandException;
import Exceptions.MissingDeadlineException;
import Exceptions.MissingTimeframeException;

import java.util.Scanner;
import java.util.ArrayList;

public class Dukey {

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
        ArrayList<Task> todoList = new ArrayList<>();
        boolean active = true;

        while (active) {
            String command = sc.nextLine().trim();
            if (command.toLowerCase().equals("bye")) {
                System.out.println(bye);
                active = false;
            } else if (command.toLowerCase().equals("list")) {
                Dukey.printList(todoList);
            } else if (command.split(" ").length == 2 &&
                    command.split(" ")[0].toLowerCase().equals("mark")) {
                int taskNumber = Integer.parseInt(command.split(" ")[1]);
                if (taskNumber <= todoList.size()) {
                    markDone(todoList, taskNumber);
                } else {
                    System.out.println(
                            "____________________________________________________________\n" +
                            "Task number exceeds the number of tasks! Please amend command!\n" +
                            "____________________________________________________________\n");
                }
            } else if (command.split(" ").length == 2 &&
                    command.split(" ")[0].toLowerCase().equals("unmark")) {
                int taskNumber = Integer.parseInt(command.split(" ")[1]);
                if (taskNumber <= todoList.size()) {
                    unmarkDone(todoList, taskNumber);
                } else {
                    System.out.println(
                            "____________________________________________________________\n" +
                                    "Task number exceeds the number of tasks! Please amend command!\n" +
                            "____________________________________________________________\n");
                }
            } else if (command.split(" ").length == 2 &&
                    command.split(" ")[0].toLowerCase().equals("delete")) {
                int taskNumber = Integer.parseInt(command.split(" ")[1]);
                if (taskNumber <= todoList.size()) {
                    Dukey.removeTask(todoList, taskNumber);
                } else {
                    System.out.println(
                            "____________________________________________________________\n" +
                                    "Task number exceeds the number of tasks! Please amend command!\n" +
                                    "____________________________________________________________\n");
                }
            } else {
                try {
                    Dukey.addNewTask(command, todoList);
                } catch (InvalidCommandException | EmptyDescriptionException | MissingDeadlineException |
                        MissingTimeframeException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public static void printList(ArrayList<Task> todoList) {
        System.out.println("____________________________________________________________");
        for (int count = 1; count <= todoList.size(); count++) {
            Task currentTask = todoList.get(count - 1);
            System.out.println(count + ". " + currentTask.toString());
        }
        System.out.println("____________________________________________________________\n");
    }

    public static void markDone(ArrayList<Task> todoList, int taskNumber) {
        Task currentTask = todoList.get(taskNumber - 1);
        currentTask.markDoneStatus();
        String successMessage =
                "____________________________________________________________\n" +
                        "Nice! I've marked this task as done.\n   " +
                        currentTask.toString() +
                        "\n____________________________________________________________\n";
        System.out.println(successMessage);
    }

    public static void unmarkDone(ArrayList<Task> todoList, int taskNumber) {
        Task currentTask = todoList.get(taskNumber - 1);
        currentTask.unmarkDoneStatus();
        String successMessage =
                "____________________________________________________________\n" +
                        "Nice! I've unmarked this task as not done.\n   " +
                        currentTask.toString() +
                        "\n____________________________________________________________\n";
        System.out.println(successMessage);
    }

    public static void addNewTask(String input, ArrayList<Task> todoList)
            throws InvalidCommandException, EmptyDescriptionException,
            MissingDeadlineException, MissingTimeframeException {
        Task newTask;

        switch (input.split(" ")[0].toLowerCase()) {
            case "todo" -> {
                String description = input.substring(input.indexOf(" ") + 1);
                System.out.println("description: " + description);

                if (!input.trim().contains(" ")) {
                    throw new EmptyDescriptionException();
                }

                newTask = new Todo(description);
                todoList.add(newTask);
            }
            case "deadline" -> {
                String description = input.substring(input.indexOf(" ") + 1);
                System.out.println("description: " + description);

                if (!input.trim().contains(" ")) {
                    throw new EmptyDescriptionException();
                } else if (!input.contains("/by")) {
                    throw new MissingDeadlineException();
                }

                newTask = new Deadline(description);
                todoList.add(newTask);
            }
            case "event" -> {
                String description = input.substring(input.indexOf(" ") + 1);
                System.out.println("description: " + description);

                if (!input.trim().contains(" ")) {
                    throw new EmptyDescriptionException();
                } else if (!input.contains("/from") || !input.contains("/to")) {
                    throw new MissingTimeframeException();
                }
                newTask = new Event(description);
                todoList.add(newTask);
            }
            default -> {
                throw new InvalidCommandException();
            }
        }

        System.out.println(
                "____________________________________________________________\n" +
                "Understood. I have added the task:\n    "
                + newTask.toString()
                + "\nYou now have " + todoList.size() + " tasks in the list."
                + "\n____________________________________________________________\n");
    }

    public static void removeTask(ArrayList<Task> todoList, int taskNumber) {
        System.out.println(
                "____________________________________________________________\n" +
                        "Understood. I have removed this task:\n    "
                        + todoList.get(taskNumber - 1).toString()
                        + "\nYou now have " + (todoList.size() - 1) + " tasks in the list."
                        + "\n____________________________________________________________\n");
        todoList.remove(taskNumber - 1);
    }
}


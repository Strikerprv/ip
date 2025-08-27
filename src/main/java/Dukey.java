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
            String command = sc.nextLine();
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
            } else {
                    todoList.add(new Task(command));
                    System.out.println(
                            "____________________________________________________________\n" +
                                    "added: " + command +
                                    "\n____________________________________________________________\n");
            }
        }
    }

    public static void printList(ArrayList<Task> todoList) {
        System.out.println("____________________________________________________________");
        for (int count = 1; count <= todoList.size(); count++) {
            Task currentTask = todoList.get(count - 1);
            System.out.println(count + ". [" + currentTask.getStatusIcon() + "] " + currentTask.getDescription());
        }
        System.out.println("____________________________________________________________\n");
    }

    public static void markDone(ArrayList<Task> todoList, int taskNumber) {
        Task currentTask = todoList.get(taskNumber - 1);
        currentTask.markDoneStatus();
        String successMessage =
                "____________________________________________________________\n" +
                        "Nice! I've marked this task as done.\n" +
                        "[" + currentTask.getStatusIcon() + "] " + currentTask.getDescription() +
                        "\n____________________________________________________________\n";
        System.out.println(successMessage);
    }

    public static void unmarkDone(ArrayList<Task> todoList, int taskNumber) {
        Task currentTask = todoList.get(taskNumber - 1);
        currentTask.unmarkDoneStatus();
        String successMessage =
                "____________________________________________________________\n" +
                        "Nice! I've unmarked this task as not done.\n" +
                        "[" + currentTask.getStatusIcon() + "] " + currentTask.getDescription() +
                        "\n____________________________________________________________\n";
        System.out.println(successMessage);
    }
}


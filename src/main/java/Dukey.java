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
        ArrayList<String> todoList = new ArrayList<>();
        boolean active = true;

        while (active) {
            String command = sc.nextLine();
            if (command.toLowerCase().equals("bye")) {
                System.out.println(bye);
                active = false;
            } else if (command.toLowerCase().equals("list")) {
                System.out.println("____________________________________________________________");
                for (int count = 1; count <= todoList.size(); count++) {
                    System.out.println(count + ". " + todoList.get(count-1));
                }
                System.out.println("____________________________________________________________\n");
            } else {
                    todoList.add(command);
                    System.out.println(
                            "____________________________________________________________\n" +
                                    "added: " + command +
                                    "\n____________________________________________________________\n");
            }
        }
    }
}


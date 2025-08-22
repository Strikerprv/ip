import java.util.Scanner;

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
        boolean active = true;

        while (active) {
            String command = sc.nextLine();
            if (command.toLowerCase().equals("bye")) {
                System.out.println(bye);
                active = false;
            } else {
                System.out.println(
                        "____________________________________________________________\n" +
                                command +
                                "\n____________________________________________________________\n");
            }
        }
    }
}

package dukeychatbot;

import dukeychatbot.dukeyexceptions.EmptyDescriptionException;
import dukeychatbot.dukeyexceptions.InvalidCommandException;
import dukeychatbot.dukeyexceptions.MissingDeadlineException;
import dukeychatbot.dukeyexceptions.MissingTimeframeException;

import java.util.Scanner;

/**
 * Constructs a Chatbot called Dukey, which helps compile a to-do list.
 *
 * @author dongjun
 */
public class Dukey {
    private TaskList taskArray;
    private Ui ui;
    private Storage storage;
    private Parser parser;

    public Dukey(String filePath) {
        this.storage = new Storage(filePath);
        this.taskArray = new TaskList(this.storage.load());
        this.ui = new Ui();
        this.parser = new Parser(this.taskArray, this.ui, this.storage);
    }

    /**
     * Returns Dukey chatbot.
     * Dukey helps users to compile a list of tasks,
     * with there being different types of tasks: todo, deadline and event.
     */
    public static void main(String[] args) {
        new Dukey("./data/dukey.txt").run();
    }

    /**
     * Runs the chatbot to start taking in user input.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        boolean isActive = true;

        this.ui.hello(taskArray.getTasks());

        while (this.parser.getActiveStatus()) {
            String command = sc.nextLine().trim();
            this.parser.parse(command);
        }
    }
}


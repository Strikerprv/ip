package dukeychatbot;

import dukeychatbot.dukeyexceptions.EmptyDescriptionException;
import dukeychatbot.dukeyexceptions.InvalidCommandException;
import dukeychatbot.dukeyexceptions.MissingDeadlineException;
import dukeychatbot.dukeyexceptions.MissingTimeframeException;
import dukeychatbot.tasktypes.Task;

import java.util.ArrayList;

/**
 * Constructs the Parser class which deals with making sense of the user command.
 *
 * @author dongjun
 */
public class Parser {
    private TaskList taskArray;
    private Ui ui;
    private boolean isActive;
    private Storage storage;

    public Parser(TaskList taskArray, Ui ui, Storage storage) {
        this.taskArray = taskArray;
        this.ui = ui;
        this.isActive = true;
        this.storage = storage;
    }

    /**
     * Parses the user input and carries out the corresponding commands.
     *
     * @param fullCommand Command input from user
     */
    public void parse(String fullCommand) {
        String command = fullCommand.trim();
        if (command.equalsIgnoreCase("bye")) {
            this.byeCommand();
            return;
        } else if (command.equalsIgnoreCase("list")) {
            this.ui.printList("", taskArray.getTasks());
            return;
        } else if (command.split(" ").length == 2) {
            try {
                if (command.split(" ")[0].equalsIgnoreCase("mark")) {
                    int taskNumber = Integer.parseInt(command.split(" ")[1]);
                    this.markCommand(taskNumber);
                    return;
                } else if (command.split(" ")[0].equalsIgnoreCase("unmark")) {
                    int taskNumber = Integer.parseInt(command.split(" ")[1]);
                    this.unmarkCommand(taskNumber);
                    return;
                } else if (command.split(" ")[0].equalsIgnoreCase("delete")) {
                    int taskNumber = Integer.parseInt(command.split(" ")[1]);
                    this.delete(taskNumber);
                    return;
                } else if (command.split(" ")[0].equalsIgnoreCase("find")) {
                    String keyword = command.split(" ")[1];
                    this.find(keyword);
                    return;
                }
            } catch (NumberFormatException e) {
                this.ui.numberFormatError();
                return;
            }
        }
        try {
            this.taskArray.addNewTask(command, false, false);
        } catch (InvalidCommandException | EmptyDescriptionException | MissingDeadlineException |
                 MissingTimeframeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Finds the task descriptions with the keyword in it.
     *
     * @param keyword Keyword to find
     */
    public void find(String keyword) {
        ArrayList<Task> matchingArray = this.taskArray.find(keyword);
        if (matchingArray.isEmpty()) {
            this.ui.noMatchingTasks();
        } else {
            this.ui.printList("\nHere are the matching tasks in your list: ", matchingArray);
        }
    }

    /**
     * Deletes the task corresponding to the task number from the task list.
     *
     * @param taskNumber task number
     */
    public void delete(int taskNumber) {
        if (taskNumber <= taskArray.getTasks().size()) {
            this.taskArray.removeTask(taskNumber);
        } else {
            this.ui.invalidTaskIndex();
        }
    }

    /**
     * Marks the task corresponding to the task number as not done.
     *
     * @param taskNumber task number
     */
    public void unmarkCommand(int taskNumber) {
        if (taskNumber <= taskArray.getTasks().size()) {
            this.taskArray.unmarkDone(taskNumber);
        } else {
            this.ui.invalidTaskIndex();
        }
    }

    /**
     * Marks the task corresponding to the task number as done.
     *
     * @param taskNumber task number
     */
    public void markCommand(int taskNumber) {
        if (taskNumber <= this.taskArray.getTasks().size()) {
            this.taskArray.markDone(taskNumber);
        } else {
            this.ui.invalidTaskIndex();
        }
    }

    /**
     * Returns the goodbye text on the UI and store the task list to the hard drive.
     */
    public void byeCommand() {
        this.ui.bye();
        this.isActive = false;
        this.storage.save(taskArray.getTasks());
    }

    /**
     * Returns the Active status of the chatbot.
     *
     * @return Active status
     */
    public boolean getActiveStatus() {
        return this.isActive;
    }
}

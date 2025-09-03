package dukeychatbot;

public class Parser {
    private String currentInput;
    private TaskList taskArray;
    private Ui ui;
    private boolean isActive;
    private Storage storage;

    public Parser(TaskList taskArray, Ui ui, Storage storage) {
        this.currentInput = "";
        this.taskArray = taskArray;
        this.ui = ui;
        this.isActive = true;
        this.storage = storage;
    }

    public void parse(String fullCommand) {
        String command = fullCommand.trim();
        if (command.equalsIgnoreCase("bye")) {
            this.byeCommand();
        } else if (command.equalsIgnoreCase("list")) {
            this.ui.printList(taskArray.getTasks());
        } else if (command.split(" ").length == 2) {
            if (command.split(" ")[0].equalsIgnoreCase("mark")) {
                int taskNumber = Integer.parseInt(command.split(" ")[1]);
                this.markCommand(taskNumber);
            } else if (command.split(" ")[0].equalsIgnoreCase("unmark")) {
                int taskNumber = Integer.parseInt(command.split(" ")[1]);
                this.unmarkCommand(taskNumber);
            } else if (command.split(" ")[0].equalsIgnoreCase("delete")) {
                int taskNumber = Integer.parseInt(command.split(" ")[1]);
                this.delete(taskNumber);
            }
        }
    }

    public void delete(int taskNumber) {
        if (taskNumber <= taskArray.getTasks().size()) {
            this.taskArray.removeTask(taskNumber);
        } else {
            this.ui.invalidTaskIndex();
        }
    }

    public void unmarkCommand(int taskNumber) {
        if (taskNumber <= taskArray.getTasks().size()) {
            this.taskArray.unmarkDone(taskNumber);
        } else {
            this.ui.invalidTaskIndex();
        }
    }

    public void markCommand(int taskNumber) {
        if (taskNumber <= this.taskArray.getTasks().size()) {
            this.taskArray.markDone(taskNumber);
        } else {
            this.ui.invalidTaskIndex();
        }
    }

    public void byeCommand() {
        this.ui.bye();
        this.isActive = false;
        this.storage.save(taskArray.getTasks());
    }



}

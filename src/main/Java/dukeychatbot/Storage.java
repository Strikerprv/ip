package dukeychatbot;

import dukeychatbot.tasktypes.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;
    private ArrayList<String> fileContent = new ArrayList<>();

    public Storage(String filePath) {
        this.filePath = filePath;
        try {
            File dukeyText = new File(this.filePath);
            Scanner myReader = new Scanner(dukeyText);
            while (myReader.hasNextLine()) {
                String input = myReader.nextLine();
                fileContent.add(input);
            }
        } catch (NullPointerException | FileNotFoundException e) {
            System.out.println("Loading hard disk file failed, error: " + e.getMessage());
        }
    }

    public ArrayList<String> load() {
        return this.fileContent;
    }

    public void save(ArrayList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(this.filePath);
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
            System.out.println("An error occurred while saving. Error: " + e.getMessage());
        }
    }
}

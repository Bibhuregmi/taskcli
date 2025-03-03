package main.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import main.models.Task;

public class FileHandling {
    private static final String DIRECTORY = "data"; //folder that will contain all the saved tasks
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yy-MM-dd-(HH:mm)");

    //checking if the directory exists
    static{ //try-catch are outside of the method defination so it is wrapped in the static block
        try{
            Files.createDirectories(Paths.get(DIRECTORY));
        }catch(IOException exception){
            System.out.println(ANSI.RED_BOLD + "Error creating data directory: " + exception.getMessage() + ANSI.RESET);
        }
    }

    //method to save tasks to the file
    public static void saveTasktoFile(Task task){
        String timestamp = LocalDateTime.now().format(FORMATTER);
        String filename = DIRECTORY + "/" + "task- "+task.getId() + "-" + timestamp + ".txt";

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {    
            writer.write("id: " + task.getId() + "\n");
            writer.write("Task title: " + task.getTitle() + "\n");
            writer.write("Task description: " + task.getDescription() + "\n");
            writer.write("Task status: " + task.getStatus() + "\n");
        } catch (IOException e) {
            System.out.println(ANSI.RED_BOLD + "Error saving task: " + e.getMessage());
        }
    }

    //reading all the tasks from the folder
    public static List<Task> loadTasksFromFiles(){
        List<Task> tasks = new ArrayList<>();
        File folder = new File(DIRECTORY); 

        File[] files = folder.listFiles(); 
        if(files != null){
            for(File file : files){
                try (BufferedReader reader = new BufferedReader(new FileReader(file))){
                    int id = Integer.parseInt(reader.readLine());
                    String title = reader.readLine();
                    String description = reader.readLine();
                    Boolean status = Boolean.parseBoolean(reader.readLine());
                    tasks.add(new Task(id, title, description, status));
                } catch (Exception e) {
                    System.out.println(ANSI.RED_BOLD + "Error reading task file" + ANSI.RESET);
                }
            }
        }
        return tasks;
    }

    //method to deleted all task files
    public static boolean deleteTaskFiles(int id){
        File folder = new File(DIRECTORY); 
        File[] files = folder.listFiles((dir, name) -> name.startsWith(id + "-"));

        if(files != null){
            for(File file : files){
                if(file.delete()){
                    return true; 
                }
            }
        }
        return false;
    }
}


//BufferedWriter and FileWriter are the type of Character Streams that writes characters to the files
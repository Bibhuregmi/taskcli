package main.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.models.Task;

public class FileHandling {
    private static final String DIRECTORY = "data"; //folder that will contain all the saved tasks
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yy-MM-dd-(HH:mm)");
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yy-MM-dd-(HH:mm)");

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
        String filename = DIRECTORY + "/" + "task-"+task.getId() + ".txt";

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {    
            writer.write("id: " + task.getId() + "\n");
            writer.write("Task title: " + task.getTitle() + "\n");
            writer.write("Task description: " + task.getDescription() + "\n");
            writer.write("Task status: " + task.getStatus() + "\n");
            writer.write("Task Created At: " + timestamp + "\n" );
            System.out.println("Task saved to file : " + filename);
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
                    int id = Integer.parseInt(reader.readLine().split(": ")[1]); //insted of directly parsing the line, splitting it and extracting only the reqired values.
                    String title = reader.readLine().split(": ")[1];
                    String description = reader.readLine().split(": ")[1];
                    Boolean status = Boolean.parseBoolean(reader.readLine().split(": ")[1]);
                    Date timestamp = DATE_FORMAT.parse(reader.readLine().split(": ")[1]);
                    tasks.add(new Task(id, title, description, status, timestamp));
                } catch (Exception e) {
                    System.out.println(ANSI.RED_BOLD + "Error reading task file" + ANSI.RESET + e);
                }
            }
        }
        return tasks;
    }

    //method to deleted all task files
    public static boolean deleteTaskFiles(int id){
        File folder = new File(DIRECTORY); 
        File[] files = folder.listFiles((dir, name) -> name.startsWith("task-" + id + ".txt"));

        if(files != null){
            for(File file : files){
                if(file.delete()){
                    return true; 
                }
            }
        }
        return false;
    }

    public static boolean updateStatusCompleted(int id){
        File folder = new File(DIRECTORY);
        File[] files = folder.listFiles((dir, name) -> name.startsWith("task-" + id + ".txt"));

        if(files == null || files.length == 0){
            System.out.println(ANSI.RED_BOLD + "No task found with ID " + id + " to update!" + ANSI.RESET);
            return false; 
        } 

        for(File file : files){
            try {
                //before trying to modify, extracting the value by reading the file
                BufferedReader reader = new BufferedReader(new FileReader(file));
                List<String> lines = new ArrayList<>(); 
                String line; 

                while((line = reader.readLine()) != null){
                    lines.add(line);
                }
                reader.close();

                //modifying the status line
                for(int i =0; i< lines.size(); i++){
                    if(lines.get(i).startsWith("Task status: ")){
                        lines.set(i, "Task status: true"); //marking the status to true
                        break;
                    }
                }
                //once modified, writing the updated content back to the file
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                for(String updateLine : lines){
                    writer.write(updateLine);
                    writer.newLine();
                }
                writer.close();
                System.out.println(ANSI.GREEN_BOLD + "Task with ID " + id + " marked as completed" + ANSI.RESET);
                return true; 

            } catch (Exception e) {
                System.out.println(ANSI.RED_BOLD + "Error updating task status: " + e.getMessage() + ANSI.RESET);
                return false;
            }
        }
        return false; 
    }
    
    public static boolean updateStatusInComplete(int id){
        File folder = new File(DIRECTORY);
        File[] files = folder.listFiles((dir, name) -> name.startsWith("task-" + id + ".txt"));

        if(files == null || files.length == 0){
            System.out.println(ANSI.RED_BOLD + "No task found with ID " + id + " to update!" + ANSI.RESET);
            return false; 
        } 

        for(File file : files){
            try {
                //before trying to modify, extracting the value by reading the file
                BufferedReader reader = new BufferedReader(new FileReader(file));
                List<String> lines = new ArrayList<>(); 
                String line; 

                while((line = reader.readLine()) != null){
                    lines.add(line);
                }
                reader.close();

                //modifying the status line
                for(int i =0; i< lines.size(); i++){
                    if(lines.get(i).startsWith("Task status: ")){
                        lines.set(i, "Task status: false"); //marking the status to false
                        break;
                    }
                }
                //once modified, writing the updated content back to the file
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                for(String updateLine : lines){
                    writer.write(updateLine);
                    writer.newLine();
                }
                writer.close();
                System.out.println(ANSI.GREEN_BOLD + "Task with ID " + id + " marked as incomplete" + ANSI.RESET);
                return true; 

            } catch (Exception e) {
                System.out.println(ANSI.RED_BOLD + "Error updating task status: " + e.getMessage() + ANSI.RESET);
                return false;
            }
        }
        return false; 
    }
}


//BufferedWriter and FileWriter are the type of Character Streams that writes characters to the files
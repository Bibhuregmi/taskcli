package main.models;
import main.utils.ANSI;

import java.util.List;
import main.utils.FileHandling;

public class TaskManager {
    private List<Task> tasks;
    
    //constructor 
    TaskManager(){
        this.tasks = FileHandling.loadTasksFromFiles(); //loadin tasks from the files
    }

    //method to add new task 
    public void addTask(Task task){
        //added check for the id
        for(Task t: tasks){
            if(t.getId() == task.getId()){
                System.out.println(ANSI.RED_BOLD + "The task with id " + t.getId() + " already exists" + ANSI.RESET);
                return; 
            }
        }
        tasks.add(task);
        FileHandling.saveTasktoFile(task); //saving the task to the file
        System.out.println(ANSI.GREEN_BOLD + "New task has been created!" + ANSI.RESET); //.RESET ensures that only the intended text is styled and rest of the output remains same
    }

    //method to remove the task
    public int removeTask(int id){
        FileHandling.deleteTaskFiles(id);
        tasks.removeIf(task -> task.getId() == id); //this will remove only if the id of the targeted task matches with the passed id
        return id; 
    }
    
    //method to show all the task
    public void showAllTasks(){
        List<Task> loadedTask = FileHandling.loadTasksFromFiles();
        if(loadedTask.isEmpty()){
            System.out.println(ANSI.RED_BOLD + "No any  tasks to show" + ANSI.RESET);
            return; 
        }
        for(Task task : loadedTask){
            System.out.println(task.toString());
            System.out.println("==================================================\n");
        }
    }

    //method to set completed stauts 
    public void setStatusCompleted(int id){
        if(tasks.isEmpty()){
            System.out.println(ANSI.RED_BOLD + "No any tasks were found to remove! Please add tasks before changing stautus!" + ANSI.RESET);
            return;
        }
        boolean found = false; //flag to check if the task exists   
        for(Task task : tasks){
            if(task.getId() == id){
                task.markCompleted(); // marking the task as completed in memory
                FileHandling.updateStatusCompleted(id);
                found = true; 
                break; 
            }
        }
        if(!found) System.out.println(ANSI.RED_BOLD + "No any task with id " + id + " was found! Please input correct id again" + ANSI.RESET);
    }

    //method to set incompleted status
    public void setStautsIncomplete(int id){
        if(tasks.isEmpty()){
            System.out.println(ANSI.RED_BOLD + "No any tasks were found to remove! Please add tasks before changing stautus!" + ANSI.RESET);
            return;
        }
        boolean found = false; //flag to check if the task exists   
        for(Task task : tasks){
            if(task.getId() == id){
                task.markIncomplete(); // marking the task as incomplete in memory
                FileHandling.updateStatusInComplete(id);
                found = true; 
                break; 
            }
        }
        if(!found) System.out.println(ANSI.RED_BOLD + "No any task with id " + id + " was found! Please input correct id again" + ANSI.RESET);
    }
}


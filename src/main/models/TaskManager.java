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
        tasks.add(task);
        FileHandling.saveTasktoFile(task); //saving the task to the file
        System.out.println(ANSI.GREEN_BOLD + "New task has been created!" + ANSI.RESET); //.RESET ensures that only the intended text is styled and rest of the output remains same
    }

    //method to remove the task
    public int removeTask(int id){
        if(tasks.isEmpty()){
            System.out.println(ANSI.RED_BOLD + "No any tasks were found to remove! Please add tasks before removing!" + ANSI.RESET);
        }
        for(Task task : tasks){
            if(task.getId() != id){
                System.out.println(ANSI.RED_BOLD + "No any task with id " + id + " was found! Please input correct id again" + ANSI.RESET);
            }else{
                FileHandling.deleteTaskFiles(id); 
                System.out.println(ANSI.GREEN_BOLD +"Task with id " + id + " was removed successfully!" + ANSI.RESET);
            }
        }
        return id; 
    }
    
    //method to show all the task
    public void showAllTasks(){
        if(tasks.isEmpty()){
            System.out.println(ANSI.RED_BOLD + "No any  tasks to show" + ANSI.RESET);
        }else{
            for(Task task : tasks){
                System.out.println(task.toString());
            }
        }
    }

    //method to set completed stauts 
    public void setStatusCompleted(int id){
        if(tasks.isEmpty()){
            System.out.println(ANSI.RED_BOLD + "No any tasks were found to remove! Please add tasks before changing stautus!" + ANSI.RESET);
        }for(Task task : tasks){
            if(task.getId() != id){
                System.out.println(ANSI.RED_BOLD + "No any task with id " + id + " was found! Please input correct id again" + ANSI.RESET);
            }else{
                task.markCompleted();
                System.out.println(ANSI.GREEN_BOLD + "Task with id " + id + " is marked completed!" + ANSI.RESET);
            }
        }
    }

    //method to set incompleted status
    public void setStautsIncomplete(int id){
        if(tasks.isEmpty()){
            System.out.println(ANSI.RED_BOLD + "No any tasks were found to remove! Please add tasks before changing stautus!" + ANSI.RESET);
        }for(Task task : tasks){
            if(task.getId() != id){
                System.out.println(ANSI.RED_BOLD + "No any task with id " + id + " was found! Please input correct id again" + ANSI.RESET);
            }else{
                task.markIncomplete();
                System.out.println(ANSI.GREEN_BOLD + "Task with id " + id + " is marked incomplete!" + ANSI.RESET);
            }
        }
    }
}


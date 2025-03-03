package main;

import java.util.concurrent.CopyOnWriteArrayList;;

public class TaskManager {
    private CopyOnWriteArrayList<Task> tasks;
    
    //constructor 
    TaskManager(){
        this.tasks = new CopyOnWriteArrayList<>(); 
    }

    //method to add new task 
    public void addTask(Task task){
        tasks.add(task);
        System.out.println("New task has been created!");
    }

    //method to remove the task
    public int removeTask(int id){
        if(tasks.isEmpty()){
            System.out.println("No any tasks were found to remove! Please add tasks before removing!");
        }
        for(Task task : tasks){
            if(task.getId() != id){
                System.out.println("No any task with id " + id + " was found! Please input correct id again");
            }else{
                tasks.remove(task);
                System.out.println("Task with id " + id + " was removed successfully!" );
            }
        }
        return id; 
    }
    
    //method to show all the task
    public void showAllTasks(){
        if(tasks.isEmpty()){
            System.out.println("No any  tasks to show");
        }else{
            for(Task task : tasks){
                System.out.println(task.toString());
            }
        }
    }

    //method to set completed stauts 
    public void setStatusCompleted(int id){
        if(tasks.isEmpty()){
            System.out.println("No any tasks were found to remove! Please add tasks before changing stautus!");
        }for(Task task : tasks){
            if(task.getId() != id){
                System.out.println("No any task with id " + id + " was found! Please input correct id again");
            }else{
                task.markCompleted();
                System.out.println("Task with id " + id + " is marked completed!" );
            }
        }
    }

    //method to set incompleted status
    public void setStautsIncomplete(int id){
        if(tasks.isEmpty()){
            System.out.println("No any tasks were found to remove! Please add tasks before changing stautus!");
        }for(Task task : tasks){
            if(task.getId() != id){
                System.out.println("No any task with id " + id + " was found! Please input correct id again");
            }else{
                task.markIncomplete();
                System.out.println("Task with id " + id + " is marked incomplete!" );
            }
        }
    }
}

// TODO: Require proper formatting of the toString() method  

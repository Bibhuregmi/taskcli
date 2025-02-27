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
        for(Task task : tasks) {
            if(task.getId() == id){
                tasks.remove(task); 
                System.out.println("Task with id: " + id + " has been removed!" );
            }else{
                System.out.println("Invalid id!");

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
                System.out.println(task);
            }
        }
    }

    //method to set completed stauts 
    public void setStatusCompleted(int id){
        for(Task task : tasks){
            if(task.getId() == id){
                task.markCompleted();
                System.out.println("Task with id " + id + " has been completed sucessfully!" );
            }
        }
    }

    //method to set incompleted status
    public void setStautsIncomplete(int id){
        for(Task task : tasks){
            if(task.getId() == id){
                task.markIncomplete();
                System.out.println("Task with id " + id + " is has been marked incomplete! ");
            }
        }
    }
}

// TODO: Require proper formatting of the toString() method

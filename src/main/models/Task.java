package main.models;
import java.util.Date;

import main.utils.ANSI;
public class Task{
    private int id;
    private String title; 
    private String description; 
    private boolean status;  //true is for completed and false is for not completed
    private Date timestamp; 
    //Constructor for creating a task manually
    public Task(int id, String title, String description){
        this.id  = id;
        this.title = title; 
        this. description = description; 
        this.status = false;
    }
    public Task(int id, String title, String description, boolean status, Date timestamp){
        this.id  = id;
        this.title = title; 
        this. description = description; 
        this.status = status; 
        this.timestamp = timestamp; 
    }

    //getters
    public int getId(){
        return id; 
    }
    public String getTitle(){
        return title; 
    }
    public String getDescription(){
        return description; 
    }
    public boolean getStatus(){
        return status; 
    }
    public Date getCreatedAt(){
        return timestamp; 
    }


    //setters

    public void setId(int id){
        this.id = id; 
    }

    public void setTitle(String title){
        this.title = title; 
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void markCompleted(){
        this.status = true;
    }

    public void markIncomplete(){
        this.status = false; 
    }

    @Override
    public String toString(){
        return ANSI.WHITE_BOLD + "Task id: " + ANSI.GREEN_BOLD + id + ANSI.RESET +
            ANSI.WHITE_BOLD + "\nTitle: " + ANSI.GREEN_BOLD + title + ANSI.RESET +
            ANSI.WHITE_BOLD + "\nDescription: " + ANSI.GREEN_BOLD + description + ANSI.RESET + 
            ANSI.WHITE_BOLD + "\nStauts: " + ANSI.GREEN_BOLD + ( status ? ANSI.GREEN_BOLD + "Completed" + ANSI.RESET : ANSI.RED_BOLD+ "Incomplete" + ANSI.RESET) + ANSI.RESET +
            ANSI.WHITE_BOLD + "\nCreated At: " + ANSI.GREEN_BOLD + timestamp + ANSI.RESET ;
    }
}



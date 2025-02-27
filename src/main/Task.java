package main;

public class Task{
    private int id;
    private String title; 
    private String description; 
    private boolean status;  //true is for completed and false is for not completed
    
    //Constructor 
    Task(int id, String title, String description){
        this.id  = id;
        this.title = title; 
        this. description = description; 
        this.status = false; 
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
}



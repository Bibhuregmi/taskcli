package main;

import java.util.Scanner;

public class Main {

    //creating the instance of the taskmanager and having it static so that the main methdod can access it, having it inside of the main method would create the taskmanager object evertime the main method runs causing problem to the program 
    private static TaskManager taskmanager = new TaskManager(); 
    private static Scanner in = new Scanner(System.in);
    
    public static void main(String[] args) {
        while(true){
            System.out.println("============> TASK MANAGER <============");
            System.out.println("1. Add Task: ");
            System.out.println("2. Remove Task: ");
            System.out.println("3. List all Tasks: ");
            System.out.println("4. Mark Task as done: ");
            System.out.println("5. Mark Task as Incomplete: ");
            System.out.println("6. Exit: ");


            int cases = in.nextInt();

            switch(cases){
                case 1 : addTask(); break; 
                case 2 : removeTask(); break; 
                case 3 : taskmanager.showAllTasks(); break; 
                case 4 : setStatusCompleted(); break;
                case 5 : setStatusIncomplete(); break; 
                case 6 : {
                    System.out.println("Existing the Task Manager.........");
                    in.close();
                    System.exit(0); break; 
                }
                default : System.out.println("Invalid choice!");
            }
        }
    }

    private static void addTask(){
        System.out.println("Enter task id: ");
        int id = in.nextInt();
        in.nextLine();
        
        System.out.println("Enter your task title: ");
        String title = in.nextLine(); 

        System.out.println("Enter your task desription: ");
        String description = in.nextLine(); 

        Task task = new Task(id, title, description);
        taskmanager.addTask(task);
    }

    private static void removeTask(){
        System.out.println("Enter the id of the task: ");
        int id = in.nextInt();   //TODO: Requires exception handling for the type mismatch 
        in.nextLine(); 
        taskmanager.removeTask(id);
    }

    private static void setStatusCompleted(){
        System.out.println("Enter the id of the completed task: ");
        int id = in.nextInt();
        in.nextLine(); 
        taskmanager.setStatusCompleted(id);
    }

    private static void setStatusIncomplete(){
        System.out.println("Enter the id of the incomplete task: ");
        int id = in.nextInt(); 
        in.nextLine(); 
        taskmanager.setStautsIncomplete(id);
    }
}

// TODO: Require file handling so that all the tasks are saved in the seperate text file 
// TODO: Add ANSI escape code to style the terminal 
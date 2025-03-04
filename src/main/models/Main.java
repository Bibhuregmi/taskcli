package main.models;

import java.util.Scanner;

import main.handlers.InputHandler;
import main.utils.ANSI;

public class Main {

    //creating the instance of the taskmanager and having it static so that the main methdod can access it, having it inside of the main method would create the taskmanager object evertime the main method runs causing problem to the program 
    private static TaskManager taskmanager = new TaskManager(); 
    private static Scanner in = new Scanner(System.in);
    
    public static void main(String[] args) {
        while(true){
            System.out.println(ANSI.YELLOW_BOLD + "============> TASK MANAGER <============\n" + ANSI.RESET);
            System.out.println(ANSI.GREEN_BOLD + "1. Add Task: "+ ANSI.RESET);
            System.out.println(ANSI.RED_BOLD + "2. Remove Task: "+ ANSI.RESET);
            System.out.println(ANSI.BLUE_BOLD + "3. List all Tasks: "+ ANSI.RESET);
            System.out.println(ANSI.PURPLE_BOLD + "4. Mark Task as done: "+ ANSI.RESET);
            System.out.println(ANSI.CYAN_BOLD + "5. Mark Task as Incomplete: "+ ANSI.RESET);
            System.out.println(ANSI.WHITE_BOLD + "6. Exit: \n");


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
            int id;
            do {
                id = InputHandler.getValidInteger(in, "Enter task id: ");
                if (!taskmanager.checkId(id)) { // Check if the ID already exists
                    System.out.println(ANSI.RED_BOLD + "ID already exists! Please enter a new ID." + ANSI.RESET);
                }
            } while (!taskmanager.checkId(id));
            
            System.out.println(ANSI.GREEN_BOLD + "Enter your task title: " + ANSI.RESET);
            String title = in.nextLine(); 

            System.out.println(ANSI.GREEN_BOLD + "Enter your task desription: " + ANSI.RESET);
            String description = in.nextLine(); 

            Task task = new Task(id, title, description);
            taskmanager.addTask(id, task);
    }

    private static void removeTask(){
        int id = InputHandler.getValidInteger(in, "Enter task id: ");
        taskmanager.removeTask(id);
    }

    private static void setStatusCompleted(){
        System.out.println(ANSI.GREEN_BOLD + "Enter the id of the completed task: " + ANSI.RESET);
        int id = in.nextInt();
        in.nextLine(); 
        taskmanager.setStatusCompleted(id);
    }

    private static void setStatusIncomplete(){
        System.out.println(ANSI.GREEN_BOLD + "Enter the id of the incomplete task: " + ANSI.RESET);
        int id = in.nextInt(); 
        in.nextLine(); 
        taskmanager.setStautsIncomplete(id);
    }
}

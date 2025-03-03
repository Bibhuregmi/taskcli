package main.handlers;

import java.util.Scanner;

public class InputHandler {
    //method to handle the input type mismatch 
    public static int getValidInteger(Scanner in, String command){ //takes two arguments 
        int value; //input value
        while(true){
            try {
                System.out.println(command);
                value = in.nextInt();
                in.nextLine(); //consuming the newline character
                return value; 
            } catch (Exception e) {
                in.nextLine();
                System.out.println("Please input only numbers\n");
            }
        }
    }
}

//.nextLine() consume the remaining input in the buffer and clear any invalid data, ensuring that the scanner is ready for the next input.
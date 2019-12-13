package Interpreter;

import java.util.Scanner;

 class Reader {
    static Scanner scanner = new Scanner(System.in);

     static String readCommand(){
        String command = scanner.nextLine();
        command = command.replaceAll("\\s+", "");
        return command;
    }


    //other read operations
}

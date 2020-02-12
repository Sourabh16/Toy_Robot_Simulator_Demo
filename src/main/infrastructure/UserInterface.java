package main.infrastructure;

import main.domain.common.IOUtility;

import java.io.Console;

public class UserInterface {

    public void start(Console console) {
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("Robo Demo");
        System.out.println("Enter a command, Valid commands are:");
        System.out.println("\'PLACE X,Y,NORTH|SOUTH|EAST|WEST\', MOVE, LEFT, RIGHT, REPORT or FINISH/EXIT/END");

        RoboController controller = new RoboController();
        while (true) {
            // TODO: 12/02/2020 console work
//            String input = console.readLine(": ");
            String input = IOUtility.getString("-> ").toUpperCase();
            switch (input) {
                case "FINISH":
                case "EXIT":
                case "END":
                    System.out.println("Thank you for using toy robot system");
                    return;
                default:
                    try {
                        String outputVal = controller.performOperation(input);
                        System.out.println(outputVal);
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
            }
        }
    }
}

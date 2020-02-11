package infrastructure;

import domain.exception.robotException;

import java.io.Console;

public class UserInterface {

    public void start(Console console) {
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("Robo Demo");
        System.out.println("Enter a command, Valid commands are:");
        System.out.println("\'PLACE X,Y,NORTH|SOUTH|EAST|WEST\', MOVE, LEFT, RIGHT, REPORT or FINISH");

        RoboController controller=new RoboController();
        while (true) {
            String input = console.readLine(": ");
            switch (input) {
                case "FINISH":
                    System.out.println("Thank you for using toy robot system");
                    return;
                default:
                    try {
                        String outputVal = controller.performOperation(input);
                        System.out.println(outputVal);
                    } catch (Exception e) {
                        // TODO: 11/02/2020 added generalized exception...modify
                        System.err.println(e.getMessage());
                    }
                    break;
            }
        }
    }
}

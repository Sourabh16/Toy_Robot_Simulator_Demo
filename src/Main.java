import infrastructure.UserInterface;

import java.io.Console;

import static domain.common.commonObjects.CONSOLE_NOT_AVAILABLE_FOR_THE_EXECUTION;

public class Main {

    public static void main(String[] args) {

        //check if system console is available
        Console console = System.console();
        //if console is not available or project is executed in IDE the show console not available
        if (console == null) {
            System.err.println(CONSOLE_NOT_AVAILABLE_FOR_THE_EXECUTION);
            System.exit(1);
        }
        else
        {
            UserInterface userInterface=new UserInterface();
            userInterface.start(console);
        }
    }
}

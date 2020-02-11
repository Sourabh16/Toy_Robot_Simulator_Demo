package infrastructure;

import domain.common.commonObjects;
import domain.exception.robotException;
import domain.model.MoveDirection;
import domain.model.RoboPosition;
import domain.model.RobotBoard;

import static domain.common.commonObjects.INVALID_COMMAND_ENTERED;

/*
the controller class for the application
 */
public class RoboController {

    public static final String BOARD_OBJECT_IS_INVALID = "Board object is invalid";
    public static final String INVALID_POSITION_ENTERED = "Invalid Position Entered";
    RobotBoard board = new RobotBoard(commonObjects.BOARD_ROW_COUNT, commonObjects.BOARD_COLUMN_COUNT);
    Robo robo = new Robo();

    public String performOperation(String inputString) throws robotException {
        String[] inputArray = inputString.split(" "); //split total string into chunks by " "
        String response = "";
        boolean commandsValidity = false;
        //check split inputArray is valid commands
        for (String inputCommand : inputArray) {
            if (inputCommand.equalsIgnoreCase(commonObjects.MoveCommand.valueOf(inputCommand).toString()))
                commandsValidity = true;
        }
        if (commandsValidity) {
            commonObjects.MoveCommand command = commonObjects.MoveCommand.valueOf(inputArray[0]);
            int xVal = 0, yVal = 0;
            MoveDirection moveDirection = null;

            //check for initial place command
            if (command == commonObjects.MoveCommand.PLACE && inputArray.length >= 2) {
                //separating X, Y coordinates and move direction from initial command
                String[] placeCommandCoordinates = inputArray[1].split(",");
                xVal = Integer.parseInt(placeCommandCoordinates[0]);
                yVal = Integer.parseInt(placeCommandCoordinates[1]);
                moveDirection = MoveDirection.valueOf(placeCommandCoordinates[2]);
            }
            if (command == commonObjects.MoveCommand.PLACE) {
                RoboPosition position = new RoboPosition(xVal, yVal, moveDirection);
                response = placeRobot(position);
            } else if (command == commonObjects.MoveCommand.MOVE) {
                RoboPosition newPosition = robo.getPosition().getNewPosition();
                //checking if position given is correct
                if (!board.isPositionCorrect(newPosition))
                    response = String.valueOf(false);
                else
                    response = String.valueOf(robo.moveToNewPosition(newPosition));
            }  else {
                throw new robotException(INVALID_COMMAND_ENTERED);
            }
        } else
            return INVALID_COMMAND_ENTERED;
        return response;
    }


    //place robot with provided position
    private String placeRobot(RoboPosition position) throws robotException {
        String status = "";
        try {
            if (board != null) {
                if (position != null) {
                    if (board.isPositionCorrect(position)) {
                        robo.setPosition(position);
                        status = "Robo placed at " + position.getX() + "," + position.getY();
                    } else {
                        status = "Robo placement at " + position.getX() + "," + position.getY() + " failed ";
                    }
                } else {
                    status = INVALID_POSITION_ENTERED;
                    throw new robotException(status);
                }
            } else {
                status = BOARD_OBJECT_IS_INVALID;
                throw new robotException(status);
            }
        } catch (robotException e) {
            throw new robotException(e.getMessage());
        }
        return status;
    }
}

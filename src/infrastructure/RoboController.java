package infrastructure;

import domain.common.commonObjects;
import domain.exception.robotException;
import domain.model.MoveDirection;
import domain.model.RoboPosition;
import domain.model.RobotBoard;

import static domain.common.commonObjects.*;

/*
the controller class for the application
 */
public class RoboController {

    RobotBoard board = new RobotBoard(commonObjects.BOARD_ROW_COUNT, commonObjects.BOARD_COLUMN_COUNT);
    Robo robo = new Robo();

    public String performOperation(String inputString) throws robotException {
        String[] inputArray = inputString.split(" "); //split total string into chunks by " "
        String response = "";
        boolean commandsValidity = false;
//        //check split inputArray is valid commands
//        for (String inputCommand : inputArray) {
//            if (inputCommand.equalsIgnoreCase(commonObjects.MoveCommand.valueOf(inputCommand).toString()))
//                commandsValidity = true;
//        }
//        if (commandsValidity) {
        try {
            MoveCommand command = MoveCommand.valueOf(inputArray[0]);
            int xVal = 0, yVal = 0;
            MoveDirection moveDirection = null;

            //check for initial place command
            if (command == MoveCommand.PLACE && inputArray.length >= 2) {
                //separating X, Y coordinates and move direction from initial command
                String[] placeCommandCoordinates = inputArray[1].split(",");
                xVal = Integer.parseInt(placeCommandCoordinates[0]);
                yVal = Integer.parseInt(placeCommandCoordinates[1]);
                moveDirection = MoveDirection.valueOf(placeCommandCoordinates[2]);
            }
            if (command == MoveCommand.PLACE) {
                RoboPosition position = new RoboPosition(xVal, yVal, moveDirection);
                response = placeRobot(position);
            } else {
                RoboPosition position = robo.getPosition();
                if (command == MoveCommand.MOVE) {
                    RoboPosition newPosition = position.getNewPosition();
                    //checking if position given is correct
                    if (!board.isPositionCorrect(newPosition))
                        response = INCORRECT_OR_OUT_OF_BOUNDS_PROMPT;
                    else {
                        boolean resultNewMove = robo.moveToNewPosition(newPosition);
                        if (!resultNewMove)
                            response = String.valueOf(resultNewMove);
                    }

                } else if (command == MoveCommand.LEFT) {
                    boolean turnResponse = robo.turnLeft();
                    if (!turnResponse) {
                        response = INCORRECT_OR_OUT_OF_BOUNDS_PROMPT;
                    }
                } else if (command == MoveCommand.RIGHT) {
                    boolean turnResponse = robo.turnRight();
                    if (!turnResponse) {
                        response = INCORRECT_OR_OUT_OF_BOUNDS_PROMPT;
                    }
                } else if (command == MoveCommand.REPORT) {
                    if (position == null)
                        return NULL_POSITION_FOUND;

                    int x = position.getX();
                    int y = position.getY();
                    MoveDirection direction1 = position.getDirection();
                    String direction = direction1.toString();
                    response = x + "," + y + "," + direction;
                } else {
                    throw new robotException(INVALID_COMMAND_ENTERED);
                }
            }
        } catch (IllegalArgumentException e) {
            response = ILLEGAL_ARGUMENTS_EXCEPTION +": "+e.getMessage();
        } catch (robotException e) {
            response = INVALID_COMMAND_ENTERED+": "+e.getMessage();
        }
//        }
//        else
//            return INVALID_COMMAND_ENTERED;
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
            throw new robotException(e.getMessage() +": "+ INVALID_COMMAND_ENTERED);
        }
        return status;
    }
}

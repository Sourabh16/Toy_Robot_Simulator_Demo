package main.infrastructure;

import main.domain.exception.robotException;
import main.domain.model.MoveDirection;
import main.domain.model.Robo;
import main.domain.model.RoboPosition;
import main.domain.model.RobotBoard;

import static main.domain.common.commonObjects.*;

/*
the controller class for the application
 */
public class RoboController {

    //    RobotBoard board = new RobotBoard(commonObjects.BOARD_ROW_COUNT, commonObjects.BOARD_COLUMN_COUNT);
    RobotBoard board;
    Robo robo;

    public RoboController() {
        this.board = new RobotBoard();
        this.robo = new Robo();
    }


    public String performOperation(String inputString) {
        String[] inputArray = inputString.split(" "); //split total string into chunks by " "
        String response = "";
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
                    if (!isPositionCorrect(newPosition, board))
                        response = INCORRECT_OR_OUT_OF_BOUNDS_PROMPT;
                    else {
                        boolean resultNewMove = moveToNewPosition(newPosition, robo);
                        if (!resultNewMove)
                            response = String.valueOf(resultNewMove);
                    }

                } else if (command == MoveCommand.LEFT) {
                    boolean turnResponse = turnLeft(robo);
                    if (!turnResponse) {
                        response = INCORRECT_OR_OUT_OF_BOUNDS_PROMPT;
                    }
                } else if (command == MoveCommand.RIGHT) {
                    boolean turnResponse = turnRight(robo);
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
            response = ILLEGAL_ARGUMENTS_EXCEPTION + ": " + e.getMessage();
        } catch (robotException e) {
            response = INVALID_COMMAND_ENTERED + ": " + e.getMessage();
        }
        return response;
    }


    //place robot with provided position
    private String placeRobot(RoboPosition position) throws robotException {
        String status = "";
        try {
            if (board != null) {
                if (position != null) {
                    if (isPositionCorrect(position, board)) {
                        robo.setPosition(position);
                        status = "Robo placed at " + position.getX() + "," + position.getY();
                    } else {
                        status = "Robo placement at " + position.getX() + "," + position.getY() + " failed";
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
            throw new robotException(e.getMessage() + ": " + INVALID_COMMAND_ENTERED);
        }
        return status;
    }

    /*
    check if provided position is greater than the row count and column count and less than 0 (which is invalid)
    */
    public boolean isPositionCorrect(RoboPosition roboPosition, RobotBoard board) throws robotException {
        try {
            int x = roboPosition.getX();
            int y = roboPosition.getY();
            return x <= board.getRowCount() && y <= board.getColumnCount() && x >= 0 && y >= 0;
        } catch (Exception e) {
            throw new robotException(e.getMessage());
        }
    }


    /**
     * move robo to new position
     */
    public boolean moveToNewPosition(RoboPosition newPosition, Robo robo) throws robotException {
        boolean response = false;
        try {
            if (newPosition != null) {
                // change position
                robo.setPosition(newPosition);
                response = true;
            }
            return response;
        } catch (Exception e) {
            throw new robotException(e.getMessage());
        }
    }


    /**
     * turns robo to left
     *
     * @param robo our robo on board
     */
    public boolean turnLeft(Robo robo) throws robotException {
        boolean response = false;
        try {
            if (robo.getPosition().getDirection() != null) {
                robo.getPosition().direction = robo.getPosition().getDirection().turnToLeft();
                response = true;
            }
            return response;
        } catch (Exception e) {
            throw new robotException(e.getMessage());
        }
    }

    /**
     * turns robo to left
     *
     * @param robo our robo on board
     */
    public boolean turnRight(Robo robo) throws robotException {
        boolean response = false;
        try {
            if (robo.getPosition().getDirection() != null) {
                robo.getPosition().direction = robo.getPosition().getDirection().turnToRight();
                response = true;
            }
            return response;
        } catch (Exception e) {
            throw new robotException(e.getMessage());
        }
    }

    public RobotBoard getBoard() {
        return board;
    }

    public Robo getRobo() {
        return robo;
    }
}


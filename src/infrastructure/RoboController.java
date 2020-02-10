package infrastructure;

import domain.common.commonObjects;
import domain.model.RobotBoard;

/*
the controller class for the application
 */
public class RoboController {

    RobotBoard robotBoard = new RobotBoard(commonObjects.BOARD_ROW_COUNT, commonObjects.BOARD_COLUMN_COUNT);

    public String performOperation(String inputString) {
        return null;
    }
}

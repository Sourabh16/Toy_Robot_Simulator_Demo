package domain.common;

/*
Class for storing common variables or common functions that can be reused over the application
 */
public class commonObjects {
    public static final String CONSOLE_NOT_AVAILABLE_FOR_THE_EXECUTION = "Console not available for the execution";
    public static final String DIRECTION_NOT_VALID = "direction not valid";
    public static final int BOARD_ROW_COUNT = 4;
    public static final int BOARD_COLUMN_COUNT = 4;

    /*
    Commands that will be used for movement
     */
    public enum MoveCommand {
        PLACE,
        MOVE,
        LEFT,
        RIGHT,
        REPORT
    }
}



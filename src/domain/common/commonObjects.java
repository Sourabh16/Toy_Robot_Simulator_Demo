package domain.common;

/*
Class for storing common variables or common functions that can be reused over the application
 */
public class commonObjects {
    public static final String CONSOLE_NOT_AVAILABLE_FOR_THE_EXECUTION = "Console not available for the execution";
    public static final String DIRECTION_NOT_VALID = "direction not valid";
    public static final String INVALID_COMMAND_ENTERED = "Invalid Command Entered";
    public static final int BOARD_ROW_COUNT = 4;
    public static final int BOARD_COLUMN_COUNT = 4;
    public static final String BOARD_OBJECT_IS_INVALID = "Board object is invalid";
    public static final String INVALID_POSITION_ENTERED = "Invalid Position Entered";
    public static final String INCORRECT_OR_OUT_OF_BOUNDS_PROMPT = "provided position may be incorrect or resulting in the out of bounds condition";
    public static final String NULL_POSITION_FOUND = "null position found while reporting";

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



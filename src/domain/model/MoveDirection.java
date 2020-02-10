package domain.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//creating enum for direction movement and assigning index to the direction of simplifying the move
public enum MoveDirection {
    NORTH(0),
    EAST(1),
    SOUTH(2),
    WEST(3);

    private int directionStepIndex;
    private static Map<Integer, MoveDirection> directionMap = new HashMap<>();

    //adding the each value of move direction to hashmap for assigning the step index
    static {
        MoveDirection[] values = MoveDirection.values();
        Arrays.stream(values).forEach(directionEnum -> directionMap.put(directionEnum.directionStepIndex, directionEnum));
    }


    //constructor for getting direction step
    MoveDirection(int directionStep) {
        this.directionStepIndex = directionStep;
    }

    public static MoveDirection getValue(int directionNum) {
        return directionMap.get(directionNum);
    }

    /**
     * gets the direction of turning to left for current position
     */
    public MoveDirection turnToLeft() {
        return turn(-1);
    }

    /**
     * gets the direction of turning to right for current position
     */
    public MoveDirection turnToRight() {
        return turn(1);
    }

    private MoveDirection turn(int step) {
        int newTempMoveIndex;
        if ((this.directionStepIndex + step) < 0) {
            newTempMoveIndex = directionMap.size() - 1;
        } else {
            newTempMoveIndex = (this.directionStepIndex + step) % directionMap.size();
        }
        return MoveDirection.getValue(newTempMoveIndex);
    }


}

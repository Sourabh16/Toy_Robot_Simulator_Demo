package main.domain.model;

import main.domain.model.MoveDirection;
import org.junit.Assert;
import org.junit.Test;

import static main.domain.model.MoveDirection.*;
import static org.junit.Assert.assertEquals;

public class MoveDirectionTest {

    /*
    check if the provided direction is returned properly from the Directions enum
     */
    @Test
    public void testGetValue() {
        //testing initial state after turn to left once
        MoveDirection direction = NORTH;
        direction = direction.getValue(direction);
        Assert.assertEquals(direction, NORTH);
    }

    /**
     * check if turn to left functionality gives right direction on turning
     */
    @Test
    public void testTurnToLeft() {
        MoveDirection direction = NORTH;
        direction = direction.turnToLeft();
        assertEquals(direction, WEST);
    }


    /**
     * check if turn to left functionality gives right direction on turning
     */
    @Test
    public void testTurnToLeftFromSouth() {
        MoveDirection direction = SOUTH;
        direction = direction.turnToLeft();
        assertEquals(direction, EAST);
    }

    /**
     * check if turn to right gives correct direction on right turn
     */
    @Test
    public void testTurnToRight() {
        MoveDirection direction = NORTH;
        direction = direction.turnToRight();
        assertEquals(direction, EAST);
    }

    /**
     * check if turn to right gives correct direction on right turn
     */
    @Test
    public void testTurnToRightFromSouth() {
        MoveDirection direction = SOUTH;
        direction = direction.turnToRight();
        assertEquals(direction, WEST);
    }

}
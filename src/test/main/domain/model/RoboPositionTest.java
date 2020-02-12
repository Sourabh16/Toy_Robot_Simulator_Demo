package main.domain.model;

import main.domain.exception.robotException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RoboPositionTest {

    /**
     * test to check if x,y coordinates changing or not according to new value of x or Y
     */
    @Test
    public void testPositionChange() {
        RoboPosition initialPosition = new RoboPosition(2, 1, MoveDirection.NORTH);
        RoboPosition newPosition = initialPosition.positionChange(1, 2);
        assertEquals(3, newPosition.getX());
        assertEquals(3, newPosition.getY());
    }

    /**
     * checking position change according to direction change
     *
     * @throws robotException
     */
    @Test
    public void testGetNewPositionWithNewDirection() throws robotException {
        RoboPosition initialPosition = new RoboPosition(2, 1, MoveDirection.NORTH);
        initialPosition.setDirection(MoveDirection.WEST);
        RoboPosition newPosition = initialPosition.getNewPosition();
        assertEquals(1, newPosition.getX());
        assertEquals(1, newPosition.getY());


        //checking new position for another turn
        newPosition.setDirection(MoveDirection.SOUTH);
        RoboPosition newPosition2 = newPosition.getNewPosition();
        assertEquals(1, newPosition2.getX());
        assertEquals(0, newPosition2.getY());

    }
}
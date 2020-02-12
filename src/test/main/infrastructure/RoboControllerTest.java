package main.infrastructure;

import main.domain.exception.robotException;
import main.domain.model.Robo;
import main.domain.model.RoboPosition;
import main.domain.model.RobotBoard;
import org.junit.Before;
import org.junit.Test;

import static main.domain.model.MoveDirection.*;
import static org.junit.Assert.*;

/**
 * test class for robo controller
 */
public class RoboControllerTest {
    RoboController controller;
    String response;
    String inputString;
    Robo robo;
    RobotBoard board;


    @Before
    public void setUp() {
        controller = new RoboController();
        response = "";
        inputString = "";
        robo = controller.getRobo();
        board = controller.getBoard();
    }

    @Test
    public void testPerformOperationValidPLACE() {
        //valid location
        inputString = "PLACE 0,0,NORTH";
        response = controller.performOperation(inputString);
        assertEquals("Robo placed at 0,0", response.trim());
    }

    @Test
    public void testPerformOperationInvalidPLACE() {
        //invalid x,y
        inputString = "PLACE 22,2,NORTH";
        response = controller.performOperation(inputString);
        assertEquals("Robo placement at 22,2 failed", response.trim());
    }

    @Test
    public void testPerformOperationNegativePLACE() {
        //negative x,y
        inputString = "PLACE -4,-3,NORTH";
        response = controller.performOperation(inputString);
        assertEquals("Robo placement at -4,-3 failed", response.trim());
    }

    /*
    test for valid input position (0,0)
     */
    @Test
    public void testIsPositionCorrectValidInput() throws robotException {
        RoboPosition roboPosition = new RoboPosition(0, 0, NORTH);
        boolean response = controller.isPositionCorrect(roboPosition, board);
        assertTrue(response);
    }

    /**
     * test for invalid inpput position(8,2)
     */
    @Test
    public void testIsPositionCorrectInvalidInput() throws robotException {
        RoboPosition roboPosition = new RoboPosition(8, 2, NORTH);
        boolean response = controller.isPositionCorrect(roboPosition, board);
        assertFalse(response);
    }

    /**
     * test for negative input position(-2,2)
     */
    @Test
    public void testIsPositionCorrectInvalidInputNegative() throws robotException {
        RoboPosition roboPosition = new RoboPosition(-2, 2, NORTH);
        boolean response = controller.isPositionCorrect(roboPosition, board);
        assertFalse(response);
    }

    /**
     * test for input position exceeding board size(5,2)
     */
    @Test
    public void testIsPositionCorrectInvalidInputExceedBoard() throws robotException {
        RoboPosition roboPosition = new RoboPosition(5, 2, NORTH);
        boolean response = controller.isPositionCorrect(roboPosition, board);
        assertFalse(response);
    }

    /**
     * test for input position invalid character(A,2)
     */
    @Test(expected = NumberFormatException.class)
    public void testIsPositionCorrectInvalidInputCharacters() throws robotException {
        RoboPosition roboPosition = new RoboPosition(Integer.parseInt("A"), 2, NORTH);
        boolean response = controller.isPositionCorrect(roboPosition, board);
        assertFalse(response);
    }

    /**
     * test for input position invalid special character(3,$)
     */
    @Test(expected = NumberFormatException.class)
    public void testIsPositionCorrectInvalidInputCharacters2() throws robotException {
        RoboPosition roboPosition = new RoboPosition(3, Integer.parseInt("$"), NORTH);
        boolean response = controller.isPositionCorrect(roboPosition, board);
        assertFalse(response);
    }

    /**
     * test for input position invalid space character(3," ")
     */
    @Test(expected = NumberFormatException.class)
    public void testIsPositionCorrectInvalidInputCharacters3() throws robotException {
        RoboPosition roboPosition = new RoboPosition(3, Integer.parseInt(" "), NORTH);
        boolean response = controller.isPositionCorrect(roboPosition, board);
        assertFalse(response);
    }

    /*
    test for checking valid position
     */
    @Test
    public void testMoveToNewValidPosition() throws robotException {
        String originalPosition = "PLACE 0,0,NORTH";
        controller.performOperation(originalPosition);
        RoboPosition newPosition = new RoboPosition(0, 1, NORTH);
        boolean response = controller.moveToNewPosition(newPosition, robo);
        assertTrue(response);
    }

    /*
    test for checking inValid position
     */
    @Test(expected = NumberFormatException.class)
    public void testMoveToNewInvalidPosition() throws robotException {
        String originalPosition = "PLACE 4,0,NORTH";
        controller.performOperation(originalPosition);
        int x = Integer.parseInt(null);
        RoboPosition newPosition = new RoboPosition(x, 0, NORTH);
        boolean response = controller.moveToNewPosition(newPosition, robo);
        assertFalse(response);
    }

    /*
    test for checking inValid position float value entered
    */
    @Test(expected = NumberFormatException.class)
    public void testMoveToNewInvalidPosition2() throws robotException {
        String originalPosition = "PLACE 4,0,NORTH";
        controller.performOperation(originalPosition);
        int x = Integer.parseInt(String.valueOf(48.5f));
        RoboPosition newPosition = new RoboPosition(x, 0, NORTH);
        boolean response = controller.moveToNewPosition(newPosition, robo);
        assertTrue(response);
    }

    /*
    test for turning robot to left valid condition
     */
    @Test
    public void testTurnLeftValid() throws robotException {
        String originalPosition = "PLACE 0,0,NORTH";
        controller.performOperation(originalPosition);
        controller.turnLeft(robo);
        RoboPosition roboPosition = controller.getRobo().getPosition();
        assertEquals(WEST, roboPosition.getDirection());
    }

    /*
    test for turning robot to left invalid condition null direction
    */
    @Test
    public void testTurnLeftInvalid() throws robotException {
        robo.setPosition(new RoboPosition(0,0,null));
        assertFalse(controller.turnLeft(robo));
    }

    /*
    test for turning robot to right valid condition
     */
    @Test
    public void turnRight() throws robotException {
        String originalPosition = "PLACE 0,0,NORTH";
        controller.performOperation(originalPosition);
        controller.turnRight(robo);
        RoboPosition roboPosition = controller.getRobo().getPosition();
        assertEquals(EAST, roboPosition.getDirection());
    }

    /*
    test for turning robot to right invalid condition null direction
    */
    @Test
    public void testTurnRightInvalid() throws robotException {
        robo.setPosition(new RoboPosition(0,0,null));
        assertFalse(controller.turnRight(robo));
    }
}
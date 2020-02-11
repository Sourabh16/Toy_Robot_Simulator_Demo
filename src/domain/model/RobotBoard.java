package domain.model;

public class RobotBoard implements Board {

    int rowCount, columnCount;

    public RobotBoard(int rowCount, int columnCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
    }

    /*
    check if provided position is greater than the row count and column count and less than 0 (which is invalid)
     */
    @Override
    public boolean isPositionCorrect(RoboPosition roboPosition) {
        int x = roboPosition.getX();
        int y = roboPosition.getY();
        return x <= this.rowCount && y <= this.columnCount && x >= 0 && y >= 0;
    }
}

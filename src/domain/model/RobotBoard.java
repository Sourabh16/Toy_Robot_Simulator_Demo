package domain.model;

public class RobotBoard implements Board {

    int rowCount, columnCount;

    public RobotBoard(int rowCount, int columnCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
    }

    @Override
    public boolean isPositionCorrect(RoboPosition roboPosition) {
        return false;
    }
}

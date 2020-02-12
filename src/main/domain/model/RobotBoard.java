package main.domain.model;

import main.domain.common.commonObjects;

/**
 * Java POJO class for robot board object
 */
public class RobotBoard {

    int rowCount;
    int columnCount;

    public RobotBoard(int rowCount, int columnCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
    }

    /**
     * constructor for assigning board row column parameters
     */
    public RobotBoard() {
        this.rowCount = commonObjects.BOARD_ROW_COUNT;
        this.columnCount = commonObjects.BOARD_COLUMN_COUNT;
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }
}

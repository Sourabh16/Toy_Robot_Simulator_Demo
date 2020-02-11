package infrastructure;

import domain.exception.robotException;
import domain.model.RoboPosition;

public class Robo {
    RoboPosition position;

    public RoboPosition getPosition() {
        return position;
    }

    public void setPosition(RoboPosition position) {
        this.position = position;
    }

    /**
     * move robo to new position
     */

    public boolean moveToNewPosition(RoboPosition newPosition) throws robotException {
        boolean response = false;
        if (newPosition != null) {
            // change position
            setPosition(newPosition);
            response = true;
        }
        return response;
    }

    /**
     * turns robo to left
     */
    public boolean turnLeft() {
        boolean response = false;
        if (this.position.getDirection() != null) {
            this.position.direction = this.position.getDirection().turnToLeft();
            response = true;
        }
        return response;
    }

    /**
     * turns robo to left
     */
    public boolean turnRight() {
        boolean response = false;
        if (this.position.getDirection() != null) {
            this.position.direction = this.position.getDirection().turnToRight();
            response = true;
        }
        return response;
    }
}

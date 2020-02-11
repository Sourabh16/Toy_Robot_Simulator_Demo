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
        if (newPosition != null) {
            // change position
            this.position = newPosition;
            return true;
        } else {
            return false;
        }

    }

    /**
     * turns robo to left
     */
    public boolean turnLeft() {
        if (this.position.getDirection() != null) {
            this.position.direction = this.position.getDirection().turnToLeft();
            return true;
        } else {
            return false;
        }
    }

    /**
     * turns robo to left
     */
    public boolean turnRight() {
        if (this.position.getDirection() != null) {
            this.position.direction = this.position.getDirection().turnToRight();
            return true;
        } else {
            return false;
        }
    }
}

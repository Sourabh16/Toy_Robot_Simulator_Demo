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

    public boolean moveToNewPosition(RoboPosition newPosition) throws robotException {
        if (newPosition != null) {
            // change position
            this.position = newPosition;
            return true;
        } else {
            return false;
        }

    }


}

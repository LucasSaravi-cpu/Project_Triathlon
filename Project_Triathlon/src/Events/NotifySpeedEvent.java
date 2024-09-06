package Events;

import java.util.EventObject;

public class NotifySpeedEvent  extends EventObject{

    //------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\

    private final int speedLevel;

    //------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\

    public NotifySpeedEvent(Object source, int speedLevel) {
        super(source);
        this.speedLevel = speedLevel;
    }

    //------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\
    public int getSpeedLevel() {
        return speedLevel;
    }

}

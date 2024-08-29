package Events;

import java.util.EventObject;

public class SpeedChangeEvent extends EventObject {
    private final int delta;

    public SpeedChangeEvent(Object source, int delta) {
        super(source);
        this.delta = delta;
    }

    public int getDelta() {
        return delta;
    }
}
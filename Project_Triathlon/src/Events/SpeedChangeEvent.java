package Events;

import java.util.EventObject;

public class SpeedChangeEvent extends EventObject {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\
    private final int delta;
    
  //------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\

    public SpeedChangeEvent(Object source, int delta) {
        super(source);
        this.delta = delta;
    }
  //------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\
    public int getDelta() {
        return delta;
    }
}
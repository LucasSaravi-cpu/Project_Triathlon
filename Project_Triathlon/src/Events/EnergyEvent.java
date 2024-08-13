package Events;

import java.util.EventObject;

public class EnergyEvent  extends EventObject{
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\
	
	private final double energyLevel;
	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\

    public EnergyEvent(Object source, double energyLevel) {
        super(source);
        this.energyLevel = energyLevel;
    }

   //------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\
    public double getEnergyLevel() {
        return energyLevel;
    }

}

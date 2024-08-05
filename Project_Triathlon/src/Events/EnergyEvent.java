package Events;

import java.util.EventObject;

public class EnergyEvent  extends EventObject{
	
	private final double energyLevel;

    public EnergyEvent(Object source, double energyLevel) {
        super(source);
        this.energyLevel = energyLevel;
    }

    public double getEnergyLevel() {
        return energyLevel;
    }

}

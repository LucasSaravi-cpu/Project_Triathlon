package model.weather;

public class MeasurementUnit {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\
	
    private String unit;

    
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
    
    public MeasurementUnit(String unit) {
		super();
		this.unit = unit;
	}

    //------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\
	public String getUnit() {
        return unit;
    }


	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\

	@Override
	public String toString() {
		return  unit ;
	}
    
    
    
    
}

package model;

public class MeasurementUnit {
	
    private String unit;

    
    
    
    public MeasurementUnit(String unit) {
		super();
		this.unit = unit;
	}




	public String getUnit() {
        return unit;
    }




	@Override
	public String toString() {
		return "MeasurementUnit [unit=" + unit + "]";
	}
    
    
    
    
}

package Events;

import java.util.EventObject;

import model.WeatherConditions;

public class WeatherEvent extends EventObject {
    
    //------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\
	
	private final WeatherConditions weatherConditions;
    
    //------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
	public WeatherEvent(Object source, WeatherConditions weatherConditions) {
        super(source);
        this.weatherConditions = weatherConditions;
    }

    //------------------------------------------------>||GETTERS||<--------------------------------------------------------\\
	
	public WeatherConditions getWeatherConditions() {
        return weatherConditions;
    }
}
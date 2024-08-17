package listeners;

import Events.WeatherEvent;

public interface WeatherEventListener {
    void onWeatherUpdate(WeatherEvent  event);
}

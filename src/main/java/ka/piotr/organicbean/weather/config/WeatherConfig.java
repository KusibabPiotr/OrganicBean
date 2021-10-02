package ka.piotr.organicbean.weather.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Getter
public class WeatherConfig {

    @Value("ecb50481b265872817c44b68d5c142d5")
    private String openWeatherApiKey;

    @Value("http://api.openweathermap.org/data/2.5/weather")
    private String openWeatherEndpoint;
}

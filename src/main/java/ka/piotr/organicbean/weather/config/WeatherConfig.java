package ka.piotr.organicbean.weather.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Getter
public class WeatherConfig {

    @Value("${openweathermap.api.key}")
    private String openWeatherApiKey;

    @Value("${openweathermap.now.endpoint}")
    private String openWeatherEndpoint;
}

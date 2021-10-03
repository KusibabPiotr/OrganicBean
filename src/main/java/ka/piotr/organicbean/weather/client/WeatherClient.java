package ka.piotr.organicbean.weather.client;

import ka.piotr.organicbean.weather.config.WeatherConfig;
import ka.piotr.organicbean.weather.dto.OutputWeatherDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class WeatherClient {

    private final RestTemplate restTemplate;
    private final WeatherConfig weatherConfig;
    private static final String CITY = "Redditch";

    public OutputWeatherDto getNowWeatherConditions(){
        URI uri = UriComponentsBuilder.fromHttpUrl(weatherConfig.getOpenWeatherEndpoint())
                .queryParam("appid",weatherConfig.getOpenWeatherApiKey())
                .queryParam("q", CITY)
                .queryParam("units", "metric")
                .build().encode().toUri();

        return restTemplate.getForObject(uri,OutputWeatherDto.class);
    }
}

package ka.piotr.organicbean.weather.client;

import ka.piotr.organicbean.weather.config.WeatherConfig;
import ka.piotr.organicbean.weather.dto.OutputWeatherDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class WeatherClient {

    private RestTemplate restTemplate;
    private WeatherConfig weatherConfig;

    public WeatherClient(RestTemplate restTemplate, WeatherConfig weatherConfig) {
        this.restTemplate = restTemplate;
        this.weatherConfig = weatherConfig;
    }

    public OutputWeatherDto getNowWeatherConditions(String city){
        URI uri = UriComponentsBuilder.fromHttpUrl(weatherConfig.getOpenWeatherEndpoint())
                .queryParam("appid",weatherConfig.getOpenWeatherApiKey())
                .queryParam("q", city)
                .queryParam("units", "metric")
                .build().encode().toUri();

        return restTemplate.getForObject(uri,OutputWeatherDto.class);
    }
}

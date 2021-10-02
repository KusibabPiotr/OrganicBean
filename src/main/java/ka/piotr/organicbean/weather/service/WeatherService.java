package ka.piotr.organicbean.weather.service;

import ka.piotr.organicbean.weather.client.WeatherClient;
import ka.piotr.organicbean.weather.dto.OutputWeatherDto;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    private WeatherClient weatherClient;

    public WeatherService(WeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }

    public OutputWeatherDto getDataFromApi(String city){
        return  weatherClient.getNowWeatherConditions(city);
    }
}

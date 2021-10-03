package ka.piotr.organicbean.weather.service;

import ka.piotr.organicbean.weather.client.WeatherClient;
import ka.piotr.organicbean.weather.dto.OutputWeatherDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherClient weatherClient;

    public OutputWeatherDto getDataFromApi(){
        return  weatherClient.getNowWeatherConditions();
    }
}

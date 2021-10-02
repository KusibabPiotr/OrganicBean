package ka.piotr.organicbean.weather.controller;

import ka.piotr.organicbean.weather.dto.OutputWeatherDto;
import ka.piotr.organicbean.weather.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/weather")
public class WeatherController {

    private WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping(value = "getNow")
    public OutputWeatherDto getWeatherForNow(@RequestParam String city){
        return weatherService.getDataFromApi(city);
    }
}

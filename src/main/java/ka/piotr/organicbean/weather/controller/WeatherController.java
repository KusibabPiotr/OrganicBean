package ka.piotr.organicbean.weather.controller;

import ka.piotr.organicbean.weather.dto.OutputWeatherDto;
import ka.piotr.organicbean.weather.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping(value = "getNow")
    public OutputWeatherDto getWeatherForNow(){
        return weatherService.getDataFromApi();
    }
}

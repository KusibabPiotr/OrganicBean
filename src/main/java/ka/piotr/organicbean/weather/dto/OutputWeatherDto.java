package ka.piotr.organicbean.weather.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import ka.piotr.organicbean.weather.domain.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class OutputWeatherDto {

    @JsonProperty(value = "weather")
    private List<WeatherDescription> weatherDescriptionList = new ArrayList<>();
    @JsonProperty(value = "main")
    private WeatherTemperaturePressureHumadity weatherTemperaturePressureHumadity;
    @JsonProperty(value = "wind")
    private Wind wind;
    @JsonProperty(value = "clouds")
    private Clouds clouds;
    @JsonProperty(value = "rain")
    private Rain rain;
    @JsonProperty(value = "snow")
    private Snow snow;
    @JsonProperty(value = "id")
    private int cityId;
    @JsonProperty(value = "name")
    private String cityName;
}

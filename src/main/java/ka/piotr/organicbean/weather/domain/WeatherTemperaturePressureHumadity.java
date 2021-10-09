package ka.piotr.organicbean.weather.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherTemperaturePressureHumadity {

    @JsonProperty(value = "temp")
    private double temperature;
    @JsonProperty(value = "feels_like")
    private double perceptibleTemperature;
    @JsonProperty(value = "pressure")
    private int pressure;
    @JsonProperty(value = "humidity")
    private int humidity;
}

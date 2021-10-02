package ka.piotr.organicbean.weather.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InputDataParams {

    private String city;
    private String units;

    public String getCity() {
        return city;
    }

    public String getUnits() {
        return units;
    }
}
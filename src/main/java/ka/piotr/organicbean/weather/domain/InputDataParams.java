package ka.piotr.organicbean.weather.domain;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
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
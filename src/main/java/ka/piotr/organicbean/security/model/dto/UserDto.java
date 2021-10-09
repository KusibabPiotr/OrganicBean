package ka.piotr.organicbean.security.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class UserDto {

    private final String firstName;
    private final String lastName;
    private final String username;
    private final String password;
    private final String email;
    private final String street;
    private final String city;
    private final String state;
    private final String zip;
    private final String phoneNumber;
}

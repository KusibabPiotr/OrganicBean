package ka.piotr.organicbean.registration.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginRequest {

    private String login;
    private String password;
}

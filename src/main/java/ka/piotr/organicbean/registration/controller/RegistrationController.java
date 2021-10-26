package ka.piotr.organicbean.registration.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import ka.piotr.organicbean.registration.exception.PasswordNotMatchException;
import ka.piotr.organicbean.registration.exception.TokenNotFoundException;
import ka.piotr.organicbean.registration.exception.UserNotFoundException;
import ka.piotr.organicbean.registration.model.RegistrationRequest;
import ka.piotr.organicbean.registration.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/register")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request)
            throws PasswordNotMatchException {
        return registrationService.register(request);
    }

    @GetMapping("/confirm")
    public void confirm(@RequestParam String token)
            throws TokenNotFoundException, UserNotFoundException {
        registrationService.confirm(token);
    }
}

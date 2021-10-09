package ka.piotr.organicbean.security.controller;

import ka.piotr.organicbean.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/registration")
@RequiredArgsConstructor
public class RegistrationController {

    public final UserRepository userRepository;
    public final PasswordEncoder passwordEncoder;

}

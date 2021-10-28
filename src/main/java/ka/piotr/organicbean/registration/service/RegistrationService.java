package ka.piotr.organicbean.registration.service;

import com.auth0.jwt.exceptions.TokenExpiredException;
import ka.piotr.organicbean.registration.email.EmailBuilder;
import ka.piotr.organicbean.registration.email.EmailSender;
import ka.piotr.organicbean.registration.exception.PasswordNotMatchException;
import ka.piotr.organicbean.registration.exception.TokenNotFoundException;
import ka.piotr.organicbean.registration.model.RegistrationRequest;
import ka.piotr.organicbean.registration.model.ConfirmationToken;
import ka.piotr.organicbean.user.model.AppUser;
import ka.piotr.organicbean.user.repository.AppUserRepository;
import ka.piotr.organicbean.user.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final AppUserRepository userRepository;
    private final ConfirmationTokenService tokenService;
    private final EmailSender emailSender;
    private final AppUserService userService;

    @Transactional
    public String register(RegistrationRequest credentials)
            throws PasswordNotMatchException {
        if (!credentials.getPassword().equals(credentials.getRepeatPassword())){
            throw new PasswordNotMatchException("Password not match! Try again!");
        }
        if (userRepository.existsByUsername(credentials.getLogin())){
            throw new IllegalStateException(String.format(
                    "User with email: %s already signed in!",credentials.getLogin()));
        }
        AppUser user = userService.signUpUser(new AppUser(
                credentials.getLogin(),
                credentials.getPassword())
        );

        var token = new ConfirmationToken(user);
        tokenService.saveConfirmationToken(token);

        String link = "http://localhost:8080/v1/register/confirm?token=" + token.getToken();
        emailSender.send(credentials.getLogin(), EmailBuilder.buildEmail("New Organic Bean Lover",link));

        return "Check you email box and confirm registration!";
    }
    @Transactional
    public void confirm(String token)
            throws TokenNotFoundException{
        ConfirmationToken confirmationToken = tokenService.getToken(token).orElseThrow(() ->
                new TokenNotFoundException(String.format("Cannot find the token: %s", token)));
        if (confirmationToken.getExpiresAt().isBefore(LocalDateTime.now())){
            throw new TokenExpiredException("Your registration token expired! You have to sign in again!");
        }
        tokenService.setConfirmedAt(token);
        userService.enableAppUser(confirmationToken.getAppUser().getUsername());
    }
}

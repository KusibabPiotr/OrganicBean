package ka.piotr.organicbean.registration.service;

import com.auth0.jwt.exceptions.TokenExpiredException;
import ka.piotr.organicbean.registration.exception.PasswordNotMatchException;
import ka.piotr.organicbean.registration.exception.TokenNotFoundException;
import ka.piotr.organicbean.registration.exception.UserNotFoundException;
import ka.piotr.organicbean.registration.model.RegistrationRequest;
import ka.piotr.organicbean.registration.token.ConfirmationToken;
import ka.piotr.organicbean.registration.token.ConfirmationTokenRepository;
import ka.piotr.organicbean.user.model.AppUser;
import ka.piotr.organicbean.user.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final PasswordEncoder passwordEncoder;
    private final AppUserRepository userRepository;
    private final ConfirmationTokenRepository tokenRepository;

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
        AppUser user = userRepository.save(new AppUser(
                credentials.getLogin(),
                passwordEncoder.encode(credentials.getPassword())
        ));

        var token = new ConfirmationToken(user);
        tokenRepository.save(token);

        return token.getToken();
    }
    @Transactional
    public void confirm(String token)
            throws TokenNotFoundException, UserNotFoundException {
        ConfirmationToken confirmationToken = tokenRepository.findByToken(token).orElseThrow(() ->
                new TokenNotFoundException(String.format("Cannot find the token: %s", token)));
        if (confirmationToken.getExpiresAt().isBefore(LocalDateTime.now())){
            throw new TokenExpiredException("Your registration token expired! You have to sign in again!");
        }
        confirmationToken.setConfirmedAt(LocalDateTime.now());
        String username = confirmationToken.getAppUser().getUsername();
        AppUser appUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("There is no user with given email: %s "));
        appUser.setEnabled(true);
    }
}

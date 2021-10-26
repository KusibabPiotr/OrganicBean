package ka.piotr.organicbean.user.service;


import ka.piotr.organicbean.registration.exception.PasswordNotMatchException;
import ka.piotr.organicbean.user.model.AppUser;
import ka.piotr.organicbean.registration.model.RegistrationRequest;
import ka.piotr.organicbean.user.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserService implements UserDetailsService {

    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return userRepository.findByUsername(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Użytkownik posiadający login %s nie został znaleziony"));
    }

    public void deleteUser(Long id, String password)
            throws PasswordNotMatchException{
        AppUser user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Brak użytkownika o id " + id));
        if (passwordEncoder.matches(password, user.getPassword())){
            userRepository.deleteById(id);
        } else {
            throw new PasswordNotMatchException("Podane hasło jest nieprawidłowe");
        }
    }

}

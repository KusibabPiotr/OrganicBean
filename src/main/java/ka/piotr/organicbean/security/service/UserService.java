package ka.piotr.organicbean.security.service;

import ka.piotr.organicbean.security.exceptions.PasswordNotMatchException;
import ka.piotr.organicbean.security.model.domain.User;
import ka.piotr.organicbean.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    public final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user != null){
            return user;
        }
        throw new UsernameNotFoundException(
                "Użytkownik '" + username + "' nie został znaleziony"
        );
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public void deleteUser(Long id, String password) throws PasswordNotMatchException{
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Brak użytkownika o id " + id));
        if (passwordEncoder.matches(password, user.getPassword())){
            userRepository.deleteById(id);
        } else {
            throw new PasswordNotMatchException("Podane hasło jest nieprawidłowe");
        }
    }

}

package ka.piotr.organicbean.security.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService  {

//    private final UserRepository userRepository;
//    public final PasswordEncoder passwordEncoder;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username);
//        if (user != null){
//            return user;
//        }
//        throw new UsernameNotFoundException(
//                "Użytkownik '" + username + "' nie został znaleziony"
//        );
//    }
//
//    public User saveUser(User user){
//        return userRepository.save(user);
//    }
//
//    public void deleteUser(Long id, String password) throws PasswordNotMatchException{
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new UsernameNotFoundException("Brak użytkownika o id " + id));
//        if (passwordEncoder.matches(password, user.getPassword())){
//            userRepository.deleteById(id);
//        } else {
//            throw new PasswordNotMatchException("Podane hasło jest nieprawidłowe");
//        }
//    }

}

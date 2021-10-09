package ka.piotr.organicbean.security.controller.mapper;

import ka.piotr.organicbean.security.model.domain.User;
import ka.piotr.organicbean.security.model.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapToUser(final UserDto userDto, final Long id){
        return new User(id,
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getEmail(),
                userDto.getStreet(),
                userDto.getCity(),
                userDto.getState(),
                userDto.getZip(),
                userDto.getPhoneNumber());
    }
    public UserDto mapToUserDto(final User user){
        return new UserDto(
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getStreet(),
                user.getCity(),
                user.getState(),
                user.getZip(),
                user.getPhoneNumber());
    }
}

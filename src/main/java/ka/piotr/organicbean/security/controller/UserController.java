package ka.piotr.organicbean.security.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {

//    public final UserService userService;
//    public final UserMapper userMapper;
//
//    @PostMapping(value = "create",
//    consumes = MediaType.APPLICATION_JSON_VALUE)
//    public UserDto createUser(@RequestBody UserDto userDto){
//        return userMapper.mapToUserDto(userService.saveUser(userMapper.mapToUser(userDto,null)));
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public void deleteUser(@PathVariable Long id, @RequestParam String password) throws PasswordNotMatchException {
//        userService.deleteUser(id,password);
//    }


}

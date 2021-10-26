package ka.piotr.organicbean.registration.controller;

import ka.piotr.organicbean.registration.model.LoginRequest;
import ka.piotr.organicbean.user.model.AppUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class LoginController {

    @PostMapping("/login")
    public void login(@RequestBody LoginRequest request){
    }
}

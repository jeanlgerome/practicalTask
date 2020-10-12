package practicalTask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import practicalTask.service.user.UserService;

@RestController
public class UserController {

    @Autowired
    @Qualifier("userServiceImpl")
    UserService userService;

    @GetMapping("/user/list")
    public String getUserList(@RequestParam(value = "id", defaultValue = "1") Long id){

        return "Hello, this temp UserController";

    }
}

package kma.topic8.springrestsample;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kma.topic8.springrestsample.dto.User;

@Controller
public class UsersController {

    @ResponseBody
    @GetMapping("/users-ok")
    public List<User> getAllUsersOk() {
        return Arrays.asList(
            User.of("login1", "name 1"),
            User.of("login12", "name 12")
        );
    }

    @GetMapping("/users-fail")
    public List<User> getAllUsersFail() {
        return Arrays.asList(
            User.of("login1", "name 1"),
            User.of("login12", "name 12")
        );
    }
}

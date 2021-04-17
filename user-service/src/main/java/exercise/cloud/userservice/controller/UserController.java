package exercise.cloud.userservice.controller;

import exercise.cloud.userservice.dto.UserDto;
import exercise.cloud.userservice.service.UserService;
import exercise.cloud.userservice.vo.RequestUser;
import exercise.cloud.userservice.vo.ResponseUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/")
@RestController
public class UserController {

    @Value("${greeting.message}")
    String message;

    private final UserService userService;
    private final Environment environment;

    @GetMapping("/welcome")
    public String welcome() {
        return message;
    }

    @GetMapping("/health-check")
    public String status() {
        return
                "port: local server port"+environment.getProperty("local.server.port")+
                        "port: server port"+environment.getProperty("server.port")+
                        "token secret"+environment.getProperty("token.shared_key")+
                        "token expiration time"+environment.getProperty("token.expiration_time");
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser user) {
        return new ResponseEntity(new ResponseUser(userService.createUser(UserDto.createUserDto(user))),
                HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public ResponseEntity<List<ResponseUser>> getUsers() {
        return
                new ResponseEntity<>(
                        userService.getUserByAll().stream()
                                .map(user -> new ResponseUser(user))
                                .collect(Collectors.toList())
                        , HttpStatus.OK
                );
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<ResponseUser> getUserById(@PathVariable("userId") String userId) {
        return
                new ResponseEntity<>(
                        new ResponseUser(userService.getUserById(userId)),
                        HttpStatus.OK
                );
    }
}

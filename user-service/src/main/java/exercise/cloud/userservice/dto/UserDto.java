package exercise.cloud.userservice.dto;

import exercise.cloud.userservice.entity.User;
import exercise.cloud.userservice.vo.RequestUser;
import exercise.cloud.userservice.vo.ResponseOrder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class UserDto {

    private String email;
    private String name;
    private String password;
    private String userId;
    private LocalDateTime createdAt;

    private String encryptedPassword;
    private List<ResponseOrder> orders = new ArrayList<>();

    private UserDto(RequestUser user) {
        email = user.getEmail();
        name = user.getName();
        password = user.getPassword();
    }

    private UserDto(User user) {
        email = user.getEmail();
        name = user.getName();
        password = user.getPassword();
        userId = user.getUserId();
    }

    public static UserDto createUserDto(RequestUser user) {
        UserDto userDto = new UserDto(user);
        userDto.userId = UUID.randomUUID().toString();
        userDto.createdAt = LocalDateTime.now();
        userDto.encryptedPassword = user.getPassword();
        return userDto;
    }

    public static UserDto createUserDto(User user) {
        UserDto userDto = new UserDto(user);
        return userDto;
    }
}

package exercise.cloud.userservice.service;

import exercise.cloud.userservice.dto.UserDto;
import exercise.cloud.userservice.entity.User;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto getUserById(String userId);

    List<UserDto> getUserByAll();

    UserDto getUserByEmail(String email);
}

package exercise.cloud.userservice.service;

import exercise.cloud.userservice.dto.UserDto;
import exercise.cloud.userservice.entity.User;
import exercise.cloud.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setEncryptedPassword(passwordEncoder.encode(userDto.getEncryptedPassword()));
        userRepository.save(User.createUser(userDto));
        return userDto;
    }

    @Override
    public UserDto getUserById(String userId) {
        return
                UserDto.createUserDto(
                        userRepository.findByUserId(userId).orElseThrow(RuntimeException::new)
                );
    }

    @Override
    public List<UserDto> getUserByAll() {
        return userRepository.findAllBy().stream().map(user -> UserDto.createUserDto(user)).collect(Collectors.toList());
    }

    @Override
    public UserDto getUserByEmail(String email) {
        return
                UserDto.createUserDto(
                        userRepository.findByEmail(email).orElseThrow(()->new RuntimeException())
                );
    }
}

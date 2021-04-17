package exercise.cloud.userservice.entity;

import exercise.cloud.userservice.dto.UserDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 50)
    private String email;
    private String name;
    private String userId;
    private String password;

    private User(UserDto userDto) {
        email = userDto.getEmail();
        name = userDto.getName();
        userId = userDto.getUserId();
        password = userDto.getEncryptedPassword();
    }


    public static User createUser(UserDto userDto) {
        return new User(userDto);
    }
}

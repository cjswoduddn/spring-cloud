package exercise.cloud.userservice.vo;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class RequestUser {
    private String email;
    private String name;
    private String password;
}

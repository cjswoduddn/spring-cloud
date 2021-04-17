package exercise.cloud.userservice.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import exercise.cloud.userservice.dto.UserDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseUser {
    private String email;
    private String name;
    private String userId;

    private List<ResponseOrder> orders;

    public ResponseUser(UserDto userDto) {
        email = userDto.getEmail();
        name = userDto.getName();
        userId = userDto.getUserId();
        orders = userDto.getOrders();
    }
}

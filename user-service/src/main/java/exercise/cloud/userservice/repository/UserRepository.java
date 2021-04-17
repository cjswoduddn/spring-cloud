package exercise.cloud.userservice.repository;

import exercise.cloud.userservice.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findAllBy();

    Optional<User> findByUserId(String userId);
    Optional<User> findByEmail(String email);
}

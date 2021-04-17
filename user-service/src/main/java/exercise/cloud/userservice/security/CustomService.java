package exercise.cloud.userservice.security;

import exercise.cloud.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class CustomService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User(
                userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username)).getEmail(),
                userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username)).getPassword(),
                true, true, true, true, new ArrayList<>()
        );
    }
}

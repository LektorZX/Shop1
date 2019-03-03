package root.service.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import root.DTO.UserDto;
import root.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    Long save(UserDto user);

    User findById(Long primaryKey);

    List<User> findAll();

    void delete(User entity);

    void deleteById(Long primaryKey);

    void update(User user);

    Optional<User> findByName(String name);

}

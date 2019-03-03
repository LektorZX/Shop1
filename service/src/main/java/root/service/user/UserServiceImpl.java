package root.service.user;

import lombok.Data;
import root.DTO.UserDto;
import root.converter.DtoConverter;
import root.converter.UserDetailsConverter;
import root.dao.user.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import root.entity.User;

import java.util.List;
import java.util.Optional;
@Data
@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final UserDetailsConverter detailsConverter;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return userDao.findByName(name)
                .map(detailsConverter::convert)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
    @Override
    public Long save(UserDto userDto){
        User user = DtoConverter.getInstance().UserDtoConvertToUser(userDto);
        //password--------------------------------------------------------------
        return userDao.save(user);
    }
    @Override
    public User findById(Long primaryKey){

        return userDao.findById(primaryKey);
    }
    @Override
    public List<User> findAll(){
        return userDao.findAll();
    }
    @Override
    public void delete(User entity){
        userDao.delete(entity);
    }
    @Override
    public void deleteById(Long primaryKey){
        User byId = userDao.findById(primaryKey);
        userDao.delete(byId);
    }
    @Override
    public void update(User user){
        userDao.update(user);
    }

    @Override
    public Optional<User> findByName(String name) {
        return userDao.findByName(name);
    }


}

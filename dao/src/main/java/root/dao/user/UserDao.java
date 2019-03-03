package root.dao.user;

import root.dao.base.BaseDao;
import root.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao extends BaseDao<User> {

    Optional<User> findByName(String name);


}

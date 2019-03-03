package root.dao.user;

import root.dao.base.BaseDaoImpl;
import root.entity.Role;
import root.entity.User;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;
import java.util.Set;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    @Override
    public Optional<User> findByName(String name) {
        return getCurrentSession()
                .createQuery("select u from User u where u.name = :name", User.class)
                .setParameter("name", name)
                .getResultList()
                .stream()
                .findFirst();
    }

}

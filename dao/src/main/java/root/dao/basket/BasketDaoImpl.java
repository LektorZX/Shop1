package root.dao.basket;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import root.entity.Basket;
import java.util.List;

@Repository
public class BasketDaoImpl implements BasketDao {
    @Autowired
    private SessionFactory sessionFactory;
    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(Basket basket) {
        sessionFactory.getCurrentSession().save(basket);
    }

    @Override
    public void deleteById(@Param("id")Long aLong) {
        NativeQuery nativeQuery = sessionFactory.getCurrentSession()
                .createNativeQuery("delete  from online_store.basket where order_id=?");
        nativeQuery.setParameter(1, aLong).executeUpdate();
    }

    @Override
    public void delete(Basket basket) {
        sessionFactory.getCurrentSession().delete(basket);
    }

    @Override
    public void findListProductByBasketId(Long idBasket) { }

    @Override
    public List<Basket> findBasketsByUserId(Long userId) {
        return getCurrentSession()
                .createQuery("select u from Basket u where u.userId = :userId", Basket.class)
                .setParameter("userId", userId)
                .list();
    }
}
